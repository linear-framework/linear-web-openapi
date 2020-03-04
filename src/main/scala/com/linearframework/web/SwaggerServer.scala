package com.linearframework.web

import com.linearframework.web.internal._
import com.linearframework.web.openapi.OpenApiCompiler
import com.linearframework.web.openapi.model._

/**
 * Convenience methods for configuring and starting a [[com.linearframework.web.Server]] with OpenAPI support.
 */
object SwaggerServer {

  /**
   * Configures a new server to auto-scan for Controllers, Filters, and other
   * Components in the given package
   * @param pkg the package to scan for server components
   */
  def autoScan(pkg: String): Configuration = {
    Configuration(pkg)
  }

  case class Configuration(
    override val autoScan: String,
    protected val openApi: Option[OpenApiConfiguration] = None
  ) extends ServerConfiguration(autoScan) {
    private val conf = this

    /**
     * Enables OpenAPI (Swagger) documentation
     * @param title the application name
     * @param version the application version
     * @param path (optional) the path on which OpenAPI JSON should be served.  Default is "/openapi.json".
     * @param info (optional) additional customization for the OpenAPI Info object
     */
    def openApi(title: String, version: String, path: String = "/openapi.json", info: OpenApiInfo => OpenApiInfo = identity): Configuration = {
      val openApi = OpenApiInfo(title = title, version = version)
      val existing = this.openApi.getOrElse(OpenApiConfiguration(path, openApi, Map()))
      this.copy(
        openApi = Option(
          existing.copy(
            path = path,
            info = info(openApi)
          )
        )
      )
    }

    /**
     * Describes the authentication methods (basic, bearer, API key, OAuth2, and/or OpenID Connect) available to the server.
     * This must be called after `openApi()`.
     */
    def openApiAuth(auths: (String, OpenApiSecurityScheme)*): Configuration = {
      this.openApi match {
        case None =>
          throw new IllegalStateException("No OpenAPI definition is available. Call openApi() before calling openApiAuth().")
        case Some(config) =>
          this.copy(
            openApi = Some(
              config.copy(
                authentication = auths.toMap
              )
            )
          )
      }
    }

    /**
     * Builds and starts the server
     */
    def start(): Server = {
      val server =
        new Server {
          override protected val autoScanPackage: String = conf.autoScan
          override protected val scheme: String = conf.scheme
          override protected val host: String = conf.host
          override protected val port: Int = conf.port
          override protected val corsAllowedOrigin: Option[String] = conf.corsOrigin
          override protected val logRequests: Boolean = conf.logRequests
          override protected val staticFiles: Option[StaticFilesConfiguration] = conf.staticFiles
          override protected val ssl: Option[SslConfiguration] = conf.ssl
          override private[web] val registry = conf.registry
          override private[web] val deserializers = conf.deserializers

          override protected def afterControllersRegistered(): Unit = {
            super.afterControllersRegistered()

            val info = openApi.get.info
            val authentication = openApi.get.authentication

            val url = {
              if ((scheme == "http" && port == 80) || (scheme == "https" && port == 443)) {
                s"$scheme://$host"
              }
              else {
                s"$scheme://$host:$port"
              }
            }

            val controllers = Utils.findScalaObjects[Swagger](autoScanPackage)
            val documentation = controllers.flatMap(_.swaggerRegistry.map(_.documentation)).flatten.toList

            val swagger =
              OpenApiCompiler
                .compile(info, authentication, documentation)
                .copy(
                  servers = Some(List(
                    OpenApiServer(url = url)
                  ))
                )

            val swaggerController = new OpenApiController(openApi.get.path, swagger)
            swaggerController.setServer(this)
            swaggerController.register()

            log.info("Registered Swagger Endpoint:")
            logTable(Seq("METHOD", "PATH"), Seq(Seq("GET", openApi.get.path)))
          }
        }

      server.start()
      server
    }
  }
}

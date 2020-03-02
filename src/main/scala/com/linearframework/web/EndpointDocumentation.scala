package com.linearframework.web

case class EndpointDocumentation (
  private[web] val method: HttpVerb,
  private[web] val path: String,
  private[web] val summary: String,
  private[web] val description: Option[String] = None,
  private[web] val id: Option[String] = None,
  private[web] val tags: List[String] = List(),
  private[web] val parameters: List[ParameterDocumentation] = List(),
  private[web] val request: Option[RequestDocumentation] = None,
  private[web] val response: Map[HttpStatus, ResponseDocumentation] = Map(),
  private[web] val deprecated: Boolean = false,
  private[web] val authentication: Map[String, List[String]] = Map()
) {

  /**
    * Sets the long-form description of this endpoint
    */
  def description(description: String): EndpointDocumentation = {
    this.copy(description = Option(description))
  }

  /**
    * Sets a unique identifier of this endpoint which can be used throughout the documentation
    */
  def id(id: String): EndpointDocumentation = {
    this.copy(id = Option(id))
  }

  /**
    * Tags this endpoint's documentation with the given string(s).
    */
  def tags(tags: String*): EndpointDocumentation = {
    this.copy(tags = this.tags ++ tags)
  }

  /**
    * Adds parameters to this endpoint's documentation.
    * Parameters may be:
    * <ul>
    *   <li>Query parameters</li>
    *   <li>Path parameters</li>
    *   <li>Values found in Headers</li>
    *   <li>Values found in Cookies</li>
    * </ul>
    */
  def parameters(params: ParameterDocumentation*): EndpointDocumentation = {
    this.copy(parameters = params.toList)
  }

  /**
    * Documents all possible request bodies for this endpoint
    */
  def request(description: String, required: Boolean = false)(types: (ContentType, SchemaDocumentation)*): EndpointDocumentation = {
    this.copy(
      request = Some(
        RequestDocumentation(
          description = description,
          required = required,
          contentTypes = types.toMap
        )
      )
    )
  }

  /**
    * Documents a possible response from this endpoint
    */
  def response(status: HttpStatus, description: String)(types: (ContentType, SchemaDocumentation)*): EndpointDocumentation = {
    this.copy(
      response = response + (
        status -> response.getOrElse(status, ResponseDocumentation(description)).copy (
          contentTypes = types.toMap
        )
      )
    )
  }

  /**
    * Marks this endpoint as deprecated
    */
  def deprecate(): EndpointDocumentation = {
    this.copy(deprecated = true)
  }

  /**
    * Marks this endpoint as requiring authentication
    * @param scheme the name of a previously-defined authentication scheme
    * @param scopes the valid scopes of authentication (if OAuth or OpenID Connect)
    * @return
    */
  def authentication(scheme: String, scopes: List[String] = List()): EndpointDocumentation = {
    this.copy(
      authentication = authentication + (scheme -> scopes)
    )
  }

}

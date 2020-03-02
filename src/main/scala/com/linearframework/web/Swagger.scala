package com.linearframework.web

import scala.collection.mutable

/**
 * Adds Swagger documentation capabilites to a [[com.linearframework.web.Controller]].
 *
 * {{{
 * object GreetingsController extends Controller with Swagger {
 *    PUT("/api/libraries/:libraryId/book") { (request, response) =>
 *       ...
 *    }.documentation("Adds a new book to the given library") {
 *       _.parameters(
 *          PathParam("libraryId").description("ID of the Library").asLong(),
 *          QueryParam("librarianId").description("ID of the Librarian filing the book").asLong { _.require() }
 *        )
 *        .request("Record of the Book to be filed", required = true)(
 *          JSON -> Body.Object("Book Record")
 *                       .withString("author")
 *                       .withString("title")
 *                       .withString("type", _.options("Fiction", "Non-Fiction"))
 *                       .withDate("publicationDate")
 *                       .withArray("tags", _.asStringArray())
 *        )
 *        .response(OK, "Book stored successfully")(
 *          PLAIN_TEXT -> Body.String("Success message").example("OK")
 *        )
 *        .response(NOT_FOUND, "Library or Librarian ID not found")(
 *          JSON -> Body.Object("Error message").withString("message", _.example("Library not found"))
 *        )
 *        .authentication("jwt")
 *    }
 * }
 * }}}
 */
trait Swagger extends Controller {
  private[web] val swaggerRegistry = mutable.Set[SwaggerEndpoint]()

  private[web] class SwaggerEndpoint(
    val webMethod: WebMethod,
    val documentation: Option[EndpointDocumentation]
  ) {
    override def hashCode(): Int = {
      webMethod.hashCode()
    }
    override def equals(obj: Any): Boolean = {
      obj match {
        case that: SwaggerEndpoint =>
          this.webMethod == that.webMethod
        case _ =>
          false
      }
    }
  }

  protected implicit class WebMethodImplicits(webMethod: WebMethod) {
    /**
     * Builds documentation for this endpoint
     * @param summary a brief summary of this endpoint's purpose
     * @param buildDocumentation the method by which the endpoint documentation is built
     */
    def documentation(summary: String)(buildDocumentation: EndpointDocumentation => EndpointDocumentation): Unit = {
      val doc = buildDocumentation(EndpointDocumentation(webMethod.method, webMethod.path, summary))
      val endpoint = new SwaggerEndpoint(webMethod, Option(doc))
      swaggerRegistry -= endpoint
      swaggerRegistry += endpoint
    }
  }

}

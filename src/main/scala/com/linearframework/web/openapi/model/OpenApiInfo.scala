package com.linearframework.web.openapi.model

/**
  * @see [[com.linearframework.web.openapi.model.OpenApiDocument]]
  */
case class OpenApiInfo(
  title: String,
  description: Option[String] = None,
  termsOfService: Option[String] = None,
  contact: Option[OpenApiContact] = None,
  license: Option[OpenApiLicense] = None,
  version: String
) {

  /**
    * Sets the description
    */
  def description(description: String): OpenApiInfo = {
    this.copy(description = Option(description))
  }

  /**
    * Sets the terms of service
    */
  def termsOfService(url: String): OpenApiInfo = {
    this.copy(termsOfService = Option(url))
  }

  /**
    * Sets the contact information
    */
  def contact(name: String, email: String, url: String): OpenApiInfo = {
    this.copy(contact = Option(OpenApiContact(name = Option(name), url = Option(url), email = Option(email))))
  }

  /**
    * Sets the contact information
    */
  def contact(name: String, email: String): OpenApiInfo = {
    this.copy(contact = Option(OpenApiContact(name = Option(name), email = Option(email))))
  }

  /**
    * Sets the contact information
    */
  def contact(email: String): OpenApiInfo = {
    this.copy(contact = Option(OpenApiContact(email = Option(email))))
  }

  /**
    * Sets the license
    */
  def license(name: String, url: String): OpenApiInfo = {
    this.copy(license = Option(OpenApiLicense(name, Option(url))))
  }

  /**
    * Sets the license
    */
  def license(name: String): OpenApiInfo = {
    this.copy(license = Option(OpenApiLicense(name)))
  }

}

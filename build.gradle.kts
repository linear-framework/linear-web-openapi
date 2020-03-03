plugins {
  `java-library`
  scala
  `maven-publish`
}

group = "com.linearframework"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
  jcenter()
  mavenCentral()
  maven {
    url = uri("https://maven.pkg.github.com/linear-framework/linear-web")
    credentials {
      username = System.getenv("GITHUB_USER")
      password = System.getenv("GITHUB_TOKEN")
    }
  }
}

dependencies {
  implementation("org.scala-lang:scala-library:2.13.1")
  api("com.linearframework:web:" + version)
  api("com.fasterxml.jackson.module:jackson-module-scala_2.13:2.10.2")

  testImplementation("junit:junit:4.13")
  testImplementation("org.scalatest:scalatest_2.13:3.1.1")
  testImplementation("org.scalatestplus:junit-4-12_2.13:3.1.1.0")

  testRuntimeOnly("org.slf4j:slf4j-simple:1.7.25")
}

tasks.named<Jar>("jar") {
  from(sourceSets["main"].output)
  from(sourceSets["main"].allSource)
}

publishing {
  repositories {
    maven {
      name = "LinearWebOpenApi"
      url = uri("https://maven.pkg.github.com/linear-framework/linear-web-openapi")
      credentials {
        username = System.getenv("GITHUB_USER")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
  publications {
    create<MavenPublication>("PublishToGithub") {
      artifactId = "web-openapi"
      from(components["java"])
    }
  }
}
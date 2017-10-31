job "web-application" {

  # only run this job where JVM is equal or higher than 1.8
  constraint {
    attribute = "${driver.java.version}"
    operator  = ">="
    value     = "1.8.0"
  }

  task "web" {
	driver = "java"

	config {
	  jar_path    = "/var/wplex/apps/wplex-garagem/lib/wplex-garagem.jar"
	  jvm_options = ["-Xms256m", "-Xmx512m"]
	  args        = "-Dlogging.config=/var/wplex/apps/wplex-garagem/config/logback-spring.xml -Dspring.config.location=/var/wplex/apps/wplex-garagem/config/application-$ENV.properties"
	}

  }
}
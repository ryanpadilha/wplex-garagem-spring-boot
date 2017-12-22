#
# docker-container for Java 8 Application
#
# generate-image  : docker build -t wplex/garagem --build-arg VERSION=0.0.2 .
# run-container   : docker run -d -p 8080:8080 -v /var/wplex/conf/application-garagem.properties:/conf/application.properties --name w_garagem wplex/garagem
# container-limit : docker run --memory=750m --memory-swap=750m --oom-kill-disable -d -p 8080:8080 -v /var/wplex/conf/application-garagem.properties:/conf/application.properties --name w_garagem_me wplex/garagem
#

FROM debian/server-jre8
LABEL maintainer="Ryan Padilha <ryan.padilha@wplex.com.br>"

# define commonly used variables
ENV APP_DIR /var/wplex/apps/wplex-garagem

ARG VERSION
ENV VERSION $VERSION

# define working directory
RUN mkdir -p /var/wplex/apps
WORKDIR /var/wplex/apps
ADD target/wplex-garagem-$VERSION-dev.tar.gz .

EXPOSE 8080
ENTRYPOINT java -XX:+UseG1GC -Xms64m -Xmx128m -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=/conf/application.properties -jar $APP_DIR/lib/wplex-garagem.jar

# Maven build container

#FROM maven:3.6.3-openjdk-8 AS maven_build

#COPY pom.xml /tmp/

#COPY src /tmp/src/

#WORKDIR /tmp/

#RUN mvn clean package

FROM openjdk:8-jdk-alpine

MAINTAINER Madhankumar madhan2007.psgtech@gmail.com

ENV SERVER_PORT=8080
ENV DEBUG_PORT=8000
ENV APP_HOME /app
ENV SPRING_BOOT_USER spring-boot
ENV SPRING_BOOT_GROUP spring-boot
ENV VERSION 0.0.1-SNAPSHOT
ENV ARTIFACT_NAME pretty-url-$VERSION.jar

COPY entrypoint.sh $APP_HOME/entrypoint.sh

VOLUME /tmp

RUN apk update && apk add bash

RUN addgroup -S $SPRING_BOOT_USER && adduser -S -g $SPRING_BOOT_GROUP $SPRING_BOOT_USER && \
 chmod 555 $APP_HOME/entrypoint.sh && sh -c 'touch $APP_HOME/$ARTIFACT_NAME'

EXPOSE $SERVER_PORT
EXPOSE $DEBUG_PORT

WORKDIR $APP_HOME
USER $SPRING_BOOT_USER

COPY /target/pretty-url-$VERSION.jar $APP_HOME/$ARTIFACT_NAME

ENTRYPOINT ["./entrypoint.sh"]
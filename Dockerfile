FROM openjdk:17-jdk-alpine

LABEL maintainer="adsoft@live.com.mx"

VOLUME /tmp

EXPOSE 8192

ARG JAR_FILE=target/calculator-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} antlr-api.jar

#RUN
ENTRYPOINT  ["java",  "-Djava.security.egd=file:/dev/./urandom", "-jar", "/antlr-api.jar"]

FROM openjdk:17-jdk-alpine
MAINTAINER dice-research.org

RUN apk --no-cache add curl

RUN wget https://github.com/dice-group/IGUANA/releases/download/v3.3.0/iguana-3.3.0.zip && \
    unzip iguana-3.3.0.zip

COPY target/benchmark-service-0.1.jar benchmark-service-0.1.jar
ENTRYPOINT ["java", "-jar", "/benchmark-service-0.1.jar"]
FROM eclipse-temurin:17.0.7_7-jre-alpine

RUN apk --update --no-cache add curl

RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.26.0/opentelemetry-javaagent.jar --output opentelemetry-javaagent-all.jar
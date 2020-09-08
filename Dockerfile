FROM gradle:6.6.0-jdk11 AS build

ARG USER
ARG PASSWORD

ENV GITHUB_USERNAME=$USER
ENV GITHUB_TOKEN=$PASSWORD

RUN mkdir -p /workspace
WORKDIR /workspace
COPY . /workspace
RUN chmod +x gradlew
RUN ./gradlew --no-daemon build

FROM openjdk:11-jdk

ENV TARGET_ENV=dev

COPY --from=build /workspace/api/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-DenvTarget=${TARGET_ENV}", "-jar","/app.jar"]
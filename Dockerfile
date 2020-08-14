FROM gradle:6.6.0-jdk11 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY . /workspace
RUN chmod +x gradlew
RUN ./gradlew build

FROM openjdk:11-jdk
COPY application.properties application.properties
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
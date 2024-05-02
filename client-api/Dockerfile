#FROM openjdk:11-jre-slim
#FROM adoptopenjdk/openjdk11:alpine-jre
#WORKDIR /app
#COPY build/libs/client-api-all.jar /app/client-api.jar
#EXPOSE 8080
#CMD ["java", "-jar", "client-api.jar"]

FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/client-api.jar
ENTRYPOINT ["java","-jar","/app/client-api.jar"]

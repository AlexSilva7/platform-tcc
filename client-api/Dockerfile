FROM openjdk:11-jre-slim
WORKDIR /app
COPY build/libs/client-api-0.0.1.jar /app/client-api.jar
EXPOSE 8080
CMD ["java", "-jar", "client-api.jar"]

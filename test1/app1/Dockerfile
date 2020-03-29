# FROM adoptopenjdk/openjdk11:alpine-jre
FROM openjdk:8
WORKDIR /opt/app
ARG JAR_FILE=target/app1.jar
COPY ${JAR_FILE} app1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app1.jar"]

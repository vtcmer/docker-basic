FROM openjdk:8
WORKDIR /opt/app
ARG JAR_FILE=target/app-db.jar
COPY ${JAR_FILE} app-db.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app-db.jar"]
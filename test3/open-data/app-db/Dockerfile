FROM openjdk:8
WORKDIR /opt/app
ARG JAR_FILE=target/open-data-db.jar
COPY ${JAR_FILE} open-data-db.jar
ENTRYPOINT ["java","-jar","open-data-db.jar"]
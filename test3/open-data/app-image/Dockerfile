FROM openjdk:8
WORKDIR /opt/app
ARG JAR_FILE=target/open-data-image.jar
COPY ${JAR_FILE} open-data-image.jar
ENTRYPOINT ["java","-jar","open-data-image.jar"]
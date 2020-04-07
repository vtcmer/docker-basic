FROM openjdk:8
WORKDIR /opt/app
ARG JAR_FILE=target/open-data-extractor.jar
COPY ${JAR_FILE} open-data-extractor.jar
ENTRYPOINT ["java","-jar","open-data-extractor.jar"]
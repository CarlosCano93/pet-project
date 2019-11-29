FROM maven:3-jdk-13-slim

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/pet-project-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} pet-project.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pet-project.jar"]


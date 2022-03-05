FROM maven:3-openjdk-17-slim
EXPOSE 8081
WORKDIR /root
COPY . ./
RUN mv $(find /root/target/ -type f -name '*.jar' -a ! -name '*-javadoc.jar' -a ! -name '*-sources.jar' -a ! -name '*-tests.jar') /root/app.jar
FROM openjdk:17
WORKDIR /var/app
COPY --from=0 /root/app.jar /var/app/app.jar
ENV ALLOWED_ORIGINS *
CMD ["java", "-jar", "app.jar"]
FROM maven:3-openjdk-17-slim

ENV POKEAPI_URL='https://pokeapi.co/api/v2'
ENV ENV='local'
ENV SENTRY_DNS='https://7e7aad618ed246d3a972ae2593c3834a@o325857.ingest.sentry.io/1832401'

WORKDIR /root
COPY . ./
RUN mv $(find /root/target/ -type f -name '*.jar' -a ! -name '*-javadoc.jar' -a ! -name '*-sources.jar' -a ! -name '*-tests.jar') /root/app.jar
FROM openjdk:17
WORKDIR /var/app
COPY --from=0 /root/app.jar /var/app/app.jar
ENV ALLOWED_ORIGINS *
CMD ["java", "-jar", "app.jar"]
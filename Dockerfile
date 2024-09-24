FROM openjdk:17-oracle
LABEL authors="karol"

COPY target/monolith-bank-app-0.0.1-SNAPSHOT.jar /app/monolith-bank-app-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "monolith-bank-app-0.0.1-SNAPSHOT.jar"]
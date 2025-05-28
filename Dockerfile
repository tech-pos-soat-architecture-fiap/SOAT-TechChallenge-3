ARG BASE_IMAGE=24-slim

FROM openjdk:${BASE_IMAGE} AS builder

WORKDIR /app

COPY . .

RUN apt-get update

RUN apt-get install -y maven

RUN mvn clean package -DskipTests=true -f pom.xml

FROM openjdk:${BASE_IMAGE} AS runtime

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/TechFood.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/TechFood.jar"]
FROM maven:3.8-openjdk-8 as builder

WORKDIR /usr/src/app

COPY . .

RUN mvn clean package -DskipTests=true

FROM openjdk:8-jdk

LABEL maintainer="Gabriel masschelein"

EXPOSE 8448

WORKDIR /usr/src/app

COPY --from=builder /usr/src/app/target/java*.jar java.jar

CMD ["java","-jar","java.jar"]
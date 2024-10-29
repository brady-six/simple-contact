FROM maven:latest

WORKDIR /app

COPY . .

RUN mvn clean package

COPY ./target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

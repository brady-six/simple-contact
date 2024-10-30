FROM maven:latest

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

RUN mv target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

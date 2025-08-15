# Giai đoạn 1: Build ứng dụng bằng Maven
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Giai đoạn 2: Tạo image cuối cùng chỉ chứa file .jar đã được build
FROM openjdk:17-slim-bullseye
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY backend ./backend
COPY frontend ./frontend
RUN chmod +x backend/mvnw \
    && cd backend \
    && ./mvnw -B package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/backend/target/reservacampus-backend-1.0-SNAPSHOT.jar /app/reservacampus.jar
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/reservacampus.jar"]

FROM maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /app

COPY . .

RUN mvn package -Dmaven.test.skip=true

FROM maven:3.9.9-eclipse-temurin-23

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080

EXPOSE ${PORT}

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
CMD curl http://localhost:${PORT}/status || exit 1

ENTRYPOINT SERVER_PORT=${PORT} java -jar /app/app.jar -Dserver.port=${PORT}
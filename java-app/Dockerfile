# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# сначала копируем только файлы сборки для кэширования зависимостей
COPY pom.xml .
COPY src ./src

# собираем jar
RUN mvn -q -DskipTests package

# ---------- run stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# (необязательно) создать непривилегированного пользователя
RUN useradd -r -s /bin/false appuser
USER appuser

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
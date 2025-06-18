FROM eclipse-temurin:21-jdk as build

WORKDIR /app-crud
COPY . /app-crud

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app-crud/app.jar .

RUN useradd runtime
USER runtime

ENTRYPOINT ["java", "-jar", "app.jar"]

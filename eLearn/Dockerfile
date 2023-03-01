FROM eclipse-temurin:17-jdk-alpine
RUN mkdir -p /app/server
WORKDIR /app/server

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]


FROM maven:3.8-eclipse-temurin-17-jdk-alpine AS build
RUN mkdir -p /app/server
COPY . /app/server
WORKDIR /app/server
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine
RUN apk add dumb-init
RUN mkdir /app/server
RUN addgroup --system juser && adduser -S -s /bin/false -G juser juser
COPY --from=build /app/server/target/eLearn-0.0.1-SNAPSHOT.jar /app/eLearn-application.jar
WORKDIR /app
RUN chown -R juser:juser /app/server
USER juser
CMD "dumb-init" "java" "-jar" "eLearn-application.jar"
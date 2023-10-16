FROM eclipse-temurin:17-jdk as builder
ADD . /code
RUN chmod +x /code/gradlew
RUN cd code && ./gradlew build
FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /code/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
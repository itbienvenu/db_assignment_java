FROM amazoncorretto:17-al2-jdk
VOLUME /tmp
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
ENTRYPOINT ["java","-jar","target/db-programming-assignment-0.0.1-SNAPSHOT.jar"]

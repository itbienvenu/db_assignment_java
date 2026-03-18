Package application


cd my-springboot-app
./mvnw clean package -DskipTests



BUild image
docker build -t my-springboot-app .

RUn container

docker run -p 8081:8080 my-springboot-app

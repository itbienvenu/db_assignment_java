BUILDING THE IMge 


docker build -t my-springboot-app .





RUNNING THE CONTAINER

docker run -p 8081:8081 my-springboot-app


CHECKING THE LIVE URL

http://localhost:8081/hello
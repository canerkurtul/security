https://spring.io/guides/gs/spring-boot-docker/

docker build -t caner-security/security .
docker run -p 8082:8080 caner-security/security
docker logs -f 7d416066a225

docker image ls
docker container ls
docker stop 7d416066a225 | docker start 7d416066a225 | docker restart 7d416066a225
docker rm -f 7d416066a225






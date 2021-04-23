
mvn clean install -DskipTests

docker build -t caner-security/security .

kubectl apply -f k8s-stateful.yaml



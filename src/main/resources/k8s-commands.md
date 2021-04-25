#### https://minikube.sigs.k8s.io/docs/start/
#### https://gitlab.com/nanuchi/youtube-tutorial-series/-/blob/master/basic-kubectl-redis-slave/cli-redis-slave.md

### create minikube cluster
`minikube start --vm-driver=hyperkit`

`kubectl get nodes`

`minikube status`

`kubectl version`

### delete cluster and restart in debug mode
`minikube delete`

`minikube start --vm-driver=hyperkit --v=7 --alsologtostderr`

### kubectl redis-slave
`kubectl get nodes`

`kubectl get pod`

`kubectl get services`

`kubectl create deployment nginx-depl --image=nginx`

`kubectl get deployment`

`kubectl get replicaset`

`kubectl edit deployment nginx-depl`

### debugging
`kubectl logs {pod-name}`

`kubectl exec -it {pod-name} -- bin/bash`

### create mongo deployment
`kubectl create deployment mongo-depl --image=mongo`

`kubectl logs mongo-depl-{pod-name}`

`kubectl describe pod mongo-depl-{pod-name}`

### delete deplyoment
`kubectl delete deployment mongo-depl`

`kubectl delete deployment nginx-depl`

### create or edit config file
`vim nginx-deployment.yaml`

`kubectl apply -f nginx-deployment.yaml`

`kubectl get pod`

`kubectl get deployment`

### delete with config
`kubectl delete -f nginx-deployment.yaml`

#Metrics

`kubectl top` The kubectl top command returns current CPU and memory usage for a cluster’s pods or nodes, or for a particular pod or node if specified.

minikube start
# minikube -p minikube docker-env
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://127.0.0.1:65182
SET DOCKER_CERT_PATH=C:\Users\ttmkurtul\.minikube\certs
SET MINIKUBE_ACTIVE_DOCKERD=minikube
minikube ssh
sudo ip link set docker0 promisc on

mvn clean install -DskipTests
docker build -t caner-security/security .

# kubectl port-forward kafka-broker0-56545d949b-dbr98 9092:9092
# kubectl port-forward redis-59d58d6cff-fvxr8 6379:6379

kubectl port-forward deployment/kafka-broker0 9092:9092
kubectl port-forward deployment/redis 6379:6379
# kubectl port-forward pod/caner-security-deployment-0 8080:8080




kubectl apply -f k8s.yaml
kubectl get service
minikube service caner-security-service --url

# https://medium.com/swlh/how-to-run-locally-built-docker-images-in-kubernetes-b28fbc32cc1d
minikube docker-env

kubectl logs -f pod/caner-security-deployment-65f66f75c9-b7mw6
kubectl describe service/caner-security-service
kubectl delete pod/caner-security-deployment-65f66f75c9-xgvm9

kubectl exec -ti kafka-cat-bf44dd754-qtkpm -- bash
kafkacat -P -b kafka-service:9092 -t test
kafkacat -b kafka-service:9092 -t test
# ERROR: Topic test error: Broker: Leader not available
# SOLUTION: https://stackoverflow.com/questions/45748536/kafka-inaccessible-once-inside-kubernetes-minikube
minikube ssh
sudo ip link set docker0 promisc on

# https://kubernetes.io/docs/reference/kubectl/cheatsheet/
# https://kubernetes.io/docs/tasks/access-application-cluster/create-external-load-balancer/
# https://kubernetes.io/docs/concepts/services-networking/service/

kubectl get ns
kubectl get all -n default

# gamov.dev/xmas-workshop
# https://github.com/confluentinc/demo-scene/tree/master/streaming-movies-workshop/
# https://github.com/Yolean/kubernetes-kafka

# https://www.youtube.com/watch?v=pqD4t-7g1BM   KUBERNETES ZOOKEEPER KAFKA
# https://github.com/jamesmedice/docker4kafka/
# https://github.com/jamesmedice/docker4zookeeper
# http://www.smartjava.org/content/minimal-kafka-instance-for-k8s/
# https://medium.com/accenture-the-dock/when-how-to-deploy-kafka-on-kubernetes-b18f5270db63
# https://medium.com/rahasak/kafka-zookeeper-cluster-on-kubernetes-43a4aaf27dbb

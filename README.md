Akka Http to Kafka Microservice
=================================

Install docker, docker-machine and docker-compose

1) Connect to 'default' machine, see docker docs on how to create machine in virtualbox

akka-http-kafka-streams> eval "$(docker-machine env default)"

2) CD into project and use SBT to build and publish to local Docker repo:

akka-http-kafka-streams> sbt clean docker:publishLocal

3) Run docker compose to launch

ClusterShardedApp> docker-compose up

4) Use rest client such as Postman to send http request:
Uri: http://192.168.99.100:8081/service/work
Content-Type: application/json
body:
{
    "name": "test123"
}

5) 'test123' will be returned in response body after it is published to worker_commands topic in Kafka

6) Messages in worker_commands topic can be viewed using Kafka Tool
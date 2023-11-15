FROM eclipse-temurin:21-jdk
COPY ./build/libs/DockerClient-1.0-SNAPSHOT-all.jar /client.jar

ENTRYPOINT ["java", "-jar", "./client.jar"]
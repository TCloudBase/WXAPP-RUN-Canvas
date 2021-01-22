
# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

CMD mvn compile &mvn package

# Run the web service on container startup.
#CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app/target/helloworld-0.0.1-SNAPSHOT.jar"]

ADD /target/*.jar /app.jar
# 暴露端口
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


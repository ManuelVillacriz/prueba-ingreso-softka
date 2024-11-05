FROM openjdk:17
VOLUME /temp
EXPOSE 9091
ADD ./target/cuenta-service-0.0.1-SNAPSHOT.jar cuenta-service.jar
ENTRYPOINT [ "java","-jar","/cuenta-service.jar" ]
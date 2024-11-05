FROM openjdk:17
VOLUME /temp
EXPOSE 9090
ADD ./target/cliente-service-0.0.1-SNAPSHOT.jar cliente-service.jar
ENTRYPOINT [ "java","-jar","/cliente-service.jar" ]
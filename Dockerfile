FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
EXPOSE 8090
ADD build/libs/virtualwallet-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "application.jar"]
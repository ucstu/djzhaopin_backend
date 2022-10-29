FROM maven:3.8.6-openjdk-18 as Build

WORKDIR /usr/share/djzhaopin_backend
COPY . .
RUN mvn package -f pom.xml -t 10C

FROM openjdk:18.0.2.1-jdk as Production

WORKDIR /usr/share/djzhaopin_backend
COPY --from=Build /usr/share/djzhaopin_backend/target/djzhaopin-0.0.1-SNAPSHOT.jar ./djzhaopin-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD [ "java", "-jar", "djzhaopin-0.0.1-SNAPSHOT.jar" ]

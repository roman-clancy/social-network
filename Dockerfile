FROM maven:3.8.7-eclipse-temurin-17-focal as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn package

FROM eclipse-temurin:17.0.4.1_1-jre
RUN mkdir /opt/app
COPY --from=build /usr/app/target/social-network-0.0.1.jar /opt/app/app.jar
ENTRYPOINT java -jar /opt/app/app.jar
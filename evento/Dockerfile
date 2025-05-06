FROM openjdk:24

WORKDIR /
COPY target/evento-0.0.1-SNAPSHOT.jar evento.jar
COPY Wallet_DSY2201 /app/oracle_wallet/
EXPOSE 8080

CMD [ "java", "-jar", "evento.jar" ]
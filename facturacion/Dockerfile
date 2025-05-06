FROM openjdk:24

WORKDIR /
COPY target/facturacion-0.0.1-SNAPSHOT.jar facturacion.jar
COPY Wallet_DSY2201 /app/oracle_wallet/
EXPOSE 8080

CMD [ "java", "-jar", "facturacion.jar" ]
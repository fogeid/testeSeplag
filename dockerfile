FROM openjdk:17-jdk-slim

# Copiar o arquivo JAR diretamente para a raiz do contêiner
COPY target/testeSeplag-0.0.1-SNAPSHOT.jar /app.jar

# Definir o comando padrão
CMD ["java", "-jar", "/app.jar"]
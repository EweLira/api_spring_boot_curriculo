FROM openjdk:17-jdk-alpine as build

# Definir o diretório de trabalho no container
WORKDIR /workspace/app

# Copiar o arquivo pom.xml e os arquivos de código fonte
COPY . .

# Construir o aplicativo com Maven
RUN apt-get install maven -y
RUN mvn clean install -DskipTests

# Segunda etapa da construção, para obter uma imagem mais limpa e leve
FROM openjdk:17-jdk-alpine
VOLUME /tmp


FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/back-end_SB_AOS-0.0.1-SNAPSHOT.jar /app/curriculo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/curriculo.jar"]

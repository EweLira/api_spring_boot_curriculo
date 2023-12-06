# Utilizar uma imagem base do OpenJDK para rodar a aplicação

FROM openjdk:17-jdk-alpine as build
 
# Definir o diretório de trabalho no container

WORKDIR /workspace/app
 
# Copiar o arquivo pom.xml e os arquivos de código fonte

COPY mvnw .

COPY .mvn .mvn

COPY pom.xml .

COPY src src
 
# Dar permissão de execução ao Maven Wrapper

RUN chmod +x ./mvnw
 
# Construir o aplicativo com Maven Wrapper

RUN ./mvnw clean install

 
# Segunda etapa da construção, para obter uma imagem mais limpa e leve

FROM openjdk:17-jdk-alpine

VOLUME /tmp
 
# Copiar o arquivo JAR do estágio de construção para o estágio de execução

COPY --from=build /workspace/app/target/back-end_SB_AOS-0.0.1-SNAPSHOT.jar app.jar
 
# Indicar que o container irá escutar na porta 8080

EXPOSE 8080
 
# Comando para executar a aplicação

ENTRYPOINT ["java","-jar","/app.jar"]

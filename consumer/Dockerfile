# Estágio de construção
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /workspace/app

COPY pom.xml .
# Primeiro baixa as dependências (cache eficiente)
RUN mvn dependency:go-offline -B

COPY src ./src
# Construção do projeto
RUN mvn clean package -DskipTests

# Estágio de execução
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copia o JAR específico (não use wildcard *)
COPY --from=build /workspace/app/target/*.jar app.jar

# Configurações de memória
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0 -XX:+ExitOnOutOfMemoryError"

ENTRYPOINT ["java", "-jar", "app.jar"]
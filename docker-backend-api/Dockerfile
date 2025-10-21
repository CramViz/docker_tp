# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk-alpine AS myapp-build
ENV MYAPP_HOME=/opt/myapp
WORKDIR $MYAPP_HOME

# Installer Maven
RUN apk add --no-cache maven

# Copier pom + sources
COPY pom.xml .
# Pré-télécharger les dépendances pour le cache si pom.xml n'a pas changé
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src

# Build du jar
RUN mvn -q -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:21-jdk-alpine
ENV MYAPP_HOME=/opt/myapp
WORKDIR $MYAPP_HOME
COPY --from=myapp-build $MYAPP_HOME/target/*.jar $MYAPP_HOME/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","myapp.jar"]

# Copier le jar produit
COPY --from=myapp-build $MYAPP_HOME/target/*.jar $MYAPP_HOME/myapp.jar

# Exposer le port interne
EXPOSE 8080

ENTRYPOINT ["java","-jar","myapp.jar"]

# Utiliza una imagen base de Java
FROM openjdk:17



# Copia el archivo JAR de la aplicación en el contenedor
COPY target/block7-crud-validation.jar crud-validation.jar



# Expone el puerto en el que la aplicación se ejecuta dentro del contenedor
EXPOSE 8080



# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "crud-validation.jar"]
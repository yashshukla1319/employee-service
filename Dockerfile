FROM openjdk:11
ARG JAR_FILE=C:\Users\Yash.Shukla\Documents\GitHub\employee-service\target\employee-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:8-jre-alpine

# copy application JAR
COPY notifier-packt-*.jar /app/notifier-packt.jar

# copy properties file
COPY application.yml /app/application.yml

# Establish environment variables
ENV ENCRYPT_MASTER_PASSWORD ""
ENV MAIL_PASSWORD ""

# specify default command
CMD ["java", "-jar", "/app/notifier-packt.jar"]
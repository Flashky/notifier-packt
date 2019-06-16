FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/notifier-packt-*.jar /app/notifier-packt.jar

# Establish environment variables
ENV ENCRYPT_MASTER_PASSWORD ""
ENV MAIL_PASSWORD ""
ENV twitter.accessSecret ""
ENV twitter.accessToken ""
ENV twitter.consumerKey ""
ENV twitter.consumerSecret "" 

# specify default command
CMD ["java", "-jar", "/app/notifier-packt.jar"]
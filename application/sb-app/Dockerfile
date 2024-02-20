FROM adoptopenjdk/openjdk11
# Copy fat-jar
COPY target/sb-app-*-fat.jar /opt/lib/sb/sb-app-fat.jar

USER root

# Configure listen ports
#EXPOSE 9201

# Run web-server
CMD java -jar /opt/lib/sb/sb-app-fat.jar
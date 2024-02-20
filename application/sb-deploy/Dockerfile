FROM adoptopenjdk/openjdk11
# Copy fat-jar
COPY target/sb-deploy-*-fat.jar /opt/lib/sb/sb-deploy-fat.jar

USER root

# Configure listen ports
#EXPOSE 9201

# Run web-server
CMD java -jar /opt/lib/sb/sb-deploy-fat.jar
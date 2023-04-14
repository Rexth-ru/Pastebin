FROM amazoncorretto:11
RUN mkdir -p /db
ENV SPRING_DATASOURCE_URL=jdbc:h2:/db/database
COPY target/pastebin-0.0.1-SNAPSHOT.jar /pastebin.jar
CMD java -jar pastebin.jar
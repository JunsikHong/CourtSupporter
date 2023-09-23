FROM azul/zulu-openjdk:11-latest
WORKDIR /app

COPY build/libs/CourtSupporter-0.0.1-SNAPSHOT.jar .

RUN useradd courtsupporter
USER courtsupporter
ENTRYPOINT ["java", "-jar", "CourtSupporter-0.0.1-SNAPSHOT.jar"]

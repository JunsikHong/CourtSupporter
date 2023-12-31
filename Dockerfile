FROM azul/zulu-openjdk:11-latest
WORKDIR /app

COPY build/libs/CourtSupporter-0.0.1-SNAPSHOT.jar .

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
ARG AWS_BUCKET_NAME

ARG JWT_SECRET
ARG AWS_CREDENTIALS_ACCESS_KEY
ARG AWS_CREDENTIALS_SECRET_KEY
ARG AWS_REGION_STATIC
ARG AWS_SQS_URL
ARG MAIL_USERNAME
ARG MAIL_PASSWORD

ENV AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
ENV AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
ENV AWS_BUCKET_NAME=$AWS_BUCKET_NAME

ENV JWT_SECRET=$JWT_SECRET
ENV AWS_CREDENTIALS_ACCESS_KEY=$AWS_CREDENTIALS_ACCESS_KEY
ENV AWS_CREDENTIALS_SECRET_KEY=$AWS_CREDENTIALS_SECRET_KEY
ENV AWS_REGION_STATIC=$AWS_REGION_STATIC
ENV AWS_SQS_URL=$AWS_SQS_URL
ENV MAIL_USERNAME=$MAIL_USERNAME
ENV MAIL_PASSWORD=$MAIL_PASSWORD

RUN useradd courtsupporter
USER courtsupporter
ENTRYPOINT ["java", "-jar", "CourtSupporter-0.0.1-SNAPSHOT.jar"]

REPOSITORY=/home/ubuntu/

cd $REPOSITORY

docker stop courtsupporter
docker rm courtsupporter
docker rmi courtsupporter-image

source ~/.bashrc

docker build -t courtsupporter-image . \
--build-arg AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID \
--build-arg AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
--build-arg AWS_BUCKET_NAME=$AWS_BUCKET_NAME \
--build-arg JWT_SECRET=$JWT_SECRET \
--build-arg AWS_CREDENTIALS_ACCESS_KEY=$AWS_CREDENTIALS_ACCESS_KEY \
--build-arg AWS_CREDENTIALS_SECRET_KEY=$AWS_CREDENTIALS_SECRET_KEY \
--build-arg AWS_REGION_STATIC=$AWS_REGION_STATIC \
--build-arg AWS_SQS_URL=$AWS_SQS_URL \
--build-arg MAIL_USERNAME=$MAIL_USERNAME \
--build-arg MAIL_PASSWORD=$MAIL_PASSWORD

docker run -d -p 8080:8788 --name courtsupporter courtsupporter-image






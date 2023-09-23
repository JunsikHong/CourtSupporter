REPOSITORY=/home/ubuntu/

cd $REPOSITORY

docker stop courtsupporter
docker rm courtsupporter
docker rmi courtsupporter-image

source ~/.bashrc

docker build -t courtsupporter-image .

docker run -d -p 8080:8788 --name courtsupporter courtsupporter-image






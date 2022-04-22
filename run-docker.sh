mvn clean package &&
docker build -t pet-project-service . &&
docker-compose up --build
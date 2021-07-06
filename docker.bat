mvn clean package
docker build -t pretty-url-app .
docker run -name pretty-url -p 8080:8080 pretty-url-app
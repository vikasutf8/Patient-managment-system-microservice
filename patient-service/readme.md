
## Api listing 
GET http://localhost:8081/api/v2/patient/all?page=0&size=10&sort=name,asc



## Swagger

- http://localhost:8081/swagger-ui.html
- http://localhost:8081/swagger-ui/index.html
- http://localhost:8081/v3/api-docs



## docker
```shell
docker volume create db_volume_patient_service_db


docker run -d \
  --name patient-service-db \
  -e POSTGRES_DB=patientdb \
  -e POSTGRES_USER=patientuser \
  -e POSTGRES_PASSWORD=patientpass \
  -v db_volume_patient_service_db:/var/lib/postgresql/data \
  -p 5433:5432 \
  postgres:16
  
  
spring.datasource.url=jdbc:postgresql://patient-service-db:5432/patientdb
spring.datasource.username=patientuser
spring.datasource.password=patientpass



```

```shell
# ---------- Stage 1 : Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom first (for caching dependencies)
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# ---------- Stage 2 : Run ----------
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java","-jar","app.jar"]

```

```shell
docker build -t patient-service-pm:latest .
docker network connect patient-network patient-service-db
docker run -d \
  --name patient-service \
  --network patient-network \
  -p 8091:8091 \
  patient-service-pm:latest
http://localhost:8091
http://localhost:8091/swagger-ui/index.html
docker-compose up --build

```



```shell
version: '3.8'

services:

  patient-service-db:
    image: postgres:16
    container_name: patient-service-db
    environment:
      POSTGRES_DB: patientdb
      POSTGRES_USER: patientuser
      POSTGRES_PASSWORD: patientpass
    ports:
      - "5433:5432"
    volumes:
      - db_volume_patient_service_db:/var/lib/postgresql/data

  patient-service:
    build: .
    container_name: patient-service
    ports:
      - "8091:8091"
    depends_on:
      - patient-service-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://patient-service-db:5432/patientdb
      SPRING_DATASOURCE_USERNAME: patientuser
      SPRING_DATASOURCE_PASSWORD: patientpass

volumes:
  db_volume_patient_service_db:

```
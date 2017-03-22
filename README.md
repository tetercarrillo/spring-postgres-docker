# spring-postgres-docker
Demo de aplicación utilizando Spring Boot, PostgreSQL y Docker.
Comandos

CONTENEDOR DE POSTGRESQL

PULLEAR POSTGRES

docker pull postgres

CREAR EL LUGAR DONDE VIVIRÁ LA DATA DEL CONTENEDOR

docker create -v /var/lib/postgresql/data --name spring_app_data postgres:9.4

INICIAR EL CONTAINER CON UNA INSTANCIA DE POSTGRES

docker run --volumes-from spring_app_data  --name spring_app_pg  -e POSTGRES_USER=spring -e POSTGRES_PASSWORD=1234  -d -P postgres:9.4

CONECTAR CON LA BASE DE DATOS DEL CONTENEDOR

run -it --link spring_app_pg:postgres --rm postgres sh -c 'exec psql -h "$POSTGRES_PORT_5432_TCP_ADDR" -p "$POSTGRES_PORT_5432_TCP_PORT" -U spring'

SCRIPT DE POSTGRES:

create database spring;
create table empleado (
id_empleado SERIAL PRIMARY KEY,
nombre CHARACTER(60) NOT NULL,
email CHARACTER(60) NOT NULL


);

CONTENEDOR DE LA APLICACIÓN

LEVANTAR LA APLICACIÓN
 
docker build -t mp/spring_app .





CONEXIÓN ENTRE LA APLICACIÓN Y POSTGRESQL

LINK CON LA INSTANCIA DE POSTGRES

docker run --name spring_app_container --link spring_app_pg:spring_app_pg -p 8080:8080 -d mp/spring_app

CREAR UN NUEVO EMPLEADO
curl localhost:8080/new






Para levantar la aplicación localmente:

mvn package && java -jar target/demo-0.0.1-SNAPSHOT.jar

curl localhost:8080

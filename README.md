# TEST DOCKER

Microservicio:
	1. Arrancar mediante Dockerfile
	2. Arrancar dos instancia mediante Dockerfile
	3. Arranque con docker-compose (Dos instancia y un balanceador)
	4. Arranque con docker-compose (Microservicio + Balanceador, escalado automático)

## Arrancar instancia mediante Dockerfile
	docker build -t img_node .
	docker run -it -d --name c_node img_node
	docker exec -it c_node /bin/sh
	docker stop c_node

## Arrancar dos instancias mediante Dockerfile
	docker build -t img_app1 .
	docker run -it -d --name c_app1 -p 8080:8080 img_app1
	docker run -it -d --name c_app2 -p 8081:8080 img_app1 

## Arranque con docker-compose (Dos instancia y un balanceador)
	docker-compose -f docker-compose_v1.yml up -d
	docker-compose -f docker-compose_v1.yml stop
		

## Arranque con docker-compose (Microservicio + Balanceador, escalado automático)
	docker-compose -f docker-compose_v2.yml up -d
	docker-compose -f docker-compose_v2.yml up -d --scale microservice=5
	docker-compose -f docker-compose_v2.yml stop

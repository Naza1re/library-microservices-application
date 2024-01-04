# microservice-library-application

Этапы запуска проекта

## 1)Клонирование репозитория

```bash
git clone https://github.com/Naza1re/library-microservices-application.git
```
## 2)Запуск проекта
Подождать подгрузку maven всех dependency

## 3)Создать в MySql базу данных с именем "booktestdb"
```
create database booktestdb
```
### Проверить в каждом сервисе соответствие username и password
```
spring.datasource.username=root
spring.datasource.password=root
```

## 4)Запустить проект

Запускать по порядку


### 1)Запустить EurekaServerApplication
### 2)Запустить ApiGatewayApplication
### 3)Запустить SuecurityServiceApplication
### 4)Запустить BookServiceApplication
### 5)Запустить LibraryServiceApplication


## 5)Swagger
##### [Документация SecurityService](http://localhost:8084/swagger-ui/index.html#)
##### [Документация BookService](http://localhost:8083/swagger-ui/index.html#)
##### [Документация LibraryService](http://localhost:8085/swagger-ui/index.html#)

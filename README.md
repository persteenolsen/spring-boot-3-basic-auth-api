# Spring Boot 3 Basic Auth Api

Last updated: 28-02-2025

- Spring Boot 3 REST API with Basic Authentication

- Working together with a Vue 3 Client

- Java 17

- Spring Boot 3

- Using settings for CORS

- Using Spring Security for protecting the url end points

- Maven as build tool

- JPA

- MySQL

- Login functionality

- GET a List of the Users

- DevTools with Hot Reload when developing 

- Download or fork the source code from GitHub

- Make sure that you have a MySQL Server online

- The Web Applications is prepared to be deployed to Azure App Service using Java 17 and Tomcat 10.1

# Create the file application.properties with the content below and place the file in the resources folder

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql:// the name of your mysql host / the name of your database

spring.datasource.username=your username 

spring.datasource.password=your password

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql: true

# Run the Web Application locally by open a terminal in the folder of the Spring Boot Web App

- ./mvnw spring-boot:run 

http://local:8080

# How to do for Testing with Postman

Spring Boot 3 Basic Auth API:

Create a User in the mysql db

username: user
password: persteen1967

The bcrypted value of password: persteen1967 is: $2a$10$BU4mPFHW8stXWMVH8clcZ.yZ7wl54oJq.f0Lu2HnUK6.WdooEoTZ2 and is manuel inserted in the database

POST http://localhost:8080/api/v1/login

Postman Tab "Body" - use the setting

{
"username":"user",
"password":"persteen1967"
}

After login in successfully 200 ok do the following:

GET http://localhost:8080/api/v1/ping 

Then hit Postman "send" and look at the raw body to see the message:

There should be a ok 200 with "Body": Welcome from a protected ping

Then perform the Login again and continue :-)
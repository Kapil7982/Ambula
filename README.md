# User Location
This is a sample Spring Boot application that demonstrates how to implement role-based authentication using JSON Web Tokens (JWT) and Spring Security. It provides a RESTful API for managing customers and implements basic authentication and authorization mechanisms.

## Features
- User registration: Users can register by providing their name, email, password, and role (admin or reader).
- User update: Users can update their profile information, including name, email, and password.
- Get nearest users: Reader can retrieve a list of nearest users based on the provided count.
- Role-based authorization: Different endpoints require different roles (admin or reader) for access.
- JSON Web Tokens (JWT): JWTs are used for authentication and securing API endpoints.

## Tech Stack
- Java
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- HSQL Database

## Installation
git clone [https://github.com/Kapil7982/Assignment.git](https://github.com/Kapil7982/Ambula.git)

## HSQL database details

Install and connect with the database

```bash
#changing the server port
server.port=8888
#db specific properties
spring.datasource.url=jdbc:hsqldb:mem:test
spring.datasource.driverClassName=org.hsqldb.jdbcDriver
spring.datasource.username=root
spring.datasource.password=port

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
## Dependencies
The project uses the following major dependencies:

- Spring Boot
- Spring Security
- MySQL Connector
- JSON Web Tokens (JWT)

## Testing the API:

- Use an API testing tool like Postman or Swagger-UI to send requests to the API endpoints.
- You can start by creating a new user account using the /create_data endpoint and then use the /login endpoint to authenticate and obtain a JWT.
- Include the JWT token in the Authorization header of subsequent requests to access protected resources.

# After running the application just hit the below URL to check the API's.
http://localhost:8888/swagger-ui/index.html

## Swagger-UI
![WhatsApp Image 2023-07-14 at 5 48 26 PM](https://github.com/Kapil7982/Ambula/assets/103938868/b856a841-baaa-4fa5-b441-5c14f3d45f89)

![WhatsApp Image 2023-07-14 at 6 03 09 PM](https://github.com/Kapil7982/Ambula/assets/103938868/0bfafd1d-a251-4e1e-8fec-a874984ea7de)

![WhatsApp Image 2023-07-14 at 6 41 56 PM](https://github.com/Kapil7982/Ambula/assets/103938868/75ec2cb5-6a82-41ef-8c33-b8d856b7e032)

![WhatsApp Image 2023-07-14 at 6 08 07 PM](https://github.com/Kapil7982/Ambula/assets/103938868/b9c02b7e-b0b2-49d0-b1da-cc2cb6b39dda)

![WhatsApp Image 2023-07-14 at 6 02 12 PM](https://github.com/Kapil7982/Ambula/assets/103938868/abf549ce-bb72-428a-8b18-9172cef26c04)

![WhatsApp Image 2023-07-14 at 6 00 35 PM](https://github.com/Kapil7982/Ambula/assets/103938868/d33b8859-4235-48df-81af-9a727da9b453)

![WhatsApp Image 2023-07-14 at 5 55 56 PM](https://github.com/Kapil7982/Ambula/assets/103938868/527ebfd2-da07-4766-bfc7-44155e7a9a9e)






# Build Complete REST API with Spring Data JPA and MongoDB - Todo Application
A simple Todo REST API built with Spring Data JPA that connects to the Mongodb database to perform the basic database operations such as Create, Read, Update and Delete

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/VDnoob/springboot-mongodb-CRUD.git
```

**2. Create Mongodb database**
```bash
use todo-manage-api
```

**3. Build and run the app using maven (springbootmongodb)**

```bash
mvn package
java -jar target/demo-springboot-mongodb-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080/>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /todos
    
    POST /todos
    
    GET /todos/{id}
    
    PUT /todos/{id}
    
    DELETE /todos/{id}

You can test them using postman or any other rest client.

## How to run

Install packages in your local maven repository:

```
mvn clean install
```

Go to app module:

```
cd app/
```

Run the application:

```
mvn spring-boot:run
```

## H2 console

- H2 Console: [http://localhost:8080/h2](http://localhost:8080/h2)

```
url: jdbc:h2:mem:example
username: sa
password: password
```


## API Reference

### Users

- #### Get user with filtering

```http
  GET http://localhost:8082/v1/user?search=lastName:Tomaszewski
```


- #### Get user by id

```http
  GET http://localhost:8082/v1/user/1
```



- #### Create task

```http
  POST http://localhost:8080/v1/user
```
Example body:
```json
{
    "firstName":"Jacek",
    "lastName":"New",
    "email":"jacek@new.com"
}
```  

- #### Delete user with specific id

```http
  DELETE http://localhost:8080/v1/user/2
```


### Tasks

- #### Get task with filtering

```http
  GET http://localhost:8080/v1/task?search=title:Python
```

- #### Get task by id

```http
  GET http://localhost:8080/v1/task/2
```

- #### Create task

```http
  POST http://localhost:8080/v1/task
```
Example body:
```json
{
    "title":"Python",
    "description":"Good course",
    "status":"OPEN",
    "finishDate":"2023-03-01"
}
``` 


- #### Update task

```http
  PUT http://localhost:8080/v1/task/2
```
Example body:
```json
{
    "title":"Python",
    "description":"Boring task",
    "status":"OPEN",
    "finishDate":"2023-03-01"
}
``` 

- #### Delete task with specific id

```http
  DELETE http://localhost:8080/v1/task/2
```

- #### Change status of task
```http
  PUT http://localhost:8080/v1/task/change-status/1/CLOSED
```


- #### Assign user to task

```http
  PUT http://localhost:8080/v1/task/2
```

- #### Change status of task
```http
  PUT http://localhost:8082/v1/task/assign-user/3/2
```
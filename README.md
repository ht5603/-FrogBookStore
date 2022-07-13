# FrogBookStore

## Purpose
This is programming test for Frog.
## What's inside
 * Java
 * Spring Boot
 * H2 Database
 * Mybatis
 * Swagger Open API
 * Test
## Notes

### Swagger UI
There are 4 restful APIs for curd of books and you could use Swagger UI to test them conveniently.    
Below is the endpoint.  
http://localhost:8080/bookstore/swagger-ui/index.html#/  

You could also use HB consle to check database data.  
Below is the url.  
http://localhost:8080/bookstore/h2-console/ (Password is in the application.properties)  

### Test Example  
Here are some json example for testing conveniently.  
**createBooks API**
```
[
  {
    "bookName": "bookName1",
    "author": "Mike",
    "price" : 10,
    "house": "Tokyo publishing house",
    "publishDate": "2022",
    "print": 1
   
  },
  {
    "bookName": "bookName2",
    "author": "John",
    "price" : 20,
    "house": "London publishing house",
    "publishDate": "2020",
    "print": 1
   
   }
]
```  
**updateBooks API - Price of bookName1 is modified to $99**  
```
[
  {
    "id": 1,
    "bookName": "bookName1",
    "author": "Mike",
    "price": 99,
    "house": "Tokyo publishing house",
    "publishDate": "2022",
    "print": 1,
    "createDt": "20220712090000",
    "updateDt": "20220712090000"
  }
]
```


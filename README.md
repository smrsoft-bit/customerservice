# Oriontek CustomerService
API to handle customer creation with their related address list

## Install instructions
- Before executing this project, please keep your Java Enviroment (21) ready with PostgreSQL 18.1
- Create a postgreSQL Dabatase --> oriontek
- Create an schema inside oriontek named --> core

### APIs
- Default port 8080
- API Base path = /api/v1/
#### Customer Endpoint
- POST /api/v1/customers
  request: 
  {
    "name": "",
    "email": "",
    "phone": ""
  }
  response:
  {
     UUID
  }
- GET /api/v1/queries/customers
- GET /api/v1/queries/customers/{customerId}
- PUT /api/v1/customers/{customerId}
#### Address Endpoint
- POST /api/v1/commands/addresses
  {
      "customerId": "UUID",
      "name": ""
  }
  response:
  {
     UUID
  }  
- GET /api/v1/queries/addresses
- GET /api/v1/queries/addresses/{customerId}
- PUT /api/v1/addresses/{addressId}
## Maven Profiles
- dev: mvn spring-boot:run -Dspring-boot.run.profiles=dev
- test: mvn spring-boot:run -Dspring-boot.run.profiles=test 




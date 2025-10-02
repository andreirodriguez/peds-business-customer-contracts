# Api Best Practice Azure SQL Cloud Development

> Este es un microservicio de ejemplo de la práctica de Cloud Development, este proyecto busca promover buenas prácticas para implementar desde tu microservicio una conexión con tu Azure SQL Database  y CRUD utilizando un cliente de conexión con el feature de Always Encrypted para el manejo de datos sensibles encriptados.
> Aquí encontraras buenas prácticas como: implementación con el ORM de Spring Data, manejo de datos encriptados con Always Encrypted y manejo de transacciones en un scope del PlatformTransactionManager.

## Init Project

### Initial Setup

1. Sigue la documentación  [Always Encrypted - Confluence](https://bcp-ti.atlassian.net/wiki/spaces/CTTIIDPSPUB/pages/344326230) para que puedas configurar Always Encrypted en tu Azure Sql Database.

2. Ahora configura en tu [bootstrap-local.yml](src/main/resources/config/bootstrap-local.yml) la cadena de conexión con el service principal id y el secret obtenidos en el paso anterior.

3. Ejecuta el [script_schema.sql](src/main/resources/script_schema.sql) en tu base de datos.

### Run Microservice

1. Configura el puerto donde se ejecutara tu microservicio [bootstrap.yml](src/main/resources/config/bootstrap.yml)

2. Ejecuta los casos de uso de credit cards con Always Encrypted con los siguientes curl:

###### SAVE:
```shell
curl --location 'localhost:8080/peds-cloud-azure-sql/credit-cards' \
--header 'Content-Type: application/json' \
--data '{
    "customerDocument": "43336200",
    "customerName": "Andrei Rodriguez B.",
    "number": "3023-3002-9209-0293",
    "cvv": "250",
    "registerDate": "2023-07-22T19:01:45.500-05:00"
}'
```

###### FIND ALL:
```shell
curl --location 'localhost:8080/peds-cloud-azure-sql/credit-cards/find-all'
```

###### GET BY DOCUMENT:
```shell
curl --location 'localhost:8080/peds-cloud-azure-sql/credit-cards/search?document=43336200'
```

3. Ejecuta los casos de uso de credit requests con manejo de transacciones con los siguientes curl:

###### SAVE:
```shell
curl --location 'localhost:8080/peds-cloud-azure-sql/credit-requests' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 1,
    "customerDocument": "43336200",
    "customerName": "Andrei Rodriguez",
    "creditScore": "AAA",
    "amount": 25000.00,
    "registerDatetime": "2024-03-18T11:24:00.500-05:00",
    "operations": [
        {
            "entity": "BCP",
            "amountFinancialDebt": 25000.00,
            "scoreEntity": "AAA"
        },
        {
            "entity": "INTERBANK",
            "amountFinancialDebt": 45000.00,
            "scoreEntity": "BBB"
        }                
    ],
    "states": [
        {
            "stateId": 1,
            "state": "EN EVALUACION",
            "registerDatetime": "2024-03-18T11:24:00.500-05:00"
        }        
    ]
}'
```
###### GET BY ID:
```shell
curl --location 'localhost:8080/peds-cloud-azure-sql/credit-requests/1'
```



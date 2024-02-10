# DiscountProgram

### we here apply Discount on the type of the Customer

### if he is a normal customer will apply no discount

#### to Access DB: http://localhost:8080/h2-console/login.jsp
#### jdbc url = jdbc:h2:mem:testdb
#### userName = sa
#### password = password

### mvn clean install in the terminal path
### and access to the DB because on the start up
### will generate random values of users you can test based up on

curl --location 'http://localhost:8080/discount' \
--header 'Content-Type: application/json' \
--data '{
"userId": 2,
"items": [
{
"itemId": 9200,
"name": "RAYL",
"category": "GROCERIES",
"price": 6782.834488979845
},
{
"itemId": 7386,
"name": "XESDESPL",
"category": "GROCERIES",
"price": 5372.903213423985
},
{
"itemId": 225,
"name": "KXA",
"category": "NON_GROCERIES",
"price": 300
},
{
"itemId": 4948,
"name": "UKLMVXLV",
"category": "NON_GROCERIES",
"price": 300
}
]
}'
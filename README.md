# Makersharks Assessment
- - -
Assignment for building a search page where buyers 
can search for manufacturers 
based on their customised requirements. 

# Tech stack(Backend)
- - - 
- Spring Boot
- Mysql

# Setting Up Project Locally
- - -
* Clone the project on terminal using 
```bash 
git clone https://github.com/Sundroid-ops/Makersharks-Assessment.git
``` 
* Set up **application.yml** using [exampleApplication.yml](src/main/resources/exampleApplication.yml)
* Project starts running on port 4000


# Curl Commands to test APIs
- - -
## 1. Creating A Supplier (POST)
```bash
curl -X POST http://localhost:4000/api/supplier/create \
-H "Content-Type: application/json" \
-d '{
"company_name": "ABCD",
"location": "USA",
"website": "www.ABCD.com",
"nature_of_business": "small_scale",
"manufacturing_processes": ["moulding", "casting"]
}'
```

## 2. Get Supplier By ID (GET)
```bash
curl -X GET http://localhost:4000/api/supplier/{supplierID}
```
### Example
```bash
curl -X GET http://localhost:4000/api/supplier/b29d2578-0324-4360-badb-2543412b81ff
```

## 3. Search Suppliers (POST)
```bash
curl -X POST http://localhost:4000/api/supplier/query \
-H "Content-Type: application/x-www-form-urlencoded" \
--data-urlencode "location=USA" \
--data-urlencode "business=small_scale" \
--data-urlencode "manufacture=moulding" \
--data-urlencode "manufacture=casting" \
--data-urlencode "page=0" \
--data-urlencode "size=10"
```
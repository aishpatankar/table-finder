# Getting Started

### Starting up the db
1. Start Postgres Server for port 542
2. Connect `psql -U your_username -h localhost`
3. See current databases `\l`
4. Create the database for your local environment `CREATE DATABASE tablefinderdb;`'
5. Connect into the db '\c tablefinderdb' 
6. Run `\dt` to check databases. You should seee the following message `Did not find any relations.`

### Building
Build project: `./gradlew build` This will have run the migrations and tables. Type `\dt` again to see your tables. 

### Running
Run project: `./gradlew bootRun` 

### Manual testing
Make sure project is running. 

Get request to find restaurants for list of diners

    GET http://localhost:8080/api/restaurants
    Sample request
    {
        "diners": [
                {
                    "id": 1,
                    "name": "Michael",
                    "dietaryRestrictions": ["Vegetarian"]
                }
            ],
        "time": "2024-09-27 14:45:30"
    }
    Expected response 
    {
        "restaurantList": [
            {
                "id": 2,
                "name": "Panadería Rosetta",
                "endorsements": [
                    "Gluten Free",
                    "Vegetarian"
                ]
            },
            {
                "id": 5,
                "name": "u.to.pi.a",
                "endorsements": [
                    "Vegan",
                    "Vegetarian"
                ]
            }
        ]
    }

Post request to create a reservation

    Post http://localhost:8080/api/reservations/create
    Sample request
    {
        "diners": [
            {
                "id": 1,
                "name": "Michael",
                "dietaryRestrictions": [
                    "Vegetarian"
                ]
            }
        ],
        "table": {
            "id": 1,
            "capacity": 2,
            "restaurant": {
                "id": 1,
                "name": "Lardo",
                "endorsements": [
                    "Gluten Free"
                ]
            }
        },
        "time": "2024-09-27 14:45:30"
    }
    Expected response

    {
        "id": 1,
        "dinerDtos": [
            {
                "id": 1,
                "name": "Michael",
                "dietaryRestrictions": [
                    "Vegetarian"
                ]
            }
        ],
        "table": {
        "id": 1,
        "capacity": 2,
        "restaurant": {
            "id": 1,
            "name": "Lardo",
            "endorsements": [
                "Gluten Free "
            ]
        }
    },
        "reservationTime": "2024-09-27 14:45:30"
    }
    
            




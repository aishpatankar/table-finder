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

        




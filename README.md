# Getting Started

### Starting up the db
1. Start Postgres Server for port 542
2. Connect `psql -U your_username -h localhost`
3. See current databases `\l`
4. Create the database for your local environment `CREATE DATABASE tablefinderdb;`'
5. Connect into the db '\c tablefinderdb' 
6. Run `\dt` to check databases. You should seee the following message `Did not find any relations.`

### Building the project
Build project: `./gradlew build` This will have run the migrations. Type `\dt` again to see your tables. 



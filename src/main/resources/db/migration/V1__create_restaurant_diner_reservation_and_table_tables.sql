CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    endorsements VARCHAR(255)[]
);

CREATE TABLE diners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dietary_restrictions VARCHAR(255)[]
);

CREATE TABLE tables (
    id SERIAL PRIMARY KEY,
    restaurant_id INT REFERENCES restaurants(id),
    capacity INT NOT NULL
);

CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    table_id INT REFERENCES tables (id),
    restaurant_id INT REFERENCES restaurants(id),
    diner_id INT REFERENCES diners(id),
    reservation_time TIMESTAMP NOT NULL
);
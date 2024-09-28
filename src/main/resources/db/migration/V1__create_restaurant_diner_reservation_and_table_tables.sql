CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
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
    reservation_diners INT[],
    reservation_time TIMESTAMP NOT NULL
);

CREATE TABLE restaurant_endorsements (
    id SERIAL PRIMARY KEY,
    restaurant_id INT REFERENCES restaurants(id),
    endorsement_name VARCHAR(256) NOT NULL
);

CREATE INDEX idx_restaurant_endorsement_name
ON restaurant_endorsements(endorsement_name);
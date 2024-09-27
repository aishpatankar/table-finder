INSERT INTO restaurants (name, endorsements)
VALUES
('Lardo', ARRAY['Gluten Free Options']),
('Panadería Rosetta', ARRAY['Vegetarian-Friendly', 'Gluten Free Options']),
('Tetetlán', ARRAY['Paleo-friendly', 'Gluten Free Options']),
('Falling Piano Brewing Co', NULL),
('u.to.pi.a', ARRAY['Vegan-Friendly', 'Vegetarian-Friendly']);

INSERT INTO diners (name, dietary_restrictions)
VALUES
('Michael', ARRAY['Vegetarian']),
('George Michael', ARRAY['Vegetarian', 'Gluten-Free']),
('Lucile', ARRAY['Gluten-Free']),
('Gob', ARRAY['Paleo']),
('Tobias', NULL),
('Maeby', ARRAY['Vegan']);

-- Lardo
DO $$
DECLARE
    i INT;
BEGIN
    -- Two-top tables (capacity: 2)
    FOR i IN 1..4 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Lardo'), 2);
    END LOOP;
    -- Four-top tables (capacity: 4)
    FOR i IN 1..2 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Lardo'), 4);
    END LOOP;
    -- Six-top tables (capacity: 6)
    INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Lardo'), 6);

-- Panadería Rosetta
    -- Two-top tables (capacity: 2)
    FOR i IN 1..3 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Panadería Rosetta'), 2);
    END LOOP;
    -- Four-top tables (capacity: 4)
    FOR i IN 1..2 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Panadería Rosetta'), 4);
    END LOOP;

-- Tetetlán
    -- Two-top tables (capacity: 2)
    FOR i IN 1..4 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Tetetlán'), 2);
    END LOOP;
    -- Four-top tables (capacity: 4)
    FOR i IN 1..2 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Tetetlán'), 4);
    END LOOP;
    -- Six-top tables (capacity: 6)
    INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Tetetlán'), 6);

-- Falling Piano Brewing Co
    -- Two-top tables (capacity: 2)
    FOR i IN 1..5 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Falling Piano Brewing Co'), 2);
    END LOOP;
    -- Four-top tables (capacity: 4)
    FOR i IN 1..5 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Falling Piano Brewing Co'), 4);
    END LOOP;
    -- Six-top tables (capacity: 6)
    FOR i IN 1..5 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='Falling Piano Brewing Co'), 6);
    END LOOP;

-- u.to.pi.a
    -- Two-top tables (capacity: 2)
    FOR i IN 1..2 LOOP
        INSERT INTO tables (restaurant_id, capacity) VALUES ((SELECT id FROM restaurants WHERE name='u.to.pi.a'), 2);
    END LOOP;
END $$;
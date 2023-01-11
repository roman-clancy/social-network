CREATE TABLE IF NOT EXISTS person(
    id          VARCHAR(50),
    first_name  VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    age         INTEGER,
    biography   VARCHAR(1000),
    city        VARCHAR(100),
    password    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
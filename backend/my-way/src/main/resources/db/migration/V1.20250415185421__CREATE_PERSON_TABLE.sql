CREATE TABLE tb_person (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    gender VARCHAR(50),
    history_description TEXT,
    country VARCHAR(100),
    region VARCHAR(100)
);

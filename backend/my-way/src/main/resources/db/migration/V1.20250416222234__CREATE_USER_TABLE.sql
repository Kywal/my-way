CREATE TABLE tb_user (
    id BIGINT PRIMARY KEY,
    id_person BIGINT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    tokens INT NOT NULL,
    CONSTRAINT fk_user_person FOREIGN KEY (id_person)
        REFERENCES tb_person (id)
        ON DELETE CASCADE
);

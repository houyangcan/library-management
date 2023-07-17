CREATE TABLE book_base (
                           book_id BIGINT NOT NULL,
                           name VARCHAR(50) NOT NULL,
                           author VARCHAR(50) NOT NULL,
                           publish VARCHAR(30) NOT NULL,
                           isbn VARCHAR(13) NOT NULL,
                           introduction TEXT,
                           language VARCHAR(10) NOT NULL,
                           price DECIMAL NOT NULL,
                           publish_date DATE DEFAULT NULL,
                           class_id INT DEFAULT NULL,
                           state TINYINT DEFAULT NULL ,
                           primary key(book_id)
);

CREATE TABLE USER_BASE (
    ID BIGINT NOT NULL,
    CREAT_TIME TIMESTAMP,
    EMAIL CHARACTER VARYING(255) NOT NULL,
    ID_NUMBER CHARACTER VARYING(255) NOT NULL,
    PASSWORD CHARACTER VARYING(255) NOT NULL,
    PHONE CHARACTER VARYING(255) NOT NULL,
    SEX CHARACTER VARYING(255) NOT NULL,
    UPDATE_TIME TIMESTAMP,
    USERNAME CHARACTER VARYING(255) NOT NULL,
    VALID BOOLEAN,
    primary key(id),
    unique key(USERNAME)
)
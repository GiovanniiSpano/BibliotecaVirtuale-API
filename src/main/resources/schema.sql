CREATE TABLE BOOKS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    AUTHOR VARCHAR(255) NOT NULL,
    PUBLISHED_YEAR INT,
    IS_AVAILABLE BOOLEAN
);

CREATE TABLE users(
	id				VARCHAR(10) PRIMARY KEY,
	NAME			VARCHAR(20) NOT NULL,
	PASSWORD		VARCHAR(20) NOT NULL
);

DESC users;

SELECT * FROM users;

DELETE FROM users;


DESC book;
SELECT * FROM book;
DELETE FROM book;
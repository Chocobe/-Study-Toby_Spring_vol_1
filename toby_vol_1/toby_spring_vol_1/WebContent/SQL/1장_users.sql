CREATE TABLE users(
	id				VARCHAR(10) PRIMARY KEY,
	NAME			VARCHAR(20) NOT NULL,
	PASSWORD		VARCHAR(20) NOT NULL
);

DESC users;


SELECT * FROM users;


DELETE FROM users;


CREATE TABLE myClient(
	memberID				VARCHAR(30) PRIMARY KEY,
	memberName			VARCHAR(30) NOT NULL,
	memberAddr			VARCHAR(30) NOT NULL,
	memberPhone			VARCHAR(30) NOT NULL
);

DESC myClient;


SELECT * FROM myClient;
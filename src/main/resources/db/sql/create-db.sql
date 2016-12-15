CREATE TABLE users (
  id  INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50),
  password VARCHAR(200),
  dob varchar(10),
  street varchar(50),
  zip varchar(6),
  city varchar(30),
  country varchar(50)
);

CREATE TABLE historical_data(
 id INTEGER IDENTITY PRIMARY KEY,
 userName VARCHAR(30),
 baseCurrency VARCHAR(20),
 exchangeRate VARCHAR(150),
 queryDate VARCHAR(30)
);
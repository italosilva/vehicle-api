CREATE TABLE vehicle (
  id SERIAL NOT NULL PRIMARY KEY,
  plate VARCHAR(7) NOT NULL,
  model VARCHAR(50) NOT NULL,
  manufacturer VARCHAR(30) NOT NULL,
  color VARCHAR(15) NOT NULL,
  status boolean NOT NULL,
  created TIMESTAMP NOT NULL
);
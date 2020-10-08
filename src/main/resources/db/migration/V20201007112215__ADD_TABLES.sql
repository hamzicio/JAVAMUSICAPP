CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  address VARCHAR(255),
  email VARCHAR(255) UNIQUE ,
  username VARCHAR (255) UNIQUE ,
  passwrd VARCHAR(255),
  music_music_id INTEGER
);


CREATE TABLE roles (
  role_id  SERIAL PRIMARY KEY,
  name VARCHAR (45) NOT NULL
);

CREATE TABLE users_roles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
  CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE music (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  user_user_id INTEGER NOT NULL,
  CONSTRAINT role_fk FOREIGN KEY (user_user_id) REFERENCES users (user_id)
);

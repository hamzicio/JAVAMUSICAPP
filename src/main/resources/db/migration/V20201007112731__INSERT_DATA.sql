INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users(name,address,email,passwrd,username) values('Hamza','Defence Phase 4','hamza.ashfaque2012@gmail.com','$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.','Hamza');
INSERT INTO users(name,address,email,passwrd,username) values('Ali','Defence Phase 2','hamza.ashfaque2011@gmail.com','$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.','Ali');

INSERT INTO users_roles (user_id,role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id,role_id) VALUES (2, 2);
CREATE TABLE usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuario_email (email),
  UNIQUE KEY uk_usuario_username (username)
);

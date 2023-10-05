DROP DATABASE servlets;
CREATE DATABASE servlets;
USE servlets;

DROP TABLE inventario;
CREATE TABLE inventario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    costo INT NOT NULL,
    fechaAdquisicion VARCHAR(255) NOT NULL
);

DROP TABLE usuario;
CREATE TABLE usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

DROP USER 'algo'@'%';
CREATE USER 'algo'@'%' IDENTIFIED BY 'algo';
GRANT ALL PRIVILEGES ON servlets.* TO 'algo'@'%';
FLUSH PRIVILEGES;
ALTER USER 'algo'@'%' IDENTIFIED WITH mysql_native_password BY 'algo';
FLUSH PRIVILEGES;



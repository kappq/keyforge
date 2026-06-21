CREATE SCHEMA keyforge;

USE keyforge;

CREATE TABLE articolo (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	descrizione TEXT NOT NULL,
	brand VARCHAR(255) NOT NULL,
	prezzo DECIMAL(10, 2) NOT NULL,
	disponibilita INT UNSIGNED NOT NULL,
	dimensione INT NOT NULL, -- in millimetri
	peso INT NOT NULL, -- in grammi
	layout VARCHAR(255) NOT NULL
);

CREATE TABLE immagine (
	id INT AUTO_INCREMENT PRIMARY KEY,
	articolo_id INT NOT NULL,
	dati LONGBLOB NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

CREATE TABLE carrello (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utente_id INT NOT NULL UNIQUE,
    FOREIGN KEY (utente_id) REFERENCES utente(id) ON DELETE CASCADE
);

CREATE TABLE utente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    data_nascita DATE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE inclusione (
    carrello_id INT NOT NULL,
    articolo_id INT NOT NULL,
    PRIMARY KEY (carrello_id, articolo_id),
    FOREIGN KEY (carrello_id) REFERENCES carrello(id) ON DELETE CASCADE,
    FOREIGN KEY (articolo_id) REFERENCES articolo(id)
);
CREATE SCHEMA keyforge;

USE keyforge;

CREATE TABLE articolo (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	descrizione TEXT NOT NULL,
	brand VARCHAR(255) NOT NULL,
	prezzo DECIMAL(10, 2) NOT NULL,
	disponibilita INT UNSIGNED NOT NULL
);

CREATE TABLE immagine (
	id INT AUTO_INCREMENT PRIMARY KEY,
	articolo_id INT NOT NULL,
	dati LONGBLOB NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

CREATE TABLE tastiera (
	articolo_id INT PRIMARY KEY,
	dimensione DECIMAL(10, 2) NOT NULL,
	peso DECIMAL(10, 2) NOT NULL,
	layout VARCHAR(255) NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

CREATE TABLE switch (
	articolo_id INT PRIMARY KEY,
	compatibilita VARCHAR(255) NOT NULL,
	attivazione DECIMAL(10, 2) NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

CREATE TABLE keycap (
	articolo_id INT PRIMARY KEY,
	materiale VARCHAR(255) NOT NULL,
	profilo VARCHAR(255) NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

CREATE TABLE accessorio (
	articolo_id INT PRIMARY KEY,
	tipo VARCHAR(255) NOT NULL,
	FOREIGN KEY (articolo_id) REFERENCES articolo(id) ON DELETE CASCADE
);

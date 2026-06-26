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

CREATE TABLE utente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    data_nascita DATE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE carrello (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utente_id INT NOT NULL UNIQUE,
    FOREIGN KEY (utente_id) REFERENCES utente(id) ON DELETE CASCADE
); 

CREATE TABLE inclusione (
    carrello_id INT NOT NULL,
    articolo_id INT NOT NULL,
    PRIMARY KEY (carrello_id, articolo_id),
    FOREIGN KEY (carrello_id) REFERENCES carrello(id) ON DELETE CASCADE,
    FOREIGN KEY (articolo_id) REFERENCES articolo(id)
);

CREATE TABLE ordine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utente_id INT,
    stato ENUM('in_attesa', 'confermato', 'spedito', 'consegnato', 'annullato') NOT NULL DEFAULT 'in_attesa',
    indirizzo_spedizione VARCHAR(500) NOT NULL,
    tracking VARCHAR(100),
    note TEXT,
    data_ordine DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utente_id) REFERENCES utente(id) ON DELETE SET NULL
);

CREATE TABLE pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ordine_id INT UNIQUE, 
    stato ENUM('in_attesa', 'completato', 'fallito', 'rimborsato') NOT NULL DEFAULT 'in_attesa',
    importo DECIMAL(10, 2) NOT NULL,
    valuta VARCHAR(3) NOT NULL DEFAULT 'EUR',  
    data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ordine_id) REFERENCES ordine(id) ON DELETE SET NULL
);

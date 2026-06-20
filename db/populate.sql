USE keyforge;

-- =========================
-- ARTICOLI
-- =========================

INSERT INTO articolo (id, nome, descrizione, brand, prezzo, disponibilita) VALUES
(1, 'Aurora 65', 'Tastiera meccanica compatta 65% con illuminazione RGB.', 'KeyForge', 129.99, 15),
(2, 'Titan TKL', 'Tastiera tenkeyless in alluminio anodizzato.', 'ForgeKeys', 159.99, 8),
(3, 'Nebula 75', 'Tastiera layout 75% con manopola multifunzione.', 'KeyForge', 179.99, 12),

(4, 'Cherry MX Red', 'Switch lineare leggero e silenzioso.', 'Cherry', 0.49, 500),
(5, 'Gateron Yellow Pro', 'Switch lineare fluido prelubrificato.', 'Gateron', 0.35, 800),
(6, 'Kailh Box White', 'Switch clicky con protezione antipolvere.', 'Kailh', 0.55, 350),

(7, 'PBT Retro Beige', 'Set keycap PBT ispirato ai computer vintage.', 'KeyForge', 49.99, 25),
(8, 'Cyber Night', 'Set keycap nero e viola con legende traslucide.', 'Akko', 59.99, 18),
(9, 'Ocean Blue', 'Set keycap blu in profilo Cherry.', 'GMK Clone', 39.99, 30),

(10, 'Coil Cable USB-C', 'Cavo spiralato USB-C per tastiere meccaniche.', 'KeyForge', 24.99, 40),
(11, 'Switch Puller Pro', 'Estrattore professionale per switch.', 'Glorious', 9.99, 60),
(12, 'Desk Mat Sakura', 'Tappetino da scrivania con tema sakura.', 'KeyForge', 29.99, 20);

-- =========================
-- TASTIERE
-- =========================

INSERT INTO tastiera (articolo_id, dimensione, peso, layout) VALUES
(1, 320, 720, 'ISO-IT 65%'),
(2, 360, 980, 'ISO-IT TKL'),
(3, 330, 850, 'ANSI-US 75%');

-- =========================
-- SWITCH
-- =========================

INSERT INTO switch (articolo_id, compatibilita, attivazione) VALUES
(4, 'MX', 45000),
(5, 'MX', 50000),
(6, 'MX', 45000);

-- =========================
-- KEYCAP
-- =========================

INSERT INTO keycap (articolo_id, materiale, profilo) VALUES
(7, 'PBT', 'Cherry'),
(8, 'ABS', 'OEM'),
(9, 'PBT', 'Cherry');

-- =========================
-- ACCESSORI
-- =========================

INSERT INTO accessorio (articolo_id, tipo) VALUES
(10, 'Cavo'),
(11, 'Estrattore Switch'),
(12, 'Desk Mat');

-- =========================
-- IMMAGINI (placeholder)
-- =========================

INSERT INTO immagine (articolo_id, dati) VALUES
(1, X'00'),
(2, X'00'),
(3, X'00'),
(4, X'00'),
(5, X'00'),
(6, X'00'),
(7, X'00'),
(8, X'00'),
(9, X'00'),
(10, X'00'),
(11, X'00'),
(12, X'00');


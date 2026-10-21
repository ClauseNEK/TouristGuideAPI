-- Startdata til Turistguiden.
-- INSERT IGNORE springer rækker over, der allerede findes (pga. UNIQUE/PRIMARY KEY),
-- så scriptet kan køre ved hver opstart uden at lave dubletter.

INSERT IGNORE INTO city (city_name) VALUES ('Albertslund');
INSERT IGNORE INTO city (city_name) VALUES ('København');
INSERT IGNORE INTO city (city_name) VALUES ('Odense');
INSERT IGNORE INTO city (city_name) VALUES ('Kongens Lyngby');
INSERT IGNORE INTO city (city_name) VALUES ('Aarhus');

INSERT IGNORE INTO tag (tag_name) VALUES ('Børnevenlig');
INSERT IGNORE INTO tag (tag_name) VALUES ('Gratis');
INSERT IGNORE INTO tag (tag_name) VALUES ('Kunst');
INSERT IGNORE INTO tag (tag_name) VALUES ('Museum');
INSERT IGNORE INTO tag (tag_name) VALUES ('Natur');
INSERT IGNORE INTO tag (tag_name) VALUES ('Underholdning');

-- Attraktioner (city_id slås op via bynavnet)
INSERT IGNORE INTO tourist_attraction (name, description, image, city_id)
SELECT 'Tivoli', 'Sjov for hele familien', 'tivoli.png', city_id FROM city WHERE city_name = 'København';

INSERT IGNORE INTO tourist_attraction (name, description, image, city_id)
SELECT 'Bakken', 'Underholdning for store og for små', 'bakken_logo_2026.png', city_id FROM city WHERE city_name = 'Kongens Lyngby';

INSERT IGNORE INTO tourist_attraction (name, description, image, city_id)
SELECT 'Vega', 'Musik året rundt', 'vega.png', city_id FROM city WHERE city_name = 'København';

INSERT IGNORE INTO tourist_attraction (name, description, image, city_id)
SELECT 'Royal Arena', 'Koncerter mm.', 'royalarena.png', city_id FROM city WHERE city_name = 'København';

INSERT IGNORE INTO tourist_attraction (name, description, image, city_id)
SELECT 'TuristInformation', 'Hjælp til alle dine turist behov', 'turistinformation.png', city_id FROM city WHERE city_name = 'København';

-- Kobling attraktion <-> tags
INSERT IGNORE INTO attraction_tag (attraction_id, tag_id)
SELECT a.attraction_id, t.tag_id FROM tourist_attraction a, tag t
WHERE a.name = 'Tivoli' AND t.tag_name IN ('Børnevenlig', 'Underholdning');

INSERT IGNORE INTO attraction_tag (attraction_id, tag_id)
SELECT a.attraction_id, t.tag_id FROM tourist_attraction a, tag t
WHERE a.name = 'Bakken' AND t.tag_name IN ('Børnevenlig', 'Underholdning');

INSERT IGNORE INTO attraction_tag (attraction_id, tag_id)
SELECT a.attraction_id, t.tag_id FROM tourist_attraction a, tag t
WHERE a.name = 'Vega' AND t.tag_name = 'Underholdning';

INSERT IGNORE INTO attraction_tag (attraction_id, tag_id)
SELECT a.attraction_id, t.tag_id FROM tourist_attraction a, tag t
WHERE a.name = 'Royal Arena' AND t.tag_name = 'Underholdning';

INSERT IGNORE INTO attraction_tag (attraction_id, tag_id)
SELECT a.attraction_id, t.tag_id FROM tourist_attraction a, tag t
WHERE a.name = 'TuristInformation' AND t.tag_name = 'Gratis';

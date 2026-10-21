-- Kør manuelt (fx i MySQL Workbench) hvis databasen skal nulstilles.
-- Bagefter genopretter appen tabeller + startdata ved næste opstart.
DROP TABLE IF EXISTS attraction_tag;
DROP TABLE IF EXISTS tourist_attraction;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS city;

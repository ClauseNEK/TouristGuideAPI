-- fake h2 DB
CREATE DATABASE IF NOT EXISTS fakeDB
       CHARACTER SET utf8mb4;

       USE fakeDB;

DROP TABLE IF EXISTS touristattraction;
CREATE TABLE IF NOT EXISTS touristattraction (
    touristattraction_id INT AUTO_INCREMENT PRIMARY KEY,
    touristattraction_name VARCHAR(255) NOT NULL,
    touristattraction_description VARCHAR(255)  NOT NULL,
    touristattraction_city VARCHAR(255) FOREIGN KEY,
    touristattraction_tags VARCHAR(255) FOREIGN KEY,
    touristattraction_image VARCHAR(255) NOT NULL,
    FOREIGN KEY (touristattraction_city) REFERENCES city (city_id)
    FOREIGN KEY (touristattraction_tags) REFERENCES touristattraction_tags (touristattraction_tags_id)
)

DROP TABLE IF EXISTS tags;
CREATE TABLE IF NOT EXISTS tags (
    tags_id INT AUTO_INCREMENT PRIMARY KEY,
    tags_tag VARCHAR(255) NOT NULL,(unique?)
    )

--not needed?
DROP TABLE IF EXISTS city;
CREATE TABLE IF NOT EXISTS city (
    city_id INT AUTO_INCREMENT PRIMARY KEY,
    city_name VARCHAR(255), not null
    )

DROP TABLE IF EXISTS touristattraction_tags;
CREATE TABLE IF NOT EXISTS tourist_tags (
    touristattraction_tags_id int, -- combo key fra city + tag?
    touristattraction_tags_touristattraction VARCHAR(255),
    touristattraction_tags_tag VARCHAR(255),
    PRIMARY KEY(tourist_tags_city, tourist_tags_tag), -- behover ikke være et field/celle/Whatever?
    FOREIGN KEY (tourist_tags_touristattraction) REFERENCES touristattraction (touristattraction_id)
    FOREIGN KEY (tourist_tags_tag) REFERENCES tags (tags_id)
    )
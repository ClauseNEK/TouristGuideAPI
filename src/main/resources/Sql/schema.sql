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
)

DROP TABLE IF EXISTS tags;
CREATE TABLE IF NOT EXISTS tags (
    tags_id INT AUTO_INCREMENT PRIMARY KEY,
    tags_tag VARCHAR(255) name NOT NULL,
    )

DROP TABLE IF EXISTS city;
CREATE TABLE IF NOT EXISTS city (
    tags_id INT AUTO_INCREMENT PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL,
    )

DROP TABLE IF EXISTS city_tags;
CREATE TABLE IF NOT EXISTS city_tags (
    city_tags_id int not null,
    city_tags_city VARCHAR(255) touristattraction(name) NOT NULL,
    city_tags_tag VARCHAR(255) city(name) NOT NULL,
    PRIMARY KEY(city, tag),
    FOREIGN KEY (city_tags_city) REFERENCES city (city_name)
    FOREIGN KEY (city_tags_tag) REFERENCES tags (tags_tag)
    )
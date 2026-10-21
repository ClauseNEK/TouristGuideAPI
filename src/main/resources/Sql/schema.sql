-- Opretter tabellerne til Turistguiden.
-- Kører automatisk ved opstart (spring.sql.init.mode=always) den fra app.prop


-- By 1 by har mange attraktioner
CREATE TABLE IF NOT EXISTS city (
    city_id   INT          NOT NULL AUTO_INCREMENT,
    city_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (city_id),
    CONSTRAINT uq_city_name UNIQUE (city_name)
);

-- Tag mange-til-mange med attraktion via attraction_tag
CREATE TABLE IF NOT EXISTS tag (
    tag_id   INT          NOT NULL AUTO_INCREMENT,
    tag_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (tag_id),
    CONSTRAINT uq_tag_name UNIQUE (tag_name)
);

-- Turistattraktion
CREATE TABLE IF NOT EXISTS tourist_attraction (
    attraction_id INT          NOT NULL AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    image         VARCHAR(255),
    city_id       INT          NOT NULL,
    PRIMARY KEY (attraction_id),
    CONSTRAINT uq_attraction_name UNIQUE (name),
    CONSTRAINT fk_attraction_city FOREIGN KEY (city_id) REFERENCES city (city_id)
);

-- Koblingstabel mellem attraktion og tag sammensat primærnøgle
CREATE TABLE IF NOT EXISTS attraction_tag (
    attraction_id INT NOT NULL,
    tag_id        INT NOT NULL,
    PRIMARY KEY (attraction_id, tag_id),
    CONSTRAINT fk_at_attraction FOREIGN KEY (attraction_id)
        REFERENCES tourist_attraction (attraction_id) ON DELETE CASCADE,
    CONSTRAINT fk_at_tag FOREIGN KEY (tag_id)
        REFERENCES tag (tag_id) ON DELETE CASCADE
);

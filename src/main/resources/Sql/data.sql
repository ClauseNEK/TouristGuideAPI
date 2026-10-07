INSERT INTO touristattration (name, desciption,city,tags,image)
VALUES
    ( "Tivoli", "Sjov for hele familien", "København",
                "Børnevenlig", "Underholdning", "tivoli.png"),

    ("Bakken", "Underholdning for store og for små", "Kongens Lyngby",
                tags("Underholdning"), "bakken_logo_2026.png"),


        ("Vega", "Musik året rundt", "København",
                tags("Underholdning"), "vega.png"),

        ("Royal Arena", "Koncerter mm.", city("København"),
                tags("Underholdning"), "royalarena.png"),

        ("TuristInformation", "Hjælp til alle dine turist behov", "København",
                tags("Gratis"), "turistinformation.png"));

INSERT INTO city (name)
VALUES
    ("Albertslund"),
    ("København"),
    ("Odense"),
    ("Kongens Lyngby"),
    ("Aarhus");

INSERT INTO tags (name)
VALUES
    ("Børnevenlig"),
    ("Gratis"),
    ("Kunst"),
    ("Museum"),
    ("Natur"),
    ("Underholdning");

-- Bridge for tags/touristattraction


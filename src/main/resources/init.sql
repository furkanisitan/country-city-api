INSERT INTO countries (country_id, code, name, life_expectancy, continent)
VALUES (1, 'TR', 'Turkey', 78.6, 4),
       (2, 'US', 'United States of America', 78.4, 5),
       (3, 'GB', 'United Kingdom', 81.4, 4),
       (4, 'DE', 'Germany', 81.7, 4);

INSERT INTO languages (language_id, code, name)
VALUES (1, 'tr', 'Turkish'),
       (2, 'ku', 'Kurdish'),
       (3, 'en', 'English'),
       (4, 'de', 'German');

INSERT INTO country_languages (country_id, language_id, is_official)
VALUES (1, 1, true),
       (1, 2, false),
       (2, 3, true),
       (3, 3, true),
       (4, 4, true);

INSERT INTO cities (city_id, country_id, name, population)
VALUES (1, 1, 'Ankara', 5747325),
       (2, 1, 'Samsun', 1371274),
       (3, 2, 'Washington', 7766925),
       (4, 3, 'London', 8961989),
       (5, 4, 'Berlin', 3567000);

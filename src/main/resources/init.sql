INSERT INTO countries (country_id, code, name, life_expectancy)
VALUES (1, 'TR', 'Turkey', 78.6),
       (2, 'US', 'United States of America', 78.4),
       (3, 'GB', 'United Kingdom', 81.4),
       (4, 'DE', 'Germany', 81.7);

INSERT INTO languages (language_id, code, name)
VALUES (1, 'ar', 'Arabic'),
       (2, 'tr', 'Turkish'),
       (3, 'ku', 'Kurdish'),
       (4, 'en', 'English'),
       (5, 'de', 'German');

INSERT INTO country_languages (country_id, language_id, is_official)
VALUES (1, 2, true),
       (1, 3, false),
       (2, 4, true),
       (3, 4, true),
       (4, 5, true);

INSERT INTO cities (city_id, country_id, name, population)
VALUES (1, null, 'Samsun', 1371274),
       (2, 1, 'Ankara', 5747325),
       (3, 2, 'Washington', 7766925),
       (4, 3, 'London', 8961989),
       (5, 4, 'Berlin', 3567000);

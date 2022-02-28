INSERT INTO countries (country_id, code, name, life_expectancy)
VALUES (1, 'TR', 'Turkey', 78.6),
       (2, 'US', 'United States of America', 78.4),
       (3, 'GB', 'United Kingdom', 81.4),
       (4, 'DE', 'Germany', 81.7);

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


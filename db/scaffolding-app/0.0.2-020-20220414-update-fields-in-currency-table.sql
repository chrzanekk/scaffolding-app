ALTER TABLE currency
ADD name_en VARCHAR(30) NOT NULL;

ALTER TABLE currency
RENAME COLUMN name TO name_pl;


ALTER TABLE service_actions 
ADD description VARCHAR(400);

ALTER TABLE service_action_type
DROP COLUMN description;

CREATE TABLE service_workshops (
id INT AUTO_INCREMENT,
name VARCHAR(255) NOT NULL UNIQUE,
tax_number VARCHAR (13),
street VARCHAR (150),
building_number VARCHAR(5),
apartment_number VARCHAR(5),
postal_code VARCHAR(6),
city VARCHAR (50),
create_date datetime default now(),
modify_date datetime,
remove_date DATETIME,
PRIMARY KEY (id)
);

ALTER TABLE service_actions
ADD workshop_id INT;

ALTER TABLE service_actions
ADD FOREIGN KEY (workshop_id) REFERENCES service_workshops(id);

ALTER TABLE service_actions
DROP COLUMN service_workshop;
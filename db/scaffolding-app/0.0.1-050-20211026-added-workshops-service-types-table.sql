ALTER TABLE service_workshops RENAME TO workshops;

CREATE TABLE workshops_service_types (
id INT AUTO_INCREMENT,
workshop_id INT NOT NULL,
service_action_type_id INT NOT NULL,
create_date datetime default now(),
modify_date datetime,
remove_date DATETIME,
PRIMARY KEY (id),
FOREIGN KEY (workshop_id) REFERENCES workshops(id),
FOREIGN KEY (service_action_type_id) REFERENCES service_action_type (id)
);
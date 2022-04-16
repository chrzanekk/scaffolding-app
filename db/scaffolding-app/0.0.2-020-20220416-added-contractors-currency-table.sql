CREATE TABLE contractors_currency (
id INT AUTO_INCREMENT,
contractor_id INT NOT NULL,
currency_id INT NOT NULL,
create_date datetime default NOW(),
create_user_id INT,
modify_date datetime,
modify_user_id INT,
remove_date DATETIME,
remove_user_id INT,
PRIMARY KEY (id),
FOREIGN KEY (contractor_id) REFERENCES contractors(id),
FOREIGN KEY (currency_id) REFERENCES currency(id)
);
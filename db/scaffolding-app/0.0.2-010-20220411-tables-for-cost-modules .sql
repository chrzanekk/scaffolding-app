CREATE TABLE contractor_types (
	id INT AUTO_INCREMENT,
	NAME varchar(50) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE currency(
	id INT AUTO_INCREMENT,
	NAME VARCHAR(60) NOT NULL,
	CODE VARCHAR(5) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE payment_statuses(
	id INT AUTO_INCREMENT,
	NAME varchar(50) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE payment_types(
	id INT AUTO_INCREMENT,
	NAME varchar(50) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE cost_accounts(
	id INT AUTO_INCREMENT,
	NAME varchar(50) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE contractors_categories(
	id INT AUTO_INCREMENT,
	NAME varchar(50) NOT NULL,
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE contractors(
	id INT AUTO_INCREMENT,
	contractor_type_id INT NOT NULL,
	name VARCHAR(255) NOT NULL UNIQUE,
	tax_number VARCHAR (13),
	street VARCHAR (150),
	building_number VARCHAR(5),
	apartment_number VARCHAR(5),
	postal_code VARCHAR(6),
	city VARCHAR (50),
	country VARCHAR (50),
	bank_account_number VARCHAR(35),
	description VARCHAR (500),
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (contractor_type_id) REFERENCES contractor_types(id),
	FOREIGN KEY (create_user_id) REFERENCES users(id),
	FOREIGN KEY (modify_user_id) REFERENCES users(id),
	FOREIGN KEY (remove_user_id) REFERENCES users(id)
);

CREATE TABLE invoices(
	id INT AUTO_INCREMENT,
	invoice_no VARCHAR(40),
	contractor_id INT NOT NULL,
	invoice_date DATE NOT NULL,
	contractor_category_id INT NOT NULL,
	cost_account_id INT NOT NULL,
	currency_id INT NOT NULL,
	invoice_net_value DECIMAL(10,2) NOT NULL,
	invoice_gross_value DECIMAL(10,2) NOT NULL,
	tax_value DECIMAL(10,2) NOT NULL,
	tax_rate DECIMAL(10,2) NOT NULL,
	payment_status_id INT NOT NULL,
	payment_date DATE NOT NULL,
	payment_type_id INT NOT NULL,
	description VARCHAR(500),
	create_date datetime default NOW(),
	create_user_id INT,
	modify_date datetime,
	modify_user_id INT,
	remove_date DATETIME,
	remove_user_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (contractor_id) REFERENCES contractors(id),
	FOREIGN KEY (contractor_category_id) REFERENCES contractors_categories(id),
	FOREIGN KEY (cost_account_id) REFERENCES cost_accounts(id),
	FOREIGN KEY (currency_id) REFERENCES currency(id),
	FOREIGN KEY (payment_status_id) REFERENCES payment_statuses(id),
	FOREIGN KEY (payment_type_id) REFERENCES payment_types(id)
);


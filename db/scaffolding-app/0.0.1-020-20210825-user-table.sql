CREATE TABLE users(
		id int auto_increment,
		login varchar(255) not null,
		password_hash varchar(200) not null,
		first_name varchar(50) not null,
		second_name varchar(75) not null,
		language varchar(20) not null,
		regulation_accepted boolean,
		newsletter_accepted boolean,
		is_enabled boolean,
		registration_datetime datetime,
		registration_ip varchar(200),
		registration_user_agent varchar(1000),
		email_confirmed boolean not null,
		delete_datetime datetime,
		primary key (id)
		);

CREATE TABLE user_authorities(
		id int auto_increment,
		user_id int not null,
		authority varchar(50),
		create_date datetime default now(),
		modify_date datetime,
		remove_date datetime,
		primary key (id),
		foreign key (user_id) REFERENCES users(id),		
);		
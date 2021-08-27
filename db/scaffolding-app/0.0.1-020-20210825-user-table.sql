CREATE TABLE users(
		id int auto_increment,
		login varchar(255) not null UNIQUE,
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
		foreign key (user_id) REFERENCES users(id)
);	

CREATE TABLE notifications(
		id int auto_increment,
		create_date datetime,
		seen_date datetime,
		delete_date datetime,
		user_id int,
		title varchar(20),
		content varchar(100),
		link  varchar(500),
		status varchar(1),
		type varchar(1),
		kind varchar(1),
		language varchar(2),
		primary key (id),
		foreign key (user_id) REFERENCES users(id)
);	

CREATE TABLE email_templates(
		id int auto_increment,
		title varchar(200) not null,
		content varchar(5000) not null,
		language varchar(20) not null,
		event varchar(200) not null UNIQUE,
		primary key (id)
);

CREATE TABLE action_trace(
		id int auto_increment,
		what varchar(200),
		value varchar(1000),
		who varchar(1000),
		creation_datetime datetime,
		ip_address varchar(50),
		browser varchar(200),
		operating_system varchar(200),
		primary key (id)
);

CREATE TABLE sent_emails(
		id int auto_increment,
		user_id int not null,
		title varchar(200) not null,
		content varchar(5000) not null,
		language varchar(20) not null,
		event varchar(200) not null unique,
		create_datetime datetime,
		primary key (id),
		foreign key (user_id) REFERENCES users(id)
)

CREATE TABLE password_reset_token(
		id int auto_increment,
		user_id int not null,
		value varchar(300) not null UNIQUE,
		expiration_datetime datetime not null,
		used boolean not null,
		primary key (id),
		foreign key (user_id) references users(id)
);

CREATE TABLE tokens(
		int id auto_increment,
		value varchar(300) UNIQUE,
		expiration_datetime datetime,
		user_id int,
		primary key(id),
		foreign key (user_id) REFERENCES users(id)
);

	
	CREATE TABLE fuel_type (
	id int auto_increment,
	name varchar(50) not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE tire_season(
	id int auto_increment,
	name varchar(20),
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE vehicle_type  (
	id int auto_increment,
	name varchar(50) not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE service_action_type (
	id int auto_increment,
	name varchar(30) not null,
	description varchar(255) not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE vehicle_brand (
	id int auto_increment,
	name varchar(30) UNIQUE,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE vehicle_model (
	id int auto_increment,
	brand_id int not null,
	name varchar(30) UNIQUE,
	primary key (id),
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	foreign key (brand_id) references vehicle_brand(id));

	CREATE TABLE tires (
	id int auto_increment,
	brand varchar(50) not null,
	model varchar(40) not null,
	width int not null,
	profile int not null,
	diameter int not null,
	speed_index varchar(2) not null,
	capacity_index int not null,
	reinforced varchar(5) not null,
	run_on_flat boolean not null,
	season_id int not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	foreign key (season_id) references tire_season(id),
	primary key (id));

	CREATE TABLE vehicles (
    id int AUTO_INCREMENT, 
    brand_id int not null, 
    model_id int not null, 
    registration_number varchar(15) not null, 
    vin varchar(20) not null, 
    production_year int not null,
    first_registration_date date not null,
    free_places_for_technical_inspections int not null,
    fuel_type_id int not null,
    vehicle_type_id int not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id),
	foreign key (vehicle_type_id) references vehicle_type(id),
	foreign key (fuel_type_id) references fuel_type(id),
	foreign key (brand_id) references vehicle_brand(id),
	foreign key (model_id) references vehicle_model(id));
	
	CREATE TABLE vehicle_tires(
	id int auto_increment,
	vehicle_id int not null,
	tire_id int not null,
	status varchar(30),
	production_year int not null,
	purchase_date date not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id),
	foreign key (vehicle_id) references vehicles(id),
	foreign key (tire_id) references tires(id));
	
	CREATE TABLE service_actions(
	id int auto_increment,
	vehicle_id int not null,
	car_mileage int not null,
	service_date date not null,
	invoice_no varchar(40) not null,
	service_workshop varchar(255) not null,
	service_action_type_id int not null,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id),
	foreign key (vehicle_id) references vehicles(id),
	foreign key (service_action_type_id) references service_action_type(id));
	

	
	
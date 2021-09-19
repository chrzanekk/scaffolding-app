
	
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
	name varchar(30),
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id));
	
	CREATE TABLE vehicle_model (
	id int auto_increment,
	brand_id int not null,
	name varchar(30),
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
	car_mileage int,
	service_date date,
	invoice_no varchar(40),
	service_workshop varchar(255),
	service_action_type_id int,
	create_date datetime default now(),
	modify_date datetime,
	remove_date datetime,
	primary key (id),
	foreign key (vehicle_id) references vehicles(id),
	foreign key (service_action_type_id) references service_action_type(id));
	
	
	INSERT INTO `fuel_type`(`name`) VALUES ('benzyna');
	INSERT INTO `fuel_type`(`name`) VALUES ('olej napędowy');
	
	
	INSERT INTO `tire_season`(`name` ) VALUES ('letnie');
	INSERT INTO `tire_season`(`name` ) VALUES ('zimowe');
	INSERT INTO `tire_season`(`name` ) VALUES ('wielosezonowe');
	
	
	INSERT INTO `vehicle_type`(`name`) VALUES ('osobowy');
	INSERT INTO `vehicle_type`(`name`) VALUES ('ciężarowy');
	INSERT INTO `vehicle_type`(`name`) VALUES ('bus');
	INSERT INTO `vehicle_type`(`name`) VALUES ('SUV');
	
	
	INSERT INTO `service_action_type`( `name`, `description`) VALUES ('przegląd okresowy' , 'badanie techniczne na stacji diagnostycznej');
	INSERT INTO `service_action_type`( `name`, `description`) VALUES ('serwis olejowy' , 'wymiana oleju, filtrów');
	INSERT INTO `service_action_type`( `name`, `description`) VALUES ('serwis opon' , 'usunięcie nieszczelności w oponie + wyważenie');
	
	
	INSERT INTO `vehicle_brand`(`name`) VALUES ('Renault');
	INSERT INTO `vehicle_brand`(`name`) VALUES ('Dacia');
	
	
	INSERT INTO `vehicle_model`(`brand_id`, `name`) VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Renault'), 'Megane Combi');
	INSERT INTO `vehicle_model`(`brand_id`, `name`) VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Dacia'), 'Dokker');
	
	
	INSERT INTO `tires`( `brand`, `model`, `width`, `profile`, `diameter`, `speed_index`, `capacity_index`, `season_id`, `reinforced`, `run_on_flat`) 
	VALUES ('Hankook', 'Summer Ride Supra', 205,55,16,'V',95,(SELECT id FROM tire_season WHERE name = 'letnie'),'no',0);
	INSERT INTO `tires`( `brand`, `model`, `width`, `profile`, `diameter`, `speed_index`, `capacity_index`, `season_id`, `reinforced`, `run_on_flat`) 
	VALUES ('Falken', 'Summer Run', 205,55,16,'V',91,(SELECT id FROM tire_season WHERE name = 'letnie'),'no',0);
	
	
	INSERT INTO `vehicles`(`brand_id`, `model_id`, `registration_number`, `vin`, `production_year`, `first_registration_date`, `free_places_for_technical_inspections`, `fuel_type_id`, `vehicle_type_id`) 
	VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Dacia'),
			(SELECT id FROM vehicle_model WHERE name = 'Dokker'),
			'LPU 78226',
			'AAA00022228881111',
			2015,
			'2015-05-15',
			3,
			(SELECT id FROM fuel_type WHERE name = 'olej napędowy'),
			(SELECT id FROM vehicle_type WHERE name = 'osobowy')
			);	
	INSERT INTO `vehicles`(`brand_id`, `model_id`, `registration_number`, `vin`, `production_year`, `first_registration_date`, `free_places_for_technical_inspections`, `fuel_type_id`, `vehicle_type_id`) 
	VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Renault'),
			(SELECT id FROM vehicle_model WHERE name = 'Megane Combi'),
			'LPU 42425',
			'BBB00023425685687',
			2013,
			'2014-03-05',
			4,
			(SELECT id FROM fuel_type WHERE name = 'olej napędowy'),
			(SELECT id FROM vehicle_type WHERE name = 'osobowy')
			);
		
		
		
	INSERT INTO `vehicle_tires`(`vehicle_id`, `tire_id`, `status`, `production_year`, `purchase_date`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			(SELECT id FROM tires WHERE brand = 'Hankook'),
			'U',
			2019,
			'2020-05-23'
			);
	INSERT INTO `vehicle_tires`(`vehicle_id`, `tire_id`, `status`, `production_year`, `purchase_date`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			(SELECT id FROM tires WHERE brand = 'Falken'),
			'U',
			2018,
			'2019-04-15'
			);
			
			
			
	INSERT INTO `service_actions`(`vehicle_id`, `car_mileage`, `service_date`, `invoice_no`, `service_workshop`, `service_action_type_id`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			213875,
			'2020-02-15',
			'FV/12/03/2020',
			'Andrzej Nopwak - Usługi Serwisowe',
			(SELECT id FROM service_action_type WHERE name = 'serwis olejowy')
			);
	INSERT INTO `service_actions`(`vehicle_id`, `car_mileage`, `service_date`, `invoice_no`, `service_workshop`, `service_action_type_id`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			240875,
			'2021-04-15',
			'FS/348/04/2021',
			'Renault Auto-Tamex',
			(SELECT id FROM service_action_type WHERE name = 'przegląd okresowy')
			);
	INSERT INTO `service_actions`(`vehicle_id`, `car_mileage`, `service_date`, `invoice_no`, `service_workshop`, `service_action_type_id`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			234875,
			'2021-05-22',
			'FV/12/05/2021',
			'Serwis Opon - Mizera',
			(SELECT id FROM service_action_type WHERE name = 'serwis opon')
			);
	INSERT INTO `service_actions`(`vehicle_id`, `car_mileage`, `service_date`, `invoice_no`, `service_workshop`, `service_action_type_id`) 
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			237225,
			'2021-06-26',
			'FS/18/06/2021',
			'Renault Auto Tamex',
			(SELECT id FROM service_action_type WHERE name = 'przegląd okresowy')
			);
	
	
	
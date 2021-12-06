	
	INSERT INTO fuel_type (name) VALUES ('benzyna');
	INSERT INTO fuel_type (name) VALUES ('olej napędowy');
	
	
	INSERT INTO tire_season(name) VALUES ('letnie');
	INSERT INTO tire_season(name) VALUES ('zimowe');
	INSERT INTO tire_season(name) VALUES ('wielosezonowe');
	
	
	INSERT INTO vehicle_type(name) VALUES ('osobowy');
	INSERT INTO vehicle_type(name) VALUES ('ciężarowy');
	INSERT INTO vehicle_type(name) VALUES ('motorower');
    INSERT INTO vehicle_type(name) VALUES ('motocykl');
    INSERT INTO vehicle_type(name) VALUES ('autobus');
    INSERT INTO vehicle_type(name) VALUES ('trolejbus');
    INSERT INTO vehicle_type(name) VALUES ('samochód specjalny');
    INSERT INTO vehicle_type(name) VALUES ('ciągnik samochodowy');
    INSERT INTO vehicle_type(name) VALUES ('ciągnik rolniczy');
    INSERT INTO vehicle_type(name) VALUES ('przyczepa lekka');
    INSERT INTO vehicle_type(name) VALUES ('naczepa ciężarowa');
    INSERT INTO vehicle_type(name) VALUES ('naczepa specjalna');
    INSERT INTO vehicle_type(name) VALUES ('przyczepa ciężarowa');
    INSERT INTO vehicle_type(name) VALUES ('przyczepa specjalna');
    INSERT INTO vehicle_type(name) VALUES ('przyczepa ciężarowa rolnicza');
    INSERT INTO vehicle_type(name) VALUES ('inny');

	
	INSERT INTO service_action_type(name) VALUES ('przegląd okresowy');
	INSERT INTO service_action_type(name) VALUES ('serwis olejowy');
	INSERT INTO service_action_type(name) VALUES ('serwis opon');
	INSERT INTO service_action_type(name) VALUES ('inne');

	
	
	INSERT INTO vehicle_brand(name) VALUES ('Renault');
	INSERT INTO vehicle_brand(name) VALUES ('Dacia');
	
	
	INSERT INTO vehicle_model(brand_id, name) VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Renault'), 'Megane Combi');
	INSERT INTO vehicle_model(brand_id, name) VALUES ((SELECT id FROM vehicle_brand WHERE name = 'Dacia'), 'Dokker');

    INSERT INTO workshops (name, tax_number, street, building_number, apartment_number, postal_code, city)
            VALUES('Usługi Blacharsko - Lakiernicze Andrzej Nowak', '716-102-4618', 'Oblasy', '155', null, '24-123',
            'Janowiec');

    INSERT INTO workshops (name, tax_number, street, building_number, apartment_number, postal_code, city)
            VALUES('Renault Auto Tamex', '716-100-1214', 'Lubelska', '43', null, '24-100',
            'Puławy');
    INSERT INTO workshops (name, tax_number, street, building_number, apartment_number, postal_code, city)
            VALUES('Serwis Opon Mizera', '716-105-1514', '6 Sierpnia', '12', null, '24-100',
            'Puławy');

	
	
	INSERT INTO tires(brand, model, width, profile, diameter, speed_index, capacity_index, season_id,
	                    reinforced, run_on_flat, type)
	VALUES ('Hankook', 'Summer Ride Supra', 205,55,16,'V',95,(SELECT id FROM tire_season WHERE name = 'letnie'),'no',0,
	'r');
	INSERT INTO tires(brand, model, width, profile, diameter, speed_index, capacity_index, season_id,
                	 reinforced, run_on_flat, type)
	VALUES ('Falken', 'Summer Run', 205,55,16,'V',91,(SELECT id FROM tire_season WHERE name = 'letnie'),'no',0, 'r');
	
	
	INSERT INTO vehicles(brand_id, model_id, registration_number, vin, production_year, first_registration_date, free_places_for_technical_inspections, fuel_type_id, vehicle_type_id)
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
	INSERT INTO vehicles(brand_id, model_id, registration_number, vin, production_year, first_registration_date, free_places_for_technical_inspections, fuel_type_id, vehicle_type_id)
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
		
		
		
	INSERT INTO vehicle_tires(vehicle_id, tire_id, status, production_year, purchase_date)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			(SELECT id FROM tires WHERE brand = 'Hankook'),
			'U',
			2019,
			'2020-05-23'
			);
	INSERT INTO vehicle_tires(vehicle_id, tire_id, status, production_year, purchase_date)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			(SELECT id FROM tires WHERE brand = 'Falken'),
			'U',
			2018,
			'2019-04-15'
			);
			
			
			
	INSERT INTO service_actions(vehicle_id, car_mileage, service_date, invoice_no, workshop_id, service_action_type_id,
	invoice_gross_value, invoice_net_value, tax_value, tax_rate)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			213875,
			'2020-02-15',
			'FV/12/03/2020',
			(SELECT id FROM workshops WHERE name = 'Andrzej Nopwak - Usługi Serwisowe'),
			(SELECT id FROM service_action_type WHERE name = 'serwis olejowy'),
			0,
            0,
            0,
            0.23
			);
	INSERT INTO service_actions(vehicle_id, car_mileage, service_date, invoice_no, workshop_id, service_action_type_id,
	invoice_gross_value, invoice_net_value, tax_value, tax_rate)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 78226'),
			240875,
			'2021-04-15',
			'FS/348/04/2021',
			(SELECT id FROM workshops WHERE name = 'Renault Auto Tamex'),
			(SELECT id FROM service_action_type WHERE name = 'przegląd okresowy'),
			0,
			0,
			0,
			0.23
			);
	INSERT INTO service_actions(vehicle_id, car_mileage, service_date, invoice_no, workshop_id, service_action_type_id
	,invoice_gross_value, invoice_net_value, tax_value, tax_rate)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			234875,
			'2021-05-22',
			'FV/12/05/2021',
			(SELECT id FROM workshops WHERE name = 'Serwis Opon - Mizera'),
			(SELECT id FROM service_action_type WHERE name = 'serwis opon'),
			0,
            0,
            0,
            0.23
			);
	INSERT INTO service_actions(vehicle_id, car_mileage, service_date, invoice_no, workshop_id, service_action_type_id,
	invoice_gross_value, invoice_net_value, tax_value, tax_rate)
	VALUES ((SELECT id FROM vehicles WHERE registration_number = 'LPU 42425'),
			237225,
			'2021-06-26',
			'FS/18/06/2021',
			(SELECT id FROM workshops WHERE name = 'Renault Auto Tamex'),
			(SELECT id FROM service_action_type WHERE name = 'przegląd okresowy'),
			0,
            0,
            0,
            0.23
			);
	
	

	

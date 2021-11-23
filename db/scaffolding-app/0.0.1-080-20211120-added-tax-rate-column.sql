ALTER TABLE service_actions
ADD tax_rate DECIMAL(10,2) NOT NULL;

UPDATE service_actions
SET tax_rate = 1.23
WHERE id >=1;
ALTER TABLE service_actions
ADD invoice_gross_value DECIMAL(10,2) NOT NULL;

ALTER TABLE service_actions
ADD invoice_net_value DECIMAL(10,2) NOT NULL;

ALTER TABLE service_actions
ADD tax_value DECIMAL(10,2) NOT NULL;

UPDATE service_actions
SET invoice_gross_value = 0
WHERE id >=1;

UPDATE service_actions
SET invoice_net_value = 0
WHERE id >=1;

UPDATE service_actions
SET tax_value = 0
WHERE id >=1;
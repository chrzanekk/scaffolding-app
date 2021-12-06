ALTER TABLE service_actions
ADD invoice_gross_value DECIMAL(10,2) NOT NULL;

ALTER TABLE service_actions
ADD invoice_net_value DECIMAL(10,2) NOT NULL;

ALTER TABLE service_actions
ADD tax_value DECIMAL(10,2) NOT NULL;


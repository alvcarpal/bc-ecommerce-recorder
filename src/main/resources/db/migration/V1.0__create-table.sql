-- Prices table creation
CREATE TABLE IF NOT EXISTS prices (
	id                         UUID PRIMARY KEY,
    brand_id                    VARCHAR(255) NOT NULL,
    product_id                 VARCHAR(255) NOT NULL,
    price_list                 INT NOT NULL,
    start_date                 TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date                   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    price                      NUMERIC(15, 2) NOT NULL,
    currency                   VARCHAR(3) NOT NULL,
    priority                   INT NOT NULL,
	UNIQUE(product_id, price_list, priority)
);



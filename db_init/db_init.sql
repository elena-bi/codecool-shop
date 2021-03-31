CREATE DATABASE codecoolshop;
CREATE USER dbsuperadmin1 WITH ENCRYPTED PASSWORD 'dbsuperadmin1';
GRANT ALL PRIVILEGES ON DATABASE codecoolshop TO dbsuperadmin1;

\CONNECT codecoolshop

CREATE TABLE IF NOT EXISTS cart
(
    product_id INT NOT NULL,
    product_amount INT NOT NULL
);

CREATE UNIQUE INDEX cart_product_id_uindex
    ON cart (product_id);

ALTER TABLE cart
    ADD CONSTRAINT cart_pk
        PRIMARY KEY (product_id);
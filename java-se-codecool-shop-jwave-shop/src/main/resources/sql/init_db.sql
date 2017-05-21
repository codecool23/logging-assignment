DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;

CREATE TABLE suppliers
(
  supplier_id int PRIMARY KEY,
  name varchar UNIQUE,
  description varchar
);

CREATE TABLE categories
(
  category_id int PRIMARY KEY,
  name varchar UNIQUE,
  department varchar,
  description varchar
);

CREATE TABLE products
(
  id int PRIMARY KEY,
  name varchar UNIQUE,
  defaultPrice float,
  currencyString varchar,
  description varchar,
--  productCategory varchar,
--  supplier varchar
  cat_id INTEGER REFERENCES categories(category_id),
  sup_id INTEGER REFERENCES suppliers(supplier_id)
);
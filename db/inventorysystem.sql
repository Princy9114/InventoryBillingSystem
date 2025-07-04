CREATE TABLE inventorysystem.users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL,
    status VARCHAR(45) default 1 not null
);

INSERT INTO inventorysystem.users (username, password, role) VALUES
('admin', 'admin123', 'admin'),
('staff1', 'staff123', 'staff');

CREATE TABLE inventorysystem.companies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    contact VARCHAR(50),
    status VARCHAR(45) default 1 not null
);

INSERT INTO inventorysystem.companies (name, address, contact) VALUES ('GADA electronics', 'mumbai', 7418529630);
INSERT INTO inventorysystem.companies (name, address, contact) VALUES ('Star Furniture', 'delhi', 1234569870);


CREATE TABLE inventorysystem.categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description varchar(200) NOT NULL,
    status VARCHAR(45) default 1
);

INSERT INTO inventorysystem.categories (name) VALUES ('Electronics'), ('Furniture'),('All new electronics item available');

CREATE TABLE inventorysystem.products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company_id INT,
    category_id INT,
    unit_price DOUBLE,
    purchase_price DOUBLE,
    gst DOUBLE,
    description TEXT,
    status VARCHAR(45) default 1,
    FOREIGN KEY (company_id) REFERENCES companies(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO inventorysystem.products (name, company_id, category_id, unit_price, purchase_price, gst, description) VALUES
('Smartphone', 1, 1, 12000.00, 10000.00, 18.0, 'Latest Android phone'),
('Chair', 2, 2, 800.00, 600.00, 12.0, 'Plastic chair');

CREATE TABLE inventorysystem.purchases (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    company_id INT,
    quantity INT,
    date DATE,
    unit_price DOUBLE,
    total_price DOUBLE,
    status VARCHAR(45) default 1,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

INSERT INTO inventorysystem.purchases (product_id, company_id, quantity, date, unit_price, total_price) VALUES
(4, 1, 10, '2025-06-01', 10000.00, 100000.00),
(5, 2, 5, '2025-06-05', 600.00, 3000.00);

CREATE TABLE stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT UNIQUE,
    quantity INT,
    status VARCHAR(45) default 1,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO stocks (product_id, quantity) VALUES
(4, 10),
(5, 5);

CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT,
    unit_price DOUBLE,
    tax_percent DOUBLE,
    total_amount DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    gst DECIMAL(10,2),
    grand_total DECIMAL(10,2)
);

CREATE TABLE invoice_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT,
    product_id INT,
    product_name VARCHAR(255),
    quantity INT,
    unit_price DECIMAL(10, 2),
    total_price DECIMAL(10, 2),
    `date` DATE,
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id)
);


-- Insert sample invoice
INSERT INTO invoices (customer_name, gst, grand_total)
VALUES ('max', 90.00, 590.00);

-- Get the last inserted invoice_id (assuming it's 1)
-- Insert sample items
INSERT INTO invoice_items (invoice_id, product_name, quantity, unit_price, total_price)
VALUES
(1, 'Wireless Mouse', 2, 200.00, 400.00),
(1, 'USB Cable', 1, 100.00, 100.00);



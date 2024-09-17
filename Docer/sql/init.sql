CREATE TABLE IF NOT EXISTS Users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20) UNIQUE,
    created_at VARCHAR(255),
    updated_at VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS Categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS Products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category_id BIGINT REFERENCES Categories(id) ON DELETE SET NULL,
    stock_quantity INTEGER NOT NULL,
    created_at VARCHAR(255),
    updated_at VARCHAR(255)
    );
   
   CREATE TABLE IF NOT EXISTS OrderStatus (
    id BIGSERIAL PRIMARY KEY,
    status_name VARCHAR(255) NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS Orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES Users(id) ON DELETE CASCADE,
    total_price DECIMAL(10, 2) NOT NULL,
    status_id BIGINT REFERENCES OrderStatus(id) ON DELETE SET NULL,
    created_at VARCHAR(255),
    updated_at VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS OrderItems (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES Orders(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES Products(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL,
    price_per_unit DECIMAL(10, 2) NOT NULL
    );



CREATE TABLE IF NOT EXISTS Cart (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES Users(id) ON DELETE CASCADE,
    created_at VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS CartItems (
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT REFERENCES Cart(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES Products(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL
    );

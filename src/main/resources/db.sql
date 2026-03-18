-- REMOVED CREATE DATABASE AND USE AS SPRING HANDLES CONNECTION

CREATE TABLE IF NOT EXISTS Exchanges (
    exchange_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    trading_start TIME,
    trading_end TIME
);

CREATE TABLE IF NOT EXISTS Securities (
    security_id INT PRIMARY KEY AUTO_INCREMENT,
    exchange_id INT NOT NULL,
    symbol VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100),
    FOREIGN KEY (exchange_id) REFERENCES Exchanges(exchange_id)
);

CREATE TABLE IF NOT EXISTS Participants (
    participant_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_details VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    participant_id INT NOT NULL,
    security_id INT NOT NULL,
    order_type ENUM('BUY','SELL') NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(12,4) NOT NULL CHECK (price > 0),
    order_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (participant_id) REFERENCES Participants(participant_id),
    FOREIGN KEY (security_id) REFERENCES Securities(security_id)
);

CREATE TABLE IF NOT EXISTS Trades (
    trade_id INT PRIMARY KEY AUTO_INCREMENT,
    buy_order_id INT NOT NULL,
    sell_order_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(12,4) NOT NULL CHECK (price > 0),
    trade_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (buy_order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (sell_order_id) REFERENCES Orders(order_id)
);

CREATE TABLE IF NOT EXISTS Positions (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    participant_id INT NOT NULL,
    security_id INT NOT NULL,
    quantity INT NOT NULL,
    average_price DECIMAL(12,4),
    entry_date DATE,
    exit_date DATE,
    FOREIGN KEY (participant_id) REFERENCES Participants(participant_id),
    FOREIGN KEY (security_id) REFERENCES Securities(security_id),
    UNIQUE (participant_id, security_id)
);

CREATE TABLE IF NOT EXISTS Blockers (
    blocker_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS BlockingPolicies (
    policy_id INT PRIMARY KEY AUTO_INCREMENT,
    blocker_id INT NOT NULL,
    participant_id INT NOT NULL,
    security_id INT NOT NULL,
    restriction_type VARCHAR(100) NOT NULL,
    effective_from DATE,
    effective_to DATE,
    FOREIGN KEY (blocker_id) REFERENCES Blockers(blocker_id),
    FOREIGN KEY (participant_id) REFERENCES Participants(participant_id),
    FOREIGN KEY (security_id) REFERENCES Securities(security_id)
);

-- POPULATING DATABASE WITH DATA (Using IGNORE to avoid duplicate errors on restart)
INSERT IGNORE INTO Exchanges (exchange_id, name, location, trading_start, trading_end) VALUES
(1, 'Rwanda Stock Exchange', 'Kigali', '09:00:00', '15:00:00'),
(2, 'East African Securities Market', 'Kigali', '08:30:00', '16:00:00');

INSERT IGNORE INTO Securities (security_id, exchange_id, symbol, name, type) VALUES
(1, 1, 'BK', 'Bank of Kigali', 'Equity'),
(2, 1, 'MTN', 'MTN Rwanda', 'Equity'),
(3, 1, 'RDB', 'Rwanda Development Board', 'Equity'),
(4, 2, 'I&M', 'I&M Bank Rwanda', 'Equity'),
(5, 2, 'CFL', 'CFL Telecom', 'Equity');

INSERT IGNORE INTO Participants (participant_id, name, address, contact_details) VALUES
(1, 'Jean-Claude Habimana', 'Kigali, Rwanda', '0781234567'),
(2, 'Aline Uwimana', 'Butare, Rwanda', '0789876543'),
(3, 'Eric Niyonsaba', 'Gisenyi, Rwanda', '0781122334'),
(4, 'Sandrine Mukamana', 'Kigali, Rwanda', '0785566778'),
(5, 'Patrick Uwera', 'Ruhengeri, Rwanda', '0789988776');

INSERT IGNORE INTO Orders (order_id, participant_id, security_id, order_type, quantity, price) VALUES
(1, 1, 1, 'BUY', 100, 450.50),
(2, 2, 1, 'SELL', 50, 450.50),
(3, 3, 2, 'BUY', 200, 300.00),
(4, 4, 2, 'SELL', 150, 305.00),
(5, 5, 3, 'BUY', 120, 500.00),
(6, 1, 3, 'SELL', 60, 500.00);

INSERT IGNORE INTO Trades (trade_id, buy_order_id, sell_order_id, quantity, price) VALUES
(1, 1, 2, 50, 450.50),
(2, 3, 4, 150, 305.00),
(3, 5, 6, 60, 500.00);

INSERT IGNORE INTO Positions (position_id, participant_id, security_id, quantity, average_price, entry_date) VALUES
(1, 1, 1, 50, 450.50, '2026-02-14'),
(2, 3, 2, 150, 305.00, '2026-02-14'),
(3, 5, 3, 60, 500.00, '2026-02-14');

INSERT IGNORE INTO Blockers (blocker_id, name) VALUES
(1, 'National Bank of Rwanda'),
(2, 'Capital Market Authority');

INSERT IGNORE INTO BlockingPolicies (policy_id, blocker_id, participant_id, security_id, restriction_type, effective_from, effective_to) VALUES
(1, 1, 2, 1, 'Trade Restriction', '2026-01-01', '2026-12-31'),
(2, 2, 4, 2, 'Short Selling Ban', '2026-01-01', '2026-06-30');

CREATE DATABASE securities_exchange_management;
use securities_exchange_management;

CREATE TABLE Exchanges (
    exchange_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    trading_start TIME,
    trading_end TIME
);


CREATE TABLE Securities (
    security_id INT PRIMARY KEY AUTO_INCREMENT,
    exchange_id INT NOT NULL,
    symbol VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100),
    FOREIGN KEY (exchange_id) REFERENCES Exchanges(exchange_id)
);


CREATE TABLE Participants (
    participant_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_details VARCHAR(255)
);

CREATE TABLE Orders (
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


CREATE TABLE Trades (
    trade_id INT PRIMARY KEY AUTO_INCREMENT,
    buy_order_id INT NOT NULL,
    sell_order_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(12,4) NOT NULL CHECK (price > 0),
    trade_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (buy_order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (sell_order_id) REFERENCES Orders(order_id)
);


CREATE TABLE Positions (
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


CREATE TABLE Blockers (
    blocker_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE BlockingPolicies (
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


-- POPULATING DATABSE WITH DATA


INSERT INTO Exchanges (name, location, trading_start, trading_end) VALUES
('Rwanda Stock Exchange', 'Kigali', '09:00:00', '15:00:00'),
('East African Securities Market', 'Kigali', '08:30:00', '16:00:00');

INSERT INTO Securities (exchange_id, symbol, name, type) VALUES
(1, 'BK', 'Bank of Kigali', 'Equity'),
(1, 'MTN', 'MTN Rwanda', 'Equity'),
(1, 'RDB', 'Rwanda Development Board', 'Equity'),
(2, 'I&M', 'I&M Bank Rwanda', 'Equity'),
(2, 'CFL', 'CFL Telecom', 'Equity');

INSERT INTO Participants (name, address, contact_details) VALUES
('Jean-Claude Habimana', 'Kigali, Rwanda', '0781234567'),
('Aline Uwimana', 'Butare, Rwanda', '0789876543'),
('Eric Niyonsaba', 'Gisenyi, Rwanda', '0781122334'),
('Sandrine Mukamana', 'Kigali, Rwanda', '0785566778'),
('Patrick Uwera', 'Ruhengeri, Rwanda', '0789988776');


INSERT INTO Orders (participant_id, security_id, order_type, quantity, price) VALUES
(1, 1, 'BUY', 100, 450.50),
(2, 1, 'SELL', 50, 450.50),
(3, 2, 'BUY', 200, 300.00),
(4, 2, 'SELL', 150, 305.00),
(5, 3, 'BUY', 120, 500.00),
(1, 3, 'SELL', 60, 500.00);

INSERT INTO Trades (buy_order_id, sell_order_id, quantity, price) VALUES
(1, 2, 50, 450.50),
(3, 4, 150, 305.00),
(5, 6, 60, 500.00);

INSERT INTO Positions (participant_id, security_id, quantity, average_price, entry_date) VALUES
(1, 1, 50, 450.50, '2026-02-14'),
(3, 2, 150, 305.00, '2026-02-14'),
(5, 3, 60, 500.00, '2026-02-14');


INSERT INTO Blockers (name) VALUES
('National Bank of Rwanda'),
('Capital Market Authority');


INSERT INTO BlockingPolicies (blocker_id, participant_id, security_id, restriction_type, effective_from, effective_to) VALUES
(1, 2, 1, 'Trade Restriction', '2026-01-01', '2026-12-31'),
(2, 4, 2, 'Short Selling Ban', '2026-01-01', '2026-06-30');


-- to view all securities
SELECT security_id, symbol, name, type, exchange_id
FROM Securities;

-- to list all partcipants
SELECT participant_id, name, address, contact_details
FROM Participants;

-- view all orders (with participant names)
SELECT o.order_id, p.name AS participant_name, s.symbol AS security, o.order_type,
       o.quantity, o.price, o.order_timestamp
FROM Orders o
JOIN Participants p ON o.participant_id = p.participant_id
JOIN Securities s ON o.security_id = s.security_id;

-- view all trades (with buyer and seller names)
SELECT t.trade_id,
       bp.name AS buyer_name,
       sp.name AS seller_name,
       s.symbol AS security,
       t.quantity,
       t.price,
       t.trade_timestamp
FROM Trades t
JOIN Orders bo ON t.buy_order_id = bo.order_id
JOIN Participants bp ON bo.participant_id = bp.participant_id
JOIN Orders so ON t.sell_order_id = so.order_id
JOIN Participants sp ON so.participant_id = sp.participant_id
JOIN Securities s ON bo.security_id = s.security_id;

-- current positions for each participant
SELECT p.name AS participant_name,
       s.symbol AS security,
       pos.quantity,
       pos.average_price
FROM Positions pos
JOIN Participants p ON pos.participant_id = p.participant_id
JOIN Securities s ON pos.security_id = s.security_id;

-- find all orders for a specific security
SELECT o.order_id, p.name AS participant, o.order_type, o.quantity, o.price
FROM Orders o
JOIN Participants p ON o.participant_id = p.participant_id
JOIN Securities s ON o.security_id = s.security_id
WHERE s.symbol = 'BK';

-- participants restricted from a security (blocking policy)
SELECT p.name AS participant, s.symbol AS security, b.name AS blocker, bp.restriction_type
FROM BlockingPolicies bp
JOIN Participants p ON bp.participant_id = p.participant_id
JOIN Securities s ON bp.security_id = s.security_id
JOIN Blockers b ON bp.blocker_id = b.blocker_id
WHERE CURDATE() BETWEEN bp.effective_from AND bp.effective_to;

-- total trades volume per security
SELECT s.symbol AS security, SUM(t.quantity) AS total_traded
FROM Trades t
JOIN Orders o ON t.buy_order_id = o.order_id
JOIN Securities s ON o.security_id = s.security_id
GROUP BY s.symbol;

-- highest buyer by volume
SELECT p.name AS participant, SUM(t.quantity * t.price) AS total_spent
FROM Trades t
JOIN Orders bo ON t.buy_order_id = bo.order_id
JOIN Participants p ON bo.participant_id = p.participant_id
GROUP BY p.name
ORDER BY total_spent DESC
LIMIT 5;

-- all open orders (not yet traded)
SELECT o.order_id, p.name AS participant, s.symbol AS security, o.order_type, o.quantity, o.price
FROM Orders o
JOIN Participants p ON o.participant_id = p.participant_id
JOIN Securities s ON o.security_id = s.security_id
WHERE o.order_id NOT IN (
    SELECT buy_order_id FROM Trades
    UNION
    SELECT sell_order_id FROM Trades
);

-- INDEXING THE DATABASE

-- securities
CREATE INDEX idx_securities_symbol ON Securities(symbol);
CREATE INDEX idx_securities_exchange ON Securities(exchange_id);

-- participants
CREATE INDEX idx_participants_name ON Participants(name);

-- Orders

CREATE INDEX idx_orders_participant ON Orders(participant_id);
CREATE INDEX idx_orders_security ON Orders(security_id);
CREATE INDEX idx_orders_type_price ON Orders(order_type, price); 

-- trades

CREATE INDEX idx_trades_buy_order ON Trades(buy_order_id);
CREATE INDEX idx_trades_sell_order ON Trades(sell_order_id);
CREATE INDEX idx_trades_timestamp ON Trades(trade_timestamp);

-- positions

CREATE INDEX idx_positions_participant ON Positions(participant_id);
CREATE INDEX idx_positions_security ON Positions(security_id);
CREATE INDEX idx_positions_participant ON Positions(participant_id);


-- blocking policies

CREATE INDEX idx_blocking_policy_participant ON BlockingPolicies(participant_id);
CREATE INDEX idx_blocking_policy_security ON BlockingPolicies(security_id);
CREATE INDEX idx_blocking_policy_dates ON BlockingPolicies(effective_from, effective_to);


CREATE INDEX idx_orders_participant_security ON Orders(participant_id, security_id);



-- LAB 1 USER MANAGEMENT
-- CREATE USER

CREATE USER 'bob'@'localhost' IDENTIFIED BY '12345';

use securities_exchange_management;

GRANT ALL PRIVILEGES ON securities_exchange_management.*  TO 'bob'@'localhost';

REVOKE CREATE ON securities_exchange_management FROM 'bob'@'localhost';

GRANT CREATE ON securities_exchange_management.* TO 'bob'@'localhost';


DROP USER 'bob'@'localhost';

-- LAB 2 BACKUP AND RESTORE



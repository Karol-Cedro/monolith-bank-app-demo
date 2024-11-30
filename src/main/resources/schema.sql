CREATE TABLE IF NOT EXISTS `operation` (
    `operation_id` int NOT NULL AUTO_INCREMENT,
    `operation_type` varchar(15) NOT NULL,
    `operation_amount` int NOT NULL,
    `account_id` int NOT NULL,
    PRIMARY KEY (`operation_id`)
    );

CREATE TABLE IF NOT EXISTS `card` (
    `card_id` int NOT NULL AUTO_INCREMENT,
    `owner_name` varchar(15) NOT NULL,
    `owner_surname` varchar(15) NOT NULL,
    `account_id` int NOT NULL,
    `card_number` varchar(100) NOT NULL,
    `total_limit` int NOT NULL,
    `available_amount` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`card_id`)
    );

CREATE TABLE IF NOT EXISTS `account` (
    `account_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `surname` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `account_number` int NOT NULL,
    `balance` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`account_id`)
    );

INSERT INTO card (owner_name, owner_surname, account_id, card_number, total_limit, available_amount, created_at, created_by)
VALUES ('John', 'Doe', 1, '1234567890123456', 10000, 10000, '2022-01-01', 'John');

INSERT INTO card (owner_name, owner_surname, account_id, card_number, total_limit, available_amount, created_at, created_by)
VALUES ('Jakub', 'Kowalski', 2, '3334567890123456', 10000, 10000, '2022-01-01', 'Jakub');

INSERT INTO card (owner_name, owner_surname, account_id, card_number, total_limit, available_amount, created_at, created_by)
VALUES ('Kacper', 'Szczepanowski', 3, '5534567890123456', 10000, 10000, '2022-01-01', 'Kacper');

INSERT INTO `account` (`name`,`surname`,`email`,`mobile_number`,`account_number`,`balance`,`created_at`,`created_by`)
VALUES ('John', 'Doe', 'jondoe@example.com',654321567, 1234590126, 100000, '2022-01-01', 'John');

INSERT INTO `account` (`name`,`surname`,`email`,`mobile_number`,`account_number`,`balance`,`created_at`,`created_by`)
VALUES ('Jakub', 'Kowalski', 'jakubkowalski@example.com',654421567, 1554577126, 100000, '2022-01-01', 'Jakub');

INSERT INTO `account` (`name`,`surname`,`email`,`mobile_number`,`account_number`,`balance`,`created_at`,`created_by`)
VALUES ('Kacper', 'Szczepanowski', 'kacperszczepanowski@example.com',462387156, 934590126, 100000, '2022-01-01', 'Kacper');
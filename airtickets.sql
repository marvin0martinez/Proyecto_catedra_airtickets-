-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 23, 2025 at 04:53 AM
-- Server version: 5.7.40
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airtickets`
--

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
CREATE TABLE IF NOT EXISTS `airline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_airline_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `airline`
--

INSERT INTO `airline` (`id`, `code`, `name`) VALUES
(1, 'AV', 'Avianca'),
(2, 'CM', 'Copa Airlines');

-- --------------------------------------------------------

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
CREATE TABLE IF NOT EXISTS `airport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `iata` varchar(8) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_airport_iata` (`iata`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `airport`
--

INSERT INTO `airport` (`id`, `iata`, `city`, `country`) VALUES
(1, 'SAL', 'San Salvador', 'El Salvador'),
(2, 'MIA', 'Miami', 'USA'),
(3, 'PTY', 'Panama City', 'Panama'),
(4, 'LAS', 'Las Vegas', 'USA'),
(5, 'BOG', 'Bogotá', 'Colombia');

-- --------------------------------------------------------

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `reservation_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_claim_user` (`user_id`),
  KEY `idx_claim_reservation` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
CREATE TABLE IF NOT EXISTS `flight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `airline_id` bigint(20) NOT NULL,
  `origin_id` bigint(20) NOT NULL,
  `destination_id` bigint(20) NOT NULL,
  `aircraft_type` varchar(255) DEFAULT NULL,
  `capacity` int(11) NOT NULL,
  `base_fare` double NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  `booked` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_flight_unique` (`airline_id`,`origin_id`,`destination_id`,`departure_time`),
  KEY `idx_flight_departure` (`departure_time`),
  KEY `idx_flight_origin` (`origin_id`),
  KEY `idx_flight_destination` (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`id`, `airline_id`, `origin_id`, `destination_id`, `aircraft_type`, `capacity`, `base_fare`, `departure_time`, `arrival_time`, `booked`) VALUES
(1, 1, 1, 2, 'A320', 180, 220, '2025-10-23 14:00:00', '2025-10-23 17:00:00', 0),
(2, 2, 1, 3, '737-800', 160, 180, '2025-10-23 15:30:00', '2025-10-23 17:30:00', 0),
(3, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-23 20:10:00', '2025-10-24 01:10:00', 0),
(4, 1, 1, 5, 'A320neo', 180, 210, '2025-10-23 13:45:00', '2025-10-23 16:45:00', 0),
(5, 1, 1, 2, 'A320', 180, 220, '2025-10-24 14:00:00', '2025-10-24 17:00:00', 0),
(6, 2, 1, 3, '737-800', 160, 180, '2025-10-24 15:30:00', '2025-10-24 17:30:00', 0),
(7, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-24 20:10:00', '2025-10-25 01:10:00', 0),
(8, 1, 1, 5, 'A320neo', 180, 210, '2025-10-24 13:45:00', '2025-10-24 16:45:00', 0),
(9, 1, 1, 2, 'A320', 180, 220, '2025-10-25 14:00:00', '2025-10-25 17:00:00', 0),
(10, 2, 1, 3, '737-800', 160, 180, '2025-10-25 15:30:00', '2025-10-25 17:30:00', 0),
(11, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-25 20:10:00', '2025-10-26 01:10:00', 0),
(12, 1, 1, 5, 'A320neo', 180, 210, '2025-10-25 13:45:00', '2025-10-25 16:45:00', 0),
(13, 1, 1, 2, 'A320', 180, 220, '2025-10-26 14:00:00', '2025-10-26 17:00:00', 0),
(14, 2, 1, 3, '737-800', 160, 180, '2025-10-26 15:30:00', '2025-10-26 17:30:00', 0),
(15, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-26 20:10:00', '2025-10-27 01:10:00', 0),
(16, 1, 1, 5, 'A320neo', 180, 210, '2025-10-26 13:45:00', '2025-10-26 16:45:00', 0),
(17, 1, 1, 2, 'A320', 180, 220, '2025-10-27 14:00:00', '2025-10-27 17:00:00', 0),
(18, 2, 1, 3, '737-800', 160, 180, '2025-10-27 15:30:00', '2025-10-27 17:30:00', 0),
(19, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-27 20:10:00', '2025-10-28 01:10:00', 0),
(20, 1, 1, 5, 'A320neo', 180, 210, '2025-10-27 13:45:00', '2025-10-27 16:45:00', 0),
(21, 1, 1, 2, 'A320', 180, 220, '2025-10-28 14:00:00', '2025-10-28 17:00:00', 0),
(22, 2, 1, 3, '737-800', 160, 180, '2025-10-28 15:30:00', '2025-10-28 17:30:00', 0),
(23, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-28 20:10:00', '2025-10-29 01:10:00', 0),
(24, 1, 1, 5, 'A320neo', 180, 210, '2025-10-28 13:45:00', '2025-10-28 16:45:00', 0),
(25, 1, 1, 2, 'A320', 180, 220, '2025-10-29 14:00:00', '2025-10-29 17:00:00', 0),
(26, 2, 1, 3, '737-800', 160, 180, '2025-10-29 15:30:00', '2025-10-29 17:30:00', 0),
(27, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-29 20:10:00', '2025-10-30 01:10:00', 0),
(28, 1, 1, 5, 'A320neo', 180, 210, '2025-10-29 13:45:00', '2025-10-29 16:45:00', 0),
(29, 1, 1, 2, 'A320', 180, 220, '2025-10-30 14:00:00', '2025-10-30 17:00:00', 0),
(30, 2, 1, 3, '737-800', 160, 180, '2025-10-30 15:30:00', '2025-10-30 17:30:00', 0),
(31, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-30 20:10:00', '2025-10-31 01:10:00', 0),
(32, 1, 1, 5, 'A320neo', 180, 210, '2025-10-30 13:45:00', '2025-10-30 16:45:00', 0),
(33, 1, 1, 2, 'A320', 180, 220, '2025-10-31 14:00:00', '2025-10-31 17:00:00', 0),
(34, 2, 1, 3, '737-800', 160, 180, '2025-10-31 15:30:00', '2025-10-31 17:30:00', 0),
(35, 2, 1, 4, '737 MAX 9', 170, 420, '2025-10-31 20:10:00', '2025-11-01 01:10:00', 0),
(36, 1, 1, 5, 'A320neo', 180, 210, '2025-10-31 13:45:00', '2025-10-31 16:45:00', 0),
(37, 1, 1, 2, 'A320', 180, 220, '2025-11-01 14:00:00', '2025-11-01 17:00:00', 0),
(38, 2, 1, 3, '737-800', 160, 180, '2025-11-01 15:30:00', '2025-11-01 17:30:00', 0),
(39, 2, 1, 4, '737 MAX 9', 170, 420, '2025-11-01 20:10:00', '2025-11-02 01:10:00', 0),
(40, 1, 1, 5, 'A320neo', 180, 210, '2025-11-01 13:45:00', '2025-11-01 16:45:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
CREATE TABLE IF NOT EXISTS `passenger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `document_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint(20) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `paid_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_payment_reservation` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flight_id` bigint(20) NOT NULL,
  `passenger_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `locator` varchar(32) NOT NULL,
  `service_class` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_reservation_locator` (`locator`),
  KEY `idx_reservation_user` (`user_id`),
  KEY `fk_reservation_flight` (`flight_id`),
  KEY `fk_reservation_passenger` (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roles_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(5, 'AGENT'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `full_name`, `enabled`) VALUES
(1, 'admin@demo.local', '$2a$10$0r5bpUb1v7zArsA7iQkAKOqJikbNFq8mKU0Ov3b2bK0JkWexC6Fru', 'Administrador', 1),
(2, 'user1@demo.local', '$2a$10$0r5bpUb1v7zArsA7iQkAKOqJikbNFq8mKU0Ov3b2bK0JkWexC6Fru', 'Usuario Uno', 1),
(3, 'user2@demo.local', '$2a$10$0r5bpUb1v7zArsA7iQkAKOqJikbNFq8mKU0Ov3b2bK0JkWexC6Fru', 'Usuario Dos', 1),
(4, 'admin@air.tix', '$2a$10$J79D/3phNaYx3R46F4pfruQ4AzTrK43kLJEUAPRMN6QuMTro/38g2', 'Admin', 1),
(5, 'agent@air.tix', '$2a$10$I5Qv2dQ5KoXUfdk1ey2oBuqLlxdd7J2oxyvuR..0uZNOqjyUqiK/a', 'Agent', 1),
(6, 'user@air.tix', '$2a$10$nJWHe9xiIAQSMJv/KBwMdOfNG7DIkrhRWmxITQsKLfd.PW508sOeG', 'User', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `idx_user_roles_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(4, 1),
(6, 2),
(5, 5);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `claim`
--
ALTER TABLE `claim`
  ADD CONSTRAINT `fk_claim_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_claim_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `fk_flight_airline` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_flight_destination` FOREIGN KEY (`destination_id`) REFERENCES `airport` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_flight_origin` FOREIGN KEY (`origin_id`) REFERENCES `airport` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_flight` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reservation_passenger` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_user_roles_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_roles_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

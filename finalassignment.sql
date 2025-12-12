/*
 Navicat Premium Data Transfer

 Source Server         : Le MySQL
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : finalassignment

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 12/12/2025 11:14:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `idCustomer` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `customerTelp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idCustomer`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO `customer` (`idCustomer`, `customerName`, `customerTelp`) VALUES (1, 'Alan Grant', '08123456789'), (2, 'Ellie Sattler', '08987654321'), (3, 'Luffy', '0812345678'), (4, 'Bachiko', '08987654321');
COMMIT;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game`  (
  `idGame` int NOT NULL AUTO_INCREMENT,
  `gameTitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idGenre` int NULL DEFAULT NULL,
  `priceBuy` int NULL DEFAULT NULL,
  `priceRent` int NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `studio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idGame`) USING BTREE,
  INDEX `fk_genre`(`idGenre` ASC) USING BTREE,
  CONSTRAINT `fk_genre` FOREIGN KEY (`idGenre`) REFERENCES `genre` (`idGenre`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of game
-- ----------------------------
BEGIN;
INSERT INTO `game` (`idGame`, `gameTitle`, `idGenre`, `priceBuy`, `priceRent`, `publisher`, `studio`) VALUES (1, 'Jurassic: The Hunted', 1, 250000, 50000, 'Activision', 'Cauldron HQ'), (2, 'Jurassic World Evolution 3', 2, 750000, 150000, 'Frontier Developments', 'Frontier');
COMMIT;

-- ----------------------------
-- Table structure for genre
-- ----------------------------
DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre`  (
  `idGenre` int NOT NULL AUTO_INCREMENT,
  `genreName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `genreDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idGenre`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of genre
-- ----------------------------
BEGIN;
INSERT INTO `genre` (`idGenre`, `genreName`, `genreDesc`) VALUES (1, 'First-Person Shooter', 'Action games played from the first-person perspective.'), (2, 'Simulation', 'Games that simulate real-world activities and management.'), (3, 'Prehistoric', 'A genre where we live in eras before the modern era.'), (5, 'Sci-Fi', 'Stands for Science Fiction');
COMMIT;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `idPurchase` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NULL DEFAULT NULL,
  `idGame` int NULL DEFAULT NULL,
  `qty` int NULL DEFAULT NULL,
  `purchaseDate` datetime NULL DEFAULT NULL,
  `totalPrice` int NULL DEFAULT NULL,
  PRIMARY KEY (`idPurchase`) USING BTREE,
  INDEX `fk_customer`(`idCustomer` ASC) USING BTREE,
  INDEX `fk_game`(`idGame` ASC) USING BTREE,
  CONSTRAINT `fk_customer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_game` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of purchase
-- ----------------------------
BEGIN;
INSERT INTO `purchase` (`idPurchase`, `idCustomer`, `idGame`, `qty`, `purchaseDate`, `totalPrice`) VALUES (2, 2, 2, NULL, '2023-11-05 00:00:00', 750000), (3, 1, 2, 4, '2025-12-10 00:00:00', 3000000), (4, 3, 2, 3, '2025-12-11 00:00:00', 2250000), (5, 3, 1, 2, '2025-12-11 00:00:00', 500000), (6, 4, 1, 3, '2025-12-11 00:00:00', 750000), (8, 4, 2, 3, '2025-12-11 00:00:00', 2250000);
COMMIT;

-- ----------------------------
-- Table structure for renting
-- ----------------------------
DROP TABLE IF EXISTS `renting`;
CREATE TABLE `renting`  (
  `idRenting` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NULL DEFAULT NULL,
  `idGame` int NULL DEFAULT NULL,
  `transactionDate` datetime NULL DEFAULT NULL,
  `totalPrice` int NULL DEFAULT NULL,
  `rentDate` datetime NULL DEFAULT NULL,
  `returnDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`idRenting`) USING BTREE,
  INDEX `fk_customer`(`idCustomer` ASC) USING BTREE,
  INDEX `fk_game`(`idGame` ASC) USING BTREE,
  CONSTRAINT `renting_ibfk_1` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `renting_ibfk_2` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of renting
-- ----------------------------
BEGIN;
INSERT INTO `renting` (`idRenting`, `idCustomer`, `idGame`, `transactionDate`, `totalPrice`, `rentDate`, `returnDate`) VALUES (1, 3, 1, '2025-12-11 00:00:00', 4000000, '2025-12-11 00:00:00', '2026-03-01 00:00:00'), (2, 1, 2, '2025-12-11 00:00:00', 9300000, '2025-12-11 00:00:00', '2026-02-11 00:00:00'), (3, 1, 2, '2025-12-11 00:00:00', 4800000, '2025-12-11 00:00:00', '2026-01-12 00:00:00'), (4, 4, 1, '2025-12-11 00:00:00', 2600000, '2025-12-11 00:00:00', '2026-02-01 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

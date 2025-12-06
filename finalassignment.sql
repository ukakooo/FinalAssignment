/*
 Navicat Premium Dump SQL

 Source Server         : Le MySQL
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : finalassignment

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 06/12/2025 22:17:09
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for genre
-- ----------------------------
DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre`  (
  `idGenre` int NOT NULL AUTO_INCREMENT,
  `genreName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `genreDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idGenre`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `idTransaction` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NULL DEFAULT NULL,
  `idGame` int NULL DEFAULT NULL,
  `transactionDate` datetime NULL DEFAULT NULL,
  `totalPrice` int NULL DEFAULT NULL,
  PRIMARY KEY (`idTransaction`) USING BTREE,
  INDEX `fk_customer`(`idCustomer` ASC) USING BTREE,
  INDEX `fk_game`(`idGame` ASC) USING BTREE,
  CONSTRAINT `fk_customer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_game` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

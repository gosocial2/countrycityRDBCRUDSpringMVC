/*
 Navicat Premium Data Transfer

 Source Server         : localhostM
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : countrycityperson

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 21/02/2020 12:17:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `country_code` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `fk_city_4_country` (`country_code`),
  CONSTRAINT `fk_city_4_country` FOREIGN KEY (`country_code`) REFERENCES `country` (`iso_code`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of city
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES (1, 'Paris', 'FR', b'1', b'0');
INSERT INTO `city` VALUES (2, 'London', 'GB', b'1', b'0');
INSERT INTO `city` VALUES (3, 'New York', 'US', b'1', b'0');
INSERT INTO `city` VALUES (4, 'Berlin', 'DE', b'1', b'0');
INSERT INTO `city` VALUES (5, 'Istanbul', 'TR', b'1', b'0');
INSERT INTO `city` VALUES (6, 'Brussels', 'BE', b'1', b'0');
INSERT INTO `city` VALUES (7, 'Brasilia', 'BR', b'1', b'0');
INSERT INTO `city` VALUES (8, 'Roma', 'IT', b'1', b'0');
INSERT INTO `city` VALUES (9, 'Bursa', 'TR', b'1', b'0');
INSERT INTO `city` VALUES (10, 'Oslo', 'NO', b'1', b'0');
INSERT INTO `city` VALUES (11, 'Copenhagen', 'DK', b'1', b'0');
INSERT INTO `city` VALUES (12, 'Madrid', 'ES', b'1', b'0');
COMMIT;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `iso_code` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `country_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`iso_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of country
-- ----------------------------
BEGIN;
INSERT INTO `country` VALUES ('AR', 'Argentina', b'1', b'0');
INSERT INTO `country` VALUES ('AT', 'Austria', b'1', b'0');
INSERT INTO `country` VALUES ('AU', 'Australia', b'1', b'0');
INSERT INTO `country` VALUES ('BE', 'Belgium', b'1', b'0');
INSERT INTO `country` VALUES ('BR', 'Brazil', b'1', b'0');
INSERT INTO `country` VALUES ('DE', 'Germany', b'1', b'0');
INSERT INTO `country` VALUES ('DK', 'Denmark', b'1', b'0');
INSERT INTO `country` VALUES ('ES', 'Spain', b'1', b'0');
INSERT INTO `country` VALUES ('FR', 'France', b'1', b'0');
INSERT INTO `country` VALUES ('GB', 'United Kingdom', b'1', b'0');
INSERT INTO `country` VALUES ('IT', 'Italy', b'1', b'0');
INSERT INTO `country` VALUES ('LU', 'Luxembourg', b'1', b'0');
INSERT INTO `country` VALUES ('N4', 'Neverland (4)', b'1', b'0');
INSERT INTO `country` VALUES ('NL', 'Netherlands', b'1', b'0');
INSERT INTO `country` VALUES ('NO', 'Norway', b'1', b'0');
INSERT INTO `country` VALUES ('PT', 'Portugal', b'0', b'0');
INSERT INTO `country` VALUES ('RO', 'Romania', b'0', b'0');
INSERT INTO `country` VALUES ('TR', 'Turkey', b'1', b'0');
INSERT INTO `country` VALUES ('US', 'United States', b'1', b'0');
COMMIT;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `middle_name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `designation` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_code` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `city_name` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `is_friend` bit(1) NOT NULL DEFAULT b'0',
  `date_time_created` timestamp NULL DEFAULT NULL,
  `date_time_last_modified` timestamp NULL DEFAULT NULL,
  `notes` text COLLATE utf8_unicode_ci,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_person_4_city` (`city_id`),
  KEY `FK_person_4_country` (`country_code`),
  CONSTRAINT `FK_person_4_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_person_4_country` FOREIGN KEY (`country_code`) REFERENCES `country` (`iso_code`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of person
-- ----------------------------
BEGIN;
INSERT INTO `person` VALUES (1, 'Elliot', '', 'Alderson', 'IT Specialist', 'M', '', '', NULL, 3, NULL, '1980-01-02', 0, b'0', '2020-01-10 20:37:10', '2020-02-08 23:25:50', '', '', b'1', b'1');
INSERT INTO `person` VALUES (2, 'Nestor', NULL, 'Burma', 'Private Detetctive', 'M', NULL, NULL, NULL, 1, NULL, NULL, NULL, b'0', '2020-01-10 20:37:10', NULL, NULL, NULL, b'0', b'0');
INSERT INTO `person` VALUES (3, 'Peggy', NULL, 'Mitchell', 'Pub landlady', 'F', NULL, NULL, NULL, 2, NULL, NULL, NULL, b'0', '2020-01-10 20:37:10', NULL, NULL, NULL, b'0', b'0');
INSERT INTO `person` VALUES (4, 'Otto', NULL, 'Hantzen', 'Scientist', 'M', NULL, NULL, NULL, 4, NULL, NULL, NULL, b'0', '2020-01-10 20:37:10', NULL, NULL, NULL, b'0', b'0');
INSERT INTO `person` VALUES (5, 'Meltem', NULL, 'Çetinoğlu', 'HR manager', 'F', NULL, NULL, NULL, 5, NULL, NULL, NULL, b'0', '2020-01-10 20:37:10', NULL, NULL, NULL, b'0', b'0');
INSERT INTO `person` VALUES (6, 'Gökhan', '', 'Ozar', 'IT Specialist', 'M', '123-456-7890', 'inexistent@email.address', NULL, 9, NULL, NULL, NULL, b'0', '2020-02-09 17:11:23', '2020-02-10 18:56:31', '', '', b'1', b'1');
INSERT INTO `person` VALUES (7, 'Arsen', '', 'Lupen', 'Con Artist', 'M', '', 'arsenlupen@yahoo.fr', NULL, 1, NULL, NULL, NULL, b'0', '2020-02-10 18:57:37', NULL, '', '', b'1', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

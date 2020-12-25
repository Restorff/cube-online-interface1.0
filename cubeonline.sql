/*
 Navicat Premium Data Transfer

 Source Server         : CubeOnline
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-39vzglce.cd.tencentcdb.com:10150
 Source Schema         : cubeonline

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 25/12/2020 18:14:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qqNumber` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNumber` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'n',
  PRIMARY KEY (`aId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `cId` int(11) NOT NULL,
  `pId` int(11) NOT NULL,
  `e222` int(11) DEFAULT 0,
  `e333` int(11) DEFAULT 0,
  `e444` int(11) DEFAULT 0,
  `e555` int(11) DEFAULT 0,
  `e666` int(11) DEFAULT 0,
  `e777` int(11) DEFAULT 0,
  `e333bf` int(11) DEFAULT 0,
  `e333oh` int(11) DEFAULT 0,
  `e333fm` int(11) DEFAULT 0,
  `eclock` int(11) DEFAULT 0,
  `eminx` int(11) DEFAULT 0,
  `epyram` int(11) DEFAULT 0,
  `eskewb` int(11) DEFAULT 0,
  `esq1` int(11) DEFAULT 0,
  `e444bf` int(11) DEFAULT 0,
  `e555bf` int(11) DEFAULT 0,
  `e333mbf` int(11) DEFAULT 0,
  PRIMARY KEY (`aId`) USING BTREE,
  INDEX `cId`(`cId`) USING BTREE,
  INDEX `pId`(`pId`) USING BTREE,
  CONSTRAINT `apply_ibfk_3` FOREIGN KEY (`cId`) REFERENCES `competition` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `apply_ibfk_4` FOREIGN KEY (`pId`) REFERENCES `play` (`pId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `maxNum` int(11) DEFAULT NULL,
  `nowNum` int(11) DEFAULT 0,
  `e222` int(11) DEFAULT 0,
  `e333` int(11) DEFAULT 0,
  `e444` int(11) DEFAULT 0,
  `e555` int(11) DEFAULT 0,
  `e666` int(11) DEFAULT 0,
  `e777` int(11) DEFAULT 0,
  `e333bf` int(11) DEFAULT 0,
  `e333oh` int(11) DEFAULT 0,
  `e333fm` int(11) DEFAULT 0,
  `eclock` int(11) DEFAULT 0,
  `eminx` int(11) DEFAULT 0,
  `epyram` int(11) DEFAULT 0,
  `eskewb` int(11) DEFAULT 0,
  `esq1` int(11) DEFAULT 0,
  `e444bf` int(11) DEFAULT 0,
  `e555bf` int(11) DEFAULT 0,
  `e333mbf` int(11) DEFAULT 0,
  `schedule` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play`  (
  `pId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'n',
  PRIMARY KEY (`pId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for result_5
-- ----------------------------
DROP TABLE IF EXISTS `result_5`;
CREATE TABLE `result_5`  (
  `rId` int(11) NOT NULL AUTO_INCREMENT,
  `cId` int(11) DEFAULT NULL,
  `pId` int(11) DEFAULT NULL,
  `name` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `event` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rounds` int(11) DEFAULT NULL,
  `t1` double(10, 2) DEFAULT NULL,
  `t2` double(10, 2) DEFAULT NULL,
  `t3` double(10, 2) DEFAULT NULL,
  `t4` double(10, 2) DEFAULT NULL,
  `t5` double(10, 2) DEFAULT NULL,
  `single` double(10, 2) DEFAULT NULL,
  `avg` double(10, 2) DEFAULT NULL,
  PRIMARY KEY (`rId`) USING BTREE,
  INDEX `FK_Reference_1`(`cId`) USING BTREE,
  INDEX `pId`(`pId`) USING BTREE,
  CONSTRAINT `result_5_ibfk_1` FOREIGN KEY (`cId`) REFERENCES `competition` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `result_5_ibfk_2` FOREIGN KEY (`pId`) REFERENCES `play` (`pId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

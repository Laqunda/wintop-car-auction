/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.22.143
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 192.168.22.143:3306
 Source Schema         : ningmengcar

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/09/2018 14:40:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_data_import_record
-- ----------------------------
DROP TABLE IF EXISTS `car_data_import_record`;
CREATE TABLE `car_data_import_record`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_record` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_data_import_record
-- ----------------------------
INSERT INTO `car_data_import_record` VALUES (1, 14000);

SET FOREIGN_KEY_CHECKS = 1;

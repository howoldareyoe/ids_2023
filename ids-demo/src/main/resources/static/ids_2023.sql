/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3307
 Source Schema         : ids_2023

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 24/07/2023 09:57:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `car_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号',
  `car_brand` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车品牌',
  `car_color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车颜色',
  `car_user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车主邮箱',
  `created_time` datetime NOT NULL COMMENT '注册时间',
  `total_duration` decimal(10, 1) NULL DEFAULT NULL COMMENT '总时长',
  PRIMARY KEY (`car_number`) USING BTREE,
  UNIQUE INDEX `idx_car_number`(`car_number` ASC) USING BTREE,
  INDEX `chk_user`(`car_user_email` ASC) USING BTREE,
  CONSTRAINT `chk_user` FOREIGN KEY (`car_user_email`) REFERENCES `user` (`user_email`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('浙A.VR44R', 'buick', NULL, '1912172786@qq.com', '2023-07-19 11:30:45', 0.0);

-- ----------------------------
-- Table structure for car_intrusion
-- ----------------------------
DROP TABLE IF EXISTS `car_intrusion`;
CREATE TABLE `car_intrusion`  (
  `car_intrusion_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `car_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被入侵的车牌号',
  `intrusion_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入侵类型',
  `intrusion_time` datetime NOT NULL COMMENT '入侵时间',
  `intrusion_src` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入侵来源',
  PRIMARY KEY (`car_intrusion_id`) USING BTREE,
  INDEX `chk_car_number`(`car_number` ASC) USING BTREE,
  INDEX `chk_intrusion_type`(`intrusion_type` ASC) USING BTREE,
  CONSTRAINT `chk_car_number` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `chk_intrusion_type` FOREIGN KEY (`intrusion_type`) REFERENCES `intrusion` (`intrusion_type`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_intrusion
-- ----------------------------
INSERT INTO `car_intrusion` VALUES ('1', '浙A.VR44R', '1', '2023-07-04 10:56:33', '1');
INSERT INTO `car_intrusion` VALUES ('2', '浙A.VR44R', '1', '2023-06-21 11:40:11', '1');

-- ----------------------------
-- Table structure for intrusion
-- ----------------------------
DROP TABLE IF EXISTS `intrusion`;
CREATE TABLE `intrusion`  (
  `intrusion_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入侵类型',
  `intrusion_des` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入侵描述',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`intrusion_type`) USING BTREE,
  UNIQUE INDEX `idx_type`(`intrusion_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intrusion
-- ----------------------------
INSERT INTO `intrusion` VALUES ('1', '1', '2023-07-04 10:57:07');

-- ----------------------------
-- Table structure for log_record
-- ----------------------------
DROP TABLE IF EXISTS `log_record`;
CREATE TABLE `log_record`  (
  `log_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_record
-- ----------------------------
INSERT INTO `log_record` VALUES ('19021', '浙A.VR44R', '2023-07-22 15:16:12', '2023-07-22 15:24:07');
INSERT INTO `log_record` VALUES ('16329', '浙A.VR44R', '2023-07-22 15:25:35', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户邮箱',
  `user_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `auto_detection` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启自动检测，0为不开启1为开启',
  `warning` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启警报，0为不开启1为开启',
  `created_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_email`) USING BTREE,
  UNIQUE INDEX `idx_email`(`user_email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (NULL, '1912172786@qq.com', NULL, NULL, NULL, '2023-07-19 11:30:45');

SET FOREIGN_KEY_CHECKS = 1;

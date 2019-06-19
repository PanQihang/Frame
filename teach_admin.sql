/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : frame

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 18/06/2019 10:01:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teach_admin
-- ----------------------------
DROP TABLE IF EXISTS `teach_admin`;
CREATE TABLE `teach_admin`  (
  `id` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teach_admin
-- ----------------------------
INSERT INTO `teach_admin` VALUES (2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '数据结构课组', '32');
INSERT INTO `teach_admin` VALUES (12, 'liwei', 'e10adc3949ba59abbe56e057f20f883e', '李威', '30');
INSERT INTO `teach_admin` VALUES (13, 'mengjiana', 'e10adc3949ba59abbe56e057f20f883e', '孟佳娜', '31');
INSERT INTO `teach_admin` VALUES (14, 'liuyongkui', 'e10adc3949ba59abbe56e057f20f883e', '刘勇奎', '31');
INSERT INTO `teach_admin` VALUES (15, 'wanglingfen', 'e10adc3949ba59abbe56e057f20f883e', '王玲芬', '31');
INSERT INTO `teach_admin` VALUES (16, 'lixizuo', 'e10adc3949ba59abbe56e057f20f883e', '李锡祚', '31');

-- ----------------------------
-- Table structure for teach_back_auth
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_auth`;
CREATE TABLE `teach_back_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `auth_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `auth_url` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `auth_ico` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `auth_parent` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teach_back_auth
-- ----------------------------
INSERT INTO `teach_back_auth` VALUES (30, '1000', '系统管理', '#', 'fa fa-cogs', NULL);
INSERT INTO `teach_back_auth` VALUES (35, '10001', '系统用户管理', '/userMn/', 'fa fa-user', 30);
INSERT INTO `teach_back_auth` VALUES (36, '10002', '系统角色管理', '/roleMn/', 'fa fa-black-tie', 30);
INSERT INTO `teach_back_auth` VALUES (37, '10003', '系统权限管理', '/authMn/', 'fa fa-gavel', 30);

-- ----------------------------
-- Table structure for teach_back_role
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_role`;
CREATE TABLE `teach_back_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teach_back_role
-- ----------------------------
INSERT INTO `teach_back_role` VALUES (30, '系统管理员', '1001', '后台系统管理员角色');
INSERT INTO `teach_back_role` VALUES (31, '教师', '1002', '后台教师角色');
INSERT INTO `teach_back_role` VALUES (32, '超级管理员', '1003', '拥有全部权限');

-- ----------------------------
-- Table structure for teach_back_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_role_auth`;
CREATE TABLE `teach_back_role_auth`  (
  `roleId` int(32) NULL DEFAULT NULL COMMENT '角色ID',
  `authId` int(32) NULL DEFAULT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teach_back_role_auth
-- ----------------------------
INSERT INTO `teach_back_role_auth` VALUES (32, 30);
INSERT INTO `teach_back_role_auth` VALUES (32, 35);
INSERT INTO `teach_back_role_auth` VALUES (32, 36);
INSERT INTO `teach_back_role_auth` VALUES (32, 37);
INSERT INTO `teach_back_role_auth` VALUES (30, 30);
INSERT INTO `teach_back_role_auth` VALUES (30, 35);

SET FOREIGN_KEY_CHECKS = 1;

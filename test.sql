/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-07 22:51:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teach_admin
-- ----------------------------
DROP TABLE IF EXISTS `teach_admin`;
CREATE TABLE `teach_admin` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `password` char(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `college_id` int(11) unsigned NOT NULL DEFAULT '0',
  `class_id` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

-- ----------------------------
-- Records of teach_admin
-- ----------------------------
INSERT INTO `teach_admin` VALUES ('2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '32', '0', '0');
INSERT INTO `teach_admin` VALUES ('23', 'panqihang', '96e79218965eb72c92a549dd5a330112', 'panqihang', '30', '1', '0');

-- ----------------------------
-- Table structure for teach_back_auth
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_auth`;
CREATE TABLE `teach_back_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_name` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_url` varchar(32) COLLATE utf8_bin NOT NULL,
  `auth_ico` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `auth_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of teach_back_auth
-- ----------------------------
INSERT INTO `teach_back_auth` VALUES ('30', '1000', '系统管理', '#', 'fa fa-cogs', null);
INSERT INTO `teach_back_auth` VALUES ('35', '10001', '系统用户管理', '/userMn/', 'fa fa-user', '30');
INSERT INTO `teach_back_auth` VALUES ('36', '10002', '系统角色管理', '/roleMn/', 'fa fa-black-tie', '30');
INSERT INTO `teach_back_auth` VALUES ('37', '10003', '系统权限管理', '/authMn/', 'fa fa-gavel', '30');
INSERT INTO `teach_back_auth` VALUES ('38', '2000', '全国疫情统计数据', '#', '', null);
INSERT INTO `teach_back_auth` VALUES ('39', '2001', '疫情地图', '/epidemicSituationMn/', '', '38');
INSERT INTO `teach_back_auth` VALUES ('40', '3000', '班级管理', '#', '', null);
INSERT INTO `teach_back_auth` VALUES ('41', '30001', '班级列表', '/classesMn/', '', '40');
INSERT INTO `teach_back_auth` VALUES ('44', '4000', '学院管理', '#', '', null);
INSERT INTO `teach_back_auth` VALUES ('45', '40001', '学院列表', '/collegeMn/', '', '44');
INSERT INTO `teach_back_auth` VALUES ('46', '5000', '学生管理', '#', '', null);
INSERT INTO `teach_back_auth` VALUES ('47', '50001', '学生管理', '/studentMn/', '', '46');
INSERT INTO `teach_back_auth` VALUES ('49', '6000', '健康状况统计', '#', '', null);
INSERT INTO `teach_back_auth` VALUES ('50', '60001', '学生填写信息统计', '/submitMn/', '', '49');
INSERT INTO `teach_back_auth` VALUES ('51', '60002', '今日信息收集汇总', '/todayMn/', '', '49');

-- ----------------------------
-- Table structure for teach_back_role
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_role`;
CREATE TABLE `teach_back_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_code` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of teach_back_role
-- ----------------------------
INSERT INTO `teach_back_role` VALUES ('30', '学院管理员', '1001', '学院管理员角色');
INSERT INTO `teach_back_role` VALUES ('32', '超级管理员', '1003', '拥有全部权限');

-- ----------------------------
-- Table structure for teach_back_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `teach_back_role_auth`;
CREATE TABLE `teach_back_role_auth` (
  `roleId` int(32) DEFAULT NULL COMMENT '角色ID',
  `authId` int(32) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of teach_back_role_auth
-- ----------------------------
INSERT INTO `teach_back_role_auth` VALUES ('32', '30');
INSERT INTO `teach_back_role_auth` VALUES ('32', '35');
INSERT INTO `teach_back_role_auth` VALUES ('32', '36');
INSERT INTO `teach_back_role_auth` VALUES ('32', '37');
INSERT INTO `teach_back_role_auth` VALUES ('32', '38');
INSERT INTO `teach_back_role_auth` VALUES ('32', '39');
INSERT INTO `teach_back_role_auth` VALUES ('32', '40');
INSERT INTO `teach_back_role_auth` VALUES ('32', '41');
INSERT INTO `teach_back_role_auth` VALUES ('32', '44');
INSERT INTO `teach_back_role_auth` VALUES ('32', '45');
INSERT INTO `teach_back_role_auth` VALUES ('32', '46');
INSERT INTO `teach_back_role_auth` VALUES ('32', '47');
INSERT INTO `teach_back_role_auth` VALUES ('32', '49');
INSERT INTO `teach_back_role_auth` VALUES ('32', '50');
INSERT INTO `teach_back_role_auth` VALUES ('32', '51');
INSERT INTO `teach_back_role_auth` VALUES ('30', '38');
INSERT INTO `teach_back_role_auth` VALUES ('30', '39');
INSERT INTO `teach_back_role_auth` VALUES ('30', '40');
INSERT INTO `teach_back_role_auth` VALUES ('30', '41');
INSERT INTO `teach_back_role_auth` VALUES ('30', '46');
INSERT INTO `teach_back_role_auth` VALUES ('30', '47');
INSERT INTO `teach_back_role_auth` VALUES ('30', '49');
INSERT INTO `teach_back_role_auth` VALUES ('30', '50');
INSERT INTO `teach_back_role_auth` VALUES ('30', '51');

-- ----------------------------
-- Table structure for teach_class
-- ----------------------------
DROP TABLE IF EXISTS `teach_class`;
CREATE TABLE `teach_class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` int(11) unsigned NOT NULL COMMENT '学校/学院',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '班级名称',
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teach_class
-- ----------------------------
INSERT INTO `teach_class` VALUES ('2', '1', '163');
INSERT INTO `teach_class` VALUES ('3', '2', '163');
INSERT INTO `teach_class` VALUES ('5', '3', '111');
INSERT INTO `teach_class` VALUES ('6', '1', '165');

-- ----------------------------
-- Table structure for teach_college
-- ----------------------------
DROP TABLE IF EXISTS `teach_college`;
CREATE TABLE `teach_college` (
  `college_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`college_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teach_college
-- ----------------------------
INSERT INTO `teach_college` VALUES ('1', '软件工程');
INSERT INTO `teach_college` VALUES ('2', '网络工程');
INSERT INTO `teach_college` VALUES ('3', '计算机工程');

-- ----------------------------
-- Table structure for teach_form
-- ----------------------------
DROP TABLE IF EXISTS `teach_form`;
CREATE TABLE `teach_form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) unsigned NOT NULL,
  `class_id` int(11) NOT NULL,
  `college_id` int(11) NOT NULL,
  `is_headache` tinyint(4) NOT NULL COMMENT '是否头疼 1:是 0:否',
  `temperature` double(4,1) NOT NULL COMMENT '体温',
  `create_time` varchar(13) NOT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teach_form
-- ----------------------------
INSERT INTO `teach_form` VALUES ('1', '8', '2', '1', '0', '36.4', '1585576990225');
INSERT INTO `teach_form` VALUES ('2', '1', '5', '3', '0', '36.4', '1585577080013');
INSERT INTO `teach_form` VALUES ('3', '1', '5', '3', '0', '36.4', '1585577287780');
INSERT INTO `teach_form` VALUES ('4', '1', '5', '3', '0', '36.4', '1585577568719');
INSERT INTO `teach_form` VALUES ('5', '1', '5', '3', '0', '36.4', '1585577820753');
INSERT INTO `teach_form` VALUES ('6', '19', '5', '3', '0', '36.5', '1585921480307');
INSERT INTO `teach_form` VALUES ('7', '19', '5', '3', '0', '36.5', '1585921593715');
INSERT INTO `teach_form` VALUES ('8', '19', '5', '3', '0', '36.5', '1585921950866');
INSERT INTO `teach_form` VALUES ('9', '8', '2', '1', '1', '36.5', '1585927730473');
INSERT INTO `teach_form` VALUES ('10', '8', '2', '1', '1', '36.1', '1587523608295');

-- ----------------------------
-- Table structure for teach_student
-- ----------------------------
DROP TABLE IF EXISTS `teach_student`;
CREATE TABLE `teach_student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `college_id` int(11) unsigned NOT NULL,
  `class_id` int(11) unsigned NOT NULL,
  `account` int(20) unsigned NOT NULL,
  `form_date` varchar(10) NOT NULL DEFAULT '20200403',
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teach_student
-- ----------------------------
INSERT INTO `teach_student` VALUES ('1', '潘祈航', '3', '5', '2016082314', '2020040');
INSERT INTO `teach_student` VALUES ('3', '实打实', '2', '3', '564612', '2020040');
INSERT INTO `teach_student` VALUES ('4', '潘祈航', '2', '3', '201628459', '2020040');
INSERT INTO `teach_student` VALUES ('13', '实打实', '3', '5', '564612', '2020040');
INSERT INTO `teach_student` VALUES ('7', '实打实', '1', '2', '564612', '2020040');
INSERT INTO `teach_student` VALUES ('8', '潘祈航', '1', '2', '201628459', '20200422');
INSERT INTO `teach_student` VALUES ('14', '潘祈航', '3', '5', '201628459', '2020040');
INSERT INTO `teach_student` VALUES ('15', 'xiaohang', '1', '2', '546546', '2020040');
INSERT INTO `teach_student` VALUES ('17', '111111', '2', '3', '111111', '2020040');
INSERT INTO `teach_student` VALUES ('18', '111111', '1', '2', '111111', '2020040');
INSERT INTO `teach_student` VALUES ('19', '潘祈亮', '3', '5', '20200403', '2020040');
INSERT INTO `teach_student` VALUES ('20', '项扬淳', '1', '6', '2016082311', '20200403');
INSERT INTO `teach_student` VALUES ('21', '赵子龙', '1', '6', '2016082314', '20200403');

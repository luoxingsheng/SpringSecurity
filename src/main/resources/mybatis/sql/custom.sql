/*
Navicat MySQL Data Transfer

Source Server         : MYSQL2
Source Server Version : 80013
Source Host           : 94.191.1.40:3306
Source Database       : graduate

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-08 13:38:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `credit_score` int(10) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom
-- ----------------------------
INSERT INTO `custom` VALUES ('1', 'admin', '6d789d4353c72e4f625d21c6b7ac2982', 'lxs', '893309066@qq.com', '桂苑公寓2幢401', '17857096920', '201532120123.img', '100');
INSERT INTO `custom` VALUES ('2', 'lxs', 'db06e69a9720566b47677a380c37cab4', '111', '11111111@qq.com', '桃园公寓3幢222', '17857096921', '2015555555', '100');

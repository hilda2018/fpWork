/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50095
Source Host           : 121.43.111.181:3306
Source Database       : appmanager

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2016-05-06 16:08:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customsinfo
-- ----------------------------
DROP TABLE IF EXISTS `customsinfo`;
CREATE TABLE `customsinfo` (
  `customsinfoID` varchar(36) NOT NULL,
  `username` varchar(60) default NULL,
  `company` varchar(60) default NULL,
  `Email` varchar(60) default NULL,
  `tel` varchar(60) default NULL,
  `currentdate` timestamp NULL default NULL,
  `userid` varchar(36) default NULL,
  `initial` varchar(1) default NULL,
  PRIMARY KEY  (`customsinfoID`),
  UNIQUE KEY `username` USING BTREE (`username`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customsinfo
-- ----------------------------
INSERT INTO `customsinfo` VALUES ('56fb5fe323d73f731e83e2aa', 'a', '1', null, null, '2016-03-30 13:10:59', null, null);
INSERT INTO `customsinfo` VALUES ('111', 'yichong', '1', null, null, null, null, null);
INSERT INTO `customsinfo` VALUES ('222', '18654925288', '1', null, null, null, null, null);
INSERT INTO `customsinfo` VALUES ('57296b3723d7fdb0d3ee8634', null, '阿里巴巴', 'a@b.com', '12345678901', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b4a23d7fdb0d3ee8635', null, '阿里巴巴', 'a@b.com', '12345678902', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b5f23d7fdb0d3ee8636', null, '阿里巴巴', 'a@b.com', '12345678903', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b6423d7fdb0d3ee8637', null, '阿里巴巴', 'a@b.com', '12345678904', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b6723d7fdb0d3ee8638', null, '阿里巴巴', 'a@b.com', '12345678905', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b6b23d7fdb0d3ee8639', null, '阿里巴巴', 'a@b.com', '12345678906', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b6e23d7fdb0d3ee863a', null, '阿里巴巴', 'a@b.com', '12345678907', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b7223d7fdb0d3ee863b', null, '阿里巴巴', 'a@b.com', '12345678908', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b7723d7fdb0d3ee863c', null, '阿里巴巴', 'a@b.com', '12345678909', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b7d23d7fdb0d3ee863d', null, '阿里巴巴', 'a@b.com', '12345678910', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b8123d7fdb0d3ee863e', null, '阿里巴巴', 'a@b.com', '12345678911', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b8523d7fdb0d3ee863f', null, '阿里巴巴', 'a@b.com', '12345678912', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296b8923d7fdb0d3ee8640', null, '阿里巴巴', 'a@b.com', '12345678913', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296c2d23d7fdb0d3ee8641', '马云', '阿里巴巴', 'a@b.com', '12345678914', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('57296c5d23d7fdb0d3ee8642', '马云1', '阿里巴巴', 'a@b.com', '12345678915', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'A');
INSERT INTO `customsinfo` VALUES ('5729c43b23d7c06a41f73d09', 'GOD', '天堂', 'GOD@tiantang.com', '13111111111', null, '0d6c2198-4f71-41b0-8314-1ce9c20e909b', 'T');

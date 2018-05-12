/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50095
Source Host           : 121.43.111.181:3306
Source Database       : appmanager

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2016-05-06 16:09:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mqpicture
-- ----------------------------
DROP TABLE IF EXISTS `mqpicture`;
CREATE TABLE `mqpicture` (
  `mqpictureid` varchar(36) NOT NULL,
  `mquotationid` varchar(36) default NULL,
  `pic` varchar(60) default NULL,
  `currentdate` timestamp NULL default NULL,
  PRIMARY KEY  (`mqpictureid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mqpicture
-- ----------------------------

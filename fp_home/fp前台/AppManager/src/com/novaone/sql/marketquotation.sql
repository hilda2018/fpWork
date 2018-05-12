/*
Navicat MySQL Data Transfer

Source Server         : master
Source Server Version : 50095
Source Host           : 121.43.111.181:3306
Source Database       : appmanager

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2016-05-06 16:08:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for marketquotation
-- ----------------------------
DROP TABLE IF EXISTS `marketquotation`;
CREATE TABLE `marketquotation` (
  `mq_id` varchar(36) NOT NULL,
  `enteruserid` varchar(36) default NULL,
  `enterdate` date default NULL,
  `markettype` int(11) default NULL COMMENT '0:辉展市场;1:江南市场',
  `marketdate` date default NULL,
  `country` varchar(36) default NULL,
  `product` varchar(36) default NULL,
  `varieties` varchar(60) default NULL,
  `spec` varchar(60) default NULL,
  `supplier` varchar(60) default NULL,
  `vendor` varchar(60) default NULL,
  `brand` varchar(60) default NULL,
  `weight` varchar(60) default NULL,
  `transport` int(11) default NULL COMMENT '0:海运;1:空运;2:陆运',
  `packagetype` varchar(60) default NULL,
  `minprice` decimal(10,0) default NULL,
  `maxprice` decimal(10,0) default NULL,
  `quality` varchar(60) default NULL,
  `sailedescribe` varchar(60) default NULL,
  `platenum` int(11) default NULL,
  `istransmit` int(11) default NULL COMMENT '0:非转发;1:转发',
  `transmituser` varchar(36) default NULL,
  `currentdate` timestamp NULL default NULL,
  `modifydate` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `status` varchar(20) default NULL COMMENT '记录状态 :默认为1,转发没有确认为0',
  PRIMARY KEY  (`mq_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of marketquotation
-- ----------------------------
INSERT INTO `marketquotation` VALUES ('572c4f660cf29c4f97ee849e', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-06', '0', '2016-05-06', '阿根廷', '槟郎', '品种3', '规格测试', '销售商12', '供应商测试1', '品牌测试4', '123KG', '0', '包装测试1', null, null, '品质测试', null, '12', '0', null, '2016-05-06 16:01:42', null, '1');
INSERT INTO `marketquotation` VALUES ('7b98ff72-c8d2-4277-9df2-8ef4e36cd888', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-05', '0', '2016-05-05', '阿根廷', '槟郎', '品种1', '规格测试1', '销售商12', '供应商测试2', '品牌测试4', '123KG', '2', '包装测试2', '123', '1231', '品质测试4', '销售描述', '123', '1', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-05 10:22:05', '2016-05-05 14:47:25', '1');
INSERT INTO `marketquotation` VALUES ('41cc42ee-b4b2-4886-8ebe-b2c559c4231e', '', '2016-05-05', '0', '2016-05-05', '阿根廷', '槟郎', '品种1', '规格测试1', '销售商12', '供应商测试2', '品牌测试4', '123KG', '2', '包装测试2', '123', '1231', '品质测试4', '销售描述', '123', '1', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-05 10:22:05', '2016-05-05 14:47:25', '0');
INSERT INTO `marketquotation` VALUES ('572c4d330cf29c4f97ee849c', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-06', '0', '2016-05-06', null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', null, '2016-05-06 15:52:19', null, '1');
INSERT INTO `marketquotation` VALUES ('289452bf-c984-478b-87c8-00058cc2bf23', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-05', '0', '2016-05-05', '阿根廷', '槟郎', '品种1', '规格测试1', '销售商12', '供应商测试2', '品牌测试4', '123KG', '2', '包装测试2', '123', '1231', '品质测试4', '销售描述', '123', '1', '0d6c2198-4f71-41b0-8314-1ce9c20e909b', '2016-05-05 11:06:03', '2016-05-05 14:48:49', '1');

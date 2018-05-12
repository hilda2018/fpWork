

-- ----------------------------
-- Table structure for cominfo
-- ----------------------------
DROP TABLE IF EXISTS `cominfo`;
CREATE TABLE `cominfo` (
`cominfoid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`username`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`country`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`product`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`fieldtype`  int(11) NULL DEFAULT NULL COMMENT '0:国家;1:品名;2:品种;3:规格;4:供应商;5:销售商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数' ,
`fieldvalue`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`times`  int(11) NULL DEFAULT NULL ,
`currentdate`  timestamp NULL DEFAULT NULL ,
`lastdate`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`cominfoid`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;


-- ----------------------------
-- Table structure for customsinfo
-- ----------------------------
DROP TABLE IF EXISTS `customsinfo`;
CREATE TABLE `customsinfo` (
`customsinfoID`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`username`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`company`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tel`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`currentdate`  timestamp NULL DEFAULT NULL ,
`userid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`initial`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`customsinfoID`),
UNIQUE INDEX `username` USING BTREE (`username`),
UNIQUE INDEX `tel` USING BTREE (`tel`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for marketquotation
-- ----------------------------
DROP TABLE IF EXISTS `marketquotation`;
CREATE TABLE `marketquotation` (
`mq_id`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`enteruserid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`enterdate`  date NULL DEFAULT NULL ,
`markettype`  int(11) NULL DEFAULT NULL COMMENT '0:辉展市场;1:江南市场' ,
`marketdate`  date NULL DEFAULT NULL ,
`country`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`product`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`varieties`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`spec`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`supplier`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`vendor`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`brand`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`weight`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`transport`  int(11) NULL DEFAULT NULL COMMENT '0:海运;1:空运;2:陆运' ,
`packagetype`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`minprice`  decimal(10,0) NULL DEFAULT NULL ,
`maxprice`  decimal(10,0) NULL DEFAULT NULL ,
`quality`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sailedescribe`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`platenum`  int(11) NULL DEFAULT NULL ,
`istransmit`  int(11) NULL DEFAULT NULL COMMENT '0:非转发;1:转发' ,
`transmituser`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`currentdate`  timestamp NULL DEFAULT NULL ,
`modifydate`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`status`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录状态 :默认为1,转发没有确认为0' ,
PRIMARY KEY (`mq_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;


-- ----------------------------
-- Table structure for mqpicture
-- ----------------------------
DROP TABLE IF EXISTS `mqpicture`;
CREATE TABLE `mqpicture` (
`mqpictureid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`mquotationid`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pic`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`currentdate`  timestamp NULL DEFAULT NULL ,
PRIMARY KEY (`mqpictureid`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

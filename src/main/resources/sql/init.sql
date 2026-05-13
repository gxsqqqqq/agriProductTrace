-- ----------------------------
-- Block Trace 数据库初始化脚本
-- ----------------------------

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- 初始化默认管理员账号
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', 'admin@blocktrace.com', '15888888888', '0', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', NULL, '系统管理员');

-- ----------------------------
-- 溯源产品表
-- ----------------------------
DROP TABLE IF EXISTS `trace_product`;
CREATE TABLE `trace_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(100) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `category` varchar(50) DEFAULT NULL COMMENT '产品分类',
  `origin` varchar(200) DEFAULT NULL COMMENT '产地',
  `producer` varchar(100) DEFAULT NULL COMMENT '生产商',
  `tx_hash` varchar(128) DEFAULT NULL COMMENT '区块链交易哈希',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溯源产品表';

-- ----------------------------
-- 溯源记录表
-- ----------------------------
DROP TABLE IF EXISTS `trace_record`;
CREATE TABLE `trace_record` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `stage` varchar(50) NOT NULL COMMENT '溯源阶段（生产/加工/运输/销售等）',
  `operator` varchar(100) DEFAULT NULL COMMENT '操作人/机构',
  `location` varchar(200) DEFAULT NULL COMMENT '地点',
  `event_desc` text COMMENT '事件描述',
  `tx_hash` varchar(128) DEFAULT NULL COMMENT '区块链交易哈希',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`record_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溯源记录表';

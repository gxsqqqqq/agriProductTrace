-- ----------------------------
-- Block Trace 完整数据库初始化脚本
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 清理旧表
-- ----------------------------
DROP TABLE IF EXISTS `trace_activity_records`;
DROP TABLE IF EXISTS `trace_activity`;
DROP TABLE IF EXISTS `trace_alarm`;
DROP TABLE IF EXISTS `trace_alaramrecords`;
DROP TABLE IF EXISTS `trace_data_structure`;
DROP TABLE IF EXISTS `trace_evaluate`;
DROP TABLE IF EXISTS `trace_welfare`;
DROP TABLE IF EXISTS `trace_trans`;
DROP TABLE IF EXISTS `trace_batch`;
DROP TABLE IF EXISTS `trace_product_list`;
DROP TABLE IF EXISTS `trace_product`;
DROP TABLE IF EXISTS `trace_record`;
DROP TABLE IF EXISTS `sys_user`;

-- ----------------------------
-- 用户表
-- ----------------------------
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

-- 初始化默认管理员账号
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', 'admin@blocktrace.com', '13800138000', '0', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', NOW(), 'admin', NOW(), '', NULL, '系统管理员');

-- ----------------------------
-- 溯源产品表
-- ----------------------------
CREATE TABLE `trace_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(100) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `category` varchar(50) DEFAULT NULL COMMENT '产品分类',
  `origin` varchar(200) DEFAULT NULL COMMENT '产地',
  `producer` varchar(100) DEFAULT NULL COMMENT '生产商',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `description` text DEFAULT NULL COMMENT '描述',
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

-- 插入示例数据
INSERT INTO `trace_product` VALUES (1, '有机苹果', 'PRD001', '水果', '山东烟台', '绿源农场', 25.80, '优质红富士苹果，口感脆甜', NULL, NULL, '0', '0', 'admin', NOW(), '', NULL, '');
INSERT INTO `trace_product` VALUES (2, '绿色蔬菜', 'PRD002', '蔬菜', '江苏南京', '生态农庄', 18.50, '无公害绿色蔬菜', NULL, NULL, '0', '0', 'admin', NOW(), '', NULL, '');
INSERT INTO `trace_product` VALUES (3, '天然蜂蜜', 'PRD003', '食品', '云南丽江', '高原养蜂场', 88.00, '纯天然野生蜂蜜', NULL, NULL, '0', '0', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 溯源记录表
-- ----------------------------
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

-- ----------------------------
-- 产品列表视图表
-- ----------------------------
CREATE TABLE `trace_product_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `origin` varchar(200) DEFAULT NULL,
  `producer` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `trace_product_list` SELECT product_id, product_name, category, price, origin, producer, status, create_time FROM trace_product;

-- ----------------------------
-- 批次管理表
-- ----------------------------
CREATE TABLE `trace_batch` (
  `batch_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '批次ID',
  `batch_no` varchar(50) NOT NULL COMMENT '批次编号',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `quantity` int(11) DEFAULT 0 COMMENT '数量',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `status` varchar(20) DEFAULT '未上链' COMMENT '状态（未上链/已上链）',
  `tx_hash` varchar(128) DEFAULT NULL COMMENT '交易哈希',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_no` (`batch_no`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='批次管理表';

-- 插入示例批次数据
INSERT INTO `trace_batch` VALUES (1, 'BATCH20240115001', 1, '有机苹果', 500, '2024-01-15', '已上链', '0xabc123...', 12345, 'admin', NOW(), '', NULL, '');
INSERT INTO `trace_batch` VALUES (2, 'BATCH20240114001', 2, '绿色蔬菜', 300, '2024-01-14', '未上链', NULL, NULL, 'admin', NOW(), '', NULL, '');
INSERT INTO `trace_batch` VALUES (3, 'BATCH20240113001', 3, '天然蜂蜜', 100, '2024-01-13', '已上链', '0xdef456...', 12344, 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 溯源交易表
-- ----------------------------
CREATE TABLE `trace_trans` (
  `trans_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '交易ID',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '批次ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `trans_type` varchar(50) DEFAULT NULL COMMENT '交易类型',
  `from_addr` varchar(128) DEFAULT NULL COMMENT '来源地址',
  `to_addr` varchar(128) DEFAULT NULL COMMENT '目标地址',
  `tx_hash` varchar(128) DEFAULT NULL COMMENT '交易哈希',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `trans_time` datetime DEFAULT NULL COMMENT '交易时间',
  `status` varchar(20) DEFAULT '已完成' COMMENT '状态',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`trans_id`),
  KEY `idx_batch_id` (`batch_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溯源交易表';

-- ----------------------------
-- 评价表
-- ----------------------------
CREATE TABLE `trace_evaluate` (
  `evaluate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `content` text COMMENT '评价内容',
  `score` int(11) DEFAULT 5 COMMENT '评分(1-5)',
  `create_time` datetime DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`evaluate_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价表';

-- 插入示例评价数据
INSERT INTO `trace_evaluate` VALUES (1, 1, 1, '张三', '非常好吃，推荐购买！', 5, NOW());
INSERT INTO `trace_evaluate` VALUES (2, 1, 1, '李四', '质量不错，物流也快', 4, NOW());
INSERT INTO `trace_evaluate` VALUES (3, 2, 1, '王五', '蔬菜很新鲜', 5, NOW());

-- ----------------------------
-- 福利积分表
-- ----------------------------
CREATE TABLE `trace_welfare` (
  `welfare_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '福利ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `points` int(11) DEFAULT 0 COMMENT '积分余额',
  `total_earned` int(11) DEFAULT 0 COMMENT '累计获得',
  `total_used` int(11) DEFAULT 0 COMMENT '累计使用',
  `last_update` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`welfare_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='福利积分表';

-- ----------------------------
-- 告警记录表
-- ----------------------------
CREATE TABLE `trace_alarm` (
  `alarm_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '告警ID',
  `alarm_type` varchar(50) DEFAULT NULL COMMENT '告警类型',
  `alarm_level` varchar(20) DEFAULT '一般' COMMENT '告警级别（严重/高/中/低/一般）',
  `alarm_content` text COMMENT '告警内容',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `status` varchar(20) DEFAULT '未处理' COMMENT '处理状态',
  `handle_result` text DEFAULT NULL COMMENT '处理结果',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='告警记录表';

-- 插入示例告警数据
INSERT INTO `trace_alarm` VALUES (1, '温度异常', '高', '冷库温度超过阈值', '传感器A', '未处理', NULL, NULL, 'system', NOW());

-- ----------------------------
-- 数据结构配置表
-- ----------------------------
CREATE TABLE `trace_data_structure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `structure_name` varchar(100) NOT NULL COMMENT '结构名称',
  `structure_key` varchar(100) NOT NULL COMMENT '键名',
  `structure_value` text DEFAULT NULL COMMENT '值',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort_order` int(11) DEFAULT 0 COMMENT '排序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0启用 1停用）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据结构配置表';

-- ----------------------------
-- 活动记录表
-- ----------------------------
CREATE TABLE `trace_activity` (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_name` varchar(100) NOT NULL COMMENT '活动名称',
  `activity_type` varchar(50) DEFAULT NULL COMMENT '活动类型',
  `activity_desc` text COMMENT '活动描述',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `location` varchar(200) DEFAULT NULL COMMENT '地点',
  `participants` int(11) DEFAULT 0 COMMENT '参与人数',
  `status` varchar(20) DEFAULT '进行中' COMMENT '状态',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动记录表';

-- 活动参与记录
CREATE TABLE `trace_activity_records` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `join_time` datetime DEFAULT NULL COMMENT '参与时间',
  `status` varchar(20) DEFAULT '已报名' COMMENT '状态',
  PRIMARY KEY (`record_id`),
  KEY `idx_activity_id` (`activity_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动参与记录表';

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- 完成
-- ----------------------------
SELECT '✅ 数据库初始化完成！' AS message;
SELECT COUNT(*) AS table_count FROM information_schema.tables WHERE table_schema = 'block_trace';

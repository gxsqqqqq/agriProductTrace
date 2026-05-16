SET NAMES utf8mb4;

-- 1. trace_product_list
DROP TABLE IF EXISTS trace_product_list;
CREATE TABLE trace_product_list (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  check_status int(11) DEFAULT NULL,
  machining_status int(11) DEFAULT NULL,
  sale_status int(11) DEFAULT NULL,
  create_by varchar(64) DEFAULT '',
  create_time datetime DEFAULT NULL,
  update_by varchar(64) DEFAULT '',
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='农产品列表';
INSERT INTO trace_product_list (id, code, name, check_status, machining_status, sale_status, create_time) VALUES
(1, 'PRD001', 'Apple', 1, 1, 1, NOW()),
(2, 'PRD002', 'Vegetable', 1, 0, 0, NOW()),
(3, 'PRD003', 'Honey', 1, 1, 1, NOW());

-- 2. trace_welfare
DROP TABLE IF EXISTS trace_welfare;
CREATE TABLE trace_welfare (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  status bigint(20) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  num bigint(20) DEFAULT 0,
  remark varchar(500) DEFAULT NULL,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='福利表';
INSERT INTO trace_welfare (id, status, name, num, ext1) VALUES
(1, 1, 'Apple Coupon', 10, '100'),
(2, 1, 'Vegetable Coupon', 5, '50'),
(3, 1, 'Honey Coupon', 3, '200');

-- 3. trace_activity
DROP TABLE IF EXISTS trace_activity;
CREATE TABLE trace_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_name varchar(100) DEFAULT NULL,
  product_name varchar(100) DEFAULT NULL,
  product_url varchar(255) DEFAULT NULL,
  num bigint(20) DEFAULT 0,
  integral varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动表';
INSERT INTO trace_activity (id, activity_name, product_name, num, integral) VALUES
(1, 'Spring Sale', 'Apple', 10, '50'),
(2, 'Summer Promo', 'Vegetable', 5, '30'),
(3, 'Autumn Special', 'Honey', 3, '100');

-- 4. trace_evaluate
DROP TABLE IF EXISTS trace_evaluate;
CREATE TABLE trace_evaluate (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nick_name varchar(50) DEFAULT NULL,
  content text,
  block_hash varchar(255) DEFAULT NULL,
  block_number bigint(20) DEFAULT NULL,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价表';
INSERT INTO trace_evaluate (id, nick_name, content, ext1) VALUES
(1, 'user001', 'Great product!', '5'),
(2, 'user002', 'Very fresh!', '4'),
(3, 'user003', 'Love it!', '5');

-- 5. trace_alaramrecords
DROP TABLE IF EXISTS trace_alaramrecords;
CREATE TABLE trace_alaramrecords (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  product_id bigint(20) DEFAULT NULL,
  rule_id bigint(20) DEFAULT NULL,
  rule_detail text,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  ext3 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='告警记录';

-- 6. trace_trans
DROP TABLE IF EXISTS trace_trans;
CREATE TABLE trace_trans (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  from_user varchar(100) DEFAULT NULL,
  to_user varchar(100) DEFAULT NULL,
  amount bigint(20) DEFAULT NULL,
  block_number varchar(50) DEFAULT NULL,
  block_hash varchar(255) DEFAULT NULL,
  remark varchar(500) DEFAULT NULL,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易表';
INSERT INTO trace_trans (id, from_user, to_user, amount, block_hash, create_time) VALUES
(1, 'user001', 'user002', 10, '0xtx001', NOW()),
(2, 'user002', 'user003', 5, '0xtx002', NOW());

-- 7. trace_data_structure
DROP TABLE IF EXISTS trace_data_structure;
CREATE TABLE trace_data_structure (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  node varchar(50) DEFAULT NULL,
  data_json text,
  remark varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据结构';
INSERT INTO trace_data_structure (id, code, name, node) VALUES
(1, 'plant', 'Planting', 'produce'),
(2, 'pack', 'Packaging', 'process'),
(3, 'transport', 'Transport', 'trans');

-- 8. trace_activity_records
DROP TABLE IF EXISTS trace_activity_records;
CREATE TABLE trace_activity_records (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tel varchar(50) DEFAULT NULL,
  activity_id bigint(20) DEFAULT NULL,
  block_hash varchar(255) DEFAULT NULL,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动参与记录';

-- 9. Re-insert trace_products data (was deleted by test)
DELETE FROM trace_products;
INSERT INTO trace_products (id, code, product_id, name, data_json, node, structure_id, structure_name, create_by, create_time) VALUES 
(1, 'PRD001', 1, 'Apple', '[{"code":"name","value":"Apple","type":"1","name":"Name"}]', 'produce', 1, 'plant', 'admin', NOW()),
(2, 'PRD002', 2, 'Vegetable', '[{"code":"name","value":"Vegetable","type":"1","name":"Name"}]', 'produce', 1, 'plant', 'admin', NOW()),
(3, 'PRD003', 3, 'Honey', '[{"code":"name","value":"Honey","type":"1","name":"Name"}]', 'process', 2, 'pack', 'admin', NOW());

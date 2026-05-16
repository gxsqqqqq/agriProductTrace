SET NAMES utf8mb4;

DROP TABLE IF EXISTS trace_products;

CREATE TABLE trace_products (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  code varchar(50) DEFAULT NULL COMMENT '编码',
  product_id bigint(20) DEFAULT NULL COMMENT '产品ID',
  name varchar(100) DEFAULT NULL COMMENT '名称',
  data_json text COMMENT '数据JSON',
  node varchar(100) DEFAULT NULL COMMENT '节点',
  structure_id bigint(20) DEFAULT NULL COMMENT '环节ID',
  structure_name varchar(100) DEFAULT NULL COMMENT '环节名称',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='农产品表';

INSERT INTO trace_products (id, code, product_id, name, data_json, node, structure_id, structure_name, create_by, create_time) VALUES 
(1, 'PRD001', 1, '有机苹果', '[{"code":"name","value":"有机苹果","type":"1","name":"名称"}]', 'produce', 1, 'plant', 'admin', NOW()),
(2, 'PRD002', 2, '绿色蔬菜', '[{"code":"name","value":"绿色蔬菜","type":"1","name":"名称"}]', 'produce', 1, 'plant', 'admin', NOW()),
(3, 'PRD003', 3, '天然蜂蜜', '[{"code":"name","value":"天然蜂蜜","type":"1","name":"名称"}]', 'process', 2, 'pack', 'admin', NOW());

SET NAMES utf8mb4;

DROP TABLE IF EXISTS trace_batch;

CREATE TABLE trace_batch (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  batch_no varchar(50) DEFAULT NULL COMMENT '批次编号',
  product_id bigint(20) DEFAULT NULL COMMENT '产品ID',
  product_name varchar(100) DEFAULT NULL COMMENT '产品名称',
  block_hash varchar(255) DEFAULT NULL COMMENT '区块哈希',
  block_data text DEFAULT NULL COMMENT '区块数据',
  data_json text DEFAULT NULL COMMENT '数据JSON',
  ext1 varchar(255) DEFAULT NULL COMMENT '扩展字段1',
  ext2 varchar(255) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='批次管理表';

INSERT INTO trace_batch (id, batch_no, product_id, product_name, block_hash, data_json, ext1, ext2) VALUES 
(1, 'BATCH20240115001', 1, 'Apple', '0xabc123', '[{"status":"chained"}]', 'active', ''),
(2, 'BATCH20240114001', 2, 'Vegetable', NULL, '[{"status":"pending"}]', 'pending', ''),
(3, 'BATCH20240113001', 3, 'Honey', '0xdef456', '[{"status":"chained"}]', 'active', '');

SET NAMES utf8mb4;

DROP TABLE IF EXISTS trace_alarm;
CREATE TABLE trace_alarm (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  product_id bigint(20) DEFAULT NULL,
  rules text,
  remark varchar(500) DEFAULT NULL,
  ext1 varchar(255) DEFAULT NULL,
  ext2 varchar(255) DEFAULT NULL,
  ext3 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='告警规则表';

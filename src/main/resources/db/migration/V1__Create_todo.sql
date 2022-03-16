use `ssp_engine`;
CREATE TABLE IF NOT EXISTS todo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  task varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
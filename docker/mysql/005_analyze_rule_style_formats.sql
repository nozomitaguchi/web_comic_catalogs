use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE analyze_rule_style_formats (
  distributor_id INT NOT NULL,
  analyze_rule_type ENUM('publisher', 'comic') NOT NULL,
  analyze_rule_style_type ENUM('name') NOT NULL,
  format VARCHAR(1000) NOT NULL,
  FOREIGN KEY fk_distributor_id4(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, analyze_rule_type, analyze_rule_style_type)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO analyze_rule_style_formats (distributor_id, analyze_rule_type, analyze_rule_style_type, format) VALUES
(4, 'comic', 'name', '「(.*)」作品サムネイル');

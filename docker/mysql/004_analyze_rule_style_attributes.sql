use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE analyze_rule_style_attributes (
  distributor_id INT NOT NULL,
  analyze_rule_type ENUM('publisher', 'comic') NOT NULL,
  analyze_rule_style_type ENUM('name', 'url', 'image_url') NOT NULL,
  attribute VARCHAR(1000) NOT NULL,
  FOREIGN KEY fk_distributor_id3(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, analyze_rule_type, analyze_rule_style_type)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO analyze_rule_style_attributes (distributor_id, analyze_rule_type, analyze_rule_style_type, attribute) VALUES
(1, 'comic', 'url', 'href'),
(1, 'comic', 'image_url', 'data-src'),
(2, 'comic', 'url', 'href'),
(2, 'comic', 'image_url', 'src'),
(3, 'comic', 'url', 'href'),
(3, 'comic', 'image_url', 'src'),
(4, 'comic', 'name', 'alt'),
(4, 'comic', 'url', 'href'),
(4, 'comic', 'image_url', 'src'),
(5, 'comic', 'url', 'href'),
(5, 'comic', 'image_url', 'data-src'),
(6, 'publisher', 'name', 'alt'),
(6, 'publisher', 'url', 'href'),
(6, 'publisher', 'image_url', 'src'),
(6, 'comic', 'url', 'href'),
(6, 'comic', 'image_url', 'data-original');

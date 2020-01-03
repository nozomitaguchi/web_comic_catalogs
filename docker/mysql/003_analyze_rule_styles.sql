use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE analyze_rule_styles (
  distributor_id INT NOT NULL,
  analyze_rule_type ENUM('publisher', 'comic') NOT NULL,
  analyze_rule_style_type ENUM('name', 'url', 'image_url') NOT NULL,
  selector VARCHAR(1000) NOT NULL,
  generation INT NOT NULL,
  FOREIGN KEY fk_distributor_id2(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, analyze_rule_type, analyze_rule_style_type)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO analyze_rule_styles (distributor_id, analyze_rule_type, analyze_rule_style_type, selector, generation) VALUES
(1, 'comic', 'name', 'a .series-list-title', 0),
(1, 'comic', 'url', 'a', 0),
(1, 'comic', 'image_url', 'a div img', 0),
(2, 'comic', 'name', 'h2', 0),
(2, 'comic', 'url', 'a', 0),
(2, 'comic', 'image_url', 'a img', 0),
(3, 'comic', 'name', '.gn_cList_title', 0),
(3, 'comic', 'url', '.gn_link_cList', 0),
(3, 'comic', 'image_url', 'img', 0),
(4, 'comic', 'name', 'a img', 0),
(4, 'comic', 'url', 'a', 0),
(4, 'comic', 'image_url', 'a img', 0),
(5, 'comic', 'name', 'a h5', 0),
(5, 'comic', 'url', 'a', 0),
(5, 'comic', 'image_url', 'a img', 0),
(6, 'publisher', 'name', 'a img', 0),
(6, 'publisher', 'url', 'a', 0),
(6, 'publisher', 'image_url', 'a img', 0),
(6, 'comic', 'name', 'a h3 span', 0),
(6, 'comic', 'url', 'a', 0),
(6, 'comic', 'image_url', 'a div img', 0);

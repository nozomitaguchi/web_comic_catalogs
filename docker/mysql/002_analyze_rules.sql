use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE analyze_rules (
  distributor_id INT NOT NULL,
  analyze_rule_type ENUM('publisher', 'comic') NOT NULL,
  catalog_selector VARCHAR(1000) NOT NULL,
  FOREIGN KEY fk_distributor_id(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, analyze_rule_type)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO analyze_rules (distributor_id, analyze_rule_type, catalog_selector) VALUES
(1, 'comic', '.series-list li'),
(2, 'comic', '#delivery-contents .tabContent .current li'),
(3, 'comic', '#comicList li'),
(4, 'comic', '.title-all-list li'),
(5, 'comic', '.series-section-list li'),
(6, 'publisher', '#sideMagazineLabel li'),
(6, 'comic', '.tileList li');

use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE analyze_rule_style_urls (
  distributor_id INT NOT NULL,
  analyze_rule_type ENUM('publisher', 'comic') NOT NULL,
  analyze_rule_style_type ENUM('url', 'image_url') NOT NULL,
  url VARCHAR(1000) NOT NULL,
  FOREIGN KEY fk_distributor_id5(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, analyze_rule_type, analyze_rule_style_type)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO analyze_rule_style_urls (distributor_id, analyze_rule_type, analyze_rule_style_type, url) VALUES
(2, 'comic', 'url', 'https://web-ace.jp'),
(2, 'comic', 'image_url', 'https://web-ace.jp'),
(3, 'comic', 'url', 'https://www.ganganonline.com'),
(3, 'comic', 'image_url', 'https://www.ganganonline.com'),
(4, 'comic', 'url', 'https://urasunday.com'),
(6, 'publisher', 'url', 'https://comic-walker.com'),
(6, 'comic', 'url', 'https://comic-walker.com');

use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE distributors (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR (100) NOT NULL,
  distributor_type ENUM('coordinator', 'publisher') NOT NULL,
  url VARCHAR(2083) NOT NULL,
  image_url VARCHAR(2083) NOT NULL,
  content_path VARCHAR(2083) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO distributors (id, name, distributor_type, url, image_url, content_path) VALUES
(1, '少年ジャンプ+', 'publisher', 'https://shonenjumpplus.com', 'https://shonenjumpplus.com/images/jumpplus_white.png', 'series'),
(2, 'ヤングエースUP', 'publisher', 'https://web-ace.jp', 'https://web-ace.jp/img/pc/youngaceup-logo.png', 'youngaceup/contents'),
(3, 'ガンガンONLINE', 'publisher', 'https://www.ganganonline.com', 'https://www.ganganonline.com/common/images/common/gn_header_logo_new.png', 'contents'),
(4, '裏サンデー', 'publisher', 'https://urasunday.com', 'https://urasunday.com/assets/img/logo.png', 'serial_title'),
(5, 'コミックガルド', 'publisher', 'https://comic-gardo.com', 'https://comic-gardo.com/images/gardo_white_blue.svg', 'series'),
(6, 'Comic Walker', 'coordinator', 'https://comic-walker.com', 'https://static.comic-walker.com/pc/img/logo_comicwalker.svg', '');

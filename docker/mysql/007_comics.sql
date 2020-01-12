use web_comic;

SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;

CREATE TABLE comics (
  id INT NOT NULL UNIQUE AUTO_INCREMENT,
  distributor_id INT NOT NULL,
  name VARCHAR (100) NOT NULL,
  url VARCHAR(800) NOT NULL,
  image_url VARCHAR(2083) NOT NULL,
  FOREIGN KEY fk_distributor_id6(distributor_id) REFERENCES distributors(id),
  PRIMARY KEY(distributor_id, name, url)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO comics (id, distributor_id, name, url, image_url) VALUES
(1, 2, 'サマータイムレンダ', 'https://shonenjumpplus.com/episode/13932016480029133982', 'https://cdn-ak-scissors.shonenjumpplus.com/image/scale/e4d8c3c9bf35f363c85d929297e36ebaebc3c227/enlarge=0;height=216;no_unsharpmask=1;quality=90;version=1;width=216/https%3A%2F%2Fcdn-ak-img.shonenjumpplus.com%2Fpublic%2Fseries-thumbnail%2F13932016480029133981-349018a39741c0486f0e18f7f1ebbe79%3F1578111258');

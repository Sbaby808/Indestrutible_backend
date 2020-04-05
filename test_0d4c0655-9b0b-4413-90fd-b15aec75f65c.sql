DROP TABLE IF EXISTS `testTable`;

CREATE TABLE `testTable` (
`testColumn` varchar(255) NOT NULL ,
`name` int(255) NOT NULL ,
PRIMARY KEY (`testColumn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `testTable` WRITE;
INSERT INTO `testTable` values (aaa,123),(bbb,456);
UNLOCK TABLES;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
`name` varchar(255) NOT NULL ,
`age` int(11) NOT NULL ,
PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
UNLOCK TABLES;


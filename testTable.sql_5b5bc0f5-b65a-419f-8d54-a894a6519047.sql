DROP TABLE IF EXISTS `testTable`;

CREATE TABLE `testTable` (
`testColumn` varchar(255) NOT NULL ,
`name` int(255) NOT NULL ,
PRIMARY KEY (`testColumn`,`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `testTable` WRITE;
INSERT INTO `testTable` values ('aaa',123),('bbb',456),('ccc',123),('ddd',123),('eee',123),('fff',123),('ggg',123),('hhh',123),('iii',123),('jjj',123),('kkk',123),('lll',123),('mmm',123),('nnn',123),('ooo',123),('ppp',123),('qqq',123),('rrr',123),('sss',123),('ttt',123),('uuu',123);
UNLOCK TABLES;


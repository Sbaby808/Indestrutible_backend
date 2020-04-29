DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
`site` text NOT NULL ,
`name` varchar(32) NOT NULL ,
`telephone` varchar(11) NOT NULL ,
`aid` int(11) NOT NULL ,
`isdefault` varchar(1) NOT NULL ,
`uid` varchar(32) NOT NULL ,
PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `address` WRITE;
INSERT INTO `address` values (湖南省株洲市天元区泰山路街道88号湖南工业大学,'谭益民','12345654785',3,'0','06d75b05a73c48ce9ba78f3ad671d422'),(湖南省株洲市,'丁盛','12311231123',4,'1','06d75b05a73c48ce9ba78f3ad671d422'),(湖南省长沙市,'PXX','12311231123',9,'0','06d75b05a73c48ce9ba78f3ad671d422'),(湖南省长沙市,'QWEQ','12311231123',10,'0','06d75b05a73c48ce9ba78f3ad671d422'),(湖南省长沙市,'丁盛','12311231123',11,'0','06d75b05a73c48ce9ba78f3ad671d422');
UNLOCK TABLES;

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
`adminid` int(10) unsigned NOT NULL ,
`adminuser` varchar(32) NOT NULL ,
`adminname` varchar(32) ,
PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
INSERT INTO `admin` values (1588631,'123456','??');
UNLOCK TABLES;

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
`uid` varchar(32) NOT NULL ,
`cartid` varchar(32) NOT NULL ,
`total` double NOT NULL ,
PRIMARY KEY (`cartid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `cart` WRITE;
INSERT INTO `cart` values ('0','0',9684.0),('06d75b05a73c48ce9ba78f3ad671d422','06d75b05a73c48ce9ba78f3ad671d422',14280.0),('806a834089364071a3ddfd12daf446a6','806a834089364071a3ddfd12daf446a6',14280.0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `cartitem`;

CREATE TABLE `cartitem` (
`pid` varchar(32) NOT NULL ,
`count` int(11) NOT NULL ,
`cartid` varchar(32) NOT NULL ,
`subtotal` double NOT NULL ,
PRIMARY KEY (`pid`,`cartid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `cartitem` WRITE;
INSERT INTO `cartitem` values ('1',1,'0',1299.0),('10',1,'0',2599.0),('11',2,'0',4596.0),('11',2,'806a834089364071a3ddfd12daf446a6',4596.0),('8',1,'0',1190.0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
`cid` varchar(32) NOT NULL ,
`cname` varchar(20) ,
PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `category` WRITE;
INSERT INTO `category` values ('1','手机数码'),('172934bd636d485c98fd2d3d9cccd409','运动户外'),('2','电脑办公'),('3','家具家居'),('4','鞋靴箱包'),('5','图书音像'),('6','母婴孕婴'),('afdba41a139b4320a74904485bdb7719','汽车用品'),('b1a7c745cf39430381787a26bcc1fe04','abc');
UNLOCK TABLES;

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
`pid` varchar(32) NOT NULL ,
`time` datetime NOT NULL ,
`uid` varchar(32) NOT NULL ,
PRIMARY KEY (`pid`,`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `history` WRITE;
INSERT INTO `history` values ('',2019-03-30 02:31:53.0,'06d75b05a73c48ce9ba78f3ad671d422'),(''1'',2019-03-30 02:28:45.0,'06d75b05a73c48ce9ba78f3ad671d422'),('1',2019-03-16 23:46:19.0,''),('1',2019-03-17 06:19:54.0,'06d75b05a73c48ce9ba78f3ad671d422'),('10',2019-03-16 23:46:24.0,''),('10',2019-03-17 06:29:14.0,'06d75b05a73c48ce9ba78f3ad671d422'),('10',2019-03-30 04:44:24.0,'806a834089364071a3ddfd12daf446a6'),('11',2019-03-16 23:50:56.0,''),('11',2019-03-18 00:42:10.0,'06d75b05a73c48ce9ba78f3ad671d422'),('11',2019-03-30 04:58:04.0,'806a834089364071a3ddfd12daf446a6'),('12',2019-03-17 05:21:41.0,''),('12',2019-03-21 22:23:43.0,'06d75b05a73c48ce9ba78f3ad671d422'),('13',2019-03-30 03:40:14.0,''),('13',2019-03-30 04:00:58.0,'06d75b05a73c48ce9ba78f3ad671d422'),('14',2019-03-17 04:47:26.0,''),('16',2019-03-17 04:47:51.0,''),('16',2019-03-30 03:36:42.0,'06d75b05a73c48ce9ba78f3ad671d422'),('17',2019-03-30 17:51:22.0,''),('17',2019-03-30 02:53:07.0,'06d75b05a73c48ce9ba78f3ad671d422'),('18',2019-03-30 03:52:45.0,''),('18',2019-03-17 06:31:59.0,'06d75b05a73c48ce9ba78f3ad671d422'),('19',2019-03-17 21:55:28.0,''),('2',2019-03-30 03:40:20.0,''),('2',2019-03-21 22:02:40.0,'06d75b05a73c48ce9ba78f3ad671d422'),('20',2019-03-30 03:34:54.0,''),('31',2019-03-23 03:55:54.0,'06d75b05a73c48ce9ba78f3ad671d422'),('32',2019-03-16 23:46:36.0,''),('32',2019-03-20 03:36:38.0,'06d75b05a73c48ce9ba78f3ad671d422'),('33',2019-03-30 03:35:32.0,'06d75b05a73c48ce9ba78f3ad671d422'),('34',2019-03-23 03:57:23.0,'06d75b05a73c48ce9ba78f3ad671d422'),('37',2019-03-30 03:47:07.0,''),('37',2019-03-23 03:58:27.0,'06d75b05a73c48ce9ba78f3ad671d422'),('4',2019-03-18 01:25:04.0,''),('4',2019-03-30 04:16:54.0,'06d75b05a73c48ce9ba78f3ad671d422'),('7',2019-03-30 03:52:38.0,''),('8',2019-03-30 03:35:05.0,''),('8',2019-03-30 17:44:30.0,'06d75b05a73c48ce9ba78f3ad671d422');
UNLOCK TABLES;

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
`itemid` varchar(32) NOT NULL ,
`count` int(11) ,
`subtotal` double ,
`pid` varchar(32) ,
`oid` varchar(32) ,
PRIMARY KEY (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `orderitem` WRITE;
INSERT INTO `orderitem` values ('01f67593e51a483ca754a7ffd1c2a2d0',2,5198.0,'10','50b87330f1f2491b8fa15a1a8001fe81'),('286e955c780d4cf9a6f473f74f0349d3',2,5198.0,'10','68cc6f59a4c547b2959a703efc4db207'),('47425a561b604a62974d6712b81a257c',1,1299.0,'1','bd6dca63037046a38ca3f7b4fc69021d'),('4eed9aaeea074d71ba13e2714d24e6d9',1,4087.0,'16','8297fdda9f074d9ebc02b4f1dbe89bc9'),('6f48be5ed0294299800f68fe586e1f39',2,8398.0,'33','4eaec0be932b486a874877409902c0e6'),('a6112419e79341e6a3a1ab76cb08a383',5,20995.0,'33','34eab49fa4264226a87ec83a44fc62cd'),('b4ec155e97ec4713b47b93166651974d',2,5198.0,'10','56452f0a87d546579edd217286d8374c'),('c8ff2b22019e49aaa54e6efa21cb6a40',2,2598.0,'1','98fc8cf755684aaa8a70c0f14fa75b5a'),('e983adfc20f044248c486e44367cbd01',2,4596.0,'11','50b87330f1f2491b8fa15a1a8001fe81'),('fb35f2b8371f4f1ab394978ecf9e8e6e',3,7797.0,'10','8297fdda9f074d9ebc02b4f1dbe89bc9'),('fe870269a0454999af91c8a0401bf77c',2,2598.0,'1','4eaec0be932b486a874877409902c0e6');
UNLOCK TABLES;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
`oid` varchar(32) NOT NULL ,
`ordertime` datetime ,
`total` double ,
`state` int(11) ,
`address` varchar(30) ,
`name` varchar(20) ,
`telephone` varchar(20) ,
`uid` varchar(32) ,
PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `orders` WRITE;
INSERT INTO `orders` values ('34eab49fa4264226a87ec83a44fc62cd',2019-03-30 17:37:11.0,20995.0,0,'湖南省株洲市天元区泰山路街道88号湖南工业大学','谭益民','12345654785','06d75b05a73c48ce9ba78f3ad671d422'),('4eaec0be932b486a874877409902c0e6',2019-03-30 04:05:47.0,10996.0,0,'湖南省株洲市','丁盛','12311231123','06d75b05a73c48ce9ba78f3ad671d422'),('50b87330f1f2491b8fa15a1a8001fe81',2019-03-22 00:59:44.0,9794.0,4,'null','null','null','06d75b05a73c48ce9ba78f3ad671d422'),('56452f0a87d546579edd217286d8374c',2019-03-22 01:12:57.0,5198.0,4,'null','null','null','06d75b05a73c48ce9ba78f3ad671d422'),('68cc6f59a4c547b2959a703efc4db207',2019-03-30 03:09:21.0,5198.0,4,'湖南省株洲市','丁盛','12311231123','06d75b05a73c48ce9ba78f3ad671d422'),('8297fdda9f074d9ebc02b4f1dbe89bc9',2019-03-30 17:11:26.0,11884.0,4,'湖南省株洲市','丁盛','12311231123','06d75b05a73c48ce9ba78f3ad671d422'),('98fc8cf755684aaa8a70c0f14fa75b5a',2019-03-25 00:57:19.0,2598.0,4,'湖南省株洲市','丁盛','12311231123','06d75b05a73c48ce9ba78f3ad671d422'),('bd6dca63037046a38ca3f7b4fc69021d',2019-03-30 17:41:23.0,1299.0,4,'湖南省株洲市','丁盛','12311231123','06d75b05a73c48ce9ba78f3ad671d422');
UNLOCK TABLES;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
`pid` varchar(32) NOT NULL ,
`pname` varchar(50) ,
`market_price` double ,
`shop_price` double ,
`pimage` varchar(200) ,
`pdate` date ,
`is_hot` int(11) ,
`pdesc` varchar(255) ,
`pflag` int(11) ,
`cid` varchar(32) ,
`inventory` tinyint(2) NOT NULL ,
PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `product` WRITE;
INSERT INTO `product` values ('1','小米 4c 标准版',1399.0,1299.0,'products/1/c_0001.jpg',2015-11-02,0,'小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待',1,'1',1),('10','华为 Ascend Mate7',2699.0,2599.0,'products/1/c_0010.jpg',2015-11-02,1,'华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！',0,'1',5),('11','vivo X5Pro',2399.0,2298.0,'products/1/c_0014.jpg',2015-11-02,1,'移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术',0,'1',10),('12','努比亚（nubia）My 布拉格',1899.0,1799.0,'products/1/c_0013.jpg',2015-11-02,0,'努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！',0,'1',10),('13','华为 麦芒4',2599.0,2499.0,'products/1/c_0012.jpg',2015-11-02,1,'华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖',0,'1',10),('14','vivo X5M',1899.0,1799.0,'products/1/c_0011.jpg',2015-11-02,0,'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV',0,'1',10),('15','Apple iPhone 6 (A1586)',4399.0,4288.0,'products/1/c_0015.jpg',2015-11-02,1,'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！',0,'1',10),('16','华为 HUAWEI Mate S 臻享版',4200.0,4087.0,'products/1/c_0016.jpg',2015-11-03,0,'华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版',0,'1',9),('17','索尼(SONY) E6533 Z3+',4099.0,3999.0,'products/1/c_0017.jpg',2015-11-02,0,'索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G',0,'1',10),('18','HTC One M9+',3599.0,3499.0,'products/1/c_0018.jpg',2015-11-02,0,'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！',0,'1',10),('19','HTC Desire 826d 32G 臻珠白',1599.0,1469.0,'products/1/c_0020.jpg',2015-11-02,1,'后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！',0,'1',10),('2','中兴 AXON',2899.0,2699.0,'products/1/c_0002.jpg',2015-11-05,1,'中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待',0,'1',10),('20','小米 红米2A 增强版 白色',649.0,549.0,'products/1/c_0019.jpg',2015-11-02,0,'新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！',0,'1',10),('21','魅族 魅蓝note2 16GB 白色',1099.0,999.0,'products/1/c_0021.jpg',2015-11-02,0,'现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！',0,'1',10),('22','三星 Galaxy S5 (G9008W) 闪耀白',2099.0,1999.0,'products/1/c_0022.jpg',2015-11-02,1,'5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素',0,'1',10),('23','sonim XP7700 4G手机',1799.0,1699.0,'products/1/c_0023.jpg',2015-11-09,1,'三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗',0,'1',10),('24','努比亚（nubia）Z9精英版 金色',3988.0,3888.0,'products/1/c_0024.jpg',2015-11-02,1,'移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！',0,'1',10),('25','Apple iPhone 6 Plus (A1524) 16GB 金色',5188.0,4988.0,'products/1/c_0025.jpg',2015-11-02,0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1',10),('26','Apple iPhone 6s (A1700) 64G 玫瑰金色',6388.0,6088.0,'products/1/c_0026.jpg',2015-11-02,0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1',10),('27','三星 Galaxy Note5（N9200）32G版',5588.0,5388.0,'products/1/c_0027.jpg',2015-11-02,0,'旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！',0,'1',10),('28','三星 Galaxy S6 Edge+（G9280）32G版 铂光金',5999.0,5888.0,'products/1/c_0028.jpg',2015-11-02,0,'赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳',0,'1',10),('29','LG G4（H818）陶瓷白 国际版',3018.0,2978.0,'products/1/c_0029.jpg',2015-11-02,0,'李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！',0,'1',10),('3','华为荣耀6',1599.0,1499.0,'products/1/c_0003.jpg',2015-11-02,0,'荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机',0,'1',10),('30','微软(Microsoft) Lumia 640 LTE DS (RM-1113)',1099.0,999.0,'products/1/c_0030.jpg',2015-11-02,0,'微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！',0,'1',10),('31','宏碁（acer）ATC705-N50 台式电脑',2399.0,2299.0,'products/1/c_0031.jpg',2015-11-02,0,'爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！',0,'2',10),('32','Apple MacBook Air MJVE2CH/A 13.3英寸',6788.0,6688.0,'products/1/c_0032.jpg',2015-11-02,0,'宽屏笔记本电脑 128GB 闪存',0,'2',10),('33','联想（ThinkPad） 轻薄系列E450C(20EH0001CD)',4399.0,4199.0,'products/1/c_0033.jpg',2015-11-02,0,'联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)',0,'2',10),('34','联想（Lenovo）小新V3000经典版',4599.0,4499.0,'products/1/c_0034.jpg',2015-11-02,0,'14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！',0,'2',10),('35','华硕（ASUS）经典系列R557LI',3799.0,3699.0,'products/1/c_0035.jpg',2015-11-02,0,'15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）',0,'2',10),('36','华硕（ASUS）X450J',4599.0,4399.0,'products/1/c_0036.jpg',2015-11-02,0,'14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）',0,'2',10),('37','戴尔（DELL）灵越 飞匣3000系列',3399.0,3299.0,'products/1/c_0037.jpg',2015-11-03,0,' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑',0,'2',10),('38','惠普(HP)WASD 暗影精灵',5699.0,5499.0,'products/1/c_0038.jpg',2015-11-02,0,'15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)',0,'2',10),('39','Apple 配备 Retina 显示屏的 MacBook',11299.0,10288.0,'products/1/c_0039.jpg',2015-11-02,0,'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存',0,'2',10),('4','联想 P1',2199.0,1999.0,'products/1/c_0004.jpg',2015-11-02,0,'联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！',0,'1',10),('40','机械革命（MECHREVO）MR X6S-M',6799.0,6599.0,'products/1/c_0040.jpg',2015-11-02,0,'15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色',0,'2',10),('41','神舟（HASEE） 战神K660D-i7D2',5699.0,5499.0,'products/1/c_0041.jpg',2015-11-02,0,'15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色',0,'2',10),('42','微星（MSI）GE62 2QC-264XCN',6199.0,5999.0,'products/1/c_0042.jpg',2015-11-02,0,'15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色',0,'2',10),('43','雷神（ThundeRobot）G150S',5699.0,5499.0,'products/1/c_0043.jpg',2015-11-02,0,'15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金',0,'2',10),('44','惠普（HP）轻薄系列 HP',3199.0,3099.0,'products/1/c_0044.jpg',2015-11-02,0,'15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰',0,'2',10),('45','未来人类（Terrans Force）T5',10999.0,9899.0,'products/1/c_0045.jpg',2015-11-02,0,'15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑',0,'2',10),('46','戴尔（DELL）Vostro 3800-R6308 台式电脑',3299.0,3199.0,'products/1/c_0046.jpg',2015-11-02,0,'（i3-4170 4G 500G DVD 三年上门服务 Win7）黑',0,'2',10),('47','联想（Lenovo）H3050 台式电脑',5099.0,4899.0,'products/1/c_0047.jpg',2015-11-11,0,'（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸',0,'2',10),('48','Apple iPad mini 2 ME279CH/A',2088.0,1888.0,'products/1/c_0048.jpg',2015-11-02,0,'（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）',0,'2',10),('49','小米（MI）7.9英寸平板',1399.0,1299.0,'products/1/c_0049.jpg',2015-11-02,0,'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色',0,'2',10),('5','摩托罗拉 moto x（x+1）',1799.0,1699.0,'products/1/c_0005.jpg',2015-11-01,0,'摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！',0,'1',10),('50','Apple iPad Air 2 MGLW2CH/A',2399.0,2299.0,'products/1/c_0050.jpg',2015-11-12,0,'（9.7英寸 16G WLAN 机型 银色）',0,'2',10),('6','魅族 MX5 16GB 银黑色',1899.0,1799.0,'products/1/c_0006.jpg',2015-11-02,0,'魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！',0,'1',10),('7','三星 Galaxy On7',1499.0,1398.0,'products/1/c_0007.jpg',2015-11-14,0,'三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！',0,'1',10),('8','NUU NU5',1288.0,1190.0,'products/1/c_0008.jpg',2015-11-02,0,'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机',0,'1',10),('9','乐视（Letv）乐1pro（X800）',2399.0,2299.0,'products/1/c_0009.jpg',2015-11-02,0,'乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！',0,'1',10);
UNLOCK TABLES;

DROP TABLE IF EXISTS `refund`;

CREATE TABLE `refund` (
`fid` int(11) NOT NULL ,
`oid` varchar(32) NOT NULL ,
`uid` varchar(32) NOT NULL ,
`reason` text ,
`status` varchar(2) NOT NULL ,
`classify` varchar(2) NOT NULL ,
PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `refund` WRITE;
INSERT INTO `refund` values (2,'56452f0a87d546579edd217286d8374c','06d75b05a73c48ce9ba78f3ad671d422',我不想要了啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊,'1','5'),(3,'68cc6f59a4c547b2959a703efc4db207','06d75b05a73c48ce9ba78f3ad671d422',,'0','1'),(4,'98fc8cf755684aaa8a70c0f14fa75b5a','06d75b05a73c48ce9ba78f3ad671d422',1去问问无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无,'0','1'),(5,'50b87330f1f2491b8fa15a1a8001fe81','06d75b05a73c48ce9ba78f3ad671d422',1211111,'1','1'),(6,'8297fdda9f074d9ebc02b4f1dbe89bc9','06d75b05a73c48ce9ba78f3ad671d422',1111111111111111111111111111111111,'1','1'),(7,'bd6dca63037046a38ca3f7b4fc69021d','06d75b05a73c48ce9ba78f3ad671d422',erwerwrewewer,'0','2');
UNLOCK TABLES;

DROP TABLE IF EXISTS `remark`;

CREATE TABLE `remark` (
`oid` varchar(32) NOT NULL ,
`uid` varchar(32) NOT NULL ,
`content` text ,
`remarktime` datetime NOT NULL ,
`rank` varchar(1) ,
PRIMARY KEY (`oid`,`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `remark` WRITE;
INSERT INTO `remark` values ('50b87330f1f2491b8fa15a1a8001fe81','06d75b05a73c48ce9ba78f3ad671d422',真的好呀,2019-03-24 21:31:08.0,'4'),('56452f0a87d546579edd217286d8374c','06d75b05a73c48ce9ba78f3ad671d422',是真的好用啊！,2019-03-23 03:45:54.0,'4'),('68cc6f59a4c547b2959a703efc4db207','06d75b05a73c48ce9ba78f3ad671d422',哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或,2019-03-30 03:11:33.0,'5'),('8297fdda9f074d9ebc02b4f1dbe89bc9','06d75b05a73c48ce9ba78f3ad671d422',66666666666666666666666,2019-03-30 17:12:23.0,'5'),('98fc8cf755684aaa8a70c0f14fa75b5a','06d75b05a73c48ce9ba78f3ad671d422',,2019-03-30 03:14:39.0,'5'),('bd6dca63037046a38ca3f7b4fc69021d','06d75b05a73c48ce9ba78f3ad671d422',wesdssereeerer,2019-03-30 17:43:35.0,'3');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`uid` varchar(32) NOT NULL ,
`username` varchar(20) ,
`password` varchar(20) ,
`name` varchar(20) ,
`email` varchar(30) ,
`birthday` date ,
`sex` varchar(10) ,
`state` int(11) ,
`code` varchar(64) ,
PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
INSERT INTO `user` values ('06d75b05a73c48ce9ba78f3ad671d422','Sbaby','1586321','丁盛','sbaby808@qq.com',2019-03-02,'1',1,'afde9df0ca514dc9b44f2420adc6611e92ba4341ebee4bf5aeb99ac1b4bad22b'),('071a7c9727164cfba4f614dec38940f7','123123','123123','丁盛','sbaby801238@qq.com',2019-03-01,'1',0,'c5d26305eb394efa8cb4ce2b1f8267669ef77669c90641b2b19aabbbba244d12'),('0b866f0925bc4df686bdf8e15dcac8e2','1111','1111','丁盛','sbaby8011118@qq.com',2019-03-01,'1',0,'66776558b6dd4a2eacec5ba7849fcf3a5539ba2b8e2f4489a5fe1f9b3a978e69'),('667e090a627b45deb67d8994f4290246','1212','1212','丁盛','sbaby8121208@qq.com',2019-03-01,'1',0,'ddab3e466175456aa154c2bc680ad70b11a4fac72fb04567bca95ecf01bae775'),('806a834089364071a3ddfd12daf446a6','Sbaby123','123456','丁盛','corwin808@gmail.com',2019-03-01,'1',1,'50fc910c0cc348ada681b8fa24cb30a4512dbabf51544c67a584bbcae1dfa82f'),('a6a86bde99bb4c7690c711c8030eea32','ghj','123456','q','3285688780@qq.com',2019-03-02,'0',0,'d4694d427a4049d699cf410f418faa0e5d353aa8a68e487c802c7914e64dde5d');
UNLOCK TABLES;


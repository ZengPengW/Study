/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.27 : Database - stus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stus` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `stus`;

/*Table structure for table `stu` */

DROP TABLE IF EXISTS `stu`;

CREATE TABLE `stu` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `hobby` varchar(50) DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk;

/*Data for the table `stu` */

insert  into `stu`(`sid`,`sname`,`gender`,`phone`,`birthday`,`hobby`,`info`) values (1,'Admin','女','1008611','1989-10-17','篮球, 看书, 写字','我是个中国人1111'),(5,'达达','女','123456','1111-11-11','篮球','撒达啊啊发'),(8,'root','男','13441414','1998-08-08','游泳, 足球, 写字, ','dfg sd gsgs '),(9,'zadg','女','12321331','1998-08-08','篮球, 足球, 看书,','da ad f '),(12,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(13,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(15,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(16,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(17,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(18,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(19,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(20,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(21,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(22,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(23,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(25,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(26,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff'),(27,'dad ','男','312312','1998-08-08','游泳, 篮球','s afar ff');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

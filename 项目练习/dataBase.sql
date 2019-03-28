/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.27 : Database - zpp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zpp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zpp`;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(200) NOT NULL,
  `username` varchar(21) NOT NULL,
  `password` varchar(19) NOT NULL,
  `email` varchar(100) NOT NULL,
  `shopname` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sid` (`sid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`sid`,`username`,`password`,`email`,`shopname`) values (6,'7982518acb8c22c48e53d18d0562a2e2','dasd','1222222','11@qq.com','adsda'),(8,'c57f90a0332bdddd0daf1b890f9a68ce','ajksdhkh','123456','fad@11.com','ada'),(9,'24d0e13f0ca90f08198e35a9518c1e54','ada','123456','12@qq.com','ada'),(10,'343877621d8debd1f8dd2bf720905011','adaa','','',''),(18,'a36d9d8fbc0e095f9b8721bf3dcb2c40','adaaa','123456','zpzydsxzqf@qq.com','adad');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

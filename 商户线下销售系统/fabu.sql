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

/*Table structure for table `finance` */

DROP TABLE IF EXISTS `finance`;

CREATE TABLE `finance` (
  `uid` int(11) NOT NULL,
  `pay` varchar(100) DEFAULT NULL,
  `soldout` int(11) DEFAULT '0',
  `balance` double DEFAULT '0',
  `total` double DEFAULT '0',
  `version` int(11) DEFAULT '1',
  PRIMARY KEY (`uid`),
  CONSTRAINT `finance_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `money` */

DROP TABLE IF EXISTS `money`;

CREATE TABLE `money` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `account` varchar(200) NOT NULL,
  `payee` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `onse` */

DROP TABLE IF EXISTS `onse`;

CREATE TABLE `onse` (
  `uid` int(11) NOT NULL,
  `productName` varchar(200) NOT NULL,
  `price` double NOT NULL,
  `productCount` int(11) NOT NULL,
  `productImg` varchar(300) NOT NULL,
  `productMessage` varchar(300) DEFAULT '无',
  `productClass` varchar(20) DEFAULT NULL,
  `pid` int(11) NOT NULL,
  `version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `uid` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `shopMessage` varchar(1024) NOT NULL,
  `isteke` int(11) DEFAULT '0',
  `equipment` varchar(200) NOT NULL,
  `statu` int(11) NOT NULL DEFAULT '0',
  `money` double NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `uid` int(11) NOT NULL,
  `price` double NOT NULL,
  `productCount` int(11) NOT NULL,
  `productImg` varchar(300) NOT NULL DEFAULT 'ppimgsicondouzi.svg',
  `productMessage` varchar(300) DEFAULT NULL,
  `productClass` varchar(20) DEFAULT NULL,
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(200) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

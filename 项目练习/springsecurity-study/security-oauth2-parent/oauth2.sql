/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.15 : Database - oauth2
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth2`;

/*Table structure for table `clientdetails` */

DROP TABLE IF EXISTS `clientdetails`;

CREATE TABLE `clientdetails` (
  `appId` VARCHAR(128) NOT NULL,
  `resourceIds` VARCHAR(256) DEFAULT NULL,
  `appSecret` VARCHAR(256) DEFAULT NULL,
  `scope` VARCHAR(256) DEFAULT NULL,
  `grantTypes` VARCHAR(256) DEFAULT NULL,
  `redirectUrl` VARCHAR(256) DEFAULT NULL,
  `authorities` VARCHAR(256) DEFAULT NULL,
  `access_token_validity` INT(11) DEFAULT NULL,
  `refresh_token_validity` INT(11) DEFAULT NULL,
  `additionalInformation` VARCHAR(4096) DEFAULT NULL,
  `autoApproveScopes` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` VARCHAR(256) DEFAULT NULL,
  `token` BLOB,
  `authentication_id` VARCHAR(128) NOT NULL,
  `user_name` VARCHAR(256) DEFAULT NULL,
  `client_id` VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB,
  `refresh_token` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` VARCHAR(256) DEFAULT NULL,
  `clientId` VARCHAR(256) DEFAULT NULL,
  `scope` VARCHAR(256) DEFAULT NULL,
  `status` VARCHAR(10) DEFAULT NULL,
  `expiresAt` TIMESTAMP NULL DEFAULT NULL,
  `lastModifiedAt` TIMESTAMP NULL DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` VARCHAR(128) NOT NULL,
  `resource_ids` VARCHAR(256) DEFAULT NULL,
  `client_secret` VARCHAR(256) DEFAULT NULL,
  `scope` VARCHAR(256) DEFAULT NULL,
  `authorized_grant_types` VARCHAR(256) DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256) DEFAULT NULL,
  `authorities` VARCHAR(256) DEFAULT NULL,
  `access_token_validity` INT(11) DEFAULT NULL,
  `refresh_token_validity` INT(11) DEFAULT NULL,
  `additional_information` VARCHAR(4096) DEFAULT NULL,
  `autoapprove` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` VARCHAR(256) DEFAULT NULL,
  `token` BLOB,
  `authentication_id` VARCHAR(128) NOT NULL,
  `user_name` VARCHAR(256) DEFAULT NULL,
  `client_id` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` VARCHAR(256) DEFAULT NULL,
  `token` BLOB,
  `authentication` BLOB
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




USE `oauth2`;

/*Table structure for table `tb_permission` */

DROP TABLE IF EXISTS `tb_permission`;

CREATE TABLE `tb_permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父权限',
  `name` VARCHAR(64) NOT NULL COMMENT '权限名称',
  `enname` VARCHAR(64) NOT NULL COMMENT '权限英文名称',
  `url` VARCHAR(255) NOT NULL COMMENT '授权路径',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `tb_permission` */

INSERT  INTO `tb_permission`(`id`,`parent_id`,`name`,`enname`,`url`,`description`,`created`,`updated`) VALUES 
(37,0,'系统管理','System','/',NULL,'2019-04-04 23:22:54','2019-04-04 23:22:56'),
(38,37,'用户管理','SystemUser','/users/',NULL,'2019-04-04 23:25:31','2019-04-04 23:25:33'),
(39,38,'查看用户','SystemUserView','/users/view/**',NULL,'2019-04-04 15:30:30','2019-04-04 15:30:43'),
(40,38,'新增用户','SystemUserInsert','/users/insert/**',NULL,'2019-04-04 15:30:31','2019-04-04 15:30:44'),
(41,38,'编辑用户','SystemUserUpdate','/users/update/**',NULL,'2019-04-04 15:30:32','2019-04-04 15:30:45'),
(42,38,'删除用户','SystemUserDelete','/users/delete/**',NULL,'2019-04-04 15:30:48','2019-04-04 15:30:45'),
(44,37,'内容管理','SystemContent','/contents/',NULL,'2019-04-06 18:23:58','2019-04-06 18:24:00'),
(45,44,'查看内容','SystemContentView','/contents/view/**',NULL,'2019-04-06 23:49:39','2019-04-06 23:49:41'),
(46,44,'新增内容','SystemContentInsert','/contents/insert/**',NULL,'2019-04-06 23:51:00','2019-04-06 23:51:02'),
(47,44,'编辑内容','SystemContentUpdate','/contents/update/**',NULL,'2019-04-06 23:51:04','2019-04-06 23:51:06'),
(48,44,'删除内容','SystemContentDelete','/contents/delete/**',NULL,'2019-04-06 23:51:08','2019-04-06 23:51:10');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父角色',
  `name` VARCHAR(64) NOT NULL COMMENT '角色名称',
  `enname` VARCHAR(64) NOT NULL COMMENT '角色英文名称',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `tb_role` */

INSERT  INTO `tb_role`(`id`,`parent_id`,`name`,`enname`,`description`,`created`,`updated`) VALUES 
(37,0,'超级管理员','admin',NULL,'2019-04-04 23:22:03','2019-04-04 23:22:05');

/*Table structure for table `tb_role_permission` */

DROP TABLE IF EXISTS `tb_role_permission`;

CREATE TABLE `tb_role_permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT(20) NOT NULL COMMENT '角色 ID',
  `permission_id` BIGINT(20) NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `tb_role_permission` */

INSERT  INTO `tb_role_permission`(`id`,`role_id`,`permission_id`) VALUES 
(37,37,37),
(38,37,38),
(39,37,39),
(40,37,40),
(41,37,41),
(42,37,42),
(43,37,44),
(44,37,45),
(45,37,46),
(46,37,47),
(47,37,48);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '密码，加密存储',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '注册手机号',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '注册邮箱',
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `tb_user` */

INSERT  INTO `tb_user`(`id`,`username`,`password`,`phone`,`email`,`created`,`updated`) VALUES 
(37,'admin','$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi','15888888888','lee.lusifer@gmail.com','2019-04-04 23:21:27','2019-04-04 23:21:29');

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL COMMENT '用户 ID',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `tb_user_role` */

INSERT  INTO `tb_user_role`(`id`,`user_id`,`role_id`) VALUES 
(37,37,37);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;










/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.18-log : Database - author_book_spring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`author_book_spring` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `author_book_spring`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`user_id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`user_id`,`description`,`price`,`title`) values 
(61,46,'cssscsss',1000,'book3'),
(63,46,'ggfgfg',2000,'book6'),
(64,46,'ddgfhjg',1000,'book8'),
(65,46,'fsdfdf',1000,'book7'),
(66,46,'ffggtr',1000,'book9'),
(67,46,'vff',1000,'book10'),
(68,46,'dfsf',1000,'book11'),
(69,46,'dffr',1000,'book12');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(79),
(79);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL,
  `profile_pic` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`username`,`password`,`role`,`profile_pic`,`active`,`token`) values 
(46,'petros','petrosyan','admin','$2a$10$Pw9efeMfWCApKBwIBJj5p.tVBTtOxIUT4F7Cb.v1SC143gQq.HM7q','ADMIN','1595517998874_1592744459133avatar.jpg','',NULL),
(77,'ham','ham','halexsanyan4@gmail.com','$2a$10$xIuBDFhPsMgjuOXeXqM19uyC5MrvUGEWMjUw4qVkFCs4YVrVVfXtS','USER','8eed6ce2738649238767818ce0ee3ddf','','c47497d6-1112-439a-807a-61a22e030128');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

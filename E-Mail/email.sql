/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : email

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-13 14:45:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressee_id` varchar(20) NOT NULL,
  `addresser_id` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `filepath` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `readed` int(1) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `filename` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email
-- ----------------------------
INSERT INTO `email` VALUES ('6', 'xm@666.com', 'mm@666.com', 'miss', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 09:15:36', '1', 'aa', 'hello.doc');
INSERT INTO `email` VALUES ('8', 'mm@666.com', 'xm@666.com', 'hello', 'E:\\download\\doc_up\\2018-07-12\\bad50ec1-727d-4a04-b1f1-2fa301845bc9_hello.doc', '2018-07-12 17:18:00', '0', '你好，<div>&nbsp; &nbsp; &nbsp; how are you?<img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_36.gif\" border=\"0\" /></div><div><br /></div><div>Best,</div><div>xm</div><div><br /></div>', 'hello.doc');
INSERT INTO `email` VALUES ('14', 'xm@666.com', 'mm@666.com', 'hh', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 10:29:22', '1', 'aa', 'hello.doc');
INSERT INTO `email` VALUES ('15', 'nn@666.com', 'mm@666.com', 'kkkkk', 'E:\\download\\doc_up\\2018-07-12\\4d2ebc6c-5ef6-485a-931e-99782e3b0515_miss.doc', '2018-07-13 10:29:14', '1', 'aa', 'hello.doc');
INSERT INTO `email` VALUES ('16', 'mm@666.com', 'xm@666.com', 'hello111', 'E:\\download\\doc_up\\2018-07-13\\c2dec6f1-3e54-4df8-81b9-a5917e9f3a3f_hello111.doc', '2018-07-13 11:51:00', '0', '<h1 style=\"text-align:center;\"><img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_01.gif\" border=\"0\" />hello</h1>', 'hello.doc');
INSERT INTO `email` VALUES ('18', 'mm@666.com', 'hh@666.com', 'happy', 'E:\\download\\doc_up\\2018-07-13\\564b3eae-82a8-45ed-87b9-a4e4902bcc48_happy.doc', '2018-07-13 14:37:48', '1', '<div style=\"text-align:center;\">生日<img src=\"http://localhost:8080/springmvc/editor/plugins/emoticons/etc_13.gif\" border=\"0\" /></div>', 'hello.doc');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `userid` varchar(20) DEFAULT NULL,
  `friend_id` varchar(20) DEFAULT NULL,
  `friend_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('mm@666.com', 'xm@666.com', '小明');
INSERT INTO `friend` VALUES ('mm@666.com', 'hh@666.com', '哈哈');
INSERT INTO `friend` VALUES ('mm@666.com', 'nn@666.com', '娜娜');

-- ----------------------------
-- Table structure for save
-- ----------------------------
DROP TABLE IF EXISTS `save`;
CREATE TABLE `save` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressee_id` varchar(20) NOT NULL,
  `addresser_id` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `filepath` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `readed` int(1) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `filename` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of save
-- ----------------------------
INSERT INTO `save` VALUES ('13', 'mm@666.com', 'xm@666.com', 'miss', 'E:\\download\\doc_up\\2018-07-13\\a80fc220-e330-4914-ad3e-221c97e63231_miss.doc', '2018-07-13 08:18:00', '0', 'ccccccccccc', 'hello.doc');
INSERT INTO `save` VALUES ('14', 'mm@666.com', 'xm@666.com', '草稿箱', 'E:\\download\\doc_up\\2018-07-13\\a6a7ba57-a9dd-4ac2-a1ac-0423be4469fc_草稿箱.', '2018-07-13 13:03:00', '0', '<div style=\"text-align:center;\"><span style=\"font-size:24px;\">草稿箱</span></div>', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('hh@666.com', '123456', '哈哈');
INSERT INTO `user` VALUES ('mm@666.com', '123456', '徐梦');
INSERT INTO `user` VALUES ('nn@666.com', '123456', '娜娜');
INSERT INTO `user` VALUES ('xh@666.com', '123456', '小红');
INSERT INTO `user` VALUES ('xm@666.com', '123456', '小明');

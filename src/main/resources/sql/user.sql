/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : dugt

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 14/11/2021 17:02:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password CHAR(60) NOT NULL,
  email VARCHAR(100)DEFAULT NULL,
  nickname VARCHAR(50) NOT NULL,
  free_chat_times INT DEFAULT 10,
  role varchar(20) DEFAULT NULL,
  ban int DEFAULT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (username),
  UNIQUE KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (username,password,nickname,role,ban) VALUES ('admin', 'admin','管理员', 'admin', 0);
INSERT INTO `user` (username,password,nickname,role,ban) VALUES ('user1', 'user1','游客', 'temp', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

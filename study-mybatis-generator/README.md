# Getting Started

### Guides
```
/*
 Navicat Premium Data Transfer

 Source Server         : aliyun_docker
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 106.15.177.166:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 28/03/2019 11:29:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `registerTime` time(0) NULL DEFAULT NULL COMMENT '注册时间',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```

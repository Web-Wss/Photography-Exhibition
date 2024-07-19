/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : hc

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 19/07/2024 13:07:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `classification_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `classification_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`classification_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (1, '旗袍');
INSERT INTO `classification` VALUES (2, '汉服');
INSERT INTO `classification` VALUES (3, '街拍');
INSERT INTO `classification` VALUES (4, '小清新');
INSERT INTO `classification` VALUES (5, '私房');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `avatar_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '介绍',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'http://ywesc-img.webwss.cn/HC/c312fde4-8ac9-4fe1-af38-611c58203ec3.png', '18888888888', '123456', '我是XXX，简介');

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `work_id` bigint NOT NULL AUTO_INCREMENT COMMENT '作品id',
  `classification_id` int NOT NULL COMMENT ' 分类id',
  `work_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ' 作品名称',
  `work_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作品头图',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (6, 1, '旗袍', 'http://ywesc-img.webwss.cn/HC/a8b97a3a-830a-41fe-9b60-ffd1b485bfd6.webp', '2024-07-19 10:41:57');
INSERT INTO `work` VALUES (7, 2, '汉服', 'http://ywesc-img.webwss.cn/HC/233f381e-36c6-465b-9ecf-cebc3c41a10f.webp', '2024-07-19 10:42:44');
INSERT INTO `work` VALUES (8, 3, '街拍', 'http://ywesc-img.webwss.cn/HC/dcdc105c-9034-42c3-a49c-bbcfd03c1fd0.jpg', '2024-07-19 10:43:34');
INSERT INTO `work` VALUES (9, 4, '小清新', 'http://ywesc-img.webwss.cn/HC/8bfb71c1-a2b5-45f3-a556-db9507cb507e.webp', '2024-07-19 10:44:10');
INSERT INTO `work` VALUES (10, 5, '私房', 'http://ywesc-img.webwss.cn/HC/706689fb-c3a0-43e1-935c-7c007ef07213.jpg', '2024-07-19 10:45:20');

-- ----------------------------
-- Table structure for work_img
-- ----------------------------
DROP TABLE IF EXISTS `work_img`;
CREATE TABLE `work_img`  (
  `work_img_id` bigint NOT NULL AUTO_INCREMENT COMMENT '作品图片id',
  `work_id` bigint NOT NULL COMMENT '作品id',
  `work_img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作品图片地址',
  PRIMARY KEY (`work_img_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_img
-- ----------------------------
INSERT INTO `work_img` VALUES (10, 6, 'http://ywesc-img.webwss.cn/HC/86e0ef26-313a-4eef-9a2c-05b77b1b3f6c.webp');
INSERT INTO `work_img` VALUES (11, 6, 'http://ywesc-img.webwss.cn/HC/f2f3042d-9176-4573-acec-1711e6b58827.webp');
INSERT INTO `work_img` VALUES (12, 6, 'http://ywesc-img.webwss.cn/HC/06f2aaf7-e132-4dfd-9f51-509f76bd0c9a.webp');
INSERT INTO `work_img` VALUES (13, 6, 'http://ywesc-img.webwss.cn/HC/78437a81-3b2d-4d0f-b3d5-ca953a94741d.webp');
INSERT INTO `work_img` VALUES (14, 7, 'http://ywesc-img.webwss.cn/HC/8df94e20-d802-41a5-bcec-30226f10b7a5.jpg');
INSERT INTO `work_img` VALUES (15, 7, 'http://ywesc-img.webwss.cn/HC/6f9d8964-8e85-461b-8ec9-5fd8076e5412.jpg');
INSERT INTO `work_img` VALUES (16, 8, 'http://ywesc-img.webwss.cn/HC/bdaf39c8-7e07-4cd2-8a35-d8683a710e9e.jpg');
INSERT INTO `work_img` VALUES (17, 8, 'http://ywesc-img.webwss.cn/HC/0742db94-6423-47cb-a67f-83067d16e0c1.jpg');
INSERT INTO `work_img` VALUES (18, 8, 'http://ywesc-img.webwss.cn/HC/15d326ce-ed79-490d-af23-69b10cd8b55e.jpg');
INSERT INTO `work_img` VALUES (19, 9, 'http://ywesc-img.webwss.cn/HC/60ebdf5f-4fd0-486e-9fb9-45b7321dec67.webp');
INSERT INTO `work_img` VALUES (20, 9, 'http://ywesc-img.webwss.cn/HC/7bdf28a0-90ae-4bc7-85c9-ce774676baad.webp');
INSERT INTO `work_img` VALUES (21, 10, 'http://ywesc-img.webwss.cn/HC/e38b53a1-6128-4ba1-bd68-ecb5a4f61184.jpg');
INSERT INTO `work_img` VALUES (22, 10, 'http://ywesc-img.webwss.cn/HC/69ca0817-185e-49ac-b7b5-523316066ec4.webp');
INSERT INTO `work_img` VALUES (23, 10, 'http://ywesc-img.webwss.cn/HC/bd276c9b-207b-4480-8cad-bb2abd0b8ef5.webp');

SET FOREIGN_KEY_CHECKS = 1;

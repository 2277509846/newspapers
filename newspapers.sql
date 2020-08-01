/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : newspapers

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 01/08/2020 13:59:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `consignee` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '归属的用户名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '张一', 'xx省xx市xx县2', '12345678915', 'user1');
INSERT INTO `address` VALUES (2, '张二', 'xx省xx市xx县', '12345678910', 'user1');
INSERT INTO `address` VALUES (3, '张二', 'xx省xx市xx县', '12345678910', 'user2');
INSERT INTO `address` VALUES (5, '张三', 'xxxxxx', '12345678918', 'user1');
INSERT INTO `address` VALUES (6, '李四', 'xxxxxx村', '12345678912', 'user2');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'admin', '张三', 'xx省xx市xx县', 113.10, '12345678911');
INSERT INTO `admin` VALUES ('admin2', 'admin2', '王五', 'xx省xx市xx县', 5.40, '12345678913');

-- ----------------------------
-- Table structure for newspaper
-- ----------------------------
DROP TABLE IF EXISTS `newspaper`;
CREATE TABLE `newspaper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报刊id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `category` enum('月刊','半月刊','周刊','日刊') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类，“月刊”，“半月刊”，“周刊”，“日刊”',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `admin_username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '归属的管理员用户名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `update_time` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新的时间，周刊：1~7，月刊：1~30，半月刊：1~30+1~30',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`, `name`) USING BTREE,
  INDEX `admin_username`(`admin_username`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `newspaper_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `admin` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of newspaper
-- ----------------------------
INSERT INTO `newspaper` VALUES (1, '人民日报', '日刊', 5.00, 'admin', '报纸于1948年6月15日在河北省平山县里庄创刊。时由《晋察冀日报》和晋冀鲁豫《人民日报》合并而成，为华北中央局机关报，同时担负党中央机关报职能。毛泽东同志亲笔为人民日报题写报名。1949年8月1日，中共中央决定人民日报为中国共产党中央委员会机关报，并沿用1948年6月15日的期号。1992年，人民日报被联合国教科文组织评为世界十大报纸之一。', '0', 'http://127.0.0.1:8080/uploads/627827767.jpg', b'0');
INSERT INTO `newspaper` VALUES (2, '我们爱科学', '月刊', 2.50, 'admin', '创建于2020-7-30', '1', 'http://127.0.0.1:8080/uploads/-1953828661.jpg', b'0');
INSERT INTO `newspaper` VALUES (4, '博物', '月刊', 12.00, 'admin', '《中国国家地理》青年版', '1', 'http://127.0.0.1:8080/uploads/690767.jpg', b'0');
INSERT INTO `newspaper` VALUES (5, '博物', '月刊', 15.00, 'admin', '《中国国家地理》青年版', '1', 'http://127.0.0.1:8080/uploads/690767.jpg', b'1');
INSERT INTO `newspaper` VALUES (6, '环球少年地理', '月刊', 10.00, 'admin', '内容覆盖自然、动物、地质、科技、人文历史、流行文化等领域', '3', 'http://127.0.0.1:8080/uploads/-167268659.jpg', b'0');
INSERT INTO `newspaper` VALUES (8, '东方娃娃', '月刊', 12.00, 'admin', '每月引进一本世界经典图画书', '1', 'http://127.0.0.1:8080/uploads/621463613.jpg', b'0');
INSERT INTO `newspaper` VALUES (9, '孤独星球', '月刊', 25.00, 'admin', '孤独的星球', '1', 'http://127.0.0.1:8080/uploads/726102988.jpg', b'0');
INSERT INTO `newspaper` VALUES (10, '清华管理评论', '周刊', 50.00, 'admin', '清华管理评论', '2', 'http://127.0.0.1:8080/uploads/-1921097436.jpg', b'0');
INSERT INTO `newspaper` VALUES (11, '时尚', '日刊', 20.00, 'admin', '时尚', '0', 'http://127.0.0.1:8080/uploads/832740.jpg', b'0');
INSERT INTO `newspaper` VALUES (12, '博物', '月刊', 12.00, 'admin', '博物', '1', 'http://127.0.0.1:8080/uploads/34507.PNG', b'1');
INSERT INTO `newspaper` VALUES (13, '博物', '月刊', 3.00, 'admin', '博物', '1', 'http://127.0.0.1:8080/uploads/690767.jpg', b'1');
INSERT INTO `newspaper` VALUES (14, '华夏地理', '周刊', 25.00, 'admin', '华夏地理', '2', 'http://127.0.0.1:8080/uploads/657954327.jpg', b'0');
INSERT INTO `newspaper` VALUES (15, '中国国家地理', '月刊', 30.00, 'admin', '中国国家地理', '15', 'http://127.0.0.1:8080/uploads/1502301023.png', b'0');
INSERT INTO `newspaper` VALUES (16, '医食参考', '月刊', 8.00, 'admin', '医食参考', '1', 'http://127.0.0.1:8080/uploads/673063045.jpg', b'0');
INSERT INTO `newspaper` VALUES (17, '家庭医生', '半月刊', 6.00, 'admin', '家庭医生', '1+15', 'http://127.0.0.1:8080/uploads/723415355.jpg', b'0');
INSERT INTO `newspaper` VALUES (18, '意林作文素材', '周刊', 7.00, 'admin', '意林作文素材', '3', 'http://127.0.0.1:8080/uploads/2031191427.jpg', b'0');
INSERT INTO `newspaper` VALUES (19, '创新作文', '月刊', 7.50, 'admin', '创新作文', '10', 'http://127.0.0.1:8080/uploads/651849568.jpg', b'0');
INSERT INTO `newspaper` VALUES (20, '财新周刊', '半月刊', 19.50, 'admin', '财新周刊', '1+10', 'http://127.0.0.1:8080/uploads/1102056432.jpg', b'0');
INSERT INTO `newspaper` VALUES (21, '南方周末', '周刊', 5.00, 'admin', '南方周末', '1', 'http://127.0.0.1:8080/uploads/661312645.jpg', b'0');
INSERT INTO `newspaper` VALUES (22, '法治周末', '周刊', 4.00, 'admin', '法治周末', '1', 'http://127.0.0.1:8080/uploads/857452745.jpg', b'0');
INSERT INTO `newspaper` VALUES (23, '小作家报', '月刊', 18.00, 'admin', '小作家报', '1', 'http://127.0.0.1:8080/uploads/722361244.gif', b'0');
INSERT INTO `newspaper` VALUES (24, '读者', '半月刊', 8.00, 'admin2', '读者', '1+15', 'http://127.0.0.1:8080/uploads/1143658.jpg', b'0');

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record`  (
  `time` datetime(0) NOT NULL COMMENT '充值时间',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '充值的用户名',
  `money` decimal(10, 2) NOT NULL COMMENT '充值金额',
  PRIMARY KEY (`time`, `username`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `recharge_record_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
INSERT INTO `recharge_record` VALUES ('2020-07-24 14:22:05', 'user2', 30.00);

-- ----------------------------
-- Table structure for submit
-- ----------------------------
DROP TABLE IF EXISTS `submit`;
CREATE TABLE `submit`  (
  `newspaper_id` int(11) NOT NULL COMMENT '订阅的报刊id',
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订阅的用户名',
  `submit_time` datetime(0) NOT NULL COMMENT '订阅时间',
  `fee` decimal(10, 2) NOT NULL COMMENT '费用',
  `address_id` int(11) NOT NULL COMMENT '收货地址id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间',
  `status` bit(1) NOT NULL COMMENT '状态，0：未办理，1：已办理',
  `count` int(11) NOT NULL COMMENT '订阅数',
  PRIMARY KEY (`newspaper_id`, `username`, `submit_time`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `address_id`(`address_id`) USING BTREE,
  CONSTRAINT `submit_ibfk_1` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `submit_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `submit_ibfk_3` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submit
-- ----------------------------
INSERT INTO `submit` VALUES (1, 'user1', '2020-07-24 13:25:22', 5.40, 1, '2020-07-23', '2020-07-25', b'1', 1);
INSERT INTO `submit` VALUES (1, 'user1', '2020-07-24 13:31:22', 5.40, 1, '2020-07-26', '2020-07-28', b'1', 1);
INSERT INTO `submit` VALUES (2, 'user2', '2020-08-01 13:31:58', 22.50, 3, '2020-08-01', '2020-10-24', b'1', 3);
INSERT INTO `submit` VALUES (18, 'user1', '2020-08-01 13:27:16', 42.00, 1, '2020-08-04', '2020-08-20', b'1', 2);
INSERT INTO `submit` VALUES (24, 'user2', '2020-07-24 13:25:22', 5.40, 1, '2020-07-23', '2020-07-25', b'1', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('user1', '123456', '张一', 'xx省xx市xx县', 414.80, '12345678910');
INSERT INTO `user` VALUES ('user2', '123456', '李四', 'xx省xx市xx县', 296.70, '12345678912');

-- ----------------------------
-- Triggers structure for table recharge_record
-- ----------------------------
DROP TRIGGER IF EXISTS `tri2`;
delimiter ;;
CREATE TRIGGER `tri2` AFTER INSERT ON `recharge_record` FOR EACH ROW begin
	if new.money > 0 then 
		update user set balance = balance + new.money where username = new.username;
	end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table submit
-- ----------------------------
DROP TRIGGER IF EXISTS `tri1`;
delimiter ;;
CREATE TRIGGER `tri1` AFTER UPDATE ON `submit` FOR EACH ROW # 管理员办理后完成转账操作
begin
	if new.status = 1 and old.status = 0 then
		update admin set balance = balance + new.fee where username = (select admin_username from newspaper where id = new.newspaper_id);
		update user set balance = balance - new.fee where username = new.username;
	end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

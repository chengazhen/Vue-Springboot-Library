/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : springboot-vue

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 18/04/2022 17:29:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `isbn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `create_time` date NULL DEFAULT NULL COMMENT '出版时间',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '0：未归还 1：已归还',
  `borrownum` int(0) NOT NULL COMMENT '此书被借阅次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (9, '12341541321', '十万个为什么', 15.00, '苏格与', '宁波大学出版社', '2014-12-16', '1', 7);
INSERT INTO `book` VALUES (10, '2312315132131', '五万个为什么', NULL, NULL, NULL, NULL, '1', 3);
INSERT INTO `book` VALUES (11, '25213121232', '一万个为什么', NULL, NULL, NULL, NULL, '1', 5);
INSERT INTO `book` VALUES (12, '3213123123', '操作系统', NULL, NULL, NULL, NULL, '0', 8);
INSERT INTO `book` VALUES (13, '345621212321', '伊索寓言', NULL, NULL, NULL, NULL, '0', 9);
INSERT INTO `book` VALUES (15, '54112312321', '格林童话', NULL, NULL, NULL, NULL, '1', 1);

-- ----------------------------
-- Table structure for bookwithuser
-- ----------------------------
DROP TABLE IF EXISTS `bookwithuser`;
CREATE TABLE `bookwithuser`  (
  `id` bigint(0) NOT NULL COMMENT '读者id',
  `isbn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书编号',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '读者姓名',
  `lendtime` datetime(0) NULL DEFAULT NULL COMMENT '借阅时间',
  `deadtime` datetime(0) NULL DEFAULT NULL COMMENT '应归还时间',
  `prolong` int(0) NULL DEFAULT NULL COMMENT '续借次数',
  PRIMARY KEY (`book_name`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookwithuser
-- ----------------------------
INSERT INTO `bookwithuser` VALUES (14, '345621212321', '伊索寓言', '123456', '2021-12-22 17:30:48', '2022-02-20 17:30:48', 0);
INSERT INTO `bookwithuser` VALUES (14, '3213123123', '操作系统', '123456', '2021-10-12 17:30:42', '2021-12-14 17:30:42', 1);

-- ----------------------------
-- Table structure for lend_record
-- ----------------------------
DROP TABLE IF EXISTS `lend_record`;
CREATE TABLE `lend_record`  (
  `reader_id` bigint(0) NOT NULL COMMENT '读者id',
  `isbn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书编号',
  `bookname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名',
  `lend_time` datetime(0) NULL DEFAULT NULL COMMENT '借书日期',
  `return_time` datetime(0) NULL DEFAULT NULL COMMENT '还书日期',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '0：未归还 1：已归还',
  `borrownum` int(0) NOT NULL COMMENT '此书被借阅次数'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend_record
-- ----------------------------
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 10:57:02', '2021-12-18 11:03:54', '1', 2);
INSERT INTO `lend_record` VALUES (13, '465132123123', '狂人日记', '2021-12-18 10:59:21', '2021-12-18 11:22:51', '1', 2);
INSERT INTO `lend_record` VALUES (13, '54156461231', '十万个为什么', '2021-12-18 10:59:21', '2021-12-18 11:04:38', '1', 4);
INSERT INTO `lend_record` VALUES (13, '54156461231', '十万个为什么', '2021-12-18 11:04:40', '2021-12-18 11:05:22', '1', 5);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:04:51', '2021-12-18 11:05:24', '1', 3);
INSERT INTO `lend_record` VALUES (13, '54156461231', '十万个为什么', '2021-12-18 11:05:27', '2021-12-18 11:10:19', '1', 6);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:05:29', '2021-12-18 11:06:09', '1', 4);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:11', '2021-12-18 11:06:38', '1', 5);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:42', '2021-12-18 11:06:52', '1', 6);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:53', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:54', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:54', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:55', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:56', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:56', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:56', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (13, '92392321222', '算法笔记', '2021-12-18 11:06:56', '2021-12-18 11:10:20', '1', 7);
INSERT INTO `lend_record` VALUES (14, '12341541321', '十万个为什么', '2021-12-18 16:27:35', '2021-12-21 20:18:58', '1', 1);
INSERT INTO `lend_record` VALUES (14, '2312315132131', '五万个为什么', '2021-12-18 16:27:36', '2021-12-21 20:18:59', '1', 1);
INSERT INTO `lend_record` VALUES (14, '25213121232', '一万个为什么', '2021-12-18 16:27:38', '2021-12-18 16:29:22', '1', 1);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-18 16:27:40', '2021-12-18 16:29:15', '1', 1);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-18 16:29:24', '2021-12-21 20:19:01', '1', 2);
INSERT INTO `lend_record` VALUES (14, '25213121232', '一万个为什么', '2021-12-19 11:37:54', '2021-12-21 20:19:00', '1', 2);
INSERT INTO `lend_record` VALUES (14, '345621212321', '伊索寓言', '2021-12-19 11:37:58', '2021-12-19 11:38:42', '1', 1);
INSERT INTO `lend_record` VALUES (15, '345621212321', '伊索寓言', '2021-12-19 13:13:10', '2021-12-19 13:13:26', '1', 2);
INSERT INTO `lend_record` VALUES (14, '12341541321', '十万个为什么', '2021-12-21 20:19:09', '2021-12-22 15:45:35', '1', 2);
INSERT INTO `lend_record` VALUES (14, '25213121232', '一万个为什么', '2021-12-21 20:19:11', '2021-12-22 15:45:32', '1', 3);
INSERT INTO `lend_record` VALUES (14, '2312315132131', '五万个为什么', '2021-12-21 20:20:00', '2021-12-22 15:45:34', '1', 2);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-21 20:20:01', '2021-12-22 15:45:27', '1', 3);
INSERT INTO `lend_record` VALUES (14, '345621212321', '伊索寓言', '2021-12-21 20:20:02', '2021-12-21 20:20:11', '1', 3);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-22 15:45:37', '2021-12-22 15:45:39', '1', 4);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-22 15:46:15', '2021-12-22 15:51:05', '1', 5);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-22 15:51:12', '2021-12-22 15:51:15', '1', 6);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-22 15:51:16', '2021-12-22 16:48:09', '1', 7);
INSERT INTO `lend_record` VALUES (14, '25213121232', '一万个为什么', '2021-12-22 16:48:10', '2021-12-22 16:48:13', '1', 4);
INSERT INTO `lend_record` VALUES (14, '345621212321', '伊索寓言', '2021-12-22 16:48:19', '2021-12-22 17:30:46', '1', 4);
INSERT INTO `lend_record` VALUES (14, '3213123123', '操作系统', '2021-12-22 17:30:42', NULL, '0', 8);
INSERT INTO `lend_record` VALUES (14, '345621212321', '伊索寓言', '2021-12-22 17:30:48', NULL, '0', 9);
INSERT INTO `lend_record` VALUES (16, '12341541321', '十万个为什么', '2021-12-23 14:55:34', '2022-04-18 16:36:54', '1', 3);
INSERT INTO `lend_record` VALUES (16, '2312315132131', '五万个为什么', '2021-12-23 14:59:03', '2022-04-18 16:36:55', '1', 3);
INSERT INTO `lend_record` VALUES (16, '25213121232', '一万个为什么', '2021-12-23 14:59:05', '2022-04-18 16:36:56', '1', 5);
INSERT INTO `lend_record` VALUES (17, '54112312321', '格林童话', '2022-01-18 20:02:01', '2022-01-18 20:02:51', '1', 1);
INSERT INTO `lend_record` VALUES (16, '12341541321', '十万个为什么', '2022-04-18 16:37:01', '2022-04-18 16:37:28', '1', 4);
INSERT INTO `lend_record` VALUES (16, '12341541321', '十万个为什么', '2022-04-18 16:37:32', '2022-04-18 16:43:30', '1', 5);
INSERT INTO `lend_record` VALUES (16, '12341541321', '十万个为什么', '2022-04-18 16:43:39', '2022-04-18 16:43:43', '1', 6);
INSERT INTO `lend_record` VALUES (16, '12341541321', '十万个为什么', '2022-04-18 16:44:14', '2022-04-18 16:44:18', '1', 7);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `role` int(0) NOT NULL COMMENT '角色、1：管理员 2：普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (5, '账户', '12345', '苏梦宇', '18321299982', '男', '宁波大学甬江公寓2号楼428', 1);
INSERT INTO `user` VALUES (13, '2656454', '123456', '裘斯与', '12313282823', '男', '宁波大学甬江公寓1号楼321室', 2);
INSERT INTO `user` VALUES (14, '123456', '123456', '王翀', '15321255432', '男', '宁波大学北区学生二村222室', 2);
INSERT INTO `user` VALUES (15, '542212', '12345', '邱思宇', NULL, NULL, NULL, 2);
INSERT INTO `user` VALUES (16, '12345', '12345', '苏梦宇', '13213232989', '男', '宁波大学', 2);
INSERT INTO `user` VALUES (17, '苏格瑜', 'sugeyu', NULL, NULL, NULL, NULL, 2);

SET FOREIGN_KEY_CHECKS = 1;


-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  url               varchar(200)    default '#'                comment '请求地址',
  target            varchar(20)     default ''                 comment '打开方式（menuItem页签 menuBlank新窗口）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  is_refresh        char(1)         default 1                  comment '是否刷新（0刷新 1不刷新）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------

-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id           bigint(20)      not null auto_increment    comment '角色ID',
  role_name         varchar(30)     not null                   comment '角色名称',
  role_key          varchar(100)    not null                   comment '角色权限字符串',
  role_sort         int(4)          not null                   comment '显示顺序',
  data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  status            char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

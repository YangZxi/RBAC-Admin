/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : Plain-admin

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/11/2020 14:53:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ban
-- ----------------------------
DROP TABLE IF EXISTS `ban`;
CREATE TABLE `ban`  (
  `user_id` int NOT NULL,
  `role_id` int NULL DEFAULT NULL,
  `menu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `ban_role_id_fk`(`role_id`) USING BTREE,
  INDEX `ban_menu_id_fk`(`menu_id`) USING BTREE,
  CONSTRAINT `ban_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ban_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ban_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ban
-- ----------------------------
INSERT INTO `ban` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志标题',
  `error` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '日志内容',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `time` int NULL DEFAULT NULL,
  `ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `log_user_Id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `log_user_Id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 506 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '菜单类型',
  `parent_menu` int NULL DEFAULT 0 COMMENT '父级菜单，如果为0则表示为顶级菜单',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `order` tinyint NULL DEFAULT NULL COMMENT '排序',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `component` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称（文件名）',
  `permission` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_unique`(`permission`) USING BTREE,
  INDEX `parent_menu_fk`(`parent_menu`) USING BTREE,
  CONSTRAINT `parent_menu_fk` FOREIGN KEY (`parent_menu`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', 1, 0, NULL, 1, '/system', '', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (2, '用户管理', 2, 1, NULL, 1, '/user', 'User', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (3, '角色管理', 2, 1, NULL, 2, '/role', 'Role', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (4, '菜单管理', 2, 1, NULL, 3, '/menu', 'Menu', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (5, '帖子管理', 2, 1, NULL, 4, '/post', 'Post', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (6, '评论管理', 2, 1, NULL, 5, '/comment', 'Comment', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (7, '定时任务', 2, 1, NULL, 6, '/task', 'Task', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (8, '系统监控', 1, 0, NULL, 3, '/monitor', '', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (9, '操作日志', 2, 8, NULL, 3, '/operateLog', 'OperateLog', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (10, '异常日志', 2, 8, NULL, 3, '/exceptionLog', 'ExceptionLog', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (11, '主机状态', 1, 8, NULL, 3, '/systemInfo', 'SystemInfo', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (12, '系统工具', 1, 0, NULL, 3, '/tools', '', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (13, 'SMTP管理', 2, 12, NULL, 3, '/smtp', 'SMTP', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (14, '用户查询', 3, 2, NULL, 99, NULL, NULL, 'user:query', 1, NULL, NULL);
INSERT INTO `menu` VALUES (15, '用户添加', 3, 2, NULL, 98, NULL, NULL, 'user:add', 1, NULL, NULL);
INSERT INTO `menu` VALUES (16, '用户修改', 3, 2, NULL, 100, NULL, NULL, 'user:modify', 1, NULL, NULL);
INSERT INTO `menu` VALUES (17, '用户删除', 3, 2, NULL, 100, NULL, NULL, 'user:delete', 1, NULL, NULL);
INSERT INTO `menu` VALUES (18, 'SQL监控', 2, 8, NULL, 4, '/druid', 'Druid', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (19, '角色查询', 3, 3, NULL, 3, NULL, NULL, 'role:query', 1, NULL, NULL);
INSERT INTO `menu` VALUES (21, '角色添加', 3, 3, NULL, 1, NULL, NULL, 'role:add', 1, NULL, NULL);
INSERT INTO `menu` VALUES (35, '角色修改', 3, 3, NULL, 3, NULL, NULL, 'role:modify', 1, NULL, NULL);
INSERT INTO `menu` VALUES (36, '角色删除', 3, 3, NULL, 3, NULL, NULL, 'role:delete', 1, NULL, NULL);
INSERT INTO `menu` VALUES (37, '操作日志查询', 3, 9, NULL, 100, NULL, NULL, 'log:query', 1, NULL, NULL);
INSERT INTO `menu` VALUES (38, '操作日志删除', 3, 9, NULL, 100, NULL, NULL, 'log:delete', 1, NULL, NULL);
INSERT INTO `menu` VALUES (40, '测试菜单', 2, 8, NULL, 3, '/test', 'Test', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (41, '测试目录', 1, 0, NULL, 3, '/test', NULL, NULL, 0, NULL, NULL);
INSERT INTO `menu` VALUES (42, '根目录', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `prop_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置名',
  `prop_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置值',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置类型',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `prop_key_unique`(`prop_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of properties
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `name_zh` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色中文名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_admin', '管理员', '-', 1, '2020-10-30 04:07:18', '2020-10-30 16:19:41');
INSERT INTO `role` VALUES (2, 'ROLE_manager', '经理', '-', 1, '2020-10-30 19:58:10', '2020-10-30 05:23:29');
INSERT INTO `role` VALUES (3, 'employee', '员工', '企业员工', 0, '2020-10-30 15:55:25', NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id',
  INDEX `role_fk`(`role_id`) USING BTREE,
  INDEX `menu_fk`(`menu_id`) USING BTREE,
  CONSTRAINT `menu_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (1, 9);
INSERT INTO `role_menu` VALUES (1, 10);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 13);
INSERT INTO `role_menu` VALUES (1, 14);
INSERT INTO `role_menu` VALUES (1, 15);
INSERT INTO `role_menu` VALUES (1, 16);
INSERT INTO `role_menu` VALUES (1, 17);
INSERT INTO `role_menu` VALUES (1, 18);
INSERT INTO `role_menu` VALUES (1, 37);
INSERT INTO `role_menu` VALUES (1, 38);
INSERT INTO `role_menu` VALUES (3, 13);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `cron` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '全限定类名',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `user_id` int NULL DEFAULT NULL COMMENT '创建用户的id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `task_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '心跳监控', '0/10 * * * * ?', '监控系统状态', '', NULL, 1, '2', '2020-10-30 15:47:24', '2020-10-30 11:27:34');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称或姓名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` tinyint NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态',
  `uuid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '不是木易杨', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', '111@qq.com', '男', 18, 'https://q1.qlogo.cn/g?b=qq&nk=1282381264&s=640', 1, '', '2020-10-30 15:04:33', '2020-10-30 16:29:03');

-- ----------------------------
-- Table structure for user_login_track
-- ----------------------------
DROP TABLE IF EXISTS `user_login_track`;
CREATE TABLE `user_login_track`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `track_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `track_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_login_track
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_fk`(`user_id`) USING BTREE,
  INDEX `role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;

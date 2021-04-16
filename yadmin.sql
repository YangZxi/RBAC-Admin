/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : yadmin-back

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 16/04/2021 23:35:08
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
) ENGINE = InnoDB AUTO_INCREMENT = 876 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `type` tinyint NULL DEFAULT 1 COMMENT '菜单类型',
  `parent_menu_id` int NULL DEFAULT 0 COMMENT '父级菜单，如果为0则表示为顶级菜单',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `order` tinyint NULL DEFAULT NULL COMMENT '排序',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `component` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称（文件名）',
  `permission` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_unique`(`permission`) USING BTREE,
  INDEX `parent_menu_id_fk`(`parent_menu_id`) USING BTREE,
  CONSTRAINT `self_parent_menu` FOREIGN KEY (`parent_menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '根目录', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-06-15 09:18:15', '2020-06-15 09:18:19');
INSERT INTO `menu` VALUES (2, '系统管理', 1, 1, 'el-icon-s-operation', 1, '/system', '', NULL, 1, '2020-06-21 05:33:29', '2020-06-15 09:17:15');
INSERT INTO `menu` VALUES (3, '用户管理', 2, 2, 'el-icon-user-solid', 1, '/user', 'User', NULL, 1, '2020-06-22 05:20:22', '2020-06-13 04:04:00');
INSERT INTO `menu` VALUES (4, '角色管理', 2, 2, 'fa fa-user-plus', 2, '/role', 'Role', NULL, 1, '2020-06-13 16:04:56', '2020-06-13 16:04:48');
INSERT INTO `menu` VALUES (5, '菜单管理', 2, 2, 'fa fa-list', 3, '/menu', 'Menu', NULL, 1, '2020-06-13 16:09:03', '2020-06-13 16:09:03');
INSERT INTO `menu` VALUES (6, '帖子管理', 2, 2, 'fa fa-archive', 4, '/post', 'Post', NULL, 2, '2020-06-13 16:12:54', '2020-06-14 18:45:22');
INSERT INTO `menu` VALUES (7, '评论管理', 2, 2, 'fa fa-comment', 5, '/comment', 'Comment', NULL, 2, '2020-06-13 16:13:06', '2020-06-14 18:45:24');
INSERT INTO `menu` VALUES (8, '定时任务', 2, 2, 'el-icon-timer', 6, '/task', 'Task', NULL, 1, '2020-06-13 16:13:14', '2020-06-14 18:45:26');
INSERT INTO `menu` VALUES (9, '系统监控', 1, 1, 'el-icon-data-line', 3, '/monitor', '', NULL, 1, '2020-06-13 16:14:14', '2020-06-15 09:17:17');
INSERT INTO `menu` VALUES (10, '操作日志', 2, 9, 'fa fa-history', 3, '/operateLog', 'OperateLog', NULL, 1, '2020-06-13 16:14:00', '2020-06-13 16:14:00');
INSERT INTO `menu` VALUES (11, '异常日志', 2, 9, 'fa fa-bug', 3, '/exceptionLog', 'ExceptionLog', NULL, 1, '2020-06-13 16:14:00', '2020-06-13 16:14:00');
INSERT INTO `menu` VALUES (12, 'SQL监控', 2, 9, 'el-icon-coin', 4, '/druid', 'Druid', NULL, 1, NULL, NULL);
INSERT INTO `menu` VALUES (13, '主机状态', 1, 9, 'fa fa-heartbeat', 3, '/systemInfo', 'SystemInfo', NULL, 1, '2020-06-13 16:14:00', '2020-06-13 16:14:00');
INSERT INTO `menu` VALUES (14, '系统工具', 1, 1, 'el-icon-menu', 3, '/tools', '', NULL, 1, '2020-06-13 16:14:17', '2020-06-15 09:17:20');
INSERT INTO `menu` VALUES (15, 'SMTP管理', 2, 14, 'fa fa-envelope', 3, '/smtp', 'SMTP', NULL, 1, '2020-06-13 16:14:02', '2020-06-13 16:14:02');
INSERT INTO `menu` VALUES (16, '用户查询', 3, 3, NULL, 99, NULL, NULL, 'user:query', 1, '2020-06-22 05:20:32', NULL);
INSERT INTO `menu` VALUES (17, '用户添加', 3, 3, NULL, 98, NULL, NULL, 'user:add', 1, '2020-06-22 05:20:41', NULL);
INSERT INTO `menu` VALUES (18, '用户修改', 3, 3, NULL, 100, NULL, NULL, 'user:modify', 1, '2020-06-22 05:20:55', NULL);
INSERT INTO `menu` VALUES (19, '用户删除', 3, 3, NULL, 100, NULL, NULL, 'user:delete', 1, NULL, NULL);
INSERT INTO `menu` VALUES (20, '角色查询', 3, 4, NULL, 3, NULL, NULL, 'role:query', 1, '2020-06-21 02:43:29', NULL);
INSERT INTO `menu` VALUES (21, '角色添加', 3, 4, NULL, 1, NULL, NULL, 'role:add', 1, '2020-06-20 08:45:47', '2020-06-20 08:45:47');
INSERT INTO `menu` VALUES (35, '角色修改', 3, 4, NULL, 3, NULL, NULL, 'role:modify', 1, '2020-06-21 05:05:55', '2020-06-21 05:05:55');
INSERT INTO `menu` VALUES (36, '角色删除', 3, 4, NULL, 3, NULL, NULL, 'role:delete', 1, '2020-06-21 05:07:49', '2020-06-21 05:07:15');
INSERT INTO `menu` VALUES (37, '操作日志查询', 3, 10, NULL, 100, NULL, NULL, 'log:query', 1, '2020-06-27 01:02:55', '2020-06-27 00:58:46');
INSERT INTO `menu` VALUES (40, '操作日志删除', 3, 10, NULL, 100, NULL, NULL, 'log:delete', 1, '2020-06-27 01:03:01', '2020-06-27 00:59:34');
INSERT INTO `menu` VALUES (41, '测试菜单', 2, 14, 'fa fa-user', 3, '/test', 'Test', NULL, 0, '2021-01-25 13:33:23', '2020-06-29 15:36:40');
INSERT INTO `menu` VALUES (42, '测试目录', 1, 1, 'fa fa-user', 3, '/test1', NULL, NULL, 0, '2021-04-12 13:09:00', '2020-06-29 23:55:20');
INSERT INTO `menu` VALUES (47, '菜单查询', 3, 5, NULL, 3, NULL, NULL, 'menu:query', 1, '2021-04-12 16:11:34', '2021-04-12 16:11:34');
INSERT INTO `menu` VALUES (48, '菜单添加', 3, 5, NULL, 3, NULL, NULL, 'menu:add', 1, '2021-04-12 16:11:34', '2021-04-12 16:11:34');
INSERT INTO `menu` VALUES (49, '菜单修改', 3, 5, NULL, 3, NULL, NULL, 'menu:modify', 1, '2021-04-12 16:11:34', '2021-04-12 16:11:34');
INSERT INTO `menu` VALUES (50, '菜单删除', 3, 5, NULL, 3, NULL, NULL, 'menu:delete', 1, '2021-04-12 16:15:19', '2021-04-12 16:15:21');

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
INSERT INTO `properties` VALUES (13, 'email_username', 'service@xiaosm.cn', 'email', '2020-06-28 19:43:56');
INSERT INTO `properties` VALUES (14, 'email_password', 'yangzxservice..', 'email', '2020-06-28 19:43:56');
INSERT INTO `properties` VALUES (15, 'email_send_name', 'YAdmin-易后台管理系统', 'email', '2020-06-28 19:43:56');
INSERT INTO `properties` VALUES (16, 'email_server_host', 'smtp.ym.163.com', 'email', '2020-06-28 19:43:56');
INSERT INTO `properties` VALUES (17, 'email_server_port', '994', 'email', '2020-06-28 19:43:56');
INSERT INTO `properties` VALUES (18, 'email_ssl', 'true', 'email', '2020-06-28 19:43:56');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `name_zh` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色中文名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_admin', '管理员', '-', 1, '2020-06-18 04:07:18', '2020-06-13 16:19:41');
INSERT INTO `role` VALUES (2, 'ROLE_manager', '经理', '-', 1, '2021-03-29 18:38:46', '2020-06-14 05:23:29');
INSERT INTO `role` VALUES (3, 'ROLE_employee', '员工', '企业员工', 0, '2021-04-16 15:26:52', NULL);
INSERT INTO `role` VALUES (4, 'ROLE_test', 'test', '测试员工数据', 0, '2021-04-16 15:25:38', NULL);

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
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 35);
INSERT INTO `role_menu` VALUES (2, 36);
INSERT INTO `role_menu` VALUES (2, 5);
INSERT INTO `role_menu` VALUES (2, 37);
INSERT INTO `role_menu` VALUES (2, 6);
INSERT INTO `role_menu` VALUES (2, 7);
INSERT INTO `role_menu` VALUES (2, 8);
INSERT INTO `role_menu` VALUES (2, 40);
INSERT INTO `role_menu` VALUES (2, 9);
INSERT INTO `role_menu` VALUES (2, 10);
INSERT INTO `role_menu` VALUES (2, 11);
INSERT INTO `role_menu` VALUES (2, 12);
INSERT INTO `role_menu` VALUES (2, 13);
INSERT INTO `role_menu` VALUES (2, 16);
INSERT INTO `role_menu` VALUES (2, 21);
INSERT INTO `role_menu` VALUES (3, 16);
INSERT INTO `role_menu` VALUES (3, 2);
INSERT INTO `role_menu` VALUES (3, 3);

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
  `status` tinyint NULL DEFAULT NULL COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `task_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '心跳监控', '0/10 * * * * ?', '监控系统状态', 'cn.xiaosm.yadmin.scheduler.EmailTask', NULL, 1, 0, '2020-06-30 15:47:24', '2020-06-28 11:27:34');
INSERT INTO `task` VALUES (2, '测试任务', '0/1 * * * * ?', NULL, 'cn.xiaosm.yadmin.scheduler.EmailTask', NULL, 1, 2, '2020-06-30 15:47:27', '2021-04-16 12:40:07');
INSERT INTO `task` VALUES (5, '心跳监控2', '0/10 * * * * ?', NULL, 'cn.xiaosm.yadmin.basic.scheduler.HeartTask', NULL, 1, 1, '2021-04-16 12:40:04', '2021-04-14 14:58:37');

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
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `status` tinyint NULL DEFAULT 1 COMMENT '用户状态',
  `uuid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '每次登录的uuid',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '不是木易杨', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', '111@qq.com', '男', 18, 'https://q1.qlogo.cn/g?b=qq&nk=1282381264&s=640', 1, '34f53267-c61a-45de-838e-8d0d7d92eb04', '2021-04-16 23:32:22', '2020-06-01 08:00:00');
INSERT INTO `user` VALUES (2, 'test1', '测试用户-啦啦啦', '$2a$10$2JROkp9J1aCsAiMq4iPyiOLDzSbSPbiNtAGFFwWmd/O.G.mvLGCjO', '111@qq.com', '男', 19, '/upload/8431fde2-568e-471d-b576-1f0ac4709914.jpeg', 1, 'fa804ba9-c7da-4d35-87ce-75052e2ff5d9', '2021-04-16 23:31:33', '2020-06-12 16:42:13');
INSERT INTO `user` VALUES (3, 'ceshi-1', '测试用户-1', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-21 08:18:09', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (4, 'ceshi-2', '测试用户-2', '$2a$10$8drJJLfChPHNIQx76aGuGOkuK8KBbTVAnXLQin0ij1eLn9ECc3BVK', '111@qq.com', '女', 12, NULL, 2, ' ', '2020-06-29 13:59:21', '2020-06-13 04:42:13');
INSERT INTO `user` VALUES (5, 'ceshi-3', '测试用户-3', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-22 07:14:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (6, 'ceshi-4', '测试用户-4', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-22 07:14:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (7, 'ceshi-5', '测试用户-5', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-30 07:48:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (8, 'ceshi-6', '测试用户-6', '$2a$10$/Y5fBeMcAVD1vHGupDY6SOtuoaCWXQkNVj52u9NFVopvIg4ZgSpQa', '111@qq.com', '男', 1, NULL, 2, ' ', '2020-06-30 06:08:30', '2020-06-13 04:42:13');
INSERT INTO `user` VALUES (9, 'ceshi-7', '测试用户-7', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-30 07:47:48', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (10, 'ceshi-8', '测试用户-8', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-07-02 07:04:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (11, 'ceshi-9', '测试用户-9', '$2a$10$0Uz31LbRTwc.JKf32pzaF.wef/yK7r64Yzd4xacOeITrhrx0Mj7aO', '111@qq.com', '男', NULL, NULL, 2, ' ', '2020-06-30 07:48:04', '2020-06-13 04:42:13');
INSERT INTO `user` VALUES (12, 'ceshi-10', '测试用户-10', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-27 06:43:48', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (13, 'ceshi-11', '测试用户-11', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-07-02 07:04:10', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (14, 'ceshi-12', '测试用户-12', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-27 14:05:07', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (15, 'ceshi-13', '测试用户-13', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:34', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (16, 'ceshi-14', '测试用户-14', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-30 07:48:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (17, 'ceshi-15', '测试用户-15', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2021-04-15 10:59:16', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (18, 'ceshi-16', '测试用户-16', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-30 07:48:07', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (19, 'ceshi-17', '测试用户-17', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:38', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (20, 'ceshi-18', '测试用户-18', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:39', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (21, 'ceshi-19', '测试用户-19', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:40', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (22, 'ceshi-20', '测试用户-20', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:44', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (23, 'ceshi-21', '测试用户-21', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2020-06-30 15:57:42', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (24, 'ceshi-22', '测试用户-22', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2021-04-15 10:59:22', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (25, 'ceshi-23', '测试用户-23', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 0, ' ', '2021-04-15 11:01:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (26, 'ceshi-24', '测试用户-24', '$2a$10$x7QP0YofIx23LfiO5aZXQ.eGn2omtnX3iWrte4WtttgpHLHFbLrNC', '111@qq.com', '男', 1, NULL, 1, '5b6073f1-727e-4824-bde0-9487cf7e1ee0', '2021-04-16 14:18:50', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (27, 'ceshi-25', '测试用户-25', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:56', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (28, 'ceshi-26', '测试用户-26', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:56', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (29, 'ceshi-27', '测试用户-27', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2021-04-16 15:27:15', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (30, 'ceshi-28', '测试用户-28', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2021-03-28 16:18:41', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (31, 'ceshi-29', '测试用户-29', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2021-03-28 16:18:41', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (32, 'ceshi-30', '测试用户-30', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:56', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (33, 'ceshi-31', '测试用户-31', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:56', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (34, 'ceshi-32', '测试用户-32', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (35, 'ceshi-33', '测试用户-33', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (36, 'ceshi-34', '测试用户-34', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (37, 'ceshi-35', '测试用户-35', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (38, 'ceshi-36', '测试用户-36', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (39, 'ceshi-37', '测试用户-37', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:57', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (40, 'ceshi-38', '测试用户-38', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (41, 'ceshi-39', '测试用户-39', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (42, 'ceshi-40', '测试用户-40', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (43, 'ceshi-41', '测试用户-41', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (44, 'ceshi-42', '测试用户-42', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (45, 'ceshi-43', '测试用户-43', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (46, 'ceshi-44', '测试用户-44', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:58', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (47, 'ceshi-45', '测试用户-45', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (48, 'ceshi-46', '测试用户-46', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (49, 'ceshi-47', '测试用户-47', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (50, 'ceshi-48', '测试用户-48', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (51, 'ceshi-49', '测试用户-49', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (52, 'ceshi-50', '测试用户-50', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (53, 'ceshi-51', '测试用户-51', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:04:59', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (54, 'ceshi-52', '测试用户-52', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-21 13:52:44', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (55, 'ceshi-53', '测试用户-53', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-21 13:52:45', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (56, 'ceshi-54', '测试用户-54', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:00', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (57, 'ceshi-55', '测试用户-55', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:00', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (58, 'ceshi-56', '测试用户-56', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (59, 'ceshi-57', '测试用户-57', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (60, 'ceshi-58', '测试用户-58', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (61, 'ceshi-59', '测试用户-59', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (62, 'ceshi-60', '测试用户-60', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (63, 'ceshi-61', '测试用户-61', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (64, 'ceshi-62', '测试用户-62', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:01', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (65, 'ceshi-63', '测试用户-63', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (66, 'ceshi-64', '测试用户-64', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (67, 'ceshi-65', '测试用户-65', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (68, 'ceshi-66', '测试用户-66', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (69, 'ceshi-67', '测试用户-67', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (70, 'ceshi-68', '测试用户-68', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (71, 'ceshi-69', '测试用户-69', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (72, 'ceshi-70', '测试用户-70', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:02', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (73, 'ceshi-71', '测试用户-71', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (74, 'ceshi-72', '测试用户-72', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (75, 'ceshi-73', '测试用户-73', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (76, 'ceshi-74', '测试用户-74', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (77, 'ceshi-75', '测试用户-75', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (78, 'ceshi-76', '测试用户-76', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (79, 'ceshi-77', '测试用户-77', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:03', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (80, 'ceshi-78', '测试用户-78', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (81, 'ceshi-79', '测试用户-79', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (82, 'ceshi-80', '测试用户-80', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (83, 'ceshi-81', '测试用户-81', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (84, 'ceshi-82', '测试用户-82', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (85, 'ceshi-83', '测试用户-83', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (86, 'ceshi-84', '测试用户-84', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:04', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (87, 'ceshi-85', '测试用户-85', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (88, 'ceshi-86', '测试用户-86', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (89, 'ceshi-87', '测试用户-87', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (90, 'ceshi-88', '测试用户-88', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (91, 'ceshi-89', '测试用户-89', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (92, 'ceshi-90', '测试用户-90', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (93, 'ceshi-91', '测试用户-91', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-21 08:19:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (94, 'ceshi-92', '测试用户-92', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:05', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (95, 'ceshi-93', '测试用户-93', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 2, ' ', '2020-06-21 08:18:20', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (96, 'ceshi-94', '测试用户-94', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (97, 'ceshi-95', '测试用户-95', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (98, 'ceshi-96', '测试用户-96', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (99, 'ceshi-97', '测试用户-97', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (100, 'ceshi-98', '测试用户-98', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (101, 'ceshi-99', '测试用户-99', '21232f297a57a5a743894a0e4a801fc3', '111@qq.com', NULL, NULL, NULL, 1, ' ', '2020-06-17 14:05:06', '2020-06-13 16:42:13');
INSERT INTO `user` VALUES (110, 'testRole1', '啦啦啦', '$2a$10$qbjhcuK.isybBuKEH4H5/eSpYlLKwrES/.0AU.lsi6c8LcQbz46Pu', 'abc@qq.com', '男', 11, NULL, 2, '', '2020-06-21 13:53:18', '2020-06-21 09:42:42');
INSERT INTO `user` VALUES (114, 'ceshi-112', '测试用户-112', '$2a$10$jWfGFhGekiWhNg2qF.VcB.kazSGiE6VONRHM9OhV0QgWK9LmDESi2', 'abc@qq.com', '男', 1, NULL, 1, '', '2020-06-27 14:57:19', '2020-06-27 14:57:19');
INSERT INTO `user` VALUES (115, 'tangzz', '泽泽泽泽', '$2a$10$iE5WIKdxR0Nu9JqbXOoPre1AZURkxKwXu.wgAHLuv6/BatxWrWlta', '2191408431@qq.com', '男', 18, '/upload/3a1580da-edc8-4bca-b316-8bbd3374968d.gif', 0, '8fc66dba-d1d0-42e4-88b9-464337575795', '2021-01-25 13:42:54', '2020-06-29 14:01:10');
INSERT INTO `user` VALUES (116, 'zezeze', '泽泽泽泽泽', '$2a$10$K0rBjf1YmJbE3cibP6DVwOlm/YcxnlnhHBy2PKuUYmYHt3S/jwJFq', '2191408431@qq.com', '男', 18, '/upload/1bb26d1d-dae9-4d3d-b55c-0333f5f79626.jpg', 2, '2ef67cba-9bcb-498d-b459-0bb38e436bbb', '2020-06-30 05:21:28', '2020-06-30 02:01:52');
INSERT INTO `user` VALUES (117, 'zezezeze', '泽泽泽泽', '$2a$10$9ZxEtgNrzyJIZU6sB5cI.e7zTcG75N3wbffoXsKQ1xMoznM9nbe9u', '2191408431@qq.com', '男', 18, '/upload/4f063bb1-1d37-40a0-be3d-c97349460038.gif', 1, 'b2510afa-3e66-47bc-8c19-b1602c4eade6', '2020-06-30 06:18:21', '2020-06-30 06:17:14');

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
) ENGINE = InnoDB AUTO_INCREMENT = 302 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_login_track
-- ----------------------------

-- ----------------------------
-- Table structure for user_open
-- ----------------------------
DROP TABLE IF EXISTS `user_open`;
CREATE TABLE `user_open`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快捷登录平台中的用户id',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快捷登录平台',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `open_userId_fk`(`user_id`) USING BTREE,
  CONSTRAINT `open_userId_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_open
-- ----------------------------
INSERT INTO `user_open` VALUES (1, 1, '', 'QQ', 'today', 'http://thirdqq.qlogo.cn/g?b=oidb&k=ZGIq18FHUJLrUSWo4HrO1w&s=100&t=1617554377', 1, '2021-04-16 23:32:30', '2021-04-16 23:32:30');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
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
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (15, 110, 2);
INSERT INTO `user_role` VALUES (16, 6, 2);
INSERT INTO `user_role` VALUES (24, 4, 2);
INSERT INTO `user_role` VALUES (27, 115, 2);
INSERT INTO `user_role` VALUES (29, 116, 2);
INSERT INTO `user_role` VALUES (30, 116, 3);
INSERT INTO `user_role` VALUES (31, 2, 2);
INSERT INTO `user_role` VALUES (32, 2, 3);
INSERT INTO `user_role` VALUES (33, 117, 2);

SET FOREIGN_KEY_CHECKS = 1;

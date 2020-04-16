-- 创建 demo 数据库
CREATE DATABASE
IF
	NOT EXISTS demo DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 选择 demo 数据库
USE demo;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`nick` VARCHAR ( 15 ) NOT NULL DEFAULT '' COMMENT '昵称',
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 用户授权表
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`user_id` INT ( 11 ) NOT NULL COMMENT '用户id',
	`identity_type` TINYINT (4) NOT NULL DEFAULT 1 COMMENT '1用户名 2手机号 3微信',
	`identifier` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户名、手机号或第三方应用的唯一标识',
	`certificate` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '密码凭证(站内登陆保存密码，第三方登陆不保存或保存第三方token)',
	`salt` VARCHAR ( 255 ) COMMENT '密码盐，用于加密站内密码',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `uniq_user_token_identity_type_identifier` (`identity_type`, `identifier`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户授权表';

-- 用户刷新令牌表
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`user_id` INT ( 11 ) NOT NULL COMMENT '用户id',
	`token` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT 'token',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `uniq_user_token_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户刷新令牌表';

-- 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR ( 50 ) NOT NULL COMMENT '角色名称',
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关系表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`user_id` INT ( 11 ) NOT NULL COMMENT '用户id',
	`role_id` INT ( 11 ) NOT NULL COMMENT '角色id',
	PRIMARY KEY ( `id` ),
	KEY `idx_user_role_user_id` (`user_id`),
	KEY `idx_user_role_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';
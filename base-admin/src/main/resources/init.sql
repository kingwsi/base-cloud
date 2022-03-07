-- base.`member` definition

CREATE TABLE `member` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
                          `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
                          `gender` varchar(2) DEFAULT NULL COMMENT '性别',
                          `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
                          `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                          `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                          `introduce` varchar(500) DEFAULT NULL COMMENT '介绍',
                          `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
                          `password` varchar(255) DEFAULT NULL COMMENT '密码',
                          `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最后登录ip',
                          `account_status` int DEFAULT NULL COMMENT '账户状态 1 正常 0 停用',
                          `openid` varchar(100) DEFAULT NULL COMMENT 'openid',
                          `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                          `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                          `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                          `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                          `deleted` int DEFAULT '0' COMMENT '删除标记',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员';


-- base.sys_api_whitelist definition

CREATE TABLE `sys_api_whitelist` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `description` varchar(100) DEFAULT NULL COMMENT '描述',
                                     `path` varchar(255) DEFAULT NULL COMMENT '地址',
                                     `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                                     `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                                     `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                                     `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                                     `deleted` int DEFAULT '0' COMMENT '删除标记',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='api白名单';


-- base.sys_dictionaries definition

CREATE TABLE `sys_dictionaries` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                                    `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                                    `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                                    `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                                    `deleted` int DEFAULT '0' COMMENT '删除标记',
                                    `value` varchar(500) NOT NULL COMMENT '值',
                                    `code` varchar(100) NOT NULL COMMENT '编码',
                                    `description` varchar(500) NOT NULL COMMENT '描述',
                                    `group_code` varchar(100) DEFAULT NULL COMMENT '分组CODE',
                                    `sort` int NOT NULL COMMENT '排序',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据';


-- base.sys_organizations definition

CREATE TABLE `sys_organizations` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
                                     `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                     `parent_id` varchar(100) DEFAULT NULL COMMENT '上级id',
                                     `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                                     `organization_id` varchar(32) DEFAULT NULL COMMENT '组织id',
                                     `role_ids` varchar(400) DEFAULT NULL COMMENT '角色',
                                     `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                                     `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                                     `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                                     `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                                     `deleted` int DEFAULT '0' COMMENT '删除标记',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织';


-- base.sys_resources definition

CREATE TABLE `sys_resources` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
                                 `uri` varchar(32) DEFAULT NULL COMMENT '资源地址',
                                 `methods` varchar(255) DEFAULT NULL COMMENT '请求方式',
                                 `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                 `sort` varchar(100) DEFAULT NULL COMMENT '排序',
                                 `icon` varchar(100) DEFAULT NULL COMMENT '图标',
                                 `type` varchar(20) DEFAULT NULL COMMENT '资源类型',
                                 `component` varchar(100) DEFAULT NULL COMMENT '组件路径',
                                 `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                                 `parent_id` varchar(100) DEFAULT NULL COMMENT '上级id',
                                 `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                                 `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                                 `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                                 `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                                 `deleted` int DEFAULT '0' COMMENT '删除标记',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源';


-- base.sys_roles definition

CREATE TABLE `sys_roles` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(100) DEFAULT NULL COMMENT '角色名',
                             `description` varchar(32) DEFAULT NULL COMMENT '描述',
                             `status` varchar(10) DEFAULT NULL COMMENT '状态',
                             `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                             `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                             `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                             `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                             `deleted` int DEFAULT '0' COMMENT '删除标记',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';


-- base.sys_roles_and_resources definition

CREATE TABLE `sys_roles_and_resources` (
                                           `role_id` varchar(32) NOT NULL COMMENT '角色id',
                                           `resource_id` varchar(32) NOT NULL COMMENT '资源id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源关联';


-- base.sys_users definition

CREATE TABLE `sys_users` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(100) DEFAULT NULL COMMENT '用户名',
                             `nickname` varchar(255) DEFAULT NULL COMMENT 'nickname',
                             `password` varchar(255) DEFAULT NULL COMMENT '密码',
                             `full_name` varchar(50) DEFAULT NULL COMMENT '全名',
                             `status` varchar(2) DEFAULT '1' COMMENT '状态 1启用， 0 禁用',
                             `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                             `introduction` varchar(255) DEFAULT NULL COMMENT '介绍',
                             `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                             `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                             `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                             `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                             `deleted` int DEFAULT '0' COMMENT '删除标记',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';


-- base.sys_users_and_organizations definition

CREATE TABLE `sys_users_and_organizations` (
                                               `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                               `organization_id` varchar(32) NOT NULL COMMENT '组织id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户组织关联';


-- base.sys_users_and_roles definition

INSERT INTO base.`member` (real_name,nick_name,gender,mobile,email,avatar,introduce,last_login_time,password,last_login_ip,account_status,openid,creator,created_date,last_updater,last_update_date,deleted) VALUES
    ('FF','TIEZHU','1','15555555555','15555555555@163.com','https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',NULL,NULL,'$2a$10$sQqwErGT/hFQNA/qFXiTX./0FSDgMMJMjC5oakh54GfKry75CghZK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

CREATE TABLE `sys_users_and_roles` (
                                       `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                       `role_id` varchar(32) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联';

INSERT INTO base.sys_users_and_roles (user_id,role_id) VALUES
                                                           ('1','1'),
                                                           ('2','2');

INSERT INTO base.sys_roles_and_resources (role_id,resource_id) VALUES
                                                                   ('1','1'),
                                                                   ('1','2'),
                                                                   ('1','3'),
                                                                   ('1','4'),
                                                                   ('1','5'),
                                                                   ('1','6'),
                                                                   ('1','7'),
                                                                   ('1','8'),
                                                                   ('1','9'),
                                                                   ('1','10');
INSERT INTO base.sys_roles_and_resources (role_id,resource_id) VALUES
                                                                   ('1','11'),
                                                                   ('1','12'),
                                                                   ('1','13'),
                                                                   ('2','1'),
                                                                   ('2','2'),
                                                                   ('2','3');

INSERT INTO base.sys_users (username,nickname,password,full_name,status,avatar,introduction,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                            ('admin','admin','$2a$10$vswPCKaH2RJ.hzBPOHsrVO2k72nqkVt3VigmJUvboHYiOAjN4XlX.','管理员','1','https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                            ('test','test','$2a$10$f71x3715SbPYlqA8QbmIyeR5JnJKhvBFYdX/xV/hHknAV42prD5SW','测试','1','',NULL,NULL,NULL,NULL,NULL,0);

INSERT INTO base.sys_roles (name,description,status,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                    ('admin','超级管理员','1',NULL,NULL,NULL,NULL,0),
                                                                                                                    ('general user','普通用户','1',NULL,NULL,NULL,NULL,0);

INSERT INTO base.sys_organizations (name,description,parent_id,remark,organization_id,role_ids,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                               ('1','1','-1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                               ('2','2','-1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                               ('3','3','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                               ('4','4','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                               ('5','5','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                               ('6','6','4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

INSERT INTO base.sys_resources (name,uri,methods,description,sort,icon,`type`,component,remark,parent_id,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                                         ('debug接口','/**','GET;POST;PUT;DELETE;OPTIONS','debug接口','0','','API','','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('系统配置','/system','','','2','control','MENU','RouteView','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('用户管理','/system/user','','','2','user','MENU','user/List','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('角色管理','/system/role','','','3','user','MENU','role/List','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('字典管理','/system/dictionary','','','3','user','MENU','dictionary/List','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('资源管理','/system/resource','','','4','user','MENU','resource/List','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('角色权限配置','/system/role/permission/:id','','','4','user','MENU','role/Permission','hidden','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('仪表盘','/dashboard','','','1','dashboard','MENU','RouteView','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('个人中心','/system/account/info','','','3','user','MENU','user/Info','','8',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('工作台','/dashboard/workplace','','','2','dashboard','MENU','dashboard/Workplace','','8',NULL,NULL,NULL,NULL,0);
INSERT INTO base.sys_resources (name,uri,methods,description,sort,icon,`type`,component,remark,parent_id,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                                         ('会员中心','/member','','','3','user','MENU','RouteView','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('会员管理','/member/list','','','1','user','MENU','member/List','','11',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         ('接口白名单','/system/api-whitelist','',NULL,'5','user','MENU',NULL,NULL,'2',NULL,NULL,NULL,NULL,0);
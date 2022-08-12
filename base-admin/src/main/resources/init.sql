-- `member` definition 会员

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
);


-- sys_api_whitelist definition api白名单

CREATE TABLE `sys_api_whitelist` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `description` varchar(100) DEFAULT NULL COMMENT '描述',
                                     `path` varchar(255) DEFAULT NULL COMMENT '地址',
                                     `methods` varchar(255) DEFAULT NULL COMMENT '请求方式',
                                     `creator` varchar(100) DEFAULT NULL COMMENT '创建者',
                                     `created_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
                                     `last_updater` varchar(100) DEFAULT NULL COMMENT '最后更新人',
                                     `last_update_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
                                     `deleted` int DEFAULT '0' COMMENT '删除标记',
                                     PRIMARY KEY (`id`)
);


-- sys_dictionaries definition 字典数据

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
);

-- sys_resources definition

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
);


-- sys_roles definition

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
);


-- sys_roles_and_resources definition 角色资源关联

CREATE TABLE `sys_roles_and_resources` (
                                           `role_id` varchar(32) NOT NULL COMMENT '角色id',
                                           `resource_id` varchar(32) NOT NULL COMMENT '资源id'
);


-- sys_users definition

CREATE TABLE `sys_users` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(100) DEFAULT NULL COMMENT '用户名',
                             `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
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
);


-- sys_users_and_roles definition

INSERT INTO `member` (real_name,nick_name,gender,mobile,email,avatar,introduce,last_login_time,password,last_login_ip,account_status,openid,creator,created_date,last_updater,last_update_date,deleted) VALUES
    ('FF','TIEZHU','1','15555555555','15555555555@163.com','https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',NULL,NULL,'$2a$10$sQqwErGT/hFQNA/qFXiTX./0FSDgMMJMjC5oakh54GfKry75CghZK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

CREATE TABLE `sys_users_and_roles` (
                                       `user_id` varchar(32) NOT NULL COMMENT '用户id',
                                       `role_id` varchar(32) NOT NULL COMMENT '角色id'
);

INSERT INTO sys_users_and_roles (user_id,role_id) VALUES
                                                           ('1','1'),
                                                           ('2','2');

INSERT INTO sys_roles_and_resources (role_id,resource_id) VALUES
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
INSERT INTO sys_roles_and_resources (role_id,resource_id) VALUES
                                                                   ('1','11'),
                                                                   ('1','12'),
                                                                   ('1','13'),
                                                                   ('2','8'),
                                                                   ('2','9'),
                                                                   ('2','10');

INSERT INTO sys_users (id,username,nickname,password,full_name,status,avatar,introduction,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                            (1, 'admin','admin','$2a$10$vswPCKaH2RJ.hzBPOHsrVO2k72nqkVt3VigmJUvboHYiOAjN4XlX.','管理员','1','https://dummyimage.com/200x200/cccaca/000000.png',NULL,NULL,NULL,NULL,NULL,0),
                                                                                                                                                            (2, 'test','test','$2a$10$f71x3715SbPYlqA8QbmIyeR5JnJKhvBFYdX/xV/hHknAV42prD5SW','测试','1','https://dummyimage.com/200x200/cccaca/000000.png',NULL,NULL,NULL,NULL,NULL,0);

INSERT INTO sys_roles (id,name,description,status,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                    (1,'admin','超级管理员','1',NULL,NULL,NULL,NULL,0),
                                                                                                                    (2,'general user','普通用户','1',NULL,NULL,NULL,NULL,0);

INSERT INTO sys_resources (id,name,uri,methods,description,sort,icon,`type`,component,remark,parent_id,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                                         (1,'debug接口','/**','GET;POST;PUT;DELETE;OPTIONS','debug接口','0','','API','','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (2,'系统配置','/system','','','2','control','MENU','','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (3,'用户管理','/system/user','','','2','user','MENU','','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (4,'角色管理','/system/role','','','3','user','MENU','','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (5,'字典管理','/system/dictionary','','','3','user','MENU','','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (6,'资源管理','/system/resource','','','4','user','MENU','','','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (7,'角色权限配置','/system/role/permission/:id','','','4','user','MENU','','hidden','2',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (8,'仪表盘','/dashboard','','','1','dashboard','MENU','','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (9,'个人中心','/dashboard/user','','','3','user','MENU','','','8',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (10,'工作台','/dashboard/workplace','','','2','dashboard','MENU','','','8',NULL,NULL,NULL,NULL,0);
INSERT INTO sys_resources (id,name,uri,methods,description,sort,icon,`type`,component,remark,parent_id,creator,created_date,last_updater,last_update_date,deleted) VALUES
                                                                                                                                                                         (11,'会员中心','/member','','','3','user','MENU','RouteView','','-1',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (12,'会员管理','/member/list','','','1','user','MENU','member/List','','11',NULL,NULL,NULL,NULL,0),
                                                                                                                                                                         (13,'接口白名单','/system/api-whitelist','',NULL,'5','user','MENU',NULL,NULL,'2',NULL,NULL,NULL,NULL,0);


create table banner
(
    id               int primary key auto_increment,
    image            varchar(500) comment '图片',
    link             varchar(500) comment '链接',
    title            varchar(255) comment '标题',
    groupCode        varchar(50) comment '分组',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment 'banner';

create table banner_group
(
    id               int primary key auto_increment,
    code             varchar(50) comment 'code',
    name             varchar(255) comment '组名',
    remark           varchar(255) comment '备注',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment 'banner';

create table brand
(
    `id`               int primary key auto_increment,
    `name`             varchar(255) comment '名称',
    `logo`             varchar(255) comment 'LOGO',
    `enable`           bit(1) comment '启用',
    `introduction`     varchar(255) comment '介绍',
    `sort`             int comment '排序',
    `creator`          varchar(100)  null comment '创建者',
    `created_date`     timestamp     null comment '创建日期',
    `last_updater`     varchar(100)  null comment '最后更新人',
    `last_update_date` timestamp     null comment '最后更新时间',
    `deleted`          int default 0 null comment '删除标记'
) comment 'banner分组';

create table category
(
    `id`             int primary key auto_increment,
    `name`           varchar(255) comment '分类名称',
    `description`    varchar(255) comment '描述',
    `parent_id`      int comment '上级id -1为根',
    `image`          varchar(255) comment '图片',
    `sort`           int comment '排序',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '分类';

create table favorites
(
    id               int primary key auto_increment,
    goods_id         int comment '商品id',
    member_id        int comment '会员id',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '收藏';

create table goods
(
    `id`             int primary key auto_increment,
    `category_id`    int comment '分类Id',
    `brand_id`       int comment '品牌id',
    `name`           varchar(255) comment '名称',
    `title`          varchar(255) comment '标题',
    `tags`           varchar(255) comment '标签',
    `min_price`      int comment '最高价',
    `max_price`      int comment '最低价',
    `cover_image`    varchar(255) comment '封面图',
    `status`         varchar(255) comment '状态',
    `keyword`        varchar(255) comment '关键字',
    `sort`           int comment '排序',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '商品';

create table goods_attribute
(
    `id`              int primary key auto_increment,
    `attribute_name`  varchar(100) comment '属性名称',
    `attribute_value` varchar(100) comment '属性值',
    `sku_id`          int comment '规格id',
    `goods_id`        int comment '商品id',

    creator           varchar(100)  null comment '创建者',
    created_date      timestamp     null comment '创建日期',
    last_updater      varchar(100)  null comment '最后更新人',
    last_update_date  timestamp     null comment '最后更新时间',
    deleted           int default 0 null comment '删除标记'
) comment '商品属性';

create table goods_detail
(
    `id`             int primary key auto_increment,
    `rich_text`      longtext comment '富文本',
    `share_image`    varchar(255) comment '分享图片',
    `slideshow`      varchar(255) comment '轮播图',
    `ship`           boolean comment '是否包邮',
    `tags`           varchar(255) comment '标签',
    `services`       varchar(255) comment '服务',
    `sales_return`   boolean comment '是否可退货',
    `auto_on_sale`   boolean comment '自动上架',
    `on_sale_time`   timestamp comment '上架时间',
    `off_sale_time`  timestamp comment '下架时间',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '商品明细';

create table goods_sku
(
    `id`                   int primary key auto_increment,
    `thumbnail`            varchar(500) comment '缩略图',
    `goods_id`             int comment '商品id',
    `price`                int comment '价格',
    `stock`                int comment '库存',
    `purchase_limit`       int comment '购买限制',
    `enable`               boolean comment '生效',
    `sort`                 int comment '排序',
    `property_description` varchar(500) comment '属性描述',
    creator                varchar(100)  null comment '创建者',
    created_date           timestamp     null comment '创建日期',
    last_updater           varchar(100)  null comment '最后更新人',
    last_update_date       timestamp     null comment '最后更新时间',
    deleted                int default 0 null comment '删除标记'
) comment '商品规格';

create table member_address
(
    `id`               int primary key auto_increment,
    `member_id`        int comment '会员唯一标识',
    `province`         varchar(50) comment '省',
    `city`             varchar(100) comment '市',
    `town`             varchar(100) comment '镇',
    `detail`           varchar(255) comment '详细地址',
    `addressee_mobile` varchar(30) comment '收件人电话',
    `full_address`     varchar(500) comment '完整地址',
    `isDefault`        varchar(255) comment '是否默认',
    `tag`              varchar(255) comment '标签',
    `addressee_name`   varchar(255) comment '收件人名字',
    creator            varchar(100)  null comment '创建者',
    created_date       timestamp     null comment '创建日期',
    last_updater       varchar(100)  null comment '最后更新人',
    last_update_date   timestamp     null comment '最后更新时间',
    deleted            int default 0 null comment '删除标记'
) comment '会员地址';

create table shopping_bag
(
    `id`             int primary key auto_increment,
    `goods_id`       int comment '商品id',
    `sku_id`         int comment '规格id',
    `member_id`      int comment '会员id',
    `sku_name`       varchar(255) comment '规格名称',
    `num`            int comment '数量',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '购物袋';

create table trade
(
    `id`                int primary key auto_increment,
    `member_id`         int comment '会员id',
    `total_amount`      int comment '总金额',
    `pay_amount`        int comment '实际支付金额',
    `pay_datetime`      timestamp comment '支付时间',
    `pay_channel`       varchar(255) comment '支付渠道',
    `member_message`    varchar(255) comment '客户留言',
    `receipt_datetime`  timestamp comment '确认收货时间',
    `trade_no`          varchar(255) comment '单号',
    `out_trade_no`      varchar(255) comment '外部订单号',
    `status`            varchar(50) comment '状态',
    `remark`            varchar(255) comment '备注',
    `consignee`         varchar(255) comment '收货人',
    `consignee_address` varchar(255) comment '收货人地址',
    `consignee_mobile`  varchar(30) comment '收货人手机号',
    creator             varchar(100)  null comment '创建者',
    created_date        timestamp     null comment '创建日期',
    last_updater        varchar(100)  null comment '最后更新人',
    last_update_date    timestamp     null comment '最后更新时间',
    deleted             int default 0 null comment '删除标记'
) comment '订单';

create table trade_detail
(
    `id`             int primary key auto_increment,
    `goods_id`       int comment '商品id',
    `trade_id`       int comment '订单id',
    `sku_id`         int comment '规格id',
    `sku_properties` timestamp comment 'sku属性规格',
    `sku_image`      varchar(255) comment 'sku图片',
    `num`            varchar(255) comment '数量',
    `price`          timestamp comment '标价',
    `pay_amount`     varchar(255) comment '支付金额',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
) comment '订单明细';
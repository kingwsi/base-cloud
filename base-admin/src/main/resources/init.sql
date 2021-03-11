create table sys_dictionaries
(
    id               varchar(32)   not null comment '角色id',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记',
    name             varchar(32)   not null comment '角色id',
    code             varchar(32)   not null comment '角色id',
    description      varchar(32)   not null comment '角色id',
    sort             varchar(32)   not null comment '角色id'
)
    comment '用户角色关联';

create table sys_organizations
(
    id               varchar(32)   not null
        primary key,
    name             varchar(100)  null comment '组织名称',
    description      varchar(255)  null comment '描述',
    parent_id        varchar(100)  null comment '上级id',
    remark           varchar(100)  null comment '备注',
    organization_id  varchar(32)   null comment '组织id',
    role_ids         varchar(400)  null comment '角色',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
)
    comment '组织';

create table sys_resources
(
    id               varchar(32)   not null
        primary key,
    name             varchar(100)  null comment '资源名称',
    uri              varchar(32)   null comment '资源地址',
    methods          varchar(255)  null comment '请求方式',
    description      varchar(255)  null comment '描述',
    sort             varchar(100)  null comment '排序',
    icon             varchar(100)  null comment '图标',
    type             varchar(20)   null comment '资源类型',
    COMPONENT        varchar(100)  null,
    remark           varchar(100)  null comment '备注',
    parent_id        varchar(100)  null comment '上级id',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
)
    comment '资源';

create table sys_roles
(
    id               varchar(32)   not null
        primary key,
    name             varchar(100)  null comment '角色名',
    description      varchar(32)   null comment '描述',
    status           varchar(10)   null comment '状态',
    creator          varchar(100)  null comment '创建者',
    created_date     timestamp     null comment '创建日期',
    last_updater     varchar(100)  null comment '最后更新人',
    last_update_date timestamp     null comment '最后更新时间',
    deleted          int default 0 null comment '删除标记'
)
    comment '角色';
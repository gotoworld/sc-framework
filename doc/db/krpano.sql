/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : krpano

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2017-02-25 23:51:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auth_perm`
-- ----------------------------
DROP TABLE IF EXISTS `auth_perm`;
CREATE TABLE `auth_perm` (
  `id` varchar(32) NOT NULL COMMENT '权限id',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `match_str` varchar(50) NOT NULL COMMENT '权限匹配符',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_权限信息表';

-- ----------------------------
-- Records of auth_perm
-- ----------------------------
INSERT INTO `auth_perm` VALUES ('id_auth_perm', '权限_权限信息', 'authPerm:menu', 'id_sys', '', null, '1', null, '0', '2015-10-04 19:43:29', null);
INSERT INTO `auth_perm` VALUES ('id_auth_perm_add', '新增', 'authPerm:add', 'id_auth_perm', '', null, '1', null, '0', '2015-10-07 14:44:59', null);
INSERT INTO `auth_perm` VALUES ('id_auth_perm_del', '删除', 'authPerm:del', 'id_auth_perm', '', null, '1', null, '0', '2015-10-07 14:45:26', null);
INSERT INTO `auth_perm` VALUES ('id_auth_perm_edit', '编辑', 'authPerm:edit', 'id_auth_perm', '', null, '1', null, '0', '2015-10-07 14:45:13', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role', '权限_角色信息', 'authRole:menu', 'id_sys', '', null, '1', null, '0', '2015-10-04 19:43:56', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role_add', '新增', 'authRole:add', 'id_auth_role', '', null, '3', null, '0', '2015-10-07 14:39:03', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role_del', '删除', 'authRole:del', 'id_auth_role', '', null, '2', null, '0', '2015-10-07 14:38:45', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role_edit', '编辑', 'authRole:edit', 'id_auth_role', '', null, '2', null, '0', '2015-10-07 14:38:29', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role_parm', '角色权限修改', 'authRole:parm', 'id_auth_role', '', null, '3', null, '0', '2015-10-07 14:39:25', null);
INSERT INTO `auth_perm` VALUES ('id_auth_role_super', '设置超级管理员', 'authRole:super', 'id_auth_role', '', null, '2', '', '0', '2015-10-11 23:45:49', '2016-11-26 13:05:57');
INSERT INTO `auth_perm` VALUES ('id_org_dept', '组织架构_部门', 'orgDept:menu', 'id_sys', '', null, '1', null, '0', '2015-10-04 19:42:26', null);
INSERT INTO `auth_perm` VALUES ('id_org_dept_add', '新增', 'orgDept:add', 'id_org_dept', '', null, '0', null, '0', '2015-10-04 19:46:07', null);
INSERT INTO `auth_perm` VALUES ('id_org_dept_del', '删除', 'orgDept:del', 'id_org_dept', '', null, '0', null, '0', '2015-10-04 19:46:55', null);
INSERT INTO `auth_perm` VALUES ('id_org_dept_edit', '编辑', 'orgDept:edit', 'id_org_dept', '', null, '0', null, '0', '2015-10-04 19:46:27', null);
INSERT INTO `auth_perm` VALUES ('id_org_user', '组织架构_用户', 'orgUser:menu', 'id_sys', '', null, '2', null, '0', '2015-10-04 19:42:55', null);
INSERT INTO `auth_perm` VALUES ('id_org_user_add', '新增', 'orgUser:add', 'id_org_user', '', null, '0', null, '0', '2015-10-07 14:42:06', null);
INSERT INTO `auth_perm` VALUES ('id_org_user_del', '删除', 'orgUser:del', 'id_org_user', '', null, '1', null, '0', '2015-10-07 14:42:36', null);
INSERT INTO `auth_perm` VALUES ('id_org_user_dept_edit', '用户部门更改', 'orgUser:dept.edit', 'id_org_user', '', null, '0', null, '0', '2015-10-07 14:43:19', null);
INSERT INTO `auth_perm` VALUES ('id_org_user_edit', '编辑', 'orgUser:edit', 'id_org_user', '', null, '0', null, '0', '2015-10-07 14:42:23', null);
INSERT INTO `auth_perm` VALUES ('id_org_user_role_edit', '用户角色更改', 'orgUser:role.edit', 'id_org_user', '', null, '0', null, '0', '2015-10-07 14:43:42', null);
INSERT INTO `auth_perm` VALUES ('id_pano', '全景管理', 'pano:menu', null, null, null, '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category', '全景_类目', 'panoCat:menu', 'id_pano', '系统生成', '0', '0', '', '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_add', '全景_类目_新增', 'panoCat:add', 'id_pano_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_del', '全景_类目_逻辑删除', 'panoCat:del', 'id_pano_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_edit', '全景_类目_编辑', 'panoCat:edit', 'id_pano_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_save', '全景_类目_保存', 'panoCat:save', 'id_pano_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment', '全景_评论', 'panoComment:menu', 'id_pano', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_add', '全景_评论_新增', 'panoComment:add', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_del', '全景_评论_逻辑删除', 'panoComment:del', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_edit', '全景_评论_编辑', 'panoComment:edit', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_save', '全景_评论_保存', 'panoComment:save', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj', '全景_项目', 'panoProj:menu', 'id_pano', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_add', '全景_项目_新增', 'panoProj:add', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_del', '全景_项目_逻辑删除', 'panoProj:del', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_edit', '全景_项目_编辑', 'panoProj:edit', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_save', '全景_项目_保存', 'panoProj:save', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys', '系统管理', 'sys:menu', null, '', null, '5', null, '0', '2015-10-04 19:41:25', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic', '系统_数据字典', 'sysDic:menu', 'id_sys', '', null, '0', null, '0', '2015-10-04 19:45:22', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_add', '新增', 'sysDic:add', 'id_sys_dic', '', null, '0', null, '0', '2015-10-07 14:40:53', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_del', '删除', 'sysDic:del', 'id_sys_dic', '', null, '1', null, '0', '2015-10-07 14:41:25', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_edit', '编辑', 'sysDic:edit', 'id_sys_dic', '', null, '2', null, '0', '2015-10-07 14:41:10', null);
INSERT INTO `auth_perm` VALUES ('id_sys_log', '系统_操作日志', 'sysLog:menu', 'id_sys', '', null, '3', null, '0', '2015-10-04 19:44:30', null);
INSERT INTO `auth_perm` VALUES ('id_video', '全景_视频', 'video:menu', 'id_pano', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_video_add', '全景_视频_新增', 'video:add', 'id_video', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_video_del', '全景_视频_逻辑删除', 'video:del', 'id_video', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_video_edit', '全景_视频_编辑', 'video:edit', 'id_video', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_video_save', '全景_视频_保存', 'video:save', 'id_video', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

-- ----------------------------
-- Table structure for `auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `is_super` tinyint(1) DEFAULT '0' COMMENT '超级管理员0否1是',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限_角色信息表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', '超时管理员', '1', null, '0', '3', null, '0', '2000-01-01 00:00:00', '2017-02-25 23:44:56');

-- ----------------------------
-- Table structure for `auth_role_vs_category`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_vs_category`;
CREATE TABLE `auth_role_vs_category` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `category_id` bigint(20) NOT NULL COMMENT '类目id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_角色vs资讯类目';

-- ----------------------------
-- Records of auth_role_vs_category
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_role_vs_perm`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_vs_perm`;
CREATE TABLE `auth_role_vs_perm` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `perm_id` varchar(32) NOT NULL COMMENT '权限id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_角色vs权限';

-- ----------------------------
-- Records of auth_role_vs_perm
-- ----------------------------

-- ----------------------------
-- Table structure for `auth_user_vs_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_vs_role`;
CREATE TABLE `auth_user_vs_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_用户vs角色';

-- ----------------------------
-- Records of auth_user_vs_role
-- ----------------------------
INSERT INTO `auth_user_vs_role` VALUES ('1', '1', '2000-01-01 00:00:00');

-- ----------------------------
-- Table structure for `org_dept`
-- ----------------------------
DROP TABLE IF EXISTS `org_dept`;
CREATE TABLE `org_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构_部门';

-- ----------------------------
-- Records of org_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `org_dept_vs_user`
-- ----------------------------
DROP TABLE IF EXISTS `org_dept_vs_user`;
CREATE TABLE `org_dept_vs_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构_部门vs用户';

-- ----------------------------
-- Records of org_dept_vs_user
-- ----------------------------

-- ----------------------------
-- Table structure for `org_user`
-- ----------------------------
DROP TABLE IF EXISTS `org_user`;
CREATE TABLE `org_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `accid` varchar(55) NOT NULL COMMENT '员工账号',
  `pwd` varchar(64) DEFAULT NULL COMMENT '员工密码',
  `name` varchar(55) DEFAULT NULL COMMENT '成员名称',
  `job_no` varchar(32) DEFAULT NULL COMMENT '工号',
  `position` varchar(32) DEFAULT NULL COMMENT '职位',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别[0男1女3保密]',
  `mobile` varchar(24) DEFAULT NULL COMMENT '移动电话',
  `tel` varchar(24) DEFAULT NULL COMMENT '固定电话',
  `enable` tinyint(1) DEFAULT '1' COMMENT '启用状态1启用0禁用',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `weixinid` varchar(64) DEFAULT NULL COMMENT '微信id',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '会员类型0管理员1普通用户',
  `last_login` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '最后登录日期',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `state` tinyint(1) DEFAULT '0' COMMENT '状态',
  `skin` varchar(100) DEFAULT 'default' COMMENT '皮肤',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='组织架构_用户';

-- ----------------------------
-- Records of org_user
-- ----------------------------
INSERT INTO `org_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, null, null, null, null, null, '1', null, null, null, '0', '2000-01-01 00:00:00', '0', '0', 'default', null, null, '0', '0', '0', null, '2000-01-01 00:00:00', '2000-01-01 00:00:00');

-- ----------------------------
-- Table structure for `pano_comments`
-- ----------------------------
DROP TABLE IF EXISTS `pano_comments`;
CREATE TABLE `pano_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scene_id` varchar(38) NOT NULL COMMENT '场景id',
  `content` varchar(55) DEFAULT NULL COMMENT '内容',
  `ath` varchar(20) DEFAULT NULL COMMENT '水平坐标',
  `atv` varchar(20) DEFAULT NULL COMMENT '垂直坐标',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(55) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `province` varchar(24) DEFAULT NULL COMMENT '省份',
  `city` varchar(24) DEFAULT NULL COMMENT '市区',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景_评论';

-- ----------------------------
-- Records of pano_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `pano_map`
-- ----------------------------
DROP TABLE IF EXISTS `pano_map`;
CREATE TABLE `pano_map` (
  `proj_id` bigint(20) NOT NULL COMMENT '项目id',
  `scene_id` varchar(38) NOT NULL COMMENT '场景id',
  `rotate` varchar(20) DEFAULT '0' COMMENT '雷达旋转角度',
  `x` varchar(20) NOT NULL COMMENT '导览图坐标x',
  `y` varchar(20) NOT NULL COMMENT '导览图坐标y',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`proj_id`,`scene_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_导览图';

-- ----------------------------
-- Records of pano_map
-- ----------------------------

-- ----------------------------
-- Table structure for `pano_material`
-- ----------------------------
DROP TABLE IF EXISTS `pano_material`;
CREATE TABLE `pano_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(55) NOT NULL COMMENT '素材名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '素材类型(0图片1音乐2视频)',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '封面地址',
  `material_url` varchar(255) DEFAULT NULL COMMENT '素材地址',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_素材';

-- ----------------------------
-- Records of pano_material
-- ----------------------------

-- ----------------------------
-- Table structure for `pano_proj`
-- ----------------------------
DROP TABLE IF EXISTS `pano_proj`;
CREATE TABLE `pano_proj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` bigint(20) NOT NULL COMMENT '类目id',
  `type` tinyint(1) NOT NULL COMMENT '项目类型0图片1视频',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sound_src` varchar(255) DEFAULT NULL COMMENT '背景音乐',
  `is_comments` tinyint(1) DEFAULT '0' COMMENT '开放评论 0否1是',
  `is_planetoid` tinyint(1) DEFAULT '0' COMMENT '小行星开场 0否1是',
  `map_src` varchar(255) DEFAULT NULL COMMENT '导览图',
  `video_src` varchar(255) DEFAULT NULL COMMENT '视频解说',
  `is_seccuss` tinyint(1) DEFAULT '0' COMMENT '全景生成标记0否1是',
  `thumbs_up_num` int(11) DEFAULT '0' COMMENT '点赞数量',
  `pv_num` int(11) DEFAULT '0' COMMENT '浏览量',
  `logo_pic_url` varchar(255) DEFAULT NULL COMMENT 'logo图片',
  `logo_web_url` varchar(255) DEFAULT NULL COMMENT 'logo链接',
  `pwd` varchar(6) DEFAULT NULL COMMENT '访问密码',
  `tour_edit_json` text COMMENT '漫游编辑json',
  `ext_cfg_json` varchar(4000) DEFAULT NULL COMMENT '扩展配置json',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_项目';

-- ----------------------------
-- Records of pano_proj
-- ----------------------------

-- ----------------------------
-- Table structure for `pano_scene`
-- ----------------------------
DROP TABLE IF EXISTS `pano_scene`;
CREATE TABLE `pano_scene` (
  `id` varchar(38) NOT NULL COMMENT 'id',
  `proj_id` bigint(20) NOT NULL COMMENT '项目id',
  `scene_title` varchar(50) NOT NULL COMMENT '场景名称',
  `scene_src` varchar(255) NOT NULL COMMENT '场景图/全景视频',
  `hlookat` varchar(20) DEFAULT NULL COMMENT '水平视角',
  `vlookat` varchar(20) DEFAULT NULL COMMENT '垂直视角',
  `ext_cfg_json` varchar(4000) DEFAULT NULL COMMENT '扩展配置json',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景';

-- ----------------------------
-- Records of pano_scene
-- ----------------------------

-- ----------------------------
-- Table structure for `pano_spots`
-- ----------------------------
DROP TABLE IF EXISTS `pano_spots`;
CREATE TABLE `pano_spots` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `proj_id` bigint(20) NOT NULL COMMENT '项目id',
  `scene_id` varchar(38) NOT NULL COMMENT '场景id',
  `htype` tinyint(2) NOT NULL DEFAULT '0' COMMENT '类型0热点1图片2外部链接3图文介绍4语音热点',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `detail_info` longtext COMMENT '图文详情',
  `hname` varchar(255) NOT NULL COMMENT '热点编号',
  `ath` varchar(32) NOT NULL COMMENT '水平坐标',
  `atv` varchar(32) NOT NULL COMMENT '垂直坐标',
  `linkedscene` varchar(32) DEFAULT NULL COMMENT '关联场景',
  `scale` varchar(32) DEFAULT NULL COMMENT '缩放比',
  `depth` varchar(32) DEFAULT NULL COMMENT '纵深',
  `rotate` varchar(32) DEFAULT NULL COMMENT '旋转角度',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `is_onclick` tinyint(1) DEFAULT NULL COMMENT '可否点击',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景_热点';

-- ----------------------------
-- Records of pano_spots
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_advertise`
-- ----------------------------
DROP TABLE IF EXISTS `sys_advertise`;
CREATE TABLE `sys_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `link_url` varchar(255) DEFAULT NULL COMMENT '广告链接地址',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标记',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT NULL COMMENT '创建时间',
  `date_updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告管理';

-- ----------------------------
-- Records of sys_advertise
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_category`
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(55) NOT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_类目';

-- ----------------------------
-- Records of sys_category
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_message`
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `send_id` bigint(20) DEFAULT NULL COMMENT '发件人id[0代表管理员]',
  `accept_id` bigint(20) DEFAULT NULL COMMENT '收件人id[0代表所有人]',
  `title` varchar(255) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '消息内容',
  `state` tinyint(4) DEFAULT NULL COMMENT '是否已读0未读1已读',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_站内信';

-- ----------------------------
-- Records of sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_news`
-- ----------------------------
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE `sys_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(55) NOT NULL COMMENT '标题',
  `second_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `cover_pic` varchar(255) DEFAULT NULL COMMENT '标题图',
  `brief_info` varchar(255) DEFAULT NULL COMMENT '简介',
  `detail_info` longtext COMMENT '详情',
  `is_ontop` tinyint(1) DEFAULT '0' COMMENT '置顶',
  `click_count` int(11) DEFAULT '0' COMMENT '点击量',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_资讯';

-- ----------------------------
-- Records of sys_news
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_news_vs_category`
-- ----------------------------
DROP TABLE IF EXISTS `sys_news_vs_category`;
CREATE TABLE `sys_news_vs_category` (
  `news_id` bigint(20) NOT NULL COMMENT '资讯id',
  `category_id` bigint(20) NOT NULL COMMENT '类目id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`news_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_资讯vs类目';

-- ----------------------------
-- Records of sys_news_vs_category
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_pano_plugins`
-- ----------------------------
DROP TABLE IF EXISTS `sys_pano_plugins`;
CREATE TABLE `sys_pano_plugins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `plugin_src` varchar(255) NOT NULL COMMENT '插件地址',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_全景插件';

-- ----------------------------
-- Records of sys_pano_plugins
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `memo` varchar(50) NOT NULL COMMENT '描述',
  `detail_info` text COMMENT '对象信息',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '操作时间',
  `create_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `create_name` varchar(64) DEFAULT NULL COMMENT '操作人姓名',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '操作人IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_管理员操作日志';

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_variable`
-- ----------------------------
DROP TABLE IF EXISTS `sys_variable`;
CREATE TABLE `sys_variable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) NOT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_数据字典表';

-- ----------------------------
-- Records of sys_variable
-- ----------------------------

-- ----------------------------
-- Table structure for `vip_goods`
-- ----------------------------
DROP TABLE IF EXISTS `vip_goods`;
CREATE TABLE `vip_goods` (
  `vip_code` varchar(32) NOT NULL COMMENT 'VIP等级编码',
  `months` int(3) NOT NULL DEFAULT '1' COMMENT '时长/月',
  `goods_price` decimal(11,2) NOT NULL COMMENT '商品价格',
  `pay_price` decimal(11,2) NOT NULL COMMENT '应付价格',
  `state` tinyint(1) NOT NULL DEFAULT '-1' COMMENT '状态',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标识：0正常  1删除',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`months`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIP_套餐商品';

-- ----------------------------
-- Records of vip_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `vip_logs`
-- ----------------------------
DROP TABLE IF EXISTS `vip_logs`;
CREATE TABLE `vip_logs` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '会员id',
  `vip_code` varchar(32) NOT NULL COMMENT 'VIP等级编码',
  `vip_change_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '变更类型1升级0过期',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIP_会员等级变更日志';

-- ----------------------------
-- Records of vip_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `vip_orders`
-- ----------------------------
DROP TABLE IF EXISTS `vip_orders`;
CREATE TABLE `vip_orders` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `code` varchar(32) NOT NULL COMMENT '订单编码',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `vip_code` varchar(32) NOT NULL COMMENT 'VIP等级编码',
  `months` int(3) NOT NULL DEFAULT '1' COMMENT '购买月份数',
  `order_price` decimal(11,2) NOT NULL COMMENT '订单金额',
  `pay_price` decimal(11,2) NOT NULL COMMENT '应付金额',
  `state` tinyint(1) NOT NULL DEFAULT '-1' COMMENT '订单状态-1待支付1已完成99超时',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标识：0正常  1删除',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIP_订单表';

-- ----------------------------
-- Records of vip_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `vip_user`
-- ----------------------------
DROP TABLE IF EXISTS `vip_user`;
CREATE TABLE `vip_user` (
  `user_id` bigint(20) NOT NULL COMMENT '会员id',
  `vip_code` varchar(32) NOT NULL COMMENT 'VIP等级编码',
  `vip_time_out` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'VIP过期时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态1启用0禁用',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIP_用户信息';

-- ----------------------------
-- Records of vip_user
-- ----------------------------

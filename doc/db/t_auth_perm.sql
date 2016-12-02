/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : krpano

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-12-02 23:32:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_auth_perm`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_perm`;
CREATE TABLE `t_auth_perm` (
  `id` varchar(22) NOT NULL COMMENT '权限id',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `match_str` varchar(50) NOT NULL COMMENT '权限匹配符',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(50) DEFAULT NULL COMMENT '修改者IP',
  `parentid` varchar(22) DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_权限信息表';

-- ----------------------------
-- Records of t_auth_perm
-- ----------------------------
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm', '权限_权限信息', 'authPerm:menu', '', null, '1', null, '1', '2015-10-04 19:43:29', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_add', '新增', 'authPerm:add', '', null, '1', null, '1', '2015-10-07 14:44:59', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_del', '删除', 'authPerm:del', '', null, '1', null, '1', '2015-10-07 14:45:26', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_edit', '编辑', 'authPerm:edit', '', null, '1', null, '1', '2015-10-07 14:45:13', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role', '权限_角色信息', 'authRole:menu', '', null, '1', null, '0', '2015-10-04 19:43:56', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role_add', '新增', 'authRole:add', '', null, '3', null, '0', '2015-10-07 14:39:03', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_role');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role_del', '删除', 'authRole:del', '', null, '2', null, '0', '2015-10-07 14:38:45', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_role');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role_edit', '编辑', 'authRole:edit', '', null, '2', null, '0', '2015-10-07 14:38:29', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_role');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role_parm', '角色权限修改', 'authRole:parm', '', null, '3', null, '0', '2015-10-07 14:39:25', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_role');
INSERT INTO `t_auth_perm` VALUES ('id_auth_role_super', '设置超级管理员', 'authRole:super', '', null, '2', '', '0', '2015-10-11 23:45:49', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', '2016-11-26 13:05:57', null, null, 'id_auth_role');
INSERT INTO `t_auth_perm` VALUES ('id_org_dept', '组织架构_部门', 'orgDept:menu', '', null, '1', null, '0', '2015-10-04 19:42:26', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_org_dept_add', '新增', 'orgDept:add', '', null, '0', null, '0', '2015-10-04 19:46:07', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_dept');
INSERT INTO `t_auth_perm` VALUES ('id_org_dept_del', '删除', 'orgDept:del', '', null, '0', null, '0', '2015-10-04 19:46:55', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_dept');
INSERT INTO `t_auth_perm` VALUES ('id_org_dept_edit', '编辑', 'orgDept:edit', '', null, '0', null, '0', '2015-10-04 19:46:27', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_dept');
INSERT INTO `t_auth_perm` VALUES ('id_org_user', '组织架构_用户', 'orgUser:menu', '', null, '2', null, '0', '2015-10-04 19:42:55', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_org_user_add', '新增', 'orgUser:add', '', null, '0', null, '0', '2015-10-07 14:42:06', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_user');
INSERT INTO `t_auth_perm` VALUES ('id_org_user_del', '删除', 'orgUser:del', '', null, '1', null, '1', '2015-10-07 14:42:36', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_org_user');
INSERT INTO `t_auth_perm` VALUES ('id_org_user_dept_edit', '用户部门更改', 'orgUser:dept.edit', '', null, '0', null, '0', '2015-10-07 14:43:19', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_user');
INSERT INTO `t_auth_perm` VALUES ('id_org_user_edit', '编辑', 'orgUser:edit', '', null, '0', null, '0', '2015-10-07 14:42:23', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_user');
INSERT INTO `t_auth_perm` VALUES ('id_org_user_role_edit', '用户角色更改', 'orgUser:role.edit', '', null, '0', null, '0', '2015-10-07 14:43:42', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_org_user');
INSERT INTO `t_auth_perm` VALUES ('id_pano', '全景管理', 'pano:menu', null, null, '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, null);
INSERT INTO `t_auth_perm` VALUES ('id_pano_category', '全景_类目', 'panoCat:menu', '系统生成', '0', '0', '', '0', '2000-01-01 00:00:00', '', '', '2000-01-01 00:00:00', '', '', 'id_pano');
INSERT INTO `t_auth_perm` VALUES ('id_pano_category_add', '全景_类目_新增', 'panoCat:add', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_category');
INSERT INTO `t_auth_perm` VALUES ('id_pano_category_del', '全景_类目_逻辑删除', 'panoCat:del', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_category');
INSERT INTO `t_auth_perm` VALUES ('id_pano_category_edit', '全景_类目_编辑', 'panoCat:edit', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_category');
INSERT INTO `t_auth_perm` VALUES ('id_pano_category_save', '全景_类目_保存', 'panoCat:save', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_category');
INSERT INTO `t_auth_perm` VALUES ('id_pano_comment', '全景_评论', 'panoComment:menu', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano');
INSERT INTO `t_auth_perm` VALUES ('id_pano_comment_add', '全景_评论_新增', 'panoComment:add', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_comment');
INSERT INTO `t_auth_perm` VALUES ('id_pano_comment_del', '全景_评论_逻辑删除', 'panoComment:del', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_comment');
INSERT INTO `t_auth_perm` VALUES ('id_pano_comment_edit', '全景_评论_编辑', 'panoComment:edit', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_comment');
INSERT INTO `t_auth_perm` VALUES ('id_pano_comment_save', '全景_评论_保存', 'panoComment:save', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_comment');
INSERT INTO `t_auth_perm` VALUES ('id_pano_proj', '全景_项目', 'panoProj:menu', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano');
INSERT INTO `t_auth_perm` VALUES ('id_pano_proj_add', '全景_项目_新增', 'panoProj:add', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_proj');
INSERT INTO `t_auth_perm` VALUES ('id_pano_proj_del', '全景_项目_逻辑删除', 'panoProj:del', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_proj');
INSERT INTO `t_auth_perm` VALUES ('id_pano_proj_edit', '全景_项目_编辑', 'panoProj:edit', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_proj');
INSERT INTO `t_auth_perm` VALUES ('id_pano_proj_save', '全景_项目_保存', 'panoProj:save', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano_proj');
INSERT INTO `t_auth_perm` VALUES ('id_sys', '系统管理', 'sys:menu', '', null, '5', null, '0', '2015-10-04 19:41:25', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null);
INSERT INTO `t_auth_perm` VALUES ('id_sys_dic', '系统_数据字典', 'sysDic:menu', '', null, '0', null, '0', '2015-10-04 19:45:22', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_sys_dic_add', '新增', 'sysDic:add', '', null, '0', null, '0', '2015-10-07 14:40:53', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_sys_dic');
INSERT INTO `t_auth_perm` VALUES ('id_sys_dic_del', '删除', 'sysDic:del', '', null, '1', null, '1', '2015-10-07 14:41:25', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_sys_dic');
INSERT INTO `t_auth_perm` VALUES ('id_sys_dic_edit', '编辑', 'sysDic:edit', '', null, '2', null, '0', '2015-10-07 14:41:10', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '116.231.222.193', 'id_sys_dic');
INSERT INTO `t_auth_perm` VALUES ('id_sys_log', '系统_操作日志', 'sysLog:menu', '', null, '3', null, '0', '2015-10-04 19:44:30', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_video', '全景_视频', 'video:menu', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_pano');
INSERT INTO `t_auth_perm` VALUES ('id_video_add', '全景_视频_新增', 'video:add', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_video');
INSERT INTO `t_auth_perm` VALUES ('id_video_del', '全景_视频_逻辑删除', 'video:del', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_video');
INSERT INTO `t_auth_perm` VALUES ('id_video_edit', '全景_视频_编辑', 'video:edit', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_video');
INSERT INTO `t_auth_perm` VALUES ('id_video_save', '全景_视频_保存', 'video:save', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', null, null, '2000-01-01 00:00:00', null, null, 'id_video');

-- ----------------------------
-- Table structure for `t_auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role` (
  `id` varchar(22) NOT NULL COMMENT '角色ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `is_super` char(1) DEFAULT '0' COMMENT '超级管理员0否1是',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(50) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_角色信息表';

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
INSERT INTO `t_auth_role` VALUES ('f2585c0b556240b3b706b4', '超级管理员 ', '1', null, null, '2', null, '0', '2015-10-01 12:35:33', null, null, null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `t_auth_role_vs_perm`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_vs_perm`;
CREATE TABLE `t_auth_role_vs_perm` (
  `role_id` varchar(22) NOT NULL COMMENT '角色id',
  `perm_id` varchar(22) NOT NULL COMMENT '权限id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '建立者IP',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_角色vs权限';

-- ----------------------------
-- Records of t_auth_role_vs_perm
-- ----------------------------

-- ----------------------------
-- Table structure for `t_auth_user_vs_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user_vs_role`;
CREATE TABLE `t_auth_user_vs_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(22) NOT NULL COMMENT '角色id',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '建立者IP',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_用户vs角色';

-- ----------------------------
-- Records of t_auth_user_vs_role
-- ----------------------------
INSERT INTO `t_auth_user_vs_role` VALUES ('4d868ddfd70342b093e3013886e00ea9', 'f2585c0b556240b3b706b4', '2016-09-12 00:00:00', '', null);

-- ----------------------------
-- Table structure for `t_org_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_org_department`;
CREATE TABLE `t_org_department` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `parentid` varchar(100) DEFAULT NULL COMMENT '父级ID',
  `level` int(10) DEFAULT NULL COMMENT '级别',
  `p_code` varchar(1000) DEFAULT NULL COMMENT '上下级组合编码',
  `state` char(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0否1是)',
  `invalid_time` bigint(20) DEFAULT '0' COMMENT '数据过期时间0:永不过期',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(64) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构_部门';

-- ----------------------------
-- Records of t_org_department
-- ----------------------------
INSERT INTO `t_org_department` VALUES ('8c21d5118bc842a980decd5a44618aa0', '0', '系统管理', 'System', '', null, null, null, '', null, null, '0', null, '2015-10-20 11:59:27', '4d868ddfd70342b093e3013886e00ea9', '101.230.202.114', null, '4d868ddfd70342b093e3013886e00ea9', '101.230.202.114');

-- ----------------------------
-- Table structure for `t_org_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_org_user`;
CREATE TABLE `t_org_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `userid` varchar(64) NOT NULL COMMENT '员工UserID',
  `pwd` varchar(64) DEFAULT NULL COMMENT '员工密码',
  `name` varchar(100) DEFAULT NULL COMMENT '成员名称',
  `job_no` varchar(64) DEFAULT NULL COMMENT '工号',
  `position` varchar(64) DEFAULT NULL COMMENT '职位',
  `gender` char(1) DEFAULT NULL COMMENT '性别[0男1女]',
  `mobile` varchar(50) DEFAULT NULL COMMENT '移动电话',
  `tel` varchar(50) DEFAULT NULL COMMENT '固定电话',
  `enable` char(1) DEFAULT '1' COMMENT '是否启用启用/禁用成员。1表示启用成员，0表示禁用成员',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url。注：小图将url最后的"/0"改成"/64"',
  `status` varchar(50) DEFAULT '1' COMMENT '关注状态: 1=已关注，2=已冻结，4=未关注',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `weixinid` varchar(64) DEFAULT NULL COMMENT '微信id',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '会员类型0管理员1普通用户',
  `last_login` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '最后登录日期',
  `count` int(11) NOT NULL COMMENT '登录次数',
  `state` char(1) DEFAULT '0' COMMENT '状态',
  `skin` varchar(100) DEFAULT 'default' COMMENT '皮肤',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `keyword` varchar(100) DEFAULT NULL COMMENT '关键字',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0否1是)',
  `invalid_time` bigint(20) DEFAULT '0' COMMENT '数据过期时间0:永不过期',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(64) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构_用户';

-- ----------------------------
-- Records of t_org_user
-- ----------------------------
INSERT INTO `t_org_user` VALUES ('4d868ddfd70342b093e3013886e00ea9', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', 'starr2015', null, null, '1', '', '0', null, null, '', null, '0', '2015-10-01 11:14:47', '1', null, null, '', null, '38', null, '0', null, '2015-10-01 11:14:49', null, null, null, '4d868ddfd70342b093e3013886e00ea9', '180.168.131.12');

-- ----------------------------
-- Table structure for `t_org_user_vs_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_org_user_vs_department`;
CREATE TABLE `t_org_user_vs_department` (
  `userid` varchar(64) NOT NULL COMMENT '用户id',
  `departmentid` varchar(64) NOT NULL COMMENT '部门id',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  PRIMARY KEY (`userid`,`departmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构_用户vs部门';

-- ----------------------------
-- Records of t_org_user_vs_department
-- ----------------------------

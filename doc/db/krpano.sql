/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : krpano

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-12-05 09:50:19
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
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm', '权限_权限信息', 'authPerm:menu', '', null, '1', null, '0', '2015-10-04 19:43:29', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_sys');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_add', '新增', 'authPerm:add', '', null, '1', null, '0', '2015-10-07 14:44:59', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_del', '删除', 'authPerm:del', '', null, '1', null, '0', '2015-10-07 14:45:26', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
INSERT INTO `t_auth_perm` VALUES ('id_auth_perm_edit', '编辑', 'authPerm:edit', '', null, '1', null, '0', '2015-10-07 14:45:13', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_auth_perm');
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
INSERT INTO `t_auth_perm` VALUES ('id_org_user_del', '删除', 'orgUser:del', '', null, '1', null, '0', '2015-10-07 14:42:36', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_org_user');
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
INSERT INTO `t_auth_perm` VALUES ('id_sys_dic_del', '删除', 'sysDic:del', '', null, '1', null, '0', '2015-10-07 14:41:25', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, null, null, 'id_sys_dic');
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
INSERT INTO `t_org_department` VALUES ('1c32f015323c4328847230580bbc5647', '0', '22', '22', 'f6ac5953346b428dbea4cc0ae0f22363', null, null, null, '', null, null, '0', null, '2016-12-04 10:05:11', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_org_department` VALUES ('3279395a50e646ba85c76238fa45d630', '0', 'aa', 'aa', '', null, null, null, '', null, null, '0', null, '2016-12-04 10:18:38', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_org_department` VALUES ('4954ab1b68d14b1cabdef20ccf1c7be8', '0', '33', '33', '1c32f015323c4328847230580bbc5647', null, null, null, '', null, null, '0', null, '2016-12-04 10:05:35', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_org_department` VALUES ('8c21d5118bc842a980decd5a44618aa0', '0', '系统管理', 'System', '', null, null, null, '', null, null, '0', null, '2015-10-20 11:59:27', '4d868ddfd70342b093e3013886e00ea9', '101.230.202.114', null, '4d868ddfd70342b093e3013886e00ea9', '101.230.202.114');
INSERT INTO `t_org_department` VALUES ('b60dd7c83a4c4b688006851e75da229f', '0', '222', '222', 'f6ac5953346b428dbea4cc0ae0f22363', null, null, null, '', null, null, '0', null, '2016-12-04 10:33:59', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_org_department` VALUES ('ca7cac48f165485f80eb8c9238b20fbf', '1', 'BB', 'BB', '', null, null, null, '', null, null, '1', null, '2016-12-04 10:19:01', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_org_department` VALUES ('f6ac5953346b428dbea4cc0ae0f22363', '1', '11', '1', '8c21d5118bc842a980decd5a44618aa0', null, null, null, '', null, null, '0', null, '2016-12-04 10:05:02', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1', null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');

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
INSERT INTO `t_org_user` VALUES ('4d868ddfd70342b093e3013886e00ea9', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', 'starr2015', null, null, '15021522231', '', '0', null, null, '', null, '0', '2015-10-01 11:14:47', '1', null, null, '', null, '39', null, '0', null, '2015-10-01 11:14:49', null, null, null, '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');

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
INSERT INTO `t_org_user_vs_department` VALUES ('4d868ddfd70342b093e3013886e00ea9', '8c21d5118bc842a980decd5a44618aa0', '0', '2016-12-04 14:53:41', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `t_pano_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_category`;
CREATE TABLE `t_pano_category` (
  `id` varchar(22) NOT NULL COMMENT 'ID',
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
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(64) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_类目';

-- ----------------------------
-- Records of t_pano_category
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pano_comments`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_comments`;
CREATE TABLE `t_pano_comments` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `scene_id` varchar(32) NOT NULL COMMENT '场景id',
  `sname` varchar(255) DEFAULT NULL COMMENT 'sname',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `img` varchar(255) DEFAULT NULL COMMENT '头像',
  `ath` varchar(20) DEFAULT NULL COMMENT '水平坐标',
  `atv` varchar(20) DEFAULT NULL COMMENT '垂直坐标',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '市区',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景_评论';

-- ----------------------------
-- Records of t_pano_comments
-- ----------------------------
INSERT INTO `t_pano_comments` VALUES ('0a9f54c20dce490d8686fd250695edb8', 'o_1b2b9pvmu5m9av62bv17lpeh01g', null, '666', null, '97.48431272376376', '11.837248329801806', null, null, null, null, '1', null, null, null, null, '1', null, '2016-11-26 14:40:35', null, '118.112.141.94', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('0d874cd7a8c84386ae0d63b240d9ae51', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '222222', null, '18.358121231930454', '34.37532389756509', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:26:57', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('1c3b7916e4d740228801848e8458516e', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '不错', null, '-31.203038095593115', '37.09340783342628', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-14 13:55:48', null, '59.63.248.55', null, null, '59.63.248.55');
INSERT INTO `t_pano_comments` VALUES ('3c91948f0d224f58a01221c99d9e085d', 'o_1au9lfbvo2751eraquf4jisq02n', null, '广告歌g', null, '78.3816476761494', '13.147800136783621', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-05 23:00:08', null, '223.73.138.237', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('51644b6c13e646179f095c76ac3dac5e', 'o_1b2dspg6oau9hknnc812s5191p1g', null, '8888', null, '0.6141666666666665', '0', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-25 22:25:27', null, '139.227.84.240', null, null, '139.227.84.240');
INSERT INTO `t_pano_comments` VALUES ('5496fa2602124a33a8cbbfbe52e87267', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'zzzz', null, '714.4069401920966', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:31:33', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('549ba41c6df44f678d01122984f4211f', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'ggg', null, '714.4051392218502', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:31:24', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('54c3767527914dca808756f2b72f7943', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '11111', null, '714.4069401920966', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:31:37', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('5e4ddceca5ec45e3a95218104580b72b', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '333333', null, '714.4033382516037', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:31:15', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('5f9a192f028b4d7995f0a07641cc50f8', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'qqq', null, '8.733269625440414', '28.155359083477997', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:26:36', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('6a3a275b1dfc45c5b243b2d432c2540f', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '123213', null, '715.6224804381758', '39.24949883475766', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-19 23:28:44', null, '1.205.94.112', null, null, '1.205.94.112');
INSERT INTO `t_pano_comments` VALUES ('79100e3af56347e3810482ab13a2f9cf', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'sdfs ', null, '938.7764127186009', '16.764490063456183', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-05 15:23:40', null, '114.88.221.227', null, null, '114.88.221.227');
INSERT INTO `t_pano_comments` VALUES ('7ca3c042005444a1a6bbb757aac3025f', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, '有意境', null, '-49.851421274038785', '-11.494255104738252', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-09 22:24:25', null, '171.217.40.64', null, null, '171.217.40.64');
INSERT INTO `t_pano_comments` VALUES ('7cbde507b2d244cc8d44de32a00e8bfd', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '这棵树好直 上面也没被刻字', null, '52.055862116448', '49.62324051706355', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-05 15:19:36', null, '171.217.40.64', null, null, '171.217.40.64');
INSERT INTO `t_pano_comments` VALUES ('858dc66d3fd54744aac70e1e79edc85e', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '不錯', null, '728.1062719808356', '31.29729977679121', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-13 16:55:57', null, '218.17.158.51', null, null, '218.17.158.51');
INSERT INTO `t_pano_comments` VALUES ('8d08c03268fe48a49114db682f8a5c7b', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, '打手', null, '-126.53860582920149', '-0.3947922411966008', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-05 14:50:58', null, '171.217.40.64', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('912aab4b6d014a92999593fc931b4950', 'o_1b2b9pvmu5m9av62bv17lpeh01g', null, '试一试', null, '124.87704228447149', '34.00972542462908', null, null, null, null, '1', null, null, null, null, '1', null, '2016-11-26 14:57:10', null, '118.112.141.94', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('971aaac66b5540d8bfaa1717e9caea90', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'aaaaa', null, '-45.16375160633322', '20.848268264514374', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:15:59', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('ab9ccf1de98b44e98a747f22b9b40d18', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '2222', null, '714.4033382516037', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:30:18', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('abee242fcbf4411f978d72a83dafcbcb', 'o_1au9le67ggl8kd836rvnu1lma1t', null, 'gggggg', null, '714.4069401920966', '31.457410551047193', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:31:29', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('b00bf15f9e394b9fadd7552fe8342e02', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '111111', null, '-28.032569426095904', '24.08939855629243', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-08 09:26:48', null, '183.62.21.194', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('ccbd556b06854a868ca89d03ef8b21e0', 'o_1au9lfbvo2751eraquf4jisq02n', null, '萌猫', null, '162.76860815264718', '59.01213052634342', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-07 09:52:48', null, '117.136.64.74', null, null, '117.136.64.74');
INSERT INTO `t_pano_comments` VALUES ('cf03d0cfe10c4b2d96e348a9a02c65ee', 'o_1au9lfbvo2751eraquf4jisq02n', null, '怎么搞的\n', null, '112.15031818033046', '31.597377902863446', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-09 15:46:24', null, '221.221.234.213', null, null, '221.221.234.213');
INSERT INTO `t_pano_comments` VALUES ('d0deeb8532e5470d8a7ff02224f8d418', 'o_1b2dspg6oau9hknnc812s5191p1g', null, '测试', null, '112.59958615730224', '-16.37673692640311', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-25 22:37:34', null, '140.207.21.54', null, null, '140.207.21.54');
INSERT INTO `t_pano_comments` VALUES ('d4c9f007d8e74c7bbc39ce324ec257c0', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '这个很好', null, '104.3467434398726', '-37.682992691846806', null, null, null, null, '0', null, null, null, null, '0', null, '2016-10-08 09:14:43', null, '183.62.21.194', null, null, '183.62.21.194');
INSERT INTO `t_pano_comments` VALUES ('d93bb5ec538b453aa025179295b694ed', 'o_1b2b9pvmu5m9av62bv17lpeh01g', null, '444', null, '-212.02690051340363', '-24.14070613958232', null, null, null, null, '1', null, null, null, null, '1', null, '2016-11-25 12:09:27', null, '113.205.158.201', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('e4cc40fef09a4c7ebfab57e8e6f9d9b5', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '看看有没有', null, '728.3550453045442', '31.046466443457877', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-14 13:55:24', null, '59.63.248.55', null, null, '59.63.248.55');
INSERT INTO `t_pano_comments` VALUES ('e8723c866048413c8c7b7ef8b461df3c', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, '不要踩井盖', null, '74.80141006746192', '36.16283939834939', null, null, null, null, '1', null, null, null, null, '1', null, '2016-10-05 14:58:44', null, '171.217.40.64', null, null, null);
INSERT INTO `t_pano_comments` VALUES ('f1929a297bf9409687e2bf0ec922c8d4', 'o_1au9le67ggl8kd836rvnu1lma1t', null, '可以', null, '739.0287622379115', '20.372299776791127', null, null, null, null, '0', null, null, null, null, '0', null, '2016-11-13 16:59:16', null, '124.77.1.110', null, null, '124.77.1.110');

-- ----------------------------
-- Table structure for `t_pano_map`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_map`;
CREATE TABLE `t_pano_map` (
  `proj_id` varchar(32) NOT NULL COMMENT '项目id',
  `scene_id` varchar(64) NOT NULL COMMENT '场景id',
  `rotate` varchar(20) DEFAULT '0' COMMENT '雷达旋转角度',
  `x` varchar(20) NOT NULL COMMENT '导览图坐标x',
  `y` varchar(20) NOT NULL COMMENT '导览图坐标y',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`proj_id`,`scene_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_导览图';

-- ----------------------------
-- Records of t_pano_map
-- ----------------------------
INSERT INTO `t_pano_map` VALUES ('14df1d7954f5408db09e1f1519fe2769', 'o_1b2dspg6oau9hknnc812s5191p1g', '0', '195', '118', '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_map` VALUES ('14df1d7954f5408db09e1f1519fe2769', 'o_1b2dtmji1g9i43hrk311881uva1t', '0', '26', '47', '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_map` VALUES ('1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '0', '0', '0', '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_map` VALUES ('1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '0', '91', '86', '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_map` VALUES ('1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfbvo2751eraquf4jisq02n', '0', '109', '188', '0', '2016-10-21 12:30:05', null);

-- ----------------------------
-- Table structure for `t_pano_proj`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_proj`;
CREATE TABLE `t_pano_proj` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `category_id` varchar(22) NOT NULL COMMENT '类目id',
  `type` char(1) NOT NULL COMMENT '项目类型0图片1视频',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sound_src` varchar(255) DEFAULT NULL COMMENT '背景音乐',
  `is_comments` char(1) DEFAULT '0' COMMENT '开放评论 0否1是',
  `is_planetoid` char(1) DEFAULT '0' COMMENT '小行星开场 0否1是',
  `is_mending` char(1) DEFAULT '0' COMMENT '是否补地 0否1是',
  `map_src` varchar(255) DEFAULT NULL COMMENT '导览图',
  `video_src` varchar(255) DEFAULT NULL COMMENT '视频解说',
  `is_seccuss` char(1) DEFAULT '0' COMMENT '全景生成标记0否1是',
  `thumbs_up_num` int(11) DEFAULT '0' COMMENT '点赞数量',
  `pv_num` int(11) DEFAULT '0' COMMENT '浏览量',
  `xml_data` text COMMENT 'XMlDATA',
  `logo_pic` varchar(255) DEFAULT NULL COMMENT 'logo图片',
  `logo_url` varchar(255) DEFAULT NULL COMMENT 'logo链接',
  `snow_type` varchar(50) DEFAULT NULL COMMENT '下雪类型',
  `is_fps` char(1) DEFAULT '0' COMMENT '显示fps0否1是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `state` char(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0否1是)',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(64) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_项目';

-- ----------------------------
-- Records of t_pano_proj
-- ----------------------------
INSERT INTO `t_pano_proj` VALUES ('14df1d7954f5408db09e1f1519fe2769', 'testCategoryId', '0', '测试123', '/upload/file/201611/20161125222103blcog.mp3', '1', '1', '0', '/upload/image/n1/201611/20161125223003uhuzp.png', null, null, '2', '22', '{\"o_1b2dspg6oau9hknnc812s5191p1g\":{\"view\":{\"hlookat\":84.1627827916316,\"vlookat\":6.1506121514901215},\"hotspots\":[{\"ath\":-142.7106249669914,\"atv\":13.155866444837296,\"linkedscene\":\"o_1b2dtmji1g9i43hrk311881uva1t\",\"hname\":\"hotspot_1\",\"rotate\":60}],\"picspots\":[{\"ath\":580.1440925366414,\"atv\":0.05313567106326123,\"hname\":\"pic_o_1b2du6pme17uasfiuht12d1fmv7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201611/20161125222841y4mk9.png\",\"isOnclick\":\"1\"},{\"ath\":-29.77621233883974,\"atv\":-2.579619391570396,\"hname\":\"pic_o_1b2du7n021mrn9e1qhva861r9gc\",\"scale\":0.6400000000000001,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201611/201611252229114rtfp.png\",\"isOnclick\":\"1\"}]},\"o_1b2dtmji1g9i43hrk311881uva1t\":{\"view\":null,\"hotspots\":[{\"ath\":167.35630369417692,\"atv\":15.429389833384738,\"linkedscene\":\"o_1b2dspg6oau9hknnc812s5191p1g\",\"hname\":\"hotspot_2\"},{\"ath\":43.94139506549936,\"atv\":3.0408759845952433,\"linkedscene\":\"o_1b2dspg6oau9hknnc812s5191p1g\",\"hname\":\"hotspot_23\"}],\"picspots\":null}}', null, null, 'snowflakes', '1', '7', null, '', null, null, '0', '2016-11-25 22:21:14', '4d868ddfd70342b093e3013886e00ea9', '139.227.84.240', '2016-12-02 18:27:05', '4d868ddfd70342b093e3013886e00ea9', '0:0:0:0:0:0:0:1');
INSERT INTO `t_pano_proj` VALUES ('1bd1f4ce5fc94e36942511b9d1c72460', 'testCategoryId', '0', '测试全景1', '/upload/file/201610/20161019132329pyzag.mp3', '1', '1', '0', '/upload/image/n1/201610/201610052215238djwt.png', null, null, '22', '283', '{\"o_1au9le67ggl8kd836rvnu1lma1t\":{\"view\":{\"hlookat\":728.1062719808356,\"vlookat\":31.29729977679121},\"hotspots\":[{\"ath\":-10.464050655293931,\"atv\":43.76947518702347,\"linkedscene\":\"o_1au9lemun2c1i7ddp2lip15vp2a\",\"hname\":\"hotspot_1\"},{\"ath\":175.99023505346435,\"atv\":-14.676788090231978,\"linkedscene\":\"o_1au9lemun2c1i7ddp2lip15vp2a\",\"hname\":\"hotspot_12\"},{\"ath\":-87.61926596831097,\"atv\":16.172880190512732,\"linkedscene\":\"o_1au9lfbvo2751eraquf4jisq02n\",\"hname\":\"hotspot_13\"}],\"picspots\":[{\"ath\":-177.82979287424501,\"atv\":11.100583077516251,\"hname\":\"pic_o_1au9pfk3p2buo8g1fqt13ulnlc7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161005144622y22m5.jpg\"},{\"ath\":-14.06003047207355,\"atv\":60.436780909819554,\"hname\":\"pic_o_1avh6fv4i17lc10ml1e5e14u918k4m\",\"scale\":0.42666666666666675,\"depth\":2000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161020220412r6epb.jpg\",\"isOnclick\":\"1\"}]},\"o_1au9lemun2c1i7ddp2lip15vp2a\":{\"view\":{\"hlookat\":603.059051440742,\"vlookat\":16.113608692475043},\"hotspots\":[{\"ath\":-116.61019664902528,\"atv\":20.85762764713276,\"linkedscene\":\"o_1au9lfbvo2751eraquf4jisq02n\",\"hname\":\"hotspot_2\",\"rotate\":30},{\"ath\":127.93305362924667,\"atv\":3.3045367150204803,\"linkedscene\":\"o_1au9lfbvo2751eraquf4jisq02n\",\"hname\":\"hotspot_22\"},{\"ath\":-14.683031165715988,\"atv\":22.86402747932421,\"linkedscene\":\"o_1au9le67ggl8kd836rvnu1lma1t\",\"hname\":\"hotspot_23\"}],\"picspots\":[{\"ath\":-128.8064566212031,\"atv\":-2.9011755893169093,\"hname\":\"pic_o_1au9ph1iqjn4198o1rl3fju79ac\",\"scale\":0.3466666666666667,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161005144709egyn0.jpg\"},{\"ath\":-93.96776261668958,\"atv\":-0.35175489391955217,\"hname\":\"pic_o_1au9phmadspa1bd81vh9ib319hmh\",\"scale\":0.22666666666666657,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161005144730nyaqg.jpg\"}]},\"o_1au9lfbvo2751eraquf4jisq02n\":{\"view\":{\"hlookat\":78.3816476761494,\"vlookat\":13.147800136783621},\"hotspots\":[{\"ath\":-8.319036044142478,\"atv\":8.41389121487039,\"linkedscene\":\"o_1au9lemun2c1i7ddp2lip15vp2a\",\"hname\":\"hotspot_33\"},{\"ath\":-90.95089059343928,\"atv\":14.172368466176067,\"linkedscene\":\"o_1au9lemun2c1i7ddp2lip15vp2a\",\"hname\":\"hotspot_332\"}],\"picspots\":[{\"ath\":169.92830453570156,\"atv\":58.41878564113225,\"hname\":\"pic_o_1au9pkjfcr5u1n6s13s619erii2m\",\"scale\":0.6266666666666667,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161005144905zwyb3.jpg\"}]},\"o_1au9lfvkl1qpao35busk9ivkh34\":{\"view\":null,\"hotspots\":[{\"ath\":-12.621731527831116,\"atv\":-1.2375502313311614,\"linkedscene\":\"o_1au9le67ggl8kd836rvnu1lma1t\",\"hname\":\"hotspot_4\"}],\"picspots\":[{\"ath\":-100.90154006162538,\"atv\":-21.87597902472976,\"hname\":\"pic_o_1au9pliv71lsv1h20fob2qv13ohr\",\"scale\":0.10666666666666669,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161005144938l6a8o.jpg\"}]}}', null, null, 'snowballs', '1', '28', null, '', null, null, '0', '2016-10-05 13:37:26', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-11-24 21:54:37', '4d868ddfd70342b093e3013886e00ea9', '171.214.183.98');
INSERT INTO `t_pano_proj` VALUES ('1edad6e7827645beb8117f1e870ae57c', 'testCategoryId', '0', '风景画展览', '/upload/file/201610/201610092209277mpae.mp3', '1', '1', '0', null, null, null, '3', '34', '{\"o_1auks0l471ta71thm16hc5t7ldr1g\":{\"view\":null,\"hotspots\":[{\"ath\":-353.4451999558456,\"atv\":-2.286029947842085,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_2\"},{\"ath\":0.21576805028928655,\"atv\":0,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_22\"},{\"ath\":-31.778682384054093,\"atv\":-0.7873387308762468,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_23\"},{\"ath\":163.9915996831282,\"atv\":59.46939758342728,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_25\"},{\"ath\":120.2243953893666,\"atv\":21.606651767009207,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_26\"},{\"ath\":210.80914864056214,\"atv\":-14.152515000785291,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_28\"},{\"ath\":172.64099705840118,\"atv\":-41.021832607750795,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_29\"},{\"ath\":-92.17597753409592,\"atv\":14.049236735998889,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_30\"},{\"ath\":-18.02929056058366,\"atv\":-71.54466843052017,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_31\"},{\"ath\":57.03492481481334,\"atv\":-15.611770058096425,\"linkedscene\":\"o_1auks0l471ta71thm16hc5t7ldr1g\",\"hname\":\"hotspot_32\"}],\"picspots\":[{\"ath\":-89.6131840803128,\"atv\":-4.681699695270873,\"hname\":\"pic_o_1auks6kf55ttq7p3n3mukdgt7\",\"scale\":1,\"depth\":1000,\"rotate\":7.200000000000006,\"url\":\"/upload/image/n1/201610/20161009220544ykgum.jpg\"},{\"ath\":-133.12215252593395,\"atv\":-8.204119482009092,\"hname\":\"pic_o_1auks7bmb1fs213ac2bv6mqmhnc\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220608iiy9y.jpg\"},{\"ath\":-177.97737603579227,\"atv\":-8.475452443395317,\"hname\":\"pic_o_1auks7lmh8pn10u716h8ggf1dpfh\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220618avv9u.jpg\"},{\"ath\":137.15260489488057,\"atv\":-10.562902196305888,\"hname\":\"pic_o_1auks81d71p2q16vf1jb8dfc4irm\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220631627jc.jpg\"},{\"ath\":92.68394840178095,\"atv\":-9.99525943859712,\"hname\":\"pic_o_1auks8bh21qi01fnv19oh9it1nuur\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220640oshoe.jpg\"},{\"ath\":48.03674609159145,\"atv\":-8.240367974414045,\"hname\":\"pic_o_1auks8k7t1g6l1ksmb3tkdc1u8610\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220650uiay4.jpg\"},{\"ath\":3.483965804405841,\"atv\":-8.424346270068131,\"hname\":\"pic_o_1auks8qae10tl1hkg5351c261kmm15\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220655hbmkg.jpg\"},{\"ath\":-40.90971502716934,\"atv\":-4.631569846394032,\"hname\":\"pic_o_1auks95mn7or1kol1k0vdrtdjv1a\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201610/20161009220707y6v68.jpg\"}]}}', null, null, '', '0', '9', null, '', null, null, '0', '2016-10-09 22:02:50', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-24 11:17:23', '4d868ddfd70342b093e3013886e00ea9', '171.217.43.50');
INSERT INTO `t_pano_proj` VALUES ('4c08e7be27e0416a9b39c82a837463a0', 'testCategoryId', '0', 'te t', null, '1', '1', '0', null, null, null, '2', '15', null, null, null, '', '1', '1', null, '', null, null, '0', '2016-11-22 19:24:18', '4d868ddfd70342b093e3013886e00ea9', '117.80.69.94', '2016-11-24 21:53:54', '4d868ddfd70342b093e3013886e00ea9', '171.214.183.98');
INSERT INTO `t_pano_proj` VALUES ('5b25206ed3064f1ab433c896cfde51cc', 'testCategoryId', '0', '1', null, '0', '0', '0', null, null, null, '2', '5', null, null, null, '', null, '0', null, '', null, null, '0', '2016-11-19 16:59:26', '4d868ddfd70342b093e3013886e00ea9', '171.213.58.230', null, '4d868ddfd70342b093e3013886e00ea9', '171.213.58.230');
INSERT INTO `t_pano_proj` VALUES ('844464382dbb4abfba3c60fc014002bd', 'testCategoryId', '0', 'aaa', null, '0', '0', '0', null, null, null, '5', '42', '{\"o_1avdho3ee1cl81i2j109fe8gneg1g\":{\"view\":{\"hlookat\":-271.53947736750985,\"vlookat\":-56.186833458397054},\"hotspots\":[{\"ath\":-3.116256889006877,\"atv\":5.260019576254694,\"linkedscene\":\"o_1avdho3ee1cl81i2j109fe8gneg1g\",\"hname\":\"hotspot_1\"}],\"picspots\":[{\"ath\":74.95043777989872,\"atv\":-7.104755864310902,\"hname\":\"pic_o_1b13j411111ef1govr1se6msh17\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201611/20161109114636ujpo6.png\",\"isOnclick\":\"1\"},{\"ath\":32.83559872444846,\"atv\":-22.191195452428005,\"hname\":\"pic_o_1b1u9uap51iss1rohdn8i1d1eba7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201611/20161119204557p9trs.png\",\"isOnclick\":null}]}}', null, null, 'snowballs', '0', '11', null, '', null, null, '0', '2016-10-18 14:35:36', '4d868ddfd70342b093e3013886e00ea9', '183.62.21.194', '2016-11-19 20:46:15', '4d868ddfd70342b093e3013886e00ea9', '171.217.45.105');
INSERT INTO `t_pano_proj` VALUES ('bc3e851c3c1a4c8da4653fca48154451', 'testCategoryId', '1', '测试视频1', null, '0', '0', '0', null, null, null, '0', '0', null, null, null, null, '0', '2', null, '', null, null, '0', '2016-10-05 14:10:12', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-05 23:05:50', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64');
INSERT INTO `t_pano_proj` VALUES ('cf3d2796e4f54afea45241d06cd8bcab', 'testCategoryId', '0', '测试场景2', null, '1', '1', '0', null, null, null, '0', '5', null, null, null, null, '0', '2', null, '', null, null, '1', '2016-10-05 21:26:26', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-05 22:08:21', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64');

-- ----------------------------
-- Table structure for `t_pano_scene`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_scene`;
CREATE TABLE `t_pano_scene` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `proj_id` varchar(32) NOT NULL COMMENT '项目id',
  `scene_title` varchar(50) NOT NULL COMMENT '场景名称',
  `scene_src` varchar(255) NOT NULL COMMENT '全景图宽高比须为2:1，格式为jpg,全景视频为mp4',
  `hlookat` varchar(20) DEFAULT NULL COMMENT '水平视角',
  `vlookat` varchar(20) DEFAULT NULL COMMENT '垂直视角',
  `sound_src` varchar(255) DEFAULT NULL COMMENT '场景音乐',
  `video_src` varchar(255) DEFAULT NULL COMMENT '场景视频解说',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `state` char(1) DEFAULT NULL COMMENT '状态',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT NULL COMMENT '排序',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0否1是)',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(64) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  `update_id` varchar(64) DEFAULT NULL COMMENT '修改者ID',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '修改者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景';

-- ----------------------------
-- Records of t_pano_scene
-- ----------------------------
INSERT INTO `t_pano_scene` VALUES ('o_1au9le67ggl8kd836rvnu1lma1t', '1bd1f4ce5fc94e36942511b9d1c72460', '小树林', '/upload/image/n1/1bd1f4ce5fc94e36942511b9d1c72460/20161005133547d3gtf.jpg', '728.1062719808356', '31.29729977679121', null, null, '3', null, null, '0', '651447', '0', '2016-10-09 22:20:31', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-21 12:30:05', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1au9lemun2c1i7ddp2lip15vp2a', '1bd1f4ce5fc94e36942511b9d1c72460', '三林世博', '/upload/image/n1/1bd1f4ce5fc94e36942511b9d1c72460/20161005133604lrzrn.jpg', '603.059051440742', '16.113608692475043', null, null, '3', null, null, '1', '349537', '0', '2016-10-09 22:20:31', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-21 12:30:05', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1au9lfbvo2751eraquf4jisq02n', '1bd1f4ce5fc94e36942511b9d1c72460', '外国语室内1', '/upload/image/n1/1bd1f4ce5fc94e36942511b9d1c72460/20161005133626bmg78.jpg', '78.3816476761494', '13.147800136783621', null, null, '3', null, null, '2', '732377', '0', '2016-10-09 22:20:31', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-21 12:30:05', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1auagbgi7v5a1iug1op018fe15321g', 'cf3d2796e4f54afea45241d06cd8bcab', '网图1', '/upload/image/n1/cf3d2796e4f54afea45241d06cd8bcab/201610052126069x5qv.jpg', '0', '0', null, null, '0', null, null, '0', '817256', '0', '2016-10-05 22:08:21', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', null, '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64');
INSERT INTO `t_pano_scene` VALUES ('o_1auam1me3al2a811tak1f5d1k5sq', 'bc3e851c3c1a4c8da4653fca48154451', '标清', '/upload/media/201610/20161005230536nri00.mp4', '0', '0', null, null, '0', null, null, '0', null, '0', '2016-10-05 23:05:50', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', null, '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64');
INSERT INTO `t_pano_scene` VALUES ('o_1auam1tqv1fd518711ir91gqh1d1b17', 'bc3e851c3c1a4c8da4653fca48154451', '高清', '/upload/media/201610/20161005230545xb5a2.mp4', '0', '0', null, null, '0', null, null, '1', null, '0', '2016-10-05 23:05:50', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', null, '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64');
INSERT INTO `t_pano_scene` VALUES ('o_1auks0l471ta71thm16hc5t7ldr1g', '1edad6e7827645beb8117f1e870ae57c', '22', '/upload/image/n1/1edad6e7827645beb8117f1e870ae57c/201610092202429r5yb.jpg', '0', '0', null, null, '3', null, null, '0', '598797', '0', '2016-10-09 22:02:50', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.64', '2016-10-20 22:10:59', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1avdho3ee1cl81i2j109fe8gneg1g', '844464382dbb4abfba3c60fc014002bd', 'a', '/upload/image/n1/844464382dbb4abfba3c60fc014002bd/20161019120401is4cb.jpg', '-271.53947736750985', '-56.186833458397054', null, null, '3', null, null, '0', '994485', '0', '2016-10-27 14:45:04', '4d868ddfd70342b093e3013886e00ea9', '171.217.40.68', '2016-11-19 20:46:15', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1b1tsuth7pae26td3g16qc1g0u1g', '5b25206ed3064f1ab433c896cfde51cc', '1', '/upload/image/n1/5b25206ed3064f1ab433c896cfde51cc/20161119165916rwmrc.jpg', '0', '0', null, null, '0', null, null, '0', '173799', '0', '2016-11-19 16:59:26', '4d868ddfd70342b093e3013886e00ea9', '171.213.58.230', null, '4d868ddfd70342b093e3013886e00ea9', '171.213.58.230');
INSERT INTO `t_pano_scene` VALUES ('o_1b2b9pvmu5m9av62bv17lpeh01g', '4c08e7be27e0416a9b39c82a837463a0', 'w', '/upload/image/n1/4c08e7be27e0416a9b39c82a837463a0/20161124215349ekpkb.jpg', '0', '0', null, null, '0', null, null, '0', '292512', '0', '2016-11-24 21:53:55', '4d868ddfd70342b093e3013886e00ea9', '171.214.183.98', null, '4d868ddfd70342b093e3013886e00ea9', '171.214.183.98');
INSERT INTO `t_pano_scene` VALUES ('o_1b2dspg6oau9hknnc812s5191p1g', '14df1d7954f5408db09e1f1519fe2769', '12', '/upload/image/n1/14df1d7954f5408db09e1f1519fe2769/2016112522040027tiw.jpg', '84.1627827916316', '6.1506121514901215', null, null, '4', null, null, '0', '991242', '0', '2016-11-25 22:30:14', '4d868ddfd70342b093e3013886e00ea9', '139.227.84.240', '2016-11-26 10:41:43', null, null);
INSERT INTO `t_pano_scene` VALUES ('o_1b2dtmji1g9i43hrk311881uva1t', '14df1d7954f5408db09e1f1519fe2769', '123', '/upload/image/n1/14df1d7954f5408db09e1f1519fe2769/20161125221955dqvah.jpg', '0', '0', null, null, '4', null, null, '1', '466655', '0', '2016-11-25 22:30:14', '4d868ddfd70342b093e3013886e00ea9', '139.227.84.240', '2016-11-26 10:41:43', null, null);

-- ----------------------------
-- Table structure for `t_pano_spots`
-- ----------------------------
DROP TABLE IF EXISTS `t_pano_spots`;
CREATE TABLE `t_pano_spots` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `proj_id` varchar(32) NOT NULL COMMENT '项目id',
  `scene_id` varchar(64) NOT NULL COMMENT '场景id',
  `htype` char(1) NOT NULL DEFAULT '0' COMMENT '类型0热点1图片',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `hname` varchar(255) NOT NULL COMMENT '热点编号',
  `ath` varchar(32) NOT NULL COMMENT '水平坐标',
  `atv` varchar(32) NOT NULL COMMENT '垂直坐标',
  `linkedscene` varchar(32) DEFAULT NULL COMMENT '关联场景',
  `scale` varchar(32) DEFAULT NULL COMMENT '缩放比',
  `depth` varchar(32) DEFAULT NULL COMMENT '纵深',
  `rotate` varchar(32) DEFAULT NULL COMMENT '旋转角度',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `is_onclick` char(1) DEFAULT '0' COMMENT '可否点击0否1是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_update` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全景_场景_热点';

-- ----------------------------
-- Records of t_pano_spots
-- ----------------------------
INSERT INTO `t_pano_spots` VALUES ('028f68d9e00c404095b7685c91dd9928', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '1', null, 'pic_o_1avh6fv4i17lc10ml1e5e14u918k4m', '-14.06003047207355', '60.436780909819554', null, '0.42666666666666675', '2000', '0', '/upload/image/n1/201610/20161020220412r6epb.jpg', '1', '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('052f27da26ce412fb74a4c1cebde3814', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_32', '57.03492481481334', '-15.611770058096425', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('1db26647afdb48e78f006a415716f35e', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks7lmh8pn10u716h8ggf1dpfh', '-177.97737603579227', '-8.475452443395317', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220618avv9u.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('1dd0b574ac1e467a85e9fa918399b316', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '1', null, 'pic_o_1au9ph1iqjn4198o1rl3fju79ac', '-128.8064566212031', '-2.9011755893169093', null, '0.3466666666666667', '1000', '0', '/upload/image/n1/201610/20161005144709egyn0.jpg', null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('21286c5ed6e742b7827e85bf1dc38623', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfbvo2751eraquf4jisq02n', '1', null, 'pic_o_1au9pkjfcr5u1n6s13s619erii2m', '169.92830453570156', '58.41878564113225', null, '0.6266666666666667', '1000', '0', '/upload/image/n1/201610/20161005144905zwyb3.jpg', null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('2452b81395b04524857528fc139a550f', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfvkl1qpao35busk9ivkh34', '0', null, 'hotspot_4', '-12.621731527831116', '-1.2375502313311614', 'o_1au9le67ggl8kd836rvnu1lma1t', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('3ded7a12d7fb4647bfa6b4ee4eac64d5', '844464382dbb4abfba3c60fc014002bd', 'o_1avdho3ee1cl81i2j109fe8gneg1g', '1', null, 'pic_o_1b1u9uap51iss1rohdn8i1d1eba7', '32.83559872444846', '-22.191195452428005', null, '1', '1000', '0', '/upload/image/n1/201611/20161119204557p9trs.png', null, '0', '2016-11-19 20:46:15', null);
INSERT INTO `t_pano_spots` VALUES ('3f095838d10c4721a4f65c4349c9ef63', '14df1d7954f5408db09e1f1519fe2769', 'o_1b2dspg6oau9hknnc812s5191p1g', '1', null, 'pic_o_1b2du7n021mrn9e1qhva861r9gc', '-29.77621233883974', '-2.579619391570396', null, '0.6400000000000001', '1000', '0', '/upload/image/n1/201611/201611252229114rtfp.png', '1', '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_spots` VALUES ('44a5fa726e924edbb72a1c0dce2503f1', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_29', '172.64099705840118', '-41.021832607750795', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('46fbc3192071466db55be917a4c22052', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '0', null, 'hotspot_1', '-10.464050655293931', '43.76947518702347', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('489d780736464db6a5e79c9206feb999', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks7bmb1fs213ac2bv6mqmhnc', '-133.12215252593395', '-8.204119482009092', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220608iiy9y.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('4ca4a4315e234b19b68c4e06f14e684c', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfvkl1qpao35busk9ivkh34', '1', null, 'pic_o_1au9pliv71lsv1h20fob2qv13ohr', '-100.90154006162538', '-21.87597902472976', null, '0.10666666666666669', '1000', '0', '/upload/image/n1/201610/20161005144938l6a8o.jpg', null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('4cec4d619881405c934c5fd9361b29de', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '1', null, 'pic_o_1au9pfk3p2buo8g1fqt13ulnlc7', '-177.82979287424501', '11.100583077516251', null, '1', '1000', '0', '/upload/image/n1/201610/20161005144622y22m5.jpg', null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('50df91d41df249178a727a4e3ff2deda', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_31', '-18.02929056058366', '-71.54466843052017', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('573c92e47a294397a74c4c1069cb1082', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_28', '210.80914864056214', '-14.152515000785291', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('586cb3e7a58d44c1993f2692b2fb9c59', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '0', null, 'hotspot_12', '175.99023505346435', '-14.676788090231978', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('5d8a0a45e5104bcca557a69102720b9d', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks81d71p2q16vf1jb8dfc4irm', '137.15260489488057', '-10.562902196305888', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220631627jc.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('5dda970aa11944a38b146e86f2c8eca1', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_2', '-353.4451999558456', '-2.286029947842085', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('5e722ad32a944140bee0bf414beeaa25', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks6kf55ttq7p3n3mukdgt7', '-89.6131840803128', '-4.681699695270873', null, '1', '1000', '7.200000000000006', '/upload/image/n1/201610/20161009220544ykgum.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('61ccc5f2cbe34c9db8910f7c526512ef', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_26', '120.2243953893666', '21.606651767009207', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('774a63b8efc74255844dd1b1f499c72f', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '1', null, 'pic_o_1au9phmadspa1bd81vh9ib319hmh', '-93.96776261668958', '-0.35175489391955217', null, '0.22666666666666657', '1000', '0', '/upload/image/n1/201610/20161005144730nyaqg.jpg', null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('7a8603c6f1854b568bc110108fb6de76', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks8qae10tl1hkg5351c261kmm15', '3.483965804405841', '-8.424346270068131', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220655hbmkg.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('973c4363b37742dd9f5fcd76ae9e54d7', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfbvo2751eraquf4jisq02n', '0', null, 'hotspot_33', '-8.319036044142478', '8.41389121487039', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('a308b0f3e16747b1b9a7a31c60fe99ba', '844464382dbb4abfba3c60fc014002bd', 'o_1avdho3ee1cl81i2j109fe8gneg1g', '1', null, 'pic_o_1b13j411111ef1govr1se6msh17', '74.95043777989872', '-7.104755864310902', null, '1', '1000', '0', '/upload/image/n1/201611/20161109114636ujpo6.png', '1', '0', '2016-11-19 20:46:15', null);
INSERT INTO `t_pano_spots` VALUES ('a50c74e4aff84794b35bf739e5a03181', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lfbvo2751eraquf4jisq02n', '0', null, 'hotspot_332', '-90.95089059343928', '14.172368466176067', 'o_1au9lemun2c1i7ddp2lip15vp2a', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('ab0396b4035645df8c5e74c2af0d9b79', '14df1d7954f5408db09e1f1519fe2769', 'o_1b2dspg6oau9hknnc812s5191p1g', '1', null, 'pic_o_1b2du6pme17uasfiuht12d1fmv7', '580.1440925366414', '0.05313567106326123', null, '1', '1000', '0', '/upload/image/n1/201611/20161125222841y4mk9.png', '1', '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_spots` VALUES ('b22496ccd1c3415083088a7267395d64', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '0', null, 'hotspot_22', '127.93305362924667', '3.3045367150204803', 'o_1au9lfbvo2751eraquf4jisq02n', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('b2c493d27a5243f08dfd4df1583e411c', '844464382dbb4abfba3c60fc014002bd', 'o_1avdho3ee1cl81i2j109fe8gneg1g', '0', null, 'hotspot_1', '-3.116256889006877', '5.260019576254694', 'o_1avdho3ee1cl81i2j109fe8gneg1g', null, null, null, null, null, '0', '2016-11-19 20:46:15', null);
INSERT INTO `t_pano_spots` VALUES ('b2eb7bfc11784af78b7ec0d5d3c6fd83', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks8k7t1g6l1ksmb3tkdc1u8610', '48.03674609159145', '-8.240367974414045', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220650uiay4.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('b2f734e4c136492f9f0e5d984233727f', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks95mn7or1kol1k0vdrtdjv1a', '-40.90971502716934', '-4.631569846394032', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220707y6v68.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('b3b0dc4e2840430b912778565b41f32e', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '1', null, 'pic_o_1auks8bh21qi01fnv19oh9it1nuur', '92.68394840178095', '-9.99525943859712', null, '1', '1000', '0', '/upload/image/n1/201610/20161009220640oshoe.jpg', null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('c3a33db754b04f919b16fa6901bcbf01', '14df1d7954f5408db09e1f1519fe2769', 'o_1b2dtmji1g9i43hrk311881uva1t', '0', null, 'hotspot_2', '167.35630369417692', '15.429389833384738', 'o_1b2dspg6oau9hknnc812s5191p1g', null, null, null, null, null, '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_spots` VALUES ('ca091842d16b4d77a7db3be13a634199', '14df1d7954f5408db09e1f1519fe2769', 'o_1b2dspg6oau9hknnc812s5191p1g', '0', null, 'hotspot_1', '-142.7106249669914', '13.155866444837296', 'o_1b2dtmji1g9i43hrk311881uva1t', null, null, '60', null, null, '0', '2016-11-26 10:41:43', null);
INSERT INTO `t_pano_spots` VALUES ('d1a7b908dfe04c4393f609202e4f7a89', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '0', null, 'hotspot_23', '-14.683031165715988', '22.86402747932421', 'o_1au9le67ggl8kd836rvnu1lma1t', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('d1da8bf9d1c64854b2a82bc5e000ecd8', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9lemun2c1i7ddp2lip15vp2a', '0', null, 'hotspot_2', '-116.61019664902528', '20.85762764713276', 'o_1au9lfbvo2751eraquf4jisq02n', null, null, '30', null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('debd20b13ac640e98b1726960c740d40', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_23', '-31.778682384054093', '-0.7873387308762468', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('df9f89f0e20c485db4712b30f81fb16f', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_25', '163.9915996831282', '59.46939758342728', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('e60c7de12ee44681b5a9fd47bbbc5ec0', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_30', '-92.17597753409592', '14.049236735998889', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('ea5aaac6d0bf4b058e144dd67df7d288', '1bd1f4ce5fc94e36942511b9d1c72460', 'o_1au9le67ggl8kd836rvnu1lma1t', '0', null, 'hotspot_13', '-87.61926596831097', '16.172880190512732', 'o_1au9lfbvo2751eraquf4jisq02n', null, null, null, null, null, '0', '2016-10-21 12:30:05', null);
INSERT INTO `t_pano_spots` VALUES ('faa144fd3a524e3c88d23d661b22f76e', '1edad6e7827645beb8117f1e870ae57c', 'o_1auks0l471ta71thm16hc5t7ldr1g', '0', null, 'hotspot_22', '0.21576805028928655', '0', 'o_1auks0l471ta71thm16hc5t7ldr1g', null, null, null, null, null, '0', '2016-10-20 22:10:59', null);
INSERT INTO `t_pano_spots` VALUES ('fe23a363c53e45668a90ae8eede33be3', '14df1d7954f5408db09e1f1519fe2769', 'o_1b2dtmji1g9i43hrk311881uva1t', '0', null, 'hotspot_23', '43.94139506549936', '3.0408759845952433', 'o_1b2dspg6oau9hknnc812s5191p1g', null, null, null, null, null, '0', '2016-11-26 10:41:43', null);

-- ----------------------------
-- Table structure for `t_sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_log`;
CREATE TABLE `t_sys_user_log` (
  `id` varchar(100) NOT NULL COMMENT 'ID',
  `type` varchar(20) DEFAULT NULL COMMENT '操作类型',
  `description` varchar(4000) NOT NULL COMMENT '具体描述',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `create_id` varchar(50) DEFAULT NULL COMMENT '建立者ID',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '建立者IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_管理员操作日志';

-- ----------------------------
-- Records of t_sys_user_log
-- ----------------------------
INSERT INTO `t_sys_user_log` VALUES ('0a0c2b62f4924d1fb4f8aa986da04c6c', '修改', '部门信息', '2016-12-04 10:04:53', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('23905533a53e4dcfa231ae7b580b11e2', '修改', '部门信息', '2016-12-04 10:05:21', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('4d7de9b980ad4bad8552eff42428f48b', '查询', '进入编辑页面', '2016-12-02 18:27:02', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('6d5a45a70f81465c8d5b088198cc048c', '修改', '部门信息', '2016-12-04 10:18:39', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('72c05fb5c2224344a26e538b8ff4686a', '修改', '权限信息', '2016-12-04 14:51:49', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('77770c12167f4e0c996481400dd9017c', '修改', '部门信息', '2016-12-04 10:19:01', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('8976ab68b2474c7794014f61f4c2124c', '修改', '权限信息', '2016-12-04 14:53:41', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('988d95163bd74910b010ce8f4e1adea9', '修改', '部门信息', '2016-12-04 10:05:35', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('a7ec9a7f97e34379b6203986f87a37aa', '修改', '部门信息', '2016-12-04 10:05:11', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('b44b765767174f079f4201c11d63c3d9', '修改', '部门信息', '2016-12-04 09:35:11', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('b766956e74d24627841422dc8da353d7', '修改', '全景项目信息', '2016-12-02 18:27:06', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('c7920f62508e43358756c4320986fb09', '修改', '部门信息', '2016-12-04 10:05:02', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('ded3f40964ab4cae971e87d9d0d33c12', '修改', '部门信息', '2016-12-04 10:33:59', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('f8844bcdb0de4590910ad8635ccf0283', '删除', '全景评论信息', '2016-12-02 18:25:17', '超级管理员', '0:0:0:0:0:0:0:1');
INSERT INTO `t_sys_user_log` VALUES ('fb09366bc8754c8a97723ba9b11b6a4c', '修改', '权限信息', '2016-12-04 14:48:53', '超级管理员', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `t_sys_variable`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_variable`;
CREATE TABLE `t_sys_variable` (
  `id` varchar(22) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `parentid` varchar(100) DEFAULT NULL COMMENT '父级ID',
  `level` int(10) DEFAULT NULL COMMENT '级别',
  `p_code` varchar(1000) DEFAULT NULL COMMENT '上下级组合编码',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_数据字典表';

-- ----------------------------
-- Records of t_sys_variable
-- ----------------------------

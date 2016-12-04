/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : krpano

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-12-04 16:08:37
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

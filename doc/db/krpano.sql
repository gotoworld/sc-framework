/*
Navicat MySQL Data Transfer

Source Server         : 120.77.212.106
Source Server Version : 50619
Source Host           : 120.77.212.106:3306
Source Database       : krpano

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2017-05-10 10:15:28
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
INSERT INTO `auth_perm` VALUES ('0091e1888f1c49a98dcbee', '标签编辑', 'memTags:edit', '1093ab35ed87423f981ff8', '', '0', '0', null, '0', '2017-04-20 09:03:27', '2017-04-20 09:03:27');
INSERT INTO `auth_perm` VALUES ('0318e9eec64349f6b7a7f7', '删除', 'memPanoProj:del', '27300083263149d4b1ae4c', '', '0', '0', null, '0', '2017-04-20 09:06:04', '2017-04-20 09:06:04');
INSERT INTO `auth_perm` VALUES ('05122cc6a494414ea16d52', '编辑', 'memMaterial:edit', 'c0f61f6b92424903a1b5ef', '', '0', '0', null, '0', '2017-04-19 13:32:43', '2017-04-19 13:32:43');
INSERT INTO `auth_perm` VALUES ('092f38a86fd14112a48297', '标签删除', 'memTags:del', '1093ab35ed87423f981ff8', '', '0', '0', null, '0', '2017-04-20 09:03:41', '2017-04-20 09:03:41');
INSERT INTO `auth_perm` VALUES ('1093ab35ed87423f981ff8', '标签管理', 'memTags:menu', '4f57ba29bd3e4358bab5ce', '', '0', '0', null, '0', '2017-04-19 13:34:58', '2017-04-19 13:34:58');
INSERT INTO `auth_perm` VALUES ('1cadb4eef480407d83b31e', '会员中心', 'member:menu', '', '', '0', '0', null, '0', '2017-04-19 13:30:51', '2017-04-19 13:30:51');
INSERT INTO `auth_perm` VALUES ('1cbe3181e3804cd98ba5a5', '作品评论', 'memPanoComment:menu', '4f57ba29bd3e4358bab5ce', '', '0', '0', null, '0', '2017-04-19 13:36:13', '2017-04-19 13:36:13');
INSERT INTO `auth_perm` VALUES ('1e168a459ecd4351bddc17', '编辑', 'memPanoProj:edit', '27300083263149d4b1ae4c', '', '0', '0', null, '0', '2017-04-20 09:05:38', '2017-04-20 09:05:38');
INSERT INTO `auth_perm` VALUES ('27300083263149d4b1ae4c', '全景摄影', 'memPanoProj:menu', '4f57ba29bd3e4358bab5ce', '', '0', '0', null, '0', '2017-04-19 13:35:24', '2017-04-19 13:35:24');
INSERT INTO `auth_perm` VALUES ('28d6e012bbff47d3bc4ef6', '编辑', 'memCategory:edit', '8805b6bb01f9423f937706', '', '0', '0', null, '0', '2017-04-20 09:08:27', '2017-04-20 09:08:27');
INSERT INTO `auth_perm` VALUES ('3185edd42f5b4df6824d25', '后台系统', 'admin:menu', '', '', '0', '0', null, '0', '2017-04-19 13:29:47', '2017-04-19 13:29:47');
INSERT INTO `auth_perm` VALUES ('379770c49acb44ee841aa3', '删除', 'memCategory:del', '8805b6bb01f9423f937706', '', '0', '0', null, '0', '2017-04-20 09:08:36', '2017-04-20 09:08:36');
INSERT INTO `auth_perm` VALUES ('3af2416c21b0432f928c43', '编辑', 'memVideo:edit', '8540a7b87685446f868e9a', '', '0', '0', null, '0', '2017-04-20 09:07:14', '2017-04-20 09:07:14');
INSERT INTO `auth_perm` VALUES ('438a0b19010841308e2a9c', '评论删除', 'memPanoComment:del', '1cbe3181e3804cd98ba5a5', '', '0', '0', null, '0', '2017-04-20 09:04:29', '2017-04-20 09:04:29');
INSERT INTO `auth_perm` VALUES ('4f57ba29bd3e4358bab5ce', '全景作品', 'memPano:menu', '1cadb4eef480407d83b31e', '', '0', '0', null, '0', '2017-04-19 13:34:06', '2017-04-19 13:34:06');
INSERT INTO `auth_perm` VALUES ('670f01241e3241a9a93bf6', '会员认证', 'memberAccount:menu', '6a4209b8e11b45f9ac534d', '', '0', '0', null, '0', '2017-04-19 13:39:38', '2017-04-19 13:39:38');
INSERT INTO `auth_perm` VALUES ('67620b1e15d74be29eb5fd', '我的账户', 'myAccount:menu', '6a4209b8e11b45f9ac534d', '', '0', '1', null, '0', '2017-04-19 13:38:03', '2017-04-19 13:39:54');
INSERT INTO `auth_perm` VALUES ('6a4209b8e11b45f9ac534d', '账户管理', 'account:menu', '1cadb4eef480407d83b31e', '', '0', '0', null, '0', '2017-04-19 13:37:03', '2017-04-19 13:37:03');
INSERT INTO `auth_perm` VALUES ('7d29b6cb862649538821d6', '新增', 'memVideo:add', '8540a7b87685446f868e9a', '', '0', '0', null, '0', '2017-04-20 09:07:02', '2017-04-20 09:07:02');
INSERT INTO `auth_perm` VALUES ('8540a7b87685446f868e9a', '全景视频', 'memVideo:menu', '4f57ba29bd3e4358bab5ce', '', '0', '0', null, '0', '2017-04-19 13:35:46', '2017-04-19 13:35:46');
INSERT INTO `auth_perm` VALUES ('8805b6bb01f9423f937706', '全景类目', 'memCategory:menu', '4f57ba29bd3e4358bab5ce', '', '0', '0', null, '0', '2017-04-19 13:34:33', '2017-04-19 13:34:33');
INSERT INTO `auth_perm` VALUES ('8d986e2ec84245fa8fa28b', '新增', 'memPanoProj:add', '27300083263149d4b1ae4c', '', '0', '0', null, '0', '2017-04-20 09:05:52', '2017-04-20 09:05:52');
INSERT INTO `auth_perm` VALUES ('91e542f84786488db435f2', '素材下载', 'sysMaterial:down', 'id_pano_material', '', '0', '0', null, '0', '2017-04-19 08:11:55', '2017-04-19 08:11:55');
INSERT INTO `auth_perm` VALUES ('96eb4716287149a5988164', '标签新增', 'memTags:add', '1093ab35ed87423f981ff8', '', '0', '0', null, '0', '2017-04-20 09:03:05', '2017-04-20 09:03:05');
INSERT INTO `auth_perm` VALUES ('9843b6f9f72c44f98d4ec6', '信息中心', 'message:menu', '1cadb4eef480407d83b31e', '', '0', '0', null, '0', '2017-04-19 13:37:30', '2017-04-19 13:37:30');
INSERT INTO `auth_perm` VALUES ('a08952ccdb324d7ebf79fb', '下载', 'memMaterial:down', 'c0f61f6b92424903a1b5ef', '', '0', '0', null, '0', '2017-04-19 13:33:22', '2017-04-19 13:33:22');
INSERT INTO `auth_perm` VALUES ('bac7d3c858584afca8cbd7', '新增', 'memMaterial:add', 'c0f61f6b92424903a1b5ef', '', '0', '0', null, '0', '2017-04-19 13:32:26', '2017-04-19 13:32:26');
INSERT INTO `auth_perm` VALUES ('c0f61f6b92424903a1b5ef', '素材库', 'memMaterial:menu', '1cadb4eef480407d83b31e', '', '0', '0', null, '0', '2017-04-19 13:32:09', '2017-04-19 13:32:09');
INSERT INTO `auth_perm` VALUES ('c54bdcdfd44947ffa997d6', '密码修改', 'memOrgUser:editPwd', 'db309d19d01b4a059d5310', '', '0', '0', null, '0', '2017-04-20 12:18:19', '2017-04-20 12:18:19');
INSERT INTO `auth_perm` VALUES ('db309d19d01b4a059d5310', '个人资料', 'myinfo:menu', '6a4209b8e11b45f9ac534d', '', '0', '0', null, '0', '2017-04-19 13:38:27', '2017-04-19 13:38:27');
INSERT INTO `auth_perm` VALUES ('dbdb461ab5b34b60884eb4', '删除', 'memMaterial:del', 'c0f61f6b92424903a1b5ef', '', '0', '0', null, '0', '2017-04-19 13:32:58', '2017-04-19 13:32:58');
INSERT INTO `auth_perm` VALUES ('e1e4648de4f8457cbb2e1c', '删除', 'memVideo:del', '8540a7b87685446f868e9a', '', '0', '0', null, '0', '2017-04-20 09:07:24', '2017-04-20 09:07:24');
INSERT INTO `auth_perm` VALUES ('e36d97ef220c41d58b7e6b', '新增', 'memCategory:add', '8805b6bb01f9423f937706', '', '0', '0', null, '0', '2017-04-20 09:08:12', '2017-04-20 09:08:12');
INSERT INTO `auth_perm` VALUES ('e4090e8d98b945d7b0dc57', '系统消息', 'sysMessage:menu', '9843b6f9f72c44f98d4ec6', '', '0', '0', null, '0', '2017-04-19 13:40:30', '2017-04-19 13:40:30');
INSERT INTO `auth_perm` VALUES ('ed5bd20489ba4ac5900339', '密码修改', 'orgUser:editPwd', 'id_org_user', '', '0', '0', null, '0', '2017-04-19 03:42:37', '2017-04-19 03:42:37');
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
INSERT INTO `auth_perm` VALUES ('id_news_memu', '资讯管理', 'newsManager:menu', '3185edd42f5b4df6824d25', '', null, '6', null, '0', '2015-10-04 19:41:25', '2017-04-19 13:29:54');
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
INSERT INTO `auth_perm` VALUES ('id_pano', '全景管理', 'pano:menu', '3185edd42f5b4df6824d25', ' ', null, '2', null, '0', '2000-01-01 00:00:00', '2017-04-19 13:29:59');
INSERT INTO `auth_perm` VALUES ('id_pano_category', '全景_类目', 'panoCat:menu', 'id_pano', '系统生成', '0', '1', '', '1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_add', '全景_类目_新增', 'panoCat:add', 'id_pano_category', '系统生成', '0', '1', null, '1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_del', '全景_类目_逻辑删除', 'panoCat:del', 'id_pano_category', '系统生成', '0', '1', null, '1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_edit', '全景_类目_编辑', 'panoCat:edit', 'id_pano_category', '系统生成', '0', '1', null, '1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_category_save', '全景_类目_保存', 'panoCat:save', 'id_pano_category', '系统生成', '0', '1', null, '1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment', '全景_评论', 'panoComment:menu', 'id_pano', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_add', '全景_评论_新增', 'panoComment:add', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_del', '全景_评论_逻辑删除', 'panoComment:del', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_edit', '全景_评论_编辑', 'panoComment:edit', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_comment_save', '全景_评论_保存', 'panoComment:save', 'id_pano_comment', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_material', '全景_素材', 'sysMaterial:menu', 'id_pano', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-19 08:02:07');
INSERT INTO `auth_perm` VALUES ('id_pano_material_add', '新增', 'sysMaterial:add', 'id_pano_material', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-19 08:02:13');
INSERT INTO `auth_perm` VALUES ('id_pano_material_del', '删除', 'sysMaterial:del', 'id_pano_material', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-19 08:02:19');
INSERT INTO `auth_perm` VALUES ('id_pano_material_edit', '编辑', 'sysMaterial:edit', 'id_pano_material', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-19 08:02:27');
INSERT INTO `auth_perm` VALUES ('id_pano_proj', '全景_项目', 'panoProj:menu', 'id_pano', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_add', '全景_项目_新增', 'panoProj:add', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_del', '全景_项目_逻辑删除', 'panoProj:del', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_edit', '全景_项目_编辑', 'panoProj:edit', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_pano_proj_save', '全景_项目_保存', 'panoProj:save', 'id_pano_proj', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys', '系统管理', 'sys:menu', '3185edd42f5b4df6824d25', '', null, '6', null, '0', '2015-10-04 19:41:25', '2017-04-19 13:30:09');
INSERT INTO `auth_perm` VALUES ('id_sys_advertise', '广告管理', 'sysAdvertise:menu', 'id_sys', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_advertise_add', '新增', 'sysAdvertise:add', 'id_sys_advertise', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_advertise_del', '删除', 'sysAdvertise:del', 'id_sys_advertise', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_advertise_edit', '编辑', 'sysAdvertise:edit', 'id_sys_advertise', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_category', '类目管理', 'sysCategory:menu', 'id_sys', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-17 15:05:35');
INSERT INTO `auth_perm` VALUES ('id_sys_category_add', '新增', 'sysCategory:add', 'id_sys_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_category_del', '删除', 'sysCategory:del', 'id_sys_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_category_edit', '编辑', 'sysCategory:edit', 'id_sys_category', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_dic', '系统_数据字典', 'sysDic:menu', 'id_sys', '', null, '0', null, '0', '2015-10-04 19:45:22', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_add', '新增', 'sysDic:add', 'id_sys_dic', '', null, '0', null, '0', '2015-10-07 14:40:53', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_del', '删除', 'sysDic:del', 'id_sys_dic', '', null, '1', null, '0', '2015-10-07 14:41:25', null);
INSERT INTO `auth_perm` VALUES ('id_sys_dic_edit', '编辑', 'sysDic:edit', 'id_sys_dic', '', null, '2', null, '0', '2015-10-07 14:41:10', null);
INSERT INTO `auth_perm` VALUES ('id_sys_log', '系统_操作日志', 'sysLog:menu', 'id_sys', '', null, '3', null, '0', '2015-10-04 19:44:30', null);
INSERT INTO `auth_perm` VALUES ('id_sys_message', '系统信息管理', 'sysMessage:menu', 'id_sys', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-17 15:05:58');
INSERT INTO `auth_perm` VALUES ('id_sys_message_add', '新增', 'sysMessage:add', 'id_sys_message', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_message_del', '删除', 'sysMessage:del', 'id_sys_message', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_message_edit', '编辑', 'sysMessage:edit', 'id_sys_message', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_news', '新闻资讯', 'sysNews:menu', 'id_news_memu', '', null, '0', null, '0', '2015-10-04 19:45:22', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_add', '新增', 'sysNews:add', 'id_sys_news', '', null, '0', null, '0', '2015-10-07 14:40:53', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_category', '资讯栏目', 'sysNewsCategory:menu', 'id_news_memu', '', null, '0', null, '0', '2015-10-04 19:45:22', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_category_add', '新增', 'sysNewsCategory:add', 'id_sys_news_category', '', null, '0', null, '0', '2015-10-07 14:40:53', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_category_del', '删除', 'sysNewsCategory:del', 'id_sys_news_category', '', null, '1', null, '0', '2015-10-07 14:41:25', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_category_edit', '编辑', 'sysNewsCategory:edit', 'id_sys_news_category', '', null, '2', null, '0', '2015-10-07 14:41:10', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_del', '删除', 'sysNews:del', 'id_sys_news', '', null, '1', null, '0', '2015-10-07 14:41:25', null);
INSERT INTO `auth_perm` VALUES ('id_sys_news_edit', '编辑', 'sysNews:edit', 'id_sys_news', '', null, '2', null, '0', '2015-10-07 14:41:10', null);
INSERT INTO `auth_perm` VALUES ('id_sys_pano_plugins', '全景插件管理', 'sysPanoPlugins:menu', 'id_sys', '系统生成', '0', '1', null, '0', '2000-01-01 00:00:00', '2017-04-17 15:06:30');
INSERT INTO `auth_perm` VALUES ('id_sys_pano_plugins_add', '新增', 'sysPanoPlugins:add', 'id_sys_pano_plugins', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_pano_plugins_del', '删除', 'sysPanoPlugins:del', 'id_sys_pano_plugins', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_pano_plugins_edit', '编辑', 'sysPanoPlugins:edit', 'id_sys_pano_plugins', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_sensitive_words', '系统_敏感词', 'sysSensitiveWords:menu', 'id_sys', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_sensitive_words_add', '新增', 'sysSensitiveWords:add', 'id_sys_sensitive_words', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_sensitive_words_del', '删除', 'sysSensitiveWords:del', 'id_sys_sensitive_words', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
INSERT INTO `auth_perm` VALUES ('id_sys_sensitive_words_edit', '编辑', 'sysSensitiveWords:edit', 'id_sys_sensitive_words', '系统生成', '0', '0', null, '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限_角色信息表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', '超级管理员', '1', null, '0', '7', null, '0', '2000-01-01 00:00:00', '2017-02-26 13:29:13');
INSERT INTO `auth_role` VALUES ('2', '全景会员2', '0', null, '0', '6', null, '0', '2017-04-17 15:26:59', '2017-05-03 03:01:46');

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
INSERT INTO `auth_role_vs_perm` VALUES ('2', '0091e1888f1c49a98dcbee', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '0318e9eec64349f6b7a7f7', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '05122cc6a494414ea16d52', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '092f38a86fd14112a48297', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '1093ab35ed87423f981ff8', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '1cadb4eef480407d83b31e', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '1cbe3181e3804cd98ba5a5', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '1e168a459ecd4351bddc17', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '27300083263149d4b1ae4c', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '28d6e012bbff47d3bc4ef6', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '379770c49acb44ee841aa3', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '3af2416c21b0432f928c43', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '438a0b19010841308e2a9c', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '4f57ba29bd3e4358bab5ce', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '670f01241e3241a9a93bf6', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '67620b1e15d74be29eb5fd', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '6a4209b8e11b45f9ac534d', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '7d29b6cb862649538821d6', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '8540a7b87685446f868e9a', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '8805b6bb01f9423f937706', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '8d986e2ec84245fa8fa28b', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '96eb4716287149a5988164', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', '9843b6f9f72c44f98d4ec6', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'a08952ccdb324d7ebf79fb', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'bac7d3c858584afca8cbd7', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'c0f61f6b92424903a1b5ef', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'db309d19d01b4a059d5310', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'dbdb461ab5b34b60884eb4', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'e1e4648de4f8457cbb2e1c', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'e36d97ef220c41d58b7e6b', '2017-05-03 03:01:46');
INSERT INTO `auth_role_vs_perm` VALUES ('2', 'e4090e8d98b945d7b0dc57', '2017-05-03 03:01:46');

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
INSERT INTO `auth_user_vs_role` VALUES ('2', '2', '2017-04-19 09:39:48');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='组织架构_部门';

-- ----------------------------
-- Records of org_dept
-- ----------------------------
INSERT INTO `org_dept` VALUES ('1', 'zonghebu', '综合部', null, null, '', '2', '0', null, '0', '1', '2017-04-17 15:01:32', '2017-04-17 15:02:14');
INSERT INTO `org_dept` VALUES ('2', 'yanfabu', '研发部', null, null, '', '0', '0', null, '0', '1', '2017-04-17 15:02:07', '2017-04-17 15:02:07');
INSERT INTO `org_dept` VALUES ('3', 'yunweibu', '运维部', null, null, '', '0', '0', null, '0', '1', '2017-04-17 15:02:29', '2017-04-17 15:02:29');
INSERT INTO `org_dept` VALUES ('4', 'renshibu', '人事部', null, null, '', '0', '0', null, '0', '1', '2017-04-17 15:02:39', '2017-04-17 15:02:39');
INSERT INTO `org_dept` VALUES ('5', 'ceshizu', '测试组', '2', null, '', '0', '0', null, '0', '1', '2017-04-17 15:03:48', '2017-04-17 15:03:48');
INSERT INTO `org_dept` VALUES ('6', 'javahoutai', 'java后台', '2', null, '', '0', '0', null, '0', '1', '2017-04-17 15:04:31', '2017-04-17 15:04:31');
INSERT INTO `org_dept` VALUES ('7', 'qianduanzu', '前端组', '2', null, '', '0', '0', null, '0', '1', '2017-04-17 15:04:55', '2017-04-17 15:04:55');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='组织架构_用户';

-- ----------------------------
-- Records of org_user
-- ----------------------------
INSERT INTO `org_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', '', null, null, '', '', '1', null, '', null, '0', '2017-05-10 01:54:57', '49', '0', 'default', null, null, '4', '0', '0', null, '2000-01-01 00:00:00', '2017-05-09 05:51:48');
INSERT INTO `org_user` VALUES ('2', 'pano', '7af88f1d651067943c4aff4d7023ef55', '会员', '', null, null, '', '', '1', null, '', null, '1', '2017-05-10 01:40:59', '12', '0', 'default', null, null, '1', '0', '0', null, '2000-01-01 00:00:00', '2017-04-19 09:39:48');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='全景_场景_评论';

-- ----------------------------
-- Records of pano_comments
-- ----------------------------
INSERT INTO `pano_comments` VALUES ('1', 'o_1bakkqsgr301ikn13olh315cr1t', '不做低头族,从我做起', '114.99848047370832', '39.985884845295445', null, null, null, null, null, null, '1', '2017-03-08 15:09:17', '2017-03-14 06:14:13');
INSERT INTO `pano_comments` VALUES ('2', 'o_1bakkqsgr301ikn13olh315cr1t', 'dfghjkl', '162.76438778239603', '22.940195898709856', null, null, null, null, null, null, '1', '2017-03-15 13:05:07', '2017-03-15 15:39:29');
INSERT INTO `pano_comments` VALUES ('3', 'o_1bakkqsgr301ikn13olh315cr1t', 'dfghjkl', '162.76438778239603', '22.940195898709856', null, null, null, null, null, null, '0', '2017-03-15 13:05:21', '2017-03-15 13:05:21');
INSERT INTO `pano_comments` VALUES ('4', 'o_1bakkqsgr301ikn13olh315cr1t', 'dfghjkl', '162.76438778239603', '22.940195898709856', null, null, null, null, null, null, '1', '2017-03-15 13:05:21', '2017-03-15 15:39:27');
INSERT INTO `pano_comments` VALUES ('5', 'o_1bahjke2ih3rptm8j09s1pjf1g', '去单位萨斯的', '18.17530752448033', '24.617118785779844', null, null, null, null, null, null, '0', '2017-05-10 01:55:03', '2017-05-10 01:55:03');

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
INSERT INTO `pano_map` VALUES ('4', 'o_1bahjke2ih3rptm8j09s1pjf1g', '0', '65', '27', null, '2017-04-16 11:44:59');
INSERT INTO `pano_map` VALUES ('4', 'o_1bakkqo0eac47er1fku1lh1dlt1g', '0', '59', '175', null, '2017-04-16 11:44:59');
INSERT INTO `pano_map` VALUES ('4', 'o_1bakkqsgr301ikn13olh315cr1t', '0', '76', '95', null, '2017-04-16 11:44:59');
INSERT INTO `pano_map` VALUES ('4', 'o_1bakkr84vli014b41j1jt5uve22a', '0', '171', '43', null, '2017-04-16 11:44:59');
INSERT INTO `pano_map` VALUES ('4', 'o_1bakkrkeg6tg1mrq117247fcj02n', '0', '181', '156', null, '2017-04-16 11:44:59');

-- ----------------------------
-- Table structure for `pano_proj`
-- ----------------------------
DROP TABLE IF EXISTS `pano_proj`;
CREATE TABLE `pano_proj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `category_id` bigint(20) NOT NULL COMMENT '类目id',
  `type` tinyint(1) NOT NULL COMMENT '项目类型0图片1视频',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sound_src` varchar(255) DEFAULT NULL COMMENT '背景音乐',
  `is_comments` tinyint(1) DEFAULT '0' COMMENT '开放评论 0否1是',
  `is_planetoid` tinyint(1) DEFAULT '0' COMMENT '小行星开场 0否1是',
  `is_fps` tinyint(1) DEFAULT '0' COMMENT '显示FPS0否1是',
  `snow_type` varchar(55) DEFAULT NULL COMMENT '雪景类型',
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='全景_项目';

-- ----------------------------
-- Records of pano_proj
-- ----------------------------
INSERT INTO `pano_proj` VALUES ('4', '1488797155083122853', '4', '0', '测试1', '/upload/file/201704/201704151918072ca2e.mp3', '1', '1', '1', '', '/upload/image/n1/201704/201704151917517mcm3.png', null, null, '2', '39', null, null, null, '{\"o_1bahjke2ih3rptm8j09s1pjf1g\":{\"view\":{\"hlookat\":0.3381609287550803,\"vlookat\":28.84573148771376},\"hotspots\":[{\"ath\":-9.318275548709607,\"atv\":41.06656645679561,\"linkedscene\":\"o_1bakkqsgr301ikn13olh315cr1t\",\"hname\":\"hotspot_1\"},{\"ath\":176.73666665819482,\"atv\":-13.447568636268821,\"linkedscene\":\"o_1bakkqo0eac47er1fku1lh1dlt1g\",\"hname\":\"hotspot_2\"}],\"picspots\":[{\"ath\":-177.8515196916787,\"atv\":10.817882687018898,\"hname\":\"pic_o_1ban6vggn12kb1fjfd3t19iq13eu7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201703/2017030823004003cox.jpg\",\"isOnclick\":null},{\"ath\":5.975293100110548,\"atv\":23.207398848898933,\"hname\":\"pic_o_1bdr98tn4gjjargn37c5314bl7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201704/20170416194447k3hgl.png\",\"isOnclick\":\"1\"}]},\"o_1bakkqsgr301ikn13olh315cr1t\":{\"view\":{\"hlookat\":-125.43244024042511,\"vlookat\":-4.430654575649862},\"hotspots\":[{\"ath\":-127.488126791659,\"atv\":16.379506040453023,\"linkedscene\":\"o_1bakkr84vli014b41j1jt5uve22a\",\"hname\":\"hotspot_22\"},{\"ath\":9.376044716632189,\"atv\":9.411304649398291,\"linkedscene\":\"o_1bakkrkeg6tg1mrq117247fcj02n\",\"hname\":\"hotspot_23\"}],\"picspots\":[{\"ath\":-128.88658444153907,\"atv\":-0.7432789956565123,\"hname\":\"pic_o_1ban7ao9k130ag6f161pnj01tooc\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201703/20170308230648yc3m5.png\",\"isOnclick\":null}]},\"o_1bakkqo0eac47er1fku1lh1dlt1g\":{\"view\":{\"hlookat\":2.66555748725169,\"vlookat\":0},\"hotspots\":[{\"ath\":-6.992382707015736,\"atv\":-0.47265637072481104,\"linkedscene\":\"o_1bakkqo0eac47er1fku1lh1dlt1g\",\"hname\":\"hotspot_3\"},{\"ath\":166.55888022879896,\"atv\":1.173611011568611,\"linkedscene\":\"o_1bahjke2ih3rptm8j09s1pjf1g\",\"hname\":\"hotspot_4\"}],\"picspots\":null},\"o_1bakkr84vli014b41j1jt5uve22a\":{\"view\":null,\"hotspots\":null,\"picspots\":[{\"ath\":169.45015166780502,\"atv\":55.79615183898182,\"hname\":\"pic_o_1ban79cknt6nibecf61q4eb5p7\",\"scale\":1.3733333333333333,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201703/20170308230604a5vk2.png\",\"isOnclick\":null}]},\"o_1bakkrkeg6tg1mrq117247fcj02n\":{\"view\":{\"hlookat\":2.7968044370310112,\"vlookat\":6.337069657397719},\"hotspots\":[{\"ath\":-11.502081624735183,\"atv\":-0.939537853345142,\"linkedscene\":\"o_1bahjke2ih3rptm8j09s1pjf1g\",\"hname\":\"hotspot_24\"},{\"ath\":167.5291654176252,\"atv\":-0.5715044322926466,\"linkedscene\":\"o_1bakkqsgr301ikn13olh315cr1t\",\"hname\":\"hotspot_25\"}],\"picspots\":[{\"ath\":-100.77151261383597,\"atv\":-21.489949949754592,\"hname\":\"pic_o_1ban75hs56hs19ivgr186616sqc\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201703/20170308230358n329e.jpg\",\"isOnclick\":null}]}}', null, '25', null, '', '0', null, '0', '1', '2017-03-06 10:46:56', '2017-04-20 09:11:09');
INSERT INTO `pano_proj` VALUES ('5', null, '4', '1', '测试1', null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '0', null, '', '0', null, '0', '1', '2017-04-16 11:30:50', '2017-04-16 11:30:50');
INSERT INTO `pano_proj` VALUES ('6', '1492691885612583937', '8', '0', '测试1的胜多负少', '/upload/audio/201704/201704211155257lb0c.mp3', '1', '1', '1', '', '/upload/image/n1/201704/20170420203904c35hd.png', null, null, '0', '11', null, null, null, '{\"o_1be5m01vi1jjpiga1h8p1oc91ms42n\":{\"view\":null,\"hotspots\":null,\"picspots\":[{\"ath\":-177.46462361130835,\"atv\":5.241858603280079,\"hname\":\"pic_o_1bec9tlus172m1dul1b7m1i261a0m7\",\"scale\":1,\"depth\":1000,\"rotate\":0,\"url\":\"/upload/image/n1/201704/20170423102344e8hf4.jpg\",\"isOnclick\":null}]},\"o_1be5m0g671ausq88105n1evk1p5e34\":{\"view\":null,\"hotspots\":null,\"picspots\":null}}', null, '3', null, '', '0', null, '0', '2', '2017-04-20 12:40:10', '2017-04-23 02:23:51');
INSERT INTO `pano_proj` VALUES ('7', '1494381188897258969', '4', '0', '1111', null, '0', '0', '0', '', null, null, null, '0', '2', null, null, null, null, null, '0', null, '', '0', null, '0', '1', '2017-05-10 01:53:32', '2017-05-10 01:53:32');
INSERT INTO `pano_proj` VALUES ('8', '1494381236232364705', '4', '0', '2222', null, '0', '0', '0', '', null, null, null, '0', '3', null, null, null, null, null, '0', null, '', '0', null, '0', '1', '2017-05-10 01:54:06', '2017-05-10 01:54:06');

-- ----------------------------
-- Table structure for `pano_scene`
-- ----------------------------
DROP TABLE IF EXISTS `pano_scene`;
CREATE TABLE `pano_scene` (
  `id` varchar(38) NOT NULL COMMENT 'id',
  `proj_id` bigint(20) NOT NULL COMMENT '项目id',
  `scene_title` varchar(50) NOT NULL COMMENT '场景名称',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '封面',
  `scene_src` varchar(255) NOT NULL COMMENT '场景图/全景视频',
  `hlookat` varchar(55) DEFAULT NULL COMMENT '水平视角',
  `vlookat` varchar(55) DEFAULT NULL COMMENT '垂直视角',
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
INSERT INTO `pano_scene` VALUES ('o_1bahjke2ih3rptm8j09s1pjf1g', '4', '小树林', null, '/upload/image/n1/1488797155083122853/201703061846446frre.jpg', '0.3381609287550803', '28.84573148771376', null, '1', null, null, '0', '162893', '0', '1', '2017-03-13 08:25:21', '2017-04-16 11:44:59');
INSERT INTO `pano_scene` VALUES ('o_1bakkqo0eac47er1fku1lh1dlt1g', '4', '某学校1', null, '/upload/image/n1/1488797155083122853/20170307230505zw2f0.jpg', '2.66555748725169', '0', null, '1', null, null, '0', '874566', '0', '1', '2017-03-13 08:25:21', '2017-04-16 11:44:59');
INSERT INTO `pano_scene` VALUES ('o_1bakkqsgr301ikn13olh315cr1t', '4', '30世博', null, '/upload/image/n1/1488797155083122853/20170307230518zkbt9.jpg', '-125.43244024042511', '-4.430654575649862', null, '1', null, null, '0', '535966', '0', '1', '2017-03-13 08:25:21', '2017-04-16 11:44:59');
INSERT INTO `pano_scene` VALUES ('o_1bakkr84vli014b41j1jt5uve22a', '4', '某学校2', null, '/upload/image/n1/1488797155083122853/2017030723053043eio.jpg', null, null, null, '1', null, null, '0', '186474', '0', '1', '2017-03-13 08:25:21', '2017-04-16 11:44:59');
INSERT INTO `pano_scene` VALUES ('o_1bakkrkeg6tg1mrq117247fcj02n', '4', '某学校3', null, '/upload/image/n1/1488797155083122853/201703072305422yy0t.jpg', '2.7968044370310112', '6.337069657397719', null, '1', null, null, '0', '361147', '0', '1', '2017-03-13 08:25:21', '2017-04-16 11:44:59');
INSERT INTO `pano_scene` VALUES ('o_1bdr8f024ccu1ck9107i1h8q1s54q', '5', '1024', null, '/upload/media/201704/20170416193037a2pmf.mp4', null, null, null, '0', null, null, '0', null, '0', '0', '2017-04-16 11:30:50', '2017-04-16 11:30:50');
INSERT INTO `pano_scene` VALUES ('o_1bdr8f62j1u7m12blkm4jg955217', '5', '1920', null, '/upload/media/201704/20170416193045yh46p.mp4', null, null, null, '0', null, null, '0', null, '0', '0', '2017-04-16 11:30:50', '2017-04-16 11:30:50');
INSERT INTO `pano_scene` VALUES ('o_1be5m01vi1jjpiga1h8p1oc91ms42n', '6', '123', null, '/upload/image/n1/1492691885612583937/20170420203949l4del.jpg', null, null, null, '1', null, null, '0', '674317', '0', '2', '2017-04-20 12:40:10', '2017-04-23 02:23:51');
INSERT INTO `pano_scene` VALUES ('o_1be5m0g671ausq88105n1evk1p5e34', '6', '34', null, '/upload/image/n1/1492691885612583937/20170420204003wq3iz.jpg', null, null, null, '0', null, null, '0', '776338', '0', '2', '2017-04-20 12:40:10', '2017-04-20 12:40:10');
INSERT INTO `pano_scene` VALUES ('o_1bfo0tlsf1jkr197rqbi1ker9cc1g', '7', '卧室', null, '/upload/image/n1/1494381188897258969/20170510095321wml49.jpg', null, null, null, '0', null, null, '0', '762361', '0', '1', '2017-05-10 01:53:32', '2017-05-10 01:53:32');
INSERT INTO `pano_scene` VALUES ('o_1bfo0uu3p188cejb1f5b5fi19vl1g', '8', '22222', null, '/upload/image/n1/1494381236232364705/20170510095402upcd9.jpg', null, null, null, '0', null, null, '0', '635381', '0', '1', '2017-05-10 01:54:06', '2017-05-10 01:54:06');

-- ----------------------------
-- Table structure for `pano_spots`
-- ----------------------------
DROP TABLE IF EXISTS `pano_spots`;
CREATE TABLE `pano_spots` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='全景_场景_热点';

-- ----------------------------
-- Records of pano_spots
-- ----------------------------
INSERT INTO `pano_spots` VALUES ('84', '4', 'o_1bakkr84vli014b41j1jt5uve22a', '1', null, null, 'pic_o_1ban79cknt6nibecf61q4eb5p7', '169.45015166780502', '55.79615183898182', null, '1.3733333333333333', '1000', '0', '/upload/image/n1/201703/20170308230604a5vk2.png', null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('85', '4', 'o_1bakkrkeg6tg1mrq117247fcj02n', '0', null, null, 'hotspot_24', '-11.502081624735183', '-0.939537853345142', 'o_1bahjke2ih3rptm8j09s1pjf1g', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('86', '4', 'o_1bakkrkeg6tg1mrq117247fcj02n', '0', null, null, 'hotspot_25', '167.5291654176252', '-0.5715044322926466', 'o_1bakkqsgr301ikn13olh315cr1t', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('87', '4', 'o_1bakkrkeg6tg1mrq117247fcj02n', '1', null, null, 'pic_o_1ban75hs56hs19ivgr186616sqc', '-100.77151261383597', '-21.489949949754592', null, '1', '1000', '0', '/upload/image/n1/201703/20170308230358n329e.jpg', null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('88', '4', 'o_1bahjke2ih3rptm8j09s1pjf1g', '0', null, null, 'hotspot_1', '-9.318275548709607', '41.06656645679561', 'o_1bakkqsgr301ikn13olh315cr1t', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('89', '4', 'o_1bahjke2ih3rptm8j09s1pjf1g', '0', null, null, 'hotspot_2', '176.73666665819482', '-13.447568636268821', 'o_1bakkqo0eac47er1fku1lh1dlt1g', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('90', '4', 'o_1bahjke2ih3rptm8j09s1pjf1g', '1', null, null, 'pic_o_1ban6vggn12kb1fjfd3t19iq13eu7', '-177.8515196916787', '10.817882687018898', null, '1', '1000', '0', '/upload/image/n1/201703/2017030823004003cox.jpg', null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('91', '4', 'o_1bahjke2ih3rptm8j09s1pjf1g', '1', null, null, 'pic_o_1bdr98tn4gjjargn37c5314bl7', '5.975293100110548', '23.207398848898933', null, '1', '1000', '0', '/upload/image/n1/201704/20170416194447k3hgl.png', '1', '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('92', '4', 'o_1bakkqsgr301ikn13olh315cr1t', '0', null, null, 'hotspot_22', '-127.488126791659', '16.379506040453023', 'o_1bakkr84vli014b41j1jt5uve22a', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('93', '4', 'o_1bakkqsgr301ikn13olh315cr1t', '0', null, null, 'hotspot_23', '9.376044716632189', '9.411304649398291', 'o_1bakkrkeg6tg1mrq117247fcj02n', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('94', '4', 'o_1bakkqsgr301ikn13olh315cr1t', '1', null, null, 'pic_o_1ban7ao9k130ag6f161pnj01tooc', '-128.88658444153907', '-0.7432789956565123', null, '1', '1000', '0', '/upload/image/n1/201703/20170308230648yc3m5.png', null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('95', '4', 'o_1bakkqo0eac47er1fku1lh1dlt1g', '0', null, null, 'hotspot_3', '-6.992382707015736', '-0.47265637072481104', 'o_1bakkqo0eac47er1fku1lh1dlt1g', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('96', '4', 'o_1bakkqo0eac47er1fku1lh1dlt1g', '0', null, null, 'hotspot_4', '166.55888022879896', '1.173611011568611', 'o_1bahjke2ih3rptm8j09s1pjf1g', null, null, null, null, null, '0', null, '2017-04-16 11:44:59', '2017-04-16 11:44:59');
INSERT INTO `pano_spots` VALUES ('97', '6', 'o_1be5m01vi1jjpiga1h8p1oc91ms42n', '1', null, null, 'pic_o_1bec9tlus172m1dul1b7m1i261a0m7', '-177.46462361130835', '5.241858603280079', null, '1', '1000', '0', '/upload/image/n1/201704/20170423102344e8hf4.jpg', null, '0', null, '2017-04-23 02:23:51', '2017-04-23 02:23:51');

-- ----------------------------
-- Table structure for `sys_advertise`
-- ----------------------------
DROP TABLE IF EXISTS `sys_advertise`;
CREATE TABLE `sys_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '广告名称',
  `img_url` varchar(255) NOT NULL COMMENT '图片地址',
  `link_url` varchar(255) NOT NULL COMMENT '广告链接地址',
  `state` tinyint(4) NOT NULL COMMENT '状态0下架1上架',
  `position` varchar(32) NOT NULL COMMENT '广告位置',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标记',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT NULL COMMENT '创建时间',
  `date_updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告管理';

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
  `type` tinyint(1) NOT NULL COMMENT '类型1新闻栏目2全景类目',
  `detail_info` longtext COMMENT '内容',
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统_类目';

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('4', '测试全景', '', null, '2', '', null, '', '0', null, null, '0', '1', '2017-04-16 11:30:20', '2017-04-16 11:30:20');
INSERT INTO `sys_category` VALUES ('5', '游记', 'youji', null, '1', '', null, '', '1', null, null, '0', '1', '2017-04-17 15:15:42', '2017-04-17 15:24:13');
INSERT INTO `sys_category` VALUES ('6', '风景', 'fengjing', null, '1', '', null, '', '1', null, null, '0', '1', '2017-04-17 15:15:53', '2017-04-17 15:24:07');
INSERT INTO `sys_category` VALUES ('7', '人文', 'renwen', null, '1', '<img src=\"/upload/image/n3/201704/20170417232358b5yub.gif\" alt=\"\" />', null, '', '1', null, null, '0', '1', '2017-04-17 15:18:15', '2017-04-17 15:24:01');
INSERT INTO `sys_category` VALUES ('8', '风景2', 'fengjing', null, '2', '<img src=\"/upload/image/n3/201704/20170420203755agwgm.gif\" alt=\"\" />', null, '', '1', null, null, '0', '2', '2017-04-20 12:37:57', '2017-05-03 03:01:30');

-- ----------------------------
-- Table structure for `sys_material`
-- ----------------------------
DROP TABLE IF EXISTS `sys_material`;
CREATE TABLE `sys_material` (
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='全景_素材';

-- ----------------------------
-- Records of sys_material
-- ----------------------------
INSERT INTO `sys_material` VALUES ('1', 'QQ图片20170216101026.png', '0', '/upload/image/n1/201704/20170419160142l4s5h.png', '/upload/image/n1/201704/20170419160142l4s5h.png', '0', '来源:http://120.77.212.106:6060/h/pano/proj/edit/4?bean.pageNum=1', '0', null, null, '0', '1', '2017-04-19 08:01:42', '2017-04-19 08:01:42');
INSERT INTO `sys_material` VALUES ('2', '企业信用评级管理（张玲20160913）_V0.3.docx', '3', '/upload/file/201704/201704191603065vn4l.docx', '/upload/file/201704/201704191603065vn4l.docx', '0', '来源:http://120.77.212.106:6060/h/sys/sysMaterial/edit/1?bean.pageNum=1', '0', null, null, '0', '1', '2017-04-19 08:03:06', '2017-04-19 08:03:06');
INSERT INTO `sys_material` VALUES ('3', 'QQ图片20170116144437.gif', '0', '/upload/image/n3/201704/20170420165400volco.gif', '/upload/image/n3/201704/20170420165400volco.gif', '0', '来源:http://120.77.212.106:6060/h/sys/sysCategory/edit/4?bean.pageNum=1', '0', null, null, '0', '1', '2017-04-20 08:54:01', '2017-04-20 08:54:01');
INSERT INTO `sys_material` VALUES ('4', 'QQ图片20161215140544.jpg', '0', '/upload/image/n1/201704/20170420165610i4lkt.jpg', '/upload/image/n4/201704/20170420165610i4lkt.jpg', '0', '来源:http://120.77.212.106:6060/h/sys/sysNews/edit/1?bean.pageNum=1', '0', null, null, '0', '1', '2017-04-20 08:56:10', '2017-04-20 08:56:10');
INSERT INTO `sys_material` VALUES ('5', '6adc108fly1fehfonvlwgg208c08chdt.gif', '0', '/upload/image/n3/201704/20170420203755agwgm.gif', '/upload/image/n3/201704/20170420203755agwgm.gif', '0', '来源:http://120.77.212.106:6060/m/pano/category/edit/0', '0', null, null, '0', '2', '2017-04-20 12:37:55', '2017-04-20 12:37:55');
INSERT INTO `sys_material` VALUES ('6', '9B0C5E43F7BA3EDD66F37E1E5C0E0CCF.png', '0', '/upload/image/n1/201704/20170420203855dhj6x.png', '/upload/image/n4/201704/20170420203855dhj6x.png', '0', '来源:http://120.77.212.106:6060/m/pano/proj/edit/0', '1', null, null, '1', '2', '2017-04-20 12:38:55', '2017-04-23 14:07:56');
INSERT INTO `sys_material` VALUES ('7', '3.png', '0', '/upload/image/n1/201704/20170420203904c35hd.png', '/upload/image/n4/201704/20170420203904c35hd.png', '0', '来源:http://120.77.212.106:6060/m/pano/proj/edit/0', '0', null, null, '0', '2', '2017-04-20 12:39:04', '2017-04-20 12:39:04');
INSERT INTO `sys_material` VALUES ('8', '20161206225600ewku3.jpg', '4', '/upload/image/n1/1492691885612583937/20170420203949l4del.jpg', '/upload/image/n4/1492691885612583937/20170420203949l4del.jpg', '0', '来源:http://120.77.212.106:6060/m/pano/proj/edit/0', '0', null, null, '0', '2', '2017-04-20 12:39:50', '2017-04-20 12:39:50');
INSERT INTO `sys_material` VALUES ('9', '201612062301593ddbg.jpg', '4', '/upload/image/n1/1492691885612583937/20170420204003wq3iz.jpg', '/upload/image/n4/1492691885612583937/20170420204003wq3iz.jpg', '0', '来源:http://120.77.212.106:6060/m/pano/proj/edit/0', '1', null, null, '1', '2', '2017-04-20 12:40:05', '2017-04-20 12:43:26');
INSERT INTO `sys_material` VALUES ('10', '201704151918072ca2e.mp3', '1', '/img/audio.png', '/upload/audio/201704/201704211155257lb0c.mp3', '0', '来源:http://120.77.212.106:6060/h/pano/proj/edit/6?pageNum=1', '0', null, null, '0', '1', '2017-04-21 03:55:25', '2017-04-21 03:55:25');
INSERT INTO `sys_material` VALUES ('11', 'psb.webp (1).jpgw', '0', '/upload/image/n1/201704/20170423102344e8hf4.jpg', '/upload/image/n4/201704/20170423102344e8hf4.jpg', '0', '来源:http://120.77.212.106:6060/h/touredit/6', '1', null, null, '0', '1', '2017-04-23 02:23:44', '2017-05-03 03:01:38');
INSERT INTO `sys_material` VALUES ('12', '001.jpg', '4', '/upload/image/n1/1494381188897258969/20170510095321wml49.jpg', '/upload/image/n4/1494381188897258969/20170510095321wml49.jpg', '0', '来源:http://120.77.212.106:6060/h/pano/proj/edit/0', '0', null, null, '0', '1', '2017-05-10 01:53:21', '2017-05-10 01:53:21');
INSERT INTO `sys_material` VALUES ('13', '001.jpg', '4', '/upload/image/n1/1494381236232364705/20170510095402upcd9.jpg', '/upload/image/n4/1494381236232364705/20170510095402upcd9.jpg', '0', '来源:http://120.77.212.106:6060/h/pano/proj/edit/0', '0', null, null, '0', '1', '2017-05-10 01:54:02', '2017-05-10 01:54:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统_资讯';

-- ----------------------------
-- Records of sys_news
-- ----------------------------
INSERT INTO `sys_news` VALUES ('1', '上海', '', '/upload/image/n1/201704/20170420165610i4lkt.jpg', '', '<img src=\"/upload/image/n4/201704/201704172321462cc2e.gif\" alt=\"\" />', '0', null, null, null, '1', null, null, '0', '1', '2017-04-17 15:22:18', '2017-04-20 08:56:12');

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
INSERT INTO `sys_news_vs_category` VALUES ('1', '5', '2017-04-20 08:56:12');

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
-- Table structure for `sys_sensitive_words`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sensitive_words`;
CREATE TABLE `sys_sensitive_words` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `date_updated` datetime DEFAULT '2000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系统_敏感词';

-- ----------------------------
-- Records of sys_sensitive_words
-- ----------------------------
INSERT INTO `sys_sensitive_words` VALUES ('1', '法轮功', '', '1', '2017-04-17 15:13:38', '2017-05-03 03:16:33');
INSERT INTO `sys_sensitive_words` VALUES ('2', '恐怖份子', '', '1', '2017-04-17 15:13:59', '2017-04-17 15:13:59');

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
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='系统_管理员操作日志';

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------
INSERT INTO `sys_user_log` VALUES ('31', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=null, isComments=1, isPlanetoid=1, isFps=1, snowType=snowflakes, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-06 10:46:57', '1', '超级管理员', '101.204.218.13');
INSERT INTO `sys_user_log` VALUES ('32', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=null, isComments=1, isPlanetoid=1, isFps=1, snowType=snowflakes, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-06 14:42:12', '1', '超级管理员', '171.213.57.111');
INSERT INTO `sys_user_log` VALUES ('33', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=null, isComments=1, isPlanetoid=1, isFps=1, snowType=snowflakes, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-07 07:05:55', '1', '超级管理员', '101.204.218.13');
INSERT INTO `sys_user_log` VALUES ('34', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=null, isComments=1, isPlanetoid=1, isFps=1, snowType=snowflakes, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bajsjkrh7lqn7u1oomn9l1nn11g, projId=4, sceneTitle=600M大图, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307162833cwjmk.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=1, keyword=355966, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-07 08:35:23', '1', '超级管理员', '101.204.218.13');
INSERT INTO `sys_user_log` VALUES ('35', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/201703072308007wkhu.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=snowflakes, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqo0eac47er1fku1lh1dlt1g, projId=4, sceneTitle=某学校1, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230505zw2f0.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=1, keyword=874566, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqsgr301ikn13olh315cr1t, projId=4, sceneTitle=30世博, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230518zkbt9.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=2, keyword=535966, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkr84vli014b41j1jt5uve22a, projId=4, sceneTitle=某学校2, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/2017030723053043eio.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=3, keyword=186474, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkrkeg6tg1mrq117247fcj02n, projId=4, sceneTitle=某学校3, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703072305422yy0t.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=4, keyword=361147, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-07 15:08:29', '1', '超级管理员', '171.213.57.111');
INSERT INTO `sys_user_log` VALUES ('36', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/20170308225747xj37d.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=heavyrain, mapSrc=/upload/image/n1/201703/20170308225923jhvoi.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-03-08 14:59:26', '1', '超级管理员', '171.214.183.100');
INSERT INTO `sys_user_log` VALUES ('37', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/201703122036207yexx.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201703/20170308225923jhvoi.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-03-12 12:36:38', '1', '超级管理员', '171.214.183.100');
INSERT INTO `sys_user_log` VALUES ('38', '修改', '数据字典信息', '[SysVariable(id=1, code=1, name=1, parentId=null, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=1, tags=[0])]', '2017-03-12 12:46:39', '1', '超级管理员', '171.214.183.100');
INSERT INTO `sys_user_log` VALUES ('39', '修改', '数据字典信息', '[SysVariable(id=1, code=1, name=1, parentId=null, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=1, tags=[0])]', '2017-03-12 12:46:52', '1', '超级管理员', '171.214.183.100');
INSERT INTO `sys_user_log` VALUES ('40', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/201703122036207yexx.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201703/20170308225923jhvoi.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqsgr301ikn13olh315cr1t, projId=4, sceneTitle=30世博, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230518zkbt9.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=1, keyword=535966, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqo0eac47er1fku1lh1dlt1g, projId=4, sceneTitle=某学校1, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230505zw2f0.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=2, keyword=874566, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkr84vli014b41j1jt5uve22a, projId=4, sceneTitle=某学校2, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/2017030723053043eio.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=3, keyword=186474, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkrkeg6tg1mrq117247fcj02n, projId=4, sceneTitle=某学校3, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703072305422yy0t.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=4, keyword=361147, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-13 08:22:49', '1', '超级管理员', '101.204.216.153');
INSERT INTO `sys_user_log` VALUES ('41', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/201703122036207yexx.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201703/20170308225923jhvoi.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bahjke2ih3rptm8j09s1pjf1g, projId=4, sceneTitle=小树林, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703061846446frre.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=162893, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqsgr301ikn13olh315cr1t, projId=4, sceneTitle=30世博, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230518zkbt9.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=1, keyword=535966, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkqo0eac47er1fku1lh1dlt1g, projId=4, sceneTitle=某学校1, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/20170307230505zw2f0.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=2, keyword=874566, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkr84vli014b41j1jt5uve22a, projId=4, sceneTitle=某学校2, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/2017030723053043eio.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=3, keyword=186474, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bakkrkeg6tg1mrq117247fcj02n, projId=4, sceneTitle=某学校3, logoUrl=null, sceneSrc=/upload/image/n1/1488797155083122853/201703072305422yy0t.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=4, keyword=361147, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-03-13 08:25:22', '1', '超级管理员', '101.204.216.153');
INSERT INTO `sys_user_log` VALUES ('42', '删除', '全景评论信息', '[1]', '2017-03-13 08:25:31', '1', '超级管理员', '101.204.216.153');
INSERT INTO `sys_user_log` VALUES ('43', '删除', '全景评论信息', '[1]', '2017-03-14 05:55:30', '1', '超级管理员', '101.204.216.153');
INSERT INTO `sys_user_log` VALUES ('44', '删除', '全景评论信息', '[1]', '2017-03-14 06:14:13', '1', '超级管理员', '101.204.216.153');
INSERT INTO `sys_user_log` VALUES ('45', '删除', '全景评论信息', '[4]', '2017-03-15 15:39:27', '1', '超级管理员', '171.213.56.144');
INSERT INTO `sys_user_log` VALUES ('46', '删除', '全景评论信息', '[2]', '2017-03-15 15:39:29', '1', '超级管理员', '171.213.56.144');
INSERT INTO `sys_user_log` VALUES ('47', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201703/201703122036207yexx.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/20170410232050kbv98.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-10 15:20:55', '1', '超级管理员', '171.217.40.181');
INSERT INTO `sys_user_log` VALUES ('48', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=1, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-15 11:18:29', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('49', '修改', '系统_类目', '[SysCategory(id=4, name=测试全景, code=, parentId=null, state=null, type=2, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-16 11:30:20', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('50', '修改', '全景视频信息', '[PanoProj(id=5, code=null, categoryId=4, type=1, name=测试1, soundSrc=null, isComments=null, isPlanetoid=null, isFps=null, snowType=null, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bdr8f024ccu1ck9107i1h8q1s54q, projId=5, sceneTitle=1024, logoUrl=null, sceneSrc=/upload/media/201704/20170416193037a2pmf.mp4, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=null, delFlag=null, createId=0, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null), PanoScene(id=o_1bdr8f62j1u7m12blkm4jg955217, projId=5, sceneTitle=1920, logoUrl=null, sceneSrc=/upload/media/201704/20170416193045yh46p.mp4, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=1, keyword=null, delFlag=null, createId=0, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=false)]', '2017-04-16 11:30:50', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('51', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-16 11:31:39', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('52', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-16 11:31:52', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('53', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-16 11:35:18', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('54', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-16 11:44:23', '1', '超级管理员', '171.214.181.86');
INSERT INTO `sys_user_log` VALUES ('55', '修改', '部门信息', '[OrgDept(id=1, code=11, name=测试部门, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=测试部门, tags=[0])]', '2017-04-17 15:01:33', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('56', '修改', '部门信息', '[OrgDept(id=1, code=11, name=综合部, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=综合部, tags=[0])]', '2017-04-17 15:01:58', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('57', '修改', '部门信息', '[OrgDept(id=2, code=yanfabu, name=研发部, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=研发部, tags=[0])]', '2017-04-17 15:02:07', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('58', '修改', '部门信息', '[OrgDept(id=1, code=zonghebu, name=综合部, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=综合部, tags=[0])]', '2017-04-17 15:02:14', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('59', '修改', '部门信息', '[OrgDept(id=3, code=yunweibu, name=运维部, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=运维部, tags=[0])]', '2017-04-17 15:02:29', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('60', '修改', '部门信息', '[OrgDept(id=4, code=renshibu, name=人事部, parentId=null, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=人事部, tags=[0])]', '2017-04-17 15:02:39', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('61', '修改', '部门信息', '[OrgDept(id=5, code=ceshizu, name=测试组, parentId=2, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=测试组, tags=[0])]', '2017-04-17 15:03:48', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('62', '修改', '部门信息', '[OrgDept(id=6, code=javahoutai, name=java后台, parentId=2, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=java后台, tags=[0])]', '2017-04-17 15:04:31', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('63', '修改', '部门信息', '[OrgDept(id=7, code=qianduanzu, name=前端组, parentId=2, state=null, memo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=前端组, tags=[0])]', '2017-04-17 15:04:55', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('64', '修改', '权限信息', '[AuthPerm(id=id_sys_category, name=类目管理, matchStr=sysCategory:menu, parentId=id_sys, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=类目管理, tags=[0])]', '2017-04-17 15:05:35', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('65', '修改', '权限信息', '[AuthPerm(id=id_sys_message, name=系统信息管理, matchStr=sysMessage:menu, parentId=id_sys, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=系统信息管理, tags=[0])]', '2017-04-17 15:05:58', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('66', '修改', '权限信息', '[AuthPerm(id=id_sys_pano_plugins, name=全景插件管理, matchStr=sysPanoPlugins:menu, parentId=id_sys, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景插件管理, tags=[0])]', '2017-04-17 15:06:30', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('67', '修改', '数据字典信息', '[SysVariable(id=1, code=tags, name=标签, parentId=null, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=标签, tags=[0])]', '2017-04-17 15:09:51', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('68', '修改', '数据字典信息', '[SysVariable(id=2, code=fengjing, name=风景, parentId=1, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=风景, tags=[0])]', '2017-04-17 15:10:17', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('69', '修改', '数据字典信息', '[SysVariable(id=3, code=renwen, name=人文, parentId=1, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=人文, tags=[0])]', '2017-04-17 15:10:31', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('70', '修改', '数据字典信息', '[SysVariable(id=4, code=youji, name=游记, parentId=1, memo=, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=游记, tags=[0])]', '2017-04-17 15:12:43', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('71', '修改', '系统_敏感词', '[SysSensitiveWords(id=1, name=法轮功, memo=, createId=1, dateCreated=null, dateUpdated=null)]', '2017-04-17 15:13:38', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('72', '修改', '系统_敏感词', '[SysSensitiveWords(id=2, name=恐怖份子, memo=, createId=1, dateCreated=null, dateUpdated=null)]', '2017-04-17 15:13:59', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('73', '修改', '资讯_栏目', '[SysCategory(id=5, name=游记, code=, parentId=null, state=null, type=1, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:15:42', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('74', '修改', '资讯_栏目', '[SysCategory(id=6, name=风景, code=, parentId=null, state=null, type=1, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:15:53', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('75', '修改', '资讯_栏目', '[SysCategory(id=7, name=人文, code=, parentId=null, state=null, type=1, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:18:15', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('76', '修改', '系统_资讯', '[SysNews(id=null, title=上海东方明珠, secondTitle=, coverPic=/upload/image/n1/201704/20170417232113idk13.png, briefInfo=, detailInfo=<img src=\"/upload/image/n3/201704/201704172321462cc2e.gif\" alt=\"\" />, isOntop=null, clickCount=null, state=null, memo=null, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, categoryId=null, categorys=[])]', '2017-04-17 15:21:52', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('77', '修改', '系统_资讯', '[SysNews(id=1, title=上海, secondTitle=, coverPic=null, briefInfo=, detailInfo=<img src=\"/upload/image/n4/201704/201704172321462cc2e.gif\" alt=\"\" />, isOntop=null, clickCount=null, state=null, memo=null, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, categoryId=null, categorys=[5])]', '2017-04-17 15:22:18', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('78', '修改', '资讯_栏目', '[SysCategory(id=7, name=人文, code=renwen, parentId=null, state=null, type=1, memo=, detailInfo=<img src=\"/upload/image/n3/201704/20170417232358b5yub.gif\" alt=\"\" />, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:24:01', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('79', '修改', '资讯_栏目', '[SysCategory(id=6, name=风景, code=fengjing, parentId=null, state=null, type=1, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:24:07', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('80', '修改', '资讯_栏目', '[SysCategory(id=5, name=游记, code=youji, parentId=null, state=null, type=1, memo=, detailInfo=, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null)]', '2017-04-17 15:24:13', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('81', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=null)]', '2017-04-17 15:26:59', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('82', '修改', '权限信息', '[OrgUser(id=2, accid=admin, pwd=, name=会员, jobNo=, position=null, gender=null, mobile=, tel=, enable=1, avatar=null, email=, weixinid=null, type=0, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=[2], roleNames=null, deptIdArray=null)]', '2017-04-17 15:27:11', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('83', '修改', '权限信息', '[OrgUser(id=2, accid=admin, pwd=7af88f1d651067943c4aff4d7023ef55, name=会员, jobNo=, position=null, gender=null, mobile=, tel=, enable=1, avatar=null, email=, weixinid=null, type=0, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=[2], roleNames=null, deptIdArray=null)]', '2017-04-17 15:27:22', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('84', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[id_pano, id_pano_comment, id_pano_comment_add, id_pano_comment_del, id_pano_comment_edit, id_pano_comment_save, id_pano_material, id_pano_material_add, id_pano_material_edit, id_pano_proj, id_pano_proj_add, id_pano_proj_del, id_pano_proj_edit, id_pano_proj_save, id_video, id_video_add, id_video_del, id_video_edit, id_video_save])]', '2017-04-18 08:00:44', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('85', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[id_pano, id_pano_comment, id_pano_comment_add, id_pano_comment_del, id_pano_comment_edit, id_pano_comment_save, id_pano_material, id_pano_material_add, id_pano_material_edit, id_pano_proj, id_pano_proj_add, id_pano_proj_del, id_pano_proj_edit, id_pano_proj_save, id_video, id_video_add, id_video_del, id_video_edit, id_video_save])]', '2017-04-18 08:01:09', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('86', '修改', '权限信息', '[AuthPerm(id=ed5bd20489ba4ac5900339, name=密码修改, matchStr=orgUser:editPwd, parentId=id_org_user, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=密码修改, tags=[0])]', '2017-04-19 03:42:37', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('87', '修改', '用户密码', '[OrgUser(id=2, accid=null, pwd=null, name=null, jobNo=null, position=null, gender=null, mobile=null, tel=null, enable=null, avatar=null, email=null, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=7af88f1d651067943c4aff4d7023ef55, newpwd=7af88f1d651067943c4aff4d7023ef55, confirmpwd=7af88f1d651067943c4aff4d7023ef55)]', '2017-04-19 03:43:42', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('88', '修改', '用户密码', '[OrgUser(id=2, accid=null, pwd=null, name=null, jobNo=null, position=null, gender=null, mobile=null, tel=null, enable=null, avatar=null, email=null, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=7af88f1d651067943c4aff4d7023ef55, newpwd=7af88f1d651067943c4aff4d7023ef55, confirmpwd=7af88f1d651067943c4aff4d7023ef55)]', '2017-04-19 03:44:03', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('89', '修改', '权限信息', '[AuthPerm(id=id_pano_material, name=全景_素材, matchStr=sysMaterial:menu, parentId=id_pano, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景_素材, tags=[0])]', '2017-04-19 08:02:07', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('90', '修改', '权限信息', '[AuthPerm(id=id_pano_material_add, name=新增, matchStr=sysMaterial:add, parentId=id_pano_material, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=新增, tags=[0])]', '2017-04-19 08:02:13', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('91', '修改', '权限信息', '[AuthPerm(id=id_pano_material_del, name=删除, matchStr=sysMaterial:del, parentId=id_pano_material, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=删除, tags=[0])]', '2017-04-19 08:02:19', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('92', '修改', '权限信息', '[AuthPerm(id=id_pano_material_edit, name=编辑, matchStr=sysMaterial:edit, parentId=id_pano_material, memo=系统生成, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=编辑, tags=[0])]', '2017-04-19 08:02:27', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('93', '修改', '权限信息', '[AuthPerm(id=91e542f84786488db435f2, name=素材下载, matchStr=sysMaterial:down, parentId=id_pano_material, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=素材下载, tags=[0])]', '2017-04-19 08:11:55', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('94', '修改', '权限信息', '[OrgUser(id=2, accid=admin, pwd=null, name=会员, jobNo=, position=null, gender=null, mobile=, tel=, enable=1, avatar=null, email=, weixinid=null, type=0, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=[2], roleNames=null, deptIdArray=null, oldpwd=null, newpwd=null, confirmpwd=null)]', '2017-04-19 09:39:48', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('95', '修改', '权限信息', '[AuthPerm(id=id_pano, name=全景管理, matchStr=pano:menu, parentId=, memo= , orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景管理, tags=[0])]', '2017-04-19 13:28:43', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('96', '修改', '权限信息', '[AuthPerm(id=3185edd42f5b4df6824d25, name=后台系统, matchStr=admin:menu, parentId=, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=后台系统, tags=[0])]', '2017-04-19 13:29:47', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('97', '修改', '权限信息', '[AuthPerm(id=id_news_memu, name=资讯管理, matchStr=newsManager:menu, parentId=3185edd42f5b4df6824d25, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=资讯管理, tags=[0])]', '2017-04-19 13:29:54', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('98', '修改', '权限信息', '[AuthPerm(id=id_pano, name=全景管理, matchStr=pano:menu, parentId=3185edd42f5b4df6824d25, memo= , orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景管理, tags=[0])]', '2017-04-19 13:29:59', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('99', '修改', '权限信息', '[AuthPerm(id=id_sys, name=系统管理, matchStr=sys:menu, parentId=3185edd42f5b4df6824d25, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=系统管理, tags=[0])]', '2017-04-19 13:30:09', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('100', '修改', '权限信息', '[AuthPerm(id=1cadb4eef480407d83b31e, name=会员中心, matchStr=member:menu, parentId=, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=会员中心, tags=[0])]', '2017-04-19 13:30:51', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('101', '修改', '权限信息', '[AuthPerm(id=c0f61f6b92424903a1b5ef, name=素材库, matchStr=memMaterial:menu, parentId=1cadb4eef480407d83b31e, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=素材库, tags=[0])]', '2017-04-19 13:32:09', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('102', '修改', '权限信息', '[AuthPerm(id=bac7d3c858584afca8cbd7, name=新增, matchStr=memMaterial:add, parentId=c0f61f6b92424903a1b5ef, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=新增, tags=[0])]', '2017-04-19 13:32:26', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('103', '修改', '权限信息', '[AuthPerm(id=05122cc6a494414ea16d52, name=编辑, matchStr=memMaterial:edit, parentId=c0f61f6b92424903a1b5ef, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=编辑, tags=[0])]', '2017-04-19 13:32:43', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('104', '修改', '权限信息', '[AuthPerm(id=dbdb461ab5b34b60884eb4, name=删除, matchStr=memMaterial:del, parentId=c0f61f6b92424903a1b5ef, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=删除, tags=[0])]', '2017-04-19 13:32:58', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('105', '修改', '权限信息', '[AuthPerm(id=a08952ccdb324d7ebf79fb, name=下载, matchStr=memMaterial:down, parentId=c0f61f6b92424903a1b5ef, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=下载, tags=[0])]', '2017-04-19 13:33:22', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('106', '修改', '权限信息', '[AuthPerm(id=4f57ba29bd3e4358bab5ce, name=全景作品, matchStr=memPano:menu, parentId=1cadb4eef480407d83b31e, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景作品, tags=[0])]', '2017-04-19 13:34:06', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('107', '修改', '权限信息', '[AuthPerm(id=8805b6bb01f9423f937706, name=全景类目, matchStr=memCategory:menu, parentId=4f57ba29bd3e4358bab5ce, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景类目, tags=[0])]', '2017-04-19 13:34:33', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('108', '修改', '权限信息', '[AuthPerm(id=1093ab35ed87423f981ff8, name=标签管理, matchStr=memTags:menu, parentId=4f57ba29bd3e4358bab5ce, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=标签管理, tags=[0])]', '2017-04-19 13:34:58', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('109', '修改', '权限信息', '[AuthPerm(id=27300083263149d4b1ae4c, name=全景摄影, matchStr=memPanoProj:menu, parentId=4f57ba29bd3e4358bab5ce, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景摄影, tags=[0])]', '2017-04-19 13:35:24', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('110', '修改', '权限信息', '[AuthPerm(id=8540a7b87685446f868e9a, name=全景视频, matchStr=memVideo:menu, parentId=4f57ba29bd3e4358bab5ce, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=全景视频, tags=[0])]', '2017-04-19 13:35:46', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('111', '修改', '权限信息', '[AuthPerm(id=1cbe3181e3804cd98ba5a5, name=作品评论, matchStr=memPanoComment:menu, parentId=4f57ba29bd3e4358bab5ce, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=作品评论, tags=[0])]', '2017-04-19 13:36:13', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('112', '修改', '权限信息', '[AuthPerm(id=6a4209b8e11b45f9ac534d, name=账户管理, matchStr=account:menu, parentId=1cadb4eef480407d83b31e, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=账户管理, tags=[0])]', '2017-04-19 13:37:03', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('113', '修改', '权限信息', '[AuthPerm(id=9843b6f9f72c44f98d4ec6, name=信息中心, matchStr=message:menu, parentId=1cadb4eef480407d83b31e, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=信息中心, tags=[0])]', '2017-04-19 13:37:30', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('114', '修改', '权限信息', '[AuthPerm(id=67620b1e15d74be29eb5fd, name=我的账户, matchStr=myacount:menu, parentId=6a4209b8e11b45f9ac534d, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=我的账户, tags=[0])]', '2017-04-19 13:38:03', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('115', '修改', '权限信息', '[AuthPerm(id=db309d19d01b4a059d5310, name=个人资料, matchStr=myinfo:menu, parentId=6a4209b8e11b45f9ac534d, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=个人资料, tags=[0])]', '2017-04-19 13:38:27', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('116', '修改', '权限信息', '[AuthPerm(id=670f01241e3241a9a93bf6, name=会员认证, matchStr=memberAccount:menu, parentId=6a4209b8e11b45f9ac534d, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=会员认证, tags=[0])]', '2017-04-19 13:39:38', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('117', '修改', '权限信息', '[AuthPerm(id=67620b1e15d74be29eb5fd, name=我的账户, matchStr=myAccount:menu, parentId=6a4209b8e11b45f9ac534d, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=我的账户, tags=[0])]', '2017-04-19 13:39:54', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('118', '修改', '权限信息', '[AuthPerm(id=e4090e8d98b945d7b0dc57, name=系统消息, matchStr=sysMessage:menu, parentId=9843b6f9f72c44f98d4ec6, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=系统消息, tags=[0])]', '2017-04-19 13:40:30', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('119', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[1cadb4eef480407d83b31e, 4f57ba29bd3e4358bab5ce, 1093ab35ed87423f981ff8, 1cbe3181e3804cd98ba5a5, 27300083263149d4b1ae4c, 8540a7b87685446f868e9a, 8805b6bb01f9423f937706, 6a4209b8e11b45f9ac534d, 670f01241e3241a9a93bf6, 67620b1e15d74be29eb5fd, db309d19d01b4a059d5310, 9843b6f9f72c44f98d4ec6, e4090e8d98b945d7b0dc57, c0f61f6b92424903a1b5ef, 05122cc6a494414ea16d52, a08952ccdb324d7ebf79fb, bac7d3c858584afca8cbd7, dbdb461ab5b34b60884eb4, id_pano, id_pano_comment, id_pano_comment_add, id_pano_comment_del, id_pano_comment_edit, id_pano_comment_save, id_pano_material, id_pano_material_add, id_pano_material_edit, id_pano_proj, id_pano_proj_add, id_pano_proj_del, id_pano_proj_edit, id_pano_proj_save, id_video, id_video_add, id_video_del, id_video_edit, id_video_save])]', '2017-04-19 13:50:49', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('120', '修改', '系统_资讯', '[SysNews(id=1, title=上海, secondTitle=, coverPic=/upload/image/n1/201704/20170420165610i4lkt.jpg, briefInfo=, detailInfo=<img src=\"/upload/image/n4/201704/201704172321462cc2e.gif\" alt=\"\" />, isOntop=0, clickCount=null, state=null, memo=null, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, categoryId=null, categorys=[5])]', '2017-04-20 08:56:12', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('121', '修改', '权限信息', '[AuthPerm(id=96eb4716287149a5988164, name=标签新增, matchStr=memTags:add, parentId=1093ab35ed87423f981ff8, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=标签新增, tags=[0])]', '2017-04-20 09:03:05', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('122', '修改', '权限信息', '[AuthPerm(id=0091e1888f1c49a98dcbee, name=标签编辑, matchStr=memTags:edit, parentId=1093ab35ed87423f981ff8, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=标签编辑, tags=[0])]', '2017-04-20 09:03:27', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('123', '修改', '权限信息', '[AuthPerm(id=092f38a86fd14112a48297, name=标签删除, matchStr=memTags:del, parentId=1093ab35ed87423f981ff8, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=标签删除, tags=[0])]', '2017-04-20 09:03:41', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('124', '修改', '权限信息', '[AuthPerm(id=438a0b19010841308e2a9c, name=评论删除, matchStr=memPanoComment:del, parentId=1cbe3181e3804cd98ba5a5, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=评论删除, tags=[0])]', '2017-04-20 09:04:29', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('125', '修改', '权限信息', '[AuthPerm(id=1e168a459ecd4351bddc17, name=编辑, matchStr=memPanoProj:edit, parentId=27300083263149d4b1ae4c, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=编辑, tags=[0])]', '2017-04-20 09:05:38', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('126', '修改', '权限信息', '[AuthPerm(id=8d986e2ec84245fa8fa28b, name=新增, matchStr=memPanoProj:add, parentId=27300083263149d4b1ae4c, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=新增, tags=[0])]', '2017-04-20 09:05:52', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('127', '修改', '权限信息', '[AuthPerm(id=0318e9eec64349f6b7a7f7, name=删除, matchStr=memPanoProj:del, parentId=27300083263149d4b1ae4c, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=删除, tags=[0])]', '2017-04-20 09:06:04', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('128', '修改', '权限信息', '[AuthPerm(id=7d29b6cb862649538821d6, name=新增, matchStr=memVideo:add, parentId=8540a7b87685446f868e9a, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=新增, tags=[0])]', '2017-04-20 09:07:02', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('129', '修改', '权限信息', '[AuthPerm(id=3af2416c21b0432f928c43, name=编辑, matchStr=memVideo:edit, parentId=8540a7b87685446f868e9a, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=编辑, tags=[0])]', '2017-04-20 09:07:14', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('130', '修改', '权限信息', '[AuthPerm(id=e1e4648de4f8457cbb2e1c, name=删除, matchStr=memVideo:del, parentId=8540a7b87685446f868e9a, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=删除, tags=[0])]', '2017-04-20 09:07:24', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('131', '修改', '权限信息', '[AuthPerm(id=e36d97ef220c41d58b7e6b, name=新增, matchStr=memCategory:add, parentId=8805b6bb01f9423f937706, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=新增, tags=[0])]', '2017-04-20 09:08:12', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('132', '修改', '权限信息', '[AuthPerm(id=28d6e012bbff47d3bc4ef6, name=编辑, matchStr=memCategory:edit, parentId=8805b6bb01f9423f937706, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=编辑, tags=[0])]', '2017-04-20 09:08:27', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('133', '修改', '权限信息', '[AuthPerm(id=379770c49acb44ee841aa3, name=删除, matchStr=memCategory:del, parentId=8805b6bb01f9423f937706, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=删除, tags=[0])]', '2017-04-20 09:08:36', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('134', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=0, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-20 09:11:04', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('135', '修改', '全景项目信息', '[PanoProj(id=4, code=1488797155083122853, categoryId=4, type=0, name=测试1, soundSrc=/upload/file/201704/201704151918072ca2e.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/201704151917517mcm3.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-20 09:11:09', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('136', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[1cadb4eef480407d83b31e, 4f57ba29bd3e4358bab5ce, 1093ab35ed87423f981ff8, 0091e1888f1c49a98dcbee, 092f38a86fd14112a48297, 96eb4716287149a5988164, 1cbe3181e3804cd98ba5a5, 438a0b19010841308e2a9c, 27300083263149d4b1ae4c, 0318e9eec64349f6b7a7f7, 1e168a459ecd4351bddc17, 8d986e2ec84245fa8fa28b, 8540a7b87685446f868e9a, 3af2416c21b0432f928c43, 7d29b6cb862649538821d6, e1e4648de4f8457cbb2e1c, 8805b6bb01f9423f937706, 28d6e012bbff47d3bc4ef6, 379770c49acb44ee841aa3, e36d97ef220c41d58b7e6b, 6a4209b8e11b45f9ac534d, 670f01241e3241a9a93bf6, 67620b1e15d74be29eb5fd, db309d19d01b4a059d5310, 9843b6f9f72c44f98d4ec6, e4090e8d98b945d7b0dc57, c0f61f6b92424903a1b5ef, 05122cc6a494414ea16d52, a08952ccdb324d7ebf79fb, bac7d3c858584afca8cbd7, dbdb461ab5b34b60884eb4, id_pano, id_pano_comment, id_pano_comment_add, id_pano_comment_del, id_pano_comment_edit, id_pano_comment_save, id_pano_material, id_pano_material_add, id_pano_material_edit, id_pano_proj, id_pano_proj_add, id_pano_proj_del, id_pano_proj_edit, id_pano_proj_save, id_video, id_video_add, id_video_del, id_video_edit, id_video_save])]', '2017-04-20 09:18:14', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('137', '修改', '角色信息', '[AuthRole(id=2, name=全景会员, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[1cadb4eef480407d83b31e, 4f57ba29bd3e4358bab5ce, 1093ab35ed87423f981ff8, 0091e1888f1c49a98dcbee, 092f38a86fd14112a48297, 96eb4716287149a5988164, 1cbe3181e3804cd98ba5a5, 438a0b19010841308e2a9c, 27300083263149d4b1ae4c, 0318e9eec64349f6b7a7f7, 1e168a459ecd4351bddc17, 8d986e2ec84245fa8fa28b, 8540a7b87685446f868e9a, 3af2416c21b0432f928c43, 7d29b6cb862649538821d6, e1e4648de4f8457cbb2e1c, 8805b6bb01f9423f937706, 28d6e012bbff47d3bc4ef6, 379770c49acb44ee841aa3, e36d97ef220c41d58b7e6b, 6a4209b8e11b45f9ac534d, 670f01241e3241a9a93bf6, 67620b1e15d74be29eb5fd, db309d19d01b4a059d5310, 9843b6f9f72c44f98d4ec6, e4090e8d98b945d7b0dc57, c0f61f6b92424903a1b5ef, 05122cc6a494414ea16d52, a08952ccdb324d7ebf79fb, bac7d3c858584afca8cbd7, dbdb461ab5b34b60884eb4])]', '2017-04-20 10:26:18', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('138', '修改', '权限信息', '[AuthPerm(id=c54bdcdfd44947ffa997d6, name=密码修改, matchStr=memOrgUser:editPwd, parentId=db309d19d01b4a059d5310, memo=, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, nodes=null, text=密码修改, tags=[0])]', '2017-04-20 12:18:19', '1', '超级管理员', '171.217.43.119');
INSERT INTO `sys_user_log` VALUES ('139', '修改', '全景项目信息', '[PanoProj(id=6, code=1492691885612583937, categoryId=8, type=0, name=测试1, soundSrc=/upload/audio/201704/201704211155257lb0c.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/20170420203904c35hd.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-21 03:55:52', '1', '超级管理员', '112.193.145.111');
INSERT INTO `sys_user_log` VALUES ('140', '修改', '全景项目信息', '[PanoProj(id=6, code=1492691885612583937, categoryId=8, type=0, name=测试1的胜多负少, soundSrc=/upload/audio/201704/201704211155257lb0c.mp3, isComments=1, isPlanetoid=1, isFps=1, snowType=, mapSrc=/upload/image/n1/201704/20170420203904c35hd.png, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=null, radars=null, makePanoFlag=false)]', '2017-04-22 12:16:32', '1', '超级管理员', '171.213.58.160');
INSERT INTO `sys_user_log` VALUES ('141', '删除', '全景_素材', '[6]', '2017-04-23 14:07:56', '1', '超级管理员', '171.213.58.160');
INSERT INTO `sys_user_log` VALUES ('142', '修改', '系统_类目', '[SysCategory(id=8, name=风景2, code=fengjing, parentId=null, state=null, type=2, memo=, detailInfo=<img src=\"/upload/image/n3/201704/20170420203755agwgm.gif\" alt=\"\" />, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, nodes=null, text=风景2, tags=[0])]', '2017-05-03 03:01:30', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('143', '修改', '全景_素材', '[SysMaterial(id=11, name=psb.webp (1).jpgw, type=null, logoUrl=null, materialUrl=null, state=null, memo=来源:http://120.77.212.106:6060/h/touredit/6, version=null, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null)]', '2017-05-03 03:01:38', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('144', '修改', '角色信息', '[AuthRole(id=2, name=全景会员2, isSuper=0, memo=null, orderNo=null, version=null, keyword=null, delFlag=null, dateCreated=null, dateUpdated=null, permIdArray=[1cadb4eef480407d83b31e, 4f57ba29bd3e4358bab5ce, 1093ab35ed87423f981ff8, 0091e1888f1c49a98dcbee, 092f38a86fd14112a48297, 96eb4716287149a5988164, 1cbe3181e3804cd98ba5a5, 438a0b19010841308e2a9c, 27300083263149d4b1ae4c, 0318e9eec64349f6b7a7f7, 1e168a459ecd4351bddc17, 8d986e2ec84245fa8fa28b, 8540a7b87685446f868e9a, 3af2416c21b0432f928c43, 7d29b6cb862649538821d6, e1e4648de4f8457cbb2e1c, 8805b6bb01f9423f937706, 28d6e012bbff47d3bc4ef6, 379770c49acb44ee841aa3, e36d97ef220c41d58b7e6b, 6a4209b8e11b45f9ac534d, 670f01241e3241a9a93bf6, 67620b1e15d74be29eb5fd, db309d19d01b4a059d5310, 9843b6f9f72c44f98d4ec6, e4090e8d98b945d7b0dc57, c0f61f6b92424903a1b5ef, 05122cc6a494414ea16d52, a08952ccdb324d7ebf79fb, bac7d3c858584afca8cbd7, dbdb461ab5b34b60884eb4])]', '2017-05-03 03:01:46', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('145', '修改', '系统_敏感词', '[SysSensitiveWords(id=1, name=法轮功, memo=, createId=1, dateCreated=null, dateUpdated=null)]', '2017-05-03 03:16:33', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('146', '修改', '用户信息', '[OrgUser(id=1, accid=admin, pwd=null, name=超级管理员, jobNo=, position=null, gender=null, mobile=, tel=, enable=null, avatar=null, email=, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=null, newpwd=null, confirmpwd=null)]', '2017-05-05 04:31:38', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('147', '修改', '用户密码', '[OrgUser(id=1, accid=null, pwd=null, name=null, jobNo=null, position=null, gender=null, mobile=null, tel=null, enable=null, avatar=null, email=null, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=21232f297a57a5a743894a0e4a801fc3, newpwd=21232f297a57a5a743894a0e4a801fc3, confirmpwd=21232f297a57a5a743894a0e4a801fc3)]', '2017-05-05 04:31:52', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('148', '修改', '用户信息', '[OrgUser(id=1, accid=admin, pwd=null, name=超级管理员, jobNo=, position=null, gender=null, mobile=, tel=, enable=null, avatar=null, email=, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=null, newpwd=null, confirmpwd=null)]', '2017-05-05 04:34:26', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('149', '修改', '用户信息', '[OrgUser(id=1, accid=admin, pwd=null, name=超级管理员, jobNo=, position=null, gender=null, mobile=, tel=, enable=null, avatar=null, email=, weixinid=null, type=null, lastLogin=null, count=null, state=null, skin=null, memo=null, keyword=null, version=null, orderNo=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, roleIdArray=null, roleNames=null, deptIdArray=null, oldpwd=null, newpwd=null, confirmpwd=null)]', '2017-05-09 05:51:48', '1', '超级管理员', '101.204.30.14');
INSERT INTO `sys_user_log` VALUES ('150', '修改', '全景项目信息', '[PanoProj(id=7, code=1494381188897258969, categoryId=4, type=0, name=1111, soundSrc=null, isComments=0, isPlanetoid=0, isFps=0, snowType=, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bfo0tlsf1jkr197rqbi1ker9cc1g, projId=7, sceneTitle=卧室, logoUrl=null, sceneSrc=/upload/image/n1/1494381188897258969/20170510095321wml49.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=762361, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-05-10 01:53:32', '1', '超级管理员', '115.60.197.94');
INSERT INTO `sys_user_log` VALUES ('151', '修改', '全景项目信息', '[PanoProj(id=8, code=1494381236232364705, categoryId=4, type=0, name=2222, soundSrc=null, isComments=0, isPlanetoid=0, isFps=0, snowType=, mapSrc=null, videoSrc=null, isSeccuss=null, thumbsUpNum=null, pvNum=null, logoPicUrl=null, logoWebUrl=null, pwd=null, tourEditJson=null, extCfgJson=null, version=null, state=null, memo=, orderNo=null, keyword=null, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, scenes=[PanoScene(id=o_1bfo0uu3p188cejb1f5b5fi19vl1g, projId=8, sceneTitle=22222, logoUrl=null, sceneSrc=/upload/image/n1/1494381236232364705/20170510095402upcd9.jpg, hlookat=null, vlookat=null, extCfgJson=null, version=null, state=null, memo=null, orderNo=0, keyword=635381, delFlag=null, createId=1, dateCreated=null, dateUpdated=null, breakdownImg=null, hotSpots=null, imgSpots=null)], radars=null, makePanoFlag=true)]', '2017-05-10 01:54:06', '1', '超级管理员', '115.60.197.94');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统_数据字典表';

-- ----------------------------
-- Records of sys_variable
-- ----------------------------
INSERT INTO `sys_variable` VALUES ('1', 'tags', '标签', null, '', '2', '1', '0', '1', '2017-03-12 12:46:39', '2017-04-17 15:09:51');
INSERT INTO `sys_variable` VALUES ('2', 'fengjing', '风景', '1', '', '0', '1', '0', '1', '2017-04-17 15:10:17', '2017-04-17 15:10:17');
INSERT INTO `sys_variable` VALUES ('3', 'renwen', '人文', '1', '', '0', '2', '0', '1', '2017-04-17 15:10:31', '2017-04-17 15:10:31');
INSERT INTO `sys_variable` VALUES ('4', 'youji', '游记', '1', '', '0', '3', '0', '1', '2017-04-17 15:12:43', '2017-04-17 15:12:43');

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

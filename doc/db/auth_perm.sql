/*
Navicat MySQL Data Transfer

Source Server         : 合时代成都test
Source Server Version : 50626
Source Host           : 192.168.108.101:3306
Source Database       : hsd_test

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-01-12 15:16:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_perm
-- ----------------------------
DROP TABLE IF EXISTS `auth_perm`;
CREATE TABLE `auth_perm` (
  `id` varchar(32) NOT NULL COMMENT '权限id',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `match_str` varchar(50) NOT NULL COMMENT '权限匹配符',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_no` int(10) DEFAULT '0' COMMENT '排序',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_id` bigint(20) DEFAULT NULL COMMENT '建立者ID',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `date_updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `nodel_flag` tinyint(1) DEFAULT '0' COMMENT '禁止删除(0否1是)',
  `app_id` varchar(32) DEFAULT NULL COMMENT '应用id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_auth_perm_match_str` (`match_str`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限_功能信息';

-- ----------------------------
-- Records of auth_perm
-- ----------------------------
INSERT INTO `auth_perm` VALUES ('0034d92a46034c2c997e80', '工资条管理', 'oa_archives_salarySheet', '82e283756ecd4c5e862ebd', '', null, '0', null, '0', '1', '2017-12-06 15:48:02', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('016e182875064a8ba41e99', '我的薪资', 'oa_personnel_mySalaryInfo', '1b016e417968497cb66ccd', '', null, '1', null, '0', '1', '2018-01-04 09:47:53', '2018-01-04 17:28:37', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('06ac60f7b38e4f0cbef7cc', '供应商管理', 'oa_admin_supplierMgr', 'ba9b0c8070d14bd18f0e33', '', null, '0', null, '0', '1', '2017-12-06 15:50:38', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('0896582fe88a4846b8634d', '新增', '/apiadministrative/addNotice', '2c7834b955a94888b00380', '', null, '1', null, '0', '1', '2018-01-04 15:53:06', '2018-01-04 17:34:53', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('0ba205a889e947bfb04c68', '客户-黑名单设置', 'user:edit:blacklist', 'id_user', '', null, '0', null, '0', '1', '2017-08-25 16:42:18', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('0be01f2f141145e494e6be', '流程中心', 'oa_flow_flowCenter', '4568a14125534cc482bc2e', '', null, '0', null, '0', '1', '2017-12-06 15:45:55', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('15a13921d0624157b501c2', '数据字典列表查询', '/web/dictionary/queryAll', '2e6dd15a53614fbebc3fac', '查询所有数据', null, '2', null, '0', '1', '2017-12-04 16:17:09', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('171e31bf373c4be498ab4f', '我的考勤', 'oa_personnel_myAttendance', '1b016e417968497cb66ccd', '', null, '1', null, '0', '1', '2018-01-04 09:46:59', '2018-01-04 17:28:55', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('179c2e81a2174dbdb9ec1b', '组织架构_员工/员工_回收站_恢复', 'orgStaff:recovery', '857c80d64d3a48529644c1', '', null, '0', null, '0', '1', '2017-08-17 11:57:02', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('17d9c1f25a9d4996a6fa2e', '根据流程表单配置ID查询流程表单元素配置', '/web/flowFormElementConfig/queryByFlowFormId', '8bfa12bef5c5422aa11e68', '', null, '0', null, '0', '1', '2018-01-05 16:09:05', '2018-01-05 16:09:05', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1851fdfc7f90427a910cb2', '导出', '/oa/employeeArchives/exportArchives', '897733fe58e14afb8a2a2c', '', null, '1', null, '0', '1', '2018-01-04 16:19:25', '2018-01-04 17:35:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1877efec5c6e4e69880e0e', '数据字典删除', '/web/dictionary/delete', '2e6dd15a53614fbebc3fac', '', null, '2', null, '0', '1', '2017-12-04 16:24:20', '2018-01-11 17:51:50', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1a2efe8d578945c593c93a', '客户管理', 'actor:menu', 'efa0993149b043508f8e42', '', null, '3', null, '0', '1', '2017-08-25 14:22:08', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('1ace65e1f616460498e4be', '修改', '/web/flowFormConfig/update', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-04 17:49:46', '2018-01-04 17:49:46', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1b016e417968497cb66ccd', '个人中心', 'oa_personnel', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2018-01-04 09:46:29', '2018-01-04 17:27:55', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1b77d89cb09749718d9b4c', '添加', '/web/flowFormTaskConfig/add', '5bd888d0ab7c47b5899a4e', '', null, '0', null, '0', '1', '2018-01-05 16:13:52', '2018-01-05 16:13:52', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1c3067faf1fa4c3e9bcc02', '渠道商信息_恢复', 'channelInfo:recovery', 'id_channel_info', '', null, '0', null, '0', '1', '2017-08-25 14:50:11', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('1c501c7fe0764fcfb5e362', '班次管理', 'oa_attendance_workType', 'fe1948de8df7464c9bf895', '', null, '1', null, '0', '1', '2018-01-04 09:42:28', '2018-01-04 17:29:54', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('1da8d44c174e4253bb64a4', '导出', '/oa/employeeAttendance/exportAttendance', '40f3357138cf49fdb2e373', '', null, '1', null, '0', '1', '2018-01-04 10:07:22', '2018-01-04 17:31:55', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('23db03e6d1e341cfba0fc5', '资产管理', 'oa_admin_assetsMgr', 'ba9b0c8070d14bd18f0e33', '', null, '0', null, '0', '1', '2017-12-06 15:49:36', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('253a25a1bd4a4e709c0956', '组织架构_回收站', 'orgInfo:recycle', 'id_org_info', '', null, '1', null, '0', '1', '2017-11-09 15:11:38', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('2c534918392b4e9480c63a', '设置组织', 'orgStaff:edit:org', 'id_org_staff', '', null, '1', null, '0', '1', '2017-08-17 16:54:32', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('2c7834b955a94888b00380', '通知公告管理', 'oa_admin_noticeInfo', 'ba9b0c8070d14bd18f0e33', '', null, '1', null, '0', '1', '2018-01-04 09:44:10', '2018-01-04 17:32:55', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('2e6dd15a53614fbebc3fac', '数据字典管理', 'oa_system_dictionary', '724c37470c1a40978dbff6', '', null, '0', null, '0', '1', '2017-12-04 16:15:18', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('2f1353bc3381497285e011', '超级管理员', 'authRole:super', 'id_auth_role', '', null, '0', null, '0', '1', '2017-08-15 11:15:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('32ef462e7ac3402ea92454', '导入', '/oa/employeeAttendance/importSchedule', '84ce16c906c1491ba3be91', '', null, '1', null, '0', '1', '2018-01-04 10:18:35', '2018-01-04 17:31:08', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('352b8657e7a9466e83fdf2', '详情/编辑', '/oa/employeeArchives/archivesInfo', '897733fe58e14afb8a2a2c', '', null, '1', null, '0', '1', '2018-01-04 16:25:45', '2018-01-04 17:35:28', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('36307059ea364bdda79135', '日志详情', 'orgLogOperation:info', 'id_org_log_operation', '', null, '0', null, '0', '1', '2017-08-18 17:37:59', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('3c9418be00d64c049accf4', '权限_角色vs应用', 'authRole:edit:app', 'id_auth_role', '', null, '0', null, '0', '1', '2017-12-28 16:00:20', '2017-12-28 16:00:20', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('3db78f2a18404f5aae5e44', '员工_密码重置', 'orgStaff:edit:resetpwd', 'id_org_staff', '', null, '0', null, '0', '1', '2017-08-17 11:26:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('3e20f3692b4d4205a82521', '日志详情', 'orgLogLogin:info', 'id_org_log_login', '', null, '0', null, '0', '1', '2017-08-18 17:38:51', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('40f3357138cf49fdb2e373', '考勤管理', 'oa_attendance_attendance', 'fe1948de8df7464c9bf895', '', null, '1', null, '0', '1', '2018-01-04 09:40:43', '2018-01-04 17:30:26', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('41e96490ca6445de8b428e', '查询', '/apiadministrative/getRules', '6d40c0d286b24d1cbec9e2', '', null, '0', null, '0', '1', '2018-01-05 09:57:28', '2018-01-05 09:57:28', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('4568a14125534cc482bc2e', '流程管理', 'oa_flow', '534e085043f8431b82326d', '', null, '0', null, '0', '1', '2017-12-06 15:45:13', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('469ae3c129fd43fcbead34', '11', '11', 'e30995491a3c4820bf8228', '', null, '0', null, '0', '1', '2017-12-12 10:47:55', '2017-12-12 10:47:55', '0', '22f2466947d64842b779a5');
INSERT INTO `auth_perm` VALUES ('46f4ada8e33d4eb78b6b08', '111', '111', '469ae3c129fd43fcbead34', '', null, '1', null, '0', '2330', '2017-12-22 15:48:07', '2017-12-22 15:48:30', '0', '22f2466947d64842b779a5');
INSERT INTO `auth_perm` VALUES ('475fb3e35a3c41ab900f76', '新增（预定会议室）', '/apimeet/reserviceRoom', 'a79b953632864b50833978', '', null, '2', null, '0', '1', '2018-01-04 15:33:28', '2018-01-05 10:08:11', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('4bc6b40b24294d1586b676', '修改密码', 'updatepasswd', '585ceb4962b441a292b316', '', null, '0', null, '0', '1', '2017-12-07 13:24:30', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('4e18bd49914f4f55b5033d', '导出', '/oa/employeeAttendance/exportAnnualLeave', 'db9bfbdae5b341d8a232a1', '', null, '1', null, '0', '1', '2018-01-04 10:15:06', '2018-01-04 17:31:27', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('4f0851cd3aee4278bfe99b', '提交', '/oa/flow/candidateInfo', 'b7d215097d61495d84832e', '', null, '0', null, '0', '1', '2018-01-11 18:04:27', '2018-01-11 18:04:27', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('50d8b9463d0146dabd98e1', '查询', '/oa/employeeAttendance/scheduleManage', '84ce16c906c1491ba3be91', '', null, '1', null, '0', '1', '2018-01-04 10:17:43', '2018-01-04 17:31:17', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('51878c928976425b8caada', '会议室已预定查询', '/apimeet/selectReservices', 'a79b953632864b50833978', '', null, '0', null, '0', '1', '2018-01-05 10:26:24', '2018-01-05 10:26:24', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('51ec60f7ba8a4c919ed77c', '公司制度-查询前6条（首页用）', '/administrative/getRule', '6d40c0d286b24d1cbec9e2', '', null, '2', null, '0', '1', '2018-01-08 17:37:57', '2018-01-11 17:42:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('531525e9520a486fa1f2ae', '查询', '/apiadministrative/getNotices', '2c7834b955a94888b00380', '', null, '0', null, '0', '1', '2018-01-05 09:57:56', '2018-01-05 09:57:56', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('534e085043f8431b82326d', 'OA系统', 'oa', '', '', null, '1', null, '0', '1', '2017-12-04 16:12:46', '2017-12-12 11:24:22', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('585ceb4962b441a292b316', '个人密码修改', 'orgStaff:edit:pwd', 'efa0993149b043508f8e42', '', null, '0', null, '0', '1', '2017-08-17 11:08:25', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('5bd888d0ab7c47b5899a4e', '审批节点配置', 'oa_system_flowFormConfig_flowFormTaskConfig', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-05 16:06:10', '2018-01-05 16:06:10', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('5bde290b62b24e449d1f26', '查询班次', '/oa/employeeAttendance/workTypeByPage', '1c501c7fe0764fcfb5e362', '', null, '0', null, '0', '1', '2018-01-11 16:08:16', '2018-01-11 17:48:20', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('5d2ce1e6e88c4456befb0f', '删除', '/web/flowFormConfig/delete', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-04 17:48:36', '2018-01-04 17:48:36', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('5d5684e166e04cd0ae334c', '根据流程表单配置ID查询流程表单节点配置', '/web/flowFormTaskConfig/queryByFlowFormId', '5bd888d0ab7c47b5899a4e', '', null, '0', null, '0', '1', '2018-01-05 16:15:24', '2018-01-05 16:15:24', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('5d9a1af02e34479f8246dd', '设置角色', 'orgStaff:edit:role', 'id_org_staff', '', null, '0', null, '0', '1', '2017-08-17 15:11:47', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('5ebda947ac544b419af63e', '数据字典修改', '/web/dictionary/update', '2e6dd15a53614fbebc3fac', '', null, '1', null, '0', '1', '2017-12-04 16:24:00', '2018-01-11 17:52:09', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('60fb5337e42543d89bbf43', '渠道商密码重置', 'channelInfo:reset', 'id_channel_info', '', null, '0', null, '0', '1', '2017-08-25 14:50:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('645a8791fd8540bf9a9aa1', '预览', '/apiadministrative/getNoticeById', '2c7834b955a94888b00380', '', null, '1', null, '0', '1', '2018-01-04 15:55:32', '2018-01-04 17:34:44', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('67fe27183ab4440d952d0b', '我的年休假', 'oa_personnel_myLeave', '1b016e417968497cb66ccd', '', null, '2', null, '0', '1', '2018-01-04 09:48:28', '2018-01-04 17:28:27', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('6bafe3677cb34f528c7f8c', '修改', '/web/flowFormTaskConfig/update', '5bd888d0ab7c47b5899a4e', '', null, '0', null, '0', '1', '2018-01-05 16:14:46', '2018-01-05 16:14:46', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('6c7c616340184f1085cf2d', '查询', '/oa/employeeArchives/archivesByPage', '897733fe58e14afb8a2a2c', '', null, '1', null, '0', '1', '2018-01-04 16:17:44', '2018-01-04 17:35:48', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('6d40c0d286b24d1cbec9e2', '公司制度管理', 'oa_admin_rulesInfo', 'ba9b0c8070d14bd18f0e33', '', null, '1', null, '0', '1', '2018-01-04 09:44:49', '2018-01-04 17:32:48', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('724c37470c1a40978dbff6', '系统管理', 'oa_system', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2017-12-04 16:13:50', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('7631af3aeb164e53ae2daf', '流程配置信息查询', '/oa/flow/allFlowConfig', 'b7d215097d61495d84832e', '', null, '0', null, '0', '1', '2018-01-10 17:58:15', '2018-01-10 17:58:15', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('76639acd536d4c6c9c5425', '查询', '/oa/employeeAttendance/attendanceByPage', '40f3357138cf49fdb2e373', '', null, '2', null, '0', '1', '2018-01-04 10:00:22', '2018-01-04 17:32:18', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('7f2127f692f8441c9c654d', '操作记录', 'oa_system_operationRecord', '724c37470c1a40978dbff6', '', null, '1', null, '0', '1', '2018-01-04 09:49:27', '2018-01-04 17:36:55', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('823c0e845ead41a18142cd', '导出', '/oa/employeeAttendance/exportSchedule', '84ce16c906c1491ba3be91', '', null, '1', null, '0', '1', '2018-01-04 10:19:17', '2018-01-04 17:31:00', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('82e283756ecd4c5e862ebd', '档案管理', 'oa_archives', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2017-12-06 15:46:39', '2017-12-12 11:24:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('83f6807de5c54e38b65bff', '查询', '/oa/employeeAttendance/annualLeaveByPage', 'db9bfbdae5b341d8a232a1', '', null, '1', null, '0', '1', '2018-01-04 10:11:17', '2018-01-04 17:31:36', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('84ce16c906c1491ba3be91', '排班管理', 'oa_attendance_scheduleManage', 'fe1948de8df7464c9bf895', '', null, '1', null, '0', '1', '2018-01-04 09:42:02', '2018-01-04 17:30:09', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('8504dfc833414958abe0bc', '查询', '/apiadministrative/getCultures', '923d8f1a2eca49f3bba492', '', null, '0', null, '0', '1', '2018-01-05 09:56:58', '2018-01-05 09:56:58', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('8579eeefbe594aa485d0ff', '修改', '/web/flowFormElementConfig/update', '8bfa12bef5c5422aa11e68', '', null, '0', null, '0', '1', '2018-01-05 16:07:53', '2018-01-05 16:07:53', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('857c80d64d3a48529644c1', '组织架构_员工/员工_回收站', 'orgStaff:recycle', 'id_org_staff', '', null, '0', null, '0', '1', '2017-08-17 11:55:39', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('866ee67a65bb42f586b9e4', '根据类型查询数据字典', '/web/dictionary/queryByType', '2e6dd15a53614fbebc3fac', '', null, '0', null, '0', '1', '2018-01-05 15:53:00', '2018-01-05 15:53:00', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('875bf1eb8956441d942982', '查询调岗记录', '/oa/employeeArchives/applyTransferByPage', '897733fe58e14afb8a2a2c', '', null, '0', null, '0', '1', '2018-01-11 11:23:31', '2018-01-11 17:50:18', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('897733fe58e14afb8a2a2c', '员工档案', 'oa_archives_employeeArchives', '82e283756ecd4c5e862ebd', '', null, '0', null, '0', '1', '2017-12-06 15:47:05', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('8b29e99dd79a4040b0ed2f', '我的档案', 'oa_personnel_myArchives', '1b016e417968497cb66ccd', '', null, '1', null, '0', '1', '2018-01-04 09:47:24', '2018-01-04 17:28:45', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('8bfa12bef5c5422aa11e68', '表单元素配置', 'oa_system_flowFormConfig_flowFormElementConfig', 'd5fb04cdc15f442b863ac1', '', null, '1', null, '0', '1', '2018-01-05 16:04:33', '2018-01-05 16:05:35', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('903021e9c63d4f569a9db7', '查询工作记录', '/oa/employeeArchives/workRecordByPage', '897733fe58e14afb8a2a2c', '', null, '0', null, '0', '1', '2018-01-11 11:22:27', '2018-01-11 17:50:34', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('915a5fce0803488bbc1d3d', '新增/编辑', '/oa/employeeAttendance/holidayType', 'f480d9fb6e0b42288b8add', '', null, '1', null, '0', '1', '2018-01-04 10:30:29', '2018-01-04 17:30:35', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('923d8f1a2eca49f3bba492', '公司文化管理', 'oa_admin_cultureInfo', 'ba9b0c8070d14bd18f0e33', '', null, '1', null, '0', '1', '2018-01-04 09:45:22', '2018-01-04 17:32:40', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('9678dc0970094936b291cf', '删除', '/apiadministrative/deleteRule', '6d40c0d286b24d1cbec9e2', '', null, '2', null, '0', '1', '2018-01-04 15:57:58', '2018-01-11 17:40:45', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('97d0050c20cd4707a0f43d', '设置组织负责人', 'orgInfo:edit:setManager', 'id_org_info', '', null, '0', null, '0', '1', '2017-10-16 16:54:06', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('99ca37c9947a4e50ac61fa', '员工详情', 'orgStaff:info', 'id_org_staff', '', null, '0', null, '0', '1', '2017-08-15 18:01:43', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('a0ccaab351eb45248c1d9d', '培训记录', 'oa_archives_cultivateRecord', '82e283756ecd4c5e862ebd', '', null, '0', null, '0', '1', '2017-12-06 15:48:24', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('a1dbe1cbe8a0445bad274f', '设置员工上级', 'orgStaff:edit:leadership', 'id_org_staff', '', null, '0', null, '0', '1', '2017-10-16 15:20:15', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('a2708b5ec3a44c95bdf332', '删除', '/web/flowFormTaskConfig/delete', '5bd888d0ab7c47b5899a4e', '', null, '0', null, '0', '1', '2018-01-05 16:14:19', '2018-01-05 16:14:19', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('a2fa7a98d5334d2aa60064', '通知公告-查询前6条（首页用）', '/administrative/getNotice', '2c7834b955a94888b00380', '', null, '1', null, '0', '1', '2018-01-08 17:36:37', '2018-01-11 17:45:50', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('a79b953632864b50833978', '会议室预定', 'oa_meet_reserviceRecord', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2018-01-04 09:45:58', '2018-01-04 17:29:02', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('aaacff5670a6439fb247b5', '查询', '/web/flowFormConfig/queryAll', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-04 17:49:05', '2018-01-04 17:49:05', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('ab0d4bfbd13d44a0a7e7ff', '数据字典新增', '/web/dictionary/add', '2e6dd15a53614fbebc3fac', '', null, '2', null, '0', '1', '2017-12-04 16:23:02', '2018-01-11 17:52:23', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('add0b242d5d94217ac7d74', '组织架构_回收站_恢复', 'orgInfo:recovery', '253a25a1bd4a4e709c0956', '', null, '1', null, '0', '1', '2017-11-09 15:09:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('aeef9fcf333d430291bea9', '预览', '/apiadministrative/getCultureById', '923d8f1a2eca49f3bba492', '', null, '1', null, '0', '1', '2018-01-04 15:59:46', '2018-01-04 17:33:25', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b266349565e84b6b817f94', '导入', '/oa/employeeAttendance/importPunchClock', '40f3357138cf49fdb2e373', '', null, '1', null, '0', '1', '2018-01-04 10:04:58', '2018-01-04 17:32:07', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b4c7c332c12d430b8a874c', '渠道商信息_导入', 'channelInfo:add:batch', 'id_channel_info', '', null, '0', null, '0', '1', '2017-08-25 14:36:06', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f001', '删除', '/web/supplierMgr/delete', '06ac60f7b38e4f0cbef7cc', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f002', '查询', '/web/supplierMgr/queryAll', '06ac60f7b38e4f0cbef7cc', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f003', '导出数据', '/web/supplierMgr/exportExcel', '06ac60f7b38e4f0cbef7cc', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f004', '添加', '/web/goodsInfo/add', 'd139778a6d4a4af08a7e36', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f005', '修改', '/web/goodsInfo/update', 'd139778a6d4a4af08a7e36', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f006', '查询', '/web/goodsInfo/queryAll', 'd139778a6d4a4af08a7e36', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f007', '根据类型查询数据', '/web/goodsInfo/queryByType', 'd139778a6d4a4af08a7e36', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f008', '导出数据', '/web/goodsInfo/exportExcel', 'd139778a6d4a4af08a7e36', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f009', '入库查询', '/web/assetManagement/putQuery', '23db03e6d1e341cfba0fc5', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 14:50:02', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f010', '物品领用查询', '/web/assetManagement/useapply', '23db03e6d1e341cfba0fc5', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 14:50:04', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f011', '报废查询', '/web/assetManagement/destoryapply', '23db03e6d1e341cfba0fc5', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 14:50:07', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f012', '添加', '/web/archiveFailedRecord/add', 'b6c3b665aed4465f9b0c96', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f013', '修改', '/web/archiveFailedRecord/update', 'b6c3b665aed4465f9b0c96', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f014', '查询', '/web/archiveFailedRecord/query', 'b6c3b665aed4465f9b0c96', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f015', '重新归档', '/web/archiveFailedRecord/reArchive', 'b6c3b665aed4465f9b0c96', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f016', '查询所有批次数据', '/web/salarySheet/queryAll', '0034d92a46034c2c997e80', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f017', '导入', '/web/salarySheet/importExcel', '0034d92a46034c2c997e80', '', null, '1', null, '0', '1', '2017-12-22 11:54:38', '2018-01-11 17:49:30', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f018', '根据员工ID查询', '/web/salarySheet/queryDetailByEmployeeId', '0034d92a46034c2c997e80', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f019', '删除一个批次数据', '/web/salarySheet/batchDelete', '0034d92a46034c2c997e80', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f020', '导出一个批次的明细', '/web/salarySheet/exportExcel', '0034d92a46034c2c997e80', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f021', '添加', '/web/cultivateRecord/add', 'a0ccaab351eb45248c1d9d', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f022', '修改', '/web/cultivateRecord/update', 'a0ccaab351eb45248c1d9d', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f023', '查询', '/web/cultivateRecord/query', 'a0ccaab351eb45248c1d9d', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f024', '导出', '/web/cultivateRecord/exportExcel', 'a0ccaab351eb45248c1d9d', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f025', '修改', '/web/salaryInfo/update', 'bb09fd9fdf4f444aadeff7', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f026', '查询', '/web/salaryInfo/query', 'bb09fd9fdf4f444aadeff7', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f027', '导出', '/web/salaryInfo/exportExcel', 'bb09fd9fdf4f444aadeff7', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f028', '我的待办', 'oa_flow_myTask', '4568a14125534cc482bc2e', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f029', '我的已办', 'oa_flow_myDone', '4568a14125534cc482bc2e', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f030', '发起流程', 'oa_flow_apply', '4568a14125534cc482bc2e', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 11:54:38', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f031', '我的申请', 'oa_flow_myApply', '4568a14125534cc482bc2e', '', null, '1', null, '0', '1', '2017-12-22 11:54:38', '2018-01-04 17:36:31', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b532552a26024c76a7f995', '新增', '/web/supplierMgr/add', '06ac60f7b38e4f0cbef7cc', '', null, '0', null, '0', '1', '2017-12-22 11:54:38', '2017-12-22 13:54:10', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b6c3b665aed4465f9b0c96', '归档失败记录', 'oa_system_archiveFailedRecord', '724c37470c1a40978dbff6', '', null, '0', null, '0', '1', '2017-12-06 15:51:41', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('b7d215097d61495d84832e', '流程配置', 'oa_system_flowconfig', '724c37470c1a40978dbff6', '', null, '0', null, '0', '1', '2017-12-06 15:52:11', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('ba9b0c8070d14bd18f0e33', '行政管理', 'oa_admin', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2017-12-06 15:49:17', '2017-12-12 11:24:30', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('bb09fd9fdf4f444aadeff7', '薪资管理', 'oa_archives_salaryInfo', '82e283756ecd4c5e862ebd', '', null, '0', null, '0', '1', '2017-12-06 15:47:36', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c0b4036a23634cada6718d', '删除', '/web/flowFormElementConfig/delete', '8bfa12bef5c5422aa11e68', '', null, '0', null, '0', '1', '2018-01-05 16:08:20', '2018-01-05 16:08:20', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c4753762108a413bbd570e', '删除', '/apiadministrative/deleteNotice', '2c7834b955a94888b00380', '', null, '3', null, '0', '1', '2018-01-04 15:56:06', '2018-01-11 17:40:47', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c4d792bbc00844ada5cd7a', '我的消息', 'oa_personnel_myMessage', '1b016e417968497cb66ccd', '', null, '2', null, '0', '1', '2018-01-12 09:33:04', '2018-01-12 09:35:40', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c7a6d6b42c4b4cb79468b0', '查询调薪记录', '/apiListBusinessDate/selectSalaryPostsByUserId', 'bb09fd9fdf4f444aadeff7', '', null, '0', null, '0', '1', '2018-01-11 16:51:49', '2018-01-11 17:49:53', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c8f9b724e2894357971287', '根据日期查询会议室', '/apimeet/getRoomsByDate', 'a79b953632864b50833978', '', null, '3', null, '0', '1', '2018-01-04 15:30:44', '2018-01-05 10:07:52', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('c9df9d1d2f3940a5914b12', '客户-标签设置', 'user:edit:tag', 'id_user', '', null, '0', null, '0', '1', '2017-08-25 14:33:01', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('cd9e4d3060594cd5ba9d5a', '批量新增', 'orgStaff:add:batch', 'id_org_staff', '', null, '0', null, '0', '1', '2017-08-18 12:31:34', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('cfc38f0f117a41fd8ddb88', '预览', '/apiadministrative/getRulesById', '6d40c0d286b24d1cbec9e2', '', null, '1', null, '0', '1', '2018-01-04 15:57:36', '2018-01-04 17:34:04', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('cfec1d1bbfdb46dba7df41', '数据字典刷新缓存', '/web/dictionary/refreshCache', '2e6dd15a53614fbebc3fac', '', null, '1', null, '0', '1', '2017-12-04 16:24:40', '2018-01-11 17:51:32', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('d139778a6d4a4af08a7e36', '物品管理', 'oa_admin_goodsInfo', 'ba9b0c8070d14bd18f0e33', '', null, '0', null, '0', '1', '2017-12-06 15:49:59', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('d1b8002777694208b2f3e9', '新增', '/web/flowFormConfig/add', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-04 17:47:54', '2018-01-04 17:47:54', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('d5fb04cdc15f442b863ac1', '流程表单配置', 'oa_system_flowFormConfig', '724c37470c1a40978dbff6', '', null, '0', null, '0', '1', '2017-12-06 15:51:13', '2017-12-12 15:10:43', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('db04166a150e42e9827210', '加载会议室', '/apimeet/getRooms', 'a79b953632864b50833978', '', null, '0', null, '0', '1', '2018-01-05 10:17:16', '2018-01-05 10:17:16', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('db9bfbdae5b341d8a232a1', '年休假管理', 'oa_attendance_annualLeave', 'fe1948de8df7464c9bf895', '', null, '1', null, '0', '1', '2018-01-04 09:41:22', '2018-01-04 17:30:17', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('e0ae4061d9ec42dca827dc', '根据流程编号查询流程表单配置', '/web/flowFormConfig/queryByFlowKey', 'd5fb04cdc15f442b863ac1', '', null, '0', null, '0', '1', '2018-01-05 16:25:13', '2018-01-05 16:25:13', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('e30995491a3c4820bf8228', '1', '1', '', '', null, '0', null, '0', '1', '2017-12-08 16:05:59', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('e3ca14beed3a4823b6c17c', '新增', '/apiadministrative/addRules', '6d40c0d286b24d1cbec9e2', '', null, '1', null, '0', '1', '2018-01-04 15:57:06', '2018-01-04 17:34:19', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('e62b6b99cf4f4f18a40ab9', '查询', '/oa/employeeAttendance/holidayTypeByPage', 'f480d9fb6e0b42288b8add', '', null, '0', null, '0', '1', '2018-01-11 16:09:34', '2018-01-11 17:47:44', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('e6d036d60aa24d9ead2aed', '添加', '/web/flowFormElementConfig/add', '8bfa12bef5c5422aa11e68', '', null, '0', null, '0', '1', '2018-01-05 16:07:19', '2018-01-05 16:07:19', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('ea2478f547b8476eac6873', '新增', '/apiadministrative/addCulture', '923d8f1a2eca49f3bba492', '', null, '1', null, '0', '1', '2018-01-04 15:59:20', '2018-01-04 17:33:35', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('eb33a50547214f569b64ad', '公司文化-查询前6条（首页用）', '/apiadministrative/getCulture', '923d8f1a2eca49f3bba492', '', null, '1', null, '0', '1', '2018-01-08 17:27:31', '2018-01-08 17:33:04', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('ec7aa37dfb0d4ed6839259', '修改', '/web/supplierMgr/update', '06ac60f7b38e4f0cbef7cc', '', null, '0', null, '0', '1', '2017-12-22 11:55:47', '2017-12-22 13:54:14', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('edcbc780d7fe46c785daaa', '新增/编辑', '/oa/employeeAttendance/workType', '1c501c7fe0764fcfb5e362', '', null, '2', null, '0', '1', '2018-01-04 10:24:48', '2018-01-04 17:30:44', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('efa0993149b043508f8e42', '员工后台管理系统', 'admin:menu', null, '', null, '0', null, '0', '1', '2017-08-15 11:05:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('f18c0805ab584021b3b97a', '删除', '/apiadministrative/deleteCulture', '923d8f1a2eca49f3bba492', '', null, '5', null, '0', '1', '2018-01-04 16:00:38', '2018-01-11 17:40:51', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('f480d9fb6e0b42288b8add', '节假日管理', 'oa_attendance_holidayType', 'fe1948de8df7464c9bf895', '', null, '1', null, '0', '1', '2018-01-04 09:43:00', '2018-01-04 17:29:39', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('fe1948de8df7464c9bf895', '考勤管理', 'oa_attendance', '534e085043f8431b82326d', '', null, '1', null, '0', '1', '2018-01-04 09:40:09', '2018-01-04 17:29:25', '0', 'oa');
INSERT INTO `auth_perm` VALUES ('id_account', '支付账户', 'account:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_add', '支付账户_新增', 'account:add', 'id_account', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_bind_thirdparty', '支付账户与三方账户绑定', 'accountBindThirdparty:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_bind_thirdparty_add', '支付账户与三方账户绑定_新增', 'accountBindThirdparty:add', 'id_account_bind_thirdparty', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_bind_thirdparty_edit', '支付账户与三方账户绑定_编辑', 'accountBindThirdparty:edit', 'id_account_bind_thirdparty', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_bind_thirdparty_info', '支付账户与三方账户绑定_详情', 'accountBindThirdparty:info', 'id_account_bind_thirdparty', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_bind_thirdparty_phyde', '支付账户与三方账户绑定_物理删除', 'accountBindThirdparty:phydel', 'id_account_bind_thirdparty', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_edit', '支付账户_编辑', 'account:edit', 'id_account', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_info', '支付账户_详情', 'account:info', 'id_account', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log', '账户-日志-交易流水', 'accountLog:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-21 13:51:18', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_add', '账户-日志-交易流水_新增', 'accountLog:add', 'id_account_log', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-21 13:51:11', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_del', '账户-日志-交易流水_逻辑删除', 'accountLog:del', 'id_account_log', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-21 13:51:08', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_edit', '账户-日志-交易流水_编辑', 'accountLog:edit', 'id_account_log', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-21 13:51:04', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_freeze', '账户-日志-资金冻结记录', 'accountLogFreeze:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_freeze_add', '账户-日志-资金冻结记录_新增', 'accountLogFreeze:add', 'id_account_log_freeze', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_freeze_edit', '账户-日志-资金冻结记录_编辑', 'accountLogFreeze:edit', 'id_account_log_freeze', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_freeze_info', '账户-日志-资金冻结记录_详情', 'accountLogFreeze:info', 'id_account_log_freeze', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_freeze_phydel', '账户-日志-资金冻结记录_物理删除', 'accountLogFreeze:phydel', 'id_account_log_freeze', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_info', '账户-日志-交易流水_详情', 'accountLog:info', 'id_account_log', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-21 13:51:04', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_operational', '用户账户-操作日志', 'accountLogOperational:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_operational_add', '用户账户-操作日志_新增', 'accountLogOperational:add', 'id_account_log_operational', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_operational_edit', '用户账户-操作日志_编辑', 'accountLogOperational:edit', 'id_account_log_operational', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_operational_info', '用户账户-操作日志_详情', 'accountLogOperational:info', 'id_account_log_operational', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_operational_phyde', '用户账户-操作日志_物理删除', 'accountLogOperational:phydel', 'id_account_log_operational', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_phydel', '账户-日志-资金流水_物理删除', 'accountLog:phydel', 'id_account_log', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_recharge', '账户-日志-充值记录', 'accountLogRecharge:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:17', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_recharge_add', '账户-日志-充值记录_新增', 'accountLogRecharge:add', 'id_account_log_recharge', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_recharge_edit', '账户-日志-充值记录_编辑', 'accountLogRecharge:edit', 'id_account_log_recharge', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_recharge_info', '账户-日志-充值记录_详情', 'accountLogRecharge:info', 'id_account_log_recharge', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_recharge_phydel', '账户-日志-充值记录_物理删除', 'accountLogRecharge:phydel', 'id_account_log_recharge', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_withdrawal', '账户-日志-提现流水', 'accountLogWithdrawal:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_withdrawal_add', '账户-日志-提现流水_新增', 'accountLogWithdrawal:add', 'id_account_log_withdrawal', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_withdrawal_edit', '账户-日志-提现流水_编辑', 'accountLogWithdrawal:edit', 'id_account_log_withdrawal', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_withdrawal_info', '账户-日志-提现流水_详情', 'accountLogWithdrawal:info', 'id_account_log_withdrawal', '系统生成', '0', '0', null, '0', null, '2017-12-20 16:27:18', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_log_withdrawal_phydel', '账户-日志-提现流水_物理删除', 'accountLogWithdrawal:phydel', 'id_account_log_withdrawal', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_op', '资金账户_操作', 'account:op', 'id_account', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-12 15:06:00', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_op_freeze', '资金账户_操作_冻结', 'account:op:freeze', 'id_account_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-12 15:06:02', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_op_reverse', '资金账户_操作_冲正/抵扣', 'account:op:reverse', 'id_account_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-12 15:06:04', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_op_state', '资金账户_操作_状态变更', 'account:op:state', 'id_account_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-12 15:06:08', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_op_unfreeze', '资金账户_操作_解冻', 'account:op:unfreeze', 'id_account_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:35:08', '2018-01-12 15:06:18', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_phydel', '支付账户_物理删除', 'account:phydel', 'id_account', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:32:29', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold', '子账户-实物贵金属', 'accountSubGold:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_add', '子账户-实物贵金属_新增', 'accountSubGold:add', 'id_account_sub_gold', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_edit', '子账户-实物贵金属_编辑', 'accountSubGold:edit', 'id_account_sub_gold', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_info', '子账户-实物贵金属_详情', 'accountSubGold:info', 'id_account_sub_gold', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_op_freeze', '黄金账户_操作_冻结', 'accountSubGold:op:freeze', 'id_account_sub_gold_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:35:08', '2018-01-12 15:06:24', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_op_reverse', '资金账户_操作_冲正/抵扣', 'accountSubGold:op:reverse', 'id_account_sub_gold_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-10 09:37:19', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_op_state', '资金账户_操作_状态变更', 'accountSubGold:op:state', 'id_account_sub_gold_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-10 09:37:19', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_op_unfreeze', '黄金账户_操作_解冻', 'accountSubGold:op:unfreeze', 'id_account_sub_gold_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:35:08', '2018-01-12 15:06:25', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_gold_phydel', '子账户-实物贵金属_物理删除', 'accountSubGold:phydel', 'id_account_sub_gold', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:09', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan', '子账户-P2P网贷', 'accountSubLoan:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_add', '子账户-P2P网贷_新增', 'accountSubLoan:add', 'id_account_sub_loan', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_edit', '子账户-P2P网贷_编辑', 'accountSubLoan:edit', 'id_account_sub_loan', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_info', '子账户-P2P网贷_详情', 'accountSubLoan:info', 'id_account_sub_loan', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_op_freeze', '网贷账户_操作_冻结', 'accountSubLoan:op:freeze', 'id_account_sub_loan_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:35:08', '2018-01-12 15:06:32', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_op_reverse', '网贷账户_操作_冲正/抵扣', 'accountSubLoan:op:reverse', 'id_account_sub_loan_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-10 09:37:19', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_op_state', '网贷账户_操作_状态变更', 'accountSubLoan:op:state', 'id_account_sub_loan_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:37:19', '2018-01-10 09:37:19', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_op_unfreeze', '网贷账户_操作_解冻', 'accountSubLoan:op:unfreeze', 'id_account_sub_loan_op', '系统生成', '0', '0', null, '0', null, '2018-01-10 09:35:08', '2018-01-12 15:06:36', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_sub_loan_phydel', '子账户-P2P网贷_物理删除', 'accountSubLoan:phydel', 'id_account_sub_loan', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template', '账户模板', 'accountTemplate:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_add', '账户模板_新增', 'accountTemplate:add', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_attribute', '账户模板属性', 'accountTemplateAttribute:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_attribute_ad', '账户模板属性_新增', 'accountTemplateAttribute:add', 'id_account_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_attribute_ed', '账户模板属性_编辑', 'accountTemplateAttribute:edit', 'id_account_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_attribute_in', '账户模板属性_详情', 'accountTemplateAttribute:info', 'id_account_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_attribute_ph', '账户模板属性_物理删除', 'accountTemplateAttribute:phydel', 'id_account_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_del', '账户模板_逻辑删除', 'accountTemplate:del', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_edit', '账户模板_编辑', 'accountTemplate:edit', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_info', '账户模板_详情', 'accountTemplate:info', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_phydel', '账户模板_物理删除', 'accountTemplate:phydel', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_recovery', '账户模板_回收站_恢复', 'accountTemplate:recovery', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_template_recycle', '账户模板_回收站', 'accountTemplate:recycle', 'id_account_template', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type', '账户类型', 'accountType:menu', 'id_finance', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_add', '账户类型_新增', 'accountType:add', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_del', '账户类型_逻辑删除', 'accountType:del', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_edit', '账户类型_编辑', 'accountType:edit', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_info', '账户类型_详情', 'accountType:info', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_phydel', '账户类型_物理删除', 'accountType:phydel', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_recovery', '账户类型_回收站_恢复', 'accountType:recovery', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_account_type_recycle', '账户类型_回收站', 'accountType:recycle', 'id_account_type', '系统生成', '0', '0', null, '0', null, '2017-12-20 17:33:10', '2017-12-20 17:59:14', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_org_vs_role', '权限_组织vs角色', 'orgInfo:edit:role', 'id_org_info', '系统生成', '0', '2', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_perm', '功能/权限', 'authPerm:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_perm_add', '权限_功能信息_新增', 'authPerm:add', 'id_auth_perm', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_perm_del', '权限_功能信息_删除', 'authPerm:del', 'id_auth_perm', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_perm_edit', '权限_功能信息_编辑', 'authPerm:edit', 'id_auth_perm', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_perm_info', '权限_功能信息_详情', 'authPerm:info', 'id_auth_perm', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:18:27', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role', '角色管理', 'authRole:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_add', '权限_角色信息_新增', 'authRole:add', 'id_auth_role', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_del', '权限_角色信息_删除', 'authRole:del', 'id_auth_role', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_edit', '权限_角色信息_编辑', 'authRole:edit', 'id_auth_role', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_info', '权限_角色信息_详情', 'authRole:info', 'id_auth_role', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:18:27', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_vs_menu', '权限_角色vs菜单', 'authRole:edit:menu', 'id_auth_role', '系统生成', '0', '2', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_auth_role_vs_perm', '权限_角色vs功能', 'authRole:edit:perm', 'id_auth_role', '系统生成', '0', '2', null, '0', null, '2017-08-10 10:17:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_business_type', '业务类型', 'businessType:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_business_type_add', '业务类型_新增', 'businessType:add', 'id_business_type', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_business_type_del', '业务类型_删除', 'businessType:del', 'id_business_type', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_business_type_edit', '业务类型_编辑', 'businessType:edit', 'id_business_type', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_business_type_info', '业务类型_详情', 'businessType:info', 'id_business_type', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel', '渠道管理', 'channel:menu', 'efa0993149b043508f8e42', '', null, '0', null, '0', '1', '2017-08-15 16:21:53', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_info', '渠道商信息', 'channelInfo:menu', 'id_channel', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_info_add', '渠道商信息_新增', 'channelInfo:add', 'id_channel_info', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_info_del', '渠道商信息_删除', 'channelInfo:del', 'id_channel_info', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_info_edit', '渠道商信息_编辑', 'channelInfo:edit', 'id_channel_info', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_info_info', '渠道商信息_详情', 'channelInfo:info', 'id_channel_info', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:18:27', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_type', '渠道商类型', 'channelType:menu', 'id_channel', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_type_add', '渠道商类型_新增', 'channelType:add', 'id_channel_type', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_type_del', '渠道商类型_删除', 'channelType:del', 'id_channel_type', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_type_edit', '渠道商类型_编辑', 'channelType:edit', 'id_channel_type', '系统生成', '0', '0', null, '0', null, '2017-08-15 16:22:37', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_channel_type_info', '渠道商类型_详情', 'channelType:info', 'id_channel_type', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:18:27', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity', '实名认证表', 'identity:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_add', '实名认证表_新增', 'identity:add', 'id_identity', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_del', '实名认证表_删除', 'identity:del', 'id_identity', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_edit', '实名认证表_编辑', 'identity:edit', 'id_identity', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_info', '实名认证表_详情', 'identity:info', 'id_identity', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_log', '用户实名认证日志', 'identityLog:menu', 'id_identity', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_log_add', '用户实名认证日志_新增', 'identityLog:add', 'id_identity_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_log_del', '用户实名认证日志_删除', 'identityLog:del', 'id_identity_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_log_edit', '用户实名认证日志_编辑', 'identityLog:edit', 'id_identity_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_identity_log_info', '用户实名认证日志_详情', 'identityLog:info', 'id_identity_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:14:24', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_member', '会员信息表', 'member:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_member_add', '会员信息表_新增', 'member:add', 'id_member', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:12', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_member_del', '会员信息表_删除', 'member:del', 'id_member', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_member_edit', '会员信息表_编辑', 'member:edit', 'id_member', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_member_info', '会员信息表_详情', 'member:info', 'id_member', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_info', '组织架构', 'orgInfo:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_info_add', '组织架构_新增', 'orgInfo:add', 'id_org_info', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_info_del', '组织架构_删除', 'orgInfo:del', 'id_org_info', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_info_edit', '组织架构_编辑', 'orgInfo:edit', 'id_org_info', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_info_info', '组织架构_详情', 'orgInfo:info', 'id_org_info', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:18:27', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_login', '员工登录日志', 'orgLogLogin:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_login_add', '组织架构_员工登录日志_新增', 'orgLogLogin:add', 'id_org_log_login', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_login_del', '组织架构_员工登录日志_删除', 'orgLogLogin:del', 'id_org_log_login', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_login_edit', '组织架构_员工登录日志_编辑', 'orgLogLogin:edit', 'id_org_log_login', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_operation', '员工操作日志', 'orgLogOperation:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_operation_add', '组织架构_员工操作日志_新增', 'orgLogOperation:add', 'id_org_log_operation', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_operation_del', '组织架构_员工操作日志_删除', 'orgLogOperation:del', 'id_org_log_operation', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_log_operation_edit', '组织架构_员工操作日志_编辑', 'orgLogOperation:edit', 'id_org_log_operation', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_org_vs_staff', '组织架构_组织vs员工', 'orgInfo:edit:staff', 'id_org_info', '系统生成', '0', '4', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_staff', '员工管理', 'orgStaff:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_staff_add', '组织架构_员工/员工_新增', 'orgStaff:add', 'id_org_staff', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_staff_del', '组织架构_员工/员工_删除', 'orgStaff:del', 'id_org_staff', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_org_staff_edit', '组织架构_员工/员工_编辑', 'orgStaff:edit', 'id_org_staff', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:22', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys', '系统管理', 'sys:menu', 'efa0993149b043508f8e42', '', '0', '1', null, '0', null, '2017-08-10 10:23:32', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app', '应用管理', 'sysApp:menu', 'id_sys', '', null, '1', null, '0', '1', '2017-11-30 11:21:33', '2017-11-30 11:21:51', '1', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app_add', '应用管理_新增', 'sysApp:add', 'id_sys_app', '', null, '0', null, '0', '1', '2017-11-30 11:23:26', '2017-11-30 11:23:26', '1', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app_del', '应用管理_逻辑删除', 'sysApp:del', 'id_sys_app', '系统生成', '0', '0', null, '0', null, '2017-12-20 15:23:22', '2017-12-20 17:58:52', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app_edit', '应用管理_修改', 'sysApp:edit', 'id_sys_app', '', null, '0', null, '0', '1', '2017-11-30 11:24:01', '2017-11-30 11:24:01', '1', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app_info', '应用管理_详情', 'sysApp:info', 'id_sys_app', '', null, '0', null, '0', '1', '2017-11-30 11:24:45', '2017-11-30 11:24:45', '1', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_app_phydel', '应用管理_删除', 'sysApp:phydel', 'id_sys_app', '', null, '0', null, '0', '1', '2017-11-30 11:24:26', '2017-11-30 11:24:26', '1', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_menu', '菜单管理', 'sysMenu:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-10 10:17:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_menu_add', '系统_菜单_新增', 'sysMenu:add', 'id_sys_menu', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_menu_del', '系统_菜单_删除', 'sysMenu:del', 'id_sys_menu', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_menu_edit', '系统_菜单_编辑', 'sysMenu:edit', 'id_sys_menu', '系统生成', '0', '0', null, '0', null, '2017-08-10 10:17:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_menu_info', '系统_菜单_详情', 'sysMenu:info', 'id_sys_menu', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:19:01', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_variable', '数据字典', 'sysVariable:menu', 'id_sys', '系统生成', '0', '1', null, '0', null, '2017-08-07 13:51:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_variable_add', '系统_数据字典表_新增', 'sysVariable:add', 'id_sys_variable', '系统生成', '0', '0', null, '0', null, '2017-08-07 13:51:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_variable_del', '系统_数据字典表_删除', 'sysVariable:del', 'id_sys_variable', '系统生成', '0', '0', null, '0', null, '2017-08-07 13:51:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_variable_edit', '系统_数据字典表_编辑', 'sysVariable:edit', 'id_sys_variable', '系统生成', '0', '0', null, '0', null, '2017-08-07 13:51:41', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_sys_variable_info', '系统_数据字典表_详情', 'sysVariable:info', 'id_sys_variable', '系统生成', '0', '0', null, '0', null, '2017-08-24 10:19:01', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_tag', '标签', 'tag:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_tag_add', '标签_新增', 'tag:add', 'id_tag', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_tag_del', '标签_物理删除', 'tag:phydel', 'id_tag', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_tag_edit', '标签_编辑', 'tag:edit', 'id_tag', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_tag_info', '标签_详情', 'tag:info', 'id_tag', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:23:13', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template', '档案模板', 'template:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '1', null, '0', null, '2017-08-23 16:02:48', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_add', '档案模板_新增', 'template:add', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:48', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute', '模板属性', 'templateAttribute:menu', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute_add', '模板属性_新增', 'templateAttribute:add', 'id_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute_del', '模板属性_删除', 'templateAttribute:del', 'id_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute_edit', '模板属性_编辑', 'templateAttribute:edit', 'id_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute_info', '模板属性_详情', 'templateAttribute:info', 'id_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_attribute_phydel', '模板属性_物理删除', 'templateAttribute:phydel', 'id_template_attribute', '系统生成', '0', '0', null, '0', null, '2017-08-24 14:20:53', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_del', '档案模板_删除', 'template:del', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:48', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_edit', '档案模板_编辑', 'template:edit', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:48', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_info', '档案模板_详情', 'template:info', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-23 16:02:49', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_template_phydel', '档案模板_物理删除', 'template:phydel', 'id_template', '系统生成', '0', '0', null, '0', null, '2017-08-24 14:21:07', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user', '客户表', 'user:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '2', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_userr_login_log', '客户登录日志', 'userLoginLog:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '2', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_userr_login_log_add', '用户登录日志_新增', 'userLoginLog:add', 'id_userr_login_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_userr_login_log_del', '用户登录日志_删除', 'userLoginLog:del', 'id_userr_login_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_userr_login_log_edit', '用户登录日志_编辑', 'userLoginLog:edit', 'id_userr_login_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_userr_login_log_info', '用户登录日志_详情', 'userLoginLog:info', 'id_userr_login_log', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_add', '用户表_新增', 'user:add', 'id_user', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_del', '用户表_删除', 'user:phydel', 'id_user', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_edit', '用户表_编辑', 'user:edit', 'id_user', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_ext_info', '客户扩展数据', 'userExtInfo:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '2', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_ext_info_add', '用户扩展数据_新增', 'userExtInfo:add', 'id_user_ext_info', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_ext_info_del', '用户扩展数据_删除', 'userExtInfo:del', 'id_user_ext_info', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_ext_info_edit', '用户扩展数据_编辑', 'userExtInfo:edit', 'id_user_ext_info', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_ext_info_info', '用户扩展数据_详情', 'userExtInfo:info', 'id_user_ext_info', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_info', '用户表_详情', 'user:info', 'id_user', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_sign_contract', '客户网签协议记录', 'userSignContract:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '2', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_sign_contract_add', '用户网签协议记录_新增', 'userSignContract:add', 'id_user_sign_contract', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_sign_contract_del', '用户网签协议记录_删除', 'userSignContract:del', 'id_user_sign_contract', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_sign_contract_edit', '用户网签协议记录_编辑', 'userSignContract:edit', 'id_user_sign_contract', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_sign_contract_info', '用户网签协议记录_详情', 'userSignContract:info', 'id_user_sign_contract', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_snapshot', '客户照表', 'userSnapshot:menu', '1a2efe8d578945c593c93a', '系统生成', '0', '2', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_snapshot_add', '用户照表_新增', 'userSnapshot:add', 'id_user_snapshot', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_snapshot_del', '用户照表_删除', 'userSnapshot:del', 'id_user_snapshot', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_snapshot_edit', '用户照表_编辑', 'userSnapshot:edit', 'id_user_snapshot', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');
INSERT INTO `auth_perm` VALUES ('id_user_snapshot_info', '用户照表_详情', 'userSnapshot:info', 'id_user_snapshot', '系统生成', '0', '0', null, '0', null, '2017-08-23 15:55:40', '2017-12-11 11:18:23', '0', 'sys');

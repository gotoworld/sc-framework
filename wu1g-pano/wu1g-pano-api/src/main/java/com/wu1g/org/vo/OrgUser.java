/*	
 * 组织架构_用户  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.vo;
import java.util.List;

import com.wu1g.framework.vo.BaseVO;
/**
 * <p>组织架构_用户  BEAN类。</p>	
 * @author easycode
 */
public class OrgUser extends BaseVO{

	private static final long serialVersionUID = -520196248671485682L;
	/**ID */
	private String id;
	/**员工UserID */
	private String userid;
	/**员工密码 */
	private String pwd;
	/**成员名称 */
	private String name;
	/**工号 */
	private String jobNo;
	/**职位 */
	private String position;
	/**性别[0男1女] */
	private String gender;
	/**移动电话 */
	private String mobile;
	/**固定电话 */
	private String tel;
	/**是否启用启用/禁用成员。1表示启用成员，0表示禁用成员  默认值(1) */
	private String enable;
	/**头像url。注：小图将url最后的"/0"改成"/64" */
	private String avatar;
	/**关注状态: 1=已关注，2=已冻结，4=未关注  默认值(1) */
	private String status;
	/**邮箱 */
	private String email;
	/**微信id */
	private String weixinid;
	/**会员类型0管理员1普通用户  默认值(1) */
	private String type;
	/**最后登录日期  默认值(2000-01-01 00:00:00) */
	private String lastLogin;
	/**登录次数 */
	private String count;
	/**状态  默认值(0) */
	private String state;
	/**皮肤  默认值(default) */
	private String skin;
	/**备注 */
	private String memo;
	/**关键字 */
	private String keyword;
	/**版本号  默认值(0) */
	private String version;
	/**排序 */
	private String orderNo;
	/**是否删除(0否1是)  默认值(0) */
	private String delFlag;
	/**数据过期时间0:永不过期  默认值(0) */
	private String invalidTime;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**修改时间  默认值(2000-01-01 00:00:00) */
	private String dateUpdate;
	/**修改者ID */
	private String updateId;
	/**修改者IP */
	private String updateIp;
	/**用户角色id集合*/
	private List<String> roleIdArray;
	/**用户角色名称集合*/
	private String roleNames;
	/**部门id集合*/
	private List<String> deptIdArray;
	/**
	 * ID取得
	 * @return ID
	 */
	public String getId(){
		return id;
	}
	/**
	 * ID设定
	 * @param id ID
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * 员工UserID取得
	 * @return 员工UserID
	 */
	public String getUserid(){
		return userid;
	}
	/**
	 * 员工UserID设定
	 * @param userid 员工UserID
	 */
	public void setUserid(String userid){
		this.userid=userid;
	}
	/**
	 * 员工密码取得
	 * @return 员工密码
	 */
	public String getPwd(){
		return pwd;
	}
	/**
	 * 员工密码设定
	 * @param pwd 员工密码
	 */
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	/**
	 * 成员名称取得
	 * @return 成员名称
	 */
	public String getName(){
		return name;
	}
	/**
	 * 成员名称设定
	 * @param name 成员名称
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * 工号取得
	 * @return 工号
	 */
	public String getJobNo(){
		return jobNo;
	}
	/**
	 * 工号设定
	 * @param jobNo 工号
	 */
	public void setJobNo(String jobNo){
		this.jobNo=jobNo;
	}
	/**
	 * 职位取得
	 * @return 职位
	 */
	public String getPosition(){
		return position;
	}
	/**
	 * 职位设定
	 * @param position 职位
	 */
	public void setPosition(String position){
		this.position=position;
	}
	/**
	 * 性别[0男1女]取得
	 * @return 性别[0男1女]
	 */
	public String getGender(){
		return gender;
	}
	/**
	 * 性别[0男1女]设定
	 * @param gender 性别[0男1女]
	 */
	public void setGender(String gender){
		this.gender=gender;
	}
	/**
	 * 移动电话取得
	 * @return 移动电话
	 */
	public String getMobile(){
		return mobile;
	}
	/**
	 * 移动电话设定
	 * @param mobile 移动电话
	 */
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	/**
	 * 固定电话取得
	 * @return 固定电话
	 */
	public String getTel(){
		return tel;
	}
	/**
	 * 固定电话设定
	 * @param tel 固定电话
	 */
	public void setTel(String tel){
		this.tel=tel;
	}
	/**
	 * 是否启用启用/禁用成员。1表示启用成员，0表示禁用成员  默认值(1)取得
	 * @return 是否启用启用/禁用成员。1表示启用成员，0表示禁用成员  默认值(1)
	 */
	public String getEnable(){
		return enable;
	}
	/**
	 * 是否启用启用/禁用成员。1表示启用成员，0表示禁用成员  默认值(1)设定
	 * @param enable 是否启用启用/禁用成员。1表示启用成员，0表示禁用成员  默认值(1)
	 */
	public void setEnable(String enable){
		this.enable=enable;
	}
	/**
	 * 头像url。注：小图将url最后的"/0"改成"/64"取得
	 * @return 头像url。注：小图将url最后的"/0"改成"/64"
	 */
	public String getAvatar(){
		return avatar;
	}
	/**
	 * 头像url。注：小图将url最后的"/0"改成"/64"设定
	 * @param avatar 头像url。注：小图将url最后的"/0"改成"/64"
	 */
	public void setAvatar(String avatar){
		this.avatar=avatar;
	}
	/**
	 * 关注状态: 1=已关注，2=已冻结，4=未关注  默认值(1)取得
	 * @return 关注状态: 1=已关注，2=已冻结，4=未关注  默认值(1)
	 */
	public String getStatus(){
		return status;
	}
	/**
	 * 关注状态: 1=已关注，2=已冻结，4=未关注  默认值(1)设定
	 * @param status 关注状态: 1=已关注，2=已冻结，4=未关注  默认值(1)
	 */
	public void setStatus(String status){
		this.status=status;
	}
	/**
	 * 邮箱取得
	 * @return 邮箱
	 */
	public String getEmail(){
		return email;
	}
	/**
	 * 邮箱设定
	 * @param email 邮箱
	 */
	public void setEmail(String email){
		this.email=email;
	}
	/**
	 * 微信id取得
	 * @return 微信id
	 */
	public String getWeixinid(){
		return weixinid;
	}
	/**
	 * 微信id设定
	 * @param weixinid 微信id
	 */
	public void setWeixinid(String weixinid){
		this.weixinid=weixinid;
	}
	/**
	 * 会员类型0管理员1普通用户  默认值(1)取得
	 * @return 会员类型0管理员1普通用户  默认值(1)
	 */
	public String getType(){
		return type;
	}
	/**
	 * 会员类型0管理员1普通用户  默认值(1)设定
	 * @param type 会员类型0管理员1普通用户  默认值(1)
	 */
	public void setType(String type){
		this.type=type;
	}
	/**
	 * 最后登录日期  默认值(2000-01-01 00:00:00)取得
	 * @return 最后登录日期  默认值(2000-01-01 00:00:00)
	 */
	public String getLastLogin(){
		return lastLogin;
	}
	/**
	 * 最后登录日期  默认值(2000-01-01 00:00:00)设定
	 * @param lastLogin 最后登录日期  默认值(2000-01-01 00:00:00)
	 */
	public void setLastLogin(String lastLogin){
		this.lastLogin=lastLogin;
	}
	/**
	 * 登录次数取得
	 * @return 登录次数
	 */
	public String getCount(){
		return count;
	}
	/**
	 * 登录次数设定
	 * @param count 登录次数
	 */
	public void setCount(String count){
		this.count=count;
	}
	/**
	 * 状态  默认值(0)取得
	 * @return 状态  默认值(0)
	 */
	public String getState(){
		return state;
	}
	/**
	 * 状态  默认值(0)设定
	 * @param state 状态  默认值(0)
	 */
	public void setState(String state){
		this.state=state;
	}
	/**
	 * 皮肤  默认值(default)取得
	 * @return 皮肤  默认值(default)
	 */
	public String getSkin(){
		return skin;
	}
	/**
	 * 皮肤  默认值(default)设定
	 * @param skin 皮肤  默认值(default)
	 */
	public void setSkin(String skin){
		this.skin=skin;
	}
	/**
	 * 备注取得
	 * @return 备注
	 */
	public String getMemo(){
		return memo;
	}
	/**
	 * 备注设定
	 * @param memo 备注
	 */
	public void setMemo(String memo){
		this.memo=memo;
	}
	/**
	 * 关键字取得
	 * @return 关键字
	 */
	public String getKeyword(){
		return keyword;
	}
	/**
	 * 关键字设定
	 * @param keyword 关键字
	 */
	public void setKeyword(String keyword){
		this.keyword=keyword;
	}
	/**
	 * 版本号  默认值(0)取得
	 * @return 版本号  默认值(0)
	 */
	public String getVersion(){
		return version;
	}
	/**
	 * 版本号  默认值(0)设定
	 * @param version 版本号  默认值(0)
	 */
	public void setVersion(String version){
		this.version=version;
	}
	/**
	 * 排序取得
	 * @return 排序
	 */
	public String getOrderNo(){
		return orderNo;
	}
	/**
	 * 排序设定
	 * @param orderNo 排序
	 */
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**
	 * 是否删除(0否1是)  默认值(0)取得
	 * @return 是否删除(0否1是)  默认值(0)
	 */
	public String getDelFlag(){
		return delFlag;
	}
	/**
	 * 是否删除(0否1是)  默认值(0)设定
	 * @param delFlag 是否删除(0否1是)  默认值(0)
	 */
	public void setDelFlag(String delFlag){
		this.delFlag=delFlag;
	}
	/**
	 * 数据过期时间0:永不过期  默认值(0)取得
	 * @return 数据过期时间0:永不过期  默认值(0)
	 */
	public String getInvalidTime(){
		return invalidTime;
	}
	/**
	 * 数据过期时间0:永不过期  默认值(0)设定
	 * @param invalidTime 数据过期时间0:永不过期  默认值(0)
	 */
	public void setInvalidTime(String invalidTime){
		this.invalidTime=invalidTime;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)取得
	 * @return 创建时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateCreated(){
		return dateCreated;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateCreated 创建时间  默认值(2000-01-01 00:00:00)
	 */
	public void setDateCreated(String dateCreated){
		this.dateCreated=dateCreated;
	}
	/**
	 * 建立者ID取得
	 * @return 建立者ID
	 */
	public String getCreateId(){
		return createId;
	}
	/**
	 * 建立者ID设定
	 * @param createId 建立者ID
	 */
	public void setCreateId(String createId){
		this.createId=createId;
	}
	/**
	 * 建立者IP取得
	 * @return 建立者IP
	 */
	public String getCreateIp(){
		return createIp;
	}
	/**
	 * 建立者IP设定
	 * @param createIp 建立者IP
	 */
	public void setCreateIp(String createIp){
		this.createIp=createIp;
	}
	/**
	 * 修改时间  默认值(2000-01-01 00:00:00)取得
	 * @return 修改时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateUpdate(){
		return dateUpdate;
	}
	/**
	 * 修改时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateUpdate 修改时间  默认值(2000-01-01 00:00:00)
	 */
	public void setDateUpdate(String dateUpdate){
		this.dateUpdate=dateUpdate;
	}
	/**
	 * 修改者ID取得
	 * @return 修改者ID
	 */
	public String getUpdateId(){
		return updateId;
	}
	/**
	 * 修改者ID设定
	 * @param updateId 修改者ID
	 */
	public void setUpdateId(String updateId){
		this.updateId=updateId;
	}
	/**
	 * 修改者IP取得
	 * @return 修改者IP
	 */
	public String getUpdateIp(){
		return updateIp;
	}
	/**
	 * 修改者IP设定
	 * @param updateIp 修改者IP
	 */
	public void setUpdateIp(String updateIp){
		this.updateIp=updateIp;
	}
	/**
	 * 用户角色id集合取得
	 * @return 用户角色id集合
	 */
	public List<String> getRoleIdArray() {
	    return roleIdArray;
	}
	/**
	 * 用户角色id集合设定
	 * @param roleIdArray 用户角色id集合
	 */
	public void setRoleIdArray(List<String> roleIdArray) {
	    this.roleIdArray = roleIdArray;
	}
	/**
	 * 用户角色名称集合取得
	 * @return 用户角色名称集合
	 */
	public String getRoleNames() {
	    return roleNames;
	}
	/**
	 * 用户角色名称集合设定
	 * @param roleNames 用户角色名称集合
	 */
	public void setRoleNames(String roleNames) {
	    this.roleNames = roleNames;
	}
	/**
	 * 部门id集合取得
	 * @return 部门id集合
	 */
	public List<String> getDeptIdArray() {
	    return deptIdArray;
	}
	/**
	 * 部门id集合设定
	 * @param deptIdArray 部门id集合
	 */
	public void setDeptIdArray(List<String> deptIdArray) {
	    this.deptIdArray = deptIdArray;
	}
}
package com.hsd.dao.org;

import java.util.List;
import java.util.Map;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import com.hsd.vo.org.OrgUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>组织架构_用户  数据库处理接口类。
 */
@Mapper
public interface IOrgUserDao extends IBaseDao {
    /**
     * <p>获取用户信息>根据用户登录名。
     */
    OrgUser findUserByLoginName(Map dto) throws Exception;

    /**
     * 获取某一种角色所有用户
     */
    List<OrgUser> getUserList(OrgUser dto) throws Exception;

    /**
     * 获取某一种角色所有用户(分页)
     */
    List<OrgUser> getUserIsPage(OrgUser dto) throws Exception;

    /**
     * <p>判断用户id是否存在
     */
    int isUidYN(@Param("UserId") String uid) throws Exception;
    /**
     * <p>密码修改
     */
    @Update("update org_user set version=version+1,date_updated=now(),pwd=#{confirmpwd} where id = #{id} and pwd=#{oldpwd}")
    int updatePwd(IEntity entity) throws Exception;

    List<OrgUser> findTeacherDataIsList(OrgUser dto);

    /**
     * <p>最后登陆记录
     */
    @Update("update org_user set count=count+1,last_login=now() where id = #{id}")
    int lastLogin(IEntity entity) throws Exception;
}
package com.vr.dao.sys;

import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>系统_类目 数据库处理接口类。
 */
@Mapper
public interface ISysCategoryDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_category where  id = #{id} ")
    int isDataYN(IEntity dto) throws Exception;
}
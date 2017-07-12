package com.hsd.dao.sys;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>系统_敏感词 数据库处理接口类。
 */
@Mapper
public interface ISysSensitiveWordsDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_sensitive_words where  id = #{id} ")
    int isDataYN(IEntity dto) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_sensitive_words where  id = #{id} ")
    int deleteByPrimaryKey(IEntity dto) throws Exception;
}
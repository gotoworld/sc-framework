package com.wu1g.dao.sys;

import com.wu1g.framework.IBaseDao;
import com.wu1g.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>系统_资讯vs类目 数据库处理接口类。
 */
@Mapper
public interface ISysNewsVsCategoryDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_news_vs_category where  news_id = #{newsId} and  category_id = #{categoryId} ")
    int isDataYN(IEntity dto) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete ftom sys_news_vs_category where  news_id = #{newsId} and  category_id = #{categoryId} ")
    int deleteByPrimaryKey(IEntity dto) throws Exception;
}
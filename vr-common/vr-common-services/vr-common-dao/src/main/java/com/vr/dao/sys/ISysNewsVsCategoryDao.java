package com.vr.dao.sys;

import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Delete("delete from sys_news_vs_category where  news_id = #{newsId} and  category_id = #{categoryId} ")
    int deleteByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 根据资讯id 物理删除
     */
    @Delete("delete from sys_news_vs_category where  news_id = #{newsId}")
    int deleteByNewsId(IEntity dto) throws Exception;
    /**根据资讯id获取栏目*/
    @Select("select category_id from sys_news_vs_category where  news_id = #{newsId}")
    List<Long> getNewsCategorysByNewsId(IEntity dto) throws Exception;
}
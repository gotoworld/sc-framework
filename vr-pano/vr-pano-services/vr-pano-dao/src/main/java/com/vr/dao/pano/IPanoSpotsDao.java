package com.vr.dao.pano;

import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>全景_热点 数据库处理接口类
 */
@Mapper
public interface IPanoSpotsDao extends IBaseDao {
    /**
     * 根据项目id删除
     */
    int deleteByProjId(IEntity dto) throws Exception;

    /**
     * 删除场景不存在的热点
     */
    int deletePanoSpots(IEntity dto) throws Exception;
}
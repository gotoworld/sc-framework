package com.wu1g.dao.org;

import com.wu1g.framework.IBaseDao;
import com.wu1g.vo.org.OrgDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>组织架构_部门  数据库处理接口类。
 */
@Mapper
public interface IOrgDeptDao extends IBaseDao {
    /**
     * 获取用户所在部门集合>根据用户id
     */
    List<OrgDept> getDeptListByUId(Map dto) throws Exception;
}
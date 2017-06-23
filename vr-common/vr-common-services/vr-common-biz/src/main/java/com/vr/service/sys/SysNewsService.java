package com.vr.service.sys;

import com.vr.api.sys.ISysNewsService;
import com.vr.dao.sys.ISysNewsDao;
import com.vr.dao.sys.ISysNewsVsCategoryDao;
import com.vr.framework.annotation.FeignService;
import com.vr.vo.sys.SysNews;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.service.BaseService;
import com.vr.framework.util.CommonConstant;
import com.github.pagehelper.PageHelper;
import com.vr.vo.sys.SysNewsVsCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@FeignService
@Slf4j
public class SysNewsService extends BaseService implements ISysNewsService {
    @Autowired
    private ISysNewsDao sysNewsDao;
    @Autowired
    private ISysNewsVsCategoryDao sysNewsVsCategoryDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysNews dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysNewsDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysNewsDao.update(dto);
                } else {
                    //新增
                     sysNewsDao.insert(dto);
                }

                SysNewsVsCategory sysNewsVsCategory=new SysNewsVsCategory();
                sysNewsVsCategory.setNewsId(dto.getId());
                sysNewsVsCategoryDao.deleteByNewsId(sysNewsVsCategory);

                if(dto.getCategorys()!=null && dto.getCategorys().size()>0){
                    List<SysNewsVsCategory> sysNewsVsCategoriesList=new ArrayList<>();
                    dto.getCategorys().forEach(catecory->{
                        SysNewsVsCategory sysNewsVsCategory2=new SysNewsVsCategory();
                        sysNewsVsCategory2.setNewsId(dto.getId());
                        sysNewsVsCategory2.setCategoryId(catecory);
                        sysNewsVsCategoriesList.add(sysNewsVsCategory2);
                    });
                    sysNewsVsCategoryDao.insertBatch(sysNewsVsCategoriesList);
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(SysNews dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.deleteByPrimaryKey(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysNews dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.deleteById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public List<SysNews> findDataIsPage(SysNews dto) throws Exception {
        List<SysNews> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysNews>) sysNewsDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    public List<SysNews> findDataIsList(SysNews dto) throws Exception {
        List<SysNews> results = null;
        try {
            results = (List<SysNews>) sysNewsDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysNews findDataById(SysNews dto) throws Exception {
        SysNews result = null;
        try {
            result = (SysNews) sysNewsDao.selectByPrimaryKey(dto);
            if(result!=null){
                SysNewsVsCategory sysNewsVsCategory=new SysNewsVsCategory();
                sysNewsVsCategory.setNewsId(result.getId());
                result.setCategorys(sysNewsVsCategoryDao.getNewsCategorysByNewsId(sysNewsVsCategory));
            }
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return result;
    }

    @Override
    public String recoveryDataById(SysNews dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.recoveryDataById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}
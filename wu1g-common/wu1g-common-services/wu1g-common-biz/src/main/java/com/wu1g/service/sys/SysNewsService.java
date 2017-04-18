package com.wu1g.service.sys;

import com.wu1g.api.sys.ISysNewsService;
import com.wu1g.dao.sys.ISysNewsDao;
import com.wu1g.dao.sys.ISysNewsVsCategoryDao;
import com.wu1g.vo.sys.SysNews;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.github.pagehelper.PageHelper;
import com.wu1g.vo.sys.SysNewsVsCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SysNewsService extends BaseService implements ISysNewsService {
    @Autowired
    private ISysNewsDao sysNewsDao;
    @Autowired
    private ISysNewsVsCategoryDao sysNewsVsCategoryDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysNews dto) throws Exception {
        String msg = "seccuss";
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
                throw new Exception("信息保存失败!");
            }
        }
        return msg;
    }

    @Override
    public String deleteData(SysNews dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.deleteByPrimaryKey(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除失败!", e);
                throw new Exception(e.getMessage());
            }
        }
        return msg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysNews dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.deleteById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除失败!", e);
                throw new Exception(e.getMessage());
            }
        }
        return msg;
    }

    @Override
    public List<SysNews> findDataIsPage(SysNews dto) throws Exception {
        List<SysNews> dtos = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            dtos = (List<SysNews>) sysNewsDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new Exception("信息查询失败!");
        }
        return dtos;
    }

    @Override
    public List<SysNews> findDataIsList(SysNews dto) throws Exception {
        List<SysNews> dtos = null;
        try {
            dtos = (List<SysNews>) sysNewsDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new Exception("信息查询失败!");
        }
        return dtos;
    }

    @Override
    @RfAccount2Bean
    public SysNews findDataById(SysNews dto) throws Exception {
        SysNews dto1 = null;
        try {
            dto1 = (SysNews) sysNewsDao.selectByPrimaryKey(dto);
            if(dto1!=null){
                SysNewsVsCategory sysNewsVsCategory=new SysNewsVsCategory();
                sysNewsVsCategory.setNewsId(dto1.getId());
                dto1.setCategorys(sysNewsVsCategoryDao.getNewsCategorysByNewsId(sysNewsVsCategory));
            }
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new Exception("信息查询失败!");
        }
        return dto1;
    }

    @Override
    public String recoveryDataById(SysNews dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysNewsDao.recoveryDataById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复失败!", e);
                throw new Exception(e.getMessage());
            }
        }
        return msg;
    }
}
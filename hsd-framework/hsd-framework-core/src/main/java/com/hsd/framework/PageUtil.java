package com.hsd.framework;

import com.github.pagehelper.PageInfo;
import com.hsd.framework.page.PageDto;

/**
 * Created by Administrator on 2016/11/9.
 */
public class PageUtil {
    public static void copy(PageDto pageDto, PageInfo pageInfo){
        if(pageInfo==null){
            return;
        }
        pageDto.setData(pageInfo.getList());
        pageDto.setDataCount((int) pageInfo.getTotal());
        pageDto.setPageIndex(pageInfo.getPageNum());
        pageDto.setPageSize(pageInfo.getPageSize());
    }
    public static PageDto copy(PageInfo pageInfo){
        PageDto pageDto=new PageDto();
        copy(pageDto,pageInfo);
        return pageDto;
    }
}
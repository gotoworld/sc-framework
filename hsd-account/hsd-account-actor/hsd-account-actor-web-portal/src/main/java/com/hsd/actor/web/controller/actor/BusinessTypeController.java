package com.hsd.actor.web.controller.actor;

import com.hsd.account.actor.api.actor.IBusinessTypeService;
import com.hsd.account.actor.dto.actor.BusinessTypeDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "业务类型")
@RestController
@Slf4j
public class BusinessTypeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IBusinessTypeService businessTypeService;
    private static final String acPrefix = "/api/account/actor/actor/businessType/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  BusinessTypeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("BusinessTypeController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new BusinessTypeDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(businessTypeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("BusinessTypeController info.........");
        Response result = new Response();
        try {
            BusinessTypeDto dto = new BusinessTypeDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = businessTypeService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}
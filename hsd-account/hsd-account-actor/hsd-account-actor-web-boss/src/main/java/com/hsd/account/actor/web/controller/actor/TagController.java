package com.hsd.account.actor.web.controller.actor;

import com.hsd.account.actor.api.actor.ITagService;
import com.hsd.account.actor.dto.actor.TagDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "标签")
@RestController
@Slf4j
public class TagController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ITagService tagService;
    private static final String acPrefix = "/boss/account/actor/actor/tag/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("tag:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  TagDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("TagController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new TagDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);

            result.data = PageUtil.copy(tagService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("tag:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@ModelAttribute  TagDto dto) {
        log.info("TagController userId.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null||dto.getType()==null) {throw new RuntimeException("参数异常!");}
            result.data = tagService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("tag:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("TagController info.........");
        Response result = new Response(0, "success");
        try {
            if (id==null) {throw new RuntimeException("参数异常!");}
            TagDto dto = new TagDto(){{
                setId(id);

            }};
            result.data = tagService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>物理删除。
     */
    @RequiresPermissions("tag:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "标签-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("TagController phydel.........");
        Response result = new Response(0, "success");
        try {
            if (id==null) {throw new RuntimeException("参数异常!");};
            TagDto dto = new TagDto(){{
                setId(id);
            }};
            result.message = tagService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"tag:add", "tag:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "标签")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute TagDto dto, BindingResult bindingResult) {
        log.info("TagController save.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + dto.getToken()))) {
                throw new RuntimeException("请不要重复提交!");
            }
            if (bindingResult.hasErrors()) {
                String errorMsg = "";
                List<ObjectError> errorList = bindingResult.getAllErrors();
                for (ObjectError error : errorList) {
                    errorMsg += (error.getDefaultMessage()) + ";";
                }
                result = Response.error(errorMsg);
            } else {
                result = tagService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
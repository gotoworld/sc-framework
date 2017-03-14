package com.wu1g.domain;

import com.wu1g.framework.vo.BaseVO;
import lombok.Data;

@Data
public class SchemaConf extends BaseVO {
    private static final long serialVersionUID = -762636592679599049L;
    private String _domain;//domain
    private String _mybatis;//mybatis
    private String _dao;//dao
    private String _service;//service
    private String _api;//api
    private String _web_rest;//web.rest
    private String _web_ctrl;//web.ctrl
    private String _view_ng;//view.ng
    private String _view_btl;//view.btl
    private String _insert;//增
    private String _insertBatch;//批增
    private String _delLogic;//逻辑删
    private String _recovery;//逻辑恢
    private String _del;//物理删
    private String _update;//改
    private String _page;//分页
    private String _list;//列表
    private String _detail;//详情
    private String _del_flag;//删除字段名称
    private String _date_created;//创建时间字段名称
    private String _date_updated;//修改时间字段名称
}
package com.hsd.domain;

import com.hsd.framework.vo.BaseVO;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SchemaConf extends BaseVO {
    private static final long serialVersionUID = -762636592679599049L;
    private String _domain;//domain
    private String _dto;//dto
    private String _mybatis;//mybatis
    private String _dao;//dao
    private String _dao_suffix;//dao后缀
    private String _biz;//service
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

    private String _my_pkg;
    private String _domain_pkg;
    private String _dto_pkg;
    private String _mybatis_pkg;
    private String _dao_pkg;
    private String _api_pkg;
    private String _biz_pkg;
    private String _web_pkg;
    private String _view_pkg;
    private String _web_http;

    private Map<String,String> _col_show;
    private Map<String,String> _col_edit;
    private Map<String,String> _edit_type;
    private String _col_created;
    private String _col_updated;
    private String _col_del;
    private String _col_autopk;
    private String _col_version;
    private String _template_dir;
}
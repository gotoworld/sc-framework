package com.wu1g.domain;

import com.wu1g.framework.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class SchemaInfo extends BaseVO {
    private static final long serialVersionUID = -762636592679599049L;
    private String dbName;
    private String dbCharset;
    private String tableName;
    private String tableComment;
    private String columnName;
    private String columnComment;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String dataLength;
    private String pk;

    private List<String> dbs;
    private List<String> tables;
    private List<String> columns;
}
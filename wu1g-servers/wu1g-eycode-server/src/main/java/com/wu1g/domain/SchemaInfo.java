package com.wu1g.domain;

import com.wu1g.framework.util.StrUtil;
import com.wu1g.framework.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class SchemaInfo extends BaseVO {
    private static final long serialVersionUID = -762636592679599049L;
    /**数据库名*/
    private String dbName;
    /**数据库编码*/
    private String dbCharset;
    /**表名*/
    private String tableName;
    /**表名 小驼峰格式化*/
    private String tableNameFormat;
    /**表备注*/
    private String tableComment;
    /**字段名*/
    private String columnName;
    /**字段名 小驼峰格式化*/
    private String columnNameFormat;
    /**字段备注*/
    private String columnComment;
    /**字段默认值*/
    private String columnDefault;
    /**字段非空*/
    private String isNullable;
    /**字段类型*/
    private String dataType;
    /**字段长度*/
    private Integer dataLength;
    /**是否主键*/
    private String pk;
    /**是否自增*/
    private String autoIncrement;

    private List<String> dbs;
    private List<String> tables;
    private List<String> columns;

    private SchemaConf schemaConf;

    private List<SchemaInfo> tablesConf;

    public String getTableNameFormat(){
        return StrUtil.toUpperCase(tableName,"_");
    }
    public String getColumnNameFormat(){
        return StrUtil.fmtStr(columnName,"_");
    }
}
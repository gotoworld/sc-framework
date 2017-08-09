package com.hsd.domain;

import com.hsd.framework.IEntity;
import com.hsd.framework.util.StrUtil;
import com.hsd.framework.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class SchemaInfo extends BaseDto implements IEntity {
    private static final long serialVersionUID = -762636592679599049L;
    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 数据库编码
     */
    private String dbCharset;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表名 小驼峰格式化
     */
    private String tableNameFormat;
    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 字段名 小驼峰格式化
     */
    private String columnNameFormat;
    /**
     * 字段备注
     */
    private String columnComment;
    /**
     * 字段默认值
     */
    private String columnDefault;
    /**
     * 字段非空
     */
    private String isNullable;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 字段长度
     */
    private Long dataLength;
    /**
     * 是否主键
     */
    private String pk;
    /**
     * 是否自增
     */
    private String autoIncrement;

    /**
     * jsr303
     */
    private String JSR303;
    /**
     * 实体属性类型
     */
    private String javaType;

    private List<String> dbs;
    private List<String> tables;
    private List<String> columns;

    private SchemaConf schemaConf;

    private List<SchemaInfo> tablesConf;

    public String getTableNameFormat() {
        return StrUtil.toUpperCase(tableName, "_");
    }

    public String getColumnNameFormat() {
        return StrUtil.fmtStr(columnName, "_");
    }

    public String getJSR303() {
        String _jsr303 = "";
        if (!columnName.equals("id") && isNullable.equals("NO") && !("#datetime#timestamp#".contains("#" + dataType + "#")) && !("#id#version#keyword#del_flag#create_id#date_created#date_updated#bi_update_ts#".contains("#"+columnName+"#"))) {
            _jsr303 += "@NotNull(message=\"" + columnName + "不能为空\")";
        }
        if (!("#date#time#datetime#timestamp#year#tinyint#smallint#mediumint#int#bigint#float#double#decimal#".contains("#" + dataType + "#"))) {
            if (dataLength > 0) {
                _jsr303 += "@Size(max=" + dataLength + ",message=\"" + columnName + "最大" + dataLength + "字符\")";
            }
        }
        return _jsr303;
    }
    public String getJavaType() {
        String _javaType = "String";
        if (("#date#time#datetime#timestamp#year#".contains("#" + dataType + "#"))) {
            _javaType="Date";
        }else
        if (("#tinyint#".contains("#" + dataType + "#"))) {
            _javaType="Integer";
        }else
        if (("#smallint#mediumint#int#".contains("#" + dataType + "#"))) {
            _javaType="Integer";
        }else
        if (("#bigint#".contains("#" + dataType + "#"))) {
            _javaType="Long";
        }else
        if (("#float#double#".contains("#" + dataType + "#"))) {
            _javaType="Double";
        }else
        if (("#decimal#".contains("#" + dataType + "#"))) {
            _javaType="BigDecimal";
        }
        return _javaType;
    }

    public static void main(String[] args) {
        System.out.println(("#smallint#mediumint#int#".contains("#" + "int" + " ")));
    }
}
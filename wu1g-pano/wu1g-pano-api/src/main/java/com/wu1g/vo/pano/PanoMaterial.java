package com.wu1g.vo.pano;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wu1g.framework.vo.BaseVO;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoMaterial extends BaseVO {
     /**ID*/
     private Long id;
     /**素材名称*/
	@NotNull(message="name不能为空")@Size(max=55,message="name最大55字符")
     private String name;
     /**素材类型(0图片1音乐2视频)*/
     private Integer type;
     /**封面地址*/
	@Size(max=255,message="logo_url最大255字符")
     private String logoUrl;
     /**素材地址*/
	@Size(max=255,message="material_url最大255字符")
     private String materialUrl;
     /**状态*/
     private Integer state;
     /**备注*/
	@Size(max=255,message="memo最大255字符")
     private String memo;
     /**版本号*/
     private Integer version;
     /**排序*/
     private Integer orderNo;
     /**关键字*/
	@Size(max=255,message="keyword最大255字符")
     private String keyword;
     /**删除标记(0正常1删除)*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
}
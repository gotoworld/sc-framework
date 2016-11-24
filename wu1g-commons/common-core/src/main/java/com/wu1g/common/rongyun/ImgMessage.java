package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgMessage extends Message {
    private String imageUri;
    private String title;
    private String type = "RC:ImgMsg";
    public String toString(){
        return Converter.parseObject(this);
    }
}

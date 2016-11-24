package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//文本消息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxtMessage extends Message {
	private String type="RC:TxtMsg";
	public String toString(){
		return Converter.parseObject(this);
	}
}

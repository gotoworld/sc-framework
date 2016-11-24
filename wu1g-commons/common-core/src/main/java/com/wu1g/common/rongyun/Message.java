package com.wu1g.common.rongyun;

import com.wu1g.framework.AbstractEntity;
import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息模板基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message extends AbstractEntity implements Serializable {
	private String content;
	private String extra;

	public String toString(){
		return Converter.parseObject(this);
	}
}

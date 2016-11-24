package com.wu1g.common.rongyun;

import com.wu1g.framework.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加联系人（申请添加好友）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactNtfMessage extends Message {
    private String toAccid = "3";
	private String operation = "Request" ;
	private String sourceUserId;
	private String targetUserId;
	private String message;
	private String type="RC:ContactNtf";
	public String toString(){
		return Converter.parseObject(this);
	}
}

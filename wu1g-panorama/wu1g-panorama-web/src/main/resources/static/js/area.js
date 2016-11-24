/**
 * ajax 省市县 加载 公共 js // 省市县 select 联动
 *
 * VERSION  DATE        BY           REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00     2014-3-27  WuXiaoGang        程序・发布
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2014 xx System. - All Rights Reserved.
 */

/*
 * ====ajax 数据加载 =====
 * url:action地址 
 * event:方法 
 * data:数据
 */ 
function loadArea1(url, event, data) {
	if (url == null || url == "" || event == null || event == "") {
		return;
	}
	if (data == null || data=="" || data=='null') {
		data = "";
	}
	jQuery.ajax({
		async : false,
		url : basePath+'/' + url + event+'.ac?'+ data + '&time=' + new Date(),
		success : function(req) {
			data = req;
		},
		error : function() {
			// return 0;
		}
	});
	return data;
}
/*
 * ===取得省信息=====
 * id:省id 
 * name:省名称 
 * divId:要显示在的控件id
 */ 
function areaA(id, name, divId) {
	var text = "";
	if (id != null && id != '' && id != 'null') {
		text = text + "<option value='" + id + "'>" + name + "</option>";
	} else {
		text = text + "<option  value=''>--请选择省份--</option>";
	}
	text = text + loadArea1("area.", "a", null);
	jQuery("#" + divId).html(text);
}
/*
 * ====根据省的编号取得城市信息======
 * pid:省id 
 * id:市ID 
 * name:市名称  
 * new_flag:!=null 预载, null重选  
 * divId:要显示在的控件id 
 * countyDiv:要清除的县控件id
 */ 
function areaB(pid, id, name, new_flag, divId, countyDiv) {
	var text = "";
	if (pid != null && pid != "" && pid != "null") {
		pid = "&id=" + pid;
		if (new_flag != null && new_flag != "" && new_flag != 'null') {
			if (id != null && id != '' && id != 'null') {
				text = text + "<option selected='selected' value='"+ id + "'>" + name + "</option>";
			} else {
				text = text+"<option selected='selected' value=''>--请选择地级市--</option>";
			}
		} else {
			text = text
					+ "<option selected='selected' value=''>--请选择地级市--</option>";
				if (countyDiv != null && countyDiv != '' && countyDiv != 'null') {
					jQuery("#" + countyDiv).html("<option selected='selected' value=''>--市、县级市、县--</option>");
				}
			}
		text = text + loadArea1("area.", "b", pid);
	} else {
		text = "<option selected='selected' value=''>--请选择地级市--</option>";
	}
	jQuery("#" + divId).html(text);
}
/*
 * ===根据城市的编号取得县的信息=====
 * pid:市id 
 * id:县ID 
 * name:县名称 
 * new_flag:[!=null预载,null重选] 
 * divId:要显示在的控件id
 */
function areaC(pid, id, name, new_flag, divId) {
	var text = "";
	if (pid != null && pid != "" && pid != "null") {
		pid = "&id=" + pid;
		if (new_flag != null && new_flag != "" && new_flag != 'null') {
			if (id != null && id != '' && id != 'null') {
				text = text + "<option selected='selected' value='"	+ id + "'>" + name + "</option>";
			} else {
				text = text	+ "<option selected='selected' value=''>--市、县级市、县--</option>";
			}
		} else {
			text = text	+ "<option selected='selected' value=''>--市、县级市、县--</option>";
		}
		text = text	+ loadArea1("area.", "c", pid);
	} else {
		text = "<option selected='selected' value=''>--市、县级市、县--</option>";
	}
	jQuery("#" + divId).html(text);
}
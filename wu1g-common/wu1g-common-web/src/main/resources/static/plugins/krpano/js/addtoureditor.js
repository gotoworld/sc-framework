// JavaScript Document
//取得krcommon对象
var krcommon = document.getElementById("krcommonSWFObject");
//动态保存设置的xml
//@param scene:场景名, type:view|hotspots
function save_xml_data(scene,type,ath,atv,linkedscene,hname,scale,depth,rotate,url,isOnClock){
	if(xml_data[scene]==null){
	    xml_data[scene]= new Object();
	}
	//设置初始角度
	if(type=="view"){
	    xml_data[scene]['view'] = {hlookat:ath,vlookat:atv};
	}
	//添加热点
	if(type=="hotspots"){
	    if(xml_data[scene]['hotspots']==null){
		    xml_data[scene]['hotspots'] = new Array();
		}
		var n_hotspot = {ath:ath,atv:atv,linkedscene:linkedscene,hname:hname};
		xml_data[scene]['hotspots'].push(n_hotspot);
	}
	//旋转热点
	if(type=='rotate'){
	    if(xml_data[scene]['hotspots']!=null){
		    for(var i=0;i<xml_data[scene]['hotspots'].length;i++){
		        if(xml_data[scene]['hotspots'][i].hname==ath){
				    xml_data[scene]['hotspots'][i].rotate = atv;
				}
			}
		}
	}
	//图片热点
	if(type=='picspots'){
		if(xml_data[scene]['picspots']==null){
		    xml_data[scene]['picspots'] = new Array();
		}
		var n_hotspot = {ath:ath,atv:atv,hname:hname,scale:scale,depth:depth,rotate:rotate,url:url,isOnclick:isOnClock};
		xml_data[scene]['picspots'].push(n_hotspot);
	}
//	console.log('js接收到数据：'+JSON.stringify(xml_data));
}
//删除热点
function del_xml_h(scene,hname,type){
	//删除图片热点
	if(type=='picspots'){
		if(xml_data[scene]['picspots']!=null){
			for(var i=0;i<xml_data[scene]['picspots'].length;i++){
				if(xml_data[scene]['picspots'][i].hname==hname){
					xml_data[scene]['picspots'].splice(i,1);
				}
			}
		}
	}
	else{
		if(xml_data[scene]['hotspots']!=null){
			for(var i=0;i<xml_data[scene]['hotspots'].length;i++){
				if(xml_data[scene]['hotspots'][i].hname==hname){
					xml_data[scene]['hotspots'].splice(i,1);
				}
			}
		}
	}
//	console.log('js接收到数据：'+JSON.stringify(xml_data));
}
//移动热点
function move_xml_h(scene,hname,ath,atv){
   if(xml_data[scene]['hotspots']!=null){
		for(var i=0;i<xml_data[scene]['hotspots'].length;i++){
			if(xml_data[scene]['hotspots'][i].hname==hname){
			    xml_data[scene]['hotspots'][i].ath = ath;
				xml_data[scene]['hotspots'][i].atv = atv;
			}
		}
	}
//	console.log('js接收到数据：'+JSON.stringify(xml_data));
}
//初始化当前scene保存的xml
function init_xml_data(scene){
	//隐藏layer相关按钮
	krcommon.call("set(layer[skin_visit_btn_container].visible,false);set(layer[skin_zan_btn_container].visible,false);set(layer[skin_co_btn_container].visible,false);");
	//取得当前scene
	var scene_name = krcommon.get("scene[get(xml.scene)].name");
//    console.log('js取得当前scene：'+scene_name);
//	console.log('js取得当前scene的xml:'+JSON.stringify(xml_data[scene_name]));
	var xml_data_n = xml_data[scene_name];
	if(typeof(xml_data_n)!='undefined' && xml_data_n!=null && xml_data_n!=''){
		//还原保存的初始角度
		if(typeof(xml_data_n.view)!='undefined' && xml_data_n.view!=null && xml_data_n.view!=''){
			var xml_data_v = xml_data_n.view;
//			console.log("js定位当前scene视角："+xml_data_v.hlookat+'/'+xml_data_v.vlookat);
			krcommon.call("set(view[get(xml.view)].hlookat,"+xml_data_v.hlookat+");set(view[get(xml.view)].vlookat,"+xml_data_v.vlookat+");");
		}
		//还原保存的热点
		var xml_data_h = xml_data_n.hotspots;
		if(typeof(xml_data_n.hotspots)!='undefined' && xml_data_n.hotspots!=null && xml_data_n.hotspots!=''){
			for(var i=0;i<xml_data_h.length;i++){
				var hname = xml_data_h[i].hname;
				var ath = xml_data_h[i].ath;
				var atv = xml_data_h[i].atv;
				var rotate = xml_data_h[i].rotate!=null ? xml_data_h[i].rotate : 0;
				var hs_n = hname.split("_");
				krcommon.call("set(hs_n,"+(hs_n[1]+1)+")");
				var linkedscene = xml_data_h[i].linkedscene;
//				console.log("js生成当前scene热点："+hname);
				krcommon.call("addhotspot("+hname+");set(hotspot["+hname+"].url,%SWFPATH%/skin/vtourskin_hotspot.png);set(hotspot["+hname+"].crop,'0|0|128|128');set(hotspot["+hname+"].ath,"+ath+");set(hotspot["+hname+"].atv,"+atv+");set(hotspot["+hname+"].rotate,"+rotate+");set(hotspot["+hname+"].scale,0.5);set(hotspot["+hname+"].zoom,false);set(hotspot["+hname+"].distorted,true);set(hotspot["+hname+"].depth,10000);set(hotspot["+hname+"].scale,6);set(hotspot["+hname+"].vr_timeout, 2000);set(hotspot["+hname+"].onclick,gotoscene("+linkedscene+"));");
			}
		}
		//还原保存的图片
		var xml_data_p = xml_data_n.picspots;
		if(typeof(xml_data_n.picspots)!='undefined' && xml_data_n.picspots!=null && xml_data_n.picspots!=''){
			for(var i=0;i<xml_data_p.length;i++){
				var hname = xml_data_p[i].hname;
				var url = xml_data_p[i].url;
				var ath = xml_data_p[i].ath;
				var atv = xml_data_p[i].atv;
				var scale = xml_data_p[i].scale;
				var depth = xml_data_p[i].depth;
				var rotate = xml_data_p[i].rotate;
//				console.log("js生成当前scene图片："+hname);
				krcommon.call("addhotspot("+hname+");set(hotspot["+hname+"].url,"+url+");set(hotspot["+hname+"].ath,"+ath+");set(hotspot["+hname+"].atv,"+atv+");set(hotspot["+hname+"].rotate,"+rotate+");set(hotspot["+hname+"].scale,"+scale+");set(hotspot["+hname+"].distorted,true);set(hotspot["+hname+"].depth,"+depth+");set(hotspot["+hname+"].onclick,remove_picspots());");
			}
		}
	}
}
//向服务器端post设置的xml
function post_xml_data(){
	var radars = getRadars();
	var xml_data_str=JSON.stringify(xml_data);
	var radars_str=JSON.stringify(radars);
//	console.log('radars_str='+radars_str);
  $.post(basePath+"/h/common02.xmlsave",{pid:pid,data:''+xml_data_str,radars:''+radars_str},function(data){
	    var data = eval('('+data+')');
		if(data.status==1){
		    alert('保存成功');
		}
		if(data.status==0){
		    alert(data.msg);
		}
	});
	//var data={pid:pid,data:xml_data,radars:radars};
	
	//console.info(JSON.stringify(data))

}

function up_pic_func(){
	var fileMaxSize=1;
	var fileExtensions='jpg,png';
	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'upload_pichots_btn', 
		multi_selection: false,
		container: document.getElementById('container'),
		flash_swf_url : basePath+'/plugins/plupload/js/Moxie.swf',
		silverlight_xap_url : basePath+'/plugins/plupload/js/Moxie.xap',
		url : basePath+'/fileUpload?dir=image',
	
		filters: {
			mime_types : [ //只允许上传图片
			{title : "Image files", extensions : ""+fileExtensions }, 
			],
			max_file_size : fileMaxSize+'mb', //最大只能上传mb的文件
			prevent_duplicates : false //不允许选取重复文件
		},
	
		init: {
			PostInit: function() {
				
			},
		
			FilesAdded: function(up, files) {
				var file= files[files.length-1];
				 uploader.start();
				return false;
			},
			
			UploadProgress: function(up, file) {
				krcommon.call("set(layer[notice].html,'正在上传图片，已完成"+file.percent+"%');set(layer[notice_container].visible,true);");
//				console.log("已上传"+file.percent+"%");
			},
	
			FileUploaded: function(up, file, info) {
				if (info.status == 200)
				{   var infoData=eval('('+info.response+')');
					krcommon.call("set(layer[notice_container].visible,false);");
					krcommon.call("add_pic_hotspot("+file.id+","+img_host+infoData.fileUrl+");");
				}
				else
				{
					alert(info.response);
				} 
			},
	
			Error: function(up, err) {
				if (err.code == -600) {
					alert("选择的文件太大了,不能超过"+fileMaxSize+"M");
				}
				else if (err.code == -601) {
					 alert("只能上传"+fileExtensions+"格式大小的图片");
				}
				else if (err.code == -602) {
					 alert("这个文件已经上传过一遍了");
				}
				else 
				{   
					alert("上传异常");
				}
			}
		}
	});	
	uploader.init();
	return uploader;
}
var chars = ['0','1','2','3','4','5','6','7','8','9'];
function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*10);
         res += chars[id];
     }
     return res;
}
//初始化上传类
up_pic_func();
//模拟触发文件按钮
function up_pic(){
	$("#upload_pichots_btn").click();
}



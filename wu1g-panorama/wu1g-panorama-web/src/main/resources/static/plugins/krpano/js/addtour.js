// JavaScript Document
//取得krpano对象
var krpano = document.getElementById("krpanoSWFObject");
var css = {     
	  display:"block"
};
//显示评论窗
function show_comment(){
		krpano.call("js(update_comm_ele(get(scene[get(xml.scene)].name),get(view.hlookat),get(view.vlookat)))");
		   $("#usercomm").attr('placeholder','拖动头像就可以发表说一说到指定位置哦！').unbind("click");
		   krpano.call(
                "addhotspot(commname);"+
				"set(hotspot[commname].url,%SWFPATH%/skin/comm-hide-icon.png);"+
				"set(hotspot[commname].ath,get(view.hlookat));"+
				"set(hotspot[commname].atv,get(view.vlookat));"+
				"set(hotspot[commname].edge,bottom);"+
				"set(hotspot[commname].zoom,false);"+	
				"set(hotspot[commname].ondown,draghotspot());"+
			    "set(hotspot[commname].onup,js(update_comm_ele(get(scene[get(xml.scene)].name),get(ath),get(atv))));"+
				"addplugin(commname_txt);"+
				"set(plugin[commname_txt].parent, 'hotspot[commname]');"+
				"set(plugin[commname_txt].url,'%SWFPATH%/plugins/textfield.swf');"+
				"set(plugin[commname_txt].align,righttop);"+
				"set(plugin[commname_txt].edge,lefttop);"+
				"set(plugin[commname_txt].x,-5);"+
				"set(plugin[commname_txt].autowidth,true);"+
				"set(plugin[commname_txt].height,30);"+
				"set(plugin[commname_txt].background,true);"+
				"set(plugin[commname_txt].backgroundcolor,0x000000);"+
				"set(plugin[commname_txt].roundedge,5);"+
				"set(plugin[commname_txt].backgroundalpha,0.8);"+
				"set(plugin[commname_txt].css,'text-align:center;color:#FFFFFF;font-size:14px;line-height:25px;padding:0 5px;font-family:microsoft yahei;');"+
				"set(plugin[commname_txt].html,拖动头像到你要评论的位置);"+
				"set(plugin[commname_txt].enabled,true);"+
				"addplugin(commname_avatar);"+
				"set(plugin[commname_avatar].url,%SWFPATH%/plugins/textfield.swf);"+
				"set(plugin[commname_avatar].parent,'hotspot[commname]');"+
				"set(plugin[commname_avatar].width,30);"+
				"set(plugin[commname_avatar].height,30);"+
				"set(plugin[commname_avatar].align,lefttop);"+
				"set(plugin[commname_avatar].edge,lefttop);"+
				"set(plugin[commname_avatar].roundedge,3);"+
				"set(plugin[commname_avatar].enabled,false);"+
				"set(plugin[commname_avatar].css,'margin:0;width:30px;height:30px;background:url("+basePath+"static/img/avatar.png) 0 0 no-repeat;background-size:30px');"+
				"if(autorotate.enabled==true,switch_xuanzhuan(););"
		);
   switch_show_comment(true);
   $(".commentBox").css(css);
   krpano.call("set(layer[skin_layer].visible,false)"); 
}


//取消评论
function cancel_comment(){
    $("#usercomm").val('');
	$("#doComm").css({cursor:"not-allowed",backgroundColor:"#aaaaaa"});
    $(".commentBox").hide();
	krpano.call("removehotspot(commname);removeplugin(commname_avatar);set(layer[skin_layer].visible,true);");
}
//更新sname,ath,atv
function update_comm_ele(s,ah,av){
//	console.log(s);
    sname = s;
	ath = ah;
	atv = av;
//	console.log('头像位置：'+s+':'+ah+','+av);
}
//取得某个scene的评论
function get_comm(){
    //取得当前scene
	var s = krpano.get("scene[get(xml.scene)].name");
    $.post(basePath+"/s/pano.getComment",{pid:pid,sceneId:s},function(data){
	    var data = eval('('+data+')');
		if(data.status==1){
			comment_list = data.list;
		    for(var i=0;i<comment_list.length;i++){
			    var commname = "userComm_"+comment_list[i].id;
				var commname_txt = commname+"_txt";
				var commname_avatar = commname+"_avatar";
				var is_visible = is_show_comment===true ? true : false;
				var head_img =basePath+"static/img/avatar.png";// comment_list[i].img
			    krpano.call(
				"addhotspot("+commname+");"+
				"set(hotspot["+commname+"].url,%SWFPATH%/skin/comm-hide-icon.png);"+
				"set(hotspot["+commname+"].ath,"+comment_list[i].ath+");"+
				"set(hotspot["+commname+"].atv,"+comment_list[i].atv+");"+
				"set(hotspot["+commname+"].edge,bottom);"+
				"set(hotspot["+commname+"].zoom,false);"+	
				"set(hotspot["+commname+"].visible,"+is_visible+");"+
				"addplugin("+commname_txt+");"+
				"set(plugin["+commname_txt+"].parent, 'hotspot["+commname+"]');"+
				"set(plugin["+commname_txt+"].url,'%SWFPATH%/plugins/textfield.swf');"+
				"set(plugin["+commname_txt+"].align,righttop);"+
				"set(plugin["+commname_txt+"].edge,lefttop);"+
				"set(plugin["+commname_txt+"].x,-5);"+
				"set(plugin["+commname_txt+"].autowidth,true);"+
				"set(plugin["+commname_txt+"].height,30);"+
				"set(plugin["+commname_txt+"].background,true);"+
				"set(plugin["+commname_txt+"].backgroundcolor,0x000000);"+
				"set(plugin["+commname_txt+"].roundedge,5);"+
				"set(plugin["+commname_txt+"].backgroundalpha,0.8);"+
				"set(plugin["+commname_txt+"].css,'text-align:center;color:#FFFFFF;font-size:14px;line-height:25px;padding:0 5px;font-family:microsoft yahei;');"+
				"set(plugin["+commname_txt+"].html,"+comment_list[i].content+");"+
				"set(plugin["+commname_txt+"].enabled,false);"+
				"addplugin("+commname_avatar+");"+
				"set(plugin["+commname_avatar+"].url,%SWFPATH%/plugins/textfield.swf);"+
				"set(plugin["+commname_avatar+"].parent,'hotspot["+commname+"]');"+
				"set(plugin["+commname_avatar+"].width,30);"+
				"set(plugin["+commname_avatar+"].height,30);"+
				"set(plugin["+commname_avatar+"].align,lefttop);"+
				"set(plugin["+commname_avatar+"].edge,lefttop);"+
				"set(plugin["+commname_avatar+"].keep,false);"+
				"set(plugin["+commname_avatar+"].roundedge,3);"+
				"set(plugin["+commname_avatar+"].css,'margin:0;width:30px;height:30px;background:url("+head_img+") 0 0 no-repeat;background-size:30px');"
				);
			}
		}
	});
}
//隐藏评论
//param status:要设置的状态
function switch_show_comment(status){
	if(status===true){
	    is_show_comment = false;	
	}
    if(is_show_comment===true){
		for(var i=0;i<comment_list.length;i++){
		  krpano.call("set(hotspot[userComm_"+comment_list[i].id+"].visible,false);set(layer[skin_co_btn_ico].url,%SWFPATH%/skin/no_comment.png);");
		}
		is_show_comment = false;
		cancel_comment();
    }
	else{
	    for(var i=0;i<comment_list.length;i++){
		  krpano.call("set(hotspot[userComm_"+comment_list[i].id+"].visible,true);set(layer[skin_co_btn_ico].url,%SWFPATH%/skin/comment.png);");
		}
		is_show_comment = true;	
	}
}
//更新点赞数
function doZan(){
	krpano.call("set(layer[skin_zan_btn_ico].enabled,false)");
	if(getcookie('thumbsUpNum'+pid)){
		alert('12小时内不能重复点赞');
	}else{
	    $.post(basePath+"/s/pano.thumbsUpNum",{pid:pid},function(data){
		    var data = eval('('+data+')');
		    if(data.status==1){
			    krpano.call("set(layer[skin_zan_btn_tit].html,'赞("+data.count+")');");
			    setcookie('thumbsUpNum'+pid,"1");
			}
		});
	}
}

//更新点赞数，浏览量
function initStatis(){
    krpano.call("set(layer[skin_zan_btn_tit].html,赞("+like_num+"))");
	krpano.call("set(layer[skin_visit_num].html,人气："+visit_num+")");
	if(allow_comment==0){
	    krpano.call("set(layer[skin_co_btn_container].visible,false);");
	}
	if (bgm!="") {
		krpano.call("set(layer[skin_bgm_btn_container].visible,true)");
	};	
	//krpano.call("set(layer[designer_name].html,"+company_name+designer_name+")");
	//krpano.call("set(layer[designer_btn_ico].url,"+company_logo+")");
//	console.log(company_logo);
}
//监听
$(document).ready(function(){  
    $("#usercomm").keyup(function(){  
	    var content = $.trim($("#usercomm").val());
        var curLength = content.length;   
		krpano.call("set(plugin[commname_txt].html,"+content+");");
        if(curLength>0){
		    $("#doComm").removeAttr('style');
		}
		else{
			krpano.call("set(plugin[commname_txt].html,拖动头像到你要评论的位置);");
		    $("#doComm").css({cursor:"not-allowed",backgroundColor:"#aaaaaa"});
		} 
    });
	$("#doComm").click(function(){
	    if($.trim($("#usercomm").val()).length<1){
		    return false;
		}
	    $.post(basePath+"/s/pano.addComment",{pid:pid,sceneId:sname,ath:ath,atv:atv,content:$("#usercomm").val()},function(data){
	    	var data = eval('('+data+')');
		    if(data.status==1){
			    //todo
				cancel_comment();
				get_comm();
			}else{
			    alert('添加评论失败！');
			}
		});
	});
	if(getcookie('pvNum'+pid)){
		//
	}else{
		$.post(basePath+"/s/pano.pvNum",{pid:pid},function(data){
		    var data = eval('('+data+')');
		    if(data.status==1){
			    krpano.call("set(layer[skin_visit_num].html,赞("+data.count+"))");
			    setcookie('pvNum'+pid,"1");
			}
		});
	}
})  
function setcookie(name,value){  
    var Days = 0.5;  
    var exp  = new Date();  
    exp.setTime(exp.getTime() + Days*24*60*60*1000);  
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();  
}  
function getcookie(name){  
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
    if(arr != null){  
        return (arr[2]);  
    }else{  
        return "";  
    }  
}
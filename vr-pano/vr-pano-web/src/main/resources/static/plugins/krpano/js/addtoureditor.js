// JavaScript Document
//取得krpano对象
var krpano = document.getElementById("krpanoSWFObject");
var htypeArr = ['hotspots', 'picspots', 'linkSpots', 'richSpots', 'soundSpots', 'videoSpots'];
//动态保存设置的xml
//@param scene:场景名, type:view|hotspots
//
// function save_xml_data_0(scene,type,ath,atv,linkedscene,hname,scale,depth,rotate,url,isOnClock){
//     save_xml_data(scene,type,ath,atv,linkedscene,hname,scale,depth,rotate,url,isOnClock,'','','');
// }
function save_xml_data_2(scene, type, ath, atv, hname, scale, depth, rotate, url, title) {
    save_xml_data(scene, type, ath, atv, null, hname, scale, depth, rotate, url, null, title, '', '');
}
// function save_xml_data_3(scene,type,ath,atv,linkedscene,hname,scale,depth,rotate,url,isOnClock,title,detailInfo){
//     save_xml_data(scene,type,ath,atv,linkedscene,hname,scale,depth,rotate,url,isOnClock,title,detailInfo,'');
// }
function save_xml_data(scene, type, ath, atv, linkedscene, hname, scale, depth, rotate, url, isOnClock, title, detailInfo, hotspotImg) {
    if (xml_data[scene] == null) {
        xml_data[scene] = new Object();
    }
    switch (type) {
        case "view"://设置初始角度
            xml_data[scene]['view'] = {hlookat: ath, vlookat: atv};
            break;
        case 'rotate' : //旋转热点
            for (var i = 0; i < htypeArr.length; i++) {
                if (xml_data[scene][htypeArr[i]] != null) {
                    for (var i = 0; i < xml_data[scene][htypeArr[i]].length; i++) {
                        if (xml_data[scene][htypeArr[i]][i].hname == hname) {
                            xml_data[scene][htypeArr[i]][i].rotate = atv;
                        }
                    }
                }
            }
            break;
        case "hotspots" : //热点-0场景切换
        case 'picspots' :   //热点-1装饰图片
        case 'linkSpots' :  //热点-2外部链接
        case 'richSpots' :  //热点-3图文介绍 | 内部弹窗页
        case 'soundSpots'://热点-4语音热点
        case 'videoSpots':  //热点-5视频热点
            if (xml_data[scene][type] == null) {
                xml_data[scene][type] = new Array();
            }
            var n_hotspot = {
                ath: ath,
                atv: atv,
                linkedscene: linkedscene,
                hname: hname,
                scale: scale,
                depth: depth,
                rotate: rotate,
                url: url,
                isOnclick: isOnClock,
                title: title,
                detailInfo: detailInfo,
                hotspotImg: hotspotImg
            };
            xml_data[scene][type].push(n_hotspot);
            break;
    }
    console.log('js接收到数据：' + JSON.stringify(xml_data));
}
//删除热点
function del_xml_h(scene, hname) {
    for (var i = 0; i < htypeArr.length; i++) {
        del_xml_h_a(scene, hname, htypeArr[i]);
    }
}
function del_xml_h_a(scene, hname, type) {
    if (xml_data[scene][type] != null) {
        for (var i = 0; i < xml_data[scene][type].length; i++) {
            if (xml_data[scene][type][i].hname == hname) {
                xml_data[scene][type].splice(i, 1);
            }
        }
    }
//	console.log('js接收到数据：'+JSON.stringify(xml_data));
}
//移动热点
function move_xml_h(scene, hname, ath, atv) {
    for (var i = 0; i < htypeArr.length; i++) {
        move_xml_h_a(scene, hname, htypeArr[i], ath, atv);
    }
}
function move_xml_h_a(scene, hname, type, ath, atv) {
    if (xml_data[scene][type] != null) {
        for (var i = 0; i < xml_data[scene][type].length; i++) {
            if (xml_data[scene][type][i].hname == hname) {
                xml_data[scene][type][i].ath = ath;
                xml_data[scene][type][i].atv = atv;
            }
        }
    }
    //console.log('js接收到数据：' + JSON.stringify(xml_data));
}
//初始化当前scene保存的xml
function init_xml_data(scene) {
    //隐藏layer相关按钮
    krpano.call("set(layer[skin_visit_btn_container].visible,false);set(layer[skin_zan_btn_container].visible,false);set(layer[skin_co_btn_container].visible,false);");
    //取得当前scene
    var scene_name = krpano.get("scene[get(xml.scene)].name");
//    console.log('js取得当前scene：'+scene_name);
//	console.log('js取得当前scene的xml:'+JSON.stringify(xml_data[scene_name]));
    var xml_data_n = xml_data[scene_name];
    if (typeof(xml_data_n) != 'undefined' && xml_data_n != null && xml_data_n != '') {
        //还原保存的初始角度
        if (typeof(xml_data_n.view) != 'undefined' && xml_data_n.view != null && xml_data_n.view != '') {
            var xml_data_v = xml_data_n.view;
//			console.log("js定位当前scene视角："+xml_data_v.hlookat+'/'+xml_data_v.vlookat);
            krpano.call("set(view[get(xml.view)].hlookat," + xml_data_v.hlookat + ");set(view[get(xml.view)].vlookat," + xml_data_v.vlookat + ");");
        }
        var xml_data_h = [];
        xml_data_h = xml_data_n.hotspots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                hotdata.rotate = hotdata.rotate != null ? hotdata.rotate : 0;
                var hs_n = hotdata.hname.split("_");
                krpano.call("set(hs_n," + (hs_n[1] + 1) + ")");
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].url,%SWFPATH%/skin/vtourskin_hotspot.png);set(hotspot[" + hotdata.hname + "].crop,'0|0|128|128');set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale,0.5);set(hotspot[" + hotdata.hname + "].zoom,false);set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth,10000);set(hotspot[" + hotdata.hname + "].scale,6);set(hotspot[" + hotdata.hname + "].vr_timeout, 2000);set(hotspot[" + hotdata.hname + "].onclick,gotoscene(" + hotdata.linkedscene + "));");
            }
        }
        xml_data_h = xml_data_n.picspots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].url," + hotdata.url + ");set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale," + hotdata.scale + ");set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth," + hotdata.depth + ");set(hotspot[" + hotdata.hname + "].onclick,remove_picspots());");
            }
        }
        xml_data_h = xml_data_n.linkSpots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].style,showtxt_hotspotstyle);set(hotspot[" + hotdata.hname + "].tooltip," + hotdata.title + ");set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale," + hotdata.scale + ");set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth," + hotdata.depth + ");set(hotspot[" + hotdata.hname + "].onclick,remove_picspots());");
            }
        }
        xml_data_h = xml_data_n.richSpots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].url," + hotdata.url + ");set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale," + hotdata.scale + ");set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth," + hotdata.depth + ");set(hotspot[" + hotdata.hname + "].onclick,remove_picspots());");
            }
        }
        xml_data_h = xml_data_n.soundSpots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].url," + hotdata.url + ");set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale," + hotdata.scale + ");set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth," + hotdata.depth + ");set(hotspot[" + hotdata.hname + "].onclick,remove_picspots());");
            }
        }
        xml_data_h = xml_data_n.videoSpots;
        if (typeof(xml_data_h) != 'undefined' && xml_data_h != null && xml_data_h != '') {
            for (var i = 0; i < xml_data_h.length; i++) {
                var hotdata = xml_data_h[i];
                krpano.call("addhotspot(" + hotdata.hname + ");set(hotspot[" + hotdata.hname + "].url," + hotdata.url + ");set(hotspot[" + hotdata.hname + "].ath," + hotdata.ath + ");set(hotspot[" + hotdata.hname + "].atv," + hotdata.atv + ");set(hotspot[" + hotdata.hname + "].rotate," + hotdata.rotate + ");set(hotspot[" + hotdata.hname + "].scale," + hotdata.scale + ");set(hotspot[" + hotdata.hname + "].distorted,true);set(hotspot[" + hotdata.hname + "].depth," + hotdata.depth + ");set(hotspot[" + hotdata.hname + "].onclick,remove_picspots());");
            }
        }
    }
}
//向服务器端post设置的xml
function post_xml_data() {
    var radars = getRadars();
    var xml_data_str = JSON.stringify(xml_data);
    var radars_str = JSON.stringify(radars);
//	console.log('radars_str='+radars_str);
    $.post(basePath + "h/pano/proj/xmlsave", {
        pid: pid,
        data: '' + xml_data_str,
        radars: '' + radars_str
    }, function (data) {
        var data = eval('(' + data + ')');
        if (data.status == 1) {
            alert('保存成功');
        }
        if (data.status == 0) {
            alert(data.msg);
        }
    });
    //var data={pid:pid,data:xml_data,radars:radars};

    //console.info(JSON.stringify(data))

}

function up_pic_func() {
    var fileMaxSize = 1;
    var fileExtensions = 'jpg,png';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upload_pichots_btn',
        multi_selection: false,
        container: document.getElementById('container'),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: basePath + 'fileUpload?dir=image',

        filters: {
            mime_types: [ //只允许上传图片
                {title: "Image files", extensions: "" + fileExtensions},
            ],
            max_file_size: fileMaxSize + 'mb', //最大只能上传mb的文件
            prevent_duplicates: false //不允许选取重复文件
        },

        init: {
            PostInit: function () {

            },

            FilesAdded: function (up, files) {
                var file = files[files.length - 1];
                uploader.start();
                return false;
            },

            UploadProgress: function (up, file) {
                krpano.call("set(layer[notice].html,'正在上传图片，已完成" + file.percent + "%');set(layer[notice_container].visible,true);");
//				console.log("已上传"+file.percent+"%");
            },

            FileUploaded: function (up, file, info) {
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    krpano.call("set(layer[notice_container].visible,false);");
                    krpano.call("add_pic_hotspot(" + file.id + "," + img_host + infoData.fileUrl + ");");
                }
                else {
                    alert(info.response);
                }
            },

            Error: function (up, err) {
                if (err.code == -600) {
                    alert("选择的文件太大了,不能超过" + fileMaxSize + "M");
                }
                else if (err.code == -601) {
                    alert("只能上传" + fileExtensions + "格式大小的图片");
                }
                else if (err.code == -602) {
                    alert("这个文件已经上传过一遍了");
                }
                else {
                    alert("上传异常");
                }
            }
        }
    });
    uploader.init();
    return uploader;
}
var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A','b','c','d','e','f','g','h','i','J','k','L','m'];
function generateMixed(n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 18);
        var x=chars[id];
        if(x)
        res += x;
    }
    return res;
}
//初始化上传类
up_pic_func();
//模拟触发文件按钮
function up_pic() {
    $("#upload_pichots_btn").click();
}



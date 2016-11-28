
function calcAngle(ev){
    var containerOffset = $('.sandtable-window-img').offset();
    var offsetX = containerOffset['left'];
    var offsetY = containerOffset['top'];
    //var offsetX = 0;
    //var offsetY = 0;
    var mouseX = ev.pageX - offsetX;//计算出鼠标相对于画布顶点的位置,无pageX时用clientY + body.scrollTop - body.clientTop代替,可视区域y+body滚动条所走的距离-body的border-top,不用offsetX等属性的原因在于，鼠标会移出画布
    var mouseY = ev.pageY - offsetY;
    var cx = $(dragElm).parents(".radar-control").position().left + 30;
    var cy = $(dragElm).parents(".radar-control").position().top + 30;
    var ox = mouseX - cx;//cx,cy为圆心
    var oy = mouseY - cy;
    var to = Math.abs( ox/oy );
    var angle = Math.atan( to )/( 2 * Math.PI ) * 360;//鼠标相对于旋转中心的角度
    if( ox < 0 && oy < 0)//相对在左上角，第四象限，js中坐标系是从左上角开始的，这里的象限是正常坐标系
    {
        angle = -angle;
    }else if( ox < 0 && oy > 0)//左下角,3象限
    {
        angle = -( 180 - angle )
    }else if( ox > 0 && oy < 0)//右上角，1象限
    {
        angle = angle;
    }else if( ox > 0 && oy > 0)//右下角，2象限
    {
        angle = 180 - angle;
    }
    //console.log(angle);
    $(dragElm).parent(".radar-circle").css('rotate', angle);
}

var dragElm;
function dragStart(event){
    dragElm = event.target;
    $(document).mousemove(calcAngle);
    $(document).mouseup(dragEnd);
}
function showRadar(){
    if (radar_img=="") {
        alert("请先在编辑项目中上传导览图");
    }else
        $(".radar-container").slideDown() ;
}
function dragEnd(e){
    //记录雷达初始角度偏移量
    var rotate = $(dragElm).parent(".radar-circle").css('rotate');
    $(dragElm).parent(".radar-circle").data('rotate',rotate);
    // var krcommon = document.getElementById('commonSettingObject');
    // var hlookat = krcommon.get('view.hlookat');
    var hlookat = 10;
    $(dragElm).parent(".radar-circle").data('hlookat',hlookat);
    var sceneTitle = $(dragElm).parent(".radar-circle").prev().data('original-title');
    // putSandTableData(krcommon.get('xml.scene'),sceneTitle,rotate,hlookat);
    $(document).unbind('mousemove',calcAngle);
    $(document).unbind('mouseup',dragEnd);
}
 function moveRadar(ev){
        var containerOffset = $('.sandtable-window-img').offset();
        var offsetX = containerOffset['left'];
        var offsetY = containerOffset['top'];
        var mouseX = ev.pageX - offsetX;//计算出鼠标相对于画布顶点的位置,无pageX时用clientY + body.scrollTop - body.clientTop代替,可视区域y+body滚动条所走的距离-body的border-top,不用offsetX等属性的原因在于，鼠标会移出画布
        var mouseY = ev.pageY - offsetY;
        var containerWidth = $('.sandtable-window-img').outerWidth();
        var containerHeight = $('.sandtable-window-img').outerHeight();
        
        var radar_control_left=mouseX-30;
        var radar_control_top=mouseY-30;
        
        if(mouseX > 10 && mouseX < (containerWidth-10)){
             radar_control_left=mouseX-30;
        }else if(mouseX < 10){
            radar_control_left=-20;
        }else if(mouseX > (containerWidth-10)){
            radar_control_left=containerWidth-10-30;
        }
        if(mouseY > 10 && mouseY < (containerHeight-10)){
        	radar_control_top=mouseY-30;
        }else if(mouseY < 10){
            radar_control_top=-20;
        }else if(mouseY > (containerHeight-10)){
            radar_control_top=containerHeight-10-30;
        }
        
        $(dragElm).parents('.radar-control').css('top',radar_control_top+'px');
        $(dragElm).parents('.radar-control').css('left',radar_control_left+'px');
        $(dragElm).parents('.radar-control').attr('x',radar_control_top);
        $(dragElm).parents('.radar-control').attr('y',radar_control_left);
    }
$(document).on('mousedown','.radar-center-point',function(event){
        dragElm = event.target;
        $(document).mousemove(moveRadar);
        $(document).mouseup(moveRadarEnd);
    });

    

function moveRadarEnd(e){
    // var leftPx = $(dragElm).parents('.radar-control').position().left;
    // var topPx = $(dragElm).parents('.radar-control').position().top;
    // var krcommon = document.getElementById('commonSettingObject');
    // putSandTableData(krcommon.get('xml.scene'),null,null,null,topPx,leftPx);
    $(document).unbind('mousemove',moveRadar);
    $(document).unbind('mouseup',moveRadarEnd);
}

function putSandTableData(sceneName,sceneTitle,rotate,hlookat,topPx,leftPx){
    var sandData = $('#sandImg .sand-img-clicked').data('sand-table-data');
    if(!sandData){
        sandData = {};
        sandData[sceneName] = {};
        sandData[sceneName].rotate = 0;
        sandData[sceneName].hlookat = 0;
        sandData[sceneName].top = '40%';
        sandData[sceneName].left = '40%';
        sandData[sceneName].krpTop = '48%';
        sandData[sceneName].krpLeft = '48%';
    }else{
        if(!sandData[sceneName]){
            sandData[sceneName] = {};
            sandData[sceneName].rotate = 0;
            sandData[sceneName].hlookat = 0;
            sandData[sceneName].top = '40%';
            sandData[sceneName].left = '40%';
            sandData[sceneName].krpTop = '48%';
            sandData[sceneName].krpLeft = '48%';
        }
    }

    if(rotate)sandData[sceneName].rotate = rotate;
    if(hlookat)sandData[sceneName].hlookat = hlookat;
    if(sceneTitle)sandData[sceneName].sceneTitle = sceneTitle;
    if(topPx){
        var imgHeight = $('.sandtable-img-margin').height();
        var top = percentNum(topPx,imgHeight);
        var krpTop = percentNum(topPx+20,imgHeight);
        sandData[sceneName].top = top;
        sandData[sceneName].krpTop = krpTop;
    }
    if(leftPx){
        var imgWidth = $('.sandtable-img-margin').width();
        var left = percentNum(leftPx,imgWidth);
        var krpLeft = percentNum(leftPx+20,imgWidth);
        sandData[sceneName].left = left;
        sandData[sceneName].krpLeft = krpLeft;
    }
    $('#sandImg .sand-img-clicked').data('sand-table-data',sandData);
}
function getRadars(){
    var radars ={};
    $(".radar-control").each(function(){
        var scene = $(this).children(".radar-control-img").data("original-title");
        radars[scene]={};
        radars[scene].rotate = $(this).children(".radar-circle").css('rotate');
        radars[scene].x = $(this).attr('x');
        radars[scene].y = $(this).attr('y');
    });
    return radars;
}
function appendRadarCircle(sceneName){
    var htmlStr = '<div class="radar-control" style="top:102px;left:102px">'+
        '<img class="radar-control-img" src="'+commonPath+'images/radar-out.png" onclick="triggerCircle(this)"  data-original-title="'+sceneName+'">'+
        '<div class="radar-circle"  style="transform: rotate(0deg);">'+
        '<div class="radar-point" onmousedown="dragStart(event)" ></div>'+
        '<div class="radar-center-point"></div>'+
        '</div>'+
        '</div>';
    hiddenCircle();
    $('.radar-container .sandtable-img-margin').append(htmlStr);
   
}


function hiddenCircle(){
    $(".radar-control").each(function(){
        var $circle = $(this).children(".radar-circle");
        if ($circle.css("display")=='block') {
           $circle.css("display","none");
        }
    });
}

function triggerCircle(obj){

    var sceneName = $(obj).data('original-title');
    if(sceneName != krcommon.get('xml.scene')){
        krcommon.call('loadscene('+sceneName+', null, MERGE);');
    }
    hiddenCircle();
    $(obj).siblings(".radar-circle").css("display","block");
    $(".radar-del-btn").data("original-title",sceneName);
}

function delRadar(sceneName){
    $(".radar-control-img[data-original-title='"+sceneName+"']").parent().remove();
}
$(".radar-del-btn").click(function(){
    delRadar($(this).data("original-title"));
    $(this).data("original-title","");
})
$(".radar-add-btn").click(function(){
    $(".radar-scenes-container").show();
});

$(".radar-scenes-btn").click(function(){
    $(".radar-scenes-container").hide();
});

$(".scenes-img").click(function(){
    var sceneName = $(this).data("scene-name");
    delRadar(sceneName);
    appendRadarCircle(sceneName);
     $(".radar-del-btn").data("original-title",sceneName);
    $(".radar-scenes-container").hide();
    if(sceneName != krcommon.get('xml.scene')){
        krcommon.call('loadscene('+sceneName+', null, MERGE);');
    }
});

$('.radar-container-close').click(function(){
	$('.radar-container').hide();
});

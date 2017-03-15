// guid
function guid() {
    var S4 = function() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };
    return 'uuid'+(S4() + S4() +  S4() +  S4() +  S4() +  S4()
        + S4() + S4());
}

//是否被选中验证有选中的return true,否return false 
function checkInfo(name,type){
    var falg = 0;
    $("input[name="+name+"]:"+type).each(function(){
        if($(this).attr("checked")){
            falg +=1;
            return false;
        }
    });
    if(falg>0){
        return true;
    }
    return false;
}
function autoOnClick(id){
    if(document.all) {
        document.getElementById(id).click();
    }
    // 其它浏览器
    else {
        var e = document.createEvent("MouseEvents");
        e.initEvent("click", true, true);
        document.getElementById(id).dispatchEvent(e);
    }
}
function submitForm(formId, divId) {
    $("#" + formId).ajaxSubmit({
        type:"post",  //提交方式
        //dataType:"json", //数据类型
        url:$("#" + formId).attr('data-action'), //请求url
        success:function(data) {
            $('#' + divId).html(data);
        },error: function(e) {
            alert(JSON.stringify(e));
        }
    });
}

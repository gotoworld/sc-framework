$(document).ajaxStart(function () {
    layer.load();
    setTimeout(function () {
        layer.closeAll('loading');
    }, 5000);
});
$(document).ajaxStop(function () {
    setTimeout(function () {
        layer.closeAll('loading');
    }, 300);
});

function openMyBoxLayer(mytitle, myurl) {
    if (mytitle != 'a') {
        var pageNumA = 1;
        if ($('#pageNumA').val()) {
            pageNumA = $('#pageNumA').val();
        }
        pageNumA = parseInt(pageNumA);
        if (!!myurl) {
            if (myurl.indexOf('?') != -1) {
                myurl += '&pageNum=' + pageNumA
            } else {
                myurl += '?pageNum=' + pageNumA
            }
        }
    }
    if (mytitle == 'a') {
        mytitle = '信息新增';
    }
    if (mytitle == 'e') {
        mytitle = '信息修改';
    }
    if (mytitle == 'i') {
        mytitle = '信息查看';
    }
    layer.open({
        type: 2,
        title: [mytitle,
            'background:#eee; height:40px; color:#333; border:none;' //自定义标题样式
        ],
        border: [0],
        area: ['70%', '95%'],
        content: myurl
    });
    $('body').css("overflow", "hidden");
    $('#bg').bind("touchmove mousewheel", function (e) {
//		console.info('--------');
        e.preventDefault();
    });
    $('.xubox_close').click(function () {
        closeMyBoxLayer();
    });
}
function closeMyBoxLayer() {
    $('body', parent.document).css("overflow", "auto");
    $('body').css("overflow", "auto");
    try {
        //获取当前窗口索引
        var index = parent.layer.getFrameIndex(window.name);
        //关闭iframe
        parent.layer.close(index);
    } catch (e) {
    }
}
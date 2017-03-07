function upload_single_pic(btn, divId) {
    var fileMaxSize = 1;
    var fileExtensions = 'png';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: '' + btn,
        multi_selection: false,
        container: document.getElementById('' + divId),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: basePath + 'fileUpload?dir=image',

        filters: {
            mime_types: [ //只允许上传图片
                {title: "image files", extensions: fileExtensions},
            ],
            max_file_size: fileMaxSize + 'mb', //最大只能上传mb的文件
            prevent_duplicates: true //不允许选取重复文件
        },

        init: {
            PostInit: function () {
            },
            FilesAdded: function (up, files) {
                var file = files[files.length - 1];
                $("#" + divId).append('<div id="' + file.id + '" class="img-grid-2"><b></b>'
                    + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                    + '</div>');
                uploader.start();
                return false;
            },
            BeforeUpload: function (up, file) {
                $("#" + btn).css('pointer-events', 'none');
            },
            //上传进度
            UploadProgress: function (up, file) {
                var d = document.getElementById(file.id);
                d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                var prog = d.getElementsByTagName('div')[0];
                var progBar = prog.getElementsByTagName('div')[0]
                progBar.style.width = 2 * file.percent + 'px';
                progBar.setAttribute('aria-valuenow', file.percent);
            },
            //文件上传完毕
            FileUploaded: function (up, file, info) {
                $("#" + divId + " .img-grid-2").remove();
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    if (infoData.code != 0) {
                        $("#" + divId).append(infoData.message);
                    } else {
                        var html = '<div id="' + file.id + '" class="img-grid-2">';
                        html += '<input name="mapSrc" value="' + infoData.fileUrl + '" type="hidden">';
                        html += '<img src="' + infoData.fileUrl + '" style="max-width:264px;max-height:264px">';
                        html += '</div>';
                        $("#" + divId).append(html);
                    }
                } else {
                    $("#" + divId).append(info.response);
                }
                $("#" + btn).css('pointer-events', '');
            },

            Error: function (up, err) {
                if (err.code == -600) {
                    layer.msg("选择的文件太大了,不能超过" + fileMaxSize + "M");
                }
                else if (err.code == -601) {
                    layer.msg("只能上传" + fileExtensions + "格式大小的" + fileMaxSize);
                }
                else if (err.code == -602) {
                    layer.msg("这个文件已经上传过一遍了");
                }
                else {
                    layer.msg("上传异常");
                }
            }
        }
    });
    uploader.init();
    return uploader;
}
function upload_single_excel(btn, divId, _fileExt,batchUrl) {
    var fileMaxSize = 50;
    var fileExtensions = _fileExt || 'xls';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: '' + btn,
        multi_selection: false,
        container: document.getElementById('' + divId),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: basePath + '/file/fileUpload?dir=file',

        filters: {
            mime_types: [ //只允许上传
                {title: "excel files", extensions: fileExtensions},
            ],
            max_file_size: fileMaxSize + 'mb', //最大只能上传mb的文件
            prevent_duplicates: false //不允许选取重复文件
        },

        init: {
            PostInit: function () {
            },
            FilesAdded: function (up, files) {
                var file = files[files.length - 1];
                $("#" + divId).append('<div id="' + file.id + '" class="file-grid-2"><b></b>'
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
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    if (infoData.code != 0) {
                        $("#" + divId).append(infoData.message);
                    } else {
                        var html = '<br>'+util.dateTimeFormat(new Date().getTime())+' 上传完成,正在处理!请稍等..........  <br>' + infoData.fileUrl + ' ';
                        $("#" + divId).append(html);

                        $.post(batchUrl, {"fileUrl":infoData.fileUrl}, function (result) {
                            html = '<br>'+util.dateTimeFormat(new Date().getTime())+' ============处理完成!================ <br>'
                            html+=result.message;
                            $("#" + divId).append(html);
                        }, 'json');
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
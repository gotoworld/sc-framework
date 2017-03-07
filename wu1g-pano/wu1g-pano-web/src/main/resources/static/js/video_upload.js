function video_upload(btn, divId) {
    var fileMaxSize = 4096;
    var fileExtensions = 'mp4';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: '' + btn,
        multi_selection: false,
        container: document.getElementById('' + divId),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: basePath + 'fileUpload?dir=media',

        filters: {
            mime_types: [ //只允许上传图片
                {title: "Video files", extensions: "" + fileExtensions},
            ],
            max_file_size: fileMaxSize + 'mb', //

            prevent_duplicates: true //不允许选取重复文件
        },

        init: {
            PostInit: function () {
            },
            FilesAdded: function (up, files) {
                var file = files[files.length - 1];
                $("#" + divId).append('<div id="' + file.id + '" class="img-grid"><b></b>'
                    + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                    + '<a href="javascript:;" onclick="javascript:del_scene_btn(this,' + file.id + ');" class="ico_del"></a>'
                    + '</div>');
                uploader.start();
                return false;
            },


            BeforeUpload: function (up, file) {
            },

            UploadProgress: function (up, file) {
                var d = document.getElementById(file.id);
                d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                var prog = d.getElementsByTagName('div')[0];
                var progBar = prog.getElementsByTagName('div')[0]
                progBar.style.width = 2 * file.percent + 'px';
                progBar.setAttribute('aria-valuenow', file.percent);
            },

            FileUploaded: function (up, file, info) {
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    if (infoData.code != 0) {
                        document.getElementById(file.id).innerHTML = infoData.message;
                    } else {
                        var html = '';
                        html += '<input type="hidden" name="scene_id" value="' + file.id + '" />';
                        html += '<input type="hidden" name="' + file.id + '_scene_src" value="' + infoData.fileUrl + '" />';
                        html += '<img src="' + basePath + 'img/vuped.png" >';
                        html += '<input type="text" name="' + file.id + '_scene_tit" placeholder="请填写清晰度,比如:标清" required="" aria-required="true"   maxlength="5" aria-invalid="true"/>';
                        html += '<a href="javascript:;" onclick="move_scene(this,\'left\');" class="ico_left" ></a>';
                        html += '<a href="javascript:;" onclick="javascript:del_scene_btn(this,\'' + file.id + '\');" class="ico_del"></a>';
                        html += '<a href="javascript:;" onclick="move_scene(this,\'right\');" class="ico_right" /></a>';
                        document.getElementById(file.id).innerHTML = html;
                    }
                } else {
                    document.getElementById(file.id).innerHTML = info.response;
                }
                //--
                formValidate();
            },

            Error: function (up, err) {
                if (err.code == -600) {
                    layer.msg("测试服务最大只能上传" + fileMaxSize + "M的视频，请裁剪视频后再测试");
                }
                else if (err.code == -601) {
                    layer.msg("只能上传" + fileExtensions + "格式");
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
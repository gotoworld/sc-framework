function upload_scene(btn, divId, projId) {
    var fileMaxSize = 1096;
    var fileExtensions = 'jpg,jpeg,tif,tiff,btf,tf8,bigtiff,psd,psb,kro';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: '' + btn,
        multi_selection: false,
        container: document.getElementById('' + divId),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: basePath + 'fileUpload?dir=image&projId=' + projId,
        multipart: true,
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
                $("#" + divId).append('<div id="' + file.id + '" class="img-grid"><b></b>'
                    + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                    + '<a href="javascript:;" onclick="javascript:del_scene_btn(this,' + file.id + ');" class="ico_del"></a>'
                    + '</div>');
                uploader.start();
                return false;
            },
            BeforeUpload: function (up, file) {
                $("#" + btn).css('pointer-events', 'none');
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
                //console.info(info);
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    if (infoData.code != 0) {
                        document.getElementById(file.id).innerHTML = infoData.message;
                    } else {
                        var s_key = generateMixed(6);
                        var html = '';
                        html += '<input type="hidden" name="scene_id" value="' + file.id + '" />';
                        html += '<input type="hidden" name="' + file.id + '_scene_src" value="' + infoData.fileUrl + '" />';
                        html += '<input type="hidden" name="' + file.id + '_scene_key" value="' + s_key + '" />';
                        html += '<img src="' + (infoData.defaultBigPicUrl?infoData.defaultBigPicUrl:infoData.fileUrl) + '" >';
                        html += '<input type="text" name="' + file.id + '_scene_tit" placeholder="请填写场景名称" required="" aria-required="true"   maxlength="20" aria-invalid="true"/>';
                        html += '<a href="javascript:;" onclick="move_scene(this,\'left\');" class="ico_left" ></a>';
                        html += '<a href="javascript:;" onclick="javascript:del_scene_btn(this,\'' + file.id + '\');" class="ico_del"></a>';
                        html += '<a href="javascript:;" onclick="move_scene(this,\'right\');" class="ico_right" /></a>';
                        document.getElementById(file.id).innerHTML = html;

                        $('#makePanoFlag').attr('checked', 'checked');
                    }
                }
                else {
                    document.getElementById(file.id).innerHTML = info.response;
                }
                $("#" + btn).css('pointer-events', '');
                //--
                formValidate();
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
var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
function generateMixed(n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 9);
        res += chars[id];
    }
    return res;
}
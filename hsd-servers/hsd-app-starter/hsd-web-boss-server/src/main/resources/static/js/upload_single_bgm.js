function updte_single_bgm(btn, divId, _fileExt, name) {
    var fileMaxSize = 10;
    var fileExtensions = _fileExt || 'mp3';
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: '' + btn,
        multi_selection: false,
        container: document.getElementById('' + divId),
        flash_swf_url: basePath + 'plugins/plupload/js/Moxie.swf',
        silverlight_xap_url: basePath + 'plugins/plupload/js/Moxie.xap',
        url: uploadPath + '/file/fileUpload?dir=audio',

        filters: {
            mime_types: [ //只允许上传图片
                {title: "Music files", extensions: "" + fileExtensions},
            ],
            max_file_size: fileMaxSize + 'mb', //最大只能上传mb的文件
            prevent_duplicates: false //不允许选取重复文件
        },

        init: {
            PostInit: function () {
            },
            FilesAdded: function (up, files) {
                var file = files[files.length - 1];
                $("#" + divId).append('<div id="' + file.id + '" class="music-upload"><b></b>'
                    + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                    + '</div>');
                uploader.start();
                return false;
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
                $("#" + divId + " .music-upload").remove();
                if (info.status == 200) {
                    var infoData = eval('(' + info.response + ')');
                    var html = '<div id="' + file.id + '" class="music-upload">';
                    html += '<input name="' + name + '" value="' + infoData.fileUrl + '" type="hidden">';
                    html += '<audio controls>';
                    html += '<source src="' + infoData.fileUrl + '" type="audio/mp3">';
                    html += '您的浏览器不支持在线播放';
                    html += '</audio>';
                    html += '</div>';
                    $("#" + divId).append(html);

                    plyr.setup();
                }
                else {
                    $("#" + divId).append(info.response);
                }
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
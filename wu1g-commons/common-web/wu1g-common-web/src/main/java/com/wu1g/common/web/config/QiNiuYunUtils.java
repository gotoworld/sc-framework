package com.wu1g.common.web.config;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * Created by lixuejian on 2016/9/18.
 */
public class QiNiuYunUtils {
    // 设置好账号的ACCESS_KEY和SECRET_KEY
    private final static String ACCESS_KEY = "6bN0m2sxJU5dfG1s-7hlf9vqBBxLcUXinrDsDf37";
    private final static String SECRET_KEY = "yR6vEJyxGeyHq18T9Tc6f3bmCHalDUe68frPj0Ct";
    // 要上传的空间
    private final static String BUCKET_NAME = "wu1g";
    private static Auth auth = null;
    private static UploadManager uploadManager = null;

    static {
        // 密钥配置
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        // 创建上传对象
        uploadManager = new UploadManager();
    }

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }
}

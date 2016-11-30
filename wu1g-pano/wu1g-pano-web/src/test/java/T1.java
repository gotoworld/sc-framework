import com.alibaba.fastjson.JSON;
import com.wu1g.framework.Response;

import java.nio.file.Path;

/**
 * Created by Administrator on 2016/11/30.
 */
public class T1 {
    public static void main(String[] args) {
//        System.out.println(getRealPath("classpath*:template/1/2/admin/index.html","/include/meta.html"));
        System.out.println(JSON.toJSON(Response.error("11111111")));
    }

    public static String getRealPath(String basePath,String relativePath){
        if(relativePath.startsWith("../")){
            basePath=basePath.substring(0,basePath.lastIndexOf("/"));
            do{
                basePath=basePath.substring(0,basePath.lastIndexOf("/"));
                relativePath=relativePath.substring(relativePath.indexOf("../")+3,relativePath.length());
            }while(relativePath.startsWith("../"));
        }else if(relativePath.startsWith("/")){
            basePath=basePath.substring(0,basePath.indexOf("/"));
            relativePath=relativePath.substring(1);
        }else{
            if(relativePath.startsWith("./")){
                basePath=basePath.substring(0,basePath.lastIndexOf("/"));
                relativePath=relativePath.substring(relativePath.indexOf("./")+2,relativePath.length());
            }else{
                basePath=basePath.substring(0,basePath.lastIndexOf("/"));
//                relativePath;
            }
        }
        return basePath+"/"+relativePath;
    }
}

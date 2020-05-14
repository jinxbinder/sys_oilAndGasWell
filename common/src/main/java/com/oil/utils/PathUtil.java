package com.oil.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: PathUtil <br/>
 * Description: <br/>
 * date: 2020/5/14 11:34<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
public class PathUtil {
    /**
    * Description: 功能描述（获取项目根路径） <br/>
    * date: 2020/5/14 11:35<br/>
    * @author libd <br/>  
    */
    public static String getPath(HttpServletRequest request){
//        String path = request.getServletContext().getContextPath();
        String path = request.getSession().getServletContext().getRealPath("/");
        //表示到项目的根目录下，要是想到目录下的子文件夹，修改"/"即可
        path = path.replaceAll("\\\\", "/");
        return path;
    }

}

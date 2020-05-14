package com.oil.api;

import com.oil.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: FileApi <br/>
 * Description: <br/>
 * date: 2020/5/12 17:09<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RequestMapping("/file")
public interface FileApi {
    /**
    * Description: 功能描述（文件上传） <br/>
    * date: 2020/5/14 10:28<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/uploadFile")
    Result uploadFile();
    /**
    * Description: 功能描述（文件下载） <br/>
    * date: 2020/5/14 10:28<br/>
    * @author libd <br/>
    */
    @RequestMapping("/downloadFile")
    Result downloadFile();
    /**
    * Description: 功能描述（文件分页查询） <br/>
    * date: 2020/5/14 17:47<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/fileListPage")
    Result fileListByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
}

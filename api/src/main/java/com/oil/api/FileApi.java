package com.oil.api;

import com.alibaba.fastjson.JSONObject;
import com.oil.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @RequestMapping(value = "/uploadFile",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("author") String author);
    /**
    * Description: 功能描述（文件下载） <br/>
    * date: 2020/5/14 10:28<br/>
    * @author libd <br/>
    */
    @RequestMapping("/downloadFile")
    ResponseEntity<byte[]> downloadFile(@RequestParam("id") Long id) throws IOException;
    /**
    * Description: 功能描述（文件分页查询） <br/>
    * date: 2020/5/14 17:47<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/fileListPage")
    Result fileListByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
    * Description: 功能描述（文件回收站分页查询） <br/>
    * date: 2020/5/14 17:47<br/>
    * @author libd <br/>
    */
    @RequestMapping("/fileRecyclePage")
    Result fileRecyclePage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
    * Description: 功能描述（单个文件逻辑删除） <br/>
    * date: 2020/5/15 18:19<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/fileDeleteOne/{id}")
    Result fileDeleteOne(@PathVariable("id") Long id);
    /**
    * Description: 功能描述（单个文件恢复） <br/>
    * date: 2020/5/15 18:19<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/fileReuse/{id}")
    Result fileReuse(@PathVariable("id") Long id);
}

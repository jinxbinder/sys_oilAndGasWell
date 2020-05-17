package com.oil.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: SkExportApi <br/>
 * Description: <br/>
 * date: 2020/5/17 17:04<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RequestMapping("/skt")
public interface SkExportApi {
    /**
    * Description: 功能描述（文件导出） <br/>
    * date: 2020/5/17 17:05<br/>
    * @author libd <br/>  
    */
    @RequestMapping(value = "/wellInfoExport",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void wellInfoExport();
}

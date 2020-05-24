package com.oil.feign;

import com.oil.config.MultipartSupportConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: SktExportFeign <br/>
 * Description: <br/>
 * date: 2020/5/17 17:05<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
/*
@FeignClient(value = "sk",contextId = "2",configuration = MultipartSupportConfig.class)
public interface SktExportFeign {
    @RequestMapping(value = "/skt/wellInfoExport",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response wellInfoExport();
}
*/

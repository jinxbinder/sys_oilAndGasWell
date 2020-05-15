package com.oil.feign;

import com.oil.api.FileApi;
import com.oil.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName: FileFeign <br/>
 * Description: <br/>
 * date: 2020/5/14 10:32<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient(value = "file",configuration = MultipartSupportConfig.class)
public interface FileFeign extends FileApi {
}

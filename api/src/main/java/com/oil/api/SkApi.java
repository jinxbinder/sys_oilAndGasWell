package com.oil.api;

import com.oil.entity.User;
import com.oil.entity.WellInfo;
import com.oil.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: SkApi <br/>
 * Description: <br/>
 * date: 2020/4/25 13:33<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RequestMapping("sk")
public interface SkApi {
    /**
     * Description: 功能描述（井信息列表分页查询） <br/>
     * date: 2020/4/25 17:02<br/>
     * @author libd <br/>
     */
    @RequestMapping("/wellInfoByPage")
    Result wellInfoByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
     * Description: 功能描述（井信息添加） <br/>
     * date: 2020/4/26 16:46<br/>
     * @author libd <br/>
     */
    @RequestMapping("/wellAdd")
    Result wellAdd(@RequestBody WellInfo wellInfo);
}

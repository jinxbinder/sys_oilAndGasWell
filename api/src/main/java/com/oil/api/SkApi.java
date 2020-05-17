package com.oil.api;

import com.oil.entity.Gun;
import com.oil.entity.RoadWork;
import com.oil.entity.User;
import com.oil.entity.WellInfo;
import com.oil.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;

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
    /**
    * Description: 功能描述（井信息id查询） <br/>
    * date: 2020/5/6 20:19<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/findWellInfo")
    Result findWellInfo(@RequestParam("wid") Long wid);
    /**
    * Description: 功能描述（井id查询对应施工信息） <br/>
    * date: 2020/5/11 20:19<br/>
    * @author libd <br/>
    */
    @RequestMapping("/workListPage")
    Result workListPage(@RequestParam("wid") Long wid);
    /**
    * Description: 功能描述（施工信息添加） <br/>
    * date: 2020/5/12 11:18<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/workAdd")
    Result workAdd(@RequestBody RoadWork roadWork);
    /**
    * Description: 功能描述（射孔枪信息分页查询） <br/>
    * date: 2020/5/12 11:30<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/gunByPage")
    Result gunByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
    * Description: 功能描述（射孔枪添加） <br/>
    * date: 2020/5/12 14:23<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/gunAdd")
    Result gunAdd(@RequestBody Gun gun);
    /**
    * Description: 功能描述（射孔枪查所有） <br/>
    * date: 2020/5/16 19:31<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/gunFindAll")
    Result gunFindAll();
    /**
    * Description: 功能描述（套管排序） <br/>
    * date: 2020/5/16 18:49<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/gunSortPage")
    Result gunSortPage(@RequestBody Gun gun);


}

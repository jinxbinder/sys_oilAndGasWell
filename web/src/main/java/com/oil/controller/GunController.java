package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Gun;
import com.oil.entity.User;
import com.oil.page.Pages;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.oil.feign.SkFeign;
import javax.annotation.Resource;

/**
 * ClassName: GunController <br/>
 * Description: <br/>
 * date: 2020/5/12 11:25<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class GunController {
    @Resource
    private SkFeign skFeign;
    /**
     * 射孔枪列表页面跳转
     * @return
     */
    @RequestMapping("/gunByPage")
    public String gunByPage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = skFeign.gunByPage(pageNum,pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        Pages<Gun> gunList = jb.getObject("data",Pages.class);
        model.addAttribute("gunList", gunList);
        return "gun-list";
    }

    /**
     * 射孔枪添加页跳转
     * @return
     */
    @RequestMapping("/gunAddPage")
    public String gunAddPage(){
        return "gun-add";
    }

    /**
     * 射孔枪添加
     * @return
     */
    @RequestMapping("/gunAdd")
    public Result gunAdd(@RequestBody Gun gun){
        return skFeign.gunAdd(gun);
    }
}

package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.entity.WellInfo;
import com.oil.feign.SkFeign;
import com.oil.page.Pages;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * ClassName: WellController <br/>
 * Description: <br/>
 * date: 2020/4/25 13:40<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class WellController {
    @Resource
    private SkFeign skFeign;
    /**
     * 井列表
     * @param model
     * @return
     */
    @RequestMapping("/wellInfoByPage")
    public String wellInfoList(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = skFeign.wellInfoByPage(pageNum,pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        Pages<WellInfo> wellList = jb.getObject("data",Pages.class);

        model.addAttribute("wellList", wellList);

        return "well-info-list";
    }
}

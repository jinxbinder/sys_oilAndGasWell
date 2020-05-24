package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Salvo;
import com.oil.entity.WellInfo;
import com.oil.feign.SkFeign;
import com.oil.page.Pages;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SalvoController <br/>
 * Description: <br/>
 * date: 2020/5/22 14:13<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class SalvoController {
    @Resource
    private SkFeign skFeign;

    /**
     * 排炮页面跳转 分页查询
     * @return
     */
    @RequestMapping("/salvoByPage")
    public String salvoByPage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = skFeign.salvoByPage(pageNum,pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        if(StringUtil.isNotNull(r.get("data"))){
            Pages<Salvo> salvoList = jb.getObject("data",Pages.class);
            model.addAttribute("salvoList", salvoList);
        }
        Result r1 = skFeign.wellInfoFindAll();
        if(!Constant.SUCCESS.equals(r1.get("code").toString())){
            return "error";
        }
        if(StringUtil.isNotNull(r1.get("data"))){
            List<WellInfo> wellInfos = (List<WellInfo>) r1.get("data");
            model.addAttribute("wellInfos", wellInfos);
        }
        return "salvo-list";
    }
    @RequestMapping("/findByWell")
    public String findByWell(Model model,@RequestParam("wid") Long wid, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = skFeign.salvoByWidPage(pageNum,pageSize,wid);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        if(StringUtil.isNotNull(r.get("data"))){
            Pages<Salvo> salvoList = jb.getObject("data",Pages.class);
            model.addAttribute("salvoList", salvoList);
        }
        Result r1 = skFeign.wellInfoFindAll();
        if(!Constant.SUCCESS.equals(r1.get("code").toString())){
            return "error";
        }
        if(StringUtil.isNotNull(r1.get("data"))){
            List<WellInfo> wellInfos = (List<WellInfo>) r1.get("data");
            model.addAttribute("wellInfos", wellInfos);
        }
        return "salvo-list";
    }
}

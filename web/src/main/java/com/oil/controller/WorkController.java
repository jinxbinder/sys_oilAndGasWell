package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.RoadWork;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.entity.WellInfo;
import com.oil.feign.SkFeign;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: WorkController <br/>
 * Description: <br/>
 * date: 2020/5/6 20:01<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class WorkController {
    @Resource
    private SkFeign skFeign;
    /**
     * 施工页添加跳转
     * @param wid
     * @return
     */
    @RequestMapping("/workAddPage/{wid}")
    public String workAddPage(@PathVariable("wid") Long wid, Model model){
        Result r = skFeign.findWellInfo(wid);
        if(Constant.SUCCESS.equals(r.get("code").toString())){
            JSONObject jb = new JSONObject(r);
            if (null != jb.get("data")) {
                WellInfo well = jb.getObject("data", WellInfo.class);
                model.addAttribute("well",well);
            }
        }
        return "work-add";
    }

    /**
     * 施工页列表页面跳转 同时展示 不采用分页
     * @param wid
     * @param model
     * @return
     */
    @RequestMapping("/workListPage/{wid}")
    public String workListPage(@PathVariable("wid") Long wid, Model model){
        model.addAttribute("wellId",wid);
        Result r = skFeign.workListPage(wid);
        log.info(r.toString());
        if(Constant.SUCCESS.equals(r.get("code").toString()) && StringUtil.isNotNull(r.get("data"))){
            List<RoadWork> works = (List<RoadWork>) r.get("data");
            model.addAttribute("works",works);
        }
        return "work-list";
    }
    /**
     * 施工信息添加
     * @param roadWork
     * @return
     */
    @ResponseBody
    @RequestMapping("/workAdd")
    public Result workAdd(@RequestBody RoadWork roadWork){
        log.info(roadWork.toString());
        return skFeign.workAdd(roadWork);
    }
}

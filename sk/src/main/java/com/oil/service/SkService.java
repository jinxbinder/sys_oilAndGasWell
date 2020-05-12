package com.oil.service;

import com.oil.api.SkApi;
import com.oil.dao.GunRepository;
import com.oil.entity.*;
import com.oil.manage.GunManage;
import com.oil.manage.RoadWorkManage;
import com.oil.manage.WellInfoManage;
import com.oil.page.Pages;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: SkService <br/>
 * Description: <br/>
 * date: 2020/4/25 15:21<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class SkService implements SkApi {
    @Autowired
    private WellInfoManage wellInfoManage;
    @Autowired
    private RoadWorkManage roadWorkManage;
    @Autowired
    private GunManage gunManage;
    @Override
    public Result wellInfoByPage(int pageNum, int pageSize) {
        try {
            Page<WellInfo> wellInfos = wellInfoManage.wellListByPage(PageRequest.of(pageNum,pageSize));
            Pages<WellInfo> w = new Pages<>();
            w.setContent(wellInfos.getContent());
            w.setPageNo(wellInfos.getNumber());
            w.setPageSize(wellInfos.getSize());
            w.setTotal(wellInfos.getTotalElements());
            return Result.success(w);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();

        }
    }
    @Override
    public Result wellAdd(@RequestBody WellInfo wellInfo) {
        try {
            wellInfoManage.wellAdd(wellInfo);
            return Result.success();
        }catch (Exception e){
            log.error("井信息新增失败：error#####",e);
            return Result.error("井信息新增失败");
        }
    }

    @Override
    public Result findWellInfo(@RequestParam("wid") Long wid) {
        WellInfo wellInfo = wellInfoManage.findById(wid);
        return Result.success(wellInfo);
    }

    @Override
    public Result workListPage(@RequestParam("wid") Long wid) {
        try {
            List<RoadWork> works = roadWorkManage.workListPage(wid);
            return Result.success(works);
        }catch (Exception e){
            return Result.error();

        }
    }

    @Override
    public Result workAdd(@RequestBody RoadWork roadWork) {
        try {
            roadWorkManage.workAdd(roadWork);
            return Result.success();
        }catch (Exception e){
            log.error("新增失败：error#####",e);
            return Result.error("新增失败");
        }
    }

    @Override
    public Result gunByPage(int pageNum, int pageSize) {
        Page<Gun> guns = gunManage.gunByPage(PageRequest.of(pageNum,pageSize));
        Pages<Gun> g = new Pages<>();
        g.setContent(guns.getContent());
        g.setPageNo(guns.getNumber());
        g.setPageSize(guns.getSize());
        g.setTotal(guns.getTotalElements());
        return Result.success(g);
    }

    @Override
    public Result gunAdd(Gun gun) {
        try {
            gunManage.gunAdd(gun);
            return Result.success();
        }catch (Exception e){
            log.error("新增失败：error#####",e);
            return Result.error("新增失败");
        }
    }
}

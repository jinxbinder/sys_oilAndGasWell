package com.oil.service;

import com.oil.api.SkApi;
import com.oil.config.ExcelUtil;
import com.oil.dao.GunRepository;
import com.oil.entity.*;
import com.oil.manage.GunManage;
import com.oil.manage.RoadWorkManage;
import com.oil.manage.SalvoManage;
import com.oil.manage.WellInfoManage;
import com.oil.page.Pages;
import com.oil.sort.Arith;
import com.oil.sort.DataFormatting;
import com.oil.sort.Oil;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    private SalvoManage salvoManage;
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
    public Result wellInfoFindAll() {
        try {
            List<WellInfo> wellInfos = wellInfoManage.wellInfoFindAll();
            return Result.success(wellInfos);
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
            String[] d = roadWork.getPerforationWellSection().split("-");
            double deep = Arith.sub(Double.parseDouble(d[1]),Double.parseDouble(d[0]));
            roadWork.setPerforationThick(deep);
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

    @Override
    public Result gunFindAll() {
        List<Gun> guns = gunManage.findAll();
        return Result.success(guns);
    }

    @Override
    public Result gunSortPage(Gun gun1) {
        Long id = Long.parseLong(gun1.getGunType());
        List<RoadWork> roadWorks = roadWorkManage.workListPage(id);
        Gun gunt = gunManage.findById(gun1.getId());
        DataFormatting dataFormatting = new DataFormatting();
        //射孔枪规格
        Double gun[] = null;
        gun = dataFormatting.gun(gunt);
        Double well[] = null;
        well = dataFormatting.layers(roadWorks);
        //接口长度
        double joint = gunt.getJoint();
        String gunType = gunt.getGunName();
        List<Salvo> salvos = Oil.sort1(gunType,id,gun,joint,well);
        try {
            salvoManage.saveAll(salvos);
            wellInfoManage.wellStatus(id,"1");
            return Result.success(well);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result salvoByPage(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        try {
            Page<Salvo> salvos = salvoManage.salvoByPage(PageRequest.of(pageNum,pageSize));
            Pages<Salvo> s = new Pages<>();
            s.setContent(salvos.getContent());
            s.setPageNo(salvos.getNumber());
            s.setPageSize(salvos.getSize());
            s.setTotal(salvos.getTotalElements());
            return Result.success(s);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result salvoByWidPage(int pageNum, int pageSize, Long wid) {
        try {
            Page<Salvo> salvos = salvoManage.salvoByWidPage(PageRequest.of(pageNum,pageSize),wid);
            Pages<Salvo> s = new Pages<>();
            s.setContent(salvos.getContent());
            s.setPageNo(salvos.getNumber());
            s.setPageSize(salvos.getSize());
            s.setTotal(salvos.getTotalElements());
            return Result.success(s);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


}

package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.WellInfo;
import com.oil.feign.SkFeign;

import com.oil.page.Pages;
import com.oil.utils.DateUtil;
import com.oil.utils.Result;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;

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
//    @Resource
//    private SktExportFeign sktExportFeign;
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

    /**
     * 井信息添加页跳转
     * @return
     */
    @RequestMapping("/wellAddPage")
    public String wellAddPage(){

        return "well-add";
    }

    /**
     * 井信息添加添加  ajax 返回字符串
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/wellAdd")
    public Result wellAdd(@RequestBody WellInfo wellInfo, Principal principal){
        wellInfo.setCreateTime(DateUtil.getTimestamp());
        wellInfo.setCreateBy(principal.getName());
        log.info("用户信息"+wellInfo.toString());
        Result r = skFeign.wellAdd(wellInfo);
        return r;
    }

    /**
     * 数据导出页面跳转
     * @return
     */
    @RequestMapping("/excelExport")
    public String excelExport(){
        return "excel-export";
    }

    /**
     * 全部井信息数据导出
     */
  /*  @ResponseBody
    @RequestMapping("/wellInfoExport")
    public void wellInfoExport() throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();
        //获得文件流
        Response response = sktExportFeign.wellInfoExport();
        Response.Body body = response.body();

        InputStream fileInputStream = null;
        OutputStream outStream;
        try {
            fileInputStream = body.asInputStream();
            outStream = httpServletResponse.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outStream.close();
            outStream.flush();
        } catch (Exception e) {

        }*/
//        HttpHeaders headers = new HttpHeaders();
//        ResponseEntity<byte[]> entity = null;
//        return response;
        /*InputStream in=null;
        try {
            in = response.body().asInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        InputStream in = entity.getBody().getInputStream();
        System.out.println(in);

        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }*/



        /*InputStream inputStream = null;
        String filePath ="";
        Response.Body body = response.body();
        FileOutputStream fos = null;
        try {
            //获取response中的文件流
            inputStream = body.asInputStream();
//            byte[] b = new byte[inputStream.available()];
//            inputStream.read(b);
            //临时目录
            String folder=System.getProperty("java.io.tmpdir");
            int random = (int)(1+Math.random()*(10-1+1));
            String sj = String.valueOf(DateUtil.getTimestamp());
            //临时路径+文件名称
            filePath = folder + sj+random+fileName.substring(fileName.lastIndexOf("."));
            //写入文件
            fos= new FileOutputStream(filePath);
            byte[] c = new byte[1024];
            int length;
            while((length= inputStream.read(c))>0){
                fos.write(c,0,length);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
//    }

}

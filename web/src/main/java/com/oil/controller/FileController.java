package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.feign.FileFeign;
import com.oil.page.Pages;
import com.oil.utils.PathUtil;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * ClassName: FileController <br/>
 * Description: <br/>
 * date: 2020/5/14 10:33<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class FileController {
    @Resource
    private FileFeign fileFeign;

    /**
     * 文件下载
     * @param id
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) throws IOException {
        return fileFeign.downloadFile(Long.parseLong(id));
    }

    /**
     * 文件上传
     * @param file
     * @param imageInfo
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadFile")
    public Result upload(@RequestPart("file") MultipartFile file, @RequestPart("imageInfo") JSONObject imageInfo) {
        String author = imageInfo.getString("author");
//        log.info(author+"作者————————————————————————————");
        return fileFeign.uploadFile(file,author);
    }
    /**
     * 文件上传页跳转
     * @return
     */
    @RequestMapping("/uploadPage")
    public String uploadPage(){
        return "file-upload";
    }

    /**
     * 文件列表页跳转 分页显示
     * @return
     */
    @RequestMapping("/fileListPage")
    public String fileListPage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = fileFeign.fileListByPage(pageNum,pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        Pages<com.oil.entity.File> fileList = jb.getObject("data",Pages.class);

        model.addAttribute("fileList", fileList);
        return "file-list";
    }

    /**
     * 文件回收站页面跳转 分页显示
     * @return
     */
    @RequestMapping("/fileRecyclePage")
    public String fileRecyclePage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = fileFeign.fileRecyclePage(pageNum,pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        Pages<com.oil.entity.File> fileList = jb.getObject("data",Pages.class);

        model.addAttribute("fileList", fileList);
        return "file-recycle";
    }

    /**
     * 单个文件逻辑删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/fileDeleteOne/{id}")
    public Result fileDeleteOne(@PathVariable("id") Long id){
        return fileFeign.fileDeleteOne(id);
    }

    /**
     * 单个文件还原
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/file_reuse/{id}")
    public Result file_reuse(@PathVariable("id") Long id){
        return fileFeign.fileReuse(id);
    }
}

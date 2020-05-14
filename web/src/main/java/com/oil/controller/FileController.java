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
    @RequestMapping("/uploadFile")
    public Result uploadFile(){
        return Result.success();
    }
    @ResponseBody
    @RequestMapping("/downloadFile")
    public void downloadFile() throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String fileName = "fff.jpg";
        if(StringUtil.isNotEmpty(fileName)){
            String filePath = "F:/testFile/"+fileName;
            File file = new File(filePath);
            if(file.exists()){
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i!=-1){
                        os.write(buffer,0,i);
                        i = bis.read(buffer);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    bis.close();
                    fis.close();
                }
            }
        }
    }
    @ResponseBody
    @PostMapping("/uploadFile")
    public Result upload(@RequestPart("file") MultipartFile file, @RequestPart("imageInfo") JSONObject imageInfo) {
        System.out.println(imageInfo);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String path = PathUtil.getPath(request);
        log.info(path);
        if(StringUtil.isNotEmpty(path)){
            String name = file.getOriginalFilename();
            String suffixName = name.substring(name.lastIndexOf("."));
            log.info(name);
            String uuid = UUID.randomUUID().toString();

            String filePath = "F:/testFile/"+name;

            File f = new File(filePath);
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            String author = imageInfo.getString("author");
            try {
                file.transferTo(f);
                com.oil.entity.File file1 = new com.oil.entity.File();
                file1.setAuthor(author);
                file1.setFileUUID(uuid);
                file1.setFileName(name);
                file1.setFileType(suffixName);
                log.info(file1.toString());
                return Result.success();
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传失败");
            }
        }
        return Result.error("路径为空");
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
        return "file-recycle";
    }
}

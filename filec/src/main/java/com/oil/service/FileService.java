package com.oil.service;

import com.alibaba.fastjson.JSONObject;
import com.oil.api.FileApi;
import com.oil.dao.FileRepository;
import com.oil.entity.File;
import com.oil.entity.WellInfo;
import com.oil.manage.FileManage;
import com.oil.page.Pages;
import com.oil.utils.PathUtil;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * ClassName: FileService <br/>
 * Description: <br/>
 * date: 2020/5/14 16:43<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class FileService implements FileApi{
    @Resource
    private FileManage fileManage;

    @Override
    public Result uploadFile(MultipartFile file,String author) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String path = PathUtil.getPath(request);
        if(StringUtil.isNotEmpty(path)){
            String name = file.getOriginalFilename();
            String suffixName = name.substring(name.lastIndexOf("."));
            log.info(name);
            String uuid = UUID.randomUUID().toString();

            String filePath = "F:/testFile/"+uuid+suffixName;

            java.io.File f = new java.io.File(filePath);
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
//            String author = imageInfo.getString("author");
            try {
                file.transferTo(f);
                com.oil.entity.File file1 = new com.oil.entity.File();
                file1.setAuthor(author);
                file1.setFileUUID(uuid);
                file1.setFileName(name);
                file1.setFileType(suffixName);
                fileManage.save(file1);
                log.info(file1.toString());
                return Result.success();
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传失败");
            }
        }
        return Result.error("路径为空");
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(Long id) throws IOException{
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> entity = null;
        InputStream in=null;

        File f = fileManage.findById(id);
        String type = f.getFileType();
        String fileName = f.getFileName();
        String uuid = f.getFileUUID();
        String downName = uuid+type;

        if(StringUtil.isNotEmpty(downName)){
            try {
                String filePath = "F:/testFile/"+downName;
                in=new FileInputStream(new java.io.File(filePath));
                byte[] bytes = new byte[in.available()];
//            String imageName=fileName;
                //处理IE下载文件的中文名称乱码的问题
                String header = request.getHeader("User-Agent").toUpperCase();
                if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")|| header.contains("JAVA")) {
                    fileName = URLEncoder.encode(fileName, "utf-8");
//                fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
                } else {
                    fileName = new String(fileName.getBytes(), "iso-8859-1");
                }
                in.read(bytes);
                headers.add("Content-Disposition", "attachment;filename="+fileName);
                entity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(in!=null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
//        name = name.substring(0,name.lastIndexOf("."));
        /*if(StringUtil.isNotEmpty(fileName)){
            String filePath = "F:/testFile/"+fileName;
            java.io.File file = new java.io.File(filePath);
            if(file.exists()){
                String encodeName = java.net.URLEncoder.encode(name, "utf-8");
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+encodeName);
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
        }*/

        return entity;
    }

    @Override
    public Result fileListByPage(int pageNum, int pageSize) {
        try {
            Page<File> files = fileManage.fileListByPage(PageRequest.of(pageNum,pageSize));
            Pages<File> w = new Pages<>();
            w.setContent(files.getContent());
            w.setPageNo(files.getNumber());
            w.setPageSize(files.getSize());
            w.setTotal(files.getTotalElements());
            return Result.success(w);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();

        }
    }

    @Override
    public Result fileRecyclePage(int pageNum, int pageSize) {
        try {
            Page<File> files = fileManage.fileRecyclePage(PageRequest.of(pageNum,pageSize));
            Pages<File> w = new Pages<>();
            w.setContent(files.getContent());
            w.setPageNo(files.getNumber());
            w.setPageSize(files.getSize());
            w.setTotal(files.getTotalElements());
            return Result.success(w);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();

        }
    }

    @Override
    public Result fileDeleteOne(Long id) {
        try {
            fileManage.fileDeleteOne(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result fileReuse(Long id) {
        try {
            fileManage.fileReuse(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}

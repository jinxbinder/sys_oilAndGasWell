package com.oil.service;

import com.oil.api.SkExportApi;
import com.oil.config.ExcelUtil;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExportService <br/>
 * Description: <br/>
 * date: 2020/5/17 17:38<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
public class ExportService implements SkExportApi {
    @Override
    public void wellInfoExport() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        // 定义表的标题
        String title = "员工列表一览";
        //定义表的列名
        String[] rowsName = new String[] { "序号", "姓名", "性别", "特长", "学历", "入职时间", "简历", "照片", "部门" };
        //定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = new Object[9];
        objs[0] = "测试";
        objs[1] = 11;
        objs[2] = "111";
        objs[3] = "测试";
        objs[4] = "测试";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(2018-12-24);
        objs[5] = date;
        objs[6] = "测试";
        objs[7] = "测试";
        objs[8] = "测试";
        dataList.add(objs);
        // 创建ExportExcel对象
        ExcelUtil excelUtil = new ExcelUtil();

        try{
            String fileName= new String("测试excel文档.xlsx".getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
            excelUtil.exportExcel(title,rowsName,dataList,fileName,response);
//            returnInfo.setResult(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package com.oil.task;

import com.oil.dao.FileRepository;
import com.oil.entity.File;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: FileDel <br/>
 * Description: <br/>
 * date: 2020/5/15 18:51<br/>
 * 定时任务 物理删除delFlag大于7天的文件及数据库表数据
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Component
public class FileDel {
    @Resource
    private FileRepository repository;

    @Scheduled(cron="0 0 0 * * ?")
    public void del(){
        log.info("this is scheduler task runing  del---------------");
        List<File> files = repository.del7();
        if (StringUtil.isNotEmpty(files)){
            log.info("开始清理");

            for (File file:files){
                String delName ="F:/testFile/"+ file.getFileUUID() + file.getFileType();
                java.io.File f = new java.io.File(delName);
                log.info(file.getFileName()+"文件是否存在："+f.exists());
                if(f.exists()){
                    try {
                        boolean flag = f.delete();
                        log.info(file.getFileName()+"磁盘清理:"+flag);
                        repository.deleteById(file.getId());
                        log.info("表数据清理");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

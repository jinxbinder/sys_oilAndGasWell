package com.oil.service;

import com.oil.api.FileApi;
import com.oil.dao.FileRepository;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private FileRepository fileRepository;

    @Override
    public Result uploadFile() {
        return null;
    }

    @Override
    public Result downloadFile() {
        return null;
    }

    @Override
    public Result fileListByPage(int pageNum, int pageSize) {
        return null;
    }
}

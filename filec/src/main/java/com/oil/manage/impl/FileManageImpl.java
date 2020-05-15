package com.oil.manage.impl;

import com.oil.dao.FileRepository;
import com.oil.entity.File;
import com.oil.manage.FileManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: FileManageImpl <br/>
 * Description: <br/>
 * date: 2020/5/14 16:44<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class FileManageImpl implements FileManage{
    @Resource
    private FileRepository fileRepository;
    @Override
    public Page<File> fileListByPage(Pageable pageable) {
        return fileRepository.fileable(pageable);
    }

    @Override
    public Page<File> fileRecyclePage(Pageable pageable) {
        return fileRepository.fileDel(pageable);
    }

    @Override
    public void save(File file) {
        fileRepository.save(file);
    }

    @Override
    public File findById(Long id) {
        return fileRepository.findFileById(id);
    }

    @Override
    public void fileDeleteOne(Long id) {
        fileRepository.fileDelone(id);
    }

    @Override
    public void fileReuse(Long id) {
        fileRepository.fileReuse(id);
    }
}

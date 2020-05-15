package com.oil.manage;

import com.oil.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ClassName: FileManage <br/>
 * Description: <br/>
 * date: 2020/5/14 16:43<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface FileManage {

    Page<File> fileListByPage(Pageable pageable);

    Page<File> fileRecyclePage(Pageable pageable);

    void save(File file);

    File findById(Long id);

    void fileDeleteOne(Long id);

    void fileReuse(Long id);
}

package com.oil.dao;

import com.oil.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: FileRepository <br/>
 * Description: <br/>
 * date: 2020/5/14 16:44<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface FileRepository extends JpaRepository<File,Long> {
}

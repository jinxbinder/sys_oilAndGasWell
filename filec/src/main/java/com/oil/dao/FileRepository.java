package com.oil.dao;

import com.oil.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    @Query("select f from File f where f.delFlag <> 1")
    Page<File> fileable(Pageable pageable);

    @Query("select f from File f where f.delFlag = 1")
    Page<File> fileDel(Pageable pageable);

    File findFileById(Long id);

    @Modifying
    @Query("update File f set f.delFlag = 1 where f.id = ?1")
    void fileDelone(Long id);

    @Modifying
    @Query("update File f set f.delFlag = 0 where f.id = ?1")
    void fileReuse(Long id);

    @Query(value = "SELECT * FROM file f where DATE_SUB(CURDATE(), INTERVAL 7 DAY) >= date(f.update_time) and f.del_flag = 1",nativeQuery = true)
    List<File> del7();

    void deleteById(Long id);
}

package com.oil.manage;

import com.oil.entity.Gun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * ClassName: GunManage <br/>
 * Description: <br/>
 * date: 2020/5/12 11:35<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface GunManage {

    Page<Gun> gunByPage(Pageable pageable);

    void gunAdd(Gun gun);

    List<Gun> findAll();

    Gun findById(Long id);
}

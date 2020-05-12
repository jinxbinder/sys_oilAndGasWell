package com.oil.manage;

import com.oil.entity.Gun;
import com.oil.entity.WellInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}

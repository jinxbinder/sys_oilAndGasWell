package com.oil.manage;

import com.oil.entity.Role;
import com.oil.entity.WellInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ClassName: WellInfoManage <br/>
 * Description: <br/>
 * date: 2020/4/25 15:22<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface WellInfoManage {

    Page<WellInfo> wellListByPage(Pageable pageable);

    void wellAdd(WellInfo wellInfo);
}

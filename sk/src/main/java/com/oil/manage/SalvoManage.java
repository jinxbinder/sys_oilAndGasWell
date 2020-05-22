package com.oil.manage;

import com.oil.entity.Salvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ClassName: SalvoManage <br/>
 * Description: <br/>
 * date: 2020/5/21 20:19<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface SalvoManage {

    Page<Salvo> salvoByPage(Pageable pageable);
}

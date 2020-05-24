package com.oil.manage.impl;

import com.oil.dao.SalvoRepository;
import com.oil.entity.Salvo;
import com.oil.manage.SalvoManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SalvoManageImpl <br/>
 * Description: <br/>
 * date: 2020/5/21 20:20<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class SalvoManageImpl implements SalvoManage {
    @Resource
    private SalvoRepository salvoRepository;
    @Override
    public Page<Salvo> salvoByPage(Pageable pageable) {

        return salvoRepository.findAll(pageable);
    }

    @Override
    public Page<Salvo> salvoByWidPage(Pageable pageable, Long wid) {
        return salvoRepository.salvoByWidPage(wid,pageable);
    }

    @Override
    public void saveAll(List<Salvo> salvoList) {
        salvoRepository.saveAll(salvoList);
    }


}

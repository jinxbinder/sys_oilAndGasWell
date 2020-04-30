package com.oil.manage.impl;

import com.oil.dao.WellInfoRepository;
import com.oil.entity.WellInfo;
import com.oil.manage.WellInfoManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: WellInfoManageImpl <br/>
 * Description: <br/>
 * date: 2020/4/25 15:23<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class WellInfoManageImpl implements WellInfoManage {
    @Resource
    private WellInfoRepository wellInfoRepository;
    @Override
    public Page<WellInfo> wellListByPage(Pageable pageable) {
        return wellInfoRepository.findWellable(pageable);
    }

    @Override
    public void wellAdd(WellInfo wellInfo) {
        wellInfoRepository.save(wellInfo);
    }
}

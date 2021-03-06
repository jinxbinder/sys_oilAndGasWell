package com.oil.manage.impl;

import com.oil.dao.WellInfoRepository;
import com.oil.entity.WellInfo;
import com.oil.manage.WellInfoManage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: WellInfoManageImpl <br/>
 * Description: <br/>
 * date: 2020/4/25 15:23<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@CacheConfig(cacheNames = "well")
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
    @Cacheable(key = "#wid",unless = "#result == null")
    @Override
    public WellInfo findById(Long wid) {
        return wellInfoRepository.findWellInfoByWid(wid);
    }

    @Override
    public List<WellInfo> wellInfoFindAll() {
        return wellInfoRepository.findAll();
    }

    @Override
    public void wellStatus(Long wid, String status) {
        wellInfoRepository.wellInfoStatus(wid,status);
    }
}

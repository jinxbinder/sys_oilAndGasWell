package com.oil.manage.impl;

import com.oil.dao.GunRepository;
import com.oil.entity.Gun;
import com.oil.manage.GunManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


/**
 * ClassName: GunManageImpl <br/>
 * Description: <br/>
 * date: 2020/5/12 11:35<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class GunManageImpl implements GunManage {
    @Resource
    private GunRepository gunRepository;
    @Override
    public Page<Gun> gunByPage(Pageable pageable) {
        return gunRepository.findAll(pageable);
    }

    @Override
    public void gunAdd(Gun gun) {
        gunRepository.save(gun);
    }

    @Override
    public List<Gun> findAll() {
        return gunRepository.findAll();
    }

    @Override
    public Gun findById(Long id) {

        return gunRepository.findGunById(id);
    }


}

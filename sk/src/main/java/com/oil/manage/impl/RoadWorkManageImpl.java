package com.oil.manage.impl;

import com.oil.dao.RoadWorkRepository;
import com.oil.entity.RoadWork;
import com.oil.manage.RoadWorkManage;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: RoadWorkManageImpl <br/>
 * Description: <br/>
 * date: 2020/5/11 17:19<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class RoadWorkManageImpl implements RoadWorkManage {
    @Resource
    private RoadWorkRepository roadWorkRepository;
    @Override
    public List<RoadWork> workListPage(Long wid) {
        return roadWorkRepository.findRoadWorksByWId(wid);
    }
}

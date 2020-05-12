package com.oil.manage;

import com.oil.entity.RoadWork;

import java.util.List;

/**
 * ClassName: RoadWorkManage <br/>
 * Description: <br/>
 * date: 2020/5/11 17:18<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface RoadWorkManage {

    List<RoadWork> workListPage(Long wid);

    void workAdd(RoadWork roadWork);
}

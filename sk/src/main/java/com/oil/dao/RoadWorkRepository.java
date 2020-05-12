package com.oil.dao;

import com.oil.entity.RoadWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ClassName: RoadWorkRepository <br/>
 * Description: <br/>
 * date: 2020/5/11 17:21<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface RoadWorkRepository extends JpaRepository<RoadWork,Long> {
    @Query("select r from RoadWork r where r.wid = ?1")
    List<RoadWork> findRoadWorksByWId(Long wid);
}

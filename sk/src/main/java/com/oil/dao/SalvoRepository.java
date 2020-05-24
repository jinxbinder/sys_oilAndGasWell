package com.oil.dao;

import com.oil.entity.RoadWork;
import com.oil.entity.Salvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: SalvoRepository <br/>
 * Description: <br/>
 * date: 2020/5/21 20:18<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface SalvoRepository extends JpaRepository<Salvo,Long> {
    @Query("select s from Salvo s where s.wid = ?1")
    Page<Salvo> salvoByWidPage( Long wid,Pageable pageable);
}

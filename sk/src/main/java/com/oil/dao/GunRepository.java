package com.oil.dao;

import com.oil.entity.Gun;
import com.oil.entity.RoadWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: GunRepository <br/>
 * Description: <br/>
 * date: 2020/5/12 11:40<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface GunRepository extends JpaRepository<Gun,Long> {


}

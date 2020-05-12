package com.oil.dao;

import com.oil.entity.Role;
import com.oil.entity.WellInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: WellInfoRepository <br/>
 * Description: <br/>
 * date: 2020/4/25 15:25<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface WellInfoRepository extends JpaRepository<WellInfo,Long> {

    @Query("select w from WellInfo w where w.delFlag <> 1")
    Page<WellInfo> findWellable(Pageable pageable);

    WellInfo findWellInfoByWid(Long wid);
}

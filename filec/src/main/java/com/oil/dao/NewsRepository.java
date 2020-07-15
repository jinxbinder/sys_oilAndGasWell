package com.oil.dao;

import com.oil.entity.File;
import com.oil.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: NewsRepository <br/>
 * Description: <br/>
 * date: 2020/6/24 11:19<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
}

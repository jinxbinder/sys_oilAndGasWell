package com.oil.manage.impl;

import com.oil.dao.NewsRepository;
import com.oil.entity.News;
import com.oil.manage.TestManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TestManageImpl <br/>
 * Description: <br/>
 * date: 2020/6/24 11:14<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class TestManageImpl implements TestManage {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<News> getNewsList() {
        return newsRepository.findAll();
    }
}

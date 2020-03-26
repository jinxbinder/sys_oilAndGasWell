package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

/**
 * ClassName: BaseEntity <br/>
 * Description: <br/>
 * date: 2020/3/25 19:15<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public class BaseEntity {
    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name = "create_time")
    private Timestamp createTime;

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 更新时间 */
    @Column(name = "update_time")
    private Timestamp updateTime;

    /** 备注 */
    @Column
    private String remark;
}

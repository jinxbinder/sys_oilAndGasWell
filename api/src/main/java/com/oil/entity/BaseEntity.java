package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class BaseEntity {
    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 备注 */
    private String remark;
}

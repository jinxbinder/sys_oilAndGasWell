package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: Gun <br/>
 * Description: <br/>
 * date: 2020/5/11 14:53<br/>
 * 射孔枪
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "gun")
public class Gun implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 射孔枪名 */
    private String gunName;
    /** 枪长集合空格分隔 */
    private String gunType;
    /** 接头长度 */
    private Double joint;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;
    /** 备注 */
    private String remark;

}

package com.oil.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ClassName: News <br/>
 * Description: <br/>
 * date: 2020/6/24 11:07<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "news")
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 标题 */
    private String title;
    /** 添加时间 */
    private String addTime;
    /** 摘要 */
    private String zhaiyao;
    /** 点击数 */
    private Integer click;
    /** 图片地址 */
    private String imgUrl;
}

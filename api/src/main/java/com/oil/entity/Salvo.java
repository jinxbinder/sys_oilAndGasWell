package com.oil.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ClassName: Salvo <br/>
 * Description: <br/>
 * date: 2020/5/21 20:13<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "salvo")
public class Salvo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    /** 枪型 */
    private String gunType;
    /** 枪长 */
    private String gunLen;
    /** 上空 */
    private String upNull;
    /** 下空 */
    private String downNull;
    /** 井id */
    private Long wid;
}

package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: WellInfo <br/>
 * Description: <br/>
 * date: 2020/4/25 13:45<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "well_info")
public class WellInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wid;
    /** 井id */
    private String wellId;
    /** 完钻井深(m) */
    private Double wellDeepCmplt;
    /** 油层套管规格(mm) */
    private Double oilCasingType;
    /** 油层套管深度(m) */
    private Double oilCasingDeep;
    /** 内径(mm) */
    private String diameterInner;
    /** 壁厚(mm) */
    private String wellThick;
    /** 深度(m) */
    private String wellDeep;
    /** 水泥返深(m) */
    private Double cementDeep;
    /** 人工井底(m) */
    private Double atfcalWellBtm;
    /** 压井液 */
    private String killFluid;
    /** 密度（g/cm3） */
    private Double density;
    /** 粘度(s) */
    private Double viscosity;
    /** 施工状态0未施工 1已施工 */
    private String status;
    /** 删除标志 1为删除 0正常 */
    private String delFlag = "0";
    /** 关联关系 一口井对应多个施工 */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "wi_id ")
    private List<RoadWork> roadWorks;
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

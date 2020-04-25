package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: RoadWork <br/>
 * Description: <br/>
 * date: 2020/4/25 14:51<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "road_work")
public class RoadWork implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 层位 */
    private String layerPlace;
    /** 层号 */
    private Integer layerId;
    /** 布孔方式 */
    private String holeWay;
    /** 射孔方式 */
    private String perforationWay;
    /** 射孔井段(m) */
    private String perforationWellSection;
    /** 厚度(m) */
    private Double perforationThick;
    /** 孔密 (孔/m) */
    private Double holeDensity;
    /** 孔数 */
    private Integer holeCount;
    /** 射孔弹型号 */
    private String bulletType;
    /** 总厚度(m) */
    private Double deepAll;
    /** 总孔数 */
    private Integer holeAll;
    /** 枪型弹型 */
    private String gunBulletType;
    /** 删除标志 1为删除 0正常 */
    private String delFlag = "0";
    /** 多个施工对应一口井*/
    @JsonIgnore
    @ManyToOne
    private WellInfo wellInfo;
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

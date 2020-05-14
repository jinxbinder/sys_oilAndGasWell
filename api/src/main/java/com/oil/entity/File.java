package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: File <br/>
 * Description: <br/>
 * date: 2020/5/14 16:35<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "file")
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 文件名 */
    private String fileName;
    /** 文件类型 */
    private String fileType;
    /** 作者 */
    private String author;
    /** 文件id */
    @Column(name = "file_UUID")
    private String fileUUID;
    /** 删除标志 1为删除 0正常 */
    private String delFlag = "0";
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

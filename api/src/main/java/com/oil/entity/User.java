package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javassist.SerialVersionUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: User <br/>
 * Description: <br/>
 * date: 2020/3/25 19:08<br/>
 * 用户信息
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@JsonIgnoreProperties(value = { "userList" })
@Data
@Entity
@Table(name="sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    /** 部门id */
    private Long deptId;
    /** 登录名 */
    private String loginName;
    /** 邮箱 */
    private String email;
    /** 手机号 */
    @Column(name = "phonenumber")
    private String phoneNumber;
    /** 性别 */
    private String sex;
    /** 密码 */
    private String password;
    /** 盐值 */
    private String salt;
    /** 状态标志 1为停用 0正常 */
    private String status = "0";
    /** 删除标志 1为删除 0正常 */
    private String delFlag = "0";
    /** 本次登录ip */
    private String loginIp;
    /** 登录时间 */
    private Timestamp loginDate;
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
    //用户-角色
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", loginName='" + loginName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}

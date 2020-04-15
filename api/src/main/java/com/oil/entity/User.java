package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
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
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long deptId;
    private String loginName;
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private String sex;
    private String password;
    private String salt;
    private String status = "0";
    private String delFlag = "0";
    private String loginIp;
    private Timestamp loginDate;
    //用户-角色
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}

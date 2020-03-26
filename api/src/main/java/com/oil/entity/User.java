package com.oil.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ClassName: User <br/>
 * Description: <br/>
 * date: 2020/3/25 19:08<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name="sys_user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "dept_id")
    private Long deptId;
    @Column(name = "login_name")
    private String loginName;
    @Column
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column
    private String sex;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private String status;
    @Column(name = "del_flag")
    private String delFlag;
    @Column(name = "login_ip")
    private String loginIp;
    @Column(name = "login_date")
    private Timestamp loginDate;
}

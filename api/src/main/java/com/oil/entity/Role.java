package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: Role <br/>
 * Description: <br/>
 * date: 2020/3/28 13:26<br/>
 * 用户角色
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@JsonIgnoreProperties(value = { "roleList","roles" })
@Data
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    /** 角色名称 */
    private String roleName;
    /** 角色key */
    private String roleKey;
    /** 角色状态 0正常 1禁用 */
    private String status = "0";
    /** 删除标志 1为删除 0正常 */
    private String delFlag = "0";
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
    // 用户 - 角色关系定义;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="sys_user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<User> userList;// 一个角色对应多个用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}

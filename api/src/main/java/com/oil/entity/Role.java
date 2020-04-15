package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
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
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope = "1";
    private String status;
    private String delFlag = "0";
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
                ", roleSort=" + roleSort +
                ", status='" + status + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}

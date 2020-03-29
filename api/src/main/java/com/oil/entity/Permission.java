package com.oil.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * ClassName: Permission <br/>
 * Description: <br/>
 * date: 2020/3/28 13:54<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Entity
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId = 0L;
    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;//资源类型，[menu|button]
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Integer orderNum = 0;
    private String url = "#";
    private String visible = "0";
    //权限 角色
    @ManyToMany
    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="permission_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
    private List<Role> roles;

}

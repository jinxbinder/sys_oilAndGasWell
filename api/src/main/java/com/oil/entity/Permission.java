package com.oil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 权限名 */
    private String name;
    /** 权限链接 */
    private String url = "#";
    /** 可用性 0可用 1禁用 */
    private String visible = "0";
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
    //权限 角色
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="permission_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
    private List<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", visible='" + visible + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

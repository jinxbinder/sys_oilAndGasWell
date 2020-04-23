# sys_oagw
spring version
    boot 2.2.6
    cloud Hoxton.RELEASE
## 1.项目架构
 - Euraka注册中心
 - Zuul网关
 - Oauth2认证授权
 - OpenFeign负载均衡
 - Spring Cloud Config 统一配置中心
### 1.1 user
   涵盖用户的CRUD操作
   数据访问采用spring data jpa
### 1.2 web
   使用spring security，thymeleaf，layui，x-admin
#### 1.2.1 
   对于jpa分页查询page<>在openFeign远程调用无法序列化
   的问题，使用了自定义pages.
#### 1.2.2 模糊查询
   对于根据管理员登录时间范围及用户名进行模糊查询写了一条原生sql
```sql
select * from sys_user u where u.login_name like CONCAT('%',if(?1!='',?1,''),'%') and if(?2!='',u.login_date>=?2,1=1) and if(?3!='',u.login_date<=?3,1=1) and u.del_flag <> 1;
```



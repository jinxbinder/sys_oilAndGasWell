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
 - Web 前端页面
 - User 用户服务
 - Sk 油井作业服务
 - Filec 文件服务
 - Common 通用模块
 - Api 接口模块
### 1.1 user
   涵盖用户的CRUD操作
   数据访问采用spring data jpa
### 1.2 web
   使用spring security，thymeleaf，layui，x-admin
#### 1.2.1 
   ***
#### 1.2.2 模糊查询
   对于根据管理员登录时间范围及用户名进行模糊查询写了一条原生sql
```sql
select * from sys_user u where u.login_name like CONCAT('%',if(?1!='',?1,''),'%') and if(?2!='',u.login_date>=?2,1=1) and if(?3!='',u.login_date<=?3,1=1) and u.del_flag <> 1;
```

## 2.纪念此项目踩的坑
   以下代码只展示关键部分，详情及注释请看源码
### 2.1 
  * 问题描述：对于spring data jpa分页查询page<T>在openFeign远程调用无法反序列化.  
  * 原因分析：由于模块间传输格式为json，在接受到json数据后将其转为page<T>对象，需要有无参构造才可以反序列化，
            而page<T>的实现类pageImpl没有无参构造.          
  * 解决方案：使用自定义page类，将jpa查询到的page<T>转为pages<T>后传输,才疏学浅，只想到了用这种办法解决.
```java
    /**
    * Page<User> user = userManage.userListByPage(PageRequest.of(pageNum,pageSize));
    Pages<User> u = new Pages<>();
    u.setContent(user.getContent());
    u.setPageNo(user.getNumber());
    u.setPageSize(user.getSize());
    u.setTotal(user.getTotalElements());
    return Result.success(u);*/
```
### 2.2
  * 问题描述：将th标签里的java对象用ajax传给后端，json格式错误
  * 原因分析：ajax传参时data参数需为json字符串或json对象格式，java对象不可直接用于传参.
```text
java对象： {roleId=1, roleName=管理员, roleKey=admin}
json对象： {"roleId":1, "roleName":"管理员", "roleKey":"admin"}
json字符串： {"roleId":"1", "roleName":"管理员", "roleKey":"admin"}
```
  * 解决方案：自定义js方法将java对象转为json对象.网上找到一个更简洁的方法，但是不会改，于是我将java对象拼接成json字符串的格式后又转为json对象.
```javascript 1.8
    function json2(c){
        var jsonData="";
        c=c.replace("{","").replace("}","");
        c=c.split(',');
        for(var i=0;i<c.length-1;i++){
            jsonData = jsonData + '"'+ (c[i].split('=')[0]).replace(/[\s]+/g,"").replace(/null/,"")+'":"' + (c[i].split('=')[1]).replace(/[\s]+/g,"").replace(/null/,"") + '",';
        }
        jsonData = jsonData.substring(0,jsonData.length-1);
        jsonData = "{" + jsonData + "}";
        return JSON.parse(jsonData)
    }
    form.on('submit(add)',
    function(data) {
        var ids = [];
        $("input[name='roles']:checked").each(function(i){
            var obj = json2($(this).val());
            ids.push(obj);
        });

        var userData = {
            loginName:$('#username').val(),
            password:$('#L_pass').val(),
            phoneNumber:$('#phone').val(),
            email:$('#L_email').val(),
            roleList:ids
        };
        $.ajax({
            url:'/userAdd',
            type:'post',
            dataType:'json',
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success:function (rsp) {
                var code = rsp.code;
                if("200" == code){
                    layer.alert("增加成功", {
                            icon: 6
                        },
                        function() {
                            xadmin.close();
                            xadmin.father_reload();
                        });
                }else {
                    layer.alert("增加失败", {
                            icon: 6
                        },
                        function() {
                            xadmin.close();
                        });
                }
            },
            error:function (err) {
                layer.alert("增加失败", {
                        icon: 6
                    },
                    function() {
                        xadmin.close();
                    });
            }
        });
        return false;
    });
```
### 2.3
  * 问题描述：spring data jpa 多对多级联操作关联关系维护失效（user,role,user_role),中间表数据不变
  * 原因分析：多对多应给表级联操作权限和指定关联关系维护方和被维护方
  * 解决方案：指定user为维护方，赋予二者级联操作权限，给被维护方加mappedBy属性.
```java
class User{
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;
}
class Role{
    @JsonIgnore
    @ManyToMany(mappedBy = "roleList",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private List<User> userList;
}
```
### 2.4
  * 问题描述：spring data jpa 多对多（user,role）查询栈溢出.
  * 原因分析：user对象有roleList字段，role对象有userList字段。二者进行俄罗斯套娃.
  * 解决方案：将roleList,userList其中一个字段忽略序列化，代码参考2.3.(注意注解与所用的json工具匹配)
### 2.5
  * 问题描述：将Timestamp格式的时间存入mysql数据库会有13小时的时差.
  * 原因分析：jdbc时区与mysql数据库时区不相同导致.
  * 解决方案：将jdbc时区改为serverTimezone=Hongkong.
### 2.6
  * 问题描述：将Timestamp格式的时间数据展示到前台数据格式变为2020-04-24T10:27:03.000+0000
  * 原因分析：数据进行json序列化时未指定格式.
  * 解决方案：在实体类对应成员属性上加json格式化注解.（注意json工具的匹配）
```java
class User{
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
}
```
### 2.7
  * 问题描述：docker容器间调用超时和ip地址无法解析.
  * 原因分析：eureka配置不全，容器存在虚拟ip
  * 解决方案：1）对于注入eureka的服务ip解析错误问题，ip呈现为docker容器id，在eureka-client服务中加入如下配置，启用ip注册.
```yaml
eureka:
  instance:
    prefer-ip-address: true
```
  * 解决方案：2）对于服务调用超时，ip为虚拟ip可修改容器启动命令，指定ip为宿主机ip.
```text
docker run --name user -v /root/docker/docker-user/test:/tmp -p 8762:8762 -d -e "EUREKA_INSTANCE_IP-ADDRESS=39.102.34.165" -e "SERVER_PORT=8762"
```

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oil.entity.User;
import com.oil.utils.Result;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: JsonTest <br/>
 * Description: <br/>
 * date: 2020/4/11 15:38<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class JsonTest {
    @Test
    public void jt(){
        User user = new User();
        user.setPassword("123123");
        user.setLoginName("admin");
        Result r = new Result();
        r.put("code",200);
        r.put("msg","123");
        r.put("data",user);
        System.out.println(r.toString());
//        String js = JSON.toJSONString(r);
        JSONObject jb = new JSONObject(r);
        User u  = jb.getObject("data",User.class);
        System.out.println(u.toString());

//        JSONObject obj=JSON.parseObject(jsonStr);//将json字符串转换为json对象
    }
    @Test
    public void  dd(){
       /* Result r = T1.rr();
        System.out.println(r.toString());
        if(null!=r.get("data")){
            User u = (User) r.get("data");
            System.out.println(u.toString());
        }*/
    }
}

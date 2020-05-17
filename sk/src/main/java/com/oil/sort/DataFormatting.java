package com.oil.sort;

import com.oil.entity.Gun;
import com.oil.entity.RoadWork;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: DataFormatting <br/>
 * Description: <br/>
 * date: 2020/5/16 18:54<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
public class DataFormatting {
    /**
    * Description: 功能描述（将guntype转为数组） <br/>
    * date: 2020/5/17 11:05<br/>
    * @author libd <br/>  
    */
    public Double[] gun(Gun gun){
        String gunType = gun.getGunType();
        if(StringUtil.isNotEmpty(gunType)){
            String[] gs = gunType.split(" ");
            Double[] g = new Double[gs.length];
            int i = 0;
            for(String str:gs){
                g[i++] = Double.parseDouble(str);
            }
            log.info("枪长："+Arrays.toString(g));
            return g;
        }
        return null;
    }
    /**
    * Description: 功能描述（将射孔段分成数组） <br/>
    * date: 2020/5/17 11:06<br/>
    * @author libd <br/>  
    */
    public Double[] layers(List<RoadWork> roadWorks){
        if (StringUtil.isNotEmpty(roadWorks)){
            Double[] layer = null;
            List<Double> lay = new ArrayList<>();
            for(RoadWork rw:roadWorks){
                String[] one = rw.getPerforationWellSection().split("-");
                lay.add(Double.parseDouble(one[0]));
                lay.add(Double.parseDouble(one[1]));
            }
            layer = lay.toArray(new Double[]{});
            Arrays.sort(layer);
            log.info("射孔段："+Arrays.toString(layer));
            return deep(layer);
        }
        return null;
    }
    /**
    * Description: 功能描述（夹层产层厚度解析） <br/>
    * date: 2020/5/17 11:21<br/>
    * @author libd <br/>  
    */
    public Double[] deep(Double[] layer){
        //油层分布第一层和最后一层为产层，交替出现，数组元素个数一直为奇数  最下层为数组第一个元素
        int deeplen = layer.length - 1;
        Double[] deep = new Double[deeplen];
        int j = 0;
        for(int i=layer.length-1;i>0;i--){
            deep[j++] = Arith.sub(layer[i],layer[i-1]);
        }
        log.info("厚度："+Arrays.toString(deep));
        return deep;
    }
}

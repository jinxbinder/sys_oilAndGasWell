package com.oil.sort;

import com.alibaba.fastjson.JSONObject;
import com.oil.entity.Salvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Oil {

	static double jk = 0;
	/*public static void main(String[] args) {
		//射孔枪规格
		double gun[] = {1.0,2.0,3.0,4.0,5.0};  
		//接口长度
		//油层分布第一层和最后一层为产层，交替出现，数组元素个数一直为奇数  最下层为数组第一个元素
		double well[] = {4.5,1.0,5.2,6.0,3.1}; 
		sort1(gun,jk,well);
		
		
	}*/
	public static List<Salvo> sort1(String gunType,Long id,Double gun[],double join,Double well[]){
		List<Salvo> salvoList = new ArrayList<>();
		JSONObject json = new JSONObject();
		jk = join;
		System.out.println("射孔枪串接排序开始");
		System.out.println("射孔枪的规格为(m)：");
		//上空
		double up_null = 0;
		//下空
		double down_null = 0;
		//遍历枪型
		gun_print(gun);
		//定义层数计数器
		int count1 = 1,count2 = 1;
		//定义差值
		double k2 = 0;
		//定义最大枪型
		double big_gun = gun[gun.length-1];
		//定义当前层厚
		double current = 0;
		//定义下层厚度
		double next = 0;
		for (int i = 0; i < well.length; i++) {
			Salvo salvo = new Salvo();
			Map<String,Double> result = new HashMap<>();
			//定义当前层厚
			current = well[i];
			if(i<well.length-1){
				next = well[i+1];
			}else{
				next = 0;
			}
			// 0为产层，1为夹层
			int flag = i%2;
			//定义射孔枪数
			int gun_sum1 = 0;
			if(flag==0){
				System.out.println("产层"+count1+"厚度为："+current+"m");
				System.out.println("射孔枪下空值为："+k2);
				down_null = k2;
				count1++;
				//double sum1 = current/big_gun;
				double sum1 = Arith.div(current+k2, big_gun+jk, 2);
				System.out.println("选枪个数"+sum1);
				if(sum1>1){
					if(next==0){
						//射孔枪数
						gun_sum1 = (int) Math.floor(sum1);
						next = 1.0;
					}else{
						//射孔枪数
						gun_sum1 = (int) Math.ceil(sum1);
					}
					//k2 = current - gun_sum1*big_gun+gun_sum1*jk;
					k2 = current + k2 - gun_sum1 * (big_gun + jk);
					k2 = Arith.round(k2, 2);
					//System.out.println("k2k2:"+k2);
				/*	if(next==0){
						System.out.println("排序完成");
						break;
					}*/
					if(next+k2<jk){
						gun_sum1 = gun_sum1-1;
						k2 = current - gun_sum1 * (big_gun + jk);
						k2 = Arith.round(k2, 2);
						result = choose_gun(gun,k2,current,next);
						System.out.println("射孔枪串接："+big_gun+"m枪型 *"+gun_sum1);
						salvo.setGunNum(gun_sum1);
						salvo.setGunLen(big_gun);
						salvo.setGunType(gunType);
						salvo.setWid(id);
						salvo.setGunNum(gun_sum1);
						salvo.setDownNull(down_null);
						salvo.setUpNull(result.get("up"));
						salvoList.add(salvo);
					}else{
						//result = choose_gun(gun,k2,current,next);
						k2 = k2 + next;
						k2 = Arith.round(k2, 2);
						System.out.println("射孔枪串接："+big_gun+"m枪型 *"+gun_sum1);
						salvo.setGunNum(gun_sum1);
						salvo.setGunLen(big_gun);
						salvo.setGunType(gunType);
						salvo.setWid(id);
						salvo.setGunNum(gun_sum1);
						salvo.setDownNull(down_null);
						salvo.setUpNull(result.get("up"));
						salvoList.add(salvo);
					}
					
					
					
				}else{
					//产层小于最长枪型
//					k2 = current;
					result = choose_gun(gun,k2,current,next);
				}
				if(!result.isEmpty()){
					k2 = result.get("re");
					Double gunLen = result.get("gun");
					System.out.println(gunLen+"m枪型 * 1 距离上一层："+k2);
					salvo.setGunNum(1);
					salvo.setGunLen(gunLen);
					salvo.setGunType(gunType);
					salvo.setWid(id);
					salvo.setGunNum(gun_sum1);
					salvo.setDownNull(down_null);
					salvo.setUpNull(result.get("up"));
					salvoList.add(salvo);
				}
				System.out.println("距离上一层："+k2);
				
			}else{
				System.out.println("夹层"+count2+"厚度为："+current+"m");
				
				count2++;
			}
			
		}
		
	return salvoList;
		
	}
	public static void gun_print(Double gun[]){
		for(double ss:gun){
			System.out.print(ss+"  ");
		}
		System.out.println();
	}

	public static Map<String,Double> choose_gun(Double gun[],double k2,double current,double next){
		//选枪  k2 = current 为当前层厚
		System.out.println("选枪开始,接口长度："+jk);
		Map<String,Double> map = new HashMap<>();
		double key = 0;
		double dd = 0;
		double up_null = 0;
		if(next==0){
			key = k2 + current;
			for(int i=0;i<=gun.length-1;i++){
				if(key<gun[i]){
					//dd为下空值
					dd = key - gun[i];
					dd = Arith.round(dd, 2);
					up_null = gun[i] - key;
					map.put("gun", gun[i]);
					map.put("re", dd);
					up_null = Arith.round(up_null,2);
					map.put("up",up_null);
					//System.out.println("射孔枪串接"+gun[i]+"m * 1 距离上一层："+dd);
					return map;
				}
				System.out.println("枪型："+gun[i]+"排除");
			}
		}else{
			key = next + k2 + current - jk;
			for(int i=gun.length-1;i>=0;i--){
				if(key>gun[i]){
					//dd为下空值
					dd = key - gun[i];
					dd = Arith.round(dd, 2);
					up_null = gun[i] - current -k2;
					up_null = Arith.round(up_null,2);
					map.put("gun", gun[i]);
					map.put("re", dd);
					map.put("up",up_null);
					//System.out.println("射孔枪串接"+gun[i]+"m * 1 距离上一层："+dd);
					return map;
				}
				System.out.println("枪型："+gun[i]+"排除");
			}
		}
		
		
		return map;
	}

}

package com.entity.model;

import com.entity.YuanchengkongzhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 远程控制
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public class YuanchengkongzhiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 家居名称
	 */
	
	private String jiajumingcheng;
		
	/**
	 * 家居分类
	 */
	
	private String jiajufenlei;
		
	/**
	 * 家居图片
	 */
	
	private String jiajutupian;
		
	/**
	 * 房间名称
	 */
	
	private String fangjianmingcheng;
		
	/**
	 * 品牌
	 */
	
	private String pinpai;
		
	/**
	 * 家居位置
	 */
	
	private String jiajuweizhi;
		
	/**
	 * 控制时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date kongzhishijian;
		
	/**
	 * 控制操作
	 */
	
	private String kongzhicaozuo;
				
	
	/**
	 * 设置：用户姓名
	 */
	 
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
				
	
	/**
	 * 设置：家居名称
	 */
	 
	public void setJiajumingcheng(String jiajumingcheng) {
		this.jiajumingcheng = jiajumingcheng;
	}
	
	/**
	 * 获取：家居名称
	 */
	public String getJiajumingcheng() {
		return jiajumingcheng;
	}
				
	
	/**
	 * 设置：家居分类
	 */
	 
	public void setJiajufenlei(String jiajufenlei) {
		this.jiajufenlei = jiajufenlei;
	}
	
	/**
	 * 获取：家居分类
	 */
	public String getJiajufenlei() {
		return jiajufenlei;
	}
				
	
	/**
	 * 设置：家居图片
	 */
	 
	public void setJiajutupian(String jiajutupian) {
		this.jiajutupian = jiajutupian;
	}
	
	/**
	 * 获取：家居图片
	 */
	public String getJiajutupian() {
		return jiajutupian;
	}
				
	
	/**
	 * 设置：房间名称
	 */
	 
	public void setFangjianmingcheng(String fangjianmingcheng) {
		this.fangjianmingcheng = fangjianmingcheng;
	}
	
	/**
	 * 获取：房间名称
	 */
	public String getFangjianmingcheng() {
		return fangjianmingcheng;
	}
				
	
	/**
	 * 设置：品牌
	 */
	 
	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}
	
	/**
	 * 获取：品牌
	 */
	public String getPinpai() {
		return pinpai;
	}
				
	
	/**
	 * 设置：家居位置
	 */
	 
	public void setJiajuweizhi(String jiajuweizhi) {
		this.jiajuweizhi = jiajuweizhi;
	}
	
	/**
	 * 获取：家居位置
	 */
	public String getJiajuweizhi() {
		return jiajuweizhi;
	}
				
	
	/**
	 * 设置：控制时间
	 */
	 
	public void setKongzhishijian(Date kongzhishijian) {
		this.kongzhishijian = kongzhishijian;
	}
	
	/**
	 * 获取：控制时间
	 */
	public Date getKongzhishijian() {
		return kongzhishijian;
	}
				
	
	/**
	 * 设置：控制操作
	 */
	 
	public void setKongzhicaozuo(String kongzhicaozuo) {
		this.kongzhicaozuo = kongzhicaozuo;
	}
	
	/**
	 * 获取：控制操作
	 */
	public String getKongzhicaozuo() {
		return kongzhicaozuo;
	}
			
}

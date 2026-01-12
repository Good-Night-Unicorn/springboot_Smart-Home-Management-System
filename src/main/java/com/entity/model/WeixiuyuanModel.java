package com.entity.model;

import com.entity.WeixiuyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 维修员
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-01-02 18:25:28
 */
public class WeixiuyuanModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 维修姓名
	 */
	
	private String weixiuxingming;
		
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 头像
	 */
	
	private String touxiang;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 联系方式
	 */
	
	private String lianxifangshi;
		
	/**
	 * 技能描述
	 */
	
	private String jinengmiaoshu;
		
	/**
	 * 服务范围
	 */
	
	private String fuwufanwei;
				
	
	/**
	 * 设置：维修姓名
	 */
	 
	public void setWeixiuxingming(String weixiuxingming) {
		this.weixiuxingming = weixiuxingming;
	}
	
	/**
	 * 获取：维修姓名
	 */
	public String getWeixiuxingming() {
		return weixiuxingming;
	}
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：联系方式
	 */
	 
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	
	/**
	 * 获取：联系方式
	 */
	public String getLianxifangshi() {
		return lianxifangshi;
	}
				
	
	/**
	 * 设置：技能描述
	 */
	 
	public void setJinengmiaoshu(String jinengmiaoshu) {
		this.jinengmiaoshu = jinengmiaoshu;
	}
	
	/**
	 * 获取：技能描述
	 */
	public String getJinengmiaoshu() {
		return jinengmiaoshu;
	}
				
	
	/**
	 * 设置：服务范围
	 */
	 
	public void setFuwufanwei(String fuwufanwei) {
		this.fuwufanwei = fuwufanwei;
	}
	
	/**
	 * 获取：服务范围
	 */
	public String getFuwufanwei() {
		return fuwufanwei;
	}
			
}

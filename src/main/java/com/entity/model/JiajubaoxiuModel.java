package com.entity.model;

import com.entity.JiajubaoxiuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 家居报修
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public class JiajubaoxiuModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 家居分类
	 */
	
	private String jiajufenlei;
		
	/**
	 * 家居风格
	 */
	
	private String jiajufengge;
		
	/**
	 * 家居图片
	 */
	
	private String jiajutupian;
		
	/**
	 * 家居材料
	 */
	
	private String jiajucailiao;
		
	/**
	 * 品牌
	 */
	
	private String pinpai;
		
	/**
	 * 规格
	 */
	
	private String guige;
		
	/**
	 * 报修原因
	 */
	
	private String baoxiuyuanyin;
		
	/**
	 * 报修视频
	 */
	
	private String baoxiushipin;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 联系方式
	 */
	
	private String lianxifangshi;
				
	
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
	 * 设置：家居风格
	 */
	 
	public void setJiajufengge(String jiajufengge) {
		this.jiajufengge = jiajufengge;
	}
	
	/**
	 * 获取：家居风格
	 */
	public String getJiajufengge() {
		return jiajufengge;
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
	 * 设置：家居材料
	 */
	 
	public void setJiajucailiao(String jiajucailiao) {
		this.jiajucailiao = jiajucailiao;
	}
	
	/**
	 * 获取：家居材料
	 */
	public String getJiajucailiao() {
		return jiajucailiao;
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
	 * 设置：规格
	 */
	 
	public void setGuige(String guige) {
		this.guige = guige;
	}
	
	/**
	 * 获取：规格
	 */
	public String getGuige() {
		return guige;
	}
				
	
	/**
	 * 设置：报修原因
	 */
	 
	public void setBaoxiuyuanyin(String baoxiuyuanyin) {
		this.baoxiuyuanyin = baoxiuyuanyin;
	}
	
	/**
	 * 获取：报修原因
	 */
	public String getBaoxiuyuanyin() {
		return baoxiuyuanyin;
	}
				
	
	/**
	 * 设置：报修视频
	 */
	 
	public void setBaoxiushipin(String baoxiushipin) {
		this.baoxiushipin = baoxiushipin;
	}
	
	/**
	 * 获取：报修视频
	 */
	public String getBaoxiushipin() {
		return baoxiushipin;
	}
				
	
	/**
	 * 设置：用户账号
	 */
	 
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
				
	
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
			
}

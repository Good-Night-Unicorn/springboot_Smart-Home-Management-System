package com.entity.model;

import com.entity.ZhinenglianjieEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 智能连接
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public class ZhinenglianjieModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 家居分类
	 */
	
	private String jiajufenlei;
		
	/**
	 * 家居图片
	 */
	
	private String jiajutupian;
		
	/**
	 * 家居状态
	 */
	
	private String jiajuzhuangtai;
		
	/**
	 * 家居位置
	 */
	
	private String jiajuweizhi;
		
	/**
	 * wifi连接
	 */
	
	private String wuxian;
		
	/**
	 * 参数值
	 */
	
	private String canshuzhi;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
				
	
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
	 * 设置：家居状态
	 */
	 
	public void setJiajuzhuangtai(String jiajuzhuangtai) {
		this.jiajuzhuangtai = jiajuzhuangtai;
	}
	
	/**
	 * 获取：家居状态
	 */
	public String getJiajuzhuangtai() {
		return jiajuzhuangtai;
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
	 * 设置：wifi连接
	 */
	 
	public void setWuxian(String wuxian) {
		this.wuxian = wuxian;
	}
	
	/**
	 * 获取：wifi连接
	 */
	public String getWuxian() {
		return wuxian;
	}
				
	
	/**
	 * 设置：参数值
	 */
	 
	public void setCanshuzhi(String canshuzhi) {
		this.canshuzhi = canshuzhi;
	}
	
	/**
	 * 获取：参数值
	 */
	public String getCanshuzhi() {
		return canshuzhi;
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
			
}

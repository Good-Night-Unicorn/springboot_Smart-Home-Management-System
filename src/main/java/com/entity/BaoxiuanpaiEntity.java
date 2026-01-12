package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 报修安排
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("baoxiuanpai")
public class BaoxiuanpaiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BaoxiuanpaiEntity() {
		
	}
	
	public BaoxiuanpaiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 家居名称
	 */
					
	private String jiajumingcheng;
	
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
	 * 维修账号
	 */
					
	private String weixiuzhanghao;
	
	/**
	 * 维修姓名
	 */
					
	private String weixiuxingming;
	
	/**
	 * 接单状态
	 */
					
	private String jiedanzhuangtai;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	 * 设置：维修账号
	 */
	public void setWeixiuzhanghao(String weixiuzhanghao) {
		this.weixiuzhanghao = weixiuzhanghao;
	}
	/**
	 * 获取：维修账号
	 */
	public String getWeixiuzhanghao() {
		return weixiuzhanghao;
	}
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
	 * 设置：接单状态
	 */
	public void setJiedanzhuangtai(String jiedanzhuangtai) {
		this.jiedanzhuangtai = jiedanzhuangtai;
	}
	/**
	 * 获取：接单状态
	 */
	public String getJiedanzhuangtai() {
		return jiedanzhuangtai;
	}

}

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
 * 智能连接
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("zhinenglianjie")
public class ZhinenglianjieEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhinenglianjieEntity() {
		
	}
	
	public ZhinenglianjieEntity(T t) {
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

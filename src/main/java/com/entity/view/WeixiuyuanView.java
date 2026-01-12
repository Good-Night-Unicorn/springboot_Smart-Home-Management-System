package com.entity.view;

import com.entity.WeixiuyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 维修员
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:28
 */
@TableName("weixiuyuan")
public class WeixiuyuanView  extends WeixiuyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public WeixiuyuanView(){
	}
 
 	public WeixiuyuanView(WeixiuyuanEntity weixiuyuanEntity){
 	try {
			BeanUtils.copyProperties(this, weixiuyuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

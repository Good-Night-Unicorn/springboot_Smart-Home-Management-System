package com.entity.view;

import com.entity.JiajuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 家居信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:28
 */
@TableName("jiajuxinxi")
public class JiajuxinxiView  extends JiajuxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiajuxinxiView(){
	}
 
 	public JiajuxinxiView(JiajuxinxiEntity jiajuxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, jiajuxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

package com.entity.view;

import com.entity.JiajugoumaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 家居购买
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("jiajugoumai")
public class JiajugoumaiView  extends JiajugoumaiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiajugoumaiView(){
	}
 
 	public JiajugoumaiView(JiajugoumaiEntity jiajugoumaiEntity){
 	try {
			BeanUtils.copyProperties(this, jiajugoumaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

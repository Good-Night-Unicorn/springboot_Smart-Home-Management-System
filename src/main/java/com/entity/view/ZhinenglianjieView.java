package com.entity.view;

import com.entity.ZhinenglianjieEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 智能连接
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("zhinenglianjie")
public class ZhinenglianjieView  extends ZhinenglianjieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZhinenglianjieView(){
	}
 
 	public ZhinenglianjieView(ZhinenglianjieEntity zhinenglianjieEntity){
 	try {
			BeanUtils.copyProperties(this, zhinenglianjieEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

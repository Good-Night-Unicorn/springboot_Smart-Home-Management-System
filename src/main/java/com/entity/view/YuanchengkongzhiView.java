package com.entity.view;

import com.entity.YuanchengkongzhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 远程控制
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("yuanchengkongzhi")
public class YuanchengkongzhiView  extends YuanchengkongzhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YuanchengkongzhiView(){
	}
 
 	public YuanchengkongzhiView(YuanchengkongzhiEntity yuanchengkongzhiEntity){
 	try {
			BeanUtils.copyProperties(this, yuanchengkongzhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

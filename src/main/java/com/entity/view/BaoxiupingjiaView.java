package com.entity.view;

import com.entity.BaoxiupingjiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 报修评价
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@TableName("baoxiupingjia")
public class BaoxiupingjiaView  extends BaoxiupingjiaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BaoxiupingjiaView(){
	}
 
 	public BaoxiupingjiaView(BaoxiupingjiaEntity baoxiupingjiaEntity){
 	try {
			BeanUtils.copyProperties(this, baoxiupingjiaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}

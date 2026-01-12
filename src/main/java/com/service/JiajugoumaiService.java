package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiajugoumaiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiajugoumaiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiajugoumaiView;


/**
 * 家居购买
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface JiajugoumaiService extends IService<JiajugoumaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiajugoumaiVO> selectListVO(Wrapper<JiajugoumaiEntity> wrapper);
   	
   	JiajugoumaiVO selectVO(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);
   	
   	List<JiajugoumaiView> selectListView(Wrapper<JiajugoumaiEntity> wrapper);
   	
   	JiajugoumaiView selectView(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiajugoumaiEntity> wrapper);

   	

}


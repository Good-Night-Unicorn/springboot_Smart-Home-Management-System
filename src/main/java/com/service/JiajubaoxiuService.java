package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiajubaoxiuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiajubaoxiuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiajubaoxiuView;


/**
 * 家居报修
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface JiajubaoxiuService extends IService<JiajubaoxiuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiajubaoxiuVO> selectListVO(Wrapper<JiajubaoxiuEntity> wrapper);
   	
   	JiajubaoxiuVO selectVO(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);
   	
   	List<JiajubaoxiuView> selectListView(Wrapper<JiajubaoxiuEntity> wrapper);
   	
   	JiajubaoxiuView selectView(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiajubaoxiuEntity> wrapper);

   	

}


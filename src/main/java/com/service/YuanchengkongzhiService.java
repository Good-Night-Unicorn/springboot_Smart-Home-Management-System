package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuanchengkongzhiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YuanchengkongzhiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YuanchengkongzhiView;


/**
 * 远程控制
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface YuanchengkongzhiService extends IService<YuanchengkongzhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuanchengkongzhiVO> selectListVO(Wrapper<YuanchengkongzhiEntity> wrapper);
   	
   	YuanchengkongzhiVO selectVO(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);
   	
   	List<YuanchengkongzhiView> selectListView(Wrapper<YuanchengkongzhiEntity> wrapper);
   	
   	YuanchengkongzhiView selectView(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuanchengkongzhiEntity> wrapper);

   	

}


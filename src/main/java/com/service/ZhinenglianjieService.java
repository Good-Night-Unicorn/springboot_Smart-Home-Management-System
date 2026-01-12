package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhinenglianjieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhinenglianjieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhinenglianjieView;


/**
 * 智能连接
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface ZhinenglianjieService extends IService<ZhinenglianjieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhinenglianjieVO> selectListVO(Wrapper<ZhinenglianjieEntity> wrapper);
   	
   	ZhinenglianjieVO selectVO(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);
   	
   	List<ZhinenglianjieView> selectListView(Wrapper<ZhinenglianjieEntity> wrapper);
   	
   	ZhinenglianjieView selectView(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhinenglianjieEntity> wrapper);

   	

}


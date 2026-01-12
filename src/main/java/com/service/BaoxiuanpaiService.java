package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaoxiuanpaiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaoxiuanpaiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaoxiuanpaiView;


/**
 * 报修安排
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiuanpaiService extends IService<BaoxiuanpaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaoxiuanpaiVO> selectListVO(Wrapper<BaoxiuanpaiEntity> wrapper);
   	
   	BaoxiuanpaiVO selectVO(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);
   	
   	List<BaoxiuanpaiView> selectListView(Wrapper<BaoxiuanpaiEntity> wrapper);
   	
   	BaoxiuanpaiView selectView(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaoxiuanpaiEntity> wrapper);

   	

}


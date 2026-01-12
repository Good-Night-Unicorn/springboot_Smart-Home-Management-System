package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaoxiupingjiaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaoxiupingjiaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaoxiupingjiaView;


/**
 * 报修评价
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiupingjiaService extends IService<BaoxiupingjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaoxiupingjiaVO> selectListVO(Wrapper<BaoxiupingjiaEntity> wrapper);
   	
   	BaoxiupingjiaVO selectVO(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);
   	
   	List<BaoxiupingjiaView> selectListView(Wrapper<BaoxiupingjiaEntity> wrapper);
   	
   	BaoxiupingjiaView selectView(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaoxiupingjiaEntity> wrapper);

   	

}


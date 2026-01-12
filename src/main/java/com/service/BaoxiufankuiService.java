package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaoxiufankuiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaoxiufankuiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaoxiufankuiView;


/**
 * 报修反馈
 *
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiufankuiService extends IService<BaoxiufankuiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaoxiufankuiVO> selectListVO(Wrapper<BaoxiufankuiEntity> wrapper);
   	
   	BaoxiufankuiVO selectVO(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);
   	
   	List<BaoxiufankuiView> selectListView(Wrapper<BaoxiufankuiEntity> wrapper);
   	
   	BaoxiufankuiView selectView(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaoxiufankuiEntity> wrapper);

   	

}


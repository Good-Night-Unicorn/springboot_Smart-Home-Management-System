package com.dao;

import com.entity.BaoxiufankuiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaoxiufankuiVO;
import com.entity.view.BaoxiufankuiView;


/**
 * 报修反馈
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiufankuiDao extends BaseMapper<BaoxiufankuiEntity> {
	
	List<BaoxiufankuiVO> selectListVO(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);
	
	BaoxiufankuiVO selectVO(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);
	
	List<BaoxiufankuiView> selectListView(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);

	List<BaoxiufankuiView> selectListView(Pagination page,@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);

	
	BaoxiufankuiView selectView(@Param("ew") Wrapper<BaoxiufankuiEntity> wrapper);
	

}

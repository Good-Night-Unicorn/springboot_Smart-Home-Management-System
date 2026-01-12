package com.dao;

import com.entity.YuanchengkongzhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuanchengkongzhiVO;
import com.entity.view.YuanchengkongzhiView;


/**
 * 远程控制
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface YuanchengkongzhiDao extends BaseMapper<YuanchengkongzhiEntity> {
	
	List<YuanchengkongzhiVO> selectListVO(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);
	
	YuanchengkongzhiVO selectVO(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);
	
	List<YuanchengkongzhiView> selectListView(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);

	List<YuanchengkongzhiView> selectListView(Pagination page,@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);

	
	YuanchengkongzhiView selectView(@Param("ew") Wrapper<YuanchengkongzhiEntity> wrapper);
	

}

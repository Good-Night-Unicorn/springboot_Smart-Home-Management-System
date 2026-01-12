package com.dao;

import com.entity.JiajubaoxiuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiajubaoxiuVO;
import com.entity.view.JiajubaoxiuView;


/**
 * 家居报修
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface JiajubaoxiuDao extends BaseMapper<JiajubaoxiuEntity> {
	
	List<JiajubaoxiuVO> selectListVO(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);
	
	JiajubaoxiuVO selectVO(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);
	
	List<JiajubaoxiuView> selectListView(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);

	List<JiajubaoxiuView> selectListView(Pagination page,@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);

	
	JiajubaoxiuView selectView(@Param("ew") Wrapper<JiajubaoxiuEntity> wrapper);
	

}

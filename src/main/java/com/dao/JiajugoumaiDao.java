package com.dao;

import com.entity.JiajugoumaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiajugoumaiVO;
import com.entity.view.JiajugoumaiView;


/**
 * 家居购买
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface JiajugoumaiDao extends BaseMapper<JiajugoumaiEntity> {
	
	List<JiajugoumaiVO> selectListVO(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);
	
	JiajugoumaiVO selectVO(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);
	
	List<JiajugoumaiView> selectListView(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);

	List<JiajugoumaiView> selectListView(Pagination page,@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);

	
	JiajugoumaiView selectView(@Param("ew") Wrapper<JiajugoumaiEntity> wrapper);
	

}

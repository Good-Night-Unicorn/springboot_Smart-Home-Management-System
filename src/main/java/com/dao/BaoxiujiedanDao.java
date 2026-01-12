package com.dao;

import com.entity.BaoxiujiedanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaoxiujiedanVO;
import com.entity.view.BaoxiujiedanView;


/**
 * 报修接单
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiujiedanDao extends BaseMapper<BaoxiujiedanEntity> {
	
	List<BaoxiujiedanVO> selectListVO(@Param("ew") Wrapper<BaoxiujiedanEntity> wrapper);
	
	BaoxiujiedanVO selectVO(@Param("ew") Wrapper<BaoxiujiedanEntity> wrapper);
	
	List<BaoxiujiedanView> selectListView(@Param("ew") Wrapper<BaoxiujiedanEntity> wrapper);

	List<BaoxiujiedanView> selectListView(Pagination page,@Param("ew") Wrapper<BaoxiujiedanEntity> wrapper);

	
	BaoxiujiedanView selectView(@Param("ew") Wrapper<BaoxiujiedanEntity> wrapper);
	

}

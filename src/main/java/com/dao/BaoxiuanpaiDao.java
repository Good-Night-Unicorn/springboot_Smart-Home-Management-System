package com.dao;

import com.entity.BaoxiuanpaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaoxiuanpaiVO;
import com.entity.view.BaoxiuanpaiView;


/**
 * 报修安排
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiuanpaiDao extends BaseMapper<BaoxiuanpaiEntity> {
	
	List<BaoxiuanpaiVO> selectListVO(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);
	
	BaoxiuanpaiVO selectVO(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);
	
	List<BaoxiuanpaiView> selectListView(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);

	List<BaoxiuanpaiView> selectListView(Pagination page,@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);

	
	BaoxiuanpaiView selectView(@Param("ew") Wrapper<BaoxiuanpaiEntity> wrapper);
	

}

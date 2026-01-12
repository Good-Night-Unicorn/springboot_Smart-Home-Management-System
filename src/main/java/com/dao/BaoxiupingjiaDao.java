package com.dao;

import com.entity.BaoxiupingjiaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaoxiupingjiaVO;
import com.entity.view.BaoxiupingjiaView;


/**
 * 报修评价
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface BaoxiupingjiaDao extends BaseMapper<BaoxiupingjiaEntity> {
	
	List<BaoxiupingjiaVO> selectListVO(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);
	
	BaoxiupingjiaVO selectVO(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);
	
	List<BaoxiupingjiaView> selectListView(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);

	List<BaoxiupingjiaView> selectListView(Pagination page,@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);

	
	BaoxiupingjiaView selectView(@Param("ew") Wrapper<BaoxiupingjiaEntity> wrapper);
	

}

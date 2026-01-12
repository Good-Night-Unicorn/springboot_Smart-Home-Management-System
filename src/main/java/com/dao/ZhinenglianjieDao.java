package com.dao;

import com.entity.ZhinenglianjieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhinenglianjieVO;
import com.entity.view.ZhinenglianjieView;


/**
 * 智能连接
 * 
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
public interface ZhinenglianjieDao extends BaseMapper<ZhinenglianjieEntity> {
	
	List<ZhinenglianjieVO> selectListVO(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);
	
	ZhinenglianjieVO selectVO(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);
	
	List<ZhinenglianjieView> selectListView(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);

	List<ZhinenglianjieView> selectListView(Pagination page,@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);

	
	ZhinenglianjieView selectView(@Param("ew") Wrapper<ZhinenglianjieEntity> wrapper);
	

}

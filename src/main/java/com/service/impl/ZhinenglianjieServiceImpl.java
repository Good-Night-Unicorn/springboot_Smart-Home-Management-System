package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ZhinenglianjieDao;
import com.entity.ZhinenglianjieEntity;
import com.service.ZhinenglianjieService;
import com.entity.vo.ZhinenglianjieVO;
import com.entity.view.ZhinenglianjieView;

@Service("zhinenglianjieService")
public class ZhinenglianjieServiceImpl extends ServiceImpl<ZhinenglianjieDao, ZhinenglianjieEntity> implements ZhinenglianjieService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhinenglianjieEntity> page = this.selectPage(
                new Query<ZhinenglianjieEntity>(params).getPage(),
                new EntityWrapper<ZhinenglianjieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhinenglianjieEntity> wrapper) {
		  Page<ZhinenglianjieView> page =new Query<ZhinenglianjieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhinenglianjieVO> selectListVO(Wrapper<ZhinenglianjieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhinenglianjieVO selectVO(Wrapper<ZhinenglianjieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhinenglianjieView> selectListView(Wrapper<ZhinenglianjieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhinenglianjieView selectView(Wrapper<ZhinenglianjieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

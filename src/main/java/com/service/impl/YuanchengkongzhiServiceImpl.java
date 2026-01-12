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


import com.dao.YuanchengkongzhiDao;
import com.entity.YuanchengkongzhiEntity;
import com.service.YuanchengkongzhiService;
import com.entity.vo.YuanchengkongzhiVO;
import com.entity.view.YuanchengkongzhiView;

@Service("yuanchengkongzhiService")
public class YuanchengkongzhiServiceImpl extends ServiceImpl<YuanchengkongzhiDao, YuanchengkongzhiEntity> implements YuanchengkongzhiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuanchengkongzhiEntity> page = this.selectPage(
                new Query<YuanchengkongzhiEntity>(params).getPage(),
                new EntityWrapper<YuanchengkongzhiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuanchengkongzhiEntity> wrapper) {
		  Page<YuanchengkongzhiView> page =new Query<YuanchengkongzhiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YuanchengkongzhiVO> selectListVO(Wrapper<YuanchengkongzhiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YuanchengkongzhiVO selectVO(Wrapper<YuanchengkongzhiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YuanchengkongzhiView> selectListView(Wrapper<YuanchengkongzhiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuanchengkongzhiView selectView(Wrapper<YuanchengkongzhiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

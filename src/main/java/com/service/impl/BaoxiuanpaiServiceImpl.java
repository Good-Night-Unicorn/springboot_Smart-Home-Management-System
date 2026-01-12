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


import com.dao.BaoxiuanpaiDao;
import com.entity.BaoxiuanpaiEntity;
import com.service.BaoxiuanpaiService;
import com.entity.vo.BaoxiuanpaiVO;
import com.entity.view.BaoxiuanpaiView;

@Service("baoxiuanpaiService")
public class BaoxiuanpaiServiceImpl extends ServiceImpl<BaoxiuanpaiDao, BaoxiuanpaiEntity> implements BaoxiuanpaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaoxiuanpaiEntity> page = this.selectPage(
                new Query<BaoxiuanpaiEntity>(params).getPage(),
                new EntityWrapper<BaoxiuanpaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaoxiuanpaiEntity> wrapper) {
		  Page<BaoxiuanpaiView> page =new Query<BaoxiuanpaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaoxiuanpaiVO> selectListVO(Wrapper<BaoxiuanpaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaoxiuanpaiVO selectVO(Wrapper<BaoxiuanpaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaoxiuanpaiView> selectListView(Wrapper<BaoxiuanpaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaoxiuanpaiView selectView(Wrapper<BaoxiuanpaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

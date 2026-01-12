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


import com.dao.BaoxiufankuiDao;
import com.entity.BaoxiufankuiEntity;
import com.service.BaoxiufankuiService;
import com.entity.vo.BaoxiufankuiVO;
import com.entity.view.BaoxiufankuiView;

@Service("baoxiufankuiService")
public class BaoxiufankuiServiceImpl extends ServiceImpl<BaoxiufankuiDao, BaoxiufankuiEntity> implements BaoxiufankuiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaoxiufankuiEntity> page = this.selectPage(
                new Query<BaoxiufankuiEntity>(params).getPage(),
                new EntityWrapper<BaoxiufankuiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaoxiufankuiEntity> wrapper) {
		  Page<BaoxiufankuiView> page =new Query<BaoxiufankuiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaoxiufankuiVO> selectListVO(Wrapper<BaoxiufankuiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaoxiufankuiVO selectVO(Wrapper<BaoxiufankuiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaoxiufankuiView> selectListView(Wrapper<BaoxiufankuiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaoxiufankuiView selectView(Wrapper<BaoxiufankuiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

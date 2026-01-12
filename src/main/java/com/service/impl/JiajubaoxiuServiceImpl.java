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


import com.dao.JiajubaoxiuDao;
import com.entity.JiajubaoxiuEntity;
import com.service.JiajubaoxiuService;
import com.entity.vo.JiajubaoxiuVO;
import com.entity.view.JiajubaoxiuView;

@Service("jiajubaoxiuService")
public class JiajubaoxiuServiceImpl extends ServiceImpl<JiajubaoxiuDao, JiajubaoxiuEntity> implements JiajubaoxiuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiajubaoxiuEntity> page = this.selectPage(
                new Query<JiajubaoxiuEntity>(params).getPage(),
                new EntityWrapper<JiajubaoxiuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiajubaoxiuEntity> wrapper) {
		  Page<JiajubaoxiuView> page =new Query<JiajubaoxiuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JiajubaoxiuVO> selectListVO(Wrapper<JiajubaoxiuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiajubaoxiuVO selectVO(Wrapper<JiajubaoxiuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiajubaoxiuView> selectListView(Wrapper<JiajubaoxiuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiajubaoxiuView selectView(Wrapper<JiajubaoxiuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

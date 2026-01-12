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


import com.dao.JiajugoumaiDao;
import com.entity.JiajugoumaiEntity;
import com.service.JiajugoumaiService;
import com.entity.vo.JiajugoumaiVO;
import com.entity.view.JiajugoumaiView;

@Service("jiajugoumaiService")
public class JiajugoumaiServiceImpl extends ServiceImpl<JiajugoumaiDao, JiajugoumaiEntity> implements JiajugoumaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiajugoumaiEntity> page = this.selectPage(
                new Query<JiajugoumaiEntity>(params).getPage(),
                new EntityWrapper<JiajugoumaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiajugoumaiEntity> wrapper) {
		  Page<JiajugoumaiView> page =new Query<JiajugoumaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JiajugoumaiVO> selectListVO(Wrapper<JiajugoumaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiajugoumaiVO selectVO(Wrapper<JiajugoumaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiajugoumaiView> selectListView(Wrapper<JiajugoumaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiajugoumaiView selectView(Wrapper<JiajugoumaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

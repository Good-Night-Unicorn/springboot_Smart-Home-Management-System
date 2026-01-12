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


import com.dao.BaoxiupingjiaDao;
import com.entity.BaoxiupingjiaEntity;
import com.service.BaoxiupingjiaService;
import com.entity.vo.BaoxiupingjiaVO;
import com.entity.view.BaoxiupingjiaView;

@Service("baoxiupingjiaService")
public class BaoxiupingjiaServiceImpl extends ServiceImpl<BaoxiupingjiaDao, BaoxiupingjiaEntity> implements BaoxiupingjiaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaoxiupingjiaEntity> page = this.selectPage(
                new Query<BaoxiupingjiaEntity>(params).getPage(),
                new EntityWrapper<BaoxiupingjiaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaoxiupingjiaEntity> wrapper) {
		  Page<BaoxiupingjiaView> page =new Query<BaoxiupingjiaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaoxiupingjiaVO> selectListVO(Wrapper<BaoxiupingjiaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaoxiupingjiaVO selectVO(Wrapper<BaoxiupingjiaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaoxiupingjiaView> selectListView(Wrapper<BaoxiupingjiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaoxiupingjiaView selectView(Wrapper<BaoxiupingjiaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

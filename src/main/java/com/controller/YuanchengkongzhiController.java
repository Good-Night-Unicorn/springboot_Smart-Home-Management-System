package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YuanchengkongzhiEntity;
import com.entity.view.YuanchengkongzhiView;

import com.service.YuanchengkongzhiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 远程控制
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/yuanchengkongzhi")
public class YuanchengkongzhiController {
    @Autowired
    private YuanchengkongzhiService yuanchengkongzhiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuanchengkongzhiEntity yuanchengkongzhi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yuanchengkongzhi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YuanchengkongzhiEntity> ew = new EntityWrapper<YuanchengkongzhiEntity>();



		PageUtils page = yuanchengkongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuanchengkongzhi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuanchengkongzhiEntity yuanchengkongzhi, 
		HttpServletRequest request){
        EntityWrapper<YuanchengkongzhiEntity> ew = new EntityWrapper<YuanchengkongzhiEntity>();

		PageUtils page = yuanchengkongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuanchengkongzhi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuanchengkongzhiEntity yuanchengkongzhi){
       	EntityWrapper<YuanchengkongzhiEntity> ew = new EntityWrapper<YuanchengkongzhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuanchengkongzhi, "yuanchengkongzhi")); 
        return R.ok().put("data", yuanchengkongzhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuanchengkongzhiEntity yuanchengkongzhi){
        EntityWrapper< YuanchengkongzhiEntity> ew = new EntityWrapper< YuanchengkongzhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuanchengkongzhi, "yuanchengkongzhi")); 
		YuanchengkongzhiView yuanchengkongzhiView =  yuanchengkongzhiService.selectView(ew);
		return R.ok("查询远程控制成功").put("data", yuanchengkongzhiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuanchengkongzhiEntity yuanchengkongzhi = yuanchengkongzhiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yuanchengkongzhi,deSens);
        return R.ok().put("data", yuanchengkongzhi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuanchengkongzhiEntity yuanchengkongzhi = yuanchengkongzhiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yuanchengkongzhi,deSens);
        return R.ok().put("data", yuanchengkongzhi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuanchengkongzhiEntity yuanchengkongzhi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuanchengkongzhi);
        yuanchengkongzhiService.insert(yuanchengkongzhi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuanchengkongzhiEntity yuanchengkongzhi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuanchengkongzhi);
        yuanchengkongzhiService.insert(yuanchengkongzhi);
        return R.ok().put("data",yuanchengkongzhi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YuanchengkongzhiEntity yuanchengkongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuanchengkongzhi);
        //全部更新
        yuanchengkongzhiService.updateById(yuanchengkongzhi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuanchengkongzhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

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

import com.entity.ZhinenglianjieEntity;
import com.entity.view.ZhinenglianjieView;

import com.service.ZhinenglianjieService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 智能连接
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/zhinenglianjie")
public class ZhinenglianjieController {
    @Autowired
    private ZhinenglianjieService zhinenglianjieService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhinenglianjieEntity zhinenglianjie,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			zhinenglianjie.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhinenglianjieEntity> ew = new EntityWrapper<ZhinenglianjieEntity>();



		PageUtils page = zhinenglianjieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhinenglianjie), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhinenglianjieEntity zhinenglianjie, 
		HttpServletRequest request){
        EntityWrapper<ZhinenglianjieEntity> ew = new EntityWrapper<ZhinenglianjieEntity>();

		PageUtils page = zhinenglianjieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhinenglianjie), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhinenglianjieEntity zhinenglianjie){
       	EntityWrapper<ZhinenglianjieEntity> ew = new EntityWrapper<ZhinenglianjieEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhinenglianjie, "zhinenglianjie")); 
        return R.ok().put("data", zhinenglianjieService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhinenglianjieEntity zhinenglianjie){
        EntityWrapper< ZhinenglianjieEntity> ew = new EntityWrapper< ZhinenglianjieEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhinenglianjie, "zhinenglianjie")); 
		ZhinenglianjieView zhinenglianjieView =  zhinenglianjieService.selectView(ew);
		return R.ok("查询智能连接成功").put("data", zhinenglianjieView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhinenglianjieEntity zhinenglianjie = zhinenglianjieService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhinenglianjie,deSens);
        return R.ok().put("data", zhinenglianjie);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhinenglianjieEntity zhinenglianjie = zhinenglianjieService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhinenglianjie,deSens);
        return R.ok().put("data", zhinenglianjie);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhinenglianjieEntity zhinenglianjie, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhinenglianjie);
        zhinenglianjieService.insert(zhinenglianjie);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhinenglianjieEntity zhinenglianjie, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhinenglianjie);
        zhinenglianjieService.insert(zhinenglianjie);
        return R.ok().put("data",zhinenglianjie.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhinenglianjieEntity zhinenglianjie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhinenglianjie);
        //全部更新
        zhinenglianjieService.updateById(zhinenglianjie);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhinenglianjieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

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

import com.entity.BaoxiuanpaiEntity;
import com.entity.view.BaoxiuanpaiView;

import com.service.BaoxiuanpaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报修安排
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/baoxiuanpai")
public class BaoxiuanpaiController {
    @Autowired
    private BaoxiuanpaiService baoxiuanpaiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaoxiuanpaiEntity baoxiuanpai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			baoxiuanpai.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("weixiuyuan")) {
			baoxiuanpai.setWeixiuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BaoxiuanpaiEntity> ew = new EntityWrapper<BaoxiuanpaiEntity>();



		PageUtils page = baoxiuanpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiuanpai), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaoxiuanpaiEntity baoxiuanpai, 
		HttpServletRequest request){
        EntityWrapper<BaoxiuanpaiEntity> ew = new EntityWrapper<BaoxiuanpaiEntity>();

		PageUtils page = baoxiuanpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiuanpai), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaoxiuanpaiEntity baoxiuanpai){
       	EntityWrapper<BaoxiuanpaiEntity> ew = new EntityWrapper<BaoxiuanpaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baoxiuanpai, "baoxiuanpai")); 
        return R.ok().put("data", baoxiuanpaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaoxiuanpaiEntity baoxiuanpai){
        EntityWrapper< BaoxiuanpaiEntity> ew = new EntityWrapper< BaoxiuanpaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baoxiuanpai, "baoxiuanpai")); 
		BaoxiuanpaiView baoxiuanpaiView =  baoxiuanpaiService.selectView(ew);
		return R.ok("查询报修安排成功").put("data", baoxiuanpaiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaoxiuanpaiEntity baoxiuanpai = baoxiuanpaiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiuanpai,deSens);
        return R.ok().put("data", baoxiuanpai);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaoxiuanpaiEntity baoxiuanpai = baoxiuanpaiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiuanpai,deSens);
        return R.ok().put("data", baoxiuanpai);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaoxiuanpaiEntity baoxiuanpai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiuanpai);
        baoxiuanpaiService.insert(baoxiuanpai);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaoxiuanpaiEntity baoxiuanpai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiuanpai);
        baoxiuanpaiService.insert(baoxiuanpai);
        return R.ok().put("data",baoxiuanpai.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaoxiuanpaiEntity baoxiuanpai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxiuanpai);
        //全部更新
        baoxiuanpaiService.updateById(baoxiuanpai);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baoxiuanpaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

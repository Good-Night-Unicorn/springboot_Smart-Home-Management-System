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

import com.entity.BaoxiufankuiEntity;
import com.entity.view.BaoxiufankuiView;

import com.service.BaoxiufankuiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报修反馈
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/baoxiufankui")
public class BaoxiufankuiController {
    @Autowired
    private BaoxiufankuiService baoxiufankuiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaoxiufankuiEntity baoxiufankui,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			baoxiufankui.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("weixiuyuan")) {
			baoxiufankui.setWeixiuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BaoxiufankuiEntity> ew = new EntityWrapper<BaoxiufankuiEntity>();



		PageUtils page = baoxiufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiufankui), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaoxiufankuiEntity baoxiufankui, 
		HttpServletRequest request){
        EntityWrapper<BaoxiufankuiEntity> ew = new EntityWrapper<BaoxiufankuiEntity>();

		PageUtils page = baoxiufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiufankui), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaoxiufankuiEntity baoxiufankui){
       	EntityWrapper<BaoxiufankuiEntity> ew = new EntityWrapper<BaoxiufankuiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baoxiufankui, "baoxiufankui")); 
        return R.ok().put("data", baoxiufankuiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaoxiufankuiEntity baoxiufankui){
        EntityWrapper< BaoxiufankuiEntity> ew = new EntityWrapper< BaoxiufankuiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baoxiufankui, "baoxiufankui")); 
		BaoxiufankuiView baoxiufankuiView =  baoxiufankuiService.selectView(ew);
		return R.ok("查询报修反馈成功").put("data", baoxiufankuiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaoxiufankuiEntity baoxiufankui = baoxiufankuiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiufankui,deSens);
        return R.ok().put("data", baoxiufankui);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaoxiufankuiEntity baoxiufankui = baoxiufankuiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiufankui,deSens);
        return R.ok().put("data", baoxiufankui);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaoxiufankuiEntity baoxiufankui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiufankui);
        baoxiufankuiService.insert(baoxiufankui);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaoxiufankuiEntity baoxiufankui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiufankui);
        baoxiufankuiService.insert(baoxiufankui);
        return R.ok().put("data",baoxiufankui.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaoxiufankuiEntity baoxiufankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxiufankui);
        //全部更新
        baoxiufankuiService.updateById(baoxiufankui);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baoxiufankuiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

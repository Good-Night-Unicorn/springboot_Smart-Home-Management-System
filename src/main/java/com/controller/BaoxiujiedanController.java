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

import com.entity.BaoxiujiedanEntity;
import com.entity.view.BaoxiujiedanView;

import com.service.BaoxiujiedanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报修接单
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/baoxiujiedan")
public class BaoxiujiedanController {
    @Autowired
    private BaoxiujiedanService baoxiujiedanService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaoxiujiedanEntity baoxiujiedan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			baoxiujiedan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("weixiuyuan")) {
			baoxiujiedan.setWeixiuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BaoxiujiedanEntity> ew = new EntityWrapper<BaoxiujiedanEntity>();



		PageUtils page = baoxiujiedanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiujiedan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaoxiujiedanEntity baoxiujiedan, 
		HttpServletRequest request){
        EntityWrapper<BaoxiujiedanEntity> ew = new EntityWrapper<BaoxiujiedanEntity>();

		PageUtils page = baoxiujiedanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiujiedan), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaoxiujiedanEntity baoxiujiedan){
       	EntityWrapper<BaoxiujiedanEntity> ew = new EntityWrapper<BaoxiujiedanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baoxiujiedan, "baoxiujiedan")); 
        return R.ok().put("data", baoxiujiedanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaoxiujiedanEntity baoxiujiedan){
        EntityWrapper< BaoxiujiedanEntity> ew = new EntityWrapper< BaoxiujiedanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baoxiujiedan, "baoxiujiedan")); 
		BaoxiujiedanView baoxiujiedanView =  baoxiujiedanService.selectView(ew);
		return R.ok("查询报修接单成功").put("data", baoxiujiedanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaoxiujiedanEntity baoxiujiedan = baoxiujiedanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiujiedan,deSens);
        return R.ok().put("data", baoxiujiedan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaoxiujiedanEntity baoxiujiedan = baoxiujiedanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiujiedan,deSens);
        return R.ok().put("data", baoxiujiedan);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaoxiujiedanEntity baoxiujiedan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiujiedan);
        baoxiujiedanService.insert(baoxiujiedan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaoxiujiedanEntity baoxiujiedan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiujiedan);
        baoxiujiedanService.insert(baoxiujiedan);
        return R.ok().put("data",baoxiujiedan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaoxiujiedanEntity baoxiujiedan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxiujiedan);
        //全部更新
        baoxiujiedanService.updateById(baoxiujiedan);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baoxiujiedanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

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

import com.entity.JiajubaoxiuEntity;
import com.entity.view.JiajubaoxiuView;

import com.service.JiajubaoxiuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 家居报修
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/jiajubaoxiu")
public class JiajubaoxiuController {
    @Autowired
    private JiajubaoxiuService jiajubaoxiuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiajubaoxiuEntity jiajubaoxiu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			jiajubaoxiu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JiajubaoxiuEntity> ew = new EntityWrapper<JiajubaoxiuEntity>();



		PageUtils page = jiajubaoxiuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajubaoxiu), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiajubaoxiuEntity jiajubaoxiu, 
		HttpServletRequest request){
        EntityWrapper<JiajubaoxiuEntity> ew = new EntityWrapper<JiajubaoxiuEntity>();

		PageUtils page = jiajubaoxiuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajubaoxiu), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiajubaoxiuEntity jiajubaoxiu){
       	EntityWrapper<JiajubaoxiuEntity> ew = new EntityWrapper<JiajubaoxiuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiajubaoxiu, "jiajubaoxiu")); 
        return R.ok().put("data", jiajubaoxiuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiajubaoxiuEntity jiajubaoxiu){
        EntityWrapper< JiajubaoxiuEntity> ew = new EntityWrapper< JiajubaoxiuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiajubaoxiu, "jiajubaoxiu")); 
		JiajubaoxiuView jiajubaoxiuView =  jiajubaoxiuService.selectView(ew);
		return R.ok("查询家居报修成功").put("data", jiajubaoxiuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiajubaoxiuEntity jiajubaoxiu = jiajubaoxiuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajubaoxiu,deSens);
        return R.ok().put("data", jiajubaoxiu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiajubaoxiuEntity jiajubaoxiu = jiajubaoxiuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajubaoxiu,deSens);
        return R.ok().put("data", jiajubaoxiu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiajubaoxiuEntity jiajubaoxiu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajubaoxiu);
        jiajubaoxiuService.insert(jiajubaoxiu);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiajubaoxiuEntity jiajubaoxiu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajubaoxiu);
        jiajubaoxiuService.insert(jiajubaoxiu);
        return R.ok().put("data",jiajubaoxiu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiajubaoxiuEntity jiajubaoxiu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiajubaoxiu);
        //全部更新
        jiajubaoxiuService.updateById(jiajubaoxiu);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiajubaoxiuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

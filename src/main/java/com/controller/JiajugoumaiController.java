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

import com.entity.JiajugoumaiEntity;
import com.entity.view.JiajugoumaiView;

import com.service.JiajugoumaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 家居购买
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/jiajugoumai")
public class JiajugoumaiController {
    @Autowired
    private JiajugoumaiService jiajugoumaiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiajugoumaiEntity jiajugoumai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			jiajugoumai.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JiajugoumaiEntity> ew = new EntityWrapper<JiajugoumaiEntity>();



		PageUtils page = jiajugoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajugoumai), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiajugoumaiEntity jiajugoumai, 
		HttpServletRequest request){
        EntityWrapper<JiajugoumaiEntity> ew = new EntityWrapper<JiajugoumaiEntity>();

		PageUtils page = jiajugoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajugoumai), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiajugoumaiEntity jiajugoumai){
       	EntityWrapper<JiajugoumaiEntity> ew = new EntityWrapper<JiajugoumaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiajugoumai, "jiajugoumai")); 
        return R.ok().put("data", jiajugoumaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiajugoumaiEntity jiajugoumai){
        EntityWrapper< JiajugoumaiEntity> ew = new EntityWrapper< JiajugoumaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiajugoumai, "jiajugoumai")); 
		JiajugoumaiView jiajugoumaiView =  jiajugoumaiService.selectView(ew);
		return R.ok("查询家居购买成功").put("data", jiajugoumaiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiajugoumaiEntity jiajugoumai = jiajugoumaiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajugoumai,deSens);
        return R.ok().put("data", jiajugoumai);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiajugoumaiEntity jiajugoumai = jiajugoumaiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajugoumai,deSens);
        return R.ok().put("data", jiajugoumai);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiajugoumaiEntity jiajugoumai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajugoumai);
        jiajugoumaiService.insert(jiajugoumai);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiajugoumaiEntity jiajugoumai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajugoumai);
        jiajugoumaiService.insert(jiajugoumai);
        return R.ok().put("data",jiajugoumai.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiajugoumaiEntity jiajugoumai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiajugoumai);
        //全部更新
        jiajugoumaiService.updateById(jiajugoumai);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiajugoumaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

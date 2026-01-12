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

import com.entity.BaoxiupingjiaEntity;
import com.entity.view.BaoxiupingjiaView;

import com.service.BaoxiupingjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报修评价
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:29
 */
@RestController
@RequestMapping("/baoxiupingjia")
public class BaoxiupingjiaController {
    @Autowired
    private BaoxiupingjiaService baoxiupingjiaService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaoxiupingjiaEntity baoxiupingjia,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			baoxiupingjia.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("weixiuyuan")) {
			baoxiupingjia.setWeixiuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BaoxiupingjiaEntity> ew = new EntityWrapper<BaoxiupingjiaEntity>();



		PageUtils page = baoxiupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiupingjia), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaoxiupingjiaEntity baoxiupingjia, 
		HttpServletRequest request){
        EntityWrapper<BaoxiupingjiaEntity> ew = new EntityWrapper<BaoxiupingjiaEntity>();

		PageUtils page = baoxiupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baoxiupingjia), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaoxiupingjiaEntity baoxiupingjia){
       	EntityWrapper<BaoxiupingjiaEntity> ew = new EntityWrapper<BaoxiupingjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baoxiupingjia, "baoxiupingjia")); 
        return R.ok().put("data", baoxiupingjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaoxiupingjiaEntity baoxiupingjia){
        EntityWrapper< BaoxiupingjiaEntity> ew = new EntityWrapper< BaoxiupingjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baoxiupingjia, "baoxiupingjia")); 
		BaoxiupingjiaView baoxiupingjiaView =  baoxiupingjiaService.selectView(ew);
		return R.ok("查询报修评价成功").put("data", baoxiupingjiaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaoxiupingjiaEntity baoxiupingjia = baoxiupingjiaService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiupingjia,deSens);
        return R.ok().put("data", baoxiupingjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaoxiupingjiaEntity baoxiupingjia = baoxiupingjiaService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(baoxiupingjia,deSens);
        return R.ok().put("data", baoxiupingjia);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaoxiupingjiaEntity baoxiupingjia, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiupingjia);
        baoxiupingjiaService.insert(baoxiupingjia);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaoxiupingjiaEntity baoxiupingjia, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baoxiupingjia);
        baoxiupingjiaService.insert(baoxiupingjia);
        return R.ok().put("data",baoxiupingjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaoxiupingjiaEntity baoxiupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baoxiupingjia);
        //全部更新
        baoxiupingjiaService.updateById(baoxiupingjia);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baoxiupingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

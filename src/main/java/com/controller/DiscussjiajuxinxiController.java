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

import com.entity.DiscussjiajuxinxiEntity;
import com.entity.view.DiscussjiajuxinxiView;

import com.service.DiscussjiajuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 家居信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:30
 */
@RestController
@RequestMapping("/discussjiajuxinxi")
public class DiscussjiajuxinxiController {
    @Autowired
    private DiscussjiajuxinxiService discussjiajuxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjiajuxinxiEntity discussjiajuxinxi,
		HttpServletRequest request){
        EntityWrapper<DiscussjiajuxinxiEntity> ew = new EntityWrapper<DiscussjiajuxinxiEntity>();



		PageUtils page = discussjiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiajuxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussjiajuxinxiEntity discussjiajuxinxi, 
		HttpServletRequest request){
        EntityWrapper<DiscussjiajuxinxiEntity> ew = new EntityWrapper<DiscussjiajuxinxiEntity>();

		PageUtils page = discussjiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiajuxinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussjiajuxinxiEntity discussjiajuxinxi){
       	EntityWrapper<DiscussjiajuxinxiEntity> ew = new EntityWrapper<DiscussjiajuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussjiajuxinxi, "discussjiajuxinxi")); 
        return R.ok().put("data", discussjiajuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjiajuxinxiEntity discussjiajuxinxi){
        EntityWrapper< DiscussjiajuxinxiEntity> ew = new EntityWrapper< DiscussjiajuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussjiajuxinxi, "discussjiajuxinxi")); 
		DiscussjiajuxinxiView discussjiajuxinxiView =  discussjiajuxinxiService.selectView(ew);
		return R.ok("查询家居信息评论表成功").put("data", discussjiajuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjiajuxinxiEntity discussjiajuxinxi = discussjiajuxinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussjiajuxinxi,deSens);
        return R.ok().put("data", discussjiajuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjiajuxinxiEntity discussjiajuxinxi = discussjiajuxinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussjiajuxinxi,deSens);
        return R.ok().put("data", discussjiajuxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussjiajuxinxiEntity discussjiajuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiajuxinxi);
        discussjiajuxinxiService.insert(discussjiajuxinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjiajuxinxiEntity discussjiajuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiajuxinxi);
        discussjiajuxinxiService.insert(discussjiajuxinxi);
        return R.ok().put("data",discussjiajuxinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussjiajuxinxiEntity discussjiajuxinxi = discussjiajuxinxiService.selectOne(new EntityWrapper<DiscussjiajuxinxiEntity>().eq("", username));
        return R.ok().put("data", discussjiajuxinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussjiajuxinxiEntity discussjiajuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjiajuxinxi);
        //全部更新
        discussjiajuxinxiService.updateById(discussjiajuxinxi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussjiajuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussjiajuxinxiEntity discussjiajuxinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussjiajuxinxiEntity> ew = new EntityWrapper<DiscussjiajuxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussjiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiajuxinxi), params), params));
        return R.ok().put("data", page);
    }












}

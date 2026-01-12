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

import com.entity.JiajuxinxiEntity;
import com.entity.view.JiajuxinxiView;

import com.service.JiajuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 家居信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:28
 */
@RestController
@RequestMapping("/jiajuxinxi")
public class JiajuxinxiController {
    @Autowired
    private JiajuxinxiService jiajuxinxiService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiajuxinxiEntity jiajuxinxi,
		HttpServletRequest request){
        EntityWrapper<JiajuxinxiEntity> ew = new EntityWrapper<JiajuxinxiEntity>();



		PageUtils page = jiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajuxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiajuxinxiEntity jiajuxinxi, 
		HttpServletRequest request){
        EntityWrapper<JiajuxinxiEntity> ew = new EntityWrapper<JiajuxinxiEntity>();

		PageUtils page = jiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajuxinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiajuxinxiEntity jiajuxinxi){
       	EntityWrapper<JiajuxinxiEntity> ew = new EntityWrapper<JiajuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiajuxinxi, "jiajuxinxi")); 
        return R.ok().put("data", jiajuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiajuxinxiEntity jiajuxinxi){
        EntityWrapper< JiajuxinxiEntity> ew = new EntityWrapper< JiajuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiajuxinxi, "jiajuxinxi")); 
		JiajuxinxiView jiajuxinxiView =  jiajuxinxiService.selectView(ew);
		return R.ok("查询家居信息成功").put("data", jiajuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiajuxinxiEntity jiajuxinxi = jiajuxinxiService.selectById(id);
		jiajuxinxi.setClicknum(jiajuxinxi.getClicknum()+1);
		jiajuxinxi.setClicktime(new Date());
		jiajuxinxiService.updateById(jiajuxinxi);
        jiajuxinxi = jiajuxinxiService.selectView(new EntityWrapper<JiajuxinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajuxinxi,deSens);
        return R.ok().put("data", jiajuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiajuxinxiEntity jiajuxinxi = jiajuxinxiService.selectById(id);
		jiajuxinxi.setClicknum(jiajuxinxi.getClicknum()+1);
		jiajuxinxi.setClicktime(new Date());
		jiajuxinxiService.updateById(jiajuxinxi);
        jiajuxinxi = jiajuxinxiService.selectView(new EntityWrapper<JiajuxinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(jiajuxinxi,deSens);
        return R.ok().put("data", jiajuxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiajuxinxiEntity jiajuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajuxinxi);
        jiajuxinxiService.insert(jiajuxinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiajuxinxiEntity jiajuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiajuxinxi);
        jiajuxinxiService.insert(jiajuxinxi);
        return R.ok().put("data",jiajuxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiajuxinxiEntity jiajuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiajuxinxi);
        //全部更新
        jiajuxinxiService.updateById(jiajuxinxi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiajuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {

        Wrapper<JiajuxinxiEntity> wrapper = new EntityWrapper<JiajuxinxiEntity>();

        // 从map中获取remindStart和remindEnd
    Object remindStart = map.get("remindstart") != null ? map.get("remindstart").toString() : null;
    Object remindEnd = map.get("remindend") != null ? map.get("remindend").toString() : null;
        if ("2".equals(type)) {
            Date startDate = null;
            Date endDate = null;
            if (null != remindStart) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindStart.toString()));
                startDate = c.getTime();
            }
            if (null != remindEnd) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindEnd.toString()));
                endDate = c.getTime();
            }
            if (startDate != null && endDate != null) {
                if (startDate.before(endDate)) {
                    wrapper.ge(columnName, startDate).le(columnName, endDate);
                } else {
                    wrapper.ge(columnName, startDate).or().le(columnName, endDate);
                }
            } else if (startDate == null && endDate != null) {
                wrapper.le(columnName, endDate);
            } else if (startDate != null && endDate == null) {
                wrapper.ge(columnName, startDate);
            }
        } else {
            if (remindStart != null && remindEnd != null) {
                if (Double.parseDouble(remindStart.toString()) < Double.parseDouble(remindEnd.toString())) {
                    wrapper.ge(columnName, remindStart).le(columnName, remindEnd);
                } else {
                    wrapper.ge(columnName, remindStart).or().le(columnName, remindEnd);
                }
            } else if (remindStart == null && remindEnd != null) {
                wrapper.le(columnName, remindEnd);
            } else if (remindStart != null && remindEnd == null) {
                wrapper.ge(columnName, remindStart);
            }
        }

        List<JiajuxinxiEntity> list = jiajuxinxiService.selectList(wrapper);
        Map<String,Object> res= new HashMap<>();
        res.put("count", list.size());
        res.put("data",list.stream().map(JiajuxinxiEntity::getJiajumingcheng).collect(Collectors.toList()));
        return R.ok(res);
	}
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,JiajuxinxiEntity jiajuxinxi, HttpServletRequest request,String pre){
        EntityWrapper<JiajuxinxiEntity> ew = new EntityWrapper<JiajuxinxiEntity>();
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
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = jiajuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiajuxinxi), params), params));
        return R.ok().put("data", page);
    }












}

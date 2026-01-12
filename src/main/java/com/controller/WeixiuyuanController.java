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
import com.entity.TokenEntity;
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

import com.entity.WeixiuyuanEntity;
import com.entity.view.WeixiuyuanView;

import com.service.WeixiuyuanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 维修员
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-02 18:25:28
 */
@RestController
@RequestMapping("/weixiuyuan")
public class WeixiuyuanController {
    @Autowired
    private WeixiuyuanService weixiuyuanService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		WeixiuyuanEntity u = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(u.getId(), username,"weixiuyuan",  "维修员" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody WeixiuyuanEntity weixiuyuan){
    	//ValidatorUtils.validateEntity(weixiuyuan);
    	WeixiuyuanEntity u = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		weixiuyuan.setId(uId);
        weixiuyuanService.insert(weixiuyuan);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        WeixiuyuanEntity u = weixiuyuanService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	WeixiuyuanEntity u = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        weixiuyuanService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WeixiuyuanEntity weixiuyuan,
		HttpServletRequest request){
        EntityWrapper<WeixiuyuanEntity> ew = new EntityWrapper<WeixiuyuanEntity>();



		PageUtils page = weixiuyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, weixiuyuan), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,WeixiuyuanEntity weixiuyuan, 
		HttpServletRequest request){
        EntityWrapper<WeixiuyuanEntity> ew = new EntityWrapper<WeixiuyuanEntity>();

		PageUtils page = weixiuyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, weixiuyuan), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WeixiuyuanEntity weixiuyuan){
       	EntityWrapper<WeixiuyuanEntity> ew = new EntityWrapper<WeixiuyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( weixiuyuan, "weixiuyuan")); 
        return R.ok().put("data", weixiuyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WeixiuyuanEntity weixiuyuan){
        EntityWrapper< WeixiuyuanEntity> ew = new EntityWrapper< WeixiuyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( weixiuyuan, "weixiuyuan")); 
		WeixiuyuanView weixiuyuanView =  weixiuyuanService.selectView(ew);
		return R.ok("查询维修员成功").put("data", weixiuyuanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(weixiuyuan,deSens);
        return R.ok().put("data", weixiuyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(weixiuyuan,deSens);
        return R.ok().put("data", weixiuyuan);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WeixiuyuanEntity weixiuyuan, HttpServletRequest request){
        if(weixiuyuanService.selectCount(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()))>0) {
            return R.error("维修账号已存在");
        }
    	weixiuyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(weixiuyuan);
    	WeixiuyuanEntity u = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		weixiuyuan.setId(new Date().getTime());
        weixiuyuanService.insert(weixiuyuan);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WeixiuyuanEntity weixiuyuan, HttpServletRequest request){
        if(weixiuyuanService.selectCount(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()))>0) {
            return R.error("维修账号已存在");
        }
    	weixiuyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(weixiuyuan);
    	WeixiuyuanEntity u = weixiuyuanService.selectOne(new EntityWrapper<WeixiuyuanEntity>().eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		weixiuyuan.setId(new Date().getTime());
        weixiuyuanService.insert(weixiuyuan);
        return R.ok().put("data",weixiuyuan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WeixiuyuanEntity weixiuyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(weixiuyuan);
        if(weixiuyuanService.selectCount(new EntityWrapper<WeixiuyuanEntity>().ne("id", weixiuyuan.getId()).eq("weixiuzhanghao", weixiuyuan.getWeixiuzhanghao()))>0) {
            return R.error("维修账号已存在");
        }
        //全部更新
        weixiuyuanService.updateById(weixiuyuan);
    if(null!=weixiuyuan.getWeixiuzhanghao())
    {
        // 修改token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUsername(weixiuyuan.getWeixiuzhanghao());
        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", weixiuyuan.getId()));
    }


        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        weixiuyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	












}

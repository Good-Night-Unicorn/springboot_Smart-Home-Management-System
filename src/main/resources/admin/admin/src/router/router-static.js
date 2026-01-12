import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import aboutus from '@/views/modules/aboutus/list'
	import baoxiufankui from '@/views/modules/baoxiufankui/list'
	import discussjiajuxinxi from '@/views/modules/discussjiajuxinxi/list'
	import yuanchengkongzhi from '@/views/modules/yuanchengkongzhi/list'
	import jiajufenlei from '@/views/modules/jiajufenlei/list'
	import baoxiuanpai from '@/views/modules/baoxiuanpai/list'
	import zhinenglianjie from '@/views/modules/zhinenglianjie/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import weixiuyuan from '@/views/modules/weixiuyuan/list'
	import baoxiupingjia from '@/views/modules/baoxiupingjia/list'
	import systemintro from '@/views/modules/systemintro/list'
	import jiajuxinxi from '@/views/modules/jiajuxinxi/list'
	import yonghu from '@/views/modules/yonghu/list'
	import chat from '@/views/modules/chat/list'
	import forumreport from '@/views/modules/forumreport/list'
	import messages from '@/views/modules/messages/list'
	import jiajubaoxiu from '@/views/modules/jiajubaoxiu/list'
	import baoxiujiedan from '@/views/modules/baoxiujiedan/list'
	import config from '@/views/modules/config/list'
	import jiajugoumai from '@/views/modules/jiajugoumai/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/baoxiufankui',
		name: '报修反馈',
		component: baoxiufankui
	}
	,{
		path: '/discussjiajuxinxi',
		name: '家居信息评论',
		component: discussjiajuxinxi
	}
	,{
		path: '/yuanchengkongzhi',
		name: '远程控制',
		component: yuanchengkongzhi
	}
	,{
		path: '/jiajufenlei',
		name: '家居分类',
		component: jiajufenlei
	}
	,{
		path: '/baoxiuanpai',
		name: '报修安排',
		component: baoxiuanpai
	}
	,{
		path: '/zhinenglianjie',
		name: '智能连接',
		component: zhinenglianjie
	}
	,{
		path: '/forum',
		name: '交流论坛',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/weixiuyuan',
		name: '维修员',
		component: weixiuyuan
	}
	,{
		path: '/baoxiupingjia',
		name: '报修评价',
		component: baoxiupingjia
	}
	,{
		path: '/systemintro',
		name: '系统简介',
		component: systemintro
	}
	,{
		path: '/jiajuxinxi',
		name: '家居信息',
		component: jiajuxinxi
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/chat',
		name: '在线咨询',
		component: chat
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/messages',
		name: '留言反馈',
		component: messages
	}
	,{
		path: '/jiajubaoxiu',
		name: '家居报修',
		component: jiajubaoxiu
	}
	,{
		path: '/baoxiujiedan',
		name: '报修接单',
		component: baoxiujiedan
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/jiajugoumai',
		name: '家居购买',
		component: jiajugoumai
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;

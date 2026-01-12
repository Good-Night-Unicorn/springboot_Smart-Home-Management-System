import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import weixiuyuanList from '../pages/weixiuyuan/list'
import weixiuyuanDetail from '../pages/weixiuyuan/detail'
import weixiuyuanAdd from '../pages/weixiuyuan/add'
import jiajufenleiList from '../pages/jiajufenlei/list'
import jiajufenleiDetail from '../pages/jiajufenlei/detail'
import jiajufenleiAdd from '../pages/jiajufenlei/add'
import jiajuxinxiList from '../pages/jiajuxinxi/list'
import jiajuxinxiDetail from '../pages/jiajuxinxi/detail'
import jiajuxinxiAdd from '../pages/jiajuxinxi/add'
import jiajugoumaiList from '../pages/jiajugoumai/list'
import jiajugoumaiDetail from '../pages/jiajugoumai/detail'
import jiajugoumaiAdd from '../pages/jiajugoumai/add'
import yuanchengkongzhiList from '../pages/yuanchengkongzhi/list'
import yuanchengkongzhiDetail from '../pages/yuanchengkongzhi/detail'
import yuanchengkongzhiAdd from '../pages/yuanchengkongzhi/add'
import zhinenglianjieList from '../pages/zhinenglianjie/list'
import zhinenglianjieDetail from '../pages/zhinenglianjie/detail'
import zhinenglianjieAdd from '../pages/zhinenglianjie/add'
import jiajubaoxiuList from '../pages/jiajubaoxiu/list'
import jiajubaoxiuDetail from '../pages/jiajubaoxiu/detail'
import jiajubaoxiuAdd from '../pages/jiajubaoxiu/add'
import baoxiuanpaiList from '../pages/baoxiuanpai/list'
import baoxiuanpaiDetail from '../pages/baoxiuanpai/detail'
import baoxiuanpaiAdd from '../pages/baoxiuanpai/add'
import baoxiujiedanList from '../pages/baoxiujiedan/list'
import baoxiujiedanDetail from '../pages/baoxiujiedan/detail'
import baoxiujiedanAdd from '../pages/baoxiujiedan/add'
import baoxiufankuiList from '../pages/baoxiufankui/list'
import baoxiufankuiDetail from '../pages/baoxiufankui/detail'
import baoxiufankuiAdd from '../pages/baoxiufankui/add'
import baoxiupingjiaList from '../pages/baoxiupingjia/list'
import baoxiupingjiaDetail from '../pages/baoxiupingjia/detail'
import baoxiupingjiaAdd from '../pages/baoxiupingjia/add'
import forumtypeList from '../pages/forumtype/list'
import forumtypeDetail from '../pages/forumtype/detail'
import forumtypeAdd from '../pages/forumtype/add'
import forumreportList from '../pages/forumreport/list'
import forumreportDetail from '../pages/forumreport/detail'
import forumreportAdd from '../pages/forumreport/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import discussjiajuxinxiList from '../pages/discussjiajuxinxi/list'
import discussjiajuxinxiDetail from '../pages/discussjiajuxinxi/detail'
import discussjiajuxinxiAdd from '../pages/discussjiajuxinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'weixiuyuan',
					component: weixiuyuanList
				},
				{
					path: 'weixiuyuanDetail',
					component: weixiuyuanDetail
				},
				{
					path: 'weixiuyuanAdd',
					component: weixiuyuanAdd
				},
				{
					path: 'jiajufenlei',
					component: jiajufenleiList
				},
				{
					path: 'jiajufenleiDetail',
					component: jiajufenleiDetail
				},
				{
					path: 'jiajufenleiAdd',
					component: jiajufenleiAdd
				},
				{
					path: 'jiajuxinxi',
					component: jiajuxinxiList
				},
				{
					path: 'jiajuxinxiDetail',
					component: jiajuxinxiDetail
				},
				{
					path: 'jiajuxinxiAdd',
					component: jiajuxinxiAdd
				},
				{
					path: 'jiajugoumai',
					component: jiajugoumaiList
				},
				{
					path: 'jiajugoumaiDetail',
					component: jiajugoumaiDetail
				},
				{
					path: 'jiajugoumaiAdd',
					component: jiajugoumaiAdd
				},
				{
					path: 'yuanchengkongzhi',
					component: yuanchengkongzhiList
				},
				{
					path: 'yuanchengkongzhiDetail',
					component: yuanchengkongzhiDetail
				},
				{
					path: 'yuanchengkongzhiAdd',
					component: yuanchengkongzhiAdd
				},
				{
					path: 'zhinenglianjie',
					component: zhinenglianjieList
				},
				{
					path: 'zhinenglianjieDetail',
					component: zhinenglianjieDetail
				},
				{
					path: 'zhinenglianjieAdd',
					component: zhinenglianjieAdd
				},
				{
					path: 'jiajubaoxiu',
					component: jiajubaoxiuList
				},
				{
					path: 'jiajubaoxiuDetail',
					component: jiajubaoxiuDetail
				},
				{
					path: 'jiajubaoxiuAdd',
					component: jiajubaoxiuAdd
				},
				{
					path: 'baoxiuanpai',
					component: baoxiuanpaiList
				},
				{
					path: 'baoxiuanpaiDetail',
					component: baoxiuanpaiDetail
				},
				{
					path: 'baoxiuanpaiAdd',
					component: baoxiuanpaiAdd
				},
				{
					path: 'baoxiujiedan',
					component: baoxiujiedanList
				},
				{
					path: 'baoxiujiedanDetail',
					component: baoxiujiedanDetail
				},
				{
					path: 'baoxiujiedanAdd',
					component: baoxiujiedanAdd
				},
				{
					path: 'baoxiufankui',
					component: baoxiufankuiList
				},
				{
					path: 'baoxiufankuiDetail',
					component: baoxiufankuiDetail
				},
				{
					path: 'baoxiufankuiAdd',
					component: baoxiufankuiAdd
				},
				{
					path: 'baoxiupingjia',
					component: baoxiupingjiaList
				},
				{
					path: 'baoxiupingjiaDetail',
					component: baoxiupingjiaDetail
				},
				{
					path: 'baoxiupingjiaAdd',
					component: baoxiupingjiaAdd
				},
				{
					path: 'forumtype',
					component: forumtypeList
				},
				{
					path: 'forumtypeDetail',
					component: forumtypeDetail
				},
				{
					path: 'forumtypeAdd',
					component: forumtypeAdd
				},
				{
					path: 'forumreport',
					component: forumreportList
				},
				{
					path: 'forumreportDetail',
					component: forumreportDetail
				},
				{
					path: 'forumreportAdd',
					component: forumreportAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'discussjiajuxinxi',
					component: discussjiajuxinxiList
				},
				{
					path: 'discussjiajuxinxiDetail',
					component: discussjiajuxinxiDetail
				},
				{
					path: 'discussjiajuxinxiAdd',
					component: discussjiajuxinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})

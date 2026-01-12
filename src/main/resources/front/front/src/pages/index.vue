<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<span @click="goMenu('/index/home')">基于SpringBoot的智能家居管理系统设计与实现</span>
				</div>
				<div class="top_tel"></div>
			
				<div id="search" class="search">
					<div class="select">
						<el-select v-model="queryIndex">
							<el-option v-for="(item,index) in queryList" :key="index" :label="item.queryName" :value="index"></el-option>
						</el-select>
					</div>
					<div class="input" v-if="queryIndex==0">
						<el-input v-model="jiajuxinxijiajumingcheng" placeholder="家居名称"></el-input>
					</div>
					<div class="btn" v-if="queryIndex==0">
						<el-button class="search_btn" type="primary" @click="search('jiajuxinxi')">
							<span class="icon iconfont icon-fangdajing07"></span>
							搜索
						</el-button>
					</div>
				</div>

				<img class="top_avatar1" v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
				<div class="top_nickname1" v-if="Token">{{username}}</div>
				<el-button v-if="Token" class="btn-service" @click.native="goChat">
					<span class="icon iconfont icon-touxiang09"></span>
						在线咨询
				</el-button>
				<el-button v-if="Token && notAdmin" class="btn-user" @click="goMenu('/index/center')">
					<span class="icon iconfont icon-wuliu8"></span>
					个人中心
				</el-button>
				<el-button v-if="!Token" class="btn-login" @click="toLogin">
					<span class="icon iconfont icon-touxiang03"></span>
					登录
				</el-button>
				<el-button v-if="Token" class="btn-register" @click="logout">
					<span class="icon iconfont icon-guanbi19"></span>
					退出
				</el-button>
			</div>


			<div class="menu-preview">
				<div class="menu-list">
					<div class="menu-home" :class="activeMenu=='/index/home'?'menu-active':''" @click="goMenu('/index/home')">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">系统首页</div>
						</div>
					</div>
					<div class="menu-item" v-for="(item,index) in menuList" :key="index" @mouseenter="menuShowClick4(index)" @mouseleave="menuShowClick4(-1)" :class="activeMenu==item.url?'menu-active':''" @click.stop="goMenu(item.url)">
						<div class="title">
							<span :class="iconArr[index]"></span>
							<div class="text">{{item.name}}</div>
						</div>
						<transition name="el-zoom-in-top">
							<div v-if="showType4==index&&item.hasCate" class="menu-child-list">
								<div class="child-item" v-for="(items,indexs) in item.cateList" :key="indexs" @click.stop="cateClick(item.url,items)">{{items}}</div>
							</div>
						</transition>
					</div>
					<div class="menu-service" @click="goChat" v-if="Token">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">客服</div>
						</div>
					</div>
					<div class="menu-user" :class="activeMenu=='/index/center'?'menu-active':''" @click="goMenu('/index/center')" v-if="Token && notAdmin">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">个人中心</div>
						</div>
					</div>
				</div>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<el-carousel trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="400px" :autoplay="false" :interval="3000" :loop="true">
					<el-carousel-item v-for="item in carouselList" :key="item.id">
						<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
						<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
						<div class="banner-hidden">
						</div>
					</el-carousel-item>
				</el-carousel>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
		<el-dialog title="在线咨询" :visible.sync="chatFormVisible" width="600px" :before-close="chatClose">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="item in chatList">
					<div v-if="item.addtime" style="width: 100%;text-align: center;font-size: 10px;color: #666;">{{timeFormat(item.addtime)}}</div>
					<div v-if="item.ask" class="right-content">
						<div style="display: flex;align-items: flex-start;">
							<el-alert v-if="item.type==1" class="text-content" :title="item.ask" :closable="false"
								type="warning"></el-alert>
							<el-image v-else-if="item.type==2" :src="baseUrl + item.ask" style="width: 150px;height: 150px;" fit="cover" :preview-src-list="[baseUrl + item.ask]"></el-image>
							<video v-else-if="item.type==3" :src="baseUrl + item.ask" style="width: 280px;" controls></video>
							<el-button v-else-if="item.type==4" type="primary" size="mini" @click="download(item.ask)">文件预览</el-button>
							<img :src="item.uimage?(baseUrl + item.uimage):require('@/assets/avator.png')" style="width: 30px;height: 30px;border-radius: 50%;margin: 0 0 0 5px;" alt="">
						</div>
					</div>
					<div v-else class="left-content">
						<div style="display: flex;align-items: flex-start;">
							<img :src="item.uimage?(baseUrl + item.uimage.split(',')[0]):require('@/assets/avator.png')" style="width: 30px;height: 30px;border-radius: 50%;margin: 0 5px 0 0;" alt="">
							<el-alert v-if="item.type==1" class="text-content" :title="item.reply" :closable="false"
								type="success"></el-alert>
							<el-image v-else-if="item.type==2" :src="baseUrl + item.reply" style="width: 150px;height: 150px;" fit="cover" :preview-src-list="[baseUrl + item.reply]"></el-image>
							<video v-else-if="item.type==3" :src="baseUrl + item.reply" style="width: 280px;" controls></video>
							<el-button v-else-if="item.type==4" type="primary" size="mini" @click="download(item.reply)">文件预览</el-button>
						</div>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<div v-if="askShow"
					style="padding-bottom: 10px;display: flex;align-items: center;justify-content: center;">
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess" accept=".jpg,.png"
						:show-file-list="false">
						<el-button size="mini" type="success">上传图片</el-button>
					</el-upload>
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess2" accept=".mp4"
						:show-file-list="false">
						<el-button size="mini" type="success" style="margin: 0 0 0 10px;">上传视频</el-button>
					</el-upload>
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess3"
						:show-file-list="false">
						<el-button size="mini" type="success" style="margin: 0 0 0 10px;">上传文件</el-button>
					</el-upload>
				</div>
				<div style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
					<img style="width: 30px;cursor: pointer;" @click="askShow = !askShow" src="../assets/jiahao.png">
					<el-input @keydown.enter.native="addChat(null)" v-model="form.ask" placeholder="请输入内容" style="width: calc(100% - 110px);">
					</el-input>
					<el-button type="primary" @click="addChat(null)">发送</el-button>
					<div style="position: relative;">
						<span @click="showEmoji=!showEmoji" class="icon iconfont icon-gerenzhongxin-zhihui" style="font-size: 30px;color: #666;cursor: pointer;"></span>
						<picker
							:include="['people', 'Smileys']"
							:showSearch="false"
							:showPreview="false"
							:showCategories="false"
							@select="addEmoji"
							v-if="showEmoji"
							:backgroundImageFn="((set,sheetSize)=>{
								return require('@/assets/32.png')
							})"
							style="position: absolute;bottom: 40px;left: -100px;"
						/>
					</div>
				</div>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
	import { Picker } from "emoji-mart-vue";
	import timeMethod from '@/common/timeMethod'
	import {
		WebsocketMixin
	} from '@/mixins/WebsocketMixin'
export default {
	components:{
		Picker
	},
	mixins: [WebsocketMixin],
	data() {
		return {
			queryList:[
				{
					queryName:"家居名称",
				},
			],
			queryIndex: 0,
			jiajuxinxijiajumingcheng: '',
			activeIndex: '0',
			baseUrl: '',
			carouselList: [],
			carouselForm: {
				inHome: true,
				inOther: true,
			},
			menuList: [],
			chatFormVisible: false,
			chatList: [],
			headers: {
				Token: localStorage.getItem('frontToken')
			},
			uploadUrl: this.$config.baseUrl + 'file/upload',
			askShow: false,
			showEmoji: false,
			form: {
				ask: '',
			},
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			showType4: -1,
		}
	},
	async created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
		}
		this.cateList = this.$config.cateList
		if(this.cateList.length){
			for(let x in this.menuList){
				for(let i in this.cateList){
					if(this.menuList[x].name==this.cateList[i].name){
						await this.$http.get(`option/${this.cateList[i].refTable}/${this.cateList[i].refColumn}`).then(rs=>{
							this.menuList[x].cateList = rs.data.data
							this.menuList[x].hasCate = true
						})
					}
				}
			}
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';



	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
	},
	methods: {
		cateClick(url,fenlei){
			this.$router.push(url + '?homeFenlei=' + fenlei);
		},
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},
		search(tablename) {
			if (this.queryIndex == 0 && this.jiajuxinxijiajumingcheng) {
			this.$router.push({path: '/index/' + tablename, query: {indexQueryCondition: this.jiajuxinxijiajumingcheng}});
			}
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return (this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home')
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.location.href = `${this.$config.baseUrl}admin/dist/index.html`
			
		},
		formatMessages(messages) {
			let lastTime = null;
			messages.forEach((message, index) => {
				const currentTime = new Date(message.addtime).getTime();
				if (lastTime !== null) {
					const timeDiff = (currentTime - lastTime) / 1000 / 60; // 转换为分钟
					if (timeDiff < 3) {
						message.addtime = ''; // 如果小于3分钟，不显示时间
					}
				}
				lastTime = currentTime;
			});
			return messages;
		},
		timeFormat(time) {
			const Time = timeMethod.getTime(time).split("T");
			//当前消息日期属于周
			const week = timeMethod.getDateToWeek(time);
			//当前日期0时
			const nti = timeMethod.setTimeZero(timeMethod.getNowTime());
			//消息日期当天0时
			const mnti = timeMethod.setTimeZero(timeMethod.getTime(time));
			//计算日期差值
			const diffDate = timeMethod.calculateTime(nti, mnti);
			//本周一日期0时 （后面+1是去除当天时间）
			const fwnti = timeMethod.setTimeZero(timeMethod.countDateStr(-timeMethod.getDateToWeek(timeMethod
				.getNowTime()).weekID + 1));
			//计算周日期差值
			const diffWeek = timeMethod.calculateTime(mnti, fwnti);
		
			if (diffDate === 0) { //消息发送日期减去当天日期如果等于0则是当天时间
				return Time[1].slice(0, 5);
			} else if (diffDate < 172800000) { //当前日期减去消息发送日期小于2天（172800000ms）则是昨天-  一天最大差值前天凌晨00:00:00到今天晚上23:59:59
				return "昨天 " + Time[1].slice(0, 5);
			} else if (diffWeek >= 0) { //消息日期减去本周一日期大于0则是本周
				return week.weekName;
			} else { //其他时间则是日期
				return Time[0].slice(5, 10);
			}
		},
		addEmoji(e) {
			this.form.ask += e.native;
			this.showEmoji = false
		},
		getChatList() {
			this.$http.get('chat/list', {params: { userid: Number(localStorage.getItem('frontUserid')), sort: 'addtime', order: 'asc',limit: 1000 }}).then(res => {
				if (res.data.code == 0) {
					this.chatList = this.formatMessages(res.data.data.list);
					let div = document.getElementsByClassName('chat-content')[0]
					setTimeout(() => {
						if (div){
							div.scrollTop = div.scrollHeight
						}
					}, 0)
				}
			});
		},
		addChat(ask=null,type=1) {
			let params = JSON.parse(JSON.stringify(this.form))
			if(params.ask==''&&ask==null){
				this.$message.error('内容不能为空')
				return false
			}
			if(ask){
				params.ask = ask
			}
			params.type = type
			params.uimage = localStorage.getItem('frontHeadportrait')
			params.uname = localStorage.getItem('username')
			params.userid = Number(localStorage.getItem('frontUserid'))
			this.$http.post('chat/add', params).then(res => {
				if (res.data.code == 0) {
					this.websocketSend(ask?ask:params.ask)
					this.form.ask = '';
					this.getChatList();
				}
			});
		},
		chatClose() {
			if(this.askType==2){
				this.websocketOnclose();
			}
			this.chatFormVisible = false;
		},
		websocketOnmessage:function(e) {
			this.getChatList()
		},
		goChat() {
			if(!localStorage.getItem('frontToken')) {
				this.toLogin();
				return;
			}
			
			this.initWebSocket(1)
			this.getChatList();
			this.chatFormVisible = true;
		},
		uploadSuccess(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,2)
			}
		},
		uploadSuccess2(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,3)
			}
		},
		uploadSuccess3(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,4)
			}
		},
		download(url){
			if(!url){
				return false
			}
			window.open((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] + this.$config.name + '/' + url :this.$config.baseUrl + url))
		},
		menuShowClick4(index){
			this.showType4 = index
		},
		goMenu(path) {
			this.$router.push(path);
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.main-containers {
		.body-containers {
			padding: 160px 0 0;
			margin: 0;
			background: #fff;
			min-height: 100vh;
			position: relative;
			.top-container {
				box-shadow: 0 0px 6px rgba(0,0,0,.3);
				padding: 60px 40px 30px 60px;
				z-index: 1002;
				top: 0;
				left: 0;
				background: #ffffff;
				display: flex;
				width: 100%;
				justify-content: flex-end;
				align-items: flex-end;
				position: absolute;
				height: 160px;
				.top_title {
					margin: 0 20px 0 0;
					white-space: nowrap;
					flex: 1;
					display: inline-block;
					order: -1;
					span {
						padding: 0 0 0 0;
						color: #000;
						white-space: absolute;
						font-size: 24px;
						line-height: 44px;
						float: left;
					}
				}
				.top_tel {
					margin: 0 0;
					color: #666;
					top: 60px;
					left: 0;
					background: #A7A7A7;
					width: 100%;
					font-size: 0;
					position: absolute;
					height: 1px;
				}
				// -------- search --------
				.search {
					padding: 0;
					margin: 0 20px 0 0;
					background: none;
					display: flex;
					align-items: center;
					height: auto;
					order: 0;
					.select {
						margin: 0;
						flex: 1;
						.el-select {
							width: 100%;
							/deep/ .el-input__inner {
								border: 1px solid #B0C1D1;
								border-radius: 0;
								padding: 0 30px 0 10px;
								box-shadow: 0 0 0px rgba(64, 158, 255, .3);
								outline: none;
								color: #000;
								width: 120px;
								font-size: 14px;
								border-width: 2px 0 2px 2px;
								height: 44px;
							}
						}
					}
					.input {
						margin: 0;
						flex: 1;
						.el-input {
							width: 100%;
							/deep/ .el-input__inner {
								border: 1px solid #B0C1D1;
								border-radius: 0;
								padding: 0 10px;
								box-shadow: 0 0 0px rgba(64, 158, 255, .3);
								outline: none;
								color: #000;
								width: 280px;
								font-size: 14px;
								border-width: 2px 2px 2px 0;
								height: 44px;
							}
						}
					}
					.btn {
						padding: 0;
						margin: 0;
						flex: 1;
						.search_btn {
							border: 0;
							cursor: pointer;
							border-radius: 0;
							padding: 0 10px;
							margin: 0;
							outline: none;
							color: rgba(255, 255, 255, 1);
							background: #0AB8C1;
							width: auto;
							font-size: 14px;
							line-height: 44px;
							height: 44px;
							.icon {
								margin: 0 4px 0 0;
								color: rgba(255, 255, 255, 1);
								font-size: 14px;
							}
						}
						.search_btn:hover {
						}
					}
				}
				// -------- search --------
				.top_avatar1 {
					border-radius: 50%;
					margin: 0 12px;
					display: none;
					width: 40px;
					height: 40px;
				}
				.top_nickname1 {
					padding: 0 12px;
					margin: 0 10px 0px;
					color: #000;
					font-weight: bold;
					font-size: 16px;
					line-height: 44px;
					height: 44px;
					order: 5;
				}
				.btn-service {
					border: 0;
					padding: 0px;
					margin: 0 20px 0 0;
					color: #000;
					background: none;
					font-weight: bold;
					width: auto;
					font-size: 16px;
					line-height: 44px;
					height: 44px;
					order: 2;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-service:hover {
					opacity: 0.7;
				}
				.btn-user {
					border: 0;
					padding: 0px;
					margin: 0 20px 0 0;
					color: #000;
					background: none;
					font-weight: bold;
					width: auto;
					font-size: 16px;
					line-height: 44px;
					height: 44px;
					order: 6;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-user:hover {
					opacity: 0.7;
				}
				.btn-login {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #333;
					font-weight: bold;
					font-size: 18px;
					line-height: 60px;
					right: 60px;
					top: 0;
					background: none;
					width: auto;
					position: absolute;
					height: 60px;
					.icon {
						padding: 0 5px 0 0;
						color: inherit;
						font-size: inherit;
					}
				}
				.btn-login:hover {
					opacity: 0.6;
				}
				.btn-register {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #333;
					font-weight: bold;
					font-size: 18px;
					line-height: 60px;
					right: 60px;
					top: 0;
					background: none;
					width: auto;
					position: absolute;
					height: 60px;
					.icon {
						padding: 0 5px 0 0;
						color: inherit;
						font-size: inherit;
					}
				}
				.btn-register:hover {
					opacity: 0.6;
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				padding: 10px 0;
				background: #0AB8C1;
				width: 100%;
				.menu-list {
					display: flex;
					justify-content: center;
					position: relative;
					// 首页
					.menu-home {
						cursor: pointer;
						margin: 0 10px;
						color: #fff;
						line-height: 50px;
						height: 50px;
						.title {
							border-radius: 10px;
							padding: 0 20px;
							background: none;
							display: flex;
							height: 50px;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 16px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 16px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-home:hover {
						.title {
							color: #0AB8C1;
							background: #fff;
						}
					}
					.menu-home.menu-active {
						.title {
							color: #0AB8C1;
							background: #fff;
							font-weight: bold;
						}
					}
					// 其他盒子
					.menu-item {
						margin: 0 10px;
						color: #fff;
						line-height: 50px;
						height: 50px;
						.title {
							cursor: pointer;
							border-radius: 10px;
							padding: 0 20px;
							background: none;
							display: flex;
							height: 50px;
							span {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 16px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 16px;
								line-height: 50px;
								height: 50px;
							}
						}
						.menu-child-list {
							padding: 5px 0;
							z-index: 11;
							left: 0;
							background: #0AB8C1;
							bottom: -50px;
							display: flex;
							width: 100%;
							line-height: 40px;
							justify-content: center;
							position: absolute;
							height: 50px;
							.child-item {
								cursor: pointer;
								border-radius: 10px;
								padding: 0 20px;
								font-size: 14px;
							}
							.child-item:hover {
								color: #0AB8C1;
								background: #fff;
							}
						}
					}
					.menu-item:hover {
						.title {
							color: #0AB8C1;
							background: #fff;
						}
					}
					.menu-item.menu-active {
						.title {
							color: #0AB8C1;
							background: #fff;
							font-weight: bold;
						}
					}
					// 客服
					.menu-service {
						cursor: pointer;
						color: #fff;
						display: none;
						line-height: 50px;
						height: 50px;
						.title {
							padding: 0 20px;
							display: flex;
							height: 50px;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-service:hover {
						.title {
							background: #36ca61;
						}
					}
					.menu-service.menu-active {
						.title {
							background: #55ff00;
						}
					}
					// 个人中心
					.menu-user {
						cursor: pointer;
						color: #fff;
						display: none;
						line-height: 50px;
						height: 50px;
						.title {
							padding: 0 20px;
							display: flex;
							height: 50px;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-user:hover {
						.title {
							background: #36ca61;
						}
					}
					.menu-user.menu-active {
						.title {
							background: #55ff00;
						}
					}
				}
			}
			.banner-preview {
				margin: 20px auto;
				width: 68%;
				height: auto;
				.el-carousel {
					margin: 0 auto;
					width: 100%;
					/deep/ .el-carousel__item {
						border-radius: 0;
						width: 100%;
						height: 100%;
						@keyframes wave1 {from { left: -236px } to { left: -1233px }}
						@keyframes wave2 {from { left: 0 } to { left: -1009px }}
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left:hover {
						background: #0AB8C1;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right:hover {
						background: #0AB8C1;
					}
					/deep/ .el-carousel__indicators {
						padding: 0;
						margin: 0;
						z-index: 2;
						position: absolute;
						list-style: none;
						li {
							padding: 0;
							margin: 0 4px;
							background: #fff;
							display: inline-block;
							width: 12px;
							opacity: 0.4;
							transition: 0.3s;
							height: 12px;
						}
						li:hover {
							padding: 0;
							margin: 0 4px;
							background: #0AB8C1;
							display: inline-block;
							width: 24px;
							opacity: 0.7;
							height: 12px;
						}
						li.is-active {
							padding: 0;
							margin: 0 4px;
							background: #0AB8C1;
							display: inline-block;
							width: 24px;
							opacity: 1;
							height: 12px;
						}
					}
					/deep/ .el-carousel__indicator button {
						width: 0;
						height: 0;
						display: none;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px;
					overflow: hidden;
					background: #2C2C2C;
					width: 100%;
					height: auto;
				}
			}
		}
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;

		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
		}

		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
		}
	}

	.clear-float {
		clear: both;
	}
	.emoji-mart[data-v-7bc71df8] {
		font-family: -apple-system, BlinkMacSystemFont, "Helvetica Neue", sans-serif;
		display: -ms-flexbox;
		display: flex;
		-ms-flex-direction: column;
		flex-direction: column;
		height: 420px;
		color: #ffffff !important;
		border: 1px solid #d9d9d9;
		border-radius: 5px;
		background: #fff;
	}
</style>

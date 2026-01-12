<template>
	<div class="main-content" :style='{"padding":"20px 30px","fontSize":"15px","flexWrap":"wrap","display":"flex"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"width":"100%","margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"width":"100%","padding":"10px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex"}' >
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">家居名称</label>
						<el-input v-model="searchForm.jiajumingcheng" placeholder="家居名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">家居风格</label>
						<el-input v-model="searchForm.jiajufengge" placeholder="家居风格" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">家居材料</label>
						<el-input v-model="searchForm.jiajucailiao" placeholder="家居材料" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">品牌</label>
						<el-input v-model="searchForm.pinpai" placeholder="品牌" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"20px 0","flexWrap":"wrap","background":"none","flexDirection":"row-reverse","display":"flex","width":"100%","justifyContent":"flex-start"}'>
					<el-button class="add" v-if="isAuth('baoxiujiedan','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia14" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","height":"38px"}'></span>
						创建
					</el-button>
					<el-button class="del" v-if="isAuth('baoxiujiedan','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu9" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","height":"38px"}'></span>
						减少
					</el-button>


				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"14px","color":"#000"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"#9E9E9E","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('baoxiujiedan','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='false' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='false' :sortable='false' label="序号" type="index" width="50" />
					<el-table-column :resizable='false' :sortable='false'  
						prop="jiajumingcheng"
						label="家居名称">
						<template slot-scope="scope">
							{{scope.row.jiajumingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="jiajufenlei"
						label="家居分类">
						<template slot-scope="scope">
							{{scope.row.jiajufenlei}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="jiajufengge"
						label="家居风格">
						<template slot-scope="scope">
							{{scope.row.jiajufengge}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='false' prop="jiajutupian" width="200" label="家居图片">
						<template slot-scope="scope">
							<div v-if="scope.row.jiajutupian">
								<img v-if="scope.row.jiajutupian.substring(0,4)=='http'" :src="scope.row.jiajutupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.jiajutupian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.jiajutupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.jiajutupian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="jiajucailiao"
						label="家居材料">
						<template slot-scope="scope">
							{{scope.row.jiajucailiao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="pinpai"
						label="品牌">
						<template slot-scope="scope">
							{{scope.row.pinpai}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="guige"
						label="规格">
						<template slot-scope="scope">
							{{scope.row.guige}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="yonghuzhanghao"
						label="用户账号">
						<template slot-scope="scope">
							{{scope.row.yonghuzhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="yonghuxingming"
						label="用户姓名">
						<template slot-scope="scope">
							{{scope.row.yonghuxingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="weixiuzhanghao"
						label="维修账号">
						<template slot-scope="scope">
							{{scope.row.weixiuzhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="weixiuxingming"
						label="维修姓名">
						<template slot-scope="scope">
							{{scope.row.weixiuxingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="jiedanshijian"
						label="接单时间">
						<template slot-scope="scope">
							{{scope.row.jiedanshijian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false'  
						prop="weixiuzhuangtai"
						label="维修状态">
						<template slot-scope="scope">
							{{scope.row.weixiuzhuangtai}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('baoxiujiedan','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('baoxiujiedan','反馈')" @click="baoxiufankuiCrossAddOrUpdateHandler(scope.row,'cross','','','weixiuzhuangtai','已维修','已维修,待维修'.split(',')[0])" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								反馈
							</el-button>
							<el-button class="edit" v-if=" isAuth('baoxiujiedan','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								编辑
							</el-button>




							<el-button class="del" v-if="isAuth('baoxiujiedan','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								减去
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"0","boxShadow":"0px 2px 5px 0px rgba(0,0,0,0.3)","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","alignItems":"center","textAlign":"center","display":"flex","justifyContent":"center","overflow":"hidden","borderRadius":"30px","background":"#fff","width":"auto","fontSize":"inherit","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<baoxiufankui-cross-add-or-update v-if="baoxiufankuiCrossAddOrUpdateFlag" :parent="this" ref="baoxiufankuiCrossaddOrUpdate"></baoxiufankui-cross-add-or-update>




		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import baoxiufankuiCrossAddOrUpdate from "../baoxiufankui/add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				addOrUpdateFlag:false,
				baoxiufankuiCrossAddOrUpdateFlag: false,
				layouts: ["prev","pager","next"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
			baoxiufankuiCrossAddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},
			baoxiufankuiCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.baoxiufankuiCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','baoxiujiedan');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.baoxiufankuiCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.baoxiufankuiCrossaddOrUpdate.init(row.id,type);
				});
			},





			init () {
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
 				if(this.searchForm.jiajumingcheng!='' && this.searchForm.jiajumingcheng!=undefined){
					params['jiajumingcheng'] = '%' + this.searchForm.jiajumingcheng + '%'
				}
 				if(this.searchForm.jiajufengge!='' && this.searchForm.jiajufengge!=undefined){
					params['jiajufengge'] = '%' + this.searchForm.jiajufengge + '%'
				}
 				if(this.searchForm.jiajucailiao!='' && this.searchForm.jiajucailiao!=undefined){
					params['jiajucailiao'] = '%' + this.searchForm.jiajucailiao + '%'
				}
 				if(this.searchForm.pinpai!='' && this.searchForm.pinpai!=undefined){
					params['pinpai'] = '%' + this.searchForm.pinpai + '%'
				}
				if(this.searchForm.weixiuzhuangtai!='' && this.searchForm.weixiuzhuangtai!=undefined){
					params['weixiuzhuangtai'] = this.searchForm.weixiuzhuangtai
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "baoxiujiedan/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "baoxiujiedan/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		color: #fff;
		background: #179BD7;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 4px;
		color: #fff;
		background: #179BD7;
		width: auto;
		font-size: inherit;
		height: 38px;
	}
	
	.center-form-pv .actions .add:hover {
		color: #aaa;
		opacity: 1;
	}
	
	.center-form-pv .actions .del {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 4px;
		color: #179BD7;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 38px;
	}
	
	.center-form-pv .actions .del:hover {
		color: #AAA;
		opacity: 1;
	}
	
	.center-form-pv .actions .statis {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 4px;
		color: #179BD7;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 38px;
	}
	
	.center-form-pv .actions .statis:hover {
		color: #AAA;
		opacity: 1;
	}
	
	.center-form-pv .actions .btn18 {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 4px;
		color: #179BD7;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 38px;
	}
	
	.center-form-pv .actions .btn18:hover {
		color: #AAA;
		opacity: 1;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		color: #999;
		font-weight: 500;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: #179BD7;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 10px 0;
		background: none;
		border-color: #eee;
		border-width: 0 1px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #fff;
		white-space: normal;
		font-weight: bold;
		display: flex;
		vertical-align: middle;
		font-size: 14px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #9E9E9E;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #333;
		background: #f0f0f0;
		border-color: #9E9E9E;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #9E9E9E;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #179BD7;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #179BD7;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		color: #aaa;
		opacity: 1;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 1px solid #43B27F;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #43B27F;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #43B27F;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		color: #aaa;
		opacity: 1;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 1px solid #507AFC;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #507AFC;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #507AFC;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		color: #aaa;
		opacity: 1;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 1px solid #FAC858;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #FAC858;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #FAC858;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: 1px solid #c2c2c2;
		border-radius: 2px;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 20px;
		line-height: auto;
		min-width: 35px;
		height: 60px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: 1px solid #c2c2c2;
		border-radius: 2px;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 20px;
		line-height: auto;
		min-width: 35px;
		height: 60px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: 1px solid #c2c2c2;
		cursor: not-allowed;
		border-radius: 0;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 20px;
		line-height: auto;
		height: 60px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: 1px solid #c2c2c2;
		cursor: not-allowed;
		border-radius: 0;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 20px;
		line-height: auto;
		height: 60px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0 10px;
		display: flex;
		vertical-align: top;
		align-items: center;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		border-radius: 2px;
		padding: 0 15px;
		margin: 0 10px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 40px;
		text-align: center;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		border-radius: 50%;
		padding: 0 15px;
		margin: 0 10px;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 40px;
		text-align: center;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: pointer;
		border-radius: 50%;
		padding: 0 15px;
		margin: 0 10px;
		color: #fff;
		background: #179BD7;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 40px;
		text-align: center;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 1px solid #179BD7;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #179BD7;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #179BD7;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.one .list1-view:hover {
		box-shadow: 4px 4px 0 #aaa;
		color: #aaa;
		border-color: #aaa;
		opacity: 1;
	}
	
	.one .list1-edit {
		border: 1px solid #43B27F;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #43B27F;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #43B27F;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.one .list1-edit:hover {
		box-shadow: 4px 4px 0 #aaa;
		color: #aaa;
		border-color: #aaa;
		opacity: 1;
	}
	
	.one .list1-del {
		border: 1px solid #507AFC;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #507AFC;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #507AFC;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.one .list1-del:hover {
		box-shadow: 4px 4px 0 #aaa;
		color: #aaa;
		border-color: #aaa;
		opacity: 1;
	}
	
	.one .list1-btn8 {
		border: 1px solid #FAC858;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		box-shadow: 4px 4px 0 #FAC858;
		margin: 0 10px 10px 0;
		transform: skew(-15deg, 0deg);
		color: #FAC858;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.one .list1-btn8:hover {
		box-shadow: 4px 4px 0 #aaa;
		color: #aaa;
		border-color: #aaa;
		opacity: 1;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>

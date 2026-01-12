<template>
	<div class="main-content" :style='{"padding":"20px 30px","fontSize":"15px","flexWrap":"wrap","display":"flex"}'>
		<!-- 列表页 -->
		<div v-if="!showFlag" style="width: 100%;">
			<el-form :style='{"width":"100%","margin":"0 10px 20px"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"width":"100%","padding":"10px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex"}'>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">是否回复</label>
						<el-select v-model="searchForm.isreply" placeholder="请选择">
							<el-option label="已回复" :value="0"></el-option>
							<el-option label="未回复" :value="1"></el-option>
						</el-select>
					</div>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 5px 0 0","whiteSpace":"nowrap","color":"#fff","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"15px","fontWeight":"500","height":"40px"}' class="item-label">是否已读</label>
						<el-select v-model="searchForm.isread" placeholder="请选择">
							<el-option label="已读" :value="1"></el-option>
							<el-option label="未读" :value="0"></el-option>
						</el-select>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 15px","color":"#fff","borderRadius":"5px","background":"#179BD7","width":"auto","fontSize":"16px","height":"40px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>
			</el-form>
			<div>
				<el-table
					:stripe='false'
					:style='{"padding":"0","borderColor":"#9E9E9E","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}'
					:data="dataList"
					:border='true'
					v-loading="dataListLoading"
					style="width: 100%;"
					>
					<el-table-column
						:resizable='false' :sortable='false'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="用户"
						>
						<template slot-scope="scope">
							<div style="display: flex;align-items: center;">
								<img :src="scope.row.uimage?$base.url + scope.row.uimage.split(',')[0]:require('@/assets/img/avator.png')" style="width: 80px;height: 80px;border-radius: 50%;margin: 0 5px 0 0;" alt="" />
								{{scope.row.uname}}
							</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='false' :sortable='false' prop="ask" :formatter="askFormat" header-align="center" align="center" sortable label="新消息"></el-table-column>
					<el-table-column
						:resizable='false' :sortable='false'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="是否回复"
						width="150"
						>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.isreply==1" type="success">未回复</el-tag>
							<el-tag v-if="scope.row.isreply==0" type="info">已回复</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						:resizable='false' :sortable='false'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="是否已读"
						width="150"
						>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.isread==1" type="success">已读</el-tag>
							<el-tag v-if="scope.row.isread==0" type="info">未读</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						header-align="center"
						align="center"
						width="150"
						label="操作"
						>
						<template slot-scope="scope">
							<el-button
								type="text"
								:style='{"border":"1px solid #43B27F","cursor":"pointer","padding":"0 15px","boxShadow":"4px 4px 0 #43B27F","margin":"0 10px 10px 0","transform":"skew(-15deg, 0deg)","color":"#43B27F","borderRadius":"5px","background":"#fff","width":"auto","fontSize":"14px","height":"36px"}'
								size="small"
								@click="addOrUpdateHandler(scope.row)"
							>回复</el-button>
						</template>
					</el-table-column>
				</el-table>
			
				<el-pagination
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
					:current-page="pageIndex"
					:page-sizes="[10, 50, 100, 200]"
					:page-size="pageSize"
					:total="totalPage"
					:layout="layouts.join()"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					:style='{"padding":"0","boxShadow":"0px 2px 5px 0px rgba(0,0,0,0.3)","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","alignItems":"center","textAlign":"center","display":"flex","justifyContent":"center","overflow":"hidden","borderRadius":"30px","background":"#fff","width":"auto","fontSize":"inherit","fontWeight":"500"}'
				></el-pagination>
			</div>
		</div>
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
	</div>
</template>
<script>
	import AddOrUpdate from "./chat-add-or-update";
	import { setInterval, clearInterval } from 'timers';
	export default {
		data() {
			return {
				layouts: ["prev","pager","next"],
				searchForm: {
					isreply:1,
					isread: 0
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				showFlag: false,
				dataListSelections: [],
				inter: null,
			};
		},
		created() {
			var that = this;
			that.getDataList();
			var inter = setInterval(function(){
				that.getDataList();
			},5000);
			this.inter = inter;
		},
		destroyed(){
			clearInterval(this.inter);
		},
		components: {
			AddOrUpdate
		},
		methods: {
			askFormat(row, column) {
				if (row.ask && row.type==2) {
					return '[图片]'
				} else if(row.ask&&row.type==3) {
					return '[视频]'
				} else if(row.ask&&row.type==4) {
					return '[文件]'
				} else {
					return row.ask
				}
			},
			search() {
				this.pageIndex = 1
				this.getDataList()
			},
			getDataList() {
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
				}
				params.isreply = this.searchForm.isreply
				params.isread = this.searchForm.isread
				this.dataListLoading = true;
				this.$http({
					url: this.$api.chatpage,
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
			// 回复
			addOrUpdateHandler(row) {
				this.showFlag = true;
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(row.userid);
				});
			}
		}
	};
</script>
<style lang="scss" scoped>
	.table-content {
		background: transparent;
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
</style>

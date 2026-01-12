export default {
	baseUrl: 'http://localhost:8080/springbootx2vu108k/',
	name: '/springbootx2vu108k',
	indexNav: [
		{
			name: '家居信息',
			url: '/index/jiajuxinxi',
		},
		{
			name: '交流论坛',
			url: '/index/forum'
		}, 
		{
			name: '公告信息',
			url: '/index/news'
		},
		{
			name: '留言反馈',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '家居信息',
			refTable: 'jiajufenlei',
			refColumn: 'jiajufenlei',
		},
		{
			name: '家居购买',
			refTable: 'jiajufenlei',
			refColumn: 'jiajufenlei',
		},
	]
}

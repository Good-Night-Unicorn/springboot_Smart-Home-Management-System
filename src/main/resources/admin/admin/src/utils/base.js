const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootx2vu108k/",
            name: "springbootx2vu108k",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootx2vu108k/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于SpringBoot的智能家居管理系统设计与实现"
        } 
    }
}
export default base

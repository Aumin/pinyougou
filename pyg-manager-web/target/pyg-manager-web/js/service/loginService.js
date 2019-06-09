//定义服务
app.service("loginService",function($http){
	//查询所有
	this.loadLoginName = function(){
		return $http.get("../login/loadLoginName");
	};
	
	
});
//定义控制器
app.controller("loginController",function($scope, $controller,loginService) {
	//获取登录用户名
	$scope.loadLoginName = function(){
		loginService.loadLoginName().success(
			function(data){
				$scope.loginName = data.loginName;
			}	
		);
	}

})
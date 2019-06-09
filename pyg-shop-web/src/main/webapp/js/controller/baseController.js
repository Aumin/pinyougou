app.controller("baseController",function($scope){
	//重新加载列表 数据
	$scope.reloadList = function() {
		//切换页码
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}
	
	//分页控件配置
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 10,
		itemsPerPage : 10,
		perPageOptions : [ 10, 20, 30, 40, 50 ],
		onChange : function() {
			$scope.reloadList();//重新加载
		}
	};
	
	//初始化选中的品牌id
	$scope.selectIds = [];
	
	$scope.updateSelection = function($event,id){
		if($event.target.checked){
			$scope.selectIds.push(id);
		} 
		else{
			//取消选中品牌
			$scope.selectIds.splice($scope.selectIds.indexOf(id),1);
		}
	}
	
	$scope.jsonToString = function(jsonString,key){
		var json = JSON.parse(jsonString);
		var value = "";
		for(var i=0;i<json.length;i++){
			if(i>0) value += ",";
			
			value += json[i][key];
		}
		return value;
	}

})
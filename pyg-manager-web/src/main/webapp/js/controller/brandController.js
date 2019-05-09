//定义控制器
app.controller(
		"brandController",
		function($scope, brandService) {
			//查询所有函数
			$scope.findAll = function() {
				//使用内置服务发送请求
				brandService.findAll().success(function(data) {
					$scope.list = data;
				})

			};

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
			//分页
			$scope.findPage = function(page, rows) {
				brandService.findPage(page,rows).success(function(data) {
					$scope.list = data.rows;
					$scope.paginationConf.totalItems = data.total;//更新总记录数
				});
			};
			
			//添加函数
			$scope.add = function(){
							
				//内置服务发送请求
				brandService.add($scope.entity).success(function(data){
					//判断
					if(data.success){
						//刷新
						$scope.reloadList();
					}else{
						alert(data.message);
					}
				})
				
			};
			
			//根据id查询品牌
			$scope.findOne = function(id){
				//发送请求
				brandService.findOne(id).success(
					function(data){
						$scope.entity = data;
					}		
				)
			}
			
			//初始化对象
			$scope.searchEntity = {};
			
			//条件查询方法
			$scope.search = function(page,rows){
				
				//发送条件查询请求
				brandService.search(page,rows,$scope.searchEntity).success(
					function(data){
						$scope.list = data.rows;
						$scope.paginationConf.totalItems = data.total;//更新总记录数
					}		
				)
				
			}
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
			
			$scope.dele = function(){
				brandService.dele($scope.selectIds).success(
					function(data){
						if(data.success){
							$scope.reloadList();
						}else{
							alert("删除失败");
						}
					}		
				)
			}
			
			

		})
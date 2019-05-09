//定义服务
app.service("brandService",function($http){
	//查询所有
	this.findAll = function(){
		return $http.get("../brand/findAll");
	};
	
	//分页
	this.findPage = function(page,rows){
		return $http.get('../brand/findPage?page=' + page + '&rows='+ rows);
	};
	
	//添加
	this.add = function(entity){
		
		//定义方法名
		var methodName = "add";
		//判断如果id存在,是修改
		if(entity.id!=null){
			methodName="update";
		}		
		return $http.post("../brand/"+methodName,entity);
	};
	
	//id查询品牌
	this.findOne = function(id){
		return $http.get("../brand/findOne?id="+id);
	};
	
	//查询
	this.search = function(page,rows,searchEntity){
		return $http.post("../brand/search?page="+page+"&rows="+rows,searchEntity);
	};
	
	//删除
	this.dele = function(selectIds){
		return $http.get("../brand/dele?ids=" + selectIds);
	};
});
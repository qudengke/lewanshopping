<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function (){
		//$.get("",function(){},"json");
		$('#search_redis_class_1_select').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',    
		    textField:'flmch1',
		    width:80,
		    value:'请选择',
		    onSelect:function search_redis_get_class_2_select(){
		    	var class_1_id = $(this).combobox('getValue');
		    	$('#search_redis_class_2_select').combobox({    
		    	    url:"js/json/class_2_"+class_1_id+".js",    
		    	    valueField:'id',    
		    	    textField:'flmch2',   
		    	    width:80,
		    		value:'请选择',
		    		onSelect:function search_redis_get_attr_by_class_2_id(){
		    			var class_2_id = $(this).combobox('getValue');
		    			var class_2_name = $(this).combobox('getText');	
		    			$("#search_redis_list_inner").datagrid({
		    				url:"get_attr_by_class_2_json.do",
		    				queryParams:{class_2_id:class_2_id,class_2_name:class_2_name},
		    				columns:[[    
									   {field:'id',title:'属性id',width:100,checkbox:true},
		    				           {field:'shxm_mch',title:'属性名名称',width:100},    
		    				           {field:'shfqy',title:'是否启用',width:100},    
		    				           {field:'chjshj',title:'创建时间',width:200,formatter:function(value,row,index){
		    				        	   var d = new Date();
		    				        	   var dString = d.toLocaleString(value);
		    				        	   return dString;
		    				           }},
		    				           {field:'list_value',title:'属性值',width:300,formatter:function(value,row,index){
											var vals = "";
		    				        	   $(value).each(function(i,json){
												vals+=" "+json.shxzh+json.shxzh_mch;
											});
		    				        	   return vals;
		    				           }}
		    				       ]] 
		    			});
		    		}
		    	});  
			}
		}); 
	});

	function refresh_class_2(){
		var class_2_id = $("#search_redis_class_2_select").combobox("getValue");
		var class_2_name = $("#search_redis_class_2_select").combobox("getText");
		$.post("refresh_class_2.do",{class_2_id:class_2_id},function(data){
			alert("您总共刷新了"+data+"条"+class_2_name+"二级分类的商品数据");
		});
	}
	
	function refresh_attr(){
		var class_2_id = $("#search_redis_class_2_select").combobox("getValue");
	 	var attr = $("#search_redis_list_inner").datagrid("getChecked");
	 	var attr_array = new Array();
	 	$(attr).each(function(i,json){
	 		attr_array[i] = json.id;
	 	});
		
	 	$.post("refresh_attr.do",{class_2_id:class_2_id,attr_array:attr_array},function(data){
	 		alert("您总共刷新了"+data+"条分类属性的商品数据");
		}); 
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="height:500px">
		<div data-options="region:'north',split:true" style="height:50px">
			缓存检索<br>
		</div>
		<div data-options="region:'west',split:true" style="width:100px">
			<select class="easyui-combobox" name="flbh1" id="search_redis_class_1_select"></select><br>
			<select class="easyui-combobox" name="flbh2" id="search_redis_class_2_select"></select><br>
		</div>
		
		<div data-options="region:'center'">
			
			<div id="search_redis_list_inner" class="easyui-datagrid">
				
			</div>	
			<a href="javascript:;" onclick="refresh_class_2()">刷新二级分类的商品集合</a><br>
			<a href="javascript:;" onclick="refresh_attr()">刷新分类属性的商品集合</a>
		</div>

	</div>
</body>
</html>
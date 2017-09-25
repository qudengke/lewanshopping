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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	$(function(){
		var url = "${url}";
		var title = "${title}";
		if(url!=""&&title!=""){
			index_add_tabs(url,title);
		}
	});
	function index_add_tabs(url,title){
		var b = $('#index_tt').tabs('exists',title);
		if(!b){
			$.post(url,function(data){
				$('#index_tt').tabs('add',{    
				    title:title,    
				    content:data,    
				    closable:true
				}); 
			});
		}else{
			$('#index_tt').tabs('select',title);
		}

	}
	
	function index_add_tabs2(url,title){
		$('#index_tt').tabs('add',{    
		    title:title,    
		    href:url,    
		    closable:true
		}); 
	}
</script>
<title>硅谷商城后台</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<ul class="easyui-tree">
			<li>
				<span>系统管理</span>
				<ul>
					<li><a style="text-decoration:none;" href="javascript:index_add_tabs('goto_spu.do','spu信息');" >spu信息</a></li>
					<li><a style="text-decoration:none;" href="javascript:index_add_tabs('goto_attr.do','分类属性');">分类属性</a></li>
					<li><a style="text-decoration:none;" href="javascript:index_add_tabs('goto_sku.do','库存信息');">库存信息</a></li>
					<li>
						<span>接口管理</span>
						<ul>
							<li>仓库管理</li>
							<li>物流管理</li>
							<li><a href="javascript:;" onclick="index_add_tabs()">测试管理</a></li>
						</ul>
					</li>
				</ul>
			</li>
			
			<li>
				<span>缓存管理</span>
				<ul>
					<li><a href="javascript:index_add_tabs('goto_search_redis.do','商品缓存');">商品缓存</a></li>
					<li>用户缓存</li>
				</ul>
			</li>
		</ul>
		

	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center',iconCls:'icon-ok'">
		<div id="index_tt" class="easyui-tabs" >
			
		</div>
	</div>
	
</body>
</html>
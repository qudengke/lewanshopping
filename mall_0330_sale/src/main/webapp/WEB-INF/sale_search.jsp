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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function search_attr_up(shxm_id,shxzh_id,shxm_mch){
		$("#search_attr_"+shxm_id).hide();
		$("#search_attr_show").append("<div id='search_attr_show_id"+shxm_id+"'><a href='javascript:search_attr_down("+shxm_id+");'>"+shxm_mch+"</a><input type='text' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}' name='search_attr_id_arry'><div>");
		search_get_sku_by_attr();
	}
	
	function search_attr_down(shxm_id){
		$("#search_attr_"+shxm_id).show();
		$("#search_attr_show_id"+shxm_id).remove();
		search_get_sku_by_attr();
	}
	
	function search_get_sku_by_attr(){
		
		var list_av = $("#search_attr_show input[name='search_attr_id_arry']");

		// 数组
		// var list_av_array = new Array();
		var list_av_string = "";
		$(list_av).each(function(i,json){
			var json_obj = $.parseJSON(json.value);
			list_av_string+="list_av["+i+"].shxm_id="+json_obj.shxm_id+"&list_av["+i+"].shxzh_id="+json_obj.shxzh_id+"&";
		});
		
		var class_2_id = ${class_2_id};
		var order = $("#search_order_show").val();
		list_av_string+= "class_2_id="+class_2_id+"&order="+order;
		$.ajax({
			url:"get_sku_by_attr.do",
			data:list_av_string,
			success:function(data){
				$("#search_inner").html(data);
			}
		});
	}
	
	function search_change_order(new_order){
		var old_order = $("#search_order_show").val();
		
		if(new_order==old_order){
			//客户端点击了上一次点击过的排序关键字
			new_order += " desc ";
		}
		
		 $("#search_order_show").val(new_order);
		search_get_sku_by_attr();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<jsp:include page="sale_header.jsp"></jsp:include>
	检索列表:
	<div id="search_attr_show"></div>
	<jsp:include page="sale_miniCart.jsp"></jsp:include>
	<input type="hidden" id="search_order_show" value=" order by jg "/>
	<hr>
	${class_2_id}属性列表<br>
	<c:forEach items="${list_attr}" var="attr">
		<div id="search_attr_${attr.id}">
			${attr.shxm_mch}:
			<c:forEach items="${attr.list_value}" var="val">
				<a href="javascript:search_attr_up(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}');">${val.shxzh}${val.shxzh_mch}</a>
			</c:forEach>
			<br>
		</div>
	</c:forEach>
	<hr>
	<a href="javascript:search_change_order(' order by jg ');">价格</a>  
	<a href="javascript:search_change_order(' order by sku_xl ');">销量 </a>  
	<a href="javascript:search_change_order(' order by sku.chjshj ');">上架时间 </a> 
	<a href="javascript:search_change_order(' order by plsh ');">评论数</a> 
	<hr>
	<div id="search_inner">
		<jsp:include page="sale_search_inner.jsp"></jsp:include>
	</div>
</body>
</html>
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
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body>
<jsp:include page="sale_header.jsp"></jsp:include>
	商品详情页面
	<jsp:include page="sale_miniCart.jsp"></jsp:include>
	<hr>
	<img src="upload/image/${obj_sku.spu.shp_tp}" width="100px"/>
	<br>
	${obj_sku.sku_mch}<br>
	${obj_sku.jg}<br>
	${obj_sku.kc}<br>
	<hr>
	<c:forEach items="${list_sku}" var="sku">
		<a href="get_sku_by_id.do?sku_id=${sku.id}&spu_id=${sku.shp_id}">${sku.sku_mch}</a><br>
	</c:forEach>
	<hr>
	<form action="add_cart.do" method="post">
		<input type="hidden" name="sku_mch" value="${obj_sku.sku_mch}" />
		<input type="hidden" name="sku_jg" value="${obj_sku.jg}" />
		<input type="hidden" name="tjshl" value="1" />
		<input type="hidden" name="hj" value="${obj_sku.jg}" />
		<input type="hidden" name="shp_id" value="${obj_sku.spu.id}" />
		<input type="hidden" name="sku_id" value="${obj_sku.id}" />
		<input type="hidden" name="shp_tp" value="${obj_sku.spu.shp_tp}" />
		<input type="hidden" name="shfxz" value="1" />
		<input type="hidden" name="kcdz" value="${obj_sku.kcdz}" />
		<input type="submit" value="添加购物车"/>
	</form>
	<hr>
	<c:forEach items="${obj_sku.list_av_name}" var="av">
		${av.shxm_mch}:${av.shxzh_mch}
	</c:forEach>
	<hr>
	${obj_sku.spu.shp_msh}<br>
	<c:forEach items="${obj_sku.list_image}" var="img">
		<img src="upload/image/${img.url}" width="100px"/>
	</c:forEach>
</body>
</html>
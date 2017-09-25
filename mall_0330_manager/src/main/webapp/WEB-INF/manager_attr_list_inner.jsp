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
	function b(){}
</script>
<title>硅谷商城后台</title>
</head>
<body>
	${class_2_id}属性列表<br>
	<c:forEach items="${list_attr}" var="attr">
		${attr.shxm_mch}:
		<c:forEach items="${attr.list_value}" var="val">
			${val.shxzh}${val.shxzh_mch}
		</c:forEach>
		<br>
	</c:forEach>
	<a href="javascript:index_add_tabs('goto_add_attr.do?class_2_id=${class_2_id}&class_2_name=${class_2_name}','添加分类属性');">添加分类属性</a>
</body>
</html>
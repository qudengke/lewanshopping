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
	var attr_add_table_index = 0;
	function attr_add_table(){
		var a="<hr>";
		var b='<table border="1" width="500px;" id="attr_add_table_'+(attr_add_table_index+1)+'">';
		var c='<tr><td>属性名:<input name="list_attr['+(attr_add_table_index+1)+'].shxm_mch" type="text" /></td><td><a href="javascript:;" onclick="attr_add_tr('+(attr_add_table_index+1)+')">添加属性值</a></td></tr>';
		var d='<tr><td>属性值:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[0].shxzh" type="text" /></td><td>单位:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[0].shxzh_mch"  type="text" /></td></tr>';
		var e='<tr><td>属性值:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[1].shxzh" type="text" /></td><td>单位:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[1].shxzh_mch" type="text" /></td></tr>';
		var f='</table>';
		$("#attr_add_table_"+attr_add_table_index).after(a+b+c+d+e+f);
		attr_add_table_index++;
	}
	
	function attr_add_tr(index){
		var tr_index = $("#attr_add_table_"+index+" tr").length - 1;
		var tr = '<tr><td>属性值:<input name="list_attr['+index+'].list_value['+tr_index+'].shxzh" type="text" /></td><td>单位:<input name="list_attr['+index+'].list_value['+tr_index+'].shxzh_mch" type="text" /></td></tr>';
		$("#attr_add_table_"+index).append(tr);
	}
</script>
<title>硅谷商城后台</title>
</head>
<body>
	${class_2_name}添加页面<br>
	${success}<br>
	<a href="javascript:;" onclick="attr_add_table()">添加属性选项</a><br>
	<form action="save_attr.do" method="post">
		<input type="hidden" name="class_2_id" value="${class_2_id}" />
		<input type="hidden" name="class_2_name" value="${class_2_name}" />
		<table border="1" width="500px;" id="attr_add_table_0">
			<tr><td>属性名:<input name="list_attr[0].shxm_mch" type="text" /></td><td><a href="javascript:;" onclick="attr_add_tr(0)">添加属性值</a></td></tr>
			<tr><td>属性值:<input name="list_attr[0].list_value[0].shxzh" type="text" /></td><td>单位:<input name="list_attr[0].list_value[0].shxzh_mch"  type="text" /></td></tr>
			<tr><td>属性值:<input name="list_attr[0].list_value[1].shxzh" type="text" /></td><td>单位:<input name="list_attr[0].list_value[1].shxzh_mch" type="text" /></td></tr>
		</table>
		
		<input type="submit" value="提交"/>
	</form>
</body>
</html>
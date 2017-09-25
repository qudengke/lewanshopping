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
	$(function(){
		$("#attr_class_1_select").combobox({
		    url:'js/json/class_1.js',    
		    valueField:'id',    
		    textField:'flmch1',
		    width:100,
		    value:'请选择',
		    onSelect:function attr_get_class_2_by_class_1(){
				var class_1_id = $(this).combobox('getValue');
				$("#attr_class_2_select").combobox({
				    url:'js/json/class_2_'+class_1_id+'.js',    
				    valueField:'id',    
				    textField:'flmch2',
				    width:100,
				    value:'请选择',
				    onSelect:function attr_get_attr_by_class_2(){
				    	var class_2_id = $(this).combobox('getValue');
						var class_2_name = $(this).combobox('getText');
						$.post("get_attr_by_class_2.do",{class_2_id:class_2_id,class_2_name:class_2_name},function(data){
							$("#attr_list_inner").html(data);
						});
						
						$('#attr_list_inner').datagrid({    
						    url:'get_attr_by_class_2_json.do',  
						    queryParams:{class_2_id:class_2_id,class_2_name:class_2_name},
						    columns:[[    
						        {field:'shxm_mch',title:'属性名',width:100},    
						        {field:'shfqy',title:'是否启用',width:100},    
						        {field:'chjshj',title:'创建时间',width:150,
						        	formatter: function(value,row,index){
						        		var datetime = new Date(value);
						        		var date = datetime.toLocaleString();
						        		//将时间格式化
										return date;
									}	
						        }, 
						        {field:'list_value',title:'属性值',width:300,
						        	formatter: function(value,row,index){
						        		console.log(value);
						        		var val="";
						        		$(value).each(function (i,json){
						        			val+=" "+json.shxzh+json.shxzh_mch;
						        		});
						        		return val;
									}	
						        }
						    ]]    
						}); 
					}
				});
			}
		});
	});


	
</script>
<title>硅谷商城后台</title>
</head>
<body>
	<select class="easyui-combobox" name="flbh1" id="attr_class_1_select" >
	</select>
	<select class="easyui-combobox" name="flbh2" id="attr_class_2_select" >
		<option>请选择</option>
	</select>
	
	<div class="easyui-datagrid" id="attr_list_inner"></div>
</body>
</html>
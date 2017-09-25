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
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#spu_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");	
			});
		});
	});
	
	function spu_get_class_2_by_class_1(class_1_id){
		//var class_1_id = $("#spu_class_1_select").val();
		//$("spu_class_1_select option:selected").val();
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#spu_class_2_select").empty();
			$(data).each(function(i,json){
				$("#spu_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");	
			});
		});
		spu_get_tm_by_class_1(class_1_id);
	}
	
	function spu_get_tm_by_class_1(class_1_id){
		$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
			$("#spu_tm_select").empty();
			$(data).each(function(i,json){
				$("#spu_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");	
			});
		});
	}
	
	function spu_click_image(index){
		$("#spu_file_"+index).click();

	}
	
	function spu_change_image(index){
		var file = $("#spu_file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(file);
		$("#spu_img_"+index).attr("src",url);
		if(index>=2&&(index+1) == $("#spu_images input").length){
			spu_append_image(index+1);
		}
	}
	
	function spu_append_image(index){
		var img = '<img id="spu_img_'+index+'" onclick="spu_click_image('+index+')" style="cursor:pointer;" src="image/upload_hover.png" width="70px;"/>';
		var file='<input id="spu_file_'+index+'" onChange="spu_change_image('+index+')" style="display:none;" type="file" name="files" /><br>';
		$("#spu_images").append(img+file);
	}
</script>
<title>硅谷商城后台</title>
</head>
<body>

<form action="save_spu.do" enctype="multipart/form-data" method="post">

	<div class="easyui-layout" data-options="fit:true" style="height:700px">
		<div data-options="region:'north',split:true" style="height:100px">
			spu信息管理页面<br>
			${success}<br>
			<select name="flbh1" id="spu_class_1_select" onChange="spu_get_class_2_by_class_1(this.value)"></select>
			<select name="flbh2" id="spu_class_2_select"></select>
			<select name="pp_id" id="spu_tm_select"></select>
		</div>
		<div data-options="region:'west',split:true" style="width:150px">
			spu名称：<input type="text" name="shp_mch"/><br>
			spu描述：<textarea name="shp_msh" rows="10" cols="10">
			
			        </textarea><br>
		</div>
		<div data-options="region:'center'">
			spu图片：<br>
			<div id="spu_images">
				<img id="spu_img_0" onclick="spu_click_image(0)" style="cursor:pointer;" src="image/upload_hover.png" width="70px;"/>
				<img id="spu_img_1" onclick="spu_click_image(1)" style="cursor:pointer;" src="image/upload_hover.png" width="70px;"/>
				<img id="spu_img_2" onclick="spu_click_image(2)" style="cursor:pointer;" src="image/upload_hover.png" width="70px;"/>
				<input id="spu_file_0" onChange="spu_change_image(0)" style="display:none;" type="file" name="files" /><br>
				<input id="spu_file_1" onChange="spu_change_image(1)" style="display:none;" type="file" name="files" /><br>
				<input id="spu_file_2" onChange="spu_change_image(2)" style="display:none;" type="file" name="files" /><br>
			</div>
			<input  type="submit" value="提交">
		</div>
	</div>


</form>
</body>
</html>
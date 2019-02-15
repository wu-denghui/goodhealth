<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统</title>
</head>
<body>
<c:choose>
<c:when test="${mangerStatus==2 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div  align="center">
<table border="2">
<caption><font size="6">药品管理</font> </caption>
<tr><td>添加药品</td><td>
<form action="${pageContext.request.contextPath  }/page/addDrug" method="post">
<input  type="submit" name="g" value="添加药品"></form></td>
</tr>
<tr>
<td>更新药品信息</td><td>请按药品名搜索出您要更新的药品：
<form action="${pageContext.request.contextPath }/drug/searchForUpdate" method="post">
搜索：<input type="text" name="searchMess"   value="以药品名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
<tr><td>下架药品</td><td>请按药品名搜索出您要下架的药品：
<form action="${pageContext.request.contextPath }/drug/searchForDelete" method="post">
搜索：<input type="text" name="searchMess"   value="以药品名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
</table>
</div>

</c:when>
<c:when test="${mangerStatus==3 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div  align="center">
<table border="2">
<caption><font size="6">积分商品管理</font> </caption>
<tr><td>添加积分商品</td><td>
<form action="${pageContext.request.contextPath }/page/addPrize" method="post">
<input  type="submit" name="g" value="添加积分商品"></form></td>
</tr>
<tr>
<td>更新积分商品信息</td><td>请按积分商品搜索出您要更新的积分商品：
<form action="${pageContext.request.contextPath }/prize/searchForUpdate" method="post">
搜索：<input type="text" name="searchMess"   value="以积分商品名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
<tr><td>下架积分商品</td><td>请按积分商品搜索出您要下架的积分商品：
<form action="${pageContext.request.contextPath }/prize/searchForDelete" method="post">
搜索：<input type="text" name="searchMess"   value="以积分商品名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
</table>
</div>
</c:when>
<c:when test="${mangerStatus==4 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div  align="center">
<table border="2">
<caption><font size="6">健康资讯管理</font> </caption>
<tr><td>添加健康资讯</td><td>
<form action="${pageContext.request.contextPath }/page/addNews" method="post">
<input  type="submit" name="g" value="添加健康资讯"></form></td>
</tr>
<tr>
<td>更新健康资讯信息</td><td>请按健康资讯名搜索出您要更新的健康资讯：
<form action="${pageContext.request.contextPath }/news/searchForUpdate" method="post">
搜索：<input type="text" name="searchMess"   value="以健康资讯名模糊匹配搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
<tr><td>删除健康资讯</td><td>请按健康资讯名搜索出您要删除的健康资讯：
<form action="${pageContext.request.contextPath }/news/searchForDelete" method="post">
搜索：<input type="text" name="searchMess"   value="以健康资讯名模糊匹配搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="submit" name="g" value="搜索">
</form></td></tr>
</table>
</div>
</c:when>
</c:choose>
<script>
var  change=function (obj){
	obj.value="";
}
</script>
</body>
</html>
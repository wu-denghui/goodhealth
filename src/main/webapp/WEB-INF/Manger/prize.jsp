<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统——积分奖品管理</title>
</head>
<body>
<c:choose>
<c:when test="${prizeManger==1 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<c:choose>
<c:when test="${ not empty drug }">
<c:set  var="prizeValue"  value="${prize.prizeValue }"></c:set>
</c:when>
<c:otherwise>
<c:set  var="prizeValue"  value="0"></c:set>
</c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath }/prize/addPrize"   method="post">
<table border="2">
<caption>添加积分奖品</caption>
<tr><td>积分奖品名:</td><td><input  type="text"  name="prizeName"  value="${prize.prizeName }"></td></tr>
<tr><td>积分奖品价值积分:</td><td><input  type="text"  name="prizeValue"  value="${prizeValue }"></td></tr>
<tr><td>积分奖品图片:</td><td> <input type="file" name="prizePic"></td></tr>
<tr><td><input type="submit" name="g"  value="添加"></td></tr>
</table>
</form>
</div>
</c:when>
<c:when test="${prizeManger==2 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<form action="${pageContext.request.contextPath }/prize/editPrize"   method="post">
<input  type="hidden"  name="id"  value="${prize.prizeId }">
<table border="2">
<caption>更新积分奖品信息</caption>
<tr><td>积分奖品名:</td><td><input  type="text"  name="prizeName"  value="${prize.prizeName }" disabled="disabled"></td></tr>
<tr><td>积分奖品价值积分:</td><td><input  type="text"  name="prizeValue"  value="${prize.prizeValue }"></td></tr>
<tr><td>积分奖品图片:</td></tr>
<tr><td><img alt="积分奖品图片" src="http://localhost:8080/images/prize/${prize.prizePic }" width="44" height="55"></td>
<td> <input type="text" name="prizePic"  value="${prize.prizePic }"></td></tr>
<tr><td><input type="submit" name="g"  value="确认修改"></td></tr>
</table>
</form>
</div>
</c:when>
<c:when test="${prizeManger==3 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<form action="${pageContext.request.contextPath }/prize/deletePrize"   method="post">
<input  type="hidden"  name="id"  value="${prize.prizeId }"> 
<table border="2">
<caption>删除积分奖品</caption>
<tr><td>积分奖品名:</td><td><input  type="text"  name="prizeName"  value="${prize.prizeName }" disabled="disabled"></td></tr>
<tr><td>积分奖品价值积分:</td><td><input  type="text"  name="prizeValue"  value="${prize.prizeValue }"disabled="disabled"></td></tr>
<tr><td>积分奖品图片:</td><td><img alt="积分奖品图片" src="http://localhost:8080/images/prize/${prize.prizePic }" width="44" height="55"></td></tr>
<tr><td><input type="submit" name="g"  value="删除"></td></tr>
</table>
</form>
</div>
</c:when>
</c:choose>
</body>
</html>
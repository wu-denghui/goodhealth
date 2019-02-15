<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统——药品管理</title>
</head>
<body>
<c:choose>
<c:when test="${drugManger==1 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<c:choose>
<c:when test="${ not empty drug }">
<c:set  var="drugIntegral"  value="${drug.drugIntegral }"></c:set>
</c:when>
<c:otherwise>
<c:set  var="drugIntegral"  value="0"></c:set>
</c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath }/drug/addDrug"   method="post">
<table border="2">
<caption>添加药品</caption>
<tr><td>药品名:</td><td><input  type="text"  name="drugName"  value="${drug.drugName }"></td></tr>
<tr><td>药品价格:</td><td><input  type="text"  name="drugPrice"  value="${drug.drugPrice }"></td></tr>
<tr><td>药品价值积分:</td><td><input  type="text"  name="drugIntegral"  value="${drugIntegral}"></td></tr>
<tr><td>药品生成商:</td><td><input  type="text"  name="drugProductor"  value="${drug.drugProductor }"></td></tr>
<tr><td>药品主治功能:</td><td><input  type="text"  name="drugFunction"  value="${drug.drugFunction }"></td></tr>
<tr><td>药品成分:</td><td><input  type="text"  name="drugComponent"  value="${drug.drugComponent }"></td></tr>
<tr><td>药品性状:</td><td><input  type="text"  name="drugCharacter"  value="${drug.drugCharacter }"></td></tr>
<tr><td>药品用量:</td><td><input  type="text"  name="drugUsage"  value="${drug.drugUsage }"></td></tr>
<tr><td>药品贮藏方式:</td><td><input  type="text"  name="drugStorage"  value="${drug.drugStorage }"></td></tr>
<tr><td>药品图片:</td><td> <input type="file" name="drugPic"></td></tr>
<tr><td><input type="submit" name="g"  value="添加"></td></tr>
</table>
</form>
</div>
</c:when>
<c:when test="${drugManger==2 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<form action="${pageContext.request.contextPath }/drug/editDrug"   method="post">
<input  type="hidden"  name="id"  value="${drug.drugId}">
<table border="2">
<caption>更新药品信息</caption>
<tr><td>药品名:</td><td><input  type="text"  name="drugName"  value="${drug.drugName }" disabled="disabled"></td></tr>
<tr><td>药品价格:</td><td><input  type="text"  name="drugPrice"  value="${drug.drugPrice }"></td></tr>
<tr><td>药品价值积分:</td><td><input  type="text"  name="drugIntegral"  value="${drug.drugIntegral }"></td></tr>
<tr><td>药品生成商:</td><td><input  type="text"  name="drugProductor"  value="${drug.drugProductor }"></td></tr>
<tr><td>药品主治功能:</td><td><input  type="text"  name="drugFunction"  value="${drug.drugFunction }"></td></tr>
<tr><td>药品成分:</td><td><input  type="text"  name="drugComponent"  value="${drug.drugComponent }"></td></tr>
<tr><td>药品性状:</td><td><input  type="text"  name="drugCharacter"  value="${drug.drugCharacter }"></td></tr>
<tr><td>药品用量:</td><td><input  type="text"  name="drugUsage"  value="${drug.drugUsage }"></td></tr>
<tr><td>药品贮藏方式:</td><td><input  type="text"  name="drugStorage"  value="${drug.drugStorage }"></td></tr>
<tr><td>药品图片:</td></tr>
<tr><td><img alt="药品图片" src="http://localhost:8080/images/drug/${drug.drugPic }" width="44" height="55"></td>
<td> <input type="text" name="drugPic"  value="${drug.drugPic }"></td></tr>
<tr><td><input type="submit" name="g"  value="确认修改"></td></tr>
</table>
</form>
</div>
</c:when>
<c:when test="${drugManger==3 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<form action="${pageContext.request.contextPath }/drug/deleteDrug"   method="post">
<input  type="hidden"  name="id"  value="${drug.drugId }"> 
<table border="2">
<caption>删除药品</caption>
<tr><td>药品名:</td><td><input  type="text"  name="drugName"  value="${drug.drugName }" disabled="disabled"></td></tr>
<tr><td>药品价格:</td><td><input  type="text"  name="drugPrice"  value="${drug.drugPrice }"disabled="disabled"></td></tr>
<tr><td>药品价值积分:</td><td><input  type="text"  name="drugIntegral"  value="${drug.drugIntegral }"disabled="disabled"></td></tr>
<tr><td>药品生成商:</td><td><input  type="text"  name="drugProductor"  value="${drug.drugProductor }"disabled="disabled"></td></tr>
<tr><td>药品主治功能:</td><td><input  type="text"  name="drugFunction"  value="${drug.drugFunction }"disabled="disabled"></td></tr>
<tr><td>药品成分:</td><td><input  type="text"  name="drugComponent"  value="${drug.drugComponent }"disabled="disabled"></td></tr>
<tr><td>药品性状:</td><td><input  type="text"  name="drugCharacter"  value="${drug.drugCharacter }"disabled="disabled"></td></tr>
<tr><td>药品用量:</td><td><input  type="text"  name="drugUsage"  value="${drug.drugUsage }"disabled="disabled"></td></tr>
<tr><td>药品贮藏方式:</td><td><input  type="text"  name="drugStorage"  value="${drug.drugStorage }" disabled="disabled"></td></tr>
<tr><td>药品图片:</td><td><img alt="药品图片" src="http://localhost:8080/images/drug/${drug.drugPic }" width="44" height="55"></td></tr>
<tr><td><input type="submit" name="g"  value="删除"></td></tr>
</table>
</form>
</div>
</c:when>
</c:choose>
</body>
</html>
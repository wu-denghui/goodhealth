<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统——健康资讯管理</title>
</head>
<body>
<c:choose>
<c:when test="${newsManger==1 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<form action="${pageContext.request.contextPath }/news/addNews"   method="post">
<table border="2">
<caption>添加健康资讯</caption>
<tr><td>健康资讯标题:</td><td><input  type="text"  name="newTitle"  value="${news.newTitle }"></td></tr>
<tr><td>发布者:</td><td><input  type="text"  name="newAuthor"  value="${news.newAuthor }"></td></tr>
<tr><td>链接:</td><td><input  type="text"  name="newDetail"  value="${news.newDetail }"></td></tr>
<tr><td>发布时间:</td><td><input  type="text"  name="newDate"  value="${news.newDate }"></td></tr>
<tr><td><input type="submit" name="g"  value="添加"></td></tr>
</table>
</form>
</div>
</c:when>
<c:when test="${newsManger==2 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>/
</c:forEach>
</c:if>
<table border="2">
<caption>更新健康资讯</caption>
<c:forEach  var="news"  items="${newsList }">
<form action="${pageContext.request.contextPath }/news/editNews"   method="post">
<input  type="hidden"  name="id"  value="${news.newId }">
<tr><td>健康资讯标题:</td><td><input  type="text"  name="newTitle"  value="${news.newTitle }"></td></tr>
<tr><td>发布者:</td><td><input  type="text"  name="newAuthor"  value="${news.newAuthor }"></td></tr>
<tr><td>链接:</td><td><input  type="text"  name="newDetail"  value="${news.newDetail }"></td></tr>
<tr><td>发布时间:</td><td><input  type="text"  name="newDate"  value="${news.newDate }"></td></tr>
<tr><td><input type="submit" name="g"  value="确认更新"></td></tr>
</form>
</c:forEach>
</table>
</div>
</c:when>
<c:when test="${newsManger==3 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div align="center">
<table border="2">
<caption>删除健康资讯</caption>
<c:forEach  var="news"  items="${newsList }">
<form action="${pageContext.request.contextPath }/news/deleteNews"   method="post">
<input  type="hidden"  name="id"  value="${news.newId }"> 
<tr><td>健康资讯标题:</td><td><input  type="text"  name="newTitle"  value="${news.newTitle }" disabled="disabled"></td></tr>
<tr><td>发布者:</td><td><input  type="text"  name="newAuthor"  value="${news.newAuthor }" disabled="disabled"></td></tr>
<tr><td>链接:</td><td><input  type="text"  name="newDetail"  value="${news.newDetail }" disabled="disabled"></td></tr>
<tr><td>发布时间:</td><td><input  type="text"  name="newDate"  value="${news.newDate }" disabled="disabled"></td></tr>
<tr><td><input type="submit" name="g"  value="确认删除"></td></tr>
</form>
</c:forEach>
</table>
</div>
</c:when>
</c:choose>
</body>
</html>
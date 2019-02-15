<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include  page="/goodhealth/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>健康社区</title>
</head>
<body>
<div align="center">
<table border="2">
<caption><font size="6">健康资讯</font> </caption>
<c:forEach  var="news"  items="${newsList}" >
<tr><td><a href="${news.newDetail }">${news.newTitle}</a></td></tr>
</c:forEach>
</table>
</div>
<!-- 分页功能 start -->
	<div align="center">
	<c:if test="${not empty newsList }">
		<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/news/findAll/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/news/findAll/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/news/findAll/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/news/findAll/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/news/findAll/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/news/findAll/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/news/findAll/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/news/findAll/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/news/intoPage"  Method= "post">
      跳转到:<input type="text" style="width:30px" name="into" />页 
   <input type="hidden"  name="max"  value="${max}">
   <input type="hidden"  name="index"  value="${index}">
   <Input type=submit name= "跳转" value="跳转"> 
   </form>
	<!-- 分页功能 End -->
	</c:if>
	</div>

</body>
</html>
<%@page import="org.springframework.context.annotation.Import,javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>精选药品</title>
 <jsp:include page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
<div align="center">
<table border="2">
<caption><font size="6">药品列表</font> </caption>
<tr><th>样品图片</th><th>药品名称</th><th>零售价</th><th>购买赠送积分</th><th>功能</th>
<th>用量</th><th>查看更多</th><th>加入购物车</th></tr>
<c:forEach  var="drug"  items="${drugAllList}" >
<tr><td><img onmouseover="enlarge(this)" onmouseout="reduction(this)" alt="药品图片" src="http://localhost:8080/images/drug/${drug.drugPic }" width="44" height="55">
<td><c:out value="${drug.drugName }"></c:out></td>
<td><c:out value="${drug.drugPrice}"></c:out></td>
<td><c:out value="${drug.drugIntegral }"></c:out></td>
<td><c:out value="${drug.drugFunction }"></c:out></td>
<td><c:out value="${drug.drugUsage }"></c:out></td>
<c:url  var="linkC" value="http://localhost:8080/drug/intoShoppingCar">
<c:param name="id" value="${drug.drugId }"></c:param>
<c:param name="f" value="${index }"></c:param>
</c:url>
<c:url  var="linkS" value="http://localhost:8080/drug/showDetail">
<c:param name="id" value="${drug.drugId }"></c:param>
</c:url>
<td><a href="${linkS }">详细说明书</a></td>
<td><a href="${linkC }">加入购物车</a></td>
</tr>
</c:forEach>
</table>
</div>
	<script type="text/javascript">
		var boo=undefined;
		var pheight,pwidth;
		var enlarge=function(o){
			    boo=true;
			    picchange(o);
			}
	    var reduction=function(o){
			    boo=false;
			    picchange(o);
			}
		var picchange=function(obj){

		 if (boo) {
			pwidth=obj.width;
			pheight=obj.height;
			obj.width=2*pwidth;
			obj.height=2*pheight;
		  }else{
		  	pwidth=obj.width;
			pheight=obj.height;
		    obj.height = pheight/2;
            obj.width = pwidth/2;
		  }
		}		
	</script>
<!-- 分页功能 start -->
	<div align="center">
	<c:if test="${not empty drugAllList }">
		<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/drug/findAll/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/drug/findAll/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/drug/findAll/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/drug/findAll/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/drug/findAll/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/drug/findAll/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/drug/findAll/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/drug/findAll/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/drug/intoPage"  Method= "post">
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
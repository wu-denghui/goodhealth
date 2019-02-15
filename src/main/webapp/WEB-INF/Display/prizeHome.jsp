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
<title>奖品兑换页</title>
 <jsp:include page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
<div align="center">
<font  color="red"  size="4">如要兑换积分商品，请在订单结算数时兑换，此页为积分商品展示页</font>
<table border="2">
<caption><font size="6">积分奖品列表</font> </caption>
<tr><th>样品图片</th><th>奖品名称</th><th>兑换所需积分</th></tr>
<c:forEach  var="prize"  items="${prizeList}" >
<tr>
<td><img onmouseover="enlarge(this)" onmouseout="reduction(this)" alt="奖品图片" src="http://localhost:8080/images/prize/${prize.prizePic }" width="44" height="55">
<td><c:out value="${prize.prizeName }"></c:out></td>
<td><c:out value="${prize.prizeValue }"></c:out></td>
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
	<c:if test="${not empty prizeList }">
		<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/prize/findAll/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/prize/findAll/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/prize/findAll/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/prize/findAll/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/prize/findAll/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/prize/findAll/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/prize/findAll/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/prize/findAll/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/prize/intoPage"  Method= "post">
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
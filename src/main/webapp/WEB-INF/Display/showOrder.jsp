<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<jsp:include page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
<div align="center">
<c:choose>
<c:when test="${  empty orderList }">
<h2>您还没有未完成的订单</h2>
</c:when>
<c:otherwise>
<table border="2">
<caption><font size="4">订单列表</font> </caption>
<tr><th>详细信息</th><th>订单金额</th><th>订单奖励积分</th><th>订单生成时间</th><th>结算订单</th></tr>
<c:forEach  var="order"  items="${orderList}" >
<tr>
<td><c:out value="${order.orderDetail }"></c:out></td>
<td><c:out value="${order.orderCount}"></c:out></td>
<td><c:out value="${order.orderAward }"></c:out></td>
<td><c:out value="${order.orderDate }"></c:out></td>
<c:url  var="link" value="http://localhost:8080/order/payOrder/${order.orderId }" ></c:url>
<td><a  href="${link}" >结算支付</a></td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
<c:if test="${not  empty  orderList }">
</c:if>
</div>

<!-- 分页功能 start -->
	<div align="center">
	<c:if test="${not   empty orderList }">
		<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/order/showOrder/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/order/showOrder/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/order/showOrder/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/order/showOrder/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/order/showOrder/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/order/showOrder/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/order/showOrder/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/order/showOrder/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/order/intoPage"  Method= "post">
      跳转到:<input type="text" style="width:30px" name="into" />页 
   <input type="hidden"  name="max"  value="${max}">
   <input type="hidden"  name="index"  value="${index}">
   <Input type=submit name= "跳转" value="跳转"> 
   </form>
   </c:if>
	<!-- 分页功能 End -->
	</div>
</body>
</html>
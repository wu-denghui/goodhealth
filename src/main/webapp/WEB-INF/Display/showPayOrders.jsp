<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
 <jsp:include page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
	<br>
	<br>
	<div align="center">
		<c:if test="${ allErrors!=null }">
		<c:forEach var="error" items="${allErrors }">
	${error.defaultMessage }
	</c:forEach>
	</c:if>
	<c:if test="${not  empty  error}">
	${error}
	</c:if>
	
		<form action="${pageContext.request.contextPath }/prize/showPrize/0">
		<input type="hidden" name="orderId" value="${order.orderId}">
			<font  color="red"> 是否要兑换积分商品(兑换的商品将与订单一起打包运送,每个订单仅限兑换一件积分商品)</font>
			&nbsp;	&nbsp;	&nbsp;	&nbsp;
			<input type="submit"  name="g"  value="去兑换">
		</form>
		<form action="${pageContext.request.contextPath }/order/pay"
			method="post">
			<input type="hidden" name="id" value="${order.orderId}">
			<table border="2">
				<caption>您的订单</caption>
				<tr>
					<th>订单详情</th>
					<th>价格</th>
					<th>订单奖励积分</th>
					<th>购买时间</th>
					<c:if test="${not  empty order.orderAdditional }">
					<th>兑换奖品</th>
					</c:if>
				</tr>
				<tr>
					<td><c:out value="${order.orderDetail }"></c:out></td>
					<td><c:out value="${order.orderCount }"></c:out></td>
					<td><c:out value="${order.orderAward }"></c:out></td>
					<td><c:out value="${order.orderDate }"></c:out></td>
					<c:if test="${not  empty order.orderAdditional }">
					<td><c:out value="${order.orderAdditional }"></c:out></td>
					</c:if>
				</tr>
			</table>
			<font size="4" color="red">以下信息必须填写且反复确认</font><br> 联系人：<input
				type="text" name="orderContacts"  value="${order.orderContacts}"><br>
				 联系电话：<input  type="text" name="orderTell" value="${order.orderTell}"><br> 
				 邮寄地址：<input  type="text" name="orderAddress" value="${order.orderAddress }"><br> <input
				type="submit" name="sure" value="确认支付">
		</form>

	</div>
</body>
</html>
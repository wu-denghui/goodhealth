<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include  page="/goodhealth/head.jsp"></jsp:include>
<title>用户中心</title>
</head>
<body>
<c:choose>
<c:when test="${member.memberGender==1 }">
<c:set  var="sex"   value="先生"></c:set>
</c:when>
<c:otherwise>
<c:set  var="sex"   value="女士"></c:set>
</c:otherwise>
</c:choose>
<div id="main" style="width:1200px;">
<div id="left" style="width:300px;float:left;">
<table>
<caption>${member.memberName }${sex}的用户中心</caption>
<tr><td><a  href="http://localhost:8080/member/showMember"><font  size="5">编辑个人信息</font></a></td></tr>
<tr></tr><tr></tr>
<tr><td><a  href="http://localhost:8080/order/showCompletedOrder/0"><font  size="5">查看历史订单</font></a></td></tr>
<tr></tr><tr></tr>
<tr><td><a  href="http://localhost:8080/member/showAdvice"><font  size="5">对"好健康"的咨询或建议</font></a></td></tr>
<tr></tr><tr></tr>
</table>
</div>
<div id="right" style="width:900px;float:left;">
<c:choose>
<c:when test="${searchStatus==1 }">
<div  align="center">
<h4>${member.memberName }${sex}的个人信息</h4>
<c:if test="${ allErrors!=null }">
<c:forEach var="error" items="${allErrors }">
	<font color="red">${error.defaultMessage }</font>
</c:forEach>
</c:if>
<form action="${pageContext.request.contextPath }/member/editMember"  method="post">
<input  type="hidden"  name="id"  value="${member.memberId }"  >
用户名：<input  type="text"  name="memberName"  value="${member.memberName }"> <br>
用户密码：<input  type="password"  name="memberPassword"  value="${member.memberPassword }" ><br>
用户积分：<input  type="text"  name="memberIntegral"  value="${member.memberIntegral }" disabled="disabled"> <br>
用户生日：<Input type="date"  name="memberBirthday" value="${member.memberBirthday }"> <br>
用户地址：<input  type="text"  name="memberAddress"  value="${member.memberAddress }"> <br>
用户邮箱：<input  type="text"  name="memberEmail"  value="${member.memberEmail }"> <br>
用户电话：<input  type="text"  name="memberTell"  value="${member.memberTell }"> <br>
<input  type="submit"  value="提交修改">  
</form>
</div>
</c:when>
<c:when test="${searchStatus==2 }">
<div  align="center">
<c:if  test="${empty completedOrderList  }"><h2>您还没有已完成的订单</h2></c:if>
<c:if test="${not  empty   completedOrderList}">
<table border="2">
<caption>${member.memberName }${sex}的历史订单</caption>
<tr><th>收件人</th><th>收件地址</th><th>预留联系电话</th><th>订单信息</th><th>订单金额</th><th>订单奖励积分</th><th>订单支付时间</th></tr>
<c:forEach  var="order"  items="${completedOrderList}" >
<tr>
<td>${order.orderContacts}</td>
<td>${order.orderAddress}</td>
<td>${order.orderTell }</td>
<td>${order.orderDetail }</td>
<td>${order.orderCount}</td>
<td>${order.orderAward }</td>
<td>${order.orderDate }</td>
</tr>
</c:forEach>
</table>
</c:if>
<c:if test="${not  empty   completedOrderList}">
<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/order/showCompletedOrder/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/order/showCompletedOrder/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/order/showCompletedOrder/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/order/showCompletedOrder/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/order/showCompletedOrder/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/order/showCompletedOrder/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/order/showCompletedOrder/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/order/showCompletedOrder/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/order/intoCPage"  Method= "post">
      跳转到:<input type="text" style="width:30px" name="into" />页 
   <input type="hidden"  name="max"  value="${max}">
   <input type="hidden"  name="index"  value="${index}">
   <Input type=submit name= "跳转" value="跳转"> 
   </form>
   </c:if>
   </div>
</c:when>
<c:when test="${searchStatus==3 }">
<div  align="center">
<br><br><br><br><br>
对于本药房的一切资讯及建议请联系好健康唯一邮箱:17303652309@163.com！感谢您的光临！
</div>
</c:when>
</c:choose>
</div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>    
<body onload=startclock()>
  <div align = "center"> 
 <H3>好健康网上大药房欢迎您&nbsp;&nbsp;&nbsp;&nbsp;<input id=thetime style="font-size:15 pt;color:red;border:0" size=20></H3>
  <table  cellSpacing = "1" cellPadding = "1" width= "660" align = "center" border="0"   >
  <tr valign = "bottom">
  <td><A href="http://localhost:8080/goodhealth/index.jsp">首页</A></td>
  <td><A href="http://localhost:8080/drug/findAll/0">精选药品</A></td> 
  <td><A href="http://localhost:8080/prize/findAll/0">积分商城</A></td>
  <td><A href="http://localhost:8080/news/findAll/0">健康社区</A ></td >
  <td><A href="http://localhost:8080/shoppingcar/showMyShoppingCar/0">我的购物车</A></td>
  <td><A href="http://localhost:8080/order/showOrder/0">我的订单</A></td>
  <td><A href="http://localhost:8080/member/home">用户中心</A ></td>
  </tr>
  
 <tr>
<form action="${pageContext.request.contextPath }/drug/searchDrug" method="post">
搜索：<input type="text" name="searchMess"   value="输入药品名搜索"  onmousedown="change(this)">&nbsp;&nbsp;
<input  type="hidden" name="index" value="${0 }">
<input  type="submit" name="g" value="确定">
</form>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:choose>
   <c:when test="${ not empty sessionScope.member}">  
<font size="2">用户${sessionScope.member.memberName } 已登录</font>   
<A href= "http://localhost:8080/member/logout">退出</A>
   </c:when>
   <c:otherwise> 
    <A href= "http://localhost:8080/page/login">登录</A>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <A href= "http://localhost:8080/page/register">注册</A>
   </c:otherwise>
</c:choose>
</tr>
  </table>
 </div>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a  onclick="bc()"  style="color:red;size:4;" >返回</a>
<script>
var  change=function (obj){
	obj.value="";
}
var bc=function(){
		if (location.href=="http://localhost:8080/goodhealth/index.jsp") {
			alert('已返回至最顶部');
		}else{
			history.back();
		}
	}
var timerID = null;
var timerRunning = false;
function stopclock() {
	if (timerRunning)
		clearTimeout(timerID);
	timerRunning = false;
}
function startclock() {
	stopclock();
	showtime();
}
function showtime() {
	var now = new Date();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	var timeValue = "" + ((hours >= 12) ? "下午 " : "上午 ")
	timeValue += ((hours > 12) ? hours - 12 : hours)
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds
	document.clock.thetime.value = timeValue;
	document.getElementById("thetime").value=timeValue;
	timerID = setTimeout("showtime()", 1000);
	timerRunning = true;
}
</script>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body onload=startclock()>
<p align="center"><font size="10" color="blue">欢迎使用好健康大药房后台管理系统</font></p>
<div>
<c:if test="${ not empty sessionScope.admin}">  
<font size="2">管理员${sessionScope.admin.memberName } 已登录</font>   
<A onclick="javsscript:window.parent.close()">退出</A>
</c:if>
</div>
	<div align="right"><input id=thetime style="font-size:15 pt;color:red;border:0" size=20></div>
</body>
<script type="text/javascript">
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
	var timeValue = "" + ((hours >= 12) ? "下午 " : "上午 ");
	timeValue += ((hours > 12) ? hours - 12 : hours);
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes;
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds;
	document.getElementById("thetime").value=timeValue;
	timerID = setTimeout("showtime()", 1000);
	timerRunning = true;
}
</script>
</body>
</html>
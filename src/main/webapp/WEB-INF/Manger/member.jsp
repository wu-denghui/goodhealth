<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统——会员管理</title>
</head>
<body>
<c:choose>
<c:when test="${memberManger==1 }">
<div align="left"><p onclick="javascript:history.back()"><font size="4"  color="blue">返回</font></p> </div>
<div  align="center">
<form action="${pageContext.request.contextPath }/member/search" method="post">
以会员名搜索会员信息：<input onmousedown="change(this)" type="text" name="searchMess"   value="输入会员名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="hidden"    name="index"  value="0" >
<input  type="submit" name="g" value="搜索">
</form>
<table border="2">
<caption><font size="6">"好健康"会员信息</font> </caption>
<tr><th>会员名</th><th>会员性别</th><th>会员生日</th><th>会员积分</th><th>会员住址</th><th>会员邮箱</th><th>会员电话</th></tr>
<c:forEach  var="member"  items="${memberList}" >
<tr>
<td>${member.memberName}</td>
<td>${member.memberGender}</td>
<td>${member.memberBirthday}</td>
<td>${member.memberIntegral}</td>
<td>${member.memberAddress}</td>
<td>${member.memberEmail}</td>
<td>${member.memberTell}</td>
</tr>
</c:forEach>
</table>
<!-- 分页功能 start -->
		<font size="2">共 ${max} 页</font> <font size="2">第
			${index} 页</font> <a href="http://localhost:8080/member/memberManger/0">首页</a>
		<c:choose>
			<c:when test="${index- 1 > 0}">
				<a href="http://localhost:8080/member/memberManger/${index-2}">上一页</a>
			</c:when>
			<c:when test="${index - 1 <= 0}">
				<a href="http://localhost:8080/member/memberManger/${0 }">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/member/memberManger/${0}">下一页</a>
			</c:when>
			<c:when test="${index+ 1 <=max}">
				<a href="http://localhost:8080/member/memberManger/${index}">下一页</a>
			</c:when>
			<c:when test="${index + 1 > max}">
				<a href="http://localhost:8080/member/memberManger/${max-1}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${max==0}">
				<a href="http://localhost:8080/member/memberManger/${max-1}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="http://localhost:8080/member/memberManger/${max-1}">尾页</a>
			</c:otherwise>
		</c:choose>
	<form action="${pageContext.request.contextPath }/member/intoPage"  Method= "post">
      跳转到:<input type="text" style="width:30px" name="into" />页 
   <input type="hidden"  name="max"  value="${max}">
   <input type="hidden"  name="index"  value="${index}">
   <Input type=submit name= "跳转" value="跳转"> 
   </form>
   </div>
</c:when>
<c:when test="${memberManger==2 }">
<div align="left"><p onclick="javascript:history.back()"><font  size="4" color="blue">返回</font></p> </div>
<div align="center">
<form action="${pageContext.request.contextPath }/member/search" method="post">
以会员名搜索会员信息：<input onmousedown="change(this)" type="text" name="searchMess"   value="输入会员名搜索" onmousedown="change(this)">&nbsp;&nbsp;
<input  type="hidden"    name="index"  value="0" >
<input  type="submit" name="g" value="搜索">
</form>
<table border="2">
<caption><font size="6">${member.memberName}会员信息</font> </caption>
<tr><th>会员名</th><th>会员性别</th><th>会员生日</th><th>会员积分</th><th>会员住址</th><th>会员邮箱</th><th>会员电话</th></tr>
<c:forEach  var="member"  items="${memberList}" >
<tr>
<td>${member.memberName}</td>
<td>${member.memberGender}</td>
<td>${member.memberBirthday}</td>
<td>${member.memberIntegral}</td>
<td>${member.memberAddress}</td>
<td>${member.memberEmail}</td>
<td>${member.memberTell}</td>
</tr>
</c:forEach>
</table>
</div>
</c:when>
</c:choose>
   <script type="text/javascript">
   var  change=function (obj){
		obj.value="";
	}
   </script>
</body>
</html>
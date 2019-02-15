<%@page contentType="text/html;charset=utf-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<jsp:include page="/goodhealth/head.jsp"></jsp:include>
<title>注册页面</title>
	<style type="text/css">
		.status1{
			color: #aaa;
		}
		.status2{
			color: red;
		}
		.status3{
			color: green;
		}
	</style>
</HEAD>
<body   onload="document.getElementById('ln').focus()">
	<br>
	<br>
	<br>
	<div  align="center">
	   <c:if test="${not  empty  duplicateName  }">
	  <font  color="red">${duplicateName }</font>
	  </c:if>
	 <c:if test="${ allErrors!=null }">
		<c:forEach var="error" items="${allErrors }">
	  <font  color="red">${error.defaultMessage }</font>
	</c:forEach>
	</c:if>
	</div>
	<br><br><br>
	<div align="center" >
		<form action="${pageContext.request.contextPath }/member/register"  onsubmit="return  chfo('dj')" 
			method="post">
			<table border=1>
				<caption>
					<font color="red">有*标记的为必填项其它为选填项</font>
				</caption>
				<tr>
					<td>* 用户名称:</td>
					<td><Input  id="ln" type=text name="memberName"
						value="${member.memberName }"><span  class="status1">输入用户名</span></td>
				</tr>
				<tr>
					<td>*用户密码:</td>
					<td><Input type=password name="memberPassword"
						value="${member.memberPassword }"><span  class="status1">输入密码</span></td>
				</tr>
				<tr>
					<td>*确认用户密码:</td>
					<td><Input type=password name="password2"
						value=""><span  class="status1">再次输入密码</span></td>
				</tr>
				<tr>
					<td>*性别:</td>
					<td><Input type="radio" name="memberGender" value="1"checked="checked">男 
					<Input type="radio" name="memberGender" value="0">女</td>
				</tr>
				<tr>
					<td>&nbsp;出生日期:</td>
					<td><Input type="date" name="memberBirthday"></td>
				</tr>
				<tr>
					<td>&nbsp;*联系电话:</td>
					<td><Input type=text name="memberTell"
						value="${member.memberTell }"><span  class="status1">输入正确的电话</span></td>
				</tr>
				<tr>
					<td>&nbsp;联系邮箱:</td>
					<td><Input type=text name="memberEmaill"
						value="${member.memberEmail }"><span  class="status1">输入正确的邮箱</span></td>
				</tr>
				<tr>
					<td>&nbsp;家庭地址:</td>
					<td><Input type=text name="memberAddress"
						value="${member.memberAddress }"><span  class="status1">输入正确的住址</span></td>
				</tr>
			</table>
			<Input type=submit name="sub" value="注册" >
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
				name="button" id="button" value="重置" />
		</form>
	</div>
	<script type="text/javascript">
	function getN(obj) {
		while (true) {
			if (obj.nextSibling.nodeName!="SPAN") {
				obj = obj.nextSibling;
			} else {
				return obj.nextSibling;
			}
		}
	}

	function check(obj, info, fun,dj) {
		var sp = getN(obj);
		obj.onblur = function() {
			if (fun(this.value)) {
				sp.innerText = "合法输入";
				sp.className = "status3"
			} else {
				sp.innerText = info;
				sp.className = "status2"
			}
		}
		if (dj="dj") {
			obj.onblur();
		}
	}


	function chfo(dj) {
		var boo = true;
		var name = document.getElementsByName("memberName")[0];
		console.log(name);
		var password = document.getElementsByName("memberPassword")[0];
		var password2 = document.getElementsByName("password2")[0];
		var tell = document.getElementsByName("memberTell")[0];
		var email = document.getElementsByName("memberEmaill")[0];
		var address = document.getElementsByName("memberAddress")[0];
		check(name, "用户名为数字字母的4~10位组合", function(val) {
			var reg=/[a-zA-Z0-9]{4,10}/;
			if (reg.test(val)) {
				return true;
			} else {
				boo = false;
				return false;
			}
		},dj);
		check(password, "密码为数字字母的4~16位组合", function(val) {
			var reg=/[a-zA-Z0-9]{4,16}/
			if (reg.test(val)) {
				return true;
			} else {
				boo = false;
				return false;
			}
		},dj);
		check(password2, "前后两次密码要一致", function(val) {
			var pw = document.getElementsByName("memberPassword")[0];
			if (val==pw.value&&pw!=null&&pw.innerText!="") {
				return true;
			} else {
				boo = false;
				return false;
			}
		},dj);
		check(tell, "输入正确的电话号码", function(val) {
			var reg=/^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$/;
			if (reg.test(val)){
				return true;
			} else {
				boo = false;
				return false;
			}
		},dj);

		check(email, "输入正确的邮箱", function(val) {
			var reg=/^(\w-.)+@(\w-?)+(.\w{2,})+$/ ;
			if (reg.test(val)) {
				return true;
			} else {
				boo = false;
				return false;
			}
		},dj);
		check(address, "", function(val) {
		},dj);
		return boo;
	}
	</script>
</Body>
</HTML>
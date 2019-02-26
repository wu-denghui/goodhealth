<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<html>
<head><jsp:include  page="/goodhealth/head.jsp"></jsp:include>
<script type="text/javascript"  src="/js/jquery.js"></script>
<title>登录页面</title>
</head>
<body  >
<br>
<div align="center">
<c:if test="${ not empty error}" >
<font size="4"  color="red">${error }</font>
</c:if>
</div>
<br><br>
<form action= "${pageContext.request.contextPath }/member/login" Method= "post">
<div align= "center">
<table border=1 onload="">
<caption>请输入您的用户名和密码</caption >
<tr><td>用户名:</td><td><input  id="ln" type =text name = "name"  value="${name }"></td></tr>
<tr><td>密 &nbsp;&nbsp;&nbsp;码:</td><td><input type =password name= "password"  value="${password}"></td></tr>
</table>
<input type=submit name= "g" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" name="button" id="button" value="重置" />
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#ln").focus();
});
	</script>
</form>
</body>
</html>
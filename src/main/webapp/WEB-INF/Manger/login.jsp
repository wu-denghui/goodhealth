<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<html>
<title>登录页面</title>
</head>
<body   onload="document.getElementById('ln').focus()">
<br><br><br><br><br>
<div align="center">
<c:if test="${ not empty error}" >
<font size="4"  color="red">${error }</font>
</c:if>
</div>
<br><br>
<div align= "center"> 
<form action= "${pageContext.request.contextPath }/member/mangerLogin" Method= "post">
<table border=1>
<caption>请输入管理员账号和密码</caption >
<tr><td>账号:</td><td><input  id="ln" type =text name = "name"  value="${name }"></td></tr>
<tr><td>密 &nbsp;&nbsp;&nbsp;码:</td><td><input type =password name= "password"  value="${password}"></td></tr>
</table>
&nbsp;&nbsp;<input type=submit name= "g" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" name="button" id="button" value="重置" />
</form>
</div>
</body>
</html>
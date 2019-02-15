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
<form action="">
<input  type="hidden"  name="id" value="${prize.prizeId }">  
<table border="2">
<caption>兑换积分奖品</caption>
<tr><td>积分奖品图片</td><td><img alt="积分奖品图片" src="http://localhost:8080/images/prize/${prize.prizePic }" width="44" height="55" onmouseover="enlarge(this)" onmouseout="reduction(this)"></td></tr>
<tr><td>奖品名称</td><td>${prize.prizeName }</td></tr>
<tr><td>兑换所需积分</td><td>${prize.prizeValue }</td></tr>
<tr><td>兑换所需积分</td><td>${prize.prizeValue }</td></tr>
<tr><td> 收件人：</td><td><input type="text" name="orderContacts"  value="${order.orderContacts}"><br></tr>
<tr><td> 联系电话：</td><td><input  type="text" name="orderTell" value="${order.orderTell}"></td></tr>
<tr><td>邮寄地址：</td><td><input  type="text" name="orderAddress" value="${order.orderAddress }"></td></tr>
<tr><td><input type="submit" name="g" value="确认支付"></td></tr>
</table>
</form>
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
</body>
</html>
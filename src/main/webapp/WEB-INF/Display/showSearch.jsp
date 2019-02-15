<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<jsp:include  page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
<div align="center">
<table border="2">
<caption><font size="6">搜索结果</font> </caption>
<tr><th>样品图片</th><th>药品名称</th><th>零售价</th><th>购买赠送积分</th><th>功能</th><th>用量</th><th>查看更多</th><th>加入购物车</th></tr>
<c:forEach  var="drug"  items="${resultList}" >
<tr><td><img onmouseover="enlarge(this)" onmouseout="reduction(this)" alt="药品图片" src="http://localhost:8080/image/${drug.drugPic }" width="44" height="55">
<td><c:out value="${drug.drugName }"></c:out></td>
<td><c:out value="${drug.drugPrice}"></c:out></td>
<td><c:out value="${drug.drugIntegral }"></c:out></td>
<td><c:out value="${drug.drugFunction }"></c:out></td>
<td><c:out value="${drug.drugUsage }"></c:out></td>
<c:url  var="linkC" value="http://localhost:8080/drug/intoShoppingCar">
<c:param name="id" value="${drug.drugId }"></c:param>
<c:param name="f" value="${index }"></c:param>
</c:url>
<c:url  var="linkS" value="http://localhost:8080/drug/showDetail">
<c:param name="id" value="${drug.drugId }"></c:param>
</c:url>
<td><a href="${linkS }">详细说明书</a></td>
<td><a href="${linkC }">加入购物车</a></td>
</tr>
</c:forEach>
</table>
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
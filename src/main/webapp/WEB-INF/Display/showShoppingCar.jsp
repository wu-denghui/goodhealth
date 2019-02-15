
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/goodhealth/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车</title>
</head>
<body>
	<div align="center">
		<form
			action="${pageContext.request.contextPath }/order/generateOrderMany">
			<input type="hidden" name="form" value="${index }"> <input
				type="hidden" name="size" value="${size}">
			<c:choose>
				<c:when test="${empty  recordList }">
					<h2>您的购物车为空</h2>
				</c:when>
				<c:otherwise>
					<table border="2">
						<caption>购物车</caption>
						<tr>
							<th>药品预览</th>
							<th>药品名</th>
							<th>药品价格</th>
							<th>购买数量</th>
							<th>奖励积分</th>
							<th>加入购物车时间</th>
							<th>数量+1</th>
							<th>数量-1</th>
							<th>从购物车中删除</th>
							<th>生成订单</th>
							<th>勾选以批量生成订单</th>
						</tr>
						<c:forEach var="record" items="${recordList}">
							<tr>
								<td><img alt="药品图片"  onmouseover="enlarge(this)" onmouseout="reduction(this)"
									src="http://localhost:8080/image/${drug.drugPic }" width="44"
									height="55"></td>
								<td><c:out value="${record.drug.drugName }"></c:out></td>
								<td><c:out value="${record.drug.drugPrice }"></c:out></td>
								<td><c:out value="${record.recordNumber }"></c:out></td>
								<td><c:out
										value="${record.recordNumber * record.drug.drugIntegral }"></c:out></td>
								<td><c:out value="${record.recordDate }"></c:out></td>
								<c:url var="linkA"
									value="http://localhost:8080/shoppingcar/countAdd/${record.recordId}">
									<c:param name="i" value="${index }"></c:param>
								</c:url>
								<td><a href="${linkA }"><font size="6">+</font></a></td>
								<c:url var="linkR"
									value="http://localhost:8080/shoppingcar/countReduce/${record.recordId}">
									<c:param name="i" value="${index }"></c:param>
								</c:url>
								<td><a href="${linkR}"><font size="8">-</font></a></td>
								<c:url var="linkD"
									value="http://localhost:8080/shoppingcar/countDelete/${record.recordId}">
									<c:param name="i" value="${index }"></c:param>
									<c:param name="size" value="${size}"></c:param>
								</c:url>
								<td><a href="${linkD}"><font color="red">删除</font></a></td>
								<c:url var="linkC"
									value="http://localhost:8080/order/generateOrder">
									<c:param name="form" value="${index }"></c:param>
									<c:param name="size" value="${size}"></c:param>
									<c:param name="id" value="${record.recordId}"></c:param>
								</c:url>
								<td><a href="${linkC}"><font color="red">生成订单</font></a></td>
								<td><input type="checkbox" name="intoOrder"
									value="${record.recordId }"></td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${not  empty  recordList }">
				<input type="submit" value="批量生成订单" width="50" height="30">
				<label for="ss"><input id="ss" name="all" type="checkbox"
					onclick="sall(this)">全选</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<script type="text/javascript">
					function sall(obj) {
						var arr = document.getElementsByName("intoOrder");
						for (let i = 0; i < arr.length; i++) {
							arr[i].checked = obj.checked;
						}
					}
					
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
			</c:if>
		</form>
	</div>
	<div>
		<!-- 分页功能 start -->
		<div align="center">
			<c:if test="${not  empty recordList }">
				<font size="2">共 ${max} 页</font>
				<font size="2">第 ${index} 页</font>
				<a href="http://localhost:8080/shoppingcar/showMyShoppingCar/0">首页</a>
				<c:choose>
					<c:when test="${index- 1 > 0}">
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${index-2}">上一页</a>
					</c:when>
					<c:when test="${index - 1 <= 0}">
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${0 }">上一页</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${max==0}">
						<a href="http://localhost:8080/shoppingcar/showMyShoppingCar/${0}">下一页</a>
					</c:when>
					<c:when test="${index+ 1 <=max}">
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${index}">下一页</a>
					</c:when>
					<c:when test="${index + 1 > max}">
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${max-1}">下一页</a>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${max==0}">
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${max-1}">尾页</a>
					</c:when>
					<c:otherwise>
						<a
							href="http://localhost:8080/shoppingcar/showMyShoppingCar/${max-1}">尾页</a>
					</c:otherwise>
				</c:choose>
				<form
					action="${pageContext.request.contextPath }/shoppingcar/intoPage"
					Method="post">
					跳转到:<input type="text" style="width: 30px" name="into" />页 <input
						type="hidden" name="max" value="${max}"> <input
						type="hidden" name="index" value="${index}"> <Input
						type=submit name="跳转" value="跳转">
				</form>
			</c:if>
			<!-- 分页功能 End -->
		</div>
</body>
</html>
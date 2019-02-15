<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品说明书</title>
 <jsp:include page="/goodhealth/head.jsp"></jsp:include>
</head>
<body>
<br><br><br><br><br><br><br>
<div  align="center">
<table  border="2">
<caption>${drug.drugName}说明书</caption>
<tr><td>外观</td><td><img  onmouseover="enlarge(this)" onmouseout="reduction(this)" alt="药品图片" src="http://localhost:8080/image/${drug.drugPic }" width="44" height="55"></td></tr>
<tr><td>成分</td><td>${drug.drugComponent }</td></tr>
<tr><td>性状</td><td>${drug.drugCharacter }</td></tr>
<tr><td>功能</td><td>${drug.drugFunction }</td></tr>
<tr><td>用量</td><td>${drug.drugUsage }</td></tr>
<tr><td>贮藏</td><td>${drug.drugStorage }</td></tr>
<tr><td>生产商</td><td>${drug.drugProductor }</td></tr>
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
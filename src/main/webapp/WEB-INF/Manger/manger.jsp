<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好健康药房后台管理系统</title>
  <style>
        body
        {
            margin: 0;
            padding: 0;
            border: 0;
            overflow: hidden;
            height: 100%;
            max-height: 100%;
        }
        #frameTop
        {
            position: absolute;
            top: 0;
            left: 0;
            height: 150px;
            width: 100%;
            overflow: hidden;
            vertical-align: middle;
        }
        #frameContentLeft
        {
            position: fixed;
            top: 150px;
            left: 0;
            height: 100%;
            width: 200px;
            overflow: hidden;
            vertical-align: top;
            background-color: #D2E6FA;
        }
        #frameContentRight
        {
            position: absolute;
            left: 200px;
            top: 150px;
            height: 100%;
            width: 100%;
            right: 0;
            bottom: 0;
            overflow: hidden;
            background: #fff;
        }
    </style>
</head>
<body>
    <div>
        <iframe id="frameTop" src="/goodhealth/top.jsp"></iframe>
    </div>
    <div>
        <iframe id="frameContentLeft" src="/goodhealth/main.html"></iframe>
        <iframe id="frameContentRight" src="/goodhealth/right.html"></iframe>
    </div>

</body>
</html>
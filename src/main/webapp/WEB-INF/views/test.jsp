<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>hello</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.2.6.min.js"></script>
  </head>
  
  <body>
    TEST!<br>
    <input type="submit" value="submit" onclick="send()" />
  </body>
  <script type="text/javascript">
  function send() {
	  var arr = {};
	  arr['username'] = "aaaa";
	  var dataSend = JSON.stringify(arr);
	  alert(dataSend);
	  var obj = JSON.parse(dataSend);
	  $.ajax({
	      type: "POST",
	      url: "<%=path%>/testPost",
	      contentType: "application/json",//必须有
	      dataType: "json", //表示返回值类型，不必须
	      data: dataSend,
	      success: function (jsonResult) {
	       
	          //获取数据ok
	          alert(jsonResult);
	      }
	  });
  }
  </script>
</html>

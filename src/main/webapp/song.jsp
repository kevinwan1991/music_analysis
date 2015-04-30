<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="org.music.analysis.*"%>
<%
	String songName = "";
	songName = request.getParameter("songName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="images/web_background.jpg">
<table width="80%" border="0" align="center">
		<tr>
			<td><jsp:include flush="true" page="head.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td>
				<h1>Top songs...</h1>
				<h1><%= songName%></h1>
			</td>
			<td></td>
		</tr>
		<tr>
			<td><jsp:include flush="true" page="tail.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>
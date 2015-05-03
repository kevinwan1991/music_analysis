<%@ page language="java" import="java.util.*"%> <!-- pageEncoding="ISO-8859-1"%> -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.music.analysis.module.*"%>
<%
	String singerName = "";
	singerName = request.getParameter("singerName");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body background="images/web_background.jpg">
	<table width="80%" border="0" align="center">
		<tr>
			<td><jsp:include flush="true" page="head.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td>
				<h1>I am the singer!</h1>
				<h1><%= singerName%></h1>
			</td>
			<td></td>
		</tr>
		<tr>
			<td><jsp:include flush="true" page="tail.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>

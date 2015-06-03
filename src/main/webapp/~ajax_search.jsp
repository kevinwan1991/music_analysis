<%@taglib prefix="c" url="http://java.sun.com/jstl/core_rt" %>
<table cellpadding="0" cellspacing="0" style="width:300px;color:black;font-size:12px;border:1px gray solid;">
	<c:forEach items="${strList}" var="str">
		<tr style="height:22px;" onmouseover="this.style.background='#BBB8B8';" 
								 onmouseout="this.style.background='#FFFFFF'">
			<td onmousedown="showClickText(this)">${str}</td>
		</tr>
	</c:forEach>
</table>

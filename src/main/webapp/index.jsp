<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MusicA</title>
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<script>
			$(function() {
			  $( "#tabs" ).tabs();
			});
		</script>
		<style type="text/css">
			#tabs { 
			    background: transparent; 
			    border: none; 
			} 
			#tabs .ui-widget-header { 
			    background: transparent; 
			    border: none; 
			    border-bottom: 2px solid #303030; 
			    -moz-border-radius: 0px; 
			    -webkit-border-radius: 0px; 
			    border-radius: 0px; 
			}
			#tabs .ui-tabs-nav .ui-state-default { 
			    background: transparent; 
			    border: none; 
			} 
			#tabs .ui-tabs-nav .ui-state-active { 
			    background: transparent url(images/uiTabsArrow.png) no-repeat bottom center; 
			    border: none; 
			} 
			#tabs .ui-tabs-nav .ui-state-default a { 
			    color: #303030; 
			} 
			#tabs .ui-tabs-nav .ui-state-active a { 
			    color: #FFFFFF; 
			}
			
		</style>
    </head>
	<body background="images/web_background.jpg">
	  <table width="80%" border="0" align="center">
		  <tr>
		    <td height="80" colspan="2">
		      <!--Import head.jsp-->
		      <jsp:include flush="true" page="head.jsp"></jsp:include>
		    </td>
		  </tr>
		  <tr>
		  <td>
		  <div id="tabs">
			  <ul>
			    <li><a href="#tabs-1"><img src="images/singers_tab.png"/></a></li>
			    <li><a href="#tabs-2"><img src="images/songs_tab.png"></a></li>
			  </ul>
			  <div class="tabs-background"></div>
			  <div id="tabs-1">
				  <table>
					<tr>
						<td width="80%" height="500" valign="top"><span><jsp:include flush="true" page="left_singers.jsp"></jsp:include></span></td>
						<td width="20%" height="500" valign="top"><jsp:include flush="true" page="right_singers.jsp"></jsp:include></td>
					</tr>
				  </table>
			  </div>
			  <div id="tabs-2">
			  	<table>
					<tr>
						<td width="80%" height="500" valign="top"><jsp:include flush="true" page="left_songs.jsp"></jsp:include></td>
						<td width="20%" height="500" valign="top"><jsp:include flush="true" page="right_songs.jsp"></jsp:include></td>
					</tr>
				  </table>
			  </div>
			</div>
		  </td>
		  </tr>
		  <tr>
		    <td height="60" colspan="2">
			  <!--Import head.jsp-->
			  <jsp:include flush="true" page="tail.jsp"></jsp:include>
		    </td>
		  </tr>
		</table>
    </body>
</html>



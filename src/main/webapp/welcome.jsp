<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MusicA</title>

<script src="framework/jquery.min.js" type="text/javascript"></script>
<script src="framework/jquery-ui.min.js" type="text/javascript"></script>
<script src="framework/jQueryRotate.js" type="text/javascript"></script>

<link href="framework/jquery-ui.css" rel="stylesheet" type="text/css" title="rocket-jqueryui" />
<link href="framework/jquery.wijmo-complete.all.2.1.4.min.css" rel="stylesheet" type="text/css" />
<link href="css/default.css" rel="stylesheet" type="text/css" />

<style type="text/css">
    .TabPage{overflow-y:hidden;overflow-x:hidden;}
    img{border:0px;}
    body{font-size: 70%;font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;}
    .all_sideBar{overflow-x:hidden;overflow-y:hidden;z-index:10;position: relative;left:0px;width:260px;float: left;}
</style>


</head>
<body background="imgs/web_background.jpg">
<table border="0" width="100%">
    <tr>
        <td width="50%"><a href="../../"><img src="images/music_logo.png"/></a></td>
        <td width="40%" style="text-align: center;"><h3><span id="index_userInfo"><img src="images/loading.png"/> LOADING...</span></h3></td>
        <td width="5" style="text-align:right;"> <!--<button class="button">Help</button>--><!-- <button id="index_logout" class="button">Logout</button> -->
        </td>
    </tr>

</table>


     <!-- Total Arms: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Arms: <span class="all_gridTotalArms">?</span> -->
        <div id="tabs" >
            <div id="dbInfo" style="margin-top: -25px; margin-bottom: 25px; text-align: right; padding-right: 25px; color: white; font-weight: bold;display:none">Database: <span class="all_databaseLabel">?</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Trials: <span class="all_gridTotalTrials">?</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Rows: <span class="all_gridTotalRows">?</span></div>
            <ul>
                <li><a href="#tabs-1" title="Choose from a menu of databases">Database</a></li>
                <li><a href="#tabs-2" title="Generate a subset of data based on data filters">Trial</a></li>
                <li><a href="#tabs-3" title="Generate graphs for selected data subset">Graph</a></li>
                <li><a href="#tabs-4" title="Generate summary tables for selected data subset">Summary</a></li>
                <li id="liteUserRemove"><a href="#tabs-5" title="View or download data for selected data subset">Raw Data</a></li>
                <li><a href="#tabs-6" title="View or download source data">Source Data</a></li>
            </ul>
            <div id="tabs-1" class="TabPage">

            </div>
            <div id="tabs-2" class="TabPage">

            </div>
            <div id="tabs-3" class="TabPage">

            </div>
            <div id="tabs-4" class="TabPage">

            </div>
            <div id="tabs-5" class="TabPage">

            </div>
            <div id="tabs-6" class="TabPage">

            </div>
        </div>
        <div class="feedbackLink">Questions or Feedback? <a href="mailto:info@wequantify.com">Contact Us!</a></div>
        <div style="font-size: 150%;text-align: center;padding-top: 10px;">Copyright &#169; <span class="fullYear">2013</span> <a href="http://www.rudraya.com" target="_blank">Rudraya</a> and <a href="http://www.quantitativesolutions.net" target="_blank">Quantitative Solutions</a><br/>Powered by MashFrame&reg; Technology</div>
        <div class="buildVersion"><font color="#666">Build 13.08.22</font></div>
        <div id="all_dialogue"></div>

</body>
</html>




</body>
</html>
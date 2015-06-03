<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.music.analysis.module.*"%>
<%
	String tid = (String) request.getAttribute("tid");
	String songName = (String) request.getAttribute("songName");
	String singer = (String) request.getAttribute("singerName");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>singer_search</title>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <!-- <link type="text/css" href="demo_styles.css" rel="stylesheet" />    -->
    <link type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet"/>
    
</head>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="inline" width="80%" border="0" align="center" class="no-spacing" cellspacing="0" cellpadding="0">
		<tr>
		    <td height="80" colspan="2">
		      <!--Import head.jsp-->
		      <jsp:include flush="true" page="head.jsp"></jsp:include>
		    </td>
		</tr>
		<tr>
			<td width="50%"> 
				<form action="info_singer" style="display:inline;">
						<input type = "text" name = "singerName" />
		    			<input type="submit" value="search">
				</form>
			</td>
		</tr>
		<tr>			
			<td style="width: 440px; display:inline-block">
    			<div id="info"></div>
    			<div id="all_results"  align=left style="width:440px;margin:0 auto">
       				<div id='tracks'>
            			<h3> <%= songName %></h3>
            			<div id="results"></div>
        			</div>
    			</div>
			</td>
			<td style="width: 1000px;"  valign="top"> 
				<h3>Other playlists containing <%= songName %></h3>
				
				<div id="results1"></div>
			    <div id="all_results_playList"> </div>
			</td>
		</tr>
		<tr>
		    <td height="60" colspan="2">
			  <!--Import head.jsp-->
			  <jsp:include flush="true" page="tail.jsp"></jsp:include>
		    </td>
		  </tr>
	</table>
<script type="text/javascript">

jQuery.ajaxSettings.traditional = true; 
var data1 = null;
var arrOfSongs = null;

var embed = '<iframe src="https://embed.spotify.com/?uri=spotify:track:PREFEREDTITLE:TRACKS" style="width:400px; height:528px;" frameborder="0" allowtransparency="true"></iframe>';


function fetchArtistPlaylist(sName, tid, wandering, variety) {
	var url = 'http://developer.echonest.com/api/v4/song/search';     

    $("#all_results").hide();
    info("Creating the playlist ...");
    $.getJSON(url, { 
    	//'song_id': sID, 
    	//'artist':sName,
	    //'artist_id': sID,
	    'title':sName,
        'api_key': "ICY4WXWWLLSRR8ZIX",
        'bucket': [ 'id:spotify', 'tracks'], 
        'limit' : true,
        'results': 1, 
        //'song_type':'studio',
        'sort' : "song_hotttnesss-desc",
        },function(data) {

            	 /* 'distribution' : wandering ? 'wandering' : 'focused' */
        info("");
        $("#results").empty();
        $("#all_results").show();

        var tracks = "";
        /* arrOfSongs = arr.concat(data.response.songs); */
        for (var i = 0; i < data.response.songs.length; i++) {
            var song = data.response.songs[i];
            //var tid = song.tracks[0].foreign_id.replace('spotify:track:', '');
            tracks = tracks + tid;
            //alert(data.response.songs[i].title);
        }
        var tembed = embed.replace('TRACKS', tracks);
        tembed = tembed.replace('PREFEREDTITLE:',"");//, data.response.songs[0].title + ' playlist');
        var li = $("<span>").html(tembed);
        $("#results").append(li);
     });
}

function fetchImages(artist,songName) {
    var url = 'http://developer.echonest.com/api/v4/artist/images';
    var args = { 
            format:'json', 
            api_key: "ICY4WXWWLLSRR8ZIX",
            name: artist,
            results: 1, 
    }; 
    info("Fetching images for " + artist);
    
    $.getJSON(url, { 
    	//'song_id': sID, 
    	//'artist':sName,
	    //'artist_id': sID,
	    'name':artist,
        'api_key': "ICY4WXWWLLSRR8ZIX",
        //'bucket': [ 'id:spotify', 'tracks'], 
        //'limit' : true,
        'start':15,
        'results': 2, 
        //'song_type':'studio',
        },function(data) {
        	$("#results1").empty();
            if (! ('images' in data.response)) {
                error("Can't find any images for " + artist);
            } else {
                $("#results1").show();
                
                var adiv = $("<div style=\"cursor:pointer\">");
                adiv.attr('onclick',"window.location='http://localhost:8080/musicA/info_song?songName="+songName+"&"+"';");
                if(data.response.images.length==0){
                	var img = $("<img height='300' width='230' >");
                	img.attr('src',  "images/singer"+Math.floor((Math.random() * 10) + 1)+".jpg");
                	adiv.append(img);
                	var img = $("<img height='300' width='230' >");
                	img.attr('src',  "images/singer"+Math.floor((Math.random() * 10) + 1)+".jpg");	
                }
                
                for (var i = 0; i < data.response.images.length; i++) {
                    var img = $("<img height='300' width='230' >");
                	img.attr('src',  data.response.images[i].url); 
                	adiv.append(img);
                }
                /* var adiv = $("<div style=\"cursor:pointer\">");
                var img = $("<img height='300' width='200' >");
            	img.attr('src',  data.response.images[0].url);  */
            	   
                adiv.append(img); 
            	
                /* $.each(data.response.images, function(index, item) {
                    var div = formatItem(index, item);
                    $("#results1").append(div);
                }); */
                $("#results1").append(adiv);
             
                //info("Showing " + data.response.images.length + " of " + data.response.total + " images for " + artist);
            }
        });
      
}

function formatItem(which, item) {
    var img = $("<div>");
    img.addClass("image-container span3");
    img.css("background-image", "url(" +item.url + ")");
    var attribution = $("<span class='label'>")
        .text(item.license.attribution)
        .hide();
    img.append(attribution);
    img.hover( 
        function(evt) {
            img.find('.label').show();
        },
        function(evt) {
            img.find('.label').hide();
        }
    );
    return img;
}

function newArtist() {
	<%-- alert("<%= songList.size()%>"); --%>
    var sName = "<%= songName %>";
    var tid ="<%= tid %>";
    
    var singer = "<%= singer %>"; 
    <%-- //alert("<%= singer %>"); --%>
    
    fetchArtistPlaylist(sName, tid, false, .2);
    fetchImages(singer,sName);
}

function info(txt) {
    $("#info").text(txt);
}


$(document).ready(function() {
	newArtist();
    //$("#go").removeAttr("disabled");
    /*$("#all_results").hide(); */
});
</script>
</body>

</html>
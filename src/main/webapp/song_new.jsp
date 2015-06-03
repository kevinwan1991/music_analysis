<%@ page language="java" import="java.util.*"  pageEncoding ="UTF-8" %> <!--  pageEncoding="ISO-8859-1"%> -->
<%@ page import="org.music.analysis.module.*"%>
<%
	Song song = (Song) request.getAttribute("song_obj");
	//String sID = (String) request.getAttribute("song_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

    <link href="css/spotify_styles_genre.css" rel="stylesheet">
    <link type="text/css" href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <script src="framework/spotify_en_tools.js"></script>
    <link type="text/css" href="css/spotify_common_styles.css" rel="stylesheet" />
</head>
 
<body background="images/web_background.jpg">
<div id="info"> </div>
<table width="80%" border="0" align="center">
		<tr>
			<td height="80" colspan="2">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
			</td>
		</tr>

		<tr>			
			<td width="40%">
				<h1 >PlayList:</h1>
				<div id="all_results_names"> </div>
			</td>
			<td width="60%">
				
			    <div id="all_results_playList"> </div>
			</td>
		</tr>

		<tr>
			<td height="80" colspan="2">
				<jsp:include flush="true" page="tail.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>

<script type="text/javascript">

jQuery.ajaxSettings.traditional = true; 
var config = getConfig();
var data1 = null;
var arrOfSongs = null;

function searchArtist(sName, song){//}, callback) {
	var url = config.echoNestHost + 'api/v4/song/search';
	$("#all_results_playList").empty();
    info("Searching for the song ...");
    $.getJSON(url, {
            'api_key': config.apiKey,
            'title' : sName,
            'bucket': [ 'id:' + config.spotifySpace, 'tracks'], 
            'limit' : true,
            'results': 2,
			'sort' : "song_hotttnesss-desc",        
          }) 
        .done(function(data) {
            info("");
            //callback(data);
            if (! ('songs' in data.response)) {
                console.log("Can't find that song!");
            } else {
            	data1 = data.response.songs;
	            /* getSpotifyPlayer(data.response.songs, function(player) {
	                console.log('got the first player');
	                $("#all_results_playList").append(player);
	        }); */
            }
        })
        .error( function() {
            info("Whoops, had some trouble finding that artist");
            console.log("Whoops, had some trouble finding that artist");
        }) ;
}

function fetchPlaylist(sID, wandering, variety, callback) {
    
    var url = config.echoNestHost + 'api/v4/playlist/static';
    info(url);
    $("#all_results_playList").empty();
    info("Creating the playlist ...");
    $.getJSON(url, { 
    	    'song_id': sID, 
    	    //'title': sName,
            'api_key': "ICY4WXWWLLSRR8ZIX",
            'bucket': [ 'id:' + config.spotifySpace, 'tracks'], 
            'limit' : true,
            'variety' : 1,
            'results': 10, 
            'type':'song-radio',}) 
        .done(function(data) {
            info("");
            if (! ('songs' in data.response)) {
                info("Can't find that song!");
            } else {
                var title = "Song radio!";
                
                arrOfSongs = data1.concat(data.response.songs);//data.response.songs.concat(data1);
                getSpotifyPlayer(arrOfSongs, function(player) {
                    console.log('got the player');
                    $("#all_results_playList").append(player);
                });
                callback(arrOfSongs);
            }
        })
        .error( function() {
            info("Whoops, had some trouble getting that playlist");
        }) ;
}

function getBestImage(images, minSize) {
    var best = null;
    if (images.length > 0) {
        best = images[0];
        images.forEach(
            function(image) {
                if (image.width >= minSize) {
                    best = image;
                }
            }
        );
    }
    return best;
}

function showSimilars(arrOfSongs) {
    var div = $("<div>");
    div.append($("<h2 >").text("Song List: "));
    div.addClass('similars');
    //arrOfSong = data1.concat(data.response.songs);
    arrOfSongs.forEach(function(arrOfSong) {
        var simDiv = getArtistDiv(arrOfSong);
        if (simDiv) {
            div.append(simDiv);
        }
    });
    $("#all_results_names").append(div);
}

function getArtistDiv(song) {
    //var image = getBestImage(artist.spotifyArtistInfo.images, 600);
    if (song.title) {
        var adiv = $("<div style=\"cursor:pointer\">");
        adiv.addClass('title');
        adiv.append($("<h4 align=left>").text(song.title));
      
        adiv.on('click', function() {
            $("#title").val(song.title);
            location.href = "info_song?songName="+song.title;
        });
        return adiv;
    } else {
        return null;
    }

}

function newArtist() {
    var sID = "<%= song.getId() %>";
    var sName = "<%= song.getName() %>";
    //alert(sID);
    //alert(sName);
    searchArtist(sName);
    setTimeout(function(){
    	fetchPlaylist(sID, false, .2, function showSimilars(arrOfSongs){
    		var div = $("<div>");
   		    div.append($("<h2 >").text("Song List: "));
   		    div.addClass('similars');
   		    arrOfSongs = data1.concat(data.response.songs);
   		    arrOfSongs.forEach(function(arrOfSong) {
   		        var simDiv = getArtistDiv(arrOfSong);
   		        if (simDiv) {
   		            div.append(simDiv);
   		        }
   		    });
   		    $("#all_results_names").append(div);
    	});
    }, 500);
    
}

function info(txt) {
    $("#info").text(txt);
}

function initUI() {
	newArtist();
}

$(document).ready(function() {
    initUI();
});
</script>
</html>
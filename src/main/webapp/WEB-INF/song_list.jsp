<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.music.analysis.module.*"%>
<%
	Song song = (Song) request.getAttribute("song_obj");
	//String sID = (String) request.getAttribute("song_id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Echo Nest + Spotify Play Button Demo</title>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link type="text/css" href="demo_styles.css" rel="stylesheet" />
</head>

<body>
    <h1 id='title'> Echo Nest + Spotify Play Button Demo</h1>
    <div class ='info'>
        A demo of how the 
        <a href="http://developer.echonest.com/docs/v4/playlist.html#static">Echo Nest playlist API</a>
        can work with the new Spotify Widget. See <a
        href="http://musicmachinery.com/2012/04/11/the-spotify-play-button-a-lightening-demo/">MusicMachinery</a> for more
        details. 
        This version uses the single multi-track player. Also see the <a href="SpotifyWidget.html">multi-player
        version</a>.
    </div>

    <br>

    <span class='box'> Seed artist:
        <input title="Artist" type="text"  size=24 id="artist" 
        onkeydown="if (event.keyCode == 13) newArtist()" name="artist" value='weezer'/>
    </span>


    <button value="go" id="go" name="go"  disabled onclick="newArtist()"> Go </button>

    <div id="info"> </div>
    <div id="all_results">
        <div id='tracks'>
            <h2> The Playlist</h2>
            <div id="results"> </div>
        </div>
    </div>
</body>

<script type="text/javascript">

jQuery.ajaxSettings.traditional = true; 


var embed = '<iframe src="https://embed.spotify.com/?uri=spotify:trackset:PREFEREDTITLE:TRACKS" style="width:640px; height:520px;" frameborder="0" allowtransparency="true"></iframe>';

function fetchArtistPlaylist(sID,  wandering, variety) {
    var url = 'http://developer.echonest.com/api/v4/playlist/static';

    $("#all_results").hide();
    info("Creating the playlist ...");
    $.getJSON(url, { 
    	'song_id': sID, 
	    //'title': sName,
        'api_key': "ICY4WXWWLLSRR8ZIX",
        'bucket': [ 'id:spotify', 'tracks'], 
        'limit' : true,
        'variety' : 1,
        'results': 10, 
        'type':'song-radio'},function(data) {

            	 /* 'distribution' : wandering ? 'wandering' : 'focused' */
        info("");
        $("#results").empty();
        $("#all_results").show();

        var tracks = "";
        for (var i = 0; i < data.response.songs.length; i++) {
            var song = data.response.songs[i];
            var tid = song.tracks[0].foreign_id.replace('spotify:track:', '');
            tracks = tracks + tid + ',';
        }
        var tembed = embed.replace('TRACKS', tracks);
        tembed = tembed.replace('PREFEREDTITLE', data.reponse.songs[0].name + ' playlist');
        var li = $("<span>").html(tembed);
        $("#results").append(li);
    });
}


function newArtist() {
	var sID = "<%= song.getId() %>";
    var sName = "<%= song.getName() %>";
    var artist = $("#artist").val();
    fetchArtistPlaylist(sID, false, .2);
}

function info(txt) {
    $("#info").text(txt);
}


$(document).ready(function() {
    $("#go").removeAttr("disabled");
    $("#all_results").hide();
});
</script>
</body>
</html>
<%@ page language="java" import="java.util.*"%> <!-- pageEncoding="ISO-8859-1"%> -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.music.analysis.module.*"%>
<%
	Singer singer = new Singer();
	singer = (Singer) request.getAttribute("obj_singer");
	String sName = singer.getName();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="framework/spotify_en_tools.js"></script>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link type="text/css" href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="css/spotify_common_styles.css" rel="stylesheet" />
    
</head>
<body background="images/web_background.jpg" onload="newArtist()">
	<table width="80%" border="0" align="center">
		<tr>
		    <td height="80" colspan="2">
		      <!--Import head.jsp-->
		      <jsp:include flush="true" page="head.jsp"></jsp:include>
		    </td>
		</tr>
		<tr>			
			<td width="30%">
				
				<h1 ><%= singer.getName()%></h1>
				<div id="all_results_simSingers"> </div>
			</td>
			<td width="70%">
				
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
</body>

<script type="text/javascript">

jQuery.ajaxSettings.traditional = true; 
var config = getConfig();

//2.
function fetchArtistPlaylist(artist,  wandering, variety) {
    var url = config.echoNestHost + 'api/v4/playlist/static';   //URL for fetching 
    $("#all_results_playlist").empty();
    info("Creating the playlist ...");
    $.getJSON(url, { 
    		'artist': artist, 
            'api_key': config.apiKey,
            'bucket': [ 'id:' + config.spotifySpace, 'tracks'],
            'limit' : true,
            'variety' : 1, 
            'results': 40, 
            'type':'artist-radio',  }) 
        .done(function(data) {
            info("");
            if (! ('songs' in data.response)) {
                info("Can't find that artist");
            }else {
                var title = "Artist radio for " + artist;
                getSpotifyPlayer(data.response.songs, function(player) {//???? where is the definition of player??
                    console.log('got the player');
                    $("#all_results_playList").append(player);
                });
            }
        })
        .error( function() {
            info("Whoops, had some trouble getting that playlist");
        }) ;
}

function fetchSimilarArtists(artist, callback) {
    var url = config.echoNestHost + 'api/v4/artist/similar';
    $("#all_results_simSingers").empty();
    info("Getting similar artists ...");
    $.getJSON(url, { 
            'api_key': config.apiKey,
            'id' : artist.id,
            'bucket': [ 'id:' + config.spotifySpace], 
            'limit' : true,
          }) 
        .done(function(data) {
            info("");
            if (data.response.status.code == 0 && data.response.artists.length > 0) {
                callback(data.response.artists);                       //callback????
            } else {
                info("No similars for " + artist.name);
            }
        })
        .error( function() {
            info("Whoops, had some trouble getting that playlist");
        }) ;
}

function fetchSpotifyImagesForArtists(artists, callback) {            //callback????
    info("Fetching spotify images for artists ...");
    console.log('fetchSpotifyImagesForArtists');
    var fids = [];
    artists.forEach(function(artist) {
        fids.push(fidToSpid(artist.foreign_ids[0].foreign_id));
    });

    $.getJSON("https://api.spotify.com/v1/artists/", { 'ids': fids.join(',')}) 
        .done(function(data) {
            data.artists.forEach(function(sartist, which) {
                artists[which].spotifyArtistInfo = sartist;
            });
            callback(artists);
        })
        .error( function() {
            info("Whoops, had some trouble getting that playlist");
        }) ;
}

function showArtists(seed, similars) {
    info("");
    showSimilars(seed, similars);
}

function showSimilars(seed, similars) {
    var div = $("<div>");
    div.append($("<h2 >").text("Similar Artists: "));
    div.addClass('similars');
    similars.forEach(function(similar) {
        var simDiv = getArtistDiv(similar);
        if (simDiv) {
            div.append(simDiv);
        }
    });
    $("#all_results_simSingers").append(div);
}

function getArtistDiv(artist) {
    var image = getBestImage(artist.spotifyArtistInfo.images, 600);
    if (image) {
        var adiv = $("<div style=\"cursor:pointer\">");
        adiv.addClass('artist');
        adiv.append($("<h4>").text(artist.name));
       /* var img = $("<img height='100' width='100'>");
        img.attr('src', image.url);
        adiv.append(img); */
        /* img.on('click', function() {
            $("#artist").val(artist.name);
            fetchSimilarArtists(artist, function(similars) {
                fetchSpotifyImagesForArtists(similars, function(similars) {
                    showArtists(artist, similars);
                });
            });
        }); */
        /* alert(artist.name); */
        adiv.on('click', function() {
            $("#artist").val(artist.name);
            /* fetchSimilarArtists(artist, function(similars) {
                fetchSpotifyImagesForArtists(similars, function(similars) {
                    showArtists(artist, similars);
                });
            }); */
            location.href = "info_singer?singerName="+artist.name;
        });
        return adiv;
    } else {
        return null;
    }

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

//3.
function searchArtist(name, callback) {
    var url = config.echoNestHost + 'api/v4/artist/search';
    $("#all_results_simSingers").empty();
    info("Searching for artists ...");
    $.getJSON(url, {
            'api_key': config.apiKey,
            'name' : name,
            'bucket': [ 'id:' + config.spotifySpace], 
            'limit' : true,
          }) 
        .done(function(data) {
            info("");
            callback(data);
        })
        .error( function() {
            info("Whoops, had some trouble finding that artist");
        }) ;
}

//1.Get the name 
function newArtist() {
    var artist = "<%= sName%>";
    fetchArtistPlaylist(artist, false, .2);
    searchArtist(artist, function(data) {
        if (data.response.status.code == 0 && data.response.artists.length > 0) {
            var seed = data.response.artists[0];
            fetchSpotifyImagesForArtists([seed], function(seeds) {
                fetchSimilarArtists(seeds[0], function(similars) {
                    fetchSpotifyImagesForArtists(similars, function(similars) {
                        showArtists(seed, similars);
                    });
                });
            });
        }else {
            info("Can't find that artist");
        }
    });
}

function info(txt) {
    $("#info").text(txt);
}

function initUI() {
    $("#artist").on('keydown', function(evt) {
        if (evt.keyCode == 13) {
            newArtist();
        }
    });
    /* $("#go").on("click", function() {
        newArtist();
    }); */
}

$(document).ready(function() {
    initUI();
});

</script>
</html>

function doAjax(id, url) {
	var xmlhttp = null;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}

	else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var index = url.indexOf("?");
	if (index != -1) {
		url += "&";
	} else {
		url += "?";
	}
	url += "time=" + new Date().getTime();
	xmlhttp.open("post", url, true);
	xmlhttp.send(null);

	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var div = document.getElementById(id);
			//alert(xmlhttp.responseText);
			div.innerHTML = xmlhttp.responseText;
			// div.innerHTML = div.innerHTML + xmlhttp.responseText;
		}
	}
}

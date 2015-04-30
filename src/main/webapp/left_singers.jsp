<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./framework/d3.js"></script>
<script src="./framework/d3.layout.cloud.js"></script>
<script>
  var fill = d3.scale.category20();
  
  var data = [{word:"XU Wei",weight:90},{word:"ZHENG Jun",weight:70},{word:"ZHANG Liangying",weight:30},{word:"A-Lin",weight:50},
              {word:"LI Zhi",weight:30},{word:"ZHAO Lei",weight:60},{word:"HAN Lei",weight:10},{word:"LI Zongwei",weight:60},
              {word:"YU Quan",weight:55},{word:"WAN Xiaoli",weight:40},{word:"SUN Yanzi",weight:60},{word:"CAI Jianya",weight:20},
              {word:"WANG Will",weight:30},{word:"KANG Travis",weight:30},{word:"LI Brisk",weight:20},{word:"LI Siyuan",weight:20},
              {word:"WAN Kevin",weight:40}];

  d3.layout.cloud().size([800, 500])
      .words(data.map(function(d) {
        return {text: d.word, size: d.weight};
      }))
      .padding(5)
      .rotate(function() { return ~~(Math.random() * 2) * 90; })
      .font("Impact")
      .fontSize(function(d) { return d.size; })
      .on("end", draw)
      .start();

  function draw(words) {
    d3.select("span").append("svg")
        .attr("width", 800)
        .attr("height", 500)
      .append("g")
        .attr("transform", "translate(400,250)")
      .selectAll("text")
        .data(words)
      .enter().append("text")
        .style("font-size", function(d) { return d.size + "px"; })
        .style("font-family", "Impact")
        .style("fill", function(d, i) { return fill(i); })
        .attr("text-anchor", "middle")
        .attr("transform", function(d) {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text(function(d) { return d.text; });
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	.word_cloud{
		background-color:#484545;
		padding:10px;
	}
</style>
</head>
<body>


</body>
</html>
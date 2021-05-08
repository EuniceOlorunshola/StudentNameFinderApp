<!DOCTYPE HTML>
<html>
<head>
  <title> View - Mix or Match </title>
  <style>
    table,td,th{
      border: groove 2px;
    }
	h2{
	border-style:dotted;
	text-align:center;
	background-color:rgb(128,128,128);
	color:white;
}
.sub-btn{
background-color: #32cd32;
color: white;
cursor: pointer;
}
  </style>
</head>

<body>
<h2> View all results  </h2>
<?php
  $servername = "localhost";
  $username = "eolorunshola1";
  $password = "eolorunshola1";
  $dbname = "eolorunshola1";

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
  }

  $sql = "SELECT firstNAME, flipsamount, rating FROM Player";
  $result = $conn->query($sql);

  if ($result->num_rows > 0) {

    print "<table>";
    print "<tr>";
    print "<th> FirstName </th>";
    print "<th> FlipsAmount </th>";
    print "<th> RatingofGame</th>";
    print "</tr>";

    while($row = $result->fetch_assoc()) {
      print "<tr><td>".$row["firstNAME"].
            "</td><td>".$row["flipsamount"].
            "</td><td>".$row["rating"].
            "</td></tr>";
    }

    print "</table>";

  } else {
    print "No player results found";
  }
  $conn->close();
?>
<form method="post">
 <input type="button" onclick="document.location='index.html'" value="Home" class="sub-btn">
</form>
</body>
</html>

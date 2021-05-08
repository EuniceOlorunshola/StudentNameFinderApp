<!DOCTYPE HTML>
<html>
<head>
  <title> 	Search - Mix or Match </title>
</head>
<style>
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
<body>
<h2> Search Player Resutls</h2>
<form method="post">
  <input type="text" size=30 name="fname" required placeholder="Enter your First Name"><br><br>
  <input type="text" size=30 name="flipsamot" required placeholder="Enter your number of Flips"><br><br>
  <input type="submit" value="Submit" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="reset" value="Reset" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="button" onclick="document.location='index.html'" value="Home" class="sub-btn">
</form>

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

  if($_SERVER['REQUEST_METHOD'] === 'POST') {
	$fname = $_POST['fname'];
    $flipsamot = $_POST['flipsamot'];
   

    $sql = "SELECT * FROM Player WHERE firstName='$fname' ANd flipsamount='$flipsamot'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {

      print "<table>";
      print "<tr>";
      print "<th> FirstName </th>";
      print "<th> FlipsAmount </th>";
      print "<th> RatingOfGame </th>";
      print "</tr>";
      while($row = $result->fetch_assoc()) {
        print "<tr><td>".$row["firstName"].
              "</td><td>".$row["flipsamount"].
              "</td><td>".$row["rating"].
              "</td></tr>";
      }
      print "</table>";
    } else {
      print "No results found ";
    }
  }
  $conn->close();
?>

</body>
</html>


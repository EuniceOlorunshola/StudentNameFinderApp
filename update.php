<!DOCTYPE HTML>
<html>
<head>
  <title> Update - Mix or Match </title>
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
<h2> Update Player Resutls</h2>
<form method="post">

 
  <input type="text" size=30 name="fname"placeholder="Enter your First Name"><br><br>
  <input type="text" size=30 name="flipsamot" required placeholder="Enter the Amount of flips you made "><br><br>
  <input type ="text" size="30" name="rate" required placeholder="What is your Rating"><br><br>
  <input type="reset" value="Reset" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="submit" value="Submit" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="button" onclick="document.location='index.html'" value="Home" class="sub-btn">
</form>

<?php
  $servername = "localhost";
  $username ="eolorunshola1";
  $password = "eolorunshola1";
  $dbname = "eolorunshola1";

  $conn = new mysqli($servername, $username, $password, $dbname);
  if($conn->connect_error) {
    die("Connection failed. ".$conn->connect_error);
  }

  if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fname = $_POST['fname'];
    $flipsamot = $_POST['flipsamot'];
    $rate = $_POST['rate'];

    $sql = "SELECT firstNAME, flipsamount FROM Player WHERE firstNAME='$fname' ANd flipsamount='$flipsamot'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
      $sql = "UPDATE Player SET firstNAME='$fname', flipsamot='$flipsamount', rating='$rate',  WHERE ='$fname";
      if ($conn->query($sql) === TRUE) {
        print "Player results updated successfully";
      } else {
        print "Error updating player results: " . $conn->error;
      }
    }
    else{
      print "Player Results  could not be found.";
    }
  }
  $conn->close();
?>

</body>
</html>

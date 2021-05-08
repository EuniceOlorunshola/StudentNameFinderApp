<!DOCTYPE HTML>
<html>
<head>
  <title> Delete - Mix or match Game </title>
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
<h2> Delete an player </h2>
<form method="post">
  <input type="text" size=30 name="fname" required placeholder="Enter your First Name "><br><br>
  <input type="text" size=30 name="flipsamot" required placeholder="Enter the number of Flips"><br><br>
  <input type="reset" value="Reset" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="submit" value="Submit" class="sub-btn">
  &nbsp&nbsp&nbsp
  &nbsp&nbsp&nbsp
  <input type="button" onclick="document.location='index.html'" value="Home" class="sub-btn">
</form>

<?php
  $servername = "localhost";
  $username = "eolorunshola1";
  $password = "eolorunshola1";
  $dbname = "eolorunshola1";

  $conn = new mysqli($servername, $username, $password, $dbname);
  if($conn->connect_error) {
    die("Connection failed. ".$conn->connect_error);
  }

  if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fname = $_POST['fname'];
    $flipsamot = $_POST['flipsamot'];

    $sql = "SELECT firstNAME, flipsamount FROM Player WHERE firstNAME='$fname' ANd flipsamount='$flipsamot'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
      $sql = "DELETE FROM Player WHERE firstNAME='$fname' AND flipsamount='$flipsamot'";
      if ($conn->query($sql) === TRUE) {
        echo "PLayer Results deleted successfully";
      } else {
        echo "Error deleting results: " . $conn->error;
      }
    }
    else{
      echo "No player found.";
    }
  }
  $conn->close();
?>

</body>
</html>

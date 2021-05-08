<!DOCTYPE HTML>
<html>
<head>
  <title> Add-Mix or Match </title>
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
<h2> Add an player  </h2>
<form method="post">
  <input type="text" size=30 name="fname"placeholder="Enter your First Name"><br><br>
  <input type="text" size=30 name="flipsamot" required placeholder="Enter the Amount of flips you made "><br><br>
  <input type ="text" size="30" name="rate" required placeholder="What is your Rating"><br><br>
  <input type="submit" value="Submit" class ="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="reset" value="Reset" class="sub-btn">
  &nbsp&nbsp&nbsp
  <input type="button" onclick="document.location='index.html'" value="Home" class ="sub-btn">
</form>
<?php
  $servername = "localhost";
  $username = "eolorunshola1";
  $password = "eolorunshola1";
  $dbname = "eolorunshola1";

  $conn = new mysqli($servername, $username, $password, $dbname);
  if($conn->connect_error) {
    die("Connection failed" . $conn->connect_error);
  }

  $sql = "CREATE TABLE Player (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstNAME VARCHAR(50),
   flipsamount VARCHAR (12),
    rating varchar(12),
  )";

  if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fname = $_POST['fname'];
    $flipsamot =$_POST['flipsamot'];
    $rate = $_POST['rate'];

    $sql = "INSERT INTO Player(firstNAME,  flipsamount, rating)
    VALUES('$fname','$flipsamot','$rate')";

    if($conn->query($sql) === TRUE){
      echo "Player results created successfully.";
    }else{
      echo "Error: ". $sql . "<br>". $conn->error;
    }
    $conn->close();
  }
?>

</body>
</html>

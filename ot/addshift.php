<?php 

require "init.php";
$shifttitle = $_POST["shifttitle"];
$description = $_POST["description"];
$hourlyrate = $_POST["hourlyrate"];
$date = $_POST["date"];
$ftime = $_POST["ftime"];
$ttime = $_POST["ttime"];
$uniquecode = $_POST["uniquecode"];
$employee = $_POST["employee"];

$sql = "SELECT * FROM users where uniquecode = '$uniquecode'"; //Check if Unique code is in the users db

$mysql_insert = "INSERT into jobs  (shifttitle, description, hourlyrate, date, ftime, ttime, uniquecode, employee) values ('$shifttitle','$description','$hourlyrate','$date','$ftime','$ttime','$uniquecode','$employee')";

$finduniquecode = mysqli_query($connection,$sql);



if (mysqli_num_rows($finduniquecode) <= 0){
	echo "Shift cannont be added, uniquecode not recognised.";
}

else{
	$connection->query($mysql_insert) === TRUE;
	echo "Shift has been added into the database";
}



$connection->close();



?>
<?php 

require "init.php";
$date = $_POST["date"];
$fromtime = $_POST["fromtime"];
$totime = $_POST["totime"];
$uniquecode = $_POST["uniquecode"];
$employee = $_POST["employee"];

$sql = "SELECT * FROM users where uniquecode = '$uniquecode'"; //Check if Unique code is in the users db

$mysql_insert = "INSERT into available  (date, fromtime, totime, uniquecode, employee) values ('$date','$fromtime','$totime','$uniquecode','$employee')";

$finduniquecode = mysqli_query($connection,$sql);






if (($date) === ""){
	echo "Date has not been entered. Please try again";
}
else if (($fromtime) === ""){
	echo "Start Time has not been entered. Please try again";
}
else if (($totime) === ""){
	echo "End Time has not been entered. Please try again";
}
else{
	$connection->query($mysql_insert) === TRUE;
	echo "Your Availability has been updated";
}


$connection->close();



?>
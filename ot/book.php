<?php 

require "init.php";

$employee = $_POST["employee"];
$id = $_POST["id"];

$sql = "UPDATE jobs SET employee = '$employee' WHERE id = '$id'"; //update employee with users fullname


if (mysqli_query($connection,$sql)){
	echo "Shift Booked Successfully";
}
else{
	echo "Error, Please try again";
}

$connection->close();

?>
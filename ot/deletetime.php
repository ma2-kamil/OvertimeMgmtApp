<?php 

require "init.php";

$id = $_POST["id"];

$sql = "DELETE FROM available WHERE id = '$id'"; //Delete Time Row with matching ID

if (mysqli_query($connection,$sql)){
	echo "Date Removed From Database";
}
else{
	echo "Error, Please try again";
}

$connection->close();

?>
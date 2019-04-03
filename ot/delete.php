<?php 

require "init.php";

$id = $_POST["id"];

$sql = "DELETE FROM jobs WHERE id = '$id'"; //Delete Job with matching ID

if (mysqli_query($connection,$sql)){
	echo "Shift Deleted";
}
else{
	echo "Error, Please try again";
}

$connection->close();

?>
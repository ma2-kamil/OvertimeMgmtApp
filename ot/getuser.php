<?php 

require "init.php";


$username = $_POST["username"];
$password = $_POST["password"];

$sql = "SELECT * FROM users where username like '$username' ";
$result = mysqli_query($connection,$sql);
$result1 = mysqli_query($connection,$sql);


$response = array();

$response["success"] = false;

if(mysqli_num_rows($result) > 0){
		
	
	while ($row = mysqli_fetch_array($result)){
		if (($username == $row["username"]) AND (password_verify($password, $row["password"]))){
		
			
			$row = mysqli_fetch_assoc($result1);
			$response["success"] = true;
			$response["fullname"] = $row['fullname'];
			$response["username"] = $row['username'];
			$response["password"] = $password;
			$response["email"] = $row['email'];
			$response["shiftmanager"] = $row['shiftmanager'];
			$response["uniquecode"] = $row['uniquecode'];
	
		
		}
		
		else{
			$status = "ERROR";
			echo json_encode($status, JSON_PRETTY_PRINT);
		}
	}
	
} 
echo json_encode($response);


mysqli_close($connection);


?>
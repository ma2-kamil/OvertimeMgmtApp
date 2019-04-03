<?php 

require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];

$sql = "SELECT * FROM users where username = '$username'";
$sqlname = "SELECT fullname FROM users where username = '$username'";
$sqlinfo =  "SELECT * FROM users where username = '$username'";

$result = mysqli_query($connection,$sql);
$resultname = mysqli_query($connection,$sqlname);
$resultinfo = mysqli_query($connection,$sqlinfo);

$response = array();
$response["success"] = false;

if(mysqli_num_rows($result) > 0){
		
	
	while ($row = mysqli_fetch_array($result)){
		if (password_verify($password, $row["password"])){
			$row = mysqli_fetch_assoc($resultname);
			$fullname = $row['fullname'];
			$status = "Login Successful. Hi $fullname";
			echo json_encode ($status, JSON_PRETTY_PRINT);
			
			/*
			$row = mysqli_fetch_assoc($resultinfo);
			$response["success"] = true;
			$response["fullname"] = $row['fullname'];
			$response["username"] = $row['username'];
			$response["password"] = $password;
			$response["email"] = $row['email'];
			$response["shiftmanager"] = $row['shiftmanager'];
			$response["uniquecode"] = $row['uniquecode'];
	*/
		
		}
		
		else{
			$status = "Incorrect Username or Passwrod. Please try again";
			echo json_encode($status, JSON_PRETTY_PRINT);
		}
	}
	
} 

//echo json_encode($response);


mysqli_close($connection);


?>
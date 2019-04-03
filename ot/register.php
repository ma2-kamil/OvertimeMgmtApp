<?php 

require "init.php";
$fullname = $_POST["fullname"];
$username = $_POST["username"];
$password = $_POST["password"];
$email = $_POST["email"];
$shiftmanager = $_POST["shiftmanager"];
$uniquecode = $_POST["uniquecode"];
$hashed_password = password_hash($password, PASSWORD_DEFAULT);



$sql = "SELECT * FROM users where username = '$username' or email = '$email'"; //Check if username or email is already in DB 
$checkunique = "SELECT * FROM users where uniquecode LIKE '$uniquecode'"; //Error checking the unique code 
$mysql_insert = "INSERT into users  (fullname, username, password, email, shiftmanager, uniquecode) values ('$fullname','$username','$hashed_password','$email','$shiftmanager','$uniquecode')";

$sameuser = mysqli_query($connection,$sql);
$uniquerows = mysqli_query($connection,$checkunique);


if (mysqli_num_rows($sameuser) > 0){
	echo "Username or Email not available, Please try again";
}

elseif (($_POST["shiftmanager"] === '1') AND (mysqli_num_rows($uniquerows) === 0)){
	//echo "Shift manager AND UNIQUE CODE ";
	$connection->query($mysql_insert) === TRUE;
	echo "Account has Successfully been created";
}

elseif (($_POST["shiftmanager"] === '1') AND (mysqli_num_rows($uniquerows) > 0)){
	// Show Error and say Unique code is not available
	echo "The Unique Code entered is not valid. Please try again.";
	
}



elseif (($_POST["shiftmanager"] === '0') AND (mysqli_num_rows($uniquerows) === 0)){
	// Show Error and say Unique code not valid
	echo "The Unique Code Entered is not valid. Please try again.";
	
}

elseif (($_POST["shiftmanager"] === '0') AND (mysqli_num_rows($uniquerows) > 0)){
	//echo "Employee Has correct Code";
	$connection->query($mysql_insert) === TRUE;
	echo "Account has Successfully been created";
}
else{
	echo "Error has occured, please try again";
}



/*
if ($connection->query($mysql_insert) === TRUE) {
	echo "Insert Success";
}
else {
	echo "Error: " . $mysql_insert . "<br>" . $connection->error;
	
}
*/

$connection->close();



?>
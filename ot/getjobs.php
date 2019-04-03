<?php 

require "init.php";


$sql = "SELECT * FROM jobs";
$result = mysqli_query($connection,$sql);
$result1 = mysqli_query($connection,$sql);


$response = array();


while($row = mysqli_fetch_array($result)){
	

array_push($response,array("id"=>$row['id'], "shifttitle"=>$row['shifttitle'], "description"=>$row['description'], "hourlyrate"=>$row['hourlyrate'], 
"date"=>$row['date'], "ftime"=>$row['ftime'], "ttime"=>$row['ftime'], "uniquecode"=>$row['uniquecode'], "employee"=>$row['employee']));
	
}



//echo json_encode(array($response), JSON_PRETTY_PRINT);

echo json_encode (($response), JSON_PRETTY_PRINT);

mysqli_close($connection);


?>
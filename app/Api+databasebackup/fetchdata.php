<?php

	header('Content-Type: application/json; charset=utf-8');  
	$host="localhost";
	$userName="yourusername";
	$password="yourpassword";
	$dbName="coxtunes_pagination";

	$conn= mysqli_connect($host,$userName,$password,$dbName);

	$response = array();
	
	if($conn)
	{
		    $page = $_POST['page'];
			$row_per_page = $_POST['row_per_page'];
			
			$begin = ($page * $row_per_page) - $row_per_page;
			$sql = "SELECT * FROM pagination LIMIT {$begin},{$row_per_page}";
			
			$data = array();
			
			if($result = mysqli_query($conn,$sql))
			{
				while ($row= mysqli_fetch_assoc($result)){
				
				    $data[] = $row; 
				}
				
				if(count($data) > 0)
				{
					$response["message"] = "Data Found";
					$response["status"] = 200;
					$response["data"] = $data;
				}
				else
				{
					$response["message"] = "Data Not Found";
					$response["status"] = 400;
				}
					
			}
			else
			{	
		        $response["message"] = "error in result";
				$response["status"] = 400;
				
			}
		
	}
	else
	{
		$response["message"] = "No Connection Found";
		$response["status"] = 505;
		
	}
	
	echo json_encode($response);

?>

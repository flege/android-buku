<?php
	// required headers
	header("Access-Control-Allow-Origin: *");
	header("Content-Type: application/json; charset=UTF-8");
	header("Access-Control-Allow-Methods: POST");
	header("Access-Control-Max-Age: 3600");
	header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
	 
	 
	// include database and object file
	include_once 'connection.php';
	include_once 'books.php';
	 
	// get database connection
	$database = new Database();
	$db = $database->getConnection();
	 
	// prepare books object
	$books = new books($db);
	 
	if($_SERVER['REQUEST_METHOD'] != 'DELETE')(exit());
	parse_str(file_get_contents("php://input"), $data);
	
	// set books id to be deleted
	$books->id = isset($data['id']) ? $data['id'] : exit();
	 
	// delete the books
	if($books->delete()){
		echo '{';
			echo '"message": "books was deleted."';
		echo '}';
	}
	 
	// if unable to delete the books
	else{
		echo '{';
			echo '"message": "Unable to delete object."';
		echo '}';
	}
?>
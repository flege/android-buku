<?php
	// required headers
	header("Access-Control-Allow-Origin: *");
	header("Content-Type: application/json; charset=UTF-8");
	header("Access-Control-Allow-Methods: POST");
	header("Access-Control-Max-Age: 3600");
	header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
	 
	// get database connection
	include_once 'connection.php';
	 
	// instantiate books object
	include_once 'books.php';
	 
	$database = new Database();
	$db = $database->getConnection();
	 
	$books = new books($db);
	
	if($_SERVER['REQUEST_METHOD'] != 'POST')(exit());
	parse_str(file_get_contents("php://input"), $data);

	// set books property values
	$books->title = isset($_POST['title']) ? $_POST['title'] : die();
	$books->author_name = isset($_POST['author_name']) ? $_POST['author_name'] : die();
	
	// create the books
	if($books->create()){
		echo '{';
			echo '"message": "books was created."';
		echo '}';
	}
	 
	// if unable to create the books, tell the user
	else{
		echo '{';
			echo '"message": "Unable to create books."';
		echo '}';
	}
?>
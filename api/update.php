<?php
	// required headers
	header("Access-Control-Allow-Origin: *");
	header("Content-Type: application/json; charset=UTF-8");
	header("Access-Control-Allow-Methods: POST");
	header("Access-Control-Max-Age: 3600");
	header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
	 
	// include database and object files
	include_once 'connection.php';
	include_once 'books.php';
	 
	// get database connection
	$database = new Database();
	$db = $database->getConnection();
	 
	// prepare books object
	$books = new books($db);

	if($_SERVER['REQUEST_METHOD'] != 'PUT')(exit());
	parse_str(file_get_contents("php://input"), $data);
	
	// set ID property of books to be edited
	$books->id = isset($data['id']) ? $data['id'] : exit();
	 
	// // set books property values
	$books->title = isset($data['title']) ? $data['title'] : exit();
	$books->author_name = isset($data['author_name']) ? $data['author_name'] : exit();
	 
	// // update the books
	if($books->update()){
		echo '{';
			echo '"message": "books was updated."';
		echo '}';
	}
	 
	// if unable to update the books, tell the user
	else{
		echo '{';
			echo '"message": "Unable to update books."';
		echo '}';
	}
?>
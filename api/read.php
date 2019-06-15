<?php
	// required headers
	header("Access-Control-Allow-Origin: *");
	header("Content-Type: application/json; charset=UTF-8");
	 
	// include database and object files
	include_once 'connection.php';
	include_once 'books.php';
	 
	// instantiate database and books object
	$database = new Database();
	$db = $database->getConnection();
	 
	$books = new books($db);
	 
	// query books
	$stmt = $books->read();
	$num = $stmt->rowCount();

	if($num>0){
		// books array
		$books_arr=array();
		$books_arr["result"]=array();
		while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
			// extract row
			extract($row);

			$books_item=array(
				"id" => $id,
				"title" => $title,
				"author_name" => $author_name,
			);
	 
			array_push($books_arr["result"], $books_item);
        }
	 
		echo json_encode($books_arr);
	}else{
		echo json_encode(
			array("message" => "No books found.")
		);
	}
?>
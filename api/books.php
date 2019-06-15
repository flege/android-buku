<?php
class Books{
    // database connection and table name
    private $conn;
    private $table_name = "books";
 
    // object properties
    public $id;
    public $title;
    public $author_name;
 
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }
	
	// read pengiriman
	function read(){
		// select all query
		$query = "SELECT * FROM " . $this->table_name;
	 
		// prepare query statement
		$stmt = $this->conn->prepare($query);
	 
		// execute query
		$stmt->execute();
	 
		return $stmt;
	}
	
	// create product
	function create(){
		// query to insert record
		$query = "INSERT INTO ".$this->table_name." SET title=?, author_name=?";
	 
		// prepare query
		$stmt = $this->conn->prepare($query);
	 
        // sanitize
        $this->title = htmlspecialchars(strip_tags($this->title));
		$this->author_name = htmlspecialchars(strip_tags($this->author_name));
	 
		// bind values
        $stmt->bindParam(1, $this->title);
        $stmt->bindParam(2, $this->author_name);
	 
		// execute query
		if($stmt->execute()){
			return true;
		}
		return false;
	}
	
	// update the product
	function update(){
		// update query
		$query = "UPDATE ".$this->table_name." SET title=?, author_name=? WHERE id = ?";
	 
		// prepare query statement
		$stmt = $this->conn->prepare($query);
	 
		// sanitize
		$this->title = htmlspecialchars(strip_tags($this->title));
		$this->author_name = htmlspecialchars(strip_tags($this->author_name));
		$this->id = htmlspecialchars(strip_tags($this->id));
	 
		// bind new values
		$stmt->bindParam(1, $this->title);
		$stmt->bindParam(2, $this->author_name);
		$stmt->bindParam(3, $this->id);
	 
		// execute the query
		if($stmt->execute()){
			return true;
		}
		// echo $stmt->errorInfo();
		return false;
	}
	// delete the product
	function delete(){
		// delete query
		$query = "DELETE FROM ".$this->table_name." WHERE id = ?";
	 
		// prepare query
		$stmt = $this->conn->prepare($query);
	 
		// sanitize
		$this->id = htmlspecialchars(strip_tags($this->id));
	 
		// bind id of record to delete
		$stmt->bindParam(1, $this->id);
	 
		// execute query
		if($stmt->execute()){
			return true;
		}
	 
		return false;
		 
	}
}
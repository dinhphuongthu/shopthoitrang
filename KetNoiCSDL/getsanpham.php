<?php
    include "connect.php";
    class Sanpham{
    	function Sanpham($id,$name,$price,$image,$intro,$feature,$idsp){
    		$this->id=$id;
    		$this->name=$name;
    		$this->price=$price;
    		$this->image=$image;
    		$this->intro=$intro;
    		$this->feature=$feature;
    		$this->catid=$idsp;
    	}
    }
    if(isset($_GET['page'])){
    	$idsanpham=$_GET['page'];
    $mangsanpham = array();
    $query = "SELECT * FROM product WHERE catid=$idsanpham";
    $data = mysqli_query($conn,$query);
    while($row = mysqli_fetch_assoc($data))
    {
          array_push($mangsanpham,new Sanpham(
          	$row['id'],
            $row['name'],
            $row['price'],
            $row['image'],
            $row['intro'],
            $row['feature'],
            $row['catid']));
    }
    }
    echo json_encode($mangsanpham);
?>

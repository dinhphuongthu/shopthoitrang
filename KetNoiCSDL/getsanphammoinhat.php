<?php
    include 'connect.php';
    $mangspmoinhat=array();
    $query="SELECT * FROM product ORDER BY id DESC LIMIT 6";
    $data=mysqli_query($conn,$query);
    while($row = mysqli_fetch_assoc($data)){
    	array_push($mangspmoinhat,new Sanphammoinhat(
    		$row['id'],
            $row['name'],
            $row['price'],
            $row['image'],
            $row['intro'],
            $row['feature'],
            $row['catid']));
    }
    echo json_encode($mangspmoinhat);
    class Sanphammoinhat{
    	function Sanphammoinhat($id,$tensp,$giasp,$hinhanhsp,$motasp,$feature,$idsanpham){
    		$this->id=$id;
    		$this->name=$tensp;
    		$this->price=$giasp;
            $this->image=$hinhanhsp;
            $this->intro=$motasp;
            $this->feature=$feature;
            $this->catid=$idsanpham;
    	}
    }
?>
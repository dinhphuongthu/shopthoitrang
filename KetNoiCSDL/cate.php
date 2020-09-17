<?php
include "connect.php";
$query="SELECT * FROM cate";
$data=mysqli_query($conn,$query);
$mangloaisp=array();
while($row = mysqli_fetch_assoc($data))
{
	array_push($mangloaisp,new Loaisp(
		$row['id'],
        $row['name'],
        $row['image']));
	
}
echo json_encode($mangloaisp);
class Loaisp{
	function Loaisp ($id,$name,$image){
		$this->id=$id;
		$this->name=$name;
		$this->image=$image;
	}
}

?>
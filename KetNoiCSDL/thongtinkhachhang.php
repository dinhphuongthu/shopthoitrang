<?php
    include "connect.php";

    $tenkhachhang=$_POST['tenkhachhang'];
    $sodienthoai=$_POST['sodienthoai'];
    $email=$_POST['email'];
    $diachi=$_POST['diachi'];
    if(strlen($tenkhachhang)>0 && strlen($email)>0 && strlen($sodienthoai)>0 && strlen($diachi)>0){
    	$query="INSERT INTO DonHang(id,tenkhachhang,sodienthoai,email,diachi) VALUES (null,'$tenkhachhang','$sodienthoai','$email','$diachi')";
    	if(mysqli_query($conn,$query)){
    		$iddonhang=$conn->insert_id;
    		echo $iddonhang;
    	}else{
    		echo "Thất bại";
    	}
    }else{
    	echo "Ban hay kiem tra cac du lieu";
    }

?>
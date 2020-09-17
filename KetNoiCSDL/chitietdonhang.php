<?php
   include 'connect.php';
   $json='[{"giasanpham":340000,"madonhang":1,"soluongsanpham":2,"tensanpham":"Đầm xòe bèo eo kèm dây cột - C3665","masanpham":1}]';
   $data=json_decode($json,true);
   foreach ($data as $value) {
   	 $madonhang=$value['madonhang'];
   	 $masanpham=$value['masanpham'];
   	 $tensanpham=$value['tensanpham'];
   	 $giasanpham=$value['giasanpham'];
   	 $soluongsanpham=$value['soluongsanpham'];
   	 $query="INSERT INTO ChiTietDonHang(id,madonhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES (null,'$madonhang','$masanpham','$tensanpham','$giasanpham','$soluongsanpham')";
   	 $DATA=mysqli_query($conn,$query);
   }
   if($DATA){
   	echo "1";
   }else{
   	echo "0";
   }
?>
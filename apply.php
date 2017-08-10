<?php
define('HOST','localhost');
define('USER','id2452095_shramo');
define('PASS','shramo1');
define('DB','id2452095_company');

 $con = mysqli_connect(HOST,USER,PASS,DB) ;
 if($_SERVER['REQUEST_METHOD']=='POST'){
 // $names = $_POST['name'];
  $sector = $_POST['sector'];
 // $score = $_POST['score'];
 //$count = $_POST['number']
 
$sql= "SELECT COUNT(sector) FROM Company where (sector='$sector')";
$total=mysqli_fetch_array(mysqli_query($con,$sql));
$sql1="SELECT number FROM Applied where (sector='$sector')";
$now=mysqli_fetch_array(mysqli_query($con,$sql1));
$sql4="SELECT COUNT(sector) FROM Applied where (sector='$sector')";
$now1=mysqli_fetch_array(mysqli_query($con,$sql4));

if($total[0]<10 && $now1[0]==0)
{
$sql9="INSERT INTO Applied(sector,number) VALUES ('$sector',0)";
mysqli_query($con,$sql9);
echo"Max reached";
}
elseif($now1[0]==0 && ($now[0] < intval(0.1*$total[0])))
{
$sql2="INSERT INTO Applied(sector,number) VALUES ('$sector',1)";
mysqli_query($con,$sql2);
echo "Done";
}
else{
	if($now[0] < intval(0.1*$total[0]))
	{
          
      $sql3="UPDATE Applied  SET number=number+1 WHERE sector='$sector'";
	mysqli_query($con,$sql3);
	echo "Done";
	}
	else
	{
  	echo "Maximum limit exit, you cannot apply";
	}
    }
}
else{
echo 'error';
}
 ?>
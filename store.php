<?php
define('HOST','localhost');
define('USER','id2452095_shramo');
define('PASS','shramo1');
define('DB','id2452095_company');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from Company";
 
$res = mysqli_query($con,$sql);
 if (!$res) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}

$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('name'=>$row[0],
'sector'=>$row[1],
'score'=>$row[2]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>
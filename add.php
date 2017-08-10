<?php
define('HOST','localhost');
define('USER','id2452095_shramo');
define('PASS','shramo1');
define('DB','id2452095_company');
$con = mysqli_connect(HOST,USER,PASS,DB) ;
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $name = $_POST['name'];
 $sector = $_POST['sector'];
 $score = $_POST['score'];
       $sqla= "SELECT COUNT(name) FROM Company ";
       $resulta= mysqli_fetch_array(mysqli_query($con,$sqla));
       $sqlc = "SELECT COUNT(name) FROM Company where (sector='$sector')";
       $resultc= mysqli_fetch_array(mysqli_query($con,$sqlc));
           if($resultc[0]< intval(0.1*$resulta[0]))
              {
             $sqld="SELECT * FROM Company WHERE (name='$name' AND sector='$sector')";
              $result =  mysqli_fetch_array(mysqli_query($con,$sqld));
             if(isset($result))
               {
                echo  "Company already exists";
                 }
         else
         {
              $sql = "INSERT INTO Company (name,sector,score) VALUES ('$name','$sector','$score')";
             if(mysqli_query($con,$sql))
                {
                echo "Added";
               }
         }
            }
            else{
              echo "Sector count is  ";
              echo "$resultc[0]";
              echo "  Exceeded maximum threshold";
                }
 }else{
echo 'error';
}
 
?>
<?php



include("connection.php");

$json = $_POST['json'];
$obj = json_decode($json, true);
$resume_id = $obj["resume_id"];


mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from deliver_resume_exp where deliver_resume_id ='$resume_id'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"]; 
$flag="notfull";

if($count>=3){
     $flag="full";   
}
    $sql = "select * from deliver_resume_exp where deliver_resume_id='$resume_id'";
    $query = mysql_query($sql);
   
	while( $result = mysql_fetch_assoc($query)){ 
    $array[] = array_merge($result);
	}
	
                 // 	'aspassenger'=>$result["aspassenger"],'asdriverrate'=>$result["asdriverrate"],'job'=>$result["job"]);

    echo json_encode($array);

?>
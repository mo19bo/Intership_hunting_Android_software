<?php



include("connection.php");
header("Content-type: text/html; charset:utf-8");


$json = $_POST['json'];
$obj = json_decode($json, true);
//$resume_id = $obj["resume_id"];

//$resume_id=2;
    
	$sql = "select * from sxs_place ";
	
    $query = mysql_query($sql);
     
	 mysql_query("SET NAMES 'utf-8'");
   
	while( $result = mysql_fetch_assoc($query)){ 
    $array[] = array_merge($result);
	}
	              // 	'aspassenger'=>$result["aspassenger"],'asdriverrate'=>$result["asdriverrate"],'job'=>$result["job"]);

    echo json_encode($array);

?>
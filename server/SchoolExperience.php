<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $schooltag= $obj["schooltag"];
 $contentString = $obj["contentString"];
 
 
 $resume_id=$obj["resume_id"];

  // $schooltag="111";
 //$contentString="111";
 // $resume_id=2;
 mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from deliver_resume_exp where deliver_resume_id ='$resume_id'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"]; 


if($count >4){
   $array = array('result'=>'failed');
	 
}else if($count <4){
	$sql = "insert into deliver_resume_exp
	(deliver_resume_id,exp_type, exp_info) 
	values('$resume_id','$schooltag', '$contentString')";
	 mysql_query($sql);
  
	 
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
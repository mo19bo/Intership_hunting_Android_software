<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $schooltag= $obj["schooltag"];
 $contentString = $obj["contentString"];
 
 
 $id=$obj["id"];
//$id=6;
 // $schooltag="111x";
//$contentString="111x";
 // $resume_id=2;
 mysql_query("SET NAMES UTF8");

	$sql = "update deliver_resume_exp set
	exp_type='$schooltag', exp_info='$contentString'
	where sid='$id'";
	 mysql_query($sql);
	  $result1 = mysql_query($sql) or die(mysql_error());
   
	   
if(	 $result1  ){
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
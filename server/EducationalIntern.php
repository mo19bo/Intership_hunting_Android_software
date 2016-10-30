<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $exceptedCity= $obj["exceptedCity"];
 $dayweek = $obj["dayweek"];
 $worktime=$obj["worktime"];
 $exceptedjob=$obj["exceptedjob"];
 $exceptedearings=$obj["exceptedearings"];
  $exceptedworktime=$obj["exceptedworktime"];
  $exceptedInternType=$obj["exceptedInternType"];
 $user_id=$obj["user_id"];

// $exceptedCity="exceptedCity";
 // $dayweek = 1;
  //$worktime=1;
 // $exceptedjob="exceptedjob";
 // $exceptedearings=10000;
 //  $exceptedworktime="exceptedworktime";
 //  $exceptedInternType="exceptedInternType";
// $user_id=1;




	$sql = "update resume set 
	city='$exceptedCity',dayperweek='$dayweek', 
	date_length='$worktime',job='$exceptedjob',
	salary='$exceptedearings',report_time='$exceptedworktime',
	job_category='$exceptedInternType'
	where user_id= '$user_id'";
	 $result = mysql_query($sql) or die(mysql_error());
     
if($result)	{ 
	 
    $array = array('result'=>'success');
}
else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
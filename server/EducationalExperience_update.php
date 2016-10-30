<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $eduschool= $obj["eduschool"];
 $edudegree = $obj["edudegree"];
 $edumajor=$obj["edumajor"];
 $edustart=$obj["edustart"];
 $eduend=$obj["eduend"];
 $id=$obj["id"];
 //$id=16;
 //$eduschool= "1212313123";
 //$edudegree = "edudegree";
 //$edumajor="edumajor";
 //$edustart=1992;
 //$eduend=1994;
  //$resume_id=2;

   mysql_query("SET NAMES UTF8");
	$sql = "update deliver_resume_edu set
	school='$eduschool', degree='$edudegree',
	major='$edumajor',begin_year='$edustart',
	end_year='$eduend'
	where sid='$id'";
	 mysql_query($sql);
	  $result1 = mysql_query($sql) or die(mysql_error());
   
if(	 $result1 ){
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
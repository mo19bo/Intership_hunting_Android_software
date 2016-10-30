<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
$json = $_POST['json'];
 $obj = json_decode($json, true);
 $InternComName= $obj["InternComName"];
 $InternOcuppation = $obj["InternOcuppation"];
 $Internstartyear=$obj["Internstartyear"];
 $Internendyear=$obj["Internendyear"];
 $Internstartmonth=$obj["Internstartmonth"];
  $Internendmonth=$obj["Internendmonth"];
 
 $id=$obj["id"];
 //$id=16;
 
  // $eduschool= "eduschool";
 // $edudegree = "edudegree";
 // $edumajor="edumajor";
 // $edustart=1992;
 // $eduend=1994;
 // $resume_id=2;
 mysql_query("SET NAMES UTF8");

	$sql = "update deliver_resume_practice set
	name='$InternComName', play_role='$InternOcuppation',
	begin_year='$Internstartyear',begin_month='$Internstartmonth',
	end_year='$Internendyear',end_month='$Internendmonth'
	where sid='$id'";
	 mysql_query($sql);
	  $result1 = mysql_query($sql) or die(mysql_error());
   
if(	 $result1){
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
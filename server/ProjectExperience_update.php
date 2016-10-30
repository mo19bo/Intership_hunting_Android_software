<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
$json = $_POST['json'];
 $obj = json_decode($json, true);
 $projectname= $obj["projectname"];
 $playrole = $obj["playrole"];
 $project_startyear=$obj["project_startyear"];
 $project_endyear=$obj["project_endyear"];
 $project_startmonth=$obj["project_startmonth"];
  $project_endmonth=$obj["project_endmonth"];
  $project_description=$obj["project_description"];
 
 mysql_query("SET NAMES UTF8");
 $id=$obj["id"];
 //$id=16;
 
  // $eduschool= "eduschool";
 // $edudegree = "edudegree";
 // $edumajor="edumajor";
 // $edustart=1992;
 // $eduend=1994;
 // $resume_id=2;
 mysql_query("SET NAMES UTF8");

	$sql = "update deliver_resume_project set
	name='$projectname', play_role='$playrole',
	begin_year='$project_startyear',begin_month='$project_startmonth',
	end_year='$project_endyear',end_month='$project_endmonth'
	,description='$project_description'
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
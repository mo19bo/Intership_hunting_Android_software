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
  
 $resume_id=$obj["resume_id"];
mysql_query("SET NAMES UTF8");
  // $eduschool= "eduschool";
 // $edudegree = "edudegree";
 // $edumajor="edumajor";
 // $edustart=1992;
 // $eduend=1994;
 // $resume_id=2;
 
$checksql = "select count(*) as sum from deliver_resume_project where deliver_resume_id ='$resume_id'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"]; 


if($count >3){
   $array = array('result'=>'failed');
	 
}else if($count <3){
	$sql = "insert into deliver_resume_project
	(deliver_resume_id,name, play_role,begin_year,begin_month,end_year,end_month,description) 
	values('$resume_id','$projectname', '$playrole','$project_startyear','$project_startmonth',' $project_endyear',' $project_endmonth',' $project_description')";
	 mysql_query($sql);

	 
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
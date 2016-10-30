<?php



include("connection.php");

$json = $_POST['json'];
$obj = json_decode($json, true);
$resume_id = $obj["resume_id"];

//$resume_id=2609;
   
	$sql = "select * from resume where sid='$resume_id'";
    $query = mysql_query($sql);
   
	
	if($result = mysql_fetch_assoc($query)){
    $array = array('result'=>'success','city'=>$result['city'],'intern_type'=>$result['intern_type'],
	'job'=>$result['job'],'job_category'=>$result['job_category'],'salary'=>$result['salary'],
	'date_length'=>$result['date_length'],'dayperweek'=>$result['dayperweek'],'report_time'=>$result['report_time']);
	}
	
                 // 	'aspassenger'=>$result["aspassenger"],'asdriverrate'=>$result["asdriverrate"],'job'=>$result["job"]);

    echo json_encode($array);

?>
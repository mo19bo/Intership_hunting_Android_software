<?php



include("connection.php");
$json = '{"password":"","username":""}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$user_id=$obj['user_id'];

//$user_id=14;


$checksql = "select count(*) as sum from users where sid='$user_id'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];

if($count == 0){

   $array = array('result'=>'failed');
   
   
}else{
    
    $sql = "select * from resume where user_id='$user_id'";
    $query = mysql_query($sql);
    $result1 = mysql_fetch_assoc($query);
	
    $sql1 = "select * from users where sid='$user_id'";
    $query = mysql_query($sql1);
    $result = mysql_fetch_assoc($query);

	
    $array = array('result'=>'success',
	'username'=>$result["username"],
	'realname'=>$result["real_name"],
	'telphone'=>$result["telphone"],
	'school'=>$result["school"],
	'major'=>$result["major"],
	'degree'=>$result["degree"],
	'graduate_year'=>$result["graduate_year"],
	'gender'=>$result["gender"],
	'photo_url'=>$result["photo_url"],
	'useremail'=>$result["email"],
	'user_id'=>$result["sid"],
	'resume_id'=>$result1["sid"],
	'user_img'=>$result["photo_url"]);
                 // 	'aspassenger'=>$result["aspassenger"],'asdriverrate'=>$result["asdriverrate"],'job'=>$result["job"]);
}
echo json_encode($array);

?>
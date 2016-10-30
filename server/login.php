<?php



include("connection.php");
$json = '{"password":"","username":""}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$loginname = $obj["username"];
$password = $obj["password"];
//$loginname ="mo11";
//$password = "111";


$sql = "select * from users where username ='$loginname' or email='$loginname' ";
$query = mysql_query($sql);
$result = mysql_fetch_assoc($query);

if($password != $result["password"] or $password ==""){
 
   $array = array('result'=>'failed');
   
   
}else{
    $user_id=$result["sid"];
    $sql = "select * from resume where user_id='$user_id'";
    $query = mysql_query($sql);
    $result1 = mysql_fetch_assoc($query);
	  
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
<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$email = $obj["email"];
$psd = $obj["password"];
 //$email="2586471@qq.com";
 //$psd="123456";

$checksql = "select count(*) as sum from users where email ='$email'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"];

if($count == 1){
    $array = array('result'=>'exist');
}else if($count == 0){
    $sql = "insert into users (email,username, password,gender) values('$email','$email', '$psd',1)";
    mysql_query($sql);
	$sql = "select * from users where email='$email' ";
    $query = mysql_query($sql);
    $result = mysql_fetch_assoc($query);
	$user_id=$result["sid"];
	$sql = "insert into resume (user_id,username,gender,email) values('$user_id','$email', 1,'$email')";
	mysql_query($sql);
	
    $array = array('result'=>'success',
	'usersid'=>$result["sid"]);
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $username= $obj["username"];
 $usersex = $obj["usersex"];
 $useremail=$obj["useremail"];
 $userschool=$obj["userschool"];
 $usergraduate=$obj["usergraduate"];
 $realname=$obj["realname"];
  $telphone=$obj["usertel"];
$motified=$obj['flag'];
$userdegree=$obj['userdegree'];
$usermajor=$obj['usermajor'];
$user_id=$obj['userid'];
$user_img=$obj['user_photourl'];
 // $username= "mo11";
// $usersex = "1";
// $telphone="18681273519";
// $useremail="1046219417@qq.com";
// $userschool="四川小学";
// $usergraduate="2011";
// $realname="哈哈";
// $user_id=13;
// $motified=0;
$checksql = "select count(*) as sum from users where username ='$username'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"]; 

if($motified==0){
$count =0;
}

if($count ==1){
   $array = array('result'=>'exist');
	 
}else if($count == 0){
    
   
  $sql = "update users set username='$username',email='$useremail', real_name='$realname',telphone='$telphone',
	school='$userschool',graduate_year='$usergraduate',gender='$usersex',major='$usermajor',degree='$userdegree',photo_url='$user_img' where email= '$useremail'";
    mysql_query($sql);
	$sql = "update resume set username='$username',email='$useremail', real_name='$realname',telphone='$telphone',
	school='$userschool',graduate_year='$usergraduate',gender='$usersex',major='$usermajor',degree='$userdegree' where user_id= '$user_id'";
	 mysql_query($sql);
    $array = array('result'=>'success');
}else{
    $array = array('result'=>'failed');
}
echo json_encode($array);
?>
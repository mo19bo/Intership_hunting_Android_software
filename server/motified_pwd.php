<?php


include("connection.php");
//$json = '{"username":"test1", "password":"test", "phonenumber":"18328588447","sex":0,"introduction":"","driverlicense":"","idcard":"","isdriver":0,"iscustomer":0}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$user_id = $obj["user_id"];
$psd = $obj["password"];
$oldpsd = $obj["oldpassword"];
 //$user_id ="13";
 //$psd="12345";

$checksql = "select count(*) as sum from users where sid ='$user_id' and password='$oldpsd'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_array($checkquery);
$count = $check["sum"];

if($count > 1){
    $array = array('result'=>'failed');
	
}
else if($count==0){
 $array = array('result'=>'exists');

}else {
        
    $sql = "update users set users.password='$psd' where sid='$user_id'";
    mysql_query($sql);
	
    
	
    $array = array('result'=>'success');
}
echo json_encode($array);
?>
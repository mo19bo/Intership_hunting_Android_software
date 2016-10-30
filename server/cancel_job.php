


<?php

//	关注公司phpc（用于取消对公司的关注)
//	sid:公司id	user_id:用户id	

include("connection.php");
//$json = '{"sid":"","user_id":""}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$type = "intern";


$sid = $obj["sid"];
$user_id = $obj["user_id"];

//	测试数据
//  $sid = 2;
//  $user_id = 12;

//	更改编码格式
mysql_query("SET NAMES UTF8");

$sql = "select count(*) as sum from red_heart 
where user_id ='$user_id' and stype='$type' and stype_id = '$sid'";
$query = mysql_query($sql);
$result = mysql_fetch_assoc($query);
$count = $result["sum"];

if($count == 0){

   $array = array('result'=>'取消');
   
   
}else if($count == 1){
   
    $sql = "delete from red_heart where stype_id = '$sid' and user_id ='$user_id' and stype='$type'";
 

   if( mysql_query($sql)){
    $array = array('result'=>'success');     
   }
   else{
    $array = array('result'=>'failed');     
   }
            
}
echo json_encode($array, JSON_UNESCAPED_UNICODE);

?>
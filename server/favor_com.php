


<?php

//	关注公司phpb（用于关注公司)
//	sid:公司id	user_id:用户id	

include("connection.php");
//$json = '{"sid":"","user_id":""}';
$json = $_POST['json'];
$obj = json_decode($json, true);
$type = "2";


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

if($count == 1){

   $array = array('result'=>'exists');
   
   
}else if($count == 0){
   
    $sql = "insert into red_heart (sid, user_id, stype, stype_id, memo) values('', '$user_id', '$type', '$sid', '')";
 
  if( mysql_query($sql)){
     $array = array('result'=>'success');   
  
  }else{
   $array = array('result'=>'failed');   
  }
            
}
echo json_encode($array);

?>
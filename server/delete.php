


<?php

//	关注公司phpc（用于取消对公司的关注)
//	sid:公司id	user_id:用户id	

include("connection.php");
//$json = '{"sid":"","user_id":""}';
$json = $_POST['json'];
$obj = json_decode($json, true);



$id = $obj["id"];
$flag = $obj["flag"];
$sql1="";
$sql2="";
$sql3="";
//	测试数据
 //$id = 4;
// $flag = 3;

//	更改编码格式
mysql_query("SET NAMES UTF8");
   switch($flag){
   case 1:
   $sql1 = "select count(*) as sum from deliver_resume_edu where sid ='$id'";
   $sql2 = "delete from deliver_resume_edu where sid = '$id'";
    $sql3 = "delete from resume_edu where sid = '$id'";
   break;
   case 2:
    $sql1 = "select count(*) as sum from deliver_resume_practice where sid ='$id'";
   $sql2 = "delete from deliver_resume_practice where sid = '$id'";
    $sql3 = "delete from resume_practice where sid = '$id'";
   
   
   break;
   case 3:
     $sql1 = "select count(*) as sum from deliver_resume_exp where sid ='$id'";
   $sql2 = "delete from deliver_resume_exp where sid = '$id'";
    $sql3 = "delete from resume_exp where sid = '$id'";
   break;
   case 4:
     $sql1 = "select count(*) as sum from deliver_resume_project where sid ='$id'";
   $sql2 = "delete from deliver_resume_project where sid = '$id'";
    $sql3 = "delete from resume_project where sid = '$id'";
   break;
   default:
   break;

   }

$query = mysql_query($sql1);
$result = mysql_fetch_assoc($query);
$count = $result["sum"];

if($count == 0){

   $array = array('result'=>'取消');
   
   
}else if($count == 1){
   
  
  

   if(mysql_query( $sql2)&&mysql_query( $sql3) ){
    
    $array = array('result'=>'success');     
   }
   else{
   
    $array = array('result'=>'failed');     
   }
            
}
echo json_encode($array, JSON_UNESCAPED_UNICODE);

?>
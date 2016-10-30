<?php
include("connection.php");

$json = $_POST['json'];
$obj = json_decode($json, true);

$id=$obj["id"];

$stype="1";

$checksql = "select count(*) as sum from intern";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];
//$flag= 3;
//$id=31778·;
//$com_id=2;

mysql_query("SET NAMES UTF8");
if(false){

   $array = array('result'=>'failed');
   
   
}
else{


       $sql = "select  count(*) as sum from red_heart, intern where red_heart.user_id='$id' 
	  and red_heart.stype_id=intern.sid  and red_heart.stype='$stype'";
       $checkquery = mysql_query($sql);
	   $check = mysql_fetch_assoc($checkquery);
	    $count = $check["sum"];
		//	判断查询结果是否为空
			if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
			 $sqla = "select red_heart.sid as red_heart_sid
			 ,intern.*, company.logo_url as logo from red_heart, intern, company where red_heart.user_id='$id' 
	            and red_heart.stype_id=intern.sid and red_heart.stype='$stype' and company.sid=intern.com_id";
			$querya = mysql_query($sqla);
					while ($result = mysql_fetch_assoc($querya)){
						$arraya[] = array_merge($result);}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);
			
			
			
			}
}

		echo json_encode($array);

?>
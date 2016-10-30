<?php



include("connection.php");

$json = $_POST['json'];
$obj = json_decode($json, true);
$flag= $obj["flag"];
$id=$obj["id"];

mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from company";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];
//$flag= 2;
//$id=11258;
//$user_id=14;
$stype="2";

if(false){

   $array = array('result'=>'failed');
   
   
}
else{
     switch($flag){
	  
	 case 1:
	  $sql = "select  count(*) as sum from red_heart, company where red_heart.user_id='$id' 
	  and red_heart.stype_id=company.sid  and red_heart.stype='$stype'";
       $checkquery = mysql_query($sql);
	   $check = mysql_fetch_assoc($checkquery);
	    $count = $check["sum"];
		//	判断查询结果是否为空
			if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
			 $sqla = "select red_heart.sid as red_heart_sid
			 ,company.* from red_heart, company where red_heart.user_id='$id' 
	            and red_heart.stype_id=company.sid and red_heart.stype='$stype'";
			$querya = mysql_query($sqla);
					while ($result = mysql_fetch_assoc($querya)){
						$arraya[] = array_merge($result);}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);
			
			
			
			}
	   break;
	   case 2:
	   $sql = "select  count(*) as sum from company where  sid='$id'";
       $checkquery = mysql_query($sql);
	   $check = mysql_fetch_assoc($checkquery);
	    $count = $check["sum"];
		//	判断查询结果是否为空
			if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
			 $sqla = "select * from company  where  sid='$id'";
			$querya = mysql_query($sqla);
	     	
					

				while ($result = mysql_fetch_assoc($querya)){
				
						$arraya[] = array_merge($result);
						}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);
			
			
			}
	   
	   
	   break;
	    default:
	     break;
	 
	 
	 }
   
   
                
}
echo json_encode($array);

?>
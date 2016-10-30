<?php
include("connection.php");

$json = $_POST['json'];
$obj = json_decode($json, true);

$id=$obj["id"];

//$stype="intern";


//$flag= 3;
//$id=2;
//$id=730;

mysql_query("SET NAMES UTF8");
if(false){

   $array = array('result'=>'failed');
   
   
}
else{
 
			
			 $sqla = "select intern.*,company.logo_url as logo from intern ,company where intern.com_id='$id' and intern.is_offline='0' and  intern.is_delete='0' and company.sid=intern.com_id";
			  $querya = mysql_query($sqla);
		
					while ($result = mysql_fetch_assoc($querya)){
					  
					
						$arraya[] = array_merge($result);
						
						
						}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);
			
			
			
			}


echo json_encode($array);

?>
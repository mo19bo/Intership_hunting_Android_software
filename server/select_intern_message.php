<?php
include("connection.php");
 mysql_query("SET NAMES UTF8");
$json = $_POST['json'];
$obj = json_decode($json, true);

$id=$obj["id"];
//$stype="intern";


//$flag= 3;
//$id=267;
//$com_id=2;

         

      $sql = "select  count(*) as sum from intern where sid='$id'";
       $checkquery = mysql_query($sql);
	   $check = mysql_fetch_assoc($checkquery);
	    $count = $check["sum"];
		//	判断查询结果是否为空
			if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
			 $sqla = "select intern.*,company.logo_url as logo from intern ,company where intern.sid='$id' and intern.com_id=company.sid";
			  $querya = mysql_query($sqla);
					while ($result = mysql_fetch_assoc($querya)){
					   $information=array('information'=> br2nl($result["info"]));
						$arraya[] = array_merge($result,$information);
						}
	               	//给结果加上success标示
			         $array = array('result'=>'success','data'=>$arraya);
                      }
           echo json_encode($array);
function br2nl($text){
	$text = str_replace('&nbsp;','',$text);
	$text = strip_tags($text,"<br>");
	return  preg_replace('/<br\\s*?\/??>/i',chr(13),$text);
	
}
?>
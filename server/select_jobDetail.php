<?php

// 职位详情页的查询（只查公司的logo_url 和 公司的一句话描述）,其他的通过传值传递过来
//	home_page用于传递到下一个界面（公司详情页）

include("connection.php");

	格式, com_id:""
 $json = '{"com_id":""}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
$com_id = $obj["com_id"];

//	测试数据
//$com_id = "1";

//	更改编码格式
mysql_query("SET NAMES UTF8");

$checksql = "select count(*) as sum from company where sid = '$com_id'";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];

if($count == 0){

   $array = array('result'=>'failed');
   
   
}else{
	$sql = "select * from company where sid ='$com_id'";
	$query = mysql_query($sql);
	$result = mysql_fetch_assoc($query);
	$array = array('result'=>'success',
	'logo_url'=>$result["logo_url"],
	'desc'=>$result["description"],'home_page'=>$result["home_page"]);
}
echo json_encode($array);

?>
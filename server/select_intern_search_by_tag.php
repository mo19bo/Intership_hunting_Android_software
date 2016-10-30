<?php



include("connection.php");

//	格式, 关键词:""
 
 $json = $_POST['json'];
 $obj = json_decode($json, JSON_UNESCAPED_UNICODE);
$city = $obj["city"];
$type = $obj["type"];
$dayperweek=$obj["dayperweek"];
$minsalary=$obj["minsalary"];
$maxsalary=$obj["maxsalary"];
//	测试数据
//$keyword = "大客户代表2";
mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from intern";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];

if($count == 0){

   $array = array('result'=>'failed');
   
   
}else{
	//	判断结果是否为空
	if($city==""){
	$city="intern.city ";
	}
	if($type==""){
	$type="intern.intern_type ";
	}
	if($dayperweek==""){
	$dayperweek="intern.dayperweek";
	}
	if($minsalary==0){
	$minsalary="intern.minsalary";
	}
	if($maxsalary==0){
	$minsalary="intern.maxsalary";
	}
    $sql = "select * from intern where 1
	and 1
	and 1
	and 1
	and 1
	and is_offline='0'
	order by click_num desc  ";
    $query = mysql_query($sql);    
     while ($result = mysql_fetch_assoc($query)) {
              $arraya[] = array_merge($result, $check);
      }
    //	返回结果形式为 "result":" ", "data":" "
    $array = array('result'=>'success','data'=>$arraya);

}
echo json_encode($array);

?>
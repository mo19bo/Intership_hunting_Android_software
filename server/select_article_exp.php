<?php
include("connection.php");

//	格式, 关键词:""

$json = $_POST['json'];
$obj = json_decode($json, JSON_UNESCAPED_UNICODE);
$type=$obj["type"];
$index=$obj["index"];
$pagesize=10;
//$keyword = "大客户代表2";
mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from article";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];
//$index=0;//	测试数据
//$type=3;


if($count == 0){

   $array = array('result'=>'failed');
   
   
}else{
	
	$start=$index*$pagesize;

	
	
    $sql = "select article.*,article_category.title as label from article, article_category 
	where article.category_id=article_category.sid and article.category_id='$type'
	order by article.create_time asc limit $start,$pagesize";
    $query = mysql_query($sql);    
   while ($result = mysql_fetch_assoc($query)) {
       $arraya[] = array_merge($result);
    }
    //	返回结果形式为 "result":" ", "data":" "
    $array = array('result'=>'success','data'=>$arraya);
}

echo json_encode($array);

?>
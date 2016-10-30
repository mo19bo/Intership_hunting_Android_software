<?php



include("connection.php");
include("parse_info_html.php");
//	格式, 关键词:""

$json = $_POST['json'];
$obj = json_decode($json, JSON_UNESCAPED_UNICODE);
$keyword = $obj["keyword"];
$index=$obj["index"];
$pagesize=10;
//$keyword = "大客户代表2";
mysql_query("SET NAMES UTF8");
$checksql = "select count(*) as sum from intern";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];
//$index=0;//	测试数据



if($count == 0){

   $array = array('result'=>'failed');
   
   
}else{
	//	判断结果是否为空
	$checksqla = "select count(*) as suma from intern where name like '%$keyword%' or com_name like '%$keyword%' 
    or city like '%$keyword%' ";
    $checkquerya = mysql_query($checksqla);
    $checka = mysql_fetch_assoc($checkquerya);
    $num = $checka["suma"];
	//	结果为空，返回
    if($num == 0){
    	$array = array('result'=>'failed');
    }
    //	结果不为空,返回
    else{
	$start=$index*$pagesize;

	
	
    $sql = "select intern.* ,company.logo_url as logo from intern,company where (intern.name like '%$keyword%' 
    or intern.com_name like '%$keyword%' or intern.city like '%$keyword%') and intern.is_offline='0' and intern.is_delete='0' and company.sid=intern.com_id
	order by click_num desc limit $start,$pagesize";
	
    $query = mysql_query($sql);    
     while ($result = mysql_fetch_assoc($query)) {
        
        $information=array('information'=> br2nl($result["info"]));
		
        $arraya[] = array_merge($result, $check, $information);
    }
    //	返回结果形式为 "result":" ", "data":" "
    $array = array('result'=>'success','data'=>$arraya);
}
}
echo json_encode($array);

	
	function br2nl($text){
	$text = str_replace('&nbsp;','',$text);
	$text = strip_tags($text,"<br>");
	return  preg_replace('/<br\\s*?\/??>/i',chr(13),$text);
	
}
?>
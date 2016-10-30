

<?php

//	广告查询
//	需要查询到的有图片链接，以及广告的链接地址
//  [img_url:],[target:]


include("connection.php");

 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $num  = 3;

$checksql = "select count(*) as sum from ad";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];




//	更改编码格式
mysql_query("SET NAMES UTF8");

if($count < 1){

   $array = array('result'=>'failed');
   
   
}else{
	
	//	按照点击数排序并且只取前三条
    $sql = "select * from ad where is_use = 1 order by 'click_num' desc limit 0,3 ";
    $query = mysql_query($sql);
   while ($result = mysql_fetch_assoc($query)) {
        $array[] = array_merge($result, $check);
		
    }
    $array = array('result'=>'success','data'=>$array);
                
}
echo json_encode($array, JSON_UNESCAPED_UNICODE);

?>
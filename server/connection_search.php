
<?php



include("connection.php");

//	更改编码格式
mysql_query("SET NAMES UTF8");

//	格式, 关键词:""(实习天数需要传数字)
//	关键词1:“职业类别” 关键词2:“每周实习天数” 关键词3:“地点” 关键词4:“日薪范围”, 位移值
// $json = '{"keyword1":"","keyword2":"","keyword3":"","keyword4":"","index":}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
 $index=$obj["index"];
 $keyword1 = $obj["keyword1"];
 $keyword2 = $obj["keyword2"];
 $keyword3 = $obj["keyword3"];
 $keyword4 = $obj["keyword4"];
 $pagesize=10;
//  $index=0;//	测试数据
//	测试数据
 //$keyword1 = "";
// $keyword2 = "";
// $keyword3 = "成都";
 // $keyword4 = 10;
mysql_query("SET NAMES UTF8");

$checksql = "select count(*) as sum from intern";
$checkquery = mysql_query($checksql);
$check = mysql_fetch_assoc($checkquery);
$count = $check["sum"];

if($count == 0){

   $array = array('result'=>'failed');
   
   
}
else{ 
	$salary_min = 0;
	$salary_max = 999;

switch($keyword4){ 
	case 0:
		$salary_min = 0;
		$salary_max = 50;
	break;
	case 1:
		$salary_min = 50;
		$salary_max = 100;
	break;
	case 2:
		$salary_min = 100;
		$salary_max = 150;
	break;
	case 3:
		$salary_min = 150;
		$salary_max = 200;
	break;
	case 4:
		$salary_min = 200;
		$salary_max = 300;
	break;
	case 5:
		$salary_min = 300;
		$salary_max = 9999;
	break;
	default:
		$salary_min = 0;
		$salary_max = 9999;
	break;
}

	//	判断结果是否为空
		$checksqla = "select count(*) as suma from intern where minsalary >= '$salary_min' and
		 maxsalary <= '$salary_max' and job like '%$keyword1%' and dayperweek like '%$keyword2%' 
    		and city like '%$keyword3%'";
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

    $sql = "select intern.*,intern.info as info,company.logo_url as logo from intern, company where intern.com_id = company.sid and intern.job like'%$keyword1%' 
    and intern.dayperweek like '%$keyword2%' and intern.city like'%$keyword3%' and 
    intern.minsalary >= '$salary_min' and intern.maxsalary <= '$salary_max' and intern.is_offline='0' and  intern.is_delete='0'order by click_num desc limit $start,$pagesize";
    $query = mysql_query($sql);    
   while ($result = mysql_fetch_assoc($query)) {
        $arraya[] = array_merge($result, $check);

    }
    //	返回结果形式为 "result":" ", "data":" "
    $array = array('result'=>'success','data'=>$arraya);
}
}
//	直接解析成数据
echo json_encode($array);

?>
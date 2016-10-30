

<?php

//	消息查询
//	需要查询到的有job的名称, 所属公司的名称, 状态, 投递时间
//  需要post进来的有user_name, search_style(查询的状态类型)
//	解析的时候直接解析data中的［name:］[com_name][status][deliver_time]

include("connection.php");

mysql_query("SET NAMES UTF8");

//	格式, 用户名:"", 状态类型_标记:""
//	类型默认有 全部,被查看，通知面试，不合适
//	全部为6, 投递成功为1, 被查看为2, 通过初刷为3, 通知面试为4, 不合适为5, 
 //$json = '{"user_name":"","status_tag":""}';
 $json = $_POST['json'];
 $obj = json_decode($json, true);
$user_id  = $obj["user_id"];
 $status_tag = $obj["flag"];

// 测试
//$user_id = "29150";
//$status_tag = 5;

//	查询全部
if($status_tag == 6){
	$sql_test = "select count(*) as sum from resume_deliver where is_delete = 0 and user_id = '$user_id'";
	$checkquery = mysql_query($sql_test);
	$check = mysql_fetch_assoc($checkquery);
	$count = $check["sum"];
			//判断查询结果是否为空
		if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
				$sqla = "select resume_deliver.*, intern.name as job from resume_deliver,intern where resume_deliver.intern_id=intern.sid and resume_deliver.user_id = '$user_id'  and resume_deliver.is_delete = 0";
				$querya = mysql_query($sqla);
					while ($result = mysql_fetch_assoc($querya)){
					  	   $arraya[] = array_merge($result);
						
						}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);
				 }
					}
//	查询非全部
else{
	$sql_test = "select count(*) as sum from resume_deliver where status_tag = '$status_tag' and user_id ='$user_id' and is_delete = 0";
	$checkquery = mysql_query($sql_test);
	$check = mysql_fetch_assoc($checkquery);
	$count = $check["sum"];
		//	判断查询结果是否为空
			if($count < 1){
				$array = array('result'=>'failed');
			}
			else{
				$sqla = "select resume_deliver.* , intern.name as job from resume_deliver,intern where resume_deliver.intern_id=intern.sid and resume_deliver.user_id ='$user_id'  and resume_deliver.is_delete = 0 and resume_deliver.status_tag = '$status_tag'";
				$querya = mysql_query($sqla);
					while ($result = mysql_fetch_assoc($querya)){
						$arraya[] = array_merge($result);}
		//	给结果加上success标示
			 $array = array('result'=>'success','data'=>$arraya);			
				}
		
	}
    
                

echo json_encode($array);

?>
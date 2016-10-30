

<?php

//	传递参数 用户id, 简历id,用户名, 职位id, 公司id, 公司名称, 用户邮件, 简历投递邮件, 当前系统时间

include("connection.php");

mysql_query("SET NAMES UTF8");

//	格式, 用户名:"", 状态类型_标记:""
//	类型默认有 全部,被查看，通知面试，不合适
//	全部为6, 投递成功为1, 被查看为2, 通过初刷为3, 通知面试为4, 不合适为5, 
 //$json = '{"user_name":"","status_tag":""}';
 $json = $_POST['json'];
 $obj = json_decode($json, JSON_UNESCAPED_UNICODE);
 $user_id  = $obj["user_id"];
 $resume_id = $obj["resume_id"];
 $user_name= $obj["user_name"];
 $intern_id = $obj["intern_id"];
 $com_id = $obj["com_id"];
 $com_name = $obj["com_name"];
 $useremail = $obj["useremail"];
 $deliveremail=$obj["deliver"];
 $deliverurl="http://www.shixiseng.com/internuser/preview/sid/".$resume_id."html";
 $time = time();
  //$deliveremail="";
 //$user_id  = 14;
 //$resume_id =2;
 //$user_name= 1;
 //$intern_id = 11;
 //$com_id = 1;
 //$com_name = 1;
 //$useremail ="1046219417@qq.com";
// $deliveremail="1046219417@qq.com";
// $deliverurl=$obj['deliver_url'];
 //$status = "投递成功";
 //$status_tag = 1;
 //$is_sub = 1;
 $max_send_num=1;
 
   $sql_test = "select count(*) as sum from resume_deliver where resume_id = '$resume_id' and intern_id = '$intern_id'";
	$checkquery = mysql_query($sql_test);
	$check = mysql_fetch_assoc($checkquery);
	$count = $check["sum"];
			//判断查询结果是否为空
			
	if($count <$max_send_num){
    $results= send_mail( $useremail,$deliveremail); 
	
    
	$obj1=json_decode($results,true);		
	if($obj1['message']=="success"){
	// 插入简历投递表
	  $sql = "insert into resume_deliver
	(resume_id, user_id, user_name, intern_id, com_id, com_name, status, status_tag, is_sub,deliver_time) 
	values('$resume_id','$user_id', '$user_name' ,'$intern_id', '$com_id', '$com_name', '$status', '$status_tag', '$is_sub', '$time')";
	 mysql_query($sql);
	 // 复制给公司的教育背景
	 $sql = "insert into resume_edu (resume_id,school,degree,major,begin_year,end_year) select deliver_resume_id,school,degree,major,begin_year,end_year  from deliver_resume_edu where deliver_resume_id = $resume_id";
     mysql_query($sql);
     // 复制给公司的获奖情况
     $sql = "insert into resume_exp (resume_id,exp_type,exp_info) select deliver_resume_id,exp_type,exp_info from deliver_resume_exp where deliver_resume_id = $resume_id";           
     mysql_query($sql);
     // 复制给公司的预期实习
      $sql = "insert into resume_hope (resume_id,place,stype,resume_hope.job,pay) select sid,city,intern_type,deliver_resume.job,salary from deliver_resume where deliver_id = $resume_id";           
     mysql_query($sql); 
     // 复制给公司的实习经历
     $sql = "insert into resume_practice (resume_id,name,play_role,begin_year,begin_month, end_year, end_month,description) select deliver_resume_id,name,play_role,begin_year,begin_month, end_year, end_month,description from deliver_resume_practice where deliver_resume_id = $resume_id";           
     mysql_query($sql);  
     //  复制给公司的作品展示
     $sql = "insert into resume_product (resume_id,product_url,product_info) select deliver_resume_id,product_url,product_info from deliver_resume_product where deliver_resume_id = $resume_id";           
     mysql_query($sql); 
     //  复制给公司的项目经历
    $sql = "insert into resume_project (resume_id,name,play_role,begin_year,begin_month,end_year,end_month,description) select deliver_resume_id,name,play_role,begin_year,begin_month,end_year,end_month,description from deliver_resume_project where deliver_resume_id = $resume_id";           
     mysql_query($sql); 
     //  复制给公司的掌握技能
    $sql = "insert into resume_skill (resume_id,name,level,use_time) select deliver_resume_id,name,level,use_time from deliver_resume_skill where deliver_resume_id = $resume_id";           
     mysql_query($sql); 
                
                $array = array('result'=>'success');	 
	
	}else{
	  
	  $array = array('result'=>'failed');
	}
}		

else{
				
				
				$array = array('result'=>'exists');
					
					
					}
    	 function send_mail($useremail1,$deliveremail) {
      $url = 'http://sendcloud.sohu.com/webapi/mail.send.json';
      //不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
      $param = array('api_user' => 'mo19bo_test_ihJtmS',
              'api_key' => '1n8e2NsJsjeFEUAi',
              'from' => $useremail1,
              'fromname' => 'SendCloud测试邮件',
              'to' => $deliveremail,
              'subject' => '来自SendCloud的第一封邮件！',
              'html' => '<p>来自SendCloud的第一封邮件！</p>
');

      $options = array('http' => array('method'  => 'POST',	'header' => "Content-type: application/x-www-form-urlencoded ",'content' => http_build_query($param)));
      $context  = stream_context_create($options);
      $result = file_get_contents($url, false, $context);

      return $result;
	  
    }
     
echo json_encode($array);

?>
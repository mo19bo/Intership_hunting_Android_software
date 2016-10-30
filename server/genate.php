
<?php

	include("connection.php");
	mysql_query("SET NAMES UTF8");
	for($i=0;$i<3;$i++){
	$click_num=$i*100;
	$job="这是工作的标题".$i;
	$name="我是工作的公司".$i;
	$sql = "INSERT INTO `test6`.`intern` (`sid`, `com_id`, `com_name`, `user_id`, `add_time`, `update_time`, `effective_time`, `update_number`, `add_ip`, `deliver_url`, `deliver_email`, `job`, `name`, `department`, `intern_type`, `click_num`, `deliver_num`, `minsalary`, `maxsalary`, `dayperweek`, `city`, `degree`, `attraction`, `info`, `address`, `status`, `com_logo_url`, `is_offline`, `is_delete`) VALUES (NULL, '2', '$name', '', '0205', '19920126', '0', '5', '', '', '', '$job', '', '', '互联网电子行业', '$click_num', '', '50', '100', '3', '上海', '本科', '白吃白住，随意上班，没有老板', '我是自我介绍
我是自我介绍
我是自我介绍我是自我介绍
我是自我介绍我是自我介绍我是自我介绍
我是自我介绍
我是自我介绍我是自我介绍
我是自我介绍我是自我介绍
我是自我介绍
我是自我介绍
自我介绍介绍
介绍

就急急急急急急将', '北京天安门国旗杆下', '', 'http://192.168.2.119:8080/jd.png', '0', '0');";
	$query = mysql_query($sql);
	$result = mysql_fetch_assoc($query);
	}
	?>
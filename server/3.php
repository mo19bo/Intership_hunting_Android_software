<?php
//将时区设置为中国
date_default_timezone_set("PRC");

echo time();
echo date('Y-m-d h:m:s',time());
//例输出：2010-03-06 Saturday 11:51:29 AM
?>
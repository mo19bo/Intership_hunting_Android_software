<?php





$con = mysql_connect("localhost", "mo11", "1234");
mysql_query("set names 'utf8'",$con);
if (!$con)
  {
  echo ('Could not connect: ' . mysql_error());
  }

$db_selected = mysql_select_db("1234", $con);

if (!$db_selected)
  {
  echo ("Can\'t use sxs_app : " . mysql_error());
  }



?>
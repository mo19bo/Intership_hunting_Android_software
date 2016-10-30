<?php



 function DeleteHtml($str){
  $str=trim($str);//清除字符串两边的空格
  $str = preg_replace('/<br\\s*?\/??>/i',chr(13),$str);
  $str=strip_tags($str,"");//利用php自带的函数清除html格式
  $str=preg_replace("/\t/"," ",$str);//使用正则表达式匹配需要替换的内容，如空格和换行，并将替换为空
  $str=preg_replace("/\r\n/","",$str);
  $str=preg_replace("/\r/","",$str);
  $str=preg_replace("/\n/","",$str);
  $str=preg_replace("/ /","",$str);
  $str=preg_replace("/ /","",$str);//匹配html中的空格
  return trim($str);//返回字符串
 }
 ?>
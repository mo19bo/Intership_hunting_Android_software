<?php 
    echo $_FILES['file']['tmp_name'];
	echo $_SERVER['DOCUMENT_ROOT']."/1/upload/".$_FILES["file"]["name"];
¡¡¡¡move_uploaded_file($_FILES['file']['tmp_name'], $_SERVER['DOCUMENT_ROOT']."/1/upload/".$_FILES["file"]["name"]); 
?>
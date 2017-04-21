<?php
/**
 * Created by PhpStorm.
 * User: Manik
 * Date: 4/21/2017
 * Time: 9:10 PM
 */

if(@move_uploaded_file($_FILES["filUpload"]["tmp_name"],"image/".$_FILES["filUpload"]["name"]))
{
    $arr["image_name"] = $_FILES["filUpload"]["name"];
}
else
{
    $arr["StatusID"] = "0";
    $arr["Error"] = "Cannot upload file.";
}

echo json_encode($arr);
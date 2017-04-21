<?php
include_once 'dbconfig.php';
$user_id = $_POST['user_id'];
$timestamp = $_POST['timestamp'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];


$sql_query = "UPDATE  `tbl_gps_track` SET  `lat` =  '$lat',`lon` =  '$lon',`timestamp` =  '$timestamp' WHERE  `user_id` =$user_id";
$result = $conn->query($sql_query);
if ($result>0) {
  $result=array("status"=>$id);
}
$conn->close();
echo json_encode($result);
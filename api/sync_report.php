<?php
include_once 'dbconfig.php';
$id = $_POST['id'];
$visit_number = $_POST['visit_number'];
$user_id = $_POST['user_id'];
$district_id = $_POST['district_id'];
$thana_id = $_POST['thana_id'];
$zone_id = $_POST['zone_id'];
$bazar_name = $_POST['bazar_name'];
$proprietor_name = $_POST['proprietor_name'];
$proprietor_code = $_POST['proprietor_code'];
$proprietor_address= $_POST['proprietor_address'];
$proprietor_owner= $_POST['proprietor_owner'];
$proprietor_contact= $_POST['proprietor_contact'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];


$sql_query = "INSERT INTO `tbl_report` (`visit_number`, `user_id`, `district_id`, `thana_id`, `zone_id`, `bazar_name`, `proprietor_name`, `proprietor_code`, `proprietor_address`, `proprietor_owner`, `proprietor_contact`, `lat`, `lon`) VALUES('$visit_number', '$user_id', '$district_id', '$thana_id', '$zone_id', '$bazar_name', '$proprietor_name', '$proprietor_code ', '$proprietor_address', '$proprietor_owner ', '$$proprietor_contact', '$lat','$lon')";
$result = $conn->query($sql_query);
if ($result>0) {
  $result=array("status"=>$id);
}
$conn->close();
echo json_encode($result);
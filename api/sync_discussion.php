<?php
include_once 'dbconfig.php';
$id = $_POST['id'];
$visit_number = $_POST['visit_number'];
$discussion_point = $_POST['discussion_point'];
$action = $_POST['action'];
$responsible = $_POST['responsible'];
$deadline = $_POST['deadline'];
$picture = $_POST['picture'];



$sql_query = "INSERT INTO `tbl_discussion` ( `visit_number`, `discussion_point`, `action`, `responsible`, `deadline`, `picture`) VALUES('$visit_number','$discussion_point','$action','$responsible','$deadline','$picture')";
$result = $conn->query($sql_query);
if ($result>0) {
  $result=array("status"=>$id);
}
$conn->close();
echo json_encode($result);
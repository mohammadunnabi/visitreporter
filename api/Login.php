<?php
/**
 * Created by PhpStorm.
 * User: manik
 * Date: 2/4/17
 * Time: 7:54 PM
 */

include_once 'dbconfig.php';
$user = $_POST['user'];
$password = $_POST['password'];
$sql = "SELECT id,full_name,mobile,staff_id,department,`group`,company FROM users where user='$user' AND password='$password'";
$result = $conn->query($sql);
$return_array=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $return_array=array(
            "user_id"=>$row["id"],
            "full_name"=>$row["full_name"],
            "mobile"=>$row["mobile"],
            "staff_id"=>$row["staff_id"],
            "department"=>$row["department"],
            "group"=>$row["group"],
            "company"=>$row["company"]
        );
    }
}
$conn->close();
echo json_encode($return_array);
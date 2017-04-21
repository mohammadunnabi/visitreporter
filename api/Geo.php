<?php
/**
 * Created by PhpStorm.
 * User: manik
 * Date: 2/4/17
 * Time: 7:54 PM
 */

include_once 'dbconfig.php';
$sql = "SELECT thana_id as column_id,district_id,district_name,thana_id,thana_name,token FROM `tbl_geo` ";
$result = $conn->query($sql);
$return_array=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $return_array[]=array(
            "column_id"=>$row["column_id"],
            "district_id"=>$row["district_id"],
            "district_name"=>$row["district_name"],
            "thana_id"=>$row["thana_id"],
            "thana_name"=>$row["thana_name"],
            "token"=>$row["token"]
        );
    }
}

$returnGeo['tbl_geo']['data'] = $return_array;
$conn->close();
echo json_encode($returnGeo);
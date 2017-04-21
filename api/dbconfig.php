<?php
/**
 * Created by PhpStorm.
 * User: manik
 * Date: 2/4/17
 * Time: 7:48 PM
 */

$host = "";
$user = "";
$password = "";
$dbname = "";
$conn = mysqli_connect($host, $user, $password, $dbname);
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

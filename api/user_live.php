<?php
include_once 'dbconfig2.php';
?>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple Polylines</title>
    <style>
        #map {
            height: 100%;
        }
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="map"></div>
<script>

    function initMap() {

        var locations = [
            <?php

            $lat=23.7456904;
            $lon=90.3503464;
            // $sql_query = "SELECT times_stamp,lat,lon,id,outlet_id FROM `tbl_sr_location` where  sr_id=16 and date='2017-02-20'";
            $sql_query = "SELECT DATE_FORMAT(timestamp,'%l.%i%p'),lat,lon,id FROM `tbl_gps_track`";
            $result_set = mysql_query($sql_query);
            while($row = mysql_fetch_row($result_set))
            {
            $lat=$row[1];
            $lon=$row[2];
            ?>
            ["time: <?php echo $row[0]; ?>", <?php echo $row[1]; ?>, <?php echo $row[2]; ?>, <?php echo $row[3]; ?>],

            <?php
            }
            ?>

        ];
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: {lat:<?php echo $lat; ?>, lng:<?php echo $lon; ?>},
            mapTypeId: 'terrain'
        });
        var image = new google.maps.MarkerImage(
            'http://maps.google.com/mapfiles/ms/micons/green-dot.png',
            new google.maps.Size(32, 32),   // size
            new google.maps.Point(0,0), // origin
            new google.maps.Point(16, 32)   // anchor
        );


        var infowindow = new google.maps.InfoWindow();

        var marker, i,marker1;

        for (i = 0; i < locations.length; i++) {

            if (i==locations.length-1){
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                    map: map,
                    icon: image
                });

            }else {
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                    map: map,
                });

            }


            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }


    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANcQwtMt8Y7aXbWFZOJU9VE3xiZuKcJ6E&callback=initMap">
</script>
</body>
</html>
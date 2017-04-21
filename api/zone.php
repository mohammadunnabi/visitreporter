<?php
include_once 'dbconfig2.php';

// delete condition

// delete condition
?>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple Polylines</title>
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
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

    // This example creates a 2-pixel-wide red polyline showing the path of William
    // Kingsford Smith's first trans-Pacific flight between Oakland, CA, and
    // Brisbane, Australia.







    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: {lat: 23.7456904, lng:90.3503464},
            mapTypeId: 'terrain'
        });
        var image = new google.maps.MarkerImage(
            'http://maps.google.com/mapfiles/ms/micons/green-dot.png',
            new google.maps.Size(32, 32),   // size
            new google.maps.Point(0,0), // origin
            new google.maps.Point(16, 32)   // anchor
        );
// 'http://maps.google.com/mapfiles/ms/micons/yellow-dot.png',
        var image2="http://45.64.135.76/test/outlet.png";
        var image1 = new google.maps.MarkerImage(
            'http://maps.google.com/mapfiles/ms/micons/yellow-dot.png',
            new google.maps.Size(32, 32),   // size
            new google.maps.Point(0,0), // origin
            new google.maps.Point(16, 32)   // anchor
        );
        var locations = [
            <?php
            // $sql_query = "SELECT times_stamp,lat,lon,id,outlet_id FROM `tbl_sr_location` where  sr_id=16 and date='2017-02-20'";
            $sql_query = "SELECT DATE_FORMAT(times_stamp,'%l.%i%p'),lat,lon,id FROM `tbl_sr_location` where  sr_id=21 and date=curdate() and imei_number='354453073192760' ORDER BY times_stamp ASC ";
            $result_set = mysql_query($sql_query);
            while($row = mysql_fetch_row($result_set))
            {
            ?>
            ["time: <?php echo $row[0]; ?>", <?php echo $row[1]; ?>, <?php echo $row[2]; ?>, <?php echo $row[3]; ?>],

            <?php
            }
            ?>

        ];

        var locations1 = [
            <?php
            // $sql_query = "SELECT times_stamp,lat,lon,id,outlet_id FROM `tbl_sr_location` where  sr_id=16 and date='2017-02-20'";
            $sql_query = "SELECT outlet_name,outlet_lat,outlet_lon,outlet_id FROM `tsync_todays_routes_visit` where sr_id=15";
            $result_set = mysql_query($sql_query);
            while($row = mysql_fetch_row($result_set))
            {
            ?>
            ["Outlet Name:<?php echo $row[0]; ?> ", <?php echo $row[1]; ?>, <?php echo $row[2]; ?>, <?php echo $row[3]; ?>],

            <?php
            }
            ?>

        ];

        var flightPlanCoordinates = [

            <?php
            $sql_query = "SELECT times_stamp,lat,lon,id FROM `tbl_sr_location` where sr_id=15 and date=curdate() and imei_number='354453073192760' order by times_stamp";
            $result_set = mysql_query($sql_query);
            while($row = mysql_fetch_row($result_set))
            {
            ?>

            {lat:<?php echo $row[1]; ?>, lng: <?php echo $row[2]; ?>},

            <?php
            }
            ?>
        ];

        var flightPath = new google.maps.Polyline({
            path: flightPlanCoordinates,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
        });

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
        var  i,marker1;
        for (i = 0; i < locations1.length; i++) {
            marker1 = new google.maps.Marker({
                position: new google.maps.LatLng(locations1[i][1], locations1[i][2]),
                map: map,
                icon: image2
            });
            google.maps.event.addListener(marker1, 'click', (function (marker1, i) {
                return function () {
                    infowindow.setContent(locations1[i][0]);
                    infowindow.open(map, marker1);
                }
            })(marker1, i));
        }
        flightPath.setMap(map);
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANcQwtMt8Y7aXbWFZOJU9VE3xiZuKcJ6E&callback=initMap">
</script>
</body>
</html>
#-------------------------------------------------------------------------------
# /*******************************************************************************
# * Copyright: 2019 digiBlitz Foundation
# * 
# * License: digiBlitz Public License 1.0 (DPL) 
# * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
# * 
# * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
# * 
# * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
# * 
# * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
<!DOCTYPE html>
<html lang="en">
<head>
<title>Contacts</title>

</head>
<body>
<!--header-->
<div id="header">
<!-- HEADER STARTS HERE -->
<%@ include file = "../../include/header_new.jsp" %>
  <!-- HEADER ENDS HERE -->
</div>

<div class="content indent">
    <div class="container">
        <section class="content_map">
              <div class="google-map-api"> 
                <div id="map-canvas" class="gmap"></div> 
              </div> 
        </section>
    </div>
    <div class="thumb-box14">
        <div class="container">
            
             
                <div class="col-lg-8 col-md-8 col-sm-8">
                    <h3>Write to us</h3>
                    <form id="contact-form">
                          <div class="contact-form-loader"></div>
                          <fieldset>
                            <label class="name form-div-1">
                              <input type="text" name="name" placeholder="Name:" value="" data-constraints="@Required @JustLetters"  />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid name.</span>
                            </label>
                            <label class="email form-div-2">
                              <input type="text" name="email" placeholder="E-mail:" value="" data-constraints="@Required @Email" />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid email.</span>
                            </label>
                            <label class="phone form-div-3">
                              <input type="text" name="phone" placeholder="Phone:" value="" data-constraints="@Required @JustNumbers" />
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*This is not a valid phone.</span>
                            </label>
                            <label class="message form-div-4">
                              <textarea name="message" placeholder="Message:" data-constraints='@Required @Length(min=20,max=999999)'></textarea>
                              <span class="empty-message">*This field is required.</span>
                              <span class="error-message">*The message is too short.</span>
                            </label>
                            <!-- <label class="recaptcha"><span class="empty-message">*This field is required.</span></label> -->
                            <div>
                              <a href="#" data-type="submit" class="btn-default btn3">submit</a>
                            </div>
                          </fieldset> 
                          <div class="modal fade response-message">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                  <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                  You message has been sent! We will be in touch soon.
                                </div>      
                              </div>
                            </div>
                          </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--footer-->
<footer>
     <%@ include file = "../../include/Footerpro.jsp" %>
</footer>
<script src="js/bootstrap.min.js"></script>
<script src="js/tm-scripts.js"></script>
<script type="text/javascript"> 
          google_api_map_init(); 
          function google_api_map_init(){ 
            var map; 
            var coordData = new google.maps.LatLng(parseFloat(40.646197), parseFloat(-73.9724068,14)); 
            var markCoord1 = new google.maps.LatLng(parseFloat(40.643180), parseFloat(-73.9874068,14)); 
             var markCoord2 = new google.maps.LatLng(parseFloat(40.6422180), parseFloat(-73.9784068,14)); 
             var markCoord3 = new google.maps.LatLng(parseFloat(40.6482180), parseFloat(-73.9724068,14)); 
             var markCoord4 = new google.maps.LatLng(parseFloat(40.6442180), parseFloat(-73.9664068,14)); 
             var markCoord5 = new google.maps.LatLng(parseFloat(40.6379180), parseFloat(-73.9552068,14)); 
            var marker; 
 
            var styleArray = [
    {
        "featureType": "water",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#b5d29c"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#edeae2"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#fdc043"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#e1a41b"
            },
            {
                "lightness": 0
            },
            {
                "weight": 1
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#fffd8b"
            },
            {
                "weight":2
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#d6c692"
            },
            {
                "lightness": 0
            },
            {
                "weight":1
            }
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "weight":0.5
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#d1d0cd"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "elementType": "labels.text.stroke",
        "stylers": [
            {
                "visibility": "on"
            },
            {
                "color": "#fefc8b"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "elementType": "labels.text.fill",
        "stylers": [
            {
                "saturation": 36
            },
            {
                "color": "#3b3a21"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "elementType": "labels.icon",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "transit",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#99b3cc"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#ff0000"
            },
            {
                "lightness": 0
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#ccc4b4"
            },
            {
                "lightness": 0
            },
            {
                "weight": 1.2
            }
        ]
    }
]
             
            var markerIcon = { 
                url: "img/gmap_marker.png", 
                size: new google.maps.Size(53, 71), 
                origin: new google.maps.Point(0,0), 
                anchor: new google.maps.Point(21, 70) 
            }; 
            function initialize() { 
              var mapOptions = { 
                zoom: 14, 
                center: coordData, 
                scrollwheel: false, 
                styles: styleArray 
              } 
 
              var contentString = "<div></div>"; 
              var infowindow = new google.maps.InfoWindow({ 
                content: contentString, 
                maxWidth: 200 
              }); 
               
              var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions); 
              marker = new google.maps.Marker({ 
                map:map, 
                position: markCoord1, 
                icon: markerIcon
              }); 

              /*marker1 = new google.maps.Marker({ 
                map:map, 
                position: markCoord2, 
                icon: markerIcon
              }); 

               marker2 = new google.maps.Marker({ 
                map:map, 
                position: markCoord3, 
                icon: markerIcon
              }); 

               marker3 = new google.maps.Marker({ 
                map:map, 
                position: markCoord4, 
                icon: markerIcon
              }); 

               marker4 = new google.maps.Marker({ 
                map:map, 
                position: markCoord5, 
                icon: markerIcon
              }); */

               var contentString = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<div id="bodyContent">'+
                '<p>4578 Marmora Road, Glasgow D04 89GR <span>800 2345-6789</span></p>'+
                '</div>'+
                '</div>';

                /*var contentString1 = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<div id="bodyContent">'+
                '<p>4578 Marmora Road, Glasgow D04 89GR <span>800 2345-6789</span></p>'+
                '</div>'+
                '</div>';

                var contentString2 = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<div id="bodyContent">'+
                '<p>4578 Marmora Road, Glasgow D04 89GR <span>800 2345-6789</span></p>'+
                '</div>'+
                '</div>';

                var contentString3 = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<div id="bodyContent">'+
                '<p>4578 Marmora Road, Glasgow D04 89GR <span>800 2345-6789</span></p>'+
                '</div>'+
                '</div>';

                var contentString4 = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<div id="bodyContent">'+
                '<p>4578 Marmora Road, Glasgow D04 89GR <span>800 2345-6789</span></p>'+
                '</div>'+
                '</div>';*/

            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            /*var infowindow1 = new google.maps.InfoWindow({
                content: contentString1
            });

            var infowindow2 = new google.maps.InfoWindow({
                content: contentString2
            });

            var infowindow2 = new google.maps.InfoWindow({
                content: contentString3
            });

            var infowindow2 = new google.maps.InfoWindow({
                content: contentString4
            });*/



            google.maps.event.addListener(marker, 'click', function() {
              infowindow.open(map,marker);
              $('.gm-style-iw').parent().parent().addClass("gm-wrapper");
            });

            /*google.maps.event.addListener(marker1, 'click', function() {
              infowindow.open(map,marker1);
              $('.gm-style-iw').parent().parent().addClass("gm-wrapper");
            });

            google.maps.event.addListener(marker2, 'click', function() {
              infowindow.open(map,marker2);
              $('.gm-style-iw').parent().parent().addClass("gm-wrapper");
            });

            google.maps.event.addListener(marker3, 'click', function() {
              infowindow.open(map,marker3);
              $('.gm-style-iw').parent().parent().addClass("gm-wrapper");
            });

            google.maps.event.addListener(marker4, 'click', function() {
              infowindow.open(map,marker4);
              $('.gm-style-iw').parent().parent().addClass("gm-wrapper");
            });*/

            google.maps.event.addDomListener(window, 'resize', function() {

              map.setCenter(coordData);

              var center = map.getCenter();
            });
          }

            google.maps.event.addDomListener(window, "load", initialize); 

          }    
</script>
</body>
</html>

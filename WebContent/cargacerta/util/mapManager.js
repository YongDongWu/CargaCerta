sap.ui.define([
		'jquery.sap.global'
	], function(jQuery) {
	"use strict";
   
    var mapManager = function() {
    	
    	this.oGoogleMap = null;
    	
    	this.oRoute = null;
    	
    	this.editMode = false;
    	this.editMarkers = [];
    	
    	this.oOriginMarker = null;
    	this.oDestinationMarker = null;
    	this.aRoutePolyline = null;
    	
    	this.setGoogleMap = function(googleMap) {
        	this.oGoogleMap = googleMap;
        };
        
        this.getRoute = function() {
        	return this.oRoute;
        };
        
        this.setRoute = function(route) {
        	this.oRoute = route;
        	
        	this.removeRouteFromMap();
			
			if (!this.oRoute) return;
			
			var polyine = this.drawRoute();
			this.mapFitBounds(polyine);
        };
        
        this.setEditMode = function(editMode) {
        	this.editMode = editMode;
        	
        	if (this.editMode) {
            	if (!this.directionsService) {
            		this.directionsService = new google.maps.DirectionsService;
            	}
            	
            	this.onEditMapClickListener = this.oGoogleMap.addListener('click', this._handleOnEditMapClick.bind(this));
        	} else {
        		this.oGoogleMap.removeListener(this.onEditMapClickListener);
        	}
        };
        
        this._handleOnEditMapClick = function(event) {
        	var marker = new google.maps.Marker({
                position: event.latLng, 
                map: this.oGoogleMap
            });
        	
        	this.editMarkers.push(marker);
        	
        	if (this.editMarkers.length < 2) {
        		return;
        	}
        	
        	var originMarker = this.editMarkers[this.editMarkers.length - 2];
        	
        	this.directionsService.route({
				origin: originMarker.getPosition(),
				destination: marker.getPosition(),
				travelMode: google.maps.TravelMode.DRIVING
			}, function(response, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					this.appendPathToRoute(google.maps.geometry.encoding.decodePath(response.routes[0].overview_polyline));
				}
			}.bind(this));
        };
        
        this.appendPathToRoute = function(path) {
        	if (!this.aRoutePolyline) {
        		this.oRoute = {};
        		
	        	this.aRoutePolyline = new google.maps.Polyline({
					strokeColor: "#528EF7",
		            strokeOpacity: 0.75,
		            strokeWeight: 4,
	                path: path,
	        		map: this.oGoogleMap
	        	});
        	} else {
        		for (var i in path) {
        			this.aRoutePolyline.getPath().push(path[i]);
        		}
        	}
        	
        	var path = this.aRoutePolyline.getPath();
        	
        	this.oRoute.routePoints = [];
        	this.aRoutePolyline.getPath().forEach(function(element, number) {
        		this.oRoute.routePoints.push({latitude:element.lat(), longitude: element.lng()});
        	}.bind(this));
        };
        
        this.removeRouteFromMap = function() {
    		if (this.oOriginMarker) {
    			this.oOriginMarker.setMap(null);
    			this.oOriginMarker = null;
    		}
    		
    		if (this.oDestinationMarker) {
    			this.oDestinationMarker.setMap(null);
    			this.oDestinationMarker = null;
    		}
    		
    		if (this.aRoutePolyline) {
	    		this.aRoutePolyline.setMap(null);
	    		this.aRoutePolyline = null;
    		}
    	};
        
        this.drawRoute = function() {
        	var origin = this.oRoute.routePoints[0];
        	var destination = this.oRoute.routePoints[this.oRoute.routePoints.length - 1];
        	
        	var originLatLng = new google.maps.LatLng(origin.latitude, origin.longitude);
			var destinationLatLng = new google.maps.LatLng(destination.latitude, destination.longitude);

			this.oOriginMarker = new google.maps.Marker({
    			position: originLatLng,
    			map: this.oGoogleMap
    		});
    		
    		this.oDestinationMarker = new google.maps.Marker({
    			position: destinationLatLng,
    			map: this.oGoogleMap
    		});
    		
      		var aRouteCoordinates = [];
      		
			aRouteCoordinates.push(new google.maps.LatLng(origin.latitude, origin.longitude));
    		
    		for (var i in this.oRoute.routePoints) {
    			var routePoint = this.oRoute.routePoints[i];
    			aRouteCoordinates.push(new google.maps.LatLng(routePoint.latitude, routePoint.longitude));
    		}
    		
			aRouteCoordinates.push(new google.maps.LatLng(destination.latitude, destination.longitude));
			
			this.aRoutePolyline = new google.maps.Polyline({
				strokeColor: "#528EF7",
	            strokeOpacity: 0.75,
	            strokeWeight: 4,
	            path: aRouteCoordinates,
				map: this.oGoogleMap
			});
			
			return this.aRoutePolyline;
    	};
        
        this.clearMap = function() {
    		this.removeRouteFromMap();
    	};
        
        this.mapFitBounds = function(polyline) {
        	var aCoordinates = polyline.getPath().getArray();
        	
    		var bounds = new google.maps.LatLngBounds();
    		for(var i in aCoordinates){
    			bounds.extend(aCoordinates[i]);
    		}
    		
    		this.oGoogleMap.fitBounds(bounds);
    	};
        
        this.refresh = function() {
        	this.clearMap();
        	
        	if (!this.oRoutePlan) return;
			
        	var polyine = this.drawRoute();
			this.mapFitBounds(polyine);
        };
    };

	return new mapManager();
});
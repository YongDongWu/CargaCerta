sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController',
		'cargacerta/util/mapManager'
	], function(jQuery, baseListController, mapManager) {
	"use strict";

	var CController = baseListController.extend("cargacerta.route.list", {
		title: 'Rota',
		model: 'route',
		
		openDialog: function(item, options) {
			options = options || {};
			
			options.width = "80%";
			
			options.callback = function() {
				var mapOptions = {  
					center: {lat: -29.796548, lng: -51.14876},  
					scrollwheel: false,
				    zoom: 8
				};
		    	
				var map = new google.maps.Map($("#routeMap")[0], mapOptions);
				mapManager.setGoogleMap(map);
				mapManager.setEditMode(true);
				
				if (item) {
					mapManager.setRoute(item);
				}
			};
			
			cargacerta.list.prototype.openDialog.apply(this, [item, options]);
		},
		
		saveChanges: function(item, callback, callbackComplete) {
			item.routePoints = mapManager.getRoute().routePoints;
			
			cargacerta.list.prototype.saveChanges.apply(this, arguments);
		}
	});

	return CController;
});
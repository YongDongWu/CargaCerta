sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'cargacerta/util/mapManager'
	], function(jQuery, Controller, mapManager) {
	"use strict";

	var CController = Controller.extend("cargacerta.report.execution", {
		oListModel: null,
		
		onBeforeRendering: function() {
			this.oListModel = new sap.ui.model.json.JSONModel();
			this.getView().setModel(this.oListModel);
			
			this.oListModel.setData([
			                         {"id":701,"driver":{"id":284,"name":"Douglas","age":24,"address":null,"phoneNumber":null},"truck":{"id":286,"truckType":{"id":285,"name":"Carreta","length":25,"capacity":5000},"plate":"ABC","latitude":0.0,"longitude":0.0},"route":{"id":301,"name":"NH - POA"},"startDate":"2015-10-29","deliveryDate":"2016-11-01", executedDate:"2015-10-28", totalHours:"3"},
			                         {"id":701,"driver":{"id":284,"name":"Douglas","age":24,"address":null,"phoneNumber":null},"truck":{"id":286,"truckType":{"id":285,"name":"Carreta","length":25,"capacity":5000},"plate":"ABC","latitude":0.0,"longitude":0.0},"route":{"id":301,"name":"NH - POA"},"startDate":"2015-10-29","deliveryDate":"2016-11-01", executedDate:"2015-10-29", totalHours:"4"},
			                         {"id":701,"driver":{"id":284,"name":"Douglas","age":24,"address":null,"phoneNumber":null},"truck":{"id":286,"truckType":{"id":285,"name":"Carreta","length":25,"capacity":5000},"plate":"ABC","latitude":0.0,"longitude":0.0},"route":{"id":301,"name":"NH - POA"},"startDate":"2015-10-29","deliveryDate":"2016-11-01", executedDate:"2015-11-01", totalHours:"2"}
			                         ]);
		},
		
		onDetailPress: function(oEvent) {
			var detailFragment = sap.ui.xmlfragment(this.getView().getId(), "cargacerta.report.detail", this);
			
			var dialog = new sap.m.Dialog({
				title: "Rota Executada",
				contentWidth: "80%",
				content: detailFragment,
				beginButton: new sap.m.Button({
					text: 'Fechar',
					press: function () {
						dialog.close();
					}
				}),
				afterOpen: function() {
					var mapOptions = {  
						center: {lat: -29.796548, lng: -51.14876},  
						scrollwheel: false,
					    zoom: 8
					};
			    	
					var map = new google.maps.Map($("#executedMap")[0], mapOptions);
					mapManager.setGoogleMap(map);
					mapManager.setEditMode(true);
					
					$.ajax({ 
					    type: 'GET', 
					    url: '/rest/route/301',
					    contentType : 'application/json',
					    dataType: 'json',
					    success: function(data) { 
					    	mapManager.setRoute(data);
					    }.bind(this)
					});
				},
				afterClose: function() {
					dialog.destroy();
				}
			});

			//to get access to the global model
			this.getView().addDependent(dialog);
			
			dialog.open();
		}
	});

	return CController;
});
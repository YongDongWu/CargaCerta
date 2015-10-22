sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController',
		'sap/ui/model/json/JSONModel',
		'sap/ui/model/Filter',
		'cargacerta/util/mapManager'
	], function(jQuery, baseListController, JSONModel, Filter, mapManager) {
	"use strict";

	var CController = baseListController.extend("cargacerta.routeExecution.list", {
		title: 'Execução de Rota',
		model: 'routeExecution',
		
		driverInput: null,
		truckInput: null,
		routeInput: null,
		
		onBeforeRendering: function() {
			cargacerta.list.prototype.onBeforeRendering.apply(this);
			
			var oDriverModel = new JSONModel();
			this.getView().setModel(oDriverModel, "DriverCollection");
			
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/driver',
			    contentType : 'application/json',
			    dataType: 'json',
			    success: function(data) { 
			    	oDriverModel.setData(data);
			    }.bind(this)
			});
			
			var oTruckModel = new JSONModel();
			this.getView().setModel(oTruckModel, "TruckCollection");
			
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/truck',
			    contentType : 'application/json',
			    dataType: 'json',
			    success: function(data) { 
			    	oTruckModel.setData(data);
			    }.bind(this)
			});
			
			var oRouteModel = new JSONModel();
			this.getView().setModel(oRouteModel, "RouteCollection");
			
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/route',
			    contentType : 'application/json',
			    dataType: 'json',
			    success: function(data) { 
			    	oRouteModel.setData(data);
			    }.bind(this)
			});
		},
		
		onDriverSuggestionSelected: function(oEvent) {
			var selectedItem = oEvent.getParameter("selectedItem");
			
			this.oChangeModel.getData().driver = {};
			this.oChangeModel.getData().driver.id = selectedItem.getKey();
		},

		handleDriverValueHelp: function(oEvent) {
			var sInputValue = oEvent.getSource().getValue();

			this.driverInput = oEvent.getSource();
			
			// create value help dialog
			if (!this._driverValueHelpDialog) {
				this._driverValueHelpDialog = sap.ui.xmlfragment("cargacerta.routeExecution.driverhelper", this);
				this.getView().addDependent(this._driverValueHelpDialog);
			}

			// create a filter for the binding
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sInputValue
			);
			
			this._driverValueHelpDialog.getBinding("items").filter([oFilter]);

			// open value help dialog filtered by the input value
			this._driverValueHelpDialog.open(sInputValue);
		},

		_handleDriverValueHelpSearch: function(oEvent) {
			var sValue = oEvent.getParameter("value");
			
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sValue
			);
			
			oEvent.getSource().getBinding("items").filter([oFilter]);
		},

		_handleDriverValueHelpClose: function(oEvent) {
			var oSelectedItem = oEvent.getParameter("selectedItem");
			
			if (oSelectedItem) {
				this.driverInput.setValue(oSelectedItem.getTitle());
			}
			
			oEvent.getSource().getBinding("items").filter([]);
		},
		
		onTruckSuggestionSelected: function(oEvent) {
			var selectedItem = oEvent.getParameter("selectedItem");
			
			this.oChangeModel.getData().truck = {};
			this.oChangeModel.getData().truck.id = selectedItem.getKey();
		},

		handleTruckValueHelp: function(oEvent) {
			var sInputValue = oEvent.getSource().getValue();

			this.truckInput = oEvent.getSource();
			
			// create value help dialog
			if (!this._truckValueHelpDialog) {
				this._truckValueHelpDialog = sap.ui.xmlfragment("cargacerta.routeExecution.truckhelper", this);
				this.getView().addDependent(this._truckValueHelpDialog);
			}

			// create a filter for the binding
			var oFilter = new Filter(
				"plate",
				sap.ui.model.FilterOperator.Contains, sInputValue
			);
			
			this._truckValueHelpDialog.getBinding("items").filter([oFilter]);

			// open value help dialog filtered by the input value
			this._truckValueHelpDialog.open(sInputValue);
		},

		_handleTruckValueHelpSearch: function(oEvent) {
			var sValue = oEvent.getParameter("value");
			
			var oFilter = new Filter(
				"plate",
				sap.ui.model.FilterOperator.Contains, sValue
			);
			
			oEvent.getSource().getBinding("items").filter([oFilter]);
		},

		_handleValueHelpClose: function(oEvent) {
			var oSelectedItem = oEvent.getParameter("selectedItem");
			
			if (oSelectedItem) {
				this.truckInput.setValue(oSelectedItem.getTitle());
			}
			
			oEvent.getSource().getBinding("items").filter([]);
		},
		
		onRouteSuggestionSelected: function(oEvent) {
			var selectedItem = oEvent.getParameter("selectedItem");
			
			this.oChangeModel.getData().route = {};
			this.oChangeModel.getData().route.id = selectedItem.getKey();
		},

		handleRouteValueHelp: function(oEvent) {
			var sInputValue = oEvent.getSource().getValue();

			this.routeInput = oEvent.getSource();
			
			// create value help dialog
			if (!this._routeValueHelpDialog) {
				this._routeValueHelpDialog = sap.ui.xmlfragment("cargacerta.routeExecution.routehelper", this);
				this.getView().addDependent(this._routeValueHelpDialog);
			}

			// create a filter for the binding
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sInputValue
			);
			
			this._routeValueHelpDialog.getBinding("items").filter([oFilter]);

			// open value help dialog filtered by the input value
			this._routeValueHelpDialog.open(sInputValue);
		},

		_handleRouteValueHelpSearch: function(oEvent) {
			var sValue = oEvent.getParameter("value");
			
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sValue
			);
			
			oEvent.getSource().getBinding("items").filter([oFilter]);
		},

		_handleRouteValueHelpClose: function(oEvent) {
			var oSelectedItem = oEvent.getParameter("selectedItem");
			
			if (oSelectedItem) {
				this.routeInput.setValue(oSelectedItem.getTitle());
			}
			
			oEvent.getSource().getBinding("items").filter([]);
		}
	});

	return CController;
});
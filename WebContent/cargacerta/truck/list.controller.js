sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController',
		'sap/ui/model/json/JSONModel',
		'sap/ui/model/Filter'
	], function(jQuery, baseListController, JSONModel, Filter) {
	"use strict";

	var CController = baseListController.extend("cargacerta.truck.list", {
		title: 'Caminhão',
		model: 'truck',
		
		truckTypeInput: null,
		
		onBeforeRendering: function() {
			cargacerta.list.prototype.onBeforeRendering.apply(this);
			
			var oModel = new JSONModel();
			this.getView().setModel(oModel, "TruckTypeCollection");
			
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/truckType',
			    contentType : 'application/json',
			    dataType: 'json',
			    success: function(data) { 
			    	oModel.setData(data);
			    }.bind(this)
			});
		},
		
		onTruckTypeSuggestionSelected: function(oEvent) {
			var selectedItem = oEvent.getParameter("selectedItem");
			
			this.oChangeModel.getData().truckType = {};
			this.oChangeModel.getData().truckType.id = selectedItem.getKey();
		},

		handleValueHelp: function(oEvent) {
			var sInputValue = oEvent.getSource().getValue();

			this.truckTypeInput = oEvent.getSource();
			
			// create value help dialog
			if (!this._valueHelpDialog) {
				this._valueHelpDialog = sap.ui.xmlfragment("cargacerta.truck.trucktypehelper", this);
				this.getView().addDependent(this._valueHelpDialog);
			}

			// create a filter for the binding
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sInputValue
			);
			
			this._valueHelpDialog.getBinding("items").filter([oFilter]);

			// open value help dialog filtered by the input value
			this._valueHelpDialog.open(sInputValue);
		},

		_handleValueHelpSearch: function(oEvent) {
			var sValue = oEvent.getParameter("value");
			
			var oFilter = new Filter(
				"name",
				sap.ui.model.FilterOperator.Contains, sValue
			);
			
			oEvent.getSource().getBinding("items").filter([oFilter]);
		},

		_handleValueHelpClose: function(oEvent) {
			var oSelectedItem = oEvent.getParameter("selectedItem");
			
			if (oSelectedItem) {
				this.truckTypeInput.setValue(oSelectedItem.getTitle());
			}
			
			oEvent.getSource().getBinding("items").filter([]);
		}
	});

	return CController;
});
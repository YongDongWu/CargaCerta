sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
	], function(jQuery, Controller) {
	"use strict";

	var CController = Controller.extend("cargacerta.list", {
		title: "",
		model: "",
		
		oListModel: null,
		oChangeModel: null,
		
		onBeforeRendering: function() {
			this.oListModel = new sap.ui.model.json.JSONModel();
			this.getView().setModel(this.oListModel);
			
			this.refreshList();
		},
		
		refreshList: function() {
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/' + this.model,
			    contentType : 'application/json',
			    dataType: 'json',
			    success: function(data) { 
			        this.oListModel.setData(data);
			    }.bind(this)
			});
		},

		onCreatePress: function() {
			this.openDialog();
		},
		
		openDialog: function(item) {
			item = item || {};
			
			var detailFragment = sap.ui.xmlfragment(this.getView().getId(), "cargacerta." + this.model + ".detail", this);
			
			var dialog = new sap.m.Dialog({
				title: this.title,
				content: detailFragment,
				beginButton: new sap.m.Button({
					text: 'Fechar',
					press: function () {
						dialog.close();
					}
				}),
				endButton: new sap.m.Button({
					text: 'Salvar',
					press: [function () {
						dialog.setBusy(true);
						this.saveChanges(this.oChangeModel.getData(), function() {
							dialog.close();
							this.refreshList();
						}, function() {
							dialog.setBusy(false);
						});
					}, this]
				}),
				afterClose: function() {
					dialog.destroy();
				}
			});

			//to get access to the global model
			this.getView().addDependent(dialog);
			
			this.oChangeModel = new sap.ui.model.json.JSONModel(item);
			dialog.setModel(this.oChangeModel);
			
			dialog.open();
		},
		
		saveChanges: function(item, callback, callbackComplete) {
			$.ajax({ 
			    type: item.id == null ? 'POST' : 'PUT', 
			    url: '/rest/' + this.model + (item.id != null ? '/' + item.id : ''),
			    contentType : 'application/json',
			    data: JSON.stringify(item),
			    dataType: 'json',
			    success: callback.bind(this),
			    error: function() {
			    	sap.m.MessageToast.show("Erro ao salvar dados.");
			    },
			    complete: callbackComplete.bind(this)
			});
		},
		
		onDetailPress: function(oEvent) {
			var listItem = oEvent.getSource();
			var path = listItem.getBindingContextPath();
			var item = this.oListModel.getProperty(path);
			
			this.openItem(item.id);
		},
		
		openItem: function(itemId) {
			$.ajax({ 
			    type: 'GET', 
			    url: '/rest/' + this.model + "/" + itemId,
			    contentType : 'application/json',
			    dataType: 'json',
			    success: this.openDialog.bind(this)
			});
		}
	});

	return CController;
});
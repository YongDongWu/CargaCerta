sap.ui.controller("cargacerta.trucktype.list", {
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
		    url: '/rest/trucktype',
		    contentType : 'application/json',
		    dataType: 'json',
		    success: function(data) { 
		        this.oListModel.setData(data);
		    }.bind(this)
		});
	},

	onCreatePress: function() {
		this.openDialog({
			name: null,
			length: null,
			capacity: null
		});
	},
	
	openDialog: function(item) {
		var dialog = new sap.m.Dialog({
			title: 'Tipo de Caminhão',
			content: sap.ui.xmlfragment(this.getView().getId(), "cargacerta.trucktype.detail"),
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
						dialog.setBusy(false);
						dialog.close();
						this.refreshList();
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
	
	saveChanges: function(item, callback) {
		$.ajax({ 
		    type: item.id == null ? 'POST' : 'PUT', 
		    url: '/rest/trucktype' + (item.id != null ? '/' + item.id : ''),
		    contentType : 'application/json',
		    data: JSON.stringify(item),
		    dataType: 'json',
		    success: callback.bind(this)
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
		    url: '/rest/trucktype/' + itemId,
		    contentType : 'application/json',
		    dataType: 'json',
		    success: this.openDialog.bind(this)
		});
	}

});
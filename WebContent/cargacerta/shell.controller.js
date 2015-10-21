sap.ui.controller("cargacerta.shell", {
	oShell: null,
	oModel: null,
	oSelectedItem: null,

	onBeforeRendering: function() {
		var worksheets = [
		    {text: "Rotas", view: "cargacerta.route.list"},
			{text: "Motoristas", view: "cargacerta.driver.list"},
			{text: "Tipos de Caminhões", view: "cargacerta.trucktype.list"},
			{text: "Caminhões", view: "cargacerta.truck.list"},
			{text: "Produtos", view: "cargacerta.product.list"},
			{text: "Usuários", view: "cargacerta.user.list"}
		];

		this.oModel = new sap.ui.model.json.JSONModel();
		this.oModel.setData(worksheets);
		this.getView().setModel(this.oModel, "ShellItems");
		
		this.oShell = this.getView().byId("ApplicationShell");
		this.navigateTo(worksheets[0]);
	},

	handlePressNavigation: function(oEvent) {
		var bState = this.oShell.getShowPane();
		this.toggleNavigation(!bState);
	},

	toggleNavigation: function(bState) {
		var oItem = this.oShell.getHeadItems()[0];
		this.oShell.setShowPane(bState);
		oItem.setShowMarker(bState);
		oItem.setSelected(bState);
	},

	onSelectionChange: function(oEvent) {
		var listItem = oEvent.getParameter("listItem");
		var path = listItem.getBindingContextPath();
		var item = this.oModel.getProperty(path);
		
		this.toggleNavigation(false);
		this.navigateTo(item);
	},

	navigateTo: function(item) {
		if (item != this.oSelectedItem) {
			this.oShell.removeAllContent();
			this.oSelectedItem = item;
			this.oShell.addContent(this.buildView(this.oSelectedItem));
		}
	},

	buildView: function(workSetItemConfig) {
		if (!workSetItemConfig.created) {
			workSetItemConfig.created = sap.ui.view({
				viewName: workSetItemConfig.view,
				type: sap.ui.core.mvc.ViewType.XML
			});
		}
		
		return workSetItemConfig.created;
	}

});
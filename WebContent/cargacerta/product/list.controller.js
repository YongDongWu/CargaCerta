sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController'
	], function(jQuery, baseListController) {
	"use strict";

	var CController = baseListController.extend("cargacerta.product.list", {
		title: 'Produto',
		model: 'product',
	});

	return CController;
});
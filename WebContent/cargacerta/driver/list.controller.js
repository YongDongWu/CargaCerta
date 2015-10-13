sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController'
	], function(jQuery, baseListController) {
	"use strict";

	var CController = baseListController.extend("cargacerta.driver.list", {
		title: 'Motorista',
		model: 'driver',
	});

	return CController;
});
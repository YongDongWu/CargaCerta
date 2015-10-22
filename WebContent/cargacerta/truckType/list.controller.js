sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController'
	], function(jQuery, baseListController) {
	"use strict";

	var CController = baseListController.extend("cargacerta.truckType.list", {
		title: 'Tipo de Caminhão',
		model: 'truckType',
	});

	return CController;
});
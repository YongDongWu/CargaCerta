sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController'
	], function(jQuery, baseListController) {
	"use strict";

	var CController = baseListController.extend("cargacerta.trucktype.list", {
		title: 'Tipo de Caminhão',
		model: 'truckType',
	});

	return CController;
});
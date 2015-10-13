sap.ui.define([
		'jquery.sap.global',
		'cargacerta/baseListController'
	], function(jQuery, baseListController) {
	"use strict";

	var CController = baseListController.extend("cargacerta.user.list", {
		title: 'Usuário',
		model: 'user',
	});

	return CController;
});
const app = angular.module("admin-app",["ngRoute"]);

app.config(function ($routeProvider){
	$routeProvider
	.when("/admin/product",{
		templateUrl:" ",
		controller:"product-ctrl"
	})
	.when("/authorize",{
		templateUrl:" ",
		controller:"authority-ctrl"
	})
	.when("/unthorized",{
		templateUrl:" ",
		controller:"authority-ctrl"
	});
})
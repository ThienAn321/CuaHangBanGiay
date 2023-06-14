const app = angular.module("admin-app",["ngRoute"]);

app.config(function ($routeProvider){
	$routeProvider
	.when("/admin/product",{
		templateUrl:" ",
		controller:"product-ctrl"
	})
	.when("/admin/user",{
		templateUrl:" ",
		controller:"user-ctrl"
	})
	.when("/admin/category",{
		templateUrl:" ",
		controller:"category-ctrl"
	})
	.when("/admin/order",{
		templateUrl:" ",
		controller:"order-ctrl"
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
app.controller("order-ctrl", function($scope, $http) {
	$scope.orders = [];
	$scope.orderStatus = [];

	$scope.initialize = function() {
		$http.get("/rest/orders").then(resp => {
			$scope.orders = resp.data;
		});

		$http.get("/rest/orderStatus").then(resp => {
			$scope.orderStatus = resp.data;
		});
	}

	$scope.initialize();

	$scope.updateStatus = function(item) {
		$scope.order = angular.copy(item);
		console.log(this.order.orderStatus.id);
		if (this.order.orderStatus.id == 'HuyBo') {
			$http.put(`/rest/orders/cancelOrder/${this.order.id}`, this.order).then(resp => {
				var index = $scope.orders.findIndex(p => p.id == this.order.id);
				$scope.orders[index] = this.order;
				alert("Cập nhật hóa đơn thành công");
				console.log(resp.data)
			}).catch(error => {
				alert("Lỗi cập nhật hóa đơn");
				console.log("Error", error);
			});
		} else {
			$http.put(`/rest/orders/${this.order.id}`, this.order).then(resp => {
				var index = $scope.orders.findIndex(p => p.id == this.order.id);
				$scope.orders[index] = this.order;
				alert("Cập nhật hóa đơn thành công");
			}).catch(error => {
				alert("Lỗi cập nhật hóa đơn");
				console.log("Error", error);
			});
		}
	}
	
	$scope.sortColumn = "-id";
	
	$scope.currentPage = 0;
	$scope.pageSize = "10";

	$scope.totalQuantity = function() {
		return $scope.orders.length;
	}

	$scope.numberOfPages = function() {
		return Math.ceil($scope.orders.length / $scope.pageSize);
	}
	for (let i = 0; i < 45; i++) {
		$scope.orders.push("Item " + i);
	}

	$scope.pagination = function() {
		$scope.currentPage = 0;
	}
});

app.filter('startFrom', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	}
});
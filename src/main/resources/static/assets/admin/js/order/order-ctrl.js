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
		if(this.order.orderStatus.id == 'HuyBo') {
			$http.put(`/rest/orders/cancelOrder/${this.order.id}`, this.order).then(resp => {
				var index = $scope.orders.findIndex(p => p.id == this.order.id);
				$scope.orders[index] = this.order;
				alert("Cập nhật hóa đơn thành công");
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


	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.orders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.orders.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

});
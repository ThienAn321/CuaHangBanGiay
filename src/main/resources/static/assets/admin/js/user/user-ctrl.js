app.controller("user-ctrl", function($scope, $http) {
	$scope.users = [];

	$scope.initialize = function() {
		$http.get("/rest/account/user").then(resp => {
			$scope.users = resp.data;
		});
	}

	$scope.initialize();
	
	$scope.activeUser = function(item) {
		$http.put(`/rest/account/active/${item}`).then(resp => {
				alert("Cập nhật tài khoản thành công");
				location.reload();
			}).catch(error => {
				alert("Lỗi tài khoản hóa đơn");
				console.log("Error", error);
			});
	}
	
	$scope.disableUser = function(item) {
		$http.put(`/rest/account/disable/${item}`).then(resp => {
				alert("Cập nhật tài khoản thành công");
				location.reload();
			}).catch(error => {
				alert("Lỗi tài khoản hóa đơn");
				console.log("Error", error);
			});
	}


	/*$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
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
	}*/

});
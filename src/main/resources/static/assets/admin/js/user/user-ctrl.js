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
	
	$scope.sortColumn = "-username";
	
	$scope.currentPage = 0;
	$scope.pageSize = "10";

	$scope.totalQuantity = function() {
		return $scope.users.length;
	}

	$scope.numberOfPages = function() {
		return Math.ceil($scope.users.length / $scope.pageSize);
	}
	for (let i = 0; i < 45; i++) {
		$scope.users.push("Item " + i);
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
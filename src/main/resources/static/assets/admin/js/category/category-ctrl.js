app.controller("category-ctrl", function($scope, $http) {
	$scope.category = [];

	$scope.initialize = function() {
		$http.get("/rest/category").then(resp => {
			$scope.category = resp.data;

		});
	}

	$scope.initialize();

	$scope.formCategory = {
		items: [],
		add(id) {
			var item = $scope.category.find(item => item.id == id);
			if (item) {
				$http.get(`/rest/category/${id}`).then(resp => {
					this.items.splice(0, 1);
					this.items.push(resp.data);
					console.log(resp.data);
					this.saveToSessionStorage();
					location.href = "/admin/category/add";
				})
			}
		},

		clear() {
			this.items = [];
			this.saveToSessionStorage();
		},

		get count() {
			return this.items;
		},

		saveToSessionStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			sessionStorage.setItem("category", json);
		},

		loadFromSessionStorage() {
			var json = sessionStorage.getItem("category");
			this.items = json ? JSON.parse(json) : [];
		},

		check() {
			if (this.items.length == 0) {
				$scope.formCategory.items[0] = {
					icon: 'cloud-upload.jpg',
				};
			}
		}

	}

	$scope.formCategory.loadFromSessionStorage();
	$scope.formCategory.check();

	$scope.reset = function() {
		$scope.formCategory.clear();
		$scope.formCategory.items[0] = {
			icon: 'cloud-upload.jpg'
		};
	}

	$scope.create = function() {
		var item = angular.copy($scope.formCategory.items[0]);
		$http.post('/rest/category', item).then(resp => {
			$scope.category.push(resp.data);
			$scope.formCategory.add(resp.data.id);
			alert("Thêm mới danh mục thành công");
		}).catch(error => {
			alert("Lỗi thêm mới danh mục");
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.formCategory.items[0]);
		$http.put(`/rest/category/${item.id}`, item).then(resp => {
			var index = $scope.category.findIndex(p => p.id == item.id);
			$scope.category[index] = item;
			$scope.formCategory.saveToSessionStorage();
			alert("Cập nhật danh mục thành công");
		}).catch(error => {
			alert("Lỗi cập nhật danh mục");
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		$http.delete(`/rest/category/${item.id}`).then(resp => {
			var index = $scope.category.findIndex(p => p.id == item.id);
			$scope.category.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.sortColumn = "-id";
	
	$scope.currentPage = 0;
	$scope.pageSize = "10";

	$scope.totalQuantity = function() {
		return $scope.category.length;
	}

	$scope.numberOfPages = function() {
		return Math.ceil($scope.category.length / $scope.pageSize);
	}
	for (let i = 0; i < 45; i++) {
		$scope.category.push("Item " + i);
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
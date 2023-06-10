app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];

	$scope.initialize = function() {
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;

		});

		$http.get("/rest/category").then(resp => {
			$scope.cates = resp.data;
		})
	}

	$scope.initialize();



	$scope.form = {
		items: [],
		add(id) {
			var item = $scope.items.find(item => item.id == id);
			if (item) {
				$http.get(`/rest/products/${id}`).then(resp => {
					this.items.splice(0, 1);
					this.items.push(resp.data);
					this.saveToSessionStorage();
					location.href = "/admin/product/add";
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
			sessionStorage.setItem("form", json);
		},

		loadFromSessionStorage() {
			var json = sessionStorage.getItem("form");
			this.items = json ? JSON.parse(json) : [];
		},

		check() {
			if (this.items.length == 0) {
				$scope.form.items[0] = {
					image1: 'cloud-upload.jpg',
					image2: 'cloud-upload.jpg',
					image3: 'cloud-upload.jpg',
					image4: 'cloud-upload.jpg'
				};
			}
		}

	}

	$scope.form.loadFromSessionStorage();
	$scope.form.check();

	$scope.reset = function() {
		$scope.form.clear();
		$scope.form.items[0] = {
			image1: 'cloud-upload.jpg',
			image2: 'cloud-upload.jpg',
			image3: 'cloud-upload.jpg',
			image4: 'cloud-upload.jpg'
		};
	}

	$scope.create = function() {
		var item = angular.copy($scope.form.items[0]);
		$http.post('/rest/products', item).then(resp => {
			$scope.items.push(resp.data);
			$scope.form.add(resp.data.id);
			alert("Thêm mới sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form.items[0]);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.form.saveToSessionStorage();
			alert("Cập nhật sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		});
	}

	$scope.imageChanged1 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.items[0].image1 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.imageChanged2 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.items[0].image2 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.imageChanged3 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.items[0].image3 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.imageChanged4 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.items[0].image4 = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}


	$scope.pager = {
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
	}

});
const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http){
	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToSessionStorage();
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToSessionStorage();
				})
			}
		},

		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToSessionStorage();
		},

		clear() {
			this.items = [];
			this.saveToSessionStorage();
		},

		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},

		get amount() {
			return this.items
				.map(item => item.qty * item.price * (100 - item.discount) / 100)
				.reduce((total, qty) => total += qty, 0);
		},

		saveToSessionStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			sessionStorage.setItem("cart-tco", json);
		},

		loadFromSessionStorage() {
			var json = sessionStorage.getItem("cart-tco");
			this.items = json ? JSON.parse(json) : [];
		},

		get ship() {
			if (this.count == 0) return 0;
			else if (this.count <= 3) return 12000;
			else if (this.count >= 4) return 0;
		},

	}

	$scope.cart.loadFromSessionStorage();

	$scope.order = {
		createDate: new Date(),
		address: "",
		account: {username: $("#username").text()},
		fullname : "",
		description: "",
		phoneNumber: "",
		orderStatus: {id: "ChoXacNhan"},
		isPaid: 1,
		get expectedDate() {
			let date = new Date();
			let numberOfDayToAdds = 7;
			return date.setDate(date.getDate() + numberOfDayToAdds);
		},
		get orderTimeDetail() {
			let date = new Date();
			let currentHour = date.toLocaleTimeString();
			return currentHour;
		},
		shippingCost: $scope.cart.ship,
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					products: { id: item.id },
					price: item.price * ((100 - item.discount) / 100),
					quantity: item.qty
				}
			});
		},


		purchase() {
			
				if ($scope.cart.count > 0) {
					var order = angular.copy(this);
					$http.post("/rest/orders", order).then(resp => {
						alert("Đặt hàng thành công");
						$scope.cart.clear();
						returnOrder = resp.data;
						location.href = "/order/track/" + resp.data.id;
					}).catch(error => {
						console.log(error)
						alert("Có lỗi xảy ra trong quá trình đặt hàng! Vui lòng thử lại")
					})
				} else {
					alert("Bạn chưa có sản phẩm trong giỏ hàng")
				}

		},

	};
})
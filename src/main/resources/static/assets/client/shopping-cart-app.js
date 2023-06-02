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

	/*$scope.order = {
		createDate: new Date(),
		address: "",
		user: { username: split[2] },
		description: "",
		phoneNumber: "",
		status: "ChoXacNhan",
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
					product: { id: item.id },
					price: item.price * ((100 - item.discount) / 100),
					quantity: item.qty
				}
			});
		},


		purchase() {
			if ($scope.order.phoneNumber.match("^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$")) {
				if ($scope.cart.count > 0) {
					var order = angular.copy(this);
					$http.post("/api/orders", order).then(resp => {
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
			} else {
				document.getElementById("sdt").style.display = "flex";
			}
		},

	};*/
})
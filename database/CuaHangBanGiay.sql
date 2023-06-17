use master
go

if exists(select * from sys.databases where name = 'CuaHangBanGiay')
	drop database CuaHangBanGiay

create database CuaHangBanGiay

go

use CuaHangBanGiay

go

create table Roles(
	Id varchar(10) not null,
	Name nvarchar(50) not null,
	primary key (Id)
)

go

create table Accounts(
	Username varchar(30) not null,
	Password varchar(60),
	Email varchar(40) not null,
	Fullname nvarchar(40),
	Address nvarchar(200),
	Phone nvarchar(10),
	accountVerified bit,
	Activate_Code varchar(40),
	Forgot_Password_Code varchar(40),
	Avatar varchar(40),
	primary key (Username),
	Role_Id varchar(10) not null,
	constraint FK_Accounts_Roles
	foreign key (Role_Id) references Roles(Id),
	Create_Date varchar(50)
)

go

create table Confirmation_Token(
	Id int identity(1,1),
	Token nvarchar(60) unique not null,
	Created_At varchar(50),
	Expire_At varchar(50),
	Is_Expired bit,
	primary key(id),
	Account_Id varchar(30),
	constraint FK_ConfirmationToken_Accounts
	foreign key (Account_Id) references Accounts(Username),
)

go

create table Categories(
	Id varchar(30) not null,
	Name nvarchar(40) not null,
	Icon varchar(40),
	primary key (Id)
)

go

create table Products(
	Id int identity(1,1),
	Name nvarchar(100) not null,
	Image1 varchar(50),
	Image2 varchar(50),
	Image3 varchar(50),
	Image4 varchar(50),
	Size int,
	Price decimal(12,2) not null,
	Description nvarchar(500),
	Stock int not null,
	Discount int,
	Category_Id varchar(30) not null,
	constraint FK_Products_Categories
	foreign key (Category_Id) references Categories(Id),
	primary key (Id)
)

go

create table Order_Status(
	Id varchar(30),
	Status_Name nvarchar(40),
	primary key(Id)
)

go

create table Orders(
	Id int identity(1,1),
	Username varchar(30) not null,
	constraint FK_Orders_Accounts
	foreign key (Username) references Accounts (Username),
	Fullname nvarchar(40),
	Create_Date date,
	order_status varchar(30),
	constraint FK_Orders_OrderStatus
	foreign key (order_status) references Order_Status (Id),
	Address nvarchar(100),
	Phone_Number varchar(10),
	description nvarchar(300),
	expected_date date,
	order_Time_Detail varchar(50),
	is_Paid int,
	Shipping_cost decimal(10,2),
	primary key (Id)
)

go

create table Orders_Detail(
	Id int identity(1,1),
	Order_Id int not null,
	constraint FK_OrdersDetail_Orders
	foreign key (Order_Id) references Orders (Id) on delete cascade,
	Product_Id int not null,
	constraint FK_OrdersDetail_Products
	foreign key (Product_Id) references Products (Id),
	Quantity int,
	Price decimal(12,2),
	primary key (Id)
)

go

insert into Roles(id, name)
	values('DIRECTOR', N'Giám đốc'),
		  ('ADMIN', N'Quản trị viên'),
		  ('USER', N'Người dùng')
	
insert into Accounts(username, password, email, fullname, address, phone, accountVerified, Activate_Code, Forgot_Password_Code, Avatar, Role_Id, Create_Date)
	values('director','$2a$12$.wLZ0l..lYWEsHpVL1skVO044449/.u0KGdcKgmoR.yJWwz4H.6AS','',N'Director',null,null, 1, null, null,'avatar1.png', 'DIRECTOR', null),
		  ('admin','$2a$12$9EuRbuiUGcYAoXZhwxF1kOnIbSzr0ESTk3chnCcXFiH/mwXag3.Oe','',N'Admin',null,null, 1, null, null,'avatar1.png', 'ADMIN', null),
		  ('thienan903','$2a$12$Yj44EdwxoCkuKkgCwoh5sufE444pZ5nlU3NPMMaY3BGp7bdLJ.YWC','thienandeptrai@gmail.com',N'Trịnh Hữu Thiện Ân',null,null, 1,'0123456789','0123456789','avatar1.png', 'ADMIN', null),
		  ('taipc','$2a$12$52w.qYtEmJIF9x28vEa/futvwTw3UOOPhCw2KAAxv9cNLk1h911w6','tai123456@gmail.com',N'Ngô Ngọc Tài',null,null, 1,'0123456789','0123456789','avatar1.png', 'ADMIN', null),
		  ('user1','$2a$12$paEAY3DXEYGSyEKvvNEN9eqXGC38wSnszMCCU.KohStqdi4U0z7vy','user_test@gmail.com',N'User Test 1',null,null, 1,'0123456789','0123456789','avatar1.png', 'USER', null),
		  ('user2','$2a$12$paEAY3DXEYGSyEKvvNEN9eqXGC38wSnszMCCU.KohStqdi4U0z7vy','user_test@gmail.com',N'User Test 1',null,null, 1,'0123456789','0123456789','avatar1.png', 'USER', null)
	
insert into Categories(id, name, icon)
	values('NIKE',N'NIKE','jacket.png'),
		  ('ADIDAS',N'ADIDAS','jacket1.png'),
		  ('JORDAN',N'JORDAN','jacket2.png'),
		  ('YEEZY',N'YEEZY','jacket3.png'),
		  ('OTHER BRANDS',N'OTHER BRANDS','jacket4.png')

		  
insert into Order_Status
	  values('ChoXacNhan', N'Chờ xác nhận'),
			('ChuanBi', N'Đang chuẩn bị hàng'),
			('XuatKho', N'Đã xuất kho'),
			('VanChuyen', N'Đang vận chuyển'),
			('DaGiaoHang', N'Đã giao hàng'),
			('HuyBo', N'Huỷ đơn') 
	
/*insert into Subcategories(id,name,Category_Id,icon)
	values
		-- jacket
		('JACKET1','Áo khoác nam','JACKET','jacket01.png'),
		('JACKET2','Áo khoác nữ','JACKET','jacket02.png')*/

insert into Products(name, Image1, Image2, Image3, Image4, size, price, description, stock, discount, category_id)
	values
		(N'NIKE AIR FORCE 1','airforce1_1.jpg','airforce1_2.jpg','airforce1_3.jpg','airforce1_4.jpg', 41, 3300000,N'Laptop gaming tốt nhất phân khúc',100,10,'NIKE'),
		(N'NIKE DOWNSHIFTER 12','nike_down2_1.jpg','nike_down2_2.jpg','nike_down2_3.jpg','nike_down2_4.jpg', 40, 2200000,N'Giày đẹp chất lượng cao',100,7,'NIKE'),
		(N'NIKE PEGASUS 40','nike_pegasus3_1.jpg','nike_pegasus3_2.jpg','nike_pegasus3_3.jpg','nike_pegasus3_4.jpg', 41, 1900000,N'Giày Nike Pegasus đẹp chất lượng cao',100,8,'NIKE'),
		(N'NIKE FORCE 1 SHADOW MULTICOLOR','nike_multicolor4_1.jpg','nike_multicolor4_2.jpg','nike_multicolor4_3.jpg','nike_pegasus3_4.jpg', 39, 3900000,N'Giày Nike Pegasus đẹp chất lượng cao',100,8,'NIKE'),
		(N'NIKE AIR MAX EXCEE','nike_maxexcee5_1.jpg','nike_maxexcee5_2.jpg','nike_maxexcee5_3.jpg','nike_maxexcee5_4.jpg', 40, 3200000,N'Giày Nike Pegasus đẹp chất lượng cao',100,10,'NIKE'),
		(N'ADIDAS FORUM LOW CL','adidas_low1_1.jpg','adidas_low1_2.jpg','adidas_low1_3.jpg','adidas_low1_4.jpg', 38, 3200000,N'Giày Nike Pegasus đẹp chất lượng cao',100,10,'ADIDAS'),
		(N'ADIDAS ULTRA4D SUN DEVILS','adidas_sun2_1.jpg','adidas_sun2_2.jpg','adidas_sun2_3.jpg','adidas_sun2_4.jpg', 44, 4800000,N'Giày ADIDAS Pegasus đẹp chất lượng cao',100,8,'ADIDAS'),
		(N'ADIDAS GRAND COURT','adidas_grand3_1.jpg','adidas_grand3_2.jpg','adidas_grand3_3.jpg','adidas_grand3_4.jpg', 40, 2300000,N'Giày ADIDAS Pegasus đẹp chất lượng cao',100,10,'ADIDAS'),
		(N'ADIDAS NY90','adidas_ny4_1.jpg','adidas_ny4_2.jpg','adidas_ny4_3.jpg','adidas_ny4_4.jpg', 38, 1900000,N'Giày ADIDAS Pegasus đẹp chất lượng cao',100,8,'ADIDAS'),
		(N'ADIDAS SAMBA CLASSIC','adidas_samba5_1.jpg','adidas_samba5_2.jpg','adidas_samba5_3.jpg','adidas_samba5_4.jpg', 40, 5800000,N'Giày ADIDAS Pegasus đẹp chất lượng cao',100,7,'ADIDAS'),
		(N'JORDAN 1 MID SE','jordan_mid1_1.jpg','jordan_mid1_2.jpg','jordan_mid1_3.jpg','jordan_mid1_4.jpg', 41, 7300000,N'Giày JORDAN Pegasus đẹp chất lượng cao',100,10,'JORDAN'),
		(N'JORDAN 1 LOW SE','jordan_low2_1.jpg','jordan_low2_2.jpg','jordan_low2_3.jpg','jordan_low2_4.jpg', 40, 4350000,N'Giày JORDAN Pegasus đẹp chất lượng cao',100,10,'JORDAN'),
		(N'JORDAN 1 MID OBSIDIAN','jordan_ob3_1.jpg','jordan_ob3_2.jpg','jordan_ob3_3.jpg','jordan_ob3_4.jpg', 39, 5800000,N'Giày JORDAN Pegasus đẹp chất lượng cao',100,10,'JORDAN'),
		(N'JORDAN AIR 200E','jordan_200E4_1.jpg','jordan_200E4_2.jpg','jordan_200E4_3.jpg','jordan_200E4_4.jpg', 44, 3900000,N'Giày JORDAN Pegasus đẹp chất lượng cao',100,10,'JORDAN'),
		(N'JORDAN 1 MID CARBON FIBER','jordan_carbon5_1.jpg','jordan_carbon5_2.jpg','jordan_carbon5_3.jpg','jordan_carbon5_4.jpg', 42, 7200000,N'Giày JORDAN Pegasus đẹp chất lượng cao',100,10,'JORDAN'),
		(N'YEEZY BOOST 350 V2 ONYX','yz1_1.jpg','yz1_2.jpg','yz1_3.jpg','yz1_4.jpg', 44, 14000000,N'Giày YEEZY Pegasus đẹp chất lượng cao',100,10,'YEEZY'),
		(N'YEEZY BOOST 350 V2 BONE','yz2_1.jpg','yz2_2.jpg','yz2_3.jpg','yz2_4.jpg', 42, 11000000,N'Giày YEEZY Pegasus đẹp chất lượng cao',100,8,'YEEZY'),
		(N'YEEZY BOOST 350 V2','yz3_1.jpg','yz3_2.jpg','yz3_3.jpg','yz3_4.jpg', 43, 8500000,N'Giày YEEZY Pegasus đẹp chất lượng cao',100,10,'YEEZY'),
		(N'YEEZY BOOST 350 V2 BLUE TINT','yz4_1.jpg','yz4_2.jpg','yz4_3.jpg','yz4_4.jpg', 45, 9500000,N'Giày YEEZY Pegasus đẹp chất lượng cao',100,5,'YEEZY'),
		(N'YEEZY BOOST 350 V2 YEEZREEL','yz5_1.jpg','yz5_2.jpg','yz5_3.jpg','yz5_4.jpg', 41, 8500000,N'Giày YEEZY Pegasus đẹp chất lượng cao',100,10,'YEEZY'),
		(N'PUMA SUPER LIGA','puma1_1.jpg','puma1_2.jpg','puma1_3.jpg','puma1_4.jpg', 39, 1200000,N'Giày PUMA Pegasus đẹp chất lượng cao',100,10,'YEEZY'),
		(N'DÉP JORDAN PLAY SLIDE','dep2_1.jpg','dep2_2.jpg','dep2_3.jpg','dep2_4.jpg', 42, 1600000,N'Giày GUCCI Pegasus đẹp chất lượng cao',100,10,'YEEZY'),
		(N'GUCCI LOGO CTR SNKR','gucci3_1.jpg','gucci3_2.jpg','gucci3_3.jpg','gucci3_4.jpg', 40, 13000000,N'Giày GUCCI Pegasus đẹp chất lượng cao',100,8,'YEEZY'),
		(N'GUCCI RYTHON CAT LOGO','gucci4_1.jpg','gucci4_2.jpg','gucci4_3.jpg','gucci4_4.jpg', 41, 700000,N'Giày GUCCI Pegasus đẹp chất lượng cao',100,5,'YEEZY'),
		(N'CREP MARK ON | BÚT TÔ ĐẾ GIÀY','but5_1.jpg','but5_2.jpg','but5_3.jpg','but5_4.jpg', 42, 5600000,N'BÚT TÔ ĐẾ GIÀY đẹp chất lượng cao',100,10,'YEEZY')

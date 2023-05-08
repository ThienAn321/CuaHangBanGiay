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
	Name nvarchar(20) not null,
	primary key (Id)
)

go

create table Users(
	Username varchar(30) not null,
	Password varchar(30),
	Email varchar(40) not null,
	Fullname nvarchar(40),
	Address nvarchar(200),
	Phone nvarchar(10),
	Introduce nvarchar(300),
	Status bit,
	Activate_Code varchar(40),
	Forgot_Password_Code varchar(40),
	Avatar varchar(40),
	primary key (Username),
	Role_Id varchar(10) not null,
	constraint FK_Users_Roles
	foreign key (Role_Id) references Roles(Id),
	Create_Date date
)

go

create table Categories(
	Id varchar(10) not null,
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
	Size int not null,
	Price decimal(12,2) not null,
	Description nvarchar(500),
	Stock int not null,
	Discount int,
	Category_Id varchar(10) not null,
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
	constraint FK_Orders_Users
	foreign key (Username) references Users (Username),
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
	values('ADMIN','Administrators'),
		  ('USER','Users')
	
insert into Users(username, password, email, fullname, address, phone, introduce, status, Activate_Code, Forgot_Password_Code, Avatar, Role_Id, Create_Date)
	values('admin','admin','thienandeptrai@gmail.com',N'Trịnh Hữu Thiện Ân',null,null,null,1,'0123456789','0123456789','avatar1.png', 'ADMIN', null)
	
insert into Categories(id, name, icon)
	values('NIKE',N'NIKE','jacket.png')

/*insert into Subcategories(id,name,Category_Id,icon)
	values
		-- jacket
		('JACKET1','Áo khoác nam','JACKET','jacket01.png'),
		('JACKET2','Áo khoác nữ','JACKET','jacket02.png')*/

insert into Products(name,Image1,Image2,Image3,Image4, size, price,description,stock,discount,category_id)
	values
		(N'AIR FORCE 1','airforce1_1.jpg','airforce1_2.jpg','airforce1_3.jpg','airforce1_4.jpg', 41, 3300000,N'Laptop gaming tốt nhất phân khúc',100,10,'NIKE')



/*insert into Order_Status
values
('ChoXacNhan',N'Chờ xác nhận'),
('ChuanBi',N'Đang chuẩn bị hàng'),
('XuatKho',N'Đã xuất kho'),
('VanChuyen',N'Đang vận chuyển'),
('DaGiaoHang',N'Đã giao hàng'),
('HuyBo',N'Huỷ đơn') */
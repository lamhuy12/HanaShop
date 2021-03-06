USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 1/20/2021 10:09:34 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\HanaShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [HanaShop] SET QUERY_STORE = OFF
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[FoodCategory]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodCategory](
	[cateID] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_FoodCategory] PRIMARY KEY CLUSTERED 
(
	[cateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FoodDetail]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodDetail](
	[foodID] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[createDate] [datetime] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[cateID] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_FoodDetail] PRIMARY KEY CLUSTERED 
(
	[foodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[History]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[Action] [nvarchar](100) NULL,
	[Time] [datetime] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderFoodsDetail]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderFoodsDetail](
	[OrderDetailID] [nvarchar](10) NOT NULL,
	[OrderID] [nvarchar](10) NOT NULL,
	[foodID] [nvarchar](10) NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [float] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [nvarchar](10) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[Total] [float] NULL,
	[DateOfCreate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/20/2021 10:09:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Role] [bit] NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[FoodCategory] ([cateID], [Name], [Description]) VALUES (N'C01', N'KFC', N'aaaa')
INSERT [dbo].[FoodCategory] ([cateID], [Name], [Description]) VALUES (N'C02', N'BBQ', N'bbbb')
INSERT [dbo].[FoodCategory] ([cateID], [Name], [Description]) VALUES (N'C03', N'Soup', N'ccccc')
GO
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'd', N'abc', N'Screenshot (371).png', N'da', 5, CAST(N'1900-01-01T16:50:00.000' AS DateTime), 5, 0, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F01	', N'dfgfth', N'3944544d74814a.jpeg', N'Zingers, medium popcorn', 749, CAST(N'2021-01-06T01:03:00.000' AS DateTime), 1221, 1, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F02', N'Ultimate saving bucket', N'download.jpg', N'6 hot wings', 599, CAST(N'2021-01-06T05:30:00.000' AS DateTime), 13, 0, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F03', N'KFC favorite', N'Ec-edOrXoAAXugr.jpg', N' 4 hot wings', 400, CAST(N'2021-01-06T04:00:00.000' AS DateTime), 5, 0, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F04', N'Snack	', N'images (1).jpg', N'Large fries', 199, CAST(N'2021-01-06T04:21:00.000' AS DateTime), 12, 1, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F05', N'Smoky red chicken', N'images (2).jpg', N'2 pc smokey red chicken', 150, CAST(N'2021-01-06T01:00:00.000' AS DateTime), 9, 1, N'C02')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F06', N'Potato Salad', N'images (3).jpg', N'1 poato salad', 144, CAST(N'2021-01-06T06:12:00.000' AS DateTime), 50, 1, N'C02')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F07', N'Watermelon	', N'images (4).jpg', N'3.5 pound', 50, CAST(N'2021-01-06T07:44:00.000' AS DateTime), 12, 0, N'C02')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F08', N'Berries	', N'images (5).jpg', N'3 pound', 40, CAST(N'2021-01-06T02:12:00.000' AS DateTime), 11, 1, N'C02')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F09', N'aa', N'images (6).jpg', N'a', 11, CAST(N'2021-01-06T03:21:00.000' AS DateTime), 4, 0, N'C03')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F10', N'b', N'images (7).jpg', N'b', 20, CAST(N'2021-01-06T02:14:00.000' AS DateTime), 6, 1, N'C03')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F11', N'c', N'images (8).jpg', N'c', 32, CAST(N'2021-01-06T06:40:00.000' AS DateTime), 8, 0, N'C03')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'F13', N'amb', N'images (9).jpg', N'aaaaaaa', 421, CAST(N'1970-01-01T07:00:00.000' AS DateTime), 421, 1, N'C01')
INSERT [dbo].[FoodDetail] ([foodID], [Name], [Image], [Description], [Price], [createDate], [Quantity], [Status], [cateID]) VALUES (N'f34', N'21', N'images.jpg', N'42', 421, CAST(N'1970-01-01T07:00:00.000' AS DateTime), 412, 1, N'C01')
GO
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'adminupdate foods', CAST(N'2021-01-20T14:47:01.630' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T18:23:30.237' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T18:23:47.523' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T20:54:41.280' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T20:54:46.067' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T20:55:10.290' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T22:08:03.993' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin delete F03', CAST(N'2021-01-20T22:08:11.283' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T20:58:47.707' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T20:58:52.680' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T21:30:07.443' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T21:30:09.347' AS DateTime))
INSERT [dbo].[History] ([Action], [Time]) VALUES (N'admin update foods', CAST(N'2021-01-20T21:30:11.187' AS DateTime))
GO
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-3-0', N'user-3', N'F01	', 2, 1498)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-3-1', N'user-3', N'F02', 1, 599)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-3-2', N'user-3', N'F04', 1, 199)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-3-3', N'user-3', N'F06', 1, 144)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-4-0', N'user-4', N'F01	', 3, 2247)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-4-1', N'user-4', N'F02', 2, 1198)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-5-0', N'user-5', N'F01	', 1, 749)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-5-1', N'user-5', N'F03', 12, 4800)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-5-2', N'user-5', N'F02', 1, 599)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-6-0', N'user-6', N'F03', 1, 400)
INSERT [dbo].[OrderFoodsDetail] ([OrderDetailID], [OrderID], [foodID], [Quantity], [Price]) VALUES (N'user-6-1', N'user-6', N'F04', 1, 199)
GO
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-user-1', N'user', 2149, CAST(N'2021-01-20T11:17:17.570' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-user-2', N'user', 2747, CAST(N'2021-01-20T11:33:20.920' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'user-3', N'user', 2440, CAST(N'2021-01-20T18:32:39.897' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'user-4', N'user', 3445, CAST(N'2021-01-20T18:34:19.390' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'user-5', N'user', 6148, CAST(N'2021-01-20T18:37:53.120' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'user-6', N'user', 599, CAST(N'2021-01-20T21:42:14.507' AS DateTime), 1)
GO
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role]) VALUES (N'admin', N'1', N'KHANH', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role]) VALUES (N'user', N'1', N'HUY', 0)
GO
ALTER TABLE [dbo].[FoodDetail]  WITH CHECK ADD  CONSTRAINT [FK_FoodDetail_FoodCategory] FOREIGN KEY([cateID])
REFERENCES [dbo].[FoodCategory] ([cateID])
GO
ALTER TABLE [dbo].[FoodDetail] CHECK CONSTRAINT [FK_FoodDetail_FoodCategory]
GO
ALTER TABLE [dbo].[OrderFoodsDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderFoodsDetail_FoodDetail] FOREIGN KEY([foodID])
REFERENCES [dbo].[FoodDetail] ([foodID])
GO
ALTER TABLE [dbo].[OrderFoodsDetail] CHECK CONSTRAINT [FK_OrderFoodsDetail_FoodDetail]
GO
ALTER TABLE [dbo].[OrderFoodsDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderFoodsDetail_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderFoodsDetail] CHECK CONSTRAINT [FK_OrderFoodsDetail_Orders]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Registration] FOREIGN KEY([Username])
REFERENCES [dbo].[Registration] ([Username])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Registration]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO

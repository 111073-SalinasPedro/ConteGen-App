USE [master]
GO
/****** Object:  Database [Contegen]    Script Date: 16/02/2022 21:05:27 ******/
CREATE DATABASE [Contegen]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Contegen', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Contegen.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Contegen_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Contegen_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Contegen] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Contegen].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Contegen] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Contegen] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Contegen] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Contegen] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Contegen] SET ARITHABORT OFF 
GO
ALTER DATABASE [Contegen] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Contegen] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Contegen] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Contegen] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Contegen] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Contegen] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Contegen] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Contegen] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Contegen] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Contegen] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Contegen] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Contegen] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Contegen] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Contegen] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Contegen] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Contegen] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Contegen] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Contegen] SET RECOVERY FULL 
GO
ALTER DATABASE [Contegen] SET  MULTI_USER 
GO
ALTER DATABASE [Contegen] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Contegen] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Contegen] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Contegen] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Contegen] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Contegen', N'ON'
GO
ALTER DATABASE [Contegen] SET QUERY_STORE = OFF
GO
USE [Contegen]
GO
/****** Object:  Table [dbo].[Camion]    Script Date: 16/02/2022 21:05:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Camion](
	[idCamion] [int] IDENTITY(1,1) NOT NULL,
	[Patente] [varchar](50) NULL,
	[idConductor] [int] NULL,
 CONSTRAINT [PK_Camiones] PRIMARY KEY CLUSTERED 
(
	[idCamion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[idCliente] [int] IDENTITY(1,1) NOT NULL,
	[nombreCliente] [varchar](50) NULL,
	[dniCliente] [int] NULL,
	[telefonoCliente] [int] NOT NULL,
	[habitual] [bit] NULL,
 CONSTRAINT [PK_Cliente] PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Conductor]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Conductor](
	[idConductor] [int] IDENTITY(1,1) NOT NULL,
	[nombreConductor] [varchar](50) NULL,
	[fechaNacimiento] [varchar](50) NULL,
	[dniConductor] [bigint] NULL,
	[telefonoConductor] [bigint] NULL,
 CONSTRAINT [PK_Conductor] PRIMARY KEY CLUSTERED 
(
	[idConductor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Contenedor]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contenedor](
	[idContenedor] [int] IDENTITY(1,1) NOT NULL,
	[codigoContenedor] [varchar](50) NULL,
	[disponible] [bit] NULL,
 CONSTRAINT [PK_Contenedores] PRIMARY KEY CLUSTERED 
(
	[idContenedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estado]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estado](
	[idEstado] [int] IDENTITY(1,1) NOT NULL,
	[estado] [varchar](50) NULL,
 CONSTRAINT [PK_Estado_1] PRIMARY KEY CLUSTERED 
(
	[idEstado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EstadoRemito]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EstadoRemito](
	[idEstadoRemito] [int] IDENTITY(1,1) NOT NULL,
	[estado] [varchar](50) NULL,
 CONSTRAINT [PK_EstadoRemito] PRIMARY KEY CLUSTERED 
(
	[idEstadoRemito] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Factura]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Factura](
	[idFactura] [int] IDENTITY(1,1) NOT NULL,
	[importe] [float] NULL,
	[idPedido] [int] NULL,
 CONSTRAINT [PK_Cobranza] PRIMARY KEY CLUSTERED 
(
	[idFactura] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FormaPago]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FormaPago](
	[idFormaPago] [int] NOT NULL,
	[formaPago] [varchar](50) NULL,
 CONSTRAINT [PK_FormaPago] PRIMARY KEY CLUSTERED 
(
	[idFormaPago] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pedido]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pedido](
	[idPedido] [int] IDENTITY(1,1) NOT NULL,
	[fechaEntrega] [date] NULL,
	[horario] [varchar](50) NULL,
	[observaciones] [varchar](50) NULL,
	[direccion] [varchar](50) NULL,
	[barrio] [varchar](50) NULL,
	[dias] [int] NULL,
	[contenedores] [int] NULL,
	[idZona] [int] NULL,
	[idEstado] [int] NULL,
	[idCliente] [int] NULL,
 CONSTRAINT [PK_Pedido] PRIMARY KEY CLUSTERED 
(
	[idPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Remito]    Script Date: 16/02/2022 21:05:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Remito](
	[idRemito] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [varchar](50) NULL,
	[idFormaPago] [int] NULL,
	[idContenedor] [int] NULL,
	[idEstadoRemito] [int] NULL,
	[idPedido] [int] NULL,
	[idCamion] [int] NULL,
 CONSTRAINT [PK_Remito] PRIMARY KEY CLUSTERED 
(
	[idRemito] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TMPFechas]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TMPFechas](
	[fecha1] [varchar](100) NULL,
	[fecha2] [varchar](100) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Zona]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Zona](
	[idZona] [int] IDENTITY(1,1) NOT NULL,
	[zona] [varchar](50) NULL,
 CONSTRAINT [PK_Zona] PRIMARY KEY CLUSTERED 
(
	[idZona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Camion] ON 

INSERT [dbo].[Camion] ([idCamion], [Patente], [idConductor]) VALUES (1, N'B215FR', 1)
INSERT [dbo].[Camion] ([idCamion], [Patente], [idConductor]) VALUES (2, N'B680LK', 2)
SET IDENTITY_INSERT [dbo].[Camion] OFF
SET IDENTITY_INSERT [dbo].[Cliente] ON 

INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (12, N'Luis Pavone', 35887652, 4783447, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (13, N'Vicente Heredia', 32554774, 4884567, 1)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (14, N'Miguel Nocchi', 29487687, 154472207, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (15, N'Pablo Armesto', 39774100, 4783310, 1)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (16, N'Cristian Ocaño', 24740877, 4200147, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (17, N'Camilo Torres', 30444956, 4667888, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (18, N'Eduardo Martinez', 20887414, 159777322, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (1018, N'Susana Quiroga', 28741557, 5638991, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (1019, N'Rafael Beltran', 33478002, 158322510, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (1021, N'Eduardo Monzon', 35620141, 152041480, 0)
INSERT [dbo].[Cliente] ([idCliente], [nombreCliente], [dniCliente], [telefonoCliente], [habitual]) VALUES (1022, N'Cristian Rosales', 39654226, 156366474, 0)
SET IDENTITY_INSERT [dbo].[Cliente] OFF
SET IDENTITY_INSERT [dbo].[Conductor] ON 

INSERT [dbo].[Conductor] ([idConductor], [nombreConductor], [fechaNacimiento], [dniConductor], [telefonoConductor]) VALUES (1, N'Francisco Asis', N'15/04/82', 30001588, 155930447)
INSERT [dbo].[Conductor] ([idConductor], [nombreConductor], [fechaNacimiento], [dniConductor], [telefonoConductor]) VALUES (2, N'Adrian Barbero', N'06/08/89', 38744124, 152044789)
SET IDENTITY_INSERT [dbo].[Conductor] OFF
SET IDENTITY_INSERT [dbo].[Contenedor] ON 

INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (1, N'CTG01', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (2, N'CTG02', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (3, N'CTG03', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (4, N'CTG04', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (5, N'CTG05', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (6, N'CTG06', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (7, N'CTG07', 0)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (8, N'CTG08', 1)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (10, N'CTG09', 1)
INSERT [dbo].[Contenedor] ([idContenedor], [codigoContenedor], [disponible]) VALUES (11, N'CTG10', 0)
SET IDENTITY_INSERT [dbo].[Contenedor] OFF
SET IDENTITY_INSERT [dbo].[Estado] ON 

INSERT [dbo].[Estado] ([idEstado], [estado]) VALUES (1, N'Pedido')
INSERT [dbo].[Estado] ([idEstado], [estado]) VALUES (2, N'Entregado')
INSERT [dbo].[Estado] ([idEstado], [estado]) VALUES (3, N'Recambio')
INSERT [dbo].[Estado] ([idEstado], [estado]) VALUES (4, N'Finalizado')
SET IDENTITY_INSERT [dbo].[Estado] OFF
SET IDENTITY_INSERT [dbo].[EstadoRemito] ON 

INSERT [dbo].[EstadoRemito] ([idEstadoRemito], [estado]) VALUES (1, N'Pagado')
INSERT [dbo].[EstadoRemito] ([idEstadoRemito], [estado]) VALUES (2, N'Debe')
SET IDENTITY_INSERT [dbo].[EstadoRemito] OFF
SET IDENTITY_INSERT [dbo].[Factura] ON 

INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (8, 18000, 2)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (9, 6000, 3)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (10, 6000, 4)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (11, 3000, 17)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (12, 18000, 18)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (13, 3000, 19)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (14, 3000, 20)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (15, 6000, 21)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (16, 6000, 21)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (17, 0, 26)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (18, 0, 25)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (19, 12000, 31)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1014, 9000, 1031)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1015, 9000, 1031)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1016, 9000, 1031)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1017, 9000, 1031)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1018, 12000, 31)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1019, 18000, 27)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1020, 9000, 1031)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1021, 9000, 1033)
INSERT [dbo].[Factura] ([idFactura], [importe], [idPedido]) VALUES (1025, 9000, 1037)
SET IDENTITY_INSERT [dbo].[Factura] OFF
INSERT [dbo].[FormaPago] ([idFormaPago], [formaPago]) VALUES (1, N'Tarjeta Debito')
INSERT [dbo].[FormaPago] ([idFormaPago], [formaPago]) VALUES (2, N'Tarjeta Credito')
INSERT [dbo].[FormaPago] ([idFormaPago], [formaPago]) VALUES (3, N'Contado')
INSERT [dbo].[FormaPago] ([idFormaPago], [formaPago]) VALUES (4, N'Transferencia')
INSERT [dbo].[FormaPago] ([idFormaPago], [formaPago]) VALUES (5, N'Cheque')
SET IDENTITY_INSERT [dbo].[Pedido] ON 

INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (2, CAST(N'2021-03-09' AS Date), N'10:00 - 12:00', NULL, N'Belgrano 1178', N'Guemes', 2, 3, 5, 4, 13)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (3, CAST(N'2021-02-14' AS Date), N'10:00 - 12:00', NULL, N'Luis Agote 2226', N'Los Naranjos', 1, 2, 4, 4, 12)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (4, CAST(N'2021-02-22' AS Date), N'08:00 - 10:00', N'baldio', N'Roque Arias 2388', N'Barrio Flores', 1, 2, 4, 4, 14)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (17, CAST(N'2021-03-10' AS Date), N'10:00 - 12:00', N'', N'Asturias 1947', N'Barrio Colon', 1, 2, 3, 4, 15)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (18, CAST(N'2021-03-11' AS Date), N'08:00 - 10:00', N'', N'Diagonal Ica 414', N'Crisol', 2, 3, 1, 4, 16)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (19, CAST(N'2021-02-15' AS Date), N'10:00 - 12:00', N'', N'Rio Negro 3800', N'Barrio Smata', 1, 1, 2, 1, 17)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (20, CAST(N'2020-12-07' AS Date), N'08:00 - 10:00', N'', N'Luis Agote 2226', N'Los Naranjos', 1, 1, 4, 1, 12)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (21, CAST(N'2020-09-01' AS Date), N'10:00 - 12:00', N'', N'Belgrano 1178', N'Guemes', 1, 2, 5, 1, 13)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (23, CAST(N'2020-10-05' AS Date), N'08:00 - 10:00', N'', N'Roque Arias 2388 ', N'Barrio Flores', 1, 1, 1, 1, 14)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (24, CAST(N'2020-12-14' AS Date), N'08:00 - 10:00', N'', N'Luis Agote 2226', N'Los Naranjos', 1, 1, 4, 1, 12)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (25, CAST(N'2020-08-04' AS Date), N'14:00 - 16:00', N'', N'Asturias 1947', N'Barrio Colon', 1, 2, 3, 1, 15)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (26, CAST(N'2021-02-09' AS Date), N'08:00 - 10:00', N'', N'Diagonal Ica 414', N'Crisol', 1, 1, 1, 1, 16)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (27, CAST(N'2020-11-11' AS Date), N'08:00 - 10:00', N'', N'Rio Negro 3800', N'Barrio Smata', 2, 3, 2, 1, 17)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (28, CAST(N'2021-03-01' AS Date), N'08:00 - 10:00', N'', N'Luis Agote 2226', N'Los Naranjos', 1, 1, 4, 1, 12)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (29, CAST(N'2021-01-04' AS Date), N'08:00 - 10:00', N'', N'La Rioja 4654', N'Villa Urquiza', 1, 1, 1, 1, 18)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (30, CAST(N'2020-07-21' AS Date), N'10:00 - 12:00', N'', N'La Rioja 4654', N'Villa Urquiza', 1, 1, 1, 1, 18)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (31, CAST(N'2021-08-26' AS Date), N'10:00 - 12:00', N'', N'roma 123', N'gral paz', 4, 1, 5, 1, 17)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (1031, CAST(N'2021-12-02' AS Date), N'10:00 - 12:00', N'', N'Javier Diaz 1538', N'Jardin Espinosa', 3, 1, 2, 1, 1018)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (1033, CAST(N'2021-12-14' AS Date), N'10:00 - 12:00', N'', N'Santa Rosa 1110', N'Alberdi', 3, 1, 5, 1, 1019)
INSERT [dbo].[Pedido] ([idPedido], [fechaEntrega], [horario], [observaciones], [direccion], [barrio], [dias], [contenedores], [idZona], [idEstado], [idCliente]) VALUES (1037, CAST(N'2022-02-16' AS Date), N'10:00 - 12:00', N'', N'Mendoza 250', N'Alberdi', 3, 1, 5, 1, 1022)
SET IDENTITY_INSERT [dbo].[Pedido] OFF
SET IDENTITY_INSERT [dbo].[Remito] ON 

INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (9, N'2021-03-09', 5, 7, 1, 2, 1)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (11, N'2021-02-14', 4, 1, 1, 3, 1)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (13, N'2021-03-10', 2, 4, 1, 17, 2)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (14, N'2021-03-11', 5, 1, 1, 18, 2)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (15, N'2021-02-15', 3, 1, 1, 19, 1)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (16, N'2020-12-07', 1, 5, 1, 20, 2)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (1022, N'2020-11-11', 3, 2, 1, 27, 2)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (1024, N'2021-12-14', 2, 4, 1, 1033, 1)
INSERT [dbo].[Remito] ([idRemito], [fecha], [idFormaPago], [idContenedor], [idEstadoRemito], [idPedido], [idCamion]) VALUES (1028, N'2022-02-16', 4, 6, 1, 1037, 1)
SET IDENTITY_INSERT [dbo].[Remito] OFF
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-01-01', N'2021-06-01')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-01-01', N'2021-02-01')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'1-1-2021', N'1-7-2021')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-7-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-8-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-0-1', N'2021-7-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-8-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2020-1-1', N'2020-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-2-1', N'2021-6-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-2-1', N'2021-6-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-2-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-10-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-10-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-10-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-11-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-3-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-5-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2020-1-1', N'2020-5-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-8-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-3-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2022-2-14', N'2022-2-14')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2022-2-16', N'2022-2-16')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-12-1', N'2021-12-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-2-1', N'2021-3-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-6-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-9-1', N'2021-5-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-10-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-10-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-9-1', N'2021-9-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2022-2-14', N'2022-2-14')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2020-1-1', N'2020-12-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-8-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2021-8-31')
INSERT [dbo].[TMPFechas] ([fecha1], [fecha2]) VALUES (N'2021-1-1', N'2022-1-1')
SET IDENTITY_INSERT [dbo].[Zona] ON 

INSERT [dbo].[Zona] ([idZona], [zona]) VALUES (1, N'Norte')
INSERT [dbo].[Zona] ([idZona], [zona]) VALUES (2, N'Sur')
INSERT [dbo].[Zona] ([idZona], [zona]) VALUES (3, N'Este')
INSERT [dbo].[Zona] ([idZona], [zona]) VALUES (4, N'Oeste')
INSERT [dbo].[Zona] ([idZona], [zona]) VALUES (5, N'Centro')
SET IDENTITY_INSERT [dbo].[Zona] OFF
ALTER TABLE [dbo].[Camion]  WITH CHECK ADD  CONSTRAINT [FK_Camion_Conductor] FOREIGN KEY([idConductor])
REFERENCES [dbo].[Conductor] ([idConductor])
GO
ALTER TABLE [dbo].[Camion] CHECK CONSTRAINT [FK_Camion_Conductor]
GO
ALTER TABLE [dbo].[Factura]  WITH CHECK ADD  CONSTRAINT [FK_Factura_Pedido1] FOREIGN KEY([idPedido])
REFERENCES [dbo].[Pedido] ([idPedido])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Factura] CHECK CONSTRAINT [FK_Factura_Pedido1]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_Cliente1] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Cliente] ([idCliente])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_Cliente1]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_Estado] FOREIGN KEY([idEstado])
REFERENCES [dbo].[Estado] ([idEstado])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_Estado]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_Zona1] FOREIGN KEY([idZona])
REFERENCES [dbo].[Zona] ([idZona])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_Zona1]
GO
ALTER TABLE [dbo].[Remito]  WITH CHECK ADD  CONSTRAINT [FK_Remito_Camion] FOREIGN KEY([idCamion])
REFERENCES [dbo].[Camion] ([idCamion])
GO
ALTER TABLE [dbo].[Remito] CHECK CONSTRAINT [FK_Remito_Camion]
GO
ALTER TABLE [dbo].[Remito]  WITH CHECK ADD  CONSTRAINT [FK_Remito_Contenedor] FOREIGN KEY([idContenedor])
REFERENCES [dbo].[Contenedor] ([idContenedor])
GO
ALTER TABLE [dbo].[Remito] CHECK CONSTRAINT [FK_Remito_Contenedor]
GO
ALTER TABLE [dbo].[Remito]  WITH CHECK ADD  CONSTRAINT [FK_Remito_EstadoRemito] FOREIGN KEY([idEstadoRemito])
REFERENCES [dbo].[EstadoRemito] ([idEstadoRemito])
GO
ALTER TABLE [dbo].[Remito] CHECK CONSTRAINT [FK_Remito_EstadoRemito]
GO
ALTER TABLE [dbo].[Remito]  WITH CHECK ADD  CONSTRAINT [FK_Remito_FormaPago] FOREIGN KEY([idFormaPago])
REFERENCES [dbo].[FormaPago] ([idFormaPago])
GO
ALTER TABLE [dbo].[Remito] CHECK CONSTRAINT [FK_Remito_FormaPago]
GO
ALTER TABLE [dbo].[Remito]  WITH CHECK ADD  CONSTRAINT [FK_Remito_Pedido] FOREIGN KEY([idPedido])
REFERENCES [dbo].[Pedido] ([idPedido])
GO
ALTER TABLE [dbo].[Remito] CHECK CONSTRAINT [FK_Remito_Pedido]
GO
/****** Object:  StoredProcedure [dbo].[BuscarPedido]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[BuscarPedido](@idPedido int)
as 
begin
	SELECT p.idPedido,nombreCliente,direccion,barrio,zona,fechaEntrega,horario,dias,contenedores,observaciones,estado
	FROM Pedido p
	JOIN Cliente c ON c.idCliente=p.idCliente
	JOIN Zona z ON z.idZona=p.idZona
	JOIN Estado e ON e.idEstado=p.idEstado
	WHERE p.idPedido = @idPedido
end
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_ListadoPedido]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_ListadoPedido]
(
@fechaDesde varchar(100), 
@fechaHasta varchar(100)
)
AS
BEGIN
Insert into TMPFechas VALUES (@fechaDesde,@fechaHasta)
SELECT p.idPedido,nombreCliente,direccion,barrio,zona,fechaEntrega,horario,dias,contenedores,observaciones,estado
FROM Pedido p
JOIN Cliente c ON c.idCliente=p.idCliente
JOIN Zona z ON z.idZona=p.idZona
JOIN Estado e ON e.idEstado=p.idEstado
WHERE fechaEntrega BETWEEN @fechaDesde AND @fechaHasta
END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_ModificarPedido]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_ModificarPedido]
(
	@FechaDesde VARCHAR(100),  
	@FechaHasta VARCHAR(100)
)
AS
BEGIN
SELECT p.idPedido,direccion,barrio,zona,fechaEntrega,horario,dias,contenedores,observaciones,estado
FROM Pedido p
JOIN Zona z ON z.idZona=p.idZona
JOIN Estado e ON e.idEstado=p.idEstado
WHERE fechaEntrega BETWEEN @FechaDesde AND @FechaHasta
END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_ModificarRemito]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_ModificarRemito]
(
	@FechaDesde VARCHAR(100),  
	@FechaHasta VARCHAR(100)
)
AS
BEGIN
SELECT r.idRemito,fecha,formaPago,e.estado,codigoContenedor,dias,contenedores,t.estado,importe,idCamion,r.idPedido
FROM Remito r 
JOIN FormaPago f ON r.idFormaPago=f.idFormaPago
JOIN Contenedor c ON c.idContenedor=r.idContenedor
JOIN EstadoRemito e On e.idEstadoRemito=r.idEstadoRemito
JOIN Pedido p ON r.idPedido=p.idPedido
JOIN Estado t ON p.idEstado=t.idEstado
JOIN Factura fa ON p.idPedido=fa.idPedido
WHERE fechaEntrega BETWEEN @FechaDesde AND @FechaHasta
END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_PedidoPorCliente]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_PedidoPorCliente]
(
@FechaDesde VARCHAR(100),
@FechaHasta VARCHAR(100)
)
AS
Select nombreCliente,COUNT(*)
From Pedido p JOIN Cliente c ON c.idCliente=p.idCliente
WHERE fechaEntrega BETWEEN @FechaDesde AND @FechaHasta 
GROUP BY nombreCliente
ORDER BY 2 desc
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_PedidoPorMes]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_PedidoPorMes]
	-- Add the parameters for the stored procedure here
	@fechaDesde varchar(100),
	@fechaHasta varchar(100)
AS
BEGIN
	Select DISTINCT datename([MONTH],fechaEntrega) as m,count(*),MONTH(fechaEntrega) as [m_#]
from Pedido
WHERE fechaEntrega BETWEEN @fechaDesde AND @fechaHasta
Group by datename([MONTH],fechaEntrega),MONTH(fechaEntrega)
order by [m_#]

END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_PedidoPorZona]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_PedidoPorZona]
(
@fechaDesde VARCHAR(100),
@fechaHasta VARCHAR(100)
)
AS
BEGIN
	SELECT zona, COUNT(*)
    FROM Zona z JOIN Pedido p ON p.idZona=z.idZona
    WHERE fechaEntrega BETWEEN @fechaDesde AND @fechaHasta
    GROUP BY zona
END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_PedidoRemito]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_PedidoRemito](
	@fechaDesde VARCHAR(100), 
	@fechaHasta VARCHAR(100)
)
AS
BEGIN
	SELECT p.idPedido,fechaEntrega,nombreCliente,direccion,contenedores,dias
            FROM Pedido p JOIN Cliente c ON  p.idCliente=c.idCliente
			WHERE fechaEntrega BETWEEN @fechaDesde AND @fechaHasta
			ORDER BY 1 desc
END
GO
/****** Object:  StoredProcedure [dbo].[SP_EntreFecha_Remito]    Script Date: 16/02/2022 21:05:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EntreFecha_Remito] (
	@fechaDesde Varchar(100),
	@fechaHasta varchar(100)
	)
AS
BEGIN
	SELECT r.idRemito,fecha,formaPago,estado,codigoContenedor,dias,contenedores,importe,r.idPedido,idCamion
FROM Remito r 
JOIN FormaPago f ON r.idFormaPago=f.idFormaPago
 JOIN Contenedor c ON c.idContenedor=r.idContenedor
 JOIN EstadoRemito e On e.idEstadoRemito=r.idEstadoRemito
 JOIN Pedido p ON r.idPedido= p.idPedido
 JOIN Factura fa ON p.idPedido=fa.idPedido
 WHERE fechaEntrega BETWEEN @fechaDesde AND @fechaHasta
END

GO
USE [master]
GO
ALTER DATABASE [Contegen] SET  READ_WRITE 
GO

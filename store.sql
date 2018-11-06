-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 06, 2018 lúc 10:41 AM
-- Phiên bản máy phục vụ: 10.1.34-MariaDB
-- Phiên bản PHP: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `store`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contract`
--

CREATE TABLE `contract` (
  `contractId` int(11) NOT NULL,
  `employeeId` int(11) NOT NULL,
  `date` date NOT NULL,
  `customerId` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `name` char(30) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Gender` char(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `adress` char(250) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `phoneNumber` char(15) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`customerId`, `name`, `Gender`, `DateOfBirth`, `adress`, `phoneNumber`) VALUES
(16, 'Kiều Phong', 'Nam', '1938-01-01', 'Không', 'Không'),
(19, 'Mộ Dung Phục', 'Nam', '1956-12-09', 'Yến Tử Ổ', 'Không'),
(26, 'Yên Long Uyên', 'Nam', '1950-09-10', 'Không', '016245874564'),
(27, 'Đoàn Dự', 'Nam', '1945-06-26', 'Đại Lý', '01365484'),
(28, 'Hư Trúc', 'Nam', '1942-01-01', 'Không Biết', 'Không');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detailcontract`
--

CREATE TABLE `detailcontract` (
  `contractId` int(11) NOT NULL,
  `itemId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `saleOff` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL,
  `name` char(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `gender` char(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `adress` char(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Position` char(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `DateOfBegin` date NOT NULL,
  `EmployeeImageURL` char(255) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`employeeId`, `name`, `gender`, `DateOfBirth`, `adress`, `Position`, `DateOfBegin`, `EmployeeImageURL`) VALUES
(9, 'Stephan Stranger', 'Nam', '1938-01-01', 'London', 'Quản Lí', '1938-01-01', 'C:\\Users\\HONGHA\\workspace\\MyProject\\EmployeeImage\\maxresdefault (1).jpg'),
(13, 'HR', 'Nam', '1940-02-24', 'Earth Two', 'Quản Lí', '1942-01-02', 'C:\\Users\\HONGHA\\workspace\\MyProject\\EmployeeImage\\Harrison_Wells_(Earth_Two).png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item`
--

CREATE TABLE `item` (
  `itemId` int(11) NOT NULL,
  `Name` char(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `sellPrice` int(11) NOT NULL,
  `importPrice` int(11) NOT NULL,
  `imageItemURL` char(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `note` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `item`
--

INSERT INTO `item` (`itemId`, `Name`, `quantity`, `sellPrice`, `importPrice`, `imageItemURL`, `note`) VALUES
(13, 'Luxana', 10, 3265, 12550, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\Lux.jpeg', 'Lux Thập đại nguyên tố '),
(14, 'Ezreal Vệ Binh Tinh Tú', 1000, 1000, 9999, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\Ezreal_StarGuardianSkin.jpg', ''),
(18, 'Nami Koi', 1369, 1345, 7899, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\Nami_KoiSkin.jpg', 'Nami Skins'),
(20, 'Thresh Champion', 13285, 2341, 16511, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\2.jpg', 'Champion Skin'),
(21, 'Master Yi Star Guardian', 1246, 16564, 116584, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\61cb8a9b-d4e7-44f5-9ec4-ed1018d6f3b9.jpg', 'võ sư Wuju'),
(22, 'Fnatic', 4465, 413689, 1238, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\fnatic_splash.jpg', 'World champion'),
(23, 'Zombie Slayer', 1561, 1561231, 166817, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\splash-1920x1080.jpg', 'Leaugue Of Legends'),
(25, 'Varus Conquistador', 12345, 631545, 45463, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\110007.jpg', 'Varus chinh phục'),
(26, 'Project Yasuo', 1000, 1000000, 1000, 'C:\\Users\\HONGHA\\workspace\\MyProject\\itemImage\\Yasuo_Splash_0.jpg', 'Đấng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` char(16) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` char(16) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employeeId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `employeeId`) VALUES
(4, 'user', 'user', 9);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`contractId`,`employeeId`,`customerId`),
  ADD KEY `employeeId` (`employeeId`),
  ADD KEY `customerId` (`customerId`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`);

--
-- Chỉ mục cho bảng `detailcontract`
--
ALTER TABLE `detailcontract`
  ADD PRIMARY KEY (`contractId`,`itemId`),
  ADD KEY `itemId` (`itemId`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`);

--
-- Chỉ mục cho bảng `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`itemId`);

--
-- Chỉ mục cho bảng `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`,`employeeId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `contract`
--
ALTER TABLE `contract`
  MODIFY `contractId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `item`
--
ALTER TABLE `item`
  MODIFY `itemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `contract_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`),
  ADD CONSTRAINT `contract_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  ADD CONSTRAINT `contract_ibfk_3` FOREIGN KEY (`contractId`) REFERENCES `detailcontract` (`contractId`);

--
-- Các ràng buộc cho bảng `detailcontract`
--
ALTER TABLE `detailcontract`
  ADD CONSTRAINT `detailcontract_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`);

--
-- Các ràng buộc cho bảng `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

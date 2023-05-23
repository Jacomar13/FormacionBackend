-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-05-2023 a las 11:40:09
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cafeteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alergenos`
--

CREATE TABLE `alergenos` (
  `nombre_alergeno` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alergenos`
--

INSERT INTO `alergenos` (`nombre_alergeno`) VALUES
('Altramuces'),
('Apio'),
('Cacahuetes'),
('Crustáceos'),
('Frutos con cáscara'),
('Gluten'),
('Huevos'),
('Lácteos'),
('Moluscos'),
('Mostaza'),
('Pescado'),
('Sésamo'),
('Soja'),
('Sulfitos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `correo` varchar(40) NOT NULL,
  `nombre_cliente` varchar(30) NOT NULL,
  `apellido1` varchar(20) NOT NULL,
  `apellido2` varchar(20) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `tipo_usuario` varchar(20) NOT NULL DEFAULT 'Cliente',
  `activado` enum('SI','NO') DEFAULT 'NO',
  `penalizacion` int(2) DEFAULT 0,
  `fecha_creacion` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_activacion` datetime DEFAULT NULL,
  `fecha_penalizacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`correo`, `nombre_cliente`, `apellido1`, `apellido2`, `contraseña`, `tipo_usuario`, `activado`, `penalizacion`, `fecha_creacion`, `fecha_activacion`, `fecha_penalizacion`) VALUES
('admin@g.educaand.es', 'admin', 'admin', 'admin', 'admin', 'Administrador', 'SI', 0, '2023-03-07 09:06:26', NULL, NULL),
('apinmes1211@g.educaand.es', 'Alejandro', 'Piñer', 'Mesa', '12345678', 'Cliente', 'SI', 1, '2023-03-17 15:03:29', '2023-03-20 13:11:09', '2023-03-20'),
('dueño@g.educaand.es', 'dueño', 'dueño', 'dueño', 'dueño', 'Dueño', 'SI', 0, '2023-03-07 09:06:26', NULL, NULL),
('esther@g.educaand.es', 'Esther', 'Mesa', 'Pérez', 'asdfg', 'Cliente', 'SI', 2, '2023-03-14 12:20:26', '2023-03-14 12:21:24', '2023-03-14'),
('miguel@g.educaand.es', 'Miguel Ángel', 'Martos', 'Alcaraz', '12345678', 'Cliente', 'SI', 1, '2023-03-09 00:01:17', '2023-03-08 11:15:09', '2023-04-14'),
('nesprom@g.educaand.es', 'Nerea', 'Espinosa', 'Romero', 'nerea', 'Cliente', 'SI', 3, '2023-03-07 09:06:26', NULL, NULL),
('samu@g.educaand.es', 'Samuel', 'Pérez', 'Castillo', '12345678', 'Cliente', 'SI', 8, '2023-03-07 10:10:36', NULL, '2023-03-20');

--
-- Disparadores `clientes`
--
DELIMITER $$
CREATE TRIGGER `activacion_cliente` BEFORE UPDATE ON `clientes` FOR EACH ROW BEGIN
  IF NEW.activado = 'SI' AND OLD.activado = 'NO' THEN
    SET NEW.fecha_activacion = NOW();
  END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_correo` BEFORE INSERT ON `clientes` FOR EACH ROW BEGIN
  IF NEW.correo NOT LIKE '%@g.educaand.es' THEN
    SIGNAL SQLSTATE '45000' 
      SET MESSAGE_TEXT = 'El correo electrónico debe terminar en @g.educaand.es';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(4) NOT NULL,
  `correo_cliente` varchar(40) NOT NULL,
  `fecha_realizacion` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_recogida` date NOT NULL,
  `hora_recogida` time NOT NULL,
  `precio_pedido` float NOT NULL,
  `preparacion` enum('SI','NO') DEFAULT 'NO',
  `recogido` enum('SI','NO') DEFAULT 'NO',
  `listo` enum('SI','NO') DEFAULT 'NO',
  `penalizado` enum('SI','NO') DEFAULT 'NO',
  `fecha_listo` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `correo_cliente`, `fecha_realizacion`, `fecha_recogida`, `hora_recogida`, `precio_pedido`, `preparacion`, `recogido`, `listo`, `penalizado`, `fecha_listo`) VALUES
(26, 'apinmes1211@g.educaand.es', '2023-03-17 15:06:29', '2023-03-17', '10:00:00', 3.5, 'NO', 'NO', 'SI', 'SI', '2023-03-17 15:07:48'),
(28, 'samu@g.educaand.es', '2023-03-17 09:55:19', '0000-00-00', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(29, 'samu@g.educaand.es', '2023-03-20 11:48:28', '0000-00-00', '12:15:00', 3.5, 'NO', 'NO', 'NO', 'SI', NULL),
(30, 'samu@g.educaand.es', '2023-03-20 11:56:36', '0000-00-00', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(31, 'samu@g.educaand.es', '2023-03-20 11:57:42', '0000-00-00', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'SI', NULL),
(32, 'samu@g.educaand.es', '2023-03-20 11:59:08', '0000-00-00', '12:15:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(33, 'samu@g.educaand.es', '2023-03-20 12:00:33', '0000-00-00', '12:15:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(34, 'samu@g.educaand.es', '2023-03-20 12:08:04', '0000-00-00', '12:15:00', 4, 'NO', 'NO', 'NO', 'NO', NULL),
(35, 'samu@g.educaand.es', '2023-03-20 12:14:46', '0000-00-00', '12:15:00', 4, 'NO', 'NO', 'NO', 'SI', NULL),
(36, 'samu@g.educaand.es', '2023-03-20 12:22:24', '0000-00-00', '16:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(37, 'samu@g.educaand.es', '2023-03-20 12:24:52', '0000-00-00', '15:00:00', 4, 'NO', 'NO', 'NO', 'SI', NULL),
(38, 'samu@g.educaand.es', '2023-03-20 12:32:55', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(39, 'samu@g.educaand.es', '2023-03-20 12:33:43', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(40, 'samu@g.educaand.es', '2023-03-20 12:35:13', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(41, 'samu@g.educaand.es', '2023-03-20 12:41:17', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(42, 'samu@g.educaand.es', '2023-03-20 12:41:43', '0000-00-00', '17:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(43, 'samu@g.educaand.es', '2023-03-20 12:42:01', '0000-00-00', '17:00:00', 1.5, 'NO', 'NO', 'NO', 'SI', NULL),
(44, 'samu@g.educaand.es', '2023-03-20 12:42:30', '0000-00-00', '15:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(45, 'samu@g.educaand.es', '2023-03-20 12:42:45', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'SI', NULL),
(46, 'samu@g.educaand.es', '2023-03-20 13:08:44', '2023-03-20', '11:00:00', 3.5, 'NO', 'NO', 'NO', 'SI', NULL),
(47, 'samu@g.educaand.es', '2023-03-20 13:09:41', '0000-00-00', '15:00:00', 1.5, 'NO', 'NO', 'NO', 'SI', NULL),
(48, 'apinmes1211@g.educaand.es', '2023-04-12 16:25:17', '2023-04-12', '12:15:00', 3.5, 'NO', 'SI', 'NO', 'NO', '2023-04-12 16:32:34'),
(49, 'apinmes1211@g.educaand.es', '2023-04-12 16:27:21', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'SI', 'NO', '2023-04-12 17:11:16'),
(50, 'apinmes1211@g.educaand.es', '2023-04-12 16:27:26', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(51, 'apinmes1211@g.educaand.es', '2023-04-12 16:27:30', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(52, 'apinmes1211@g.educaand.es', '2023-04-12 16:27:33', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(53, 'apinmes1211@g.educaand.es', '2023-04-12 16:34:44', '2023-04-12', '12:15:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(54, 'apinmes1211@g.educaand.es', '2023-04-12 16:43:44', '2023-04-12', '12:15:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(55, 'apinmes1211@g.educaand.es', '2023-04-12 16:44:37', '2023-04-12', '17:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(56, 'apinmes1211@g.educaand.es', '2023-04-12 16:46:13', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(57, 'apinmes1211@g.educaand.es', '2023-04-12 16:48:52', '2023-04-12', '12:15:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(58, 'apinmes1211@g.educaand.es', '2023-04-12 16:52:03', '2023-04-12', '17:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(59, 'apinmes1211@g.educaand.es', '2023-04-12 16:52:50', '2023-04-12', '18:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(60, 'apinmes1211@g.educaand.es', '2023-04-12 16:52:57', '2023-04-12', '18:00:00', 3, 'NO', 'NO', 'NO', 'NO', NULL),
(61, 'apinmes1211@g.educaand.es', '2023-04-12 16:55:24', '2023-04-12', '19:00:00', 3, 'NO', 'NO', 'NO', 'NO', NULL),
(62, 'apinmes1211@g.educaand.es', '2023-04-12 16:59:16', '2023-04-12', '17:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(63, 'apinmes1211@g.educaand.es', '2023-04-12 17:11:03', '2023-04-12', '18:00:00', 2, 'NO', 'NO', 'NO', 'NO', NULL),
(64, 'apinmes1211@g.educaand.es', '2023-05-10 20:05:55', '2023-05-10', '00:00:00', 1.5, 'NO', 'NO', 'NO', 'NO', NULL),
(65, 'apinmes1211@g.educaand.es', '2023-05-10 06:08:09', '2023-05-10', '10:00:00', 2, 'SI', 'NO', 'NO', 'NO', NULL),
(66, 'apinmes1211@g.educaand.es', '2023-05-10 06:13:31', '2023-05-10', '11:00:00', 1.5, 'SI', 'SI', 'SI', 'NO', '2023-05-10 06:14:42');

--
-- Disparadores `pedido`
--
DELIMITER $$
CREATE TRIGGER `copiar_pedidos_penalizados` AFTER UPDATE ON `pedido` FOR EACH ROW BEGIN
      INSERT INTO pedidos_penalizados 					(correo_penalizado, fecha_penalizado, precio_penalizado) 
 	  SELECT correo_cliente, fecha_recogida, precio_pedido
	  FROM pedido
      WHERE penalizado= 'Si';
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `penalizar_clientes` AFTER UPDATE ON `pedido` FOR EACH ROW BEGIN
    IF NEW.penalizado = 'Si' AND OLD.penalizado = 'No' THEN
        UPDATE clientes SET penalizacion = penalizacion + 1, fecha_penalizacion = NOW() WHERE correo = NEW.correo_cliente;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos_penalizados`
--

CREATE TABLE `pedidos_penalizados` (
  `id_penalizado` int(11) NOT NULL,
  `correo_penalizado` varchar(40) NOT NULL,
  `fecha_penalizado` date NOT NULL,
  `precio_penalizado` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos_penalizados`
--

INSERT INTO `pedidos_penalizados` (`id_penalizado`, `correo_penalizado`, `fecha_penalizado`, `precio_penalizado`) VALUES
(146, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(147, 'samu@g.educaand.es', '0000-00-00', 3.5),
(148, 'samu@g.educaand.es', '0000-00-00', 1.5),
(149, 'samu@g.educaand.es', '0000-00-00', 4),
(150, 'samu@g.educaand.es', '0000-00-00', 4),
(151, 'samu@g.educaand.es', '0000-00-00', 1.5),
(152, 'samu@g.educaand.es', '0000-00-00', 1.5),
(153, 'samu@g.educaand.es', '2023-03-20', 3.5),
(154, 'samu@g.educaand.es', '0000-00-00', 1.5),
(161, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(162, 'samu@g.educaand.es', '0000-00-00', 3.5),
(163, 'samu@g.educaand.es', '0000-00-00', 1.5),
(164, 'samu@g.educaand.es', '0000-00-00', 4),
(165, 'samu@g.educaand.es', '0000-00-00', 4),
(166, 'samu@g.educaand.es', '0000-00-00', 1.5),
(167, 'samu@g.educaand.es', '0000-00-00', 1.5),
(168, 'samu@g.educaand.es', '2023-03-20', 3.5),
(169, 'samu@g.educaand.es', '0000-00-00', 1.5),
(176, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(177, 'samu@g.educaand.es', '0000-00-00', 3.5),
(178, 'samu@g.educaand.es', '0000-00-00', 1.5),
(179, 'samu@g.educaand.es', '0000-00-00', 4),
(180, 'samu@g.educaand.es', '0000-00-00', 4),
(181, 'samu@g.educaand.es', '0000-00-00', 1.5),
(182, 'samu@g.educaand.es', '0000-00-00', 1.5),
(183, 'samu@g.educaand.es', '2023-03-20', 3.5),
(184, 'samu@g.educaand.es', '0000-00-00', 1.5),
(191, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(192, 'samu@g.educaand.es', '0000-00-00', 3.5),
(193, 'samu@g.educaand.es', '0000-00-00', 1.5),
(194, 'samu@g.educaand.es', '0000-00-00', 4),
(195, 'samu@g.educaand.es', '0000-00-00', 4),
(196, 'samu@g.educaand.es', '0000-00-00', 1.5),
(197, 'samu@g.educaand.es', '0000-00-00', 1.5),
(198, 'samu@g.educaand.es', '2023-03-20', 3.5),
(199, 'samu@g.educaand.es', '0000-00-00', 1.5),
(206, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(207, 'samu@g.educaand.es', '0000-00-00', 3.5),
(208, 'samu@g.educaand.es', '0000-00-00', 1.5),
(209, 'samu@g.educaand.es', '0000-00-00', 4),
(210, 'samu@g.educaand.es', '0000-00-00', 4),
(211, 'samu@g.educaand.es', '0000-00-00', 1.5),
(212, 'samu@g.educaand.es', '0000-00-00', 1.5),
(213, 'samu@g.educaand.es', '2023-03-20', 3.5),
(214, 'samu@g.educaand.es', '0000-00-00', 1.5),
(221, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(222, 'samu@g.educaand.es', '0000-00-00', 3.5),
(223, 'samu@g.educaand.es', '0000-00-00', 1.5),
(224, 'samu@g.educaand.es', '0000-00-00', 4),
(225, 'samu@g.educaand.es', '0000-00-00', 4),
(226, 'samu@g.educaand.es', '0000-00-00', 1.5),
(227, 'samu@g.educaand.es', '0000-00-00', 1.5),
(228, 'samu@g.educaand.es', '2023-03-20', 3.5),
(229, 'samu@g.educaand.es', '0000-00-00', 1.5),
(236, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(237, 'samu@g.educaand.es', '0000-00-00', 3.5),
(238, 'samu@g.educaand.es', '0000-00-00', 1.5),
(239, 'samu@g.educaand.es', '0000-00-00', 4),
(240, 'samu@g.educaand.es', '0000-00-00', 4),
(241, 'samu@g.educaand.es', '0000-00-00', 1.5),
(242, 'samu@g.educaand.es', '0000-00-00', 1.5),
(243, 'samu@g.educaand.es', '2023-03-20', 3.5),
(244, 'samu@g.educaand.es', '0000-00-00', 1.5),
(251, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(252, 'samu@g.educaand.es', '0000-00-00', 3.5),
(253, 'samu@g.educaand.es', '0000-00-00', 1.5),
(254, 'samu@g.educaand.es', '0000-00-00', 4),
(255, 'samu@g.educaand.es', '0000-00-00', 4),
(256, 'samu@g.educaand.es', '0000-00-00', 1.5),
(257, 'samu@g.educaand.es', '0000-00-00', 1.5),
(258, 'samu@g.educaand.es', '2023-03-20', 3.5),
(259, 'samu@g.educaand.es', '0000-00-00', 1.5),
(266, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(267, 'samu@g.educaand.es', '0000-00-00', 3.5),
(268, 'samu@g.educaand.es', '0000-00-00', 1.5),
(269, 'samu@g.educaand.es', '0000-00-00', 4),
(270, 'samu@g.educaand.es', '0000-00-00', 4),
(271, 'samu@g.educaand.es', '0000-00-00', 1.5),
(272, 'samu@g.educaand.es', '0000-00-00', 1.5),
(273, 'samu@g.educaand.es', '2023-03-20', 3.5),
(274, 'samu@g.educaand.es', '0000-00-00', 1.5),
(281, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(282, 'samu@g.educaand.es', '0000-00-00', 3.5),
(283, 'samu@g.educaand.es', '0000-00-00', 1.5),
(284, 'samu@g.educaand.es', '0000-00-00', 4),
(285, 'samu@g.educaand.es', '0000-00-00', 4),
(286, 'samu@g.educaand.es', '0000-00-00', 1.5),
(287, 'samu@g.educaand.es', '0000-00-00', 1.5),
(288, 'samu@g.educaand.es', '2023-03-20', 3.5),
(289, 'samu@g.educaand.es', '0000-00-00', 1.5),
(290, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(291, 'samu@g.educaand.es', '0000-00-00', 3.5),
(292, 'samu@g.educaand.es', '0000-00-00', 1.5),
(293, 'samu@g.educaand.es', '0000-00-00', 4),
(294, 'samu@g.educaand.es', '0000-00-00', 4),
(295, 'samu@g.educaand.es', '0000-00-00', 1.5),
(296, 'samu@g.educaand.es', '0000-00-00', 1.5),
(297, 'samu@g.educaand.es', '2023-03-20', 3.5),
(298, 'samu@g.educaand.es', '0000-00-00', 1.5),
(305, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(306, 'samu@g.educaand.es', '0000-00-00', 3.5),
(307, 'samu@g.educaand.es', '0000-00-00', 1.5),
(308, 'samu@g.educaand.es', '0000-00-00', 4),
(309, 'samu@g.educaand.es', '0000-00-00', 4),
(310, 'samu@g.educaand.es', '0000-00-00', 1.5),
(311, 'samu@g.educaand.es', '0000-00-00', 1.5),
(312, 'samu@g.educaand.es', '2023-03-20', 3.5),
(313, 'samu@g.educaand.es', '0000-00-00', 1.5),
(320, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(321, 'samu@g.educaand.es', '0000-00-00', 3.5),
(322, 'samu@g.educaand.es', '0000-00-00', 1.5),
(323, 'samu@g.educaand.es', '0000-00-00', 4),
(324, 'samu@g.educaand.es', '0000-00-00', 4),
(325, 'samu@g.educaand.es', '0000-00-00', 1.5),
(326, 'samu@g.educaand.es', '0000-00-00', 1.5),
(327, 'samu@g.educaand.es', '2023-03-20', 3.5),
(328, 'samu@g.educaand.es', '0000-00-00', 1.5),
(335, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(336, 'samu@g.educaand.es', '0000-00-00', 3.5),
(337, 'samu@g.educaand.es', '0000-00-00', 1.5),
(338, 'samu@g.educaand.es', '0000-00-00', 4),
(339, 'samu@g.educaand.es', '0000-00-00', 4),
(340, 'samu@g.educaand.es', '0000-00-00', 1.5),
(341, 'samu@g.educaand.es', '0000-00-00', 1.5),
(342, 'samu@g.educaand.es', '2023-03-20', 3.5),
(343, 'samu@g.educaand.es', '0000-00-00', 1.5),
(350, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(351, 'samu@g.educaand.es', '0000-00-00', 3.5),
(352, 'samu@g.educaand.es', '0000-00-00', 1.5),
(353, 'samu@g.educaand.es', '0000-00-00', 4),
(354, 'samu@g.educaand.es', '0000-00-00', 4),
(355, 'samu@g.educaand.es', '0000-00-00', 1.5),
(356, 'samu@g.educaand.es', '0000-00-00', 1.5),
(357, 'samu@g.educaand.es', '2023-03-20', 3.5),
(358, 'samu@g.educaand.es', '0000-00-00', 1.5),
(365, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(366, 'samu@g.educaand.es', '0000-00-00', 3.5),
(367, 'samu@g.educaand.es', '0000-00-00', 1.5),
(368, 'samu@g.educaand.es', '0000-00-00', 4),
(369, 'samu@g.educaand.es', '0000-00-00', 4),
(370, 'samu@g.educaand.es', '0000-00-00', 1.5),
(371, 'samu@g.educaand.es', '0000-00-00', 1.5),
(372, 'samu@g.educaand.es', '2023-03-20', 3.5),
(373, 'samu@g.educaand.es', '0000-00-00', 1.5),
(380, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(381, 'samu@g.educaand.es', '0000-00-00', 3.5),
(382, 'samu@g.educaand.es', '0000-00-00', 1.5),
(383, 'samu@g.educaand.es', '0000-00-00', 4),
(384, 'samu@g.educaand.es', '0000-00-00', 4),
(385, 'samu@g.educaand.es', '0000-00-00', 1.5),
(386, 'samu@g.educaand.es', '0000-00-00', 1.5),
(387, 'samu@g.educaand.es', '2023-03-20', 3.5),
(388, 'samu@g.educaand.es', '0000-00-00', 1.5),
(395, 'apinmes1211@g.educaand.es', '2023-03-17', 3.5),
(396, 'samu@g.educaand.es', '0000-00-00', 3.5),
(397, 'samu@g.educaand.es', '0000-00-00', 1.5),
(398, 'samu@g.educaand.es', '0000-00-00', 4),
(399, 'samu@g.educaand.es', '0000-00-00', 4),
(400, 'samu@g.educaand.es', '0000-00-00', 1.5),
(401, 'samu@g.educaand.es', '0000-00-00', 1.5),
(402, 'samu@g.educaand.es', '2023-03-20', 3.5),
(403, 'samu@g.educaand.es', '0000-00-00', 1.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos_productos`
--

CREATE TABLE `pedidos_productos` (
  `nombre_producto_ped` varchar(40) NOT NULL,
  `id_pedido_ped` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos_productos`
--

INSERT INTO `pedidos_productos` (`nombre_producto_ped`, `id_pedido_ped`) VALUES
('Bocata de bacon', 34),
('Bocata de bacon', 35),
('Bocata de bacon', 36),
('Bocata de bacon', 37),
('Bocata de bacon', 44),
('Bocata de bacon', 46),
('Bocata de bacon', 54),
('Bocata de bacon', 58),
('Bocata de hamburguesa', 26),
('Bocata de hamburguesa', 29),
('Bocata de hamburguesa', 32),
('Bocata de hamburguesa', 33),
('Bocata de hamburguesa', 34),
('Bocata de hamburguesa', 35),
('Bocata de hamburguesa', 37),
('Bocata de hamburguesa', 48),
('Bocata de hamburguesa', 53),
('Bocata de hamburguesa', 55),
('Bocata de hamburguesa', 59),
('Bocata de hamburguesa', 63),
('Bocata de hamburguesa', 65),
('Bocata de tortilla', 41),
('Bocata de tortilla', 45),
('Bocata de tortilla', 46),
('Bocata de tortilla', 47),
('Bocata de tortilla', 56),
('Bocata de tortilla', 60),
('Bocata de tortilla', 61),
('Bocata mediterráneo', 26),
('Bocata mediterráneo', 28),
('Bocata mediterráneo', 29),
('Bocata mediterráneo', 30),
('Bocata mediterráneo', 31),
('Bocata mediterráneo', 38),
('Bocata mediterráneo', 39),
('Bocata mediterráneo', 40),
('Bocata mediterráneo', 42),
('Bocata mediterráneo', 48),
('Bocata mediterráneo', 49),
('Bocata mediterráneo', 50),
('Bocata mediterráneo', 51),
('Bocata mediterráneo', 52),
('Bocata mediterráneo', 57),
('Bocata mediterráneo', 60),
('Bocata mediterráneo', 61),
('Bocata mediterráneo', 62),
('Bocata mediterráneo', 64),
('Bocata mediterráneo', 66);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `nombre_producto` varchar(40) NOT NULL,
  `descripcion` varchar(70) NOT NULL,
  `precio_producto` float NOT NULL,
  `Cantidad` int(4) NOT NULL DEFAULT 10,
  `mostrado` enum('Si','No') NOT NULL DEFAULT 'Si',
  `borrado` enum('Si','No') NOT NULL DEFAULT 'No',
  `imagen` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`nombre_producto`, `descripcion`, `precio_producto`, `Cantidad`, `mostrado`, `borrado`, `imagen`) VALUES
('Bocata de bacon', 'Pan, bacon, queso', 2, 2, 'Si', 'No', NULL),
('Bocata de hamburguesa', 'Pan, carne de vacuno, mayonesa, lechuga', 2, 1, 'Si', 'No', NULL),
('Bocata de tortilla', 'Pan, tortilla, aceite', 1.5, 3, 'Si', 'No', NULL),
('Bocata mediterráneo', 'Pan con sésamo, crustáceos, pescado', 1.5, 1, 'Si', 'No', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_con_alergenos`
--

CREATE TABLE `productos_con_alergenos` (
  `nombre_producto_relacion` varchar(40) NOT NULL,
  `nombre_alergeno_relacion` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos_con_alergenos`
--

INSERT INTO `productos_con_alergenos` (`nombre_producto_relacion`, `nombre_alergeno_relacion`) VALUES
('Bocata mediterráneo', 'Crustáceos'),
('Bocata de bacon', 'Gluten'),
('Bocata de hamburguesa', 'Gluten'),
('Bocata de tortilla', 'Gluten'),
('Bocata mediterráneo', 'Gluten'),
('Bocata de bacon', 'Huevos'),
('Bocata de hamburguesa', 'Huevos'),
('Bocata de tortilla', 'Huevos'),
('Bocata de bacon', 'Lácteos'),
('Bocata de hamburguesa', 'Lácteos'),
('Bocata mediterráneo', 'Pescado'),
('Bocata mediterráneo', 'Sésamo'),
('Bocata de hamburguesa', 'Sulfitos');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alergenos`
--
ALTER TABLE `alergenos`
  ADD PRIMARY KEY (`nombre_alergeno`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`correo`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`,`correo_cliente`),
  ADD KEY `fk_correo` (`correo_cliente`);

--
-- Indices de la tabla `pedidos_penalizados`
--
ALTER TABLE `pedidos_penalizados`
  ADD PRIMARY KEY (`id_penalizado`);

--
-- Indices de la tabla `pedidos_productos`
--
ALTER TABLE `pedidos_productos`
  ADD PRIMARY KEY (`nombre_producto_ped`,`id_pedido_ped`),
  ADD KEY `fk_pedidos` (`id_pedido_ped`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`nombre_producto`);

--
-- Indices de la tabla `productos_con_alergenos`
--
ALTER TABLE `productos_con_alergenos`
  ADD PRIMARY KEY (`nombre_alergeno_relacion`,`nombre_producto_relacion`),
  ADD KEY `fk_producto` (`nombre_producto_relacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT de la tabla `pedidos_penalizados`
--
ALTER TABLE `pedidos_penalizados`
  MODIFY `id_penalizado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=404;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_correo` FOREIGN KEY (`correo_cliente`) REFERENCES `clientes` (`correo`);

--
-- Filtros para la tabla `pedidos_productos`
--
ALTER TABLE `pedidos_productos`
  ADD CONSTRAINT `fk_pedidos` FOREIGN KEY (`id_pedido_ped`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `fk_productos` FOREIGN KEY (`nombre_producto_ped`) REFERENCES `productos` (`nombre_producto`);

--
-- Filtros para la tabla `productos_con_alergenos`
--
ALTER TABLE `productos_con_alergenos`
  ADD CONSTRAINT `fk_alergeno` FOREIGN KEY (`nombre_alergeno_relacion`) REFERENCES `alergenos` (`nombre_alergeno`),
  ADD CONSTRAINT `fk_producto` FOREIGN KEY (`nombre_producto_relacion`) REFERENCES `productos` (`nombre_producto`);

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `eliminar_usuarios_no_activos` ON SCHEDULE EVERY 1 SECOND STARTS '2023-03-09 18:46:58' ON COMPLETION PRESERVE DISABLE DO DELETE FROM clientes WHERE activado = 'NO' AND fecha_creacion < DATE_SUB(NOW(), INTERVAL 15 DAY)$$

CREATE DEFINER=`root`@`localhost` EVENT `eliminar_usuarios_no_pedido` ON SCHEDULE EVERY 1 SECOND STARTS '2023-03-08 10:23:56' ON COMPLETION PRESERVE DISABLE DO DELETE FROM clientes
  WHERE activado = 'SI' 
    AND correo NOT IN (
      SELECT DISTINCT correo_cliente
      FROM pedido
    )
    AND fecha_activacion <= NOW() - INTERVAL 30 DAY
    AND tipo_usuario = 'Cliente'$$

CREATE DEFINER=`root`@`localhost` EVENT `eliminar_pedidos_productos` ON SCHEDULE EVERY 1 WEEK STARTS '2023-03-13 14:52:19' ON COMPLETION PRESERVE DISABLE DO DELETE pedidos_productos
FROM pedidos_productos
INNER JOIN pedido ON pedidos_productos.id_pedido_ped = pedido.id_pedido$$

CREATE DEFINER=`root`@`localhost` EVENT `eliminar_pedido` ON SCHEDULE EVERY 1 SECOND STARTS '2023-03-13 14:57:18' ON COMPLETION PRESERVE DISABLE DO DELETE FROM pedido$$

CREATE DEFINER=`root`@`localhost` EVENT `reiniciar` ON SCHEDULE EVERY 1 SECOND STARTS '2023-03-13 15:02:38' ON COMPLETION PRESERVE DISABLE DO ALTER TABLE pedido AUTO_INCREMENT = 1$$

CREATE DEFINER=`root`@`localhost` EVENT `pedido_no_recogido` ON SCHEDULE EVERY 1 SECOND STARTS '2023-03-14 10:25:37' ON COMPLETION PRESERVE ENABLE DO UPDATE pedido
SET penalizado = 'Si'
WHERE fecha_listo <= NOW() - INTERVAL 30 MINUTE AND listo = 'Si'$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

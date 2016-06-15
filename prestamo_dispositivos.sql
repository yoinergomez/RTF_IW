-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2016 at 11:25 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `prestamo_dispositivos`
--

-- --------------------------------------------------------

--
-- Table structure for table `dispositivo`
--

CREATE TABLE IF NOT EXISTS `dispositivo` (
  `Codigo` int(12) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Marca` varchar(30) NOT NULL,
  `Caracteristicas` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dispositivo`
--

INSERT INTO `dispositivo` (`Codigo`, `Nombre`, `Marca`, `Caracteristicas`) VALUES
(1, 'Laptop', 'Lenovo', 'Intel centrino');

-- --------------------------------------------------------

--
-- Table structure for table `prestamo`
--

CREATE TABLE IF NOT EXISTS `prestamo` (
  `UsuarioId` varchar(50) NOT NULL,
  `DispositivoId` int(12) NOT NULL,
  `FechaInicio` timestamp NOT NULL,
  `FechaFin` timestamp NOT NULL,
  `EstadoPrestamo` int(1) NOT NULL,
  PRIMARY KEY (`UsuarioId`,`DispositivoId`,`FechaInicio`,`FechaFin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prestamo`
--

INSERT INTO `prestamo` (`UsuarioId`, `DispositivoId`, `FechaInicio`, `FechaFin`, `EstadoPrestamo`) VALUES
('santiago@udea', 1, '2016-03-15 05:00:00', '2016-05-04 05:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`ID`, `NOMBRE`) VALUES
(1, 'papu de papus'),
(2, 'compita');

-- --------------------------------------------------------

--
-- Table structure for table `solicitud`
--

CREATE TABLE IF NOT EXISTS `solicitud` (
  `UsuarioId` varchar(50) NOT NULL,
  `DispositivoId` int(12) NOT NULL,
  `FechaInicio` timestamp NOT NULL,
  `FechaFin` timestamp NOT NULL,
  `EstadoSolicitud` int(2) NOT NULL,
  `Motivo` varchar(120) DEFAULT NULL,
  `Respuesta` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UsuarioId`,`DispositivoId`,`FechaInicio`,`FechaFin`),
  KEY `UsuarioId` (`UsuarioId`),
  KEY `DispositivoId` (`DispositivoId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `solicitud`
--

INSERT INTO `solicitud` (`UsuarioId`, `DispositivoId`, `FechaInicio`, `FechaFin`, `EstadoSolicitud`, `Motivo`, `Respuesta`) VALUES
('santiago@udea', 1, '2016-03-15 05:00:00', '2016-05-04 05:00:00', 2, 'lo necesito', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `CORREO` varchar(50) NOT NULL,
  `CEDULA` varchar(15) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `APELLIDO` varchar(50) NOT NULL,
  `CONTRASENA` varchar(15) NOT NULL,
  `ROL_ID` int(11) NOT NULL,
  PRIMARY KEY (`CORREO`),
  KEY `ROL_ID` (`ROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`CORREO`, `CEDULA`, `NOMBRE`, `APELLIDO`, `CONTRASENA`, `ROL_ID`) VALUES
('santiago@udea', '1045024074', 'Santiago', 'Gomez', '123456', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `padre_hijo` FOREIGN KEY (`UsuarioId`, `DispositivoId`, `FechaInicio`, `FechaFin`) REFERENCES `solicitud` (`UsuarioId`, `DispositivoId`, `FechaInicio`, `FechaFin`);

--
-- Constraints for table `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `SOLICITUD_ibfk_1` FOREIGN KEY (`UsuarioId`) REFERENCES `usuario` (`CORREO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `SOLICITUD_ibfk_2` FOREIGN KEY (`DispositivoId`) REFERENCES `dispositivo` (`Codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `ROL_ID` FOREIGN KEY (`ROL_ID`) REFERENCES `rol` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

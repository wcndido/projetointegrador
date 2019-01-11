-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Jan-2019 às 00:50
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_bandas`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `medicos`
--

CREATE TABLE `medicos` (
  `cod_medico` int(11) NOT NULL,
  `nome_medico` varchar(60) NOT NULL,
  `especialidade` int(11) NOT NULL,
  `crm` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `medicos`
--

INSERT INTO `medicos` (`cod_medico`, `nome_medico`, `especialidade`, `crm`) VALUES
(1, 'WAgner', 2, 2000),
(2, 'teste2', 4, 2001),
(3, 'teste3', 1, 2002),
(4, 'teste4', 3, 2003),
(5, 'teste5', 4, 2005),
(6, 'teste6', 3, 2006),
(7, 'teste7', 4, 2007);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb01_estilos`
--

CREATE TABLE `tb01_estilos` (
  `estilo_cod` int(11) NOT NULL,
  `estilo_nome` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb01_estilos`
--

INSERT INTO `tb01_estilos` (`estilo_cod`, `estilo_nome`) VALUES
(1, 'Cardiologia'),
(2, 'Pediatra'),
(3, 'Acupuntura'),
(4, 'Ped/Cardio');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`cod_medico`),
  ADD KEY `FK_tb02_bandas_tb01_estilos` (`especialidade`);

--
-- Indexes for table `tb01_estilos`
--
ALTER TABLE `tb01_estilos`
  ADD PRIMARY KEY (`estilo_cod`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `medicos`
--
ALTER TABLE `medicos`
  MODIFY `cod_medico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb01_estilos`
--
ALTER TABLE `tb01_estilos`
  MODIFY `estilo_cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `medicos`
--
ALTER TABLE `medicos`
  ADD CONSTRAINT `FK_tb02_bandas_tb01_estilos` FOREIGN KEY (`especialidade`) REFERENCES `tb01_estilos` (`estilo_cod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

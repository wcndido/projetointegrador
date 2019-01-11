-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.27 - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para bd_bandas
DROP DATABASE IF EXISTS `bd_bandas`;
CREATE DATABASE IF NOT EXISTS `bd_bandas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd_bandas`;

-- Copiando estrutura para tabela bd_bandas.tb01_estilos
DROP TABLE IF EXISTS `tb01_estilos`;
CREATE TABLE IF NOT EXISTS `tb01_estilos` (
  `estilo_cod` int(11) NOT NULL AUTO_INCREMENT,
  `estilo_nome` varchar(60) NOT NULL,
  PRIMARY KEY (`estilo_cod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela bd_bandas.tb01_estilos: ~3 rows (aproximadamente)
DELETE FROM `tb01_estilos`;
/*!40000 ALTER TABLE `tb01_estilos` DISABLE KEYS */;
INSERT INTO `tb01_estilos` (`estilo_cod`, `estilo_nome`) VALUES
	(1, 'Rock'),
	(2, 'Forró'),
	(3, 'Sertanejo');
/*!40000 ALTER TABLE `tb01_estilos` ENABLE KEYS */;

-- Copiando estrutura para tabela bd_bandas.tb02_bandas
DROP TABLE IF EXISTS `tb02_bandas`;
CREATE TABLE IF NOT EXISTS `tb02_bandas` (
  `banda_cod` int(11) NOT NULL AUTO_INCREMENT,
  `banda_nome` varchar(60) NOT NULL,
  `banda_estilo_cod` int(11) NOT NULL,
  `banda_ano` year(4) NOT NULL,
  PRIMARY KEY (`banda_cod`),
  KEY `FK_tb02_bandas_tb01_estilos` (`banda_estilo_cod`),
  CONSTRAINT `FK_tb02_bandas_tb01_estilos` FOREIGN KEY (`banda_estilo_cod`) REFERENCES `tb01_estilos` (`estilo_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela bd_bandas.tb02_bandas: ~0 rows (aproximadamente)
DELETE FROM `tb02_bandas`;
/*!40000 ALTER TABLE `tb02_bandas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb02_bandas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

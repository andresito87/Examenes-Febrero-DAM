-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: consultorait
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `dept_no` tinyint NOT NULL,
  `dnombre` varchar(20) NOT NULL,
  `loc` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'Avengers','New York'),(2,'Justice League','Metropolis'),(3,'X-Men','Westchester'),(4,'Guardians','Knowhere');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `emp_no` smallint NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `oficio` varchar(20) NOT NULL,
  `dir` smallint DEFAULT NULL,
  `fecha_alt` date NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  `comision` decimal(10,2) DEFAULT NULL,
  `dept_no` tinyint DEFAULT NULL,
  PRIMARY KEY (`emp_no`),
  KEY `FK_empleados_departamentos` (`dept_no`),
  CONSTRAINT `FK_empleados_departamentos` FOREIGN KEY (`dept_no`) REFERENCES `departamentos` (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (101,'Stark','CEO',NULL,'2020-01-15',15000.00,500.00,1),(102,'Rogers','Leader',101,'2019-07-20',10000.00,300.00,1),(103,'Romanoff','Spy',101,'2018-05-10',8000.00,200.00,1),(104,'Banner','Scientist',101,'2017-03-25',9500.00,250.00,1),(201,'Kent','Reporter',NULL,'2016-06-18',12000.00,400.00,2),(202,'Wayne','Detective',201,'2015-11-30',14000.00,450.00,2),(203,'Prince','Warrior',201,'2017-08-12',11000.00,350.00,2),(204,'Allen','Scientist',201,'2018-04-17',9000.00,300.00,2),(301,'Xavier','Professor',NULL,'2014-02-20',13000.00,400.00,3),(302,'Summers','Leader',301,'2015-09-22',10500.00,350.00,3),(303,'Grey','Doctor',301,'2016-07-14',9500.00,300.00,3),(304,'Howlett','Agent',301,'2013-12-09',9000.00,200.00,3),(401,'Quill','Captain',NULL,'2017-03-05',11000.00,300.00,4),(402,'Raccoon','Engineer',401,'2018-10-21',8500.00,250.00,4),(403,'Gamora','Assassin',401,'2019-01-15',9500.00,200.00,4),(404,'Drax','Warrior',401,'2020-05-25',9000.00,150.00,4);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23 22:03:52

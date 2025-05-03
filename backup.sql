CREATE DATABASE  IF NOT EXISTS `mentalist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mentalist`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mentalist
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `area_ocurrencia`
--

DROP TABLE IF EXISTS `area_ocurrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area_ocurrencia` (
  `id_area_ocurrencia` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_area_ocurrencia`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_ocurrencia`
--

LOCK TABLES `area_ocurrencia` WRITE;
/*!40000 ALTER TABLE `area_ocurrencia` DISABLE KEYS */;
INSERT INTO `area_ocurrencia` VALUES (1,'Urbana'),(2,'Rural'),(3,'casa'),(4,'apartamento');
/*!40000 ALTER TABLE `area_ocurrencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caso`
--

DROP TABLE IF EXISTS `caso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caso` (
  `id_caso` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int DEFAULT NULL,
  `id_area_ocurrencia` int DEFAULT NULL,
  `id_ruta_atencion` int DEFAULT NULL,
  `id_eapb` int DEFAULT NULL,
  `id_curso_vida` int DEFAULT NULL,
  `id_diagnostico_especifico` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `fecha_notificacion` date DEFAULT NULL,
  `semana_epidemiologica` int DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_revision_historia` date DEFAULT NULL,
  `remision_ruta_salud` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_caso`),
  KEY `id_paciente_idx` (`id_paciente`),
  KEY `id_curso_vida_idx` (`id_curso_vida`),
  KEY `id_area_ocurrencia_idx` (`id_area_ocurrencia`),
  KEY `id_ruta_atencion_idx` (`id_ruta_atencion`),
  KEY `id_eapb_idx` (`id_eapb`),
  KEY `id_curso_vida_idx1` (`id_curso_vida`),
  KEY `id_usuario_caso` (`id_usuario`),
  KEY `id_diagnostico_especifico_idx` (`id_diagnostico_especifico`),
  CONSTRAINT `id_area_ocurrencia` FOREIGN KEY (`id_area_ocurrencia`) REFERENCES `area_ocurrencia` (`id_area_ocurrencia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_curso_vida` FOREIGN KEY (`id_curso_vida`) REFERENCES `curso_vida` (`id_curso_vida`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_diagnostico_especifico` FOREIGN KEY (`id_diagnostico_especifico`) REFERENCES `diagnostico_especifico` (`id_diagnostico_especifico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_eapb` FOREIGN KEY (`id_eapb`) REFERENCES `eapb` (`id_eapb`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_ruta_atencion` FOREIGN KEY (`id_ruta_atencion`) REFERENCES `ruta_atencion` (`id_ruta_atencion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_usuario_caso` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso`
--

LOCK TABLES `caso` WRITE;
/*!40000 ALTER TABLE `caso` DISABLE KEYS */;
/*!40000 ALTER TABLE `caso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_vida`
--

DROP TABLE IF EXISTS `curso_vida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_vida` (
  `id_curso_vida` int NOT NULL AUTO_INCREMENT,
  `etapa` enum('Primera_Infancia','Infancia','Adolescencia','Juventud','Adultez','Vejez') DEFAULT NULL,
  PRIMARY KEY (`id_curso_vida`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_vida`
--

LOCK TABLES `curso_vida` WRITE;
/*!40000 ALTER TABLE `curso_vida` DISABLE KEYS */;
INSERT INTO `curso_vida` VALUES (1,'Infancia'),(2,'Adolescencia'),(3,'Adultez');
/*!40000 ALTER TABLE `curso_vida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostico_especifico`
--

DROP TABLE IF EXISTS `diagnostico_especifico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnostico_especifico` (
  `id_diagnostico_especifico` int NOT NULL AUTO_INCREMENT,
  `tipo_diagnostico` enum('Violencia de Genero','Trastorno Mental','Intento de Suicidio','Consumo de Sustancias') DEFAULT NULL,
  `codigo_cie` varchar(255) DEFAULT NULL,
  `observaciones_medicas` text,
  `fecha_diagnostico` date DEFAULT NULL,
  PRIMARY KEY (`id_diagnostico_especifico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico_especifico`
--

LOCK TABLES `diagnostico_especifico` WRITE;
/*!40000 ALTER TABLE `diagnostico_especifico` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostico_especifico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eapb`
--

DROP TABLE IF EXISTS `eapb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eapb` (
  `id_eapb` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_eapb`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eapb`
--

LOCK TABLES `eapb` WRITE;
/*!40000 ALTER TABLE `eapb` DISABLE KEYS */;
INSERT INTO `eapb` VALUES (1,'EPS Salud Total'),(2,'EPS Sanitas');
/*!40000 ALTER TABLE `eapb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historia_clinica`
--

DROP TABLE IF EXISTS `historia_clinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historia_clinica` (
  `id_historia_clinica` int NOT NULL AUTO_INCREMENT,
  `id_paciente_H` int DEFAULT NULL,
  `id_caso_h` int DEFAULT NULL,
  `descripcion_historia` text,
  PRIMARY KEY (`id_historia_clinica`),
  KEY `id_paciente_idx` (`id_paciente_H`),
  KEY `id_paciente_H_idx` (`id_paciente_H`),
  KEY `id_caso_h_idx` (`id_caso_h`),
  CONSTRAINT `id_caso_h` FOREIGN KEY (`id_caso_h`) REFERENCES `caso` (`id_caso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_paciente_H` FOREIGN KEY (`id_paciente_H`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_clinica`
--

LOCK TABLES `historia_clinica` WRITE;
/*!40000 ALTER TABLE `historia_clinica` DISABLE KEYS */;
INSERT INTO `historia_clinica` VALUES (1,1076647125,NULL,'Historia clínica inicial de Laura Rodríguez');
/*!40000 ALTER TABLE `historia_clinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL,
  `tipo_documento` enum('CC','TI','CE','PAS') NOT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `genero` enum('Masculino','Femenino') DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (101,'CC','oMOFI6zPU+oU/rGmrSTJyBNlFOHY0Hcj','1990-05-12',34,'Masculino','Z0LndAdT2HEh+dBDdKRDkmwzdkppy1qi','+FtwYGAVmJogHMmsDBYa9d12RndWZA1x','OoJTWCNUsQzLorY1XhOsBZIg6BdilbDuywY8DyrZNcc=','6VC9y7z/uKsc4dwgnSu0l9gi00J0U80Mc04pRNordQk='),(12345,'CC','a','2004-05-28',20,'Masculino','Argentina','213432','a@gmail.com','calle1'),(10785134,'CC','Jesus Ariza','2004-07-28',20,'Masculino','Colombiana','124123','jisaw@gmail.com','calle 3qa'),(57346345,'CC','Maria','2005-06-28',20,'Femenino','Colombiana','135123','patricia@gmail.com','calle 3443'),(100000998,'CC','Jose','2009-02-28',27,'Masculino','Colombiana','6564534','jose@gmail.com','calle 512s1'),(1076647125,'CC','Daniel','2004-07-28',20,'Masculino','Colombiana','3214567890','jorge@example.com','calle 3a #214-12'),(1078513444,'CC','camila','2003-02-28',21,'Femenino','Colombiana','6564534','camila@gmail.com','calle 512s1');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte` (
  `id_reporte` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `tipo_reporte` enum('Medico','Administrativo','Tecnico') DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_reporte`),
  KEY `id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
INSERT INTO `reporte` VALUES (4,1,'Administrativo','Este es un reporte de prueba para verificar la API.','2025-04-25'),(5,3,'Medico','Este es un reporte de prueba para verificar la API.','2025-04-25'),(6,1,'Medico','Este es un reporte de prueba para verificar la API.','2025-04-26');
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta_atencion`
--

DROP TABLE IF EXISTS `ruta_atencion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruta_atencion` (
  `id_ruta_atencion` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ruta_atencion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta_atencion`
--

LOCK TABLES `ruta_atencion` WRITE;
/*!40000 ALTER TABLE `ruta_atencion` DISABLE KEYS */;
INSERT INTO `ruta_atencion` VALUES (1,'Atención Primaria'),(2,'Consulta Especializada');
/*!40000 ALTER TABLE `ruta_atencion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `rol` enum('Administrador','Medico') NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Camila','Medico1','Medico','123','jisaw2804@gmail.com','31513412'),(3,'Soler4123 ','maria213','Administrador','1234567890','juan.perez@example.com','123456789'),(4,'Maria ','jperez','Administrador','1234567890','juan.perez@example.com','123456789'),(5,'Angela ','angela123','Medico','72412334','angela@example.com','745346765'),(7,'Angela ','martinez1234','Medico','72412334','angela@example.com','745346765'),(8,'Daniel ','jorge1234','Administrador','1234567890','jorge@example.com','123456789'),(9,'Laura Rodríguez','laurar2025','Medico','lauraPass789','laura.rodriguez@example.com','3124567890');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mentalist'
--

--
-- Dumping routines for database 'mentalist'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-03  8:06:30

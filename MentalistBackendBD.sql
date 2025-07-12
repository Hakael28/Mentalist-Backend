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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_ocurrencia`
--

LOCK TABLES `area_ocurrencia` WRITE;
/*!40000 ALTER TABLE `area_ocurrencia` DISABLE KEYS */;
INSERT INTO `area_ocurrencia` VALUES (8,'KqeE4h1/wSm/4NRqTCrnTg==');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso`
--

LOCK TABLES `caso` WRITE;
/*!40000 ALTER TABLE `caso` DISABLE KEYS */;
INSERT INTO `caso` VALUES (9,1,8,4,5,6,5,24,'2025-07-01',27,'2025-07-01','2025-07-01','cZC4d6+J/gKrxxjdr1q19Dsy0OBe2fq9Oh4sZThsjLoeYp6zQSfJfg==');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_vida`
--

LOCK TABLES `curso_vida` WRITE;
/*!40000 ALTER TABLE `curso_vida` DISABLE KEYS */;
INSERT INTO `curso_vida` VALUES (5,'Adolescencia'),(6,'Primera_Infancia');
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
  `tipo_diagnostico` enum('Violencia_de_Genero','Trastorno_Mental','Intento_de_Suicidio','Consumo_de_Sustancias') DEFAULT NULL,
  `codigo_cie` varchar(255) DEFAULT NULL,
  `observaciones_medicas` text,
  `fecha_diagnostico` date DEFAULT NULL,
  PRIMARY KEY (`id_diagnostico_especifico`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostico_especifico`
--

LOCK TABLES `diagnostico_especifico` WRITE;
/*!40000 ALTER TABLE `diagnostico_especifico` DISABLE KEYS */;
INSERT INTO `diagnostico_especifico` VALUES (4,'Trastorno_Mental','DW8RQkYEFCaqoQTcY2/kEg==','K38eu91tgvt/FUl/77nOHovoX8904eH3','2025-01-23'),(5,'Violencia_de_Genero','N2iiFJjDvH02lxd2KShsrg==','DZfrzX1mYNcwF5hldvVmFY9klqyu1LpIs3tBYdFuk3FAdH2eL3tlrC74h8JnEaLes8m0h3Q1Ld+2+VlI12f6Kw==','2025-06-15');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eapb`
--

LOCK TABLES `eapb` WRITE;
/*!40000 ALTER TABLE `eapb` DISABLE KEYS */;
INSERT INTO `eapb` VALUES (4,'yJBg025amzotkIiznQuIvA=='),(5,'aUABCtaglHZutCOBZ5h5z9os03CsAGeZ');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_clinica`
--

LOCK TABLES `historia_clinica` WRITE;
/*!40000 ALTER TABLE `historia_clinica` DISABLE KEYS */;
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
INSERT INTO `paciente` VALUES (1,'CC','IH5uhmDFUEtNdqGor/POQ6X3xFddyEpj','1990-04-15',35,'Masculino','qyq0UgPWPnPx2ZLfYvLnMrzlKlK03vYa','soC3GgDkF1+NtUAhXd1FCP202ax5pg6j','/G346e38vlEarCTPkvfp1ePi5fpPghi03VIZGEIkpUs=','3yBENuu3H6elbhnN3jgj2yW9+CuMfPs+4TrexSzP4hk='),(1234,'CC','4SzaLemmH7GcDuSfM6eDp1D7YxS2sYGU','1990-03-15',35,'Masculino','HGZyEiZ8vBzN93Xa16LNSaNkH5Qo0xi+','Cef69M78IGSZz+/vrM1aPbw3X9MWGJ69','hMJo9JPZAK/fU8OpnguQxiuKFwAiz3qURHl0isKT8ns=','lnGqYjF2vBAzUJV9ftuAUSme7ofiZfh2');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
INSERT INTO `reporte` VALUES (7,24,'Administrativo','N1WZ7wW4WITBJO6eXvWc6g==','2025-06-20'),(8,24,'Administrativo','1BvMT+ouWM5t4gEpoL+kIsSRd0wWwtuGo8nijbTdDTM=','2025-06-20'),(9,25,'Medico','GFU6iG5Yr3DFvjENTUbb+99g91JH40q3','2025-07-07'),(10,24,'Administrativo','QxbmdNBTCtuglQqw0UOJP7quD6ZbE7kq','2025-07-07');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta_atencion`
--

LOCK TABLES `ruta_atencion` WRITE;
/*!40000 ALTER TABLE `ruta_atencion` DISABLE KEYS */;
INSERT INTO `ruta_atencion` VALUES (4,'luY98BgemmyZmjd9ujUYOaiJUpy0xxh99o6ab6iWCE7kh/ZC4+j7xNXlEYavQkIAb8bjYCNrTrOpiPdzMnfMND8G68phug/z0oOoqJU6Wm3rCOuWM1UAPNIYMoM+UKsr');
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
  `rol` enum('ADMIN','MEDICO') NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (24,'ATLMHO23uL0BqkAkkyg5iR3fTPnLNCfmbcfjJKnGzTTWkeaG0TR7Bw==','admin46','ADMIN','$2a$10$ggnInifh42yLxhMaUYZB/ubJP94npQIbzu.DRr6iTQ/pez6GI7sKq','tn90sujqzTqb8GAi24T0tiQZV9o0Ol0TM6wS5/9Q8F17sddiADD6PA==','g1QGzlQcfT1of7nkmZTykyEsEPmRTTcZ'),(25,'FUAeArYP0rkJBazuIZLSwwG9MG2592iqNhgmVks0Vkc=','medico48','MEDICO','$2a$10$hBywKWqVWuanD.YXehAPWOFHiq7Rrxj9QWm6gPKTl4GC7QzUqxZKS','DIQDU7AYEobLv026LHfO7YEc+CV0Adiyy2+z+UW8TIA=','ax+6mSYIkI7VucR+7i5NJnlAeV3wHQYq'),(26,'2N+rGvYynycZqz5b74ukHqDN5Qj1QW1q','medico46','MEDICO','$2a$10$r4GdgHvCBJ4xAH.cUhR8NugYZ.s3nIvoYhoQRMMrwZBslAWwSyEXy','DHYONiz6cfq5VsNjtSFq8mh91ouJctkzKhoS19SRFHk=','zR4HnwqanIwxgguUmQw1+NHUfhG2nhdg');
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

-- Dump completed on 2025-07-12  8:11:03

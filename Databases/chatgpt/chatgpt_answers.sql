-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chatgpt
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `question` longtext,
  `answer` longtext,
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'What is a relational database?','A relational database is a type of database that organizes data into one or more tables, with each table consisting of rows and columns.'),(2,'What is a relational database?','Relational databases use a set of rules called \"relational algebra\"\" to manipulate the data in the tables. \"'),(3,'What are its applications?','Applications of relational databases include managing large amounts of data in various industries such as finance, healthcare, and e-commerce.'),(4,'What are some advantages of using a relational database?','Advantages of using a relational database include efficient data management, data integrity, and the ability to establish relationships between tables using foreign keys.'),(5,'How do you create a new table in a relational database?','To create a new table in a relational database, you can use SQL commands like CREATE TABLE.'),(6,'What is normalization?','Normalization is the process of organizing data in a database to minimize redundancy and dependency.'),(7,'What is normalization in relational databases?','Normalization in relational databases refers to a series of rules that guide the design of a database schema to minimize redundancy and dependency.'),(8,'Give a real world example of it?','A real-world example of normalization is separating customer data from order data in a database to reduce duplication and improve data consistency.'),(9,'What is a primary key in a relational database?','A primary key is a unique identifier for each record in a table that helps ensure data integrity and facilitates the establishment of relationships with other tables.'),(10,'Can a database exist without a primary key?','A database can technically exist without a primary key, but it may not be well-designed or efficient for data management purposes.'),(11,'What is a foreign key in a relational database?','A foreign key is a column in a table that establishes a link to a primary key in another table, helping to establish relationships between tables.'),(12,'Can primary and foreign keys be the same?','Primary and foreign keys cannot be the same, as they serve different purposes and must be unique within their respective tables.'),(13,'What is a join in a relational database?','A join in a relational database is used to combine data from two or more tables based on a related column between them.'),(14,'What are the types of various joins?','Various types of joins in a relational database include inner join, left join, right join, and full outer join.');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-28 15:54:28

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
-- Table structure for table `chatgpt_sample_data`
--

DROP TABLE IF EXISTS `chatgpt_sample_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatgpt_sample_data` (
  `first_name` text,
  `last_name` text,
  `username` text,
  `password` text,
  `email` text,
  `conversation_name` text,
  `conversation_start_time` text,
  `message_text` text,
  `message_sent_time` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatgpt_sample_data`
--

LOCK TABLES `chatgpt_sample_data` WRITE;
/*!40000 ALTER TABLE `chatgpt_sample_data` DISABLE KEYS */;
INSERT INTO `chatgpt_sample_data` VALUES ('Syed','Rizvi','syedrizvi','password123','syedrizvi@example.com','Relational Database','4/1/2023 10:00','What is a relational database?','4/1/2023 10:00'),('Syed','Rizvi','syedrizvi','password123','syedrizvi@example.com','Relational Database','4/1/2023 10:00','What are its applications?','4/1/2023 10:01'),('Syed','Rizvi','syedrizvi','password123','syedrizvi@example.com','Relational Database','4/1/2023 10:00','What are some advantages of using a relational database?','4/1/2023 10:02'),('Syed','Rizvi','syedrizvi','password123','syedrizvi@example.com','Relational Database','4/1/2023 10:00','How do you create a new table in a relational database?','4/2/2023 11:00'),('Sarah','Lee','sarahlee','password456','sarahlee@example.com','Normalization of Database','4/1/2023 10:00','What is normalization?','4/2/2023 11:00'),('Sarah','Lee','sarahlee','password456','sarahlee@example.com','Normalization of Database','4/1/2023 10:00','What is normalization in relational databases?','4/3/2023 12:01'),('Sarah','Lee','sarahlee','password456','sarahlee@example.com','Normalization of Database','4/1/2023 10:00','Give a real world example of it?','4/3/2023 12:04'),('Sarah','Lee','sarahlee','password456','sarahlee@example.com','Normalization of Database','4/1/2023 10:00','What is a primary key in a relational database?','4/3/2023 12:14'),('Sarah','Lee','sarahlee','password456','sarahlee@example.com','Normalization of Database','4/1/2023 10:00','Can a database exist without a primary key?','4/3/2023 12:24'),('Karen','Tan','karentan','password123','karentan@example.com','SQL Keys and Joins','4/5/2023 14:00','What is a foreign key in a relational database?','4/5/2023 14:00'),('Karen','Tan','karentan','password123','karentan@example.com','SQL Keys and Joins','4/5/2023 14:00','Can primary and foreign keys be the same?','4/6/2023 14:04'),('Karen','Tan','karentan','password123','karentan@example.com','SQL Keys and Joins','4/5/2023 14:00','What is a join in a relational database?','4/7/2023 14:08'),('Karen','Tan','karentan','password123','karentan@example.com','SQL Keys and Joins','4/5/2023 14:00','What are the types of various joins?','4/7/2023 14:09');
/*!40000 ALTER TABLE `chatgpt_sample_data` ENABLE KEYS */;
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

-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: jobspot
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `competencies`
--

DROP TABLE IF EXISTS `competencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competencies` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `JOBSEEKER` int(11) NOT NULL,
  `SKILL` varchar(255) NOT NULL DEFAULT '',
  `LEVEL` varchar(255) NOT NULL DEFAULT '0',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_COMPETENCIES_JOBSEEKER_idx` (`JOBSEEKER`),
  CONSTRAINT `FK_COMPETENCIES_JOBSEEKER` FOREIGN KEY (`JOBSEEKER`) REFERENCES `jobseeker` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competencies`
--

LOCK TABLES `competencies` WRITE;
/*!40000 ALTER TABLE `competencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `competencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `JOBSEEKER` int(11) NOT NULL,
  `QUALIFICATION` varchar(255) NOT NULL DEFAULT '',
  `PERIOD` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `QUALIFCATIONCODE` int(11) NOT NULL,
  PRIMARY KEY (`CODE`),
  KEY `FK_EDUCATION_JOBSEEKER_idx` (`JOBSEEKER`),
  KEY `FK_QUALIFICATION_idx` (`QUALIFCATIONCODE`),
  CONSTRAINT `FK_EDUCATION_JOBSEEKER` FOREIGN KEY (`JOBSEEKER`) REFERENCES `jobseeker` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_QUALIFICATION` FOREIGN KEY (`QUALIFCATIONCODE`) REFERENCES `qualification` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employer` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL DEFAULT '',
  `USER` int(11) DEFAULT NULL,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `TOWN` bigint(20) NOT NULL DEFAULT '-1',
  `INDUSTRY` int(11) NOT NULL DEFAULT '-1',
  `CONTACTNUMBER` varchar(45) NOT NULL DEFAULT '',
  `CONTACTEMAIL` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_EMPLOYER_USER_idx` (`USER`),
  KEY `FK_TOWN_idx` (`TOWN`),
  KEY `FK_JOBCATEGORY_idx` (`INDUSTRY`),
  CONSTRAINT `FK_EMPLOYER_USER` FOREIGN KEY (`USER`) REFERENCES `systemuser` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_JOBCATEGORY` FOREIGN KEY (`INDUSTRY`) REFERENCES `jobcategory` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TOWN` FOREIGN KEY (`TOWN`) REFERENCES `town` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` (`CODE`, `NAME`, `USER`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `TOWN`, `INDUSTRY`, `CONTACTNUMBER`, `CONTACTEMAIL`) VALUES (1,'',1,0,0,'2016-08-13 21:28:43','','2016-08-13 21:28:43','',-1,-1,'','');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobapplication`
--

DROP TABLE IF EXISTS `jobapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobapplication` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `VACANCY` int(11) NOT NULL,
  `JOBSEEKER` int(11) NOT NULL,
  `APPLIEDON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_APPLICATION_VACANCY_idx` (`VACANCY`),
  KEY `FK_APPLICATION_JOBSEEKER_idx` (`JOBSEEKER`),
  CONSTRAINT `FK_APPLICATION_JOBSEEKER` FOREIGN KEY (`JOBSEEKER`) REFERENCES `jobseeker` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_APPLICATION_VACANCY` FOREIGN KEY (`VACANCY`) REFERENCES `vacancy` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobapplication`
--

LOCK TABLES `jobapplication` WRITE;
/*!40000 ALTER TABLE `jobapplication` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobcategory`
--

DROP TABLE IF EXISTS `jobcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobcategory` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobcategory`
--

LOCK TABLES `jobcategory` WRITE;
/*!40000 ALTER TABLE `jobcategory` DISABLE KEYS */;
INSERT INTO `jobcategory` (`CODE`, `NAME`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `STATUS`) VALUES (-1,'DEFAULT',0,0,'2016-08-13 21:27:47','','2016-08-13 21:27:47','',1),(1,'Software development',0,0,'2016-08-13 21:27:47','','2016-08-13 21:27:47','',1),(2,'Business management',0,0,'2016-08-13 21:27:47','','2016-08-13 21:27:47','',1);
/*!40000 ALTER TABLE `jobcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobseeker`
--

DROP TABLE IF EXISTS `jobseeker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobseeker` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(255) NOT NULL DEFAULT '',
  `LASTNAME` varchar(255) NOT NULL DEFAULT '',
  `DOB` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER` int(11) NOT NULL,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`CODE`),
  KEY `FK_JOBSEEKER_USER_idx` (`USER`),
  CONSTRAINT `FK_JOBSEEKER_USER` FOREIGN KEY (`USER`) REFERENCES `systemuser` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobseeker`
--

LOCK TABLES `jobseeker` WRITE;
/*!40000 ALTER TABLE `jobseeker` DISABLE KEYS */;
INSERT INTO `jobseeker` (`CODE`, `FIRSTNAME`, `LASTNAME`, `DOB`, `USER`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `STATUS`) VALUES (1,'','','2016-08-13 23:35:06',2,0,0,'2016-08-13 23:35:06','','2016-08-13 23:35:06','',1);
/*!40000 ALTER TABLE `jobseeker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `JOBSEEKER` int(11) NOT NULL,
  `PROFICIENCY` int(11) NOT NULL DEFAULT '0',
  KEY `FK_JOBSEEKER_idx` (`JOBSEEKER`),
  CONSTRAINT `FK_JOBSEEKER` FOREIGN KEY (`JOBSEEKER`) REFERENCES `jobseeker` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_role`
--

DROP TABLE IF EXISTS `permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_role` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` int(11) NOT NULL,
  `ROLENAME` varchar(255) NOT NULL DEFAULT '',
  `PERMISSION` int(11) NOT NULL,
  `PERMISSIONNAME` varchar(45) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_PERMISSION_ROLE_ROLE_idx` (`ROLE`),
  KEY `FK_PERMISSION_ROLE_PERMISSION_idx` (`PERMISSION`),
  CONSTRAINT `FK_PERMISSION_ROLE_PERMISSION` FOREIGN KEY (`PERMISSION`) REFERENCES `permission` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PERMISSION_ROLE_ROLE` FOREIGN KEY (`ROLE`) REFERENCES `role` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_role`
--

LOCK TABLES `permission_role` WRITE;
/*!40000 ALTER TABLE `permission_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qualification` (
  `CODE` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`CODE`, `NAME`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`) VALUES (1,'JOBSEEKER',0,0,'2016-08-13 21:27:46','MASTERDML','2016-08-13 21:27:46',''),(2,'EMPLOYER',0,0,'2016-08-13 21:27:46','MASTERDML','2016-08-13 21:27:46','');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `PRIMARYEMAIL` varchar(255) NOT NULL DEFAULT '',
  `USERNAME` varchar(255) NOT NULL DEFAULT '',
  `PASSWORD` varchar(255) NOT NULL DEFAULT '',
  `LASTLOGGEDON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
INSERT INTO `systemuser` (`CODE`, `PRIMARYEMAIL`, `USERNAME`, `PASSWORD`, `LASTLOGGEDON`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`) VALUES (1,'lol@gmail.com','brandix','$shiro1$SHA-256$500000$b0HF12NVy35bYEm3lLX5rQ==$n+GJ2BCxx49BrIYRdvOvTm+Ky41aZ0UOjJDCdtfOz0I=','2016-08-13 21:28:43',0,0,'2016-08-13 21:28:43','','2016-08-13 21:28:43',''),(2,'lol@gmail.com','sam','$shiro1$SHA-256$500000$z+FltX6FcFH0t5W/mNxpbA==$OMTOs7JY94TOPESJGw0vg3r6rJfULsiXDyA+VRqMJxA=','2016-08-13 23:35:06',0,0,'2016-08-13 23:35:06','','2016-08-13 23:35:06','');
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `town`
--

DROP TABLE IF EXISTS `town`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `town` (
  `CODE` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL DEFAULT '',
  `DISTRICT` int(11) DEFAULT '-1',
  `DSD` int(11) NOT NULL DEFAULT '-1',
  `COUNTRY` int(11) NOT NULL DEFAULT '-1',
  `PROVINCE` int(11) NOT NULL DEFAULT '-1',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `town`
--

LOCK TABLES `town` WRITE;
/*!40000 ALTER TABLE `town` DISABLE KEYS */;
INSERT INTO `town` (`CODE`, `NAME`, `DISTRICT`, `DSD`, `COUNTRY`, `PROVINCE`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `STATUS`) VALUES (-1,'DEFAULT',-1,-1,-1,-1,0,0,'2016-08-13 21:27:46','','2016-08-13 21:27:46','',1),(1,'Colombo 1',-1,-1,-1,-1,0,0,'2016-08-13 21:27:47','','2016-08-13 21:27:47','',1),(2,'Colombo 2',-1,-1,-1,-1,0,0,'2016-08-13 21:27:47','','2016-08-13 21:27:47','',1);
/*!40000 ALTER TABLE `town` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) NOT NULL,
  `ROLENAME` varchar(255) NOT NULL,
  `AUTHORITY` varchar(255) NOT NULL,
  `USER` int(11) NOT NULL,
  `ROLE` int(11) NOT NULL,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  KEY `FK_USER_ROLE_USER_idx` (`USER`),
  KEY `FK_USER_ROLE_ROLE_idx` (`ROLE`),
  CONSTRAINT `FK_USER_ROLE_ROLE` FOREIGN KEY (`ROLE`) REFERENCES `role` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_ROLE_USER` FOREIGN KEY (`USER`) REFERENCES `systemuser` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`CODE`, `USERNAME`, `ROLENAME`, `AUTHORITY`, `USER`, `ROLE`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`) VALUES (1,'brandix','EMPLOYER','',1,2,0,0,'2016-08-13 21:28:43','','2016-08-13 21:28:43',''),(2,'sam','JOBSEEKER','',2,1,0,0,'2016-08-13 23:35:06','','2016-08-13 23:35:06','');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy`
--

DROP TABLE IF EXISTS `vacancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) NOT NULL DEFAULT '',
  `EMPLOYER` int(11) NOT NULL,
  `PUBLISHEDON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REFRESHEDON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STARTDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ENDDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `BASIC` double NOT NULL DEFAULT '0',
  `ARTWORK` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_VACANCY_EMPLOYER_idx` (`EMPLOYER`),
  CONSTRAINT `FK_VACANCY_EMPLOYER` FOREIGN KEY (`EMPLOYER`) REFERENCES `employer` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy`
--

LOCK TABLES `vacancy` WRITE;
/*!40000 ALTER TABLE `vacancy` DISABLE KEYS */;
INSERT INTO `vacancy` (`CODE`, `TITLE`, `EMPLOYER`, `PUBLISHEDON`, `REFRESHEDON`, `STARTDATE`, `ENDDATE`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `BASIC`, `ARTWORK`) VALUES (1,'tester',1,'2016-08-13 23:16:21','2016-08-13 23:16:21','2016-05-05 00:00:00','2016-05-12 00:00:00',0,0,'2016-08-13 23:16:21','','2016-08-13 23:16:21','',5467,'');
/*!40000 ALTER TABLE `vacancy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy_jobcategory`
--

DROP TABLE IF EXISTS `vacancy_jobcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy_jobcategory` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `VACANCY` int(11) NOT NULL,
  `JOBCATEGORY` int(11) NOT NULL,
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  `STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`CODE`),
  KEY `FK_JOBCATEGORY_idx` (`JOBCATEGORY`),
  KEY `FK_VACANCY_idx` (`VACANCY`),
  CONSTRAINT `FK_JOBCATEGORY_VJ` FOREIGN KEY (`JOBCATEGORY`) REFERENCES `jobcategory` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_VACANCY_VJ` FOREIGN KEY (`VACANCY`) REFERENCES `vacancy` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy_jobcategory`
--

LOCK TABLES `vacancy_jobcategory` WRITE;
/*!40000 ALTER TABLE `vacancy_jobcategory` DISABLE KEYS */;
INSERT INTO `vacancy_jobcategory` (`CODE`, `VACANCY`, `JOBCATEGORY`, `MASK`, `SORT`, `CRTON`, `CRTBY`, `MODON`, `MODBY`, `STATUS`) VALUES (3,1,1,0,0,'2016-08-13 23:16:21','','2016-08-13 23:16:21','',1);
/*!40000 ALTER TABLE `vacancy_jobcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy_location`
--

DROP TABLE IF EXISTS `vacancy_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy_location` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `VACANCY` int(11) DEFAULT NULL,
  `TOWN` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CODE`),
  KEY `FK_VACANCY_idx` (`VACANCY`),
  KEY `FK_TOWN_idx` (`TOWN`),
  CONSTRAINT `FK_TOWN_VL` FOREIGN KEY (`TOWN`) REFERENCES `town` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_VACANCY_VL` FOREIGN KEY (`VACANCY`) REFERENCES `vacancy` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy_location`
--

LOCK TABLES `vacancy_location` WRITE;
/*!40000 ALTER TABLE `vacancy_location` DISABLE KEYS */;
INSERT INTO `vacancy_location` (`CODE`, `VACANCY`, `TOWN`) VALUES (3,1,1);
/*!40000 ALTER TABLE `vacancy_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workexperience`
--

DROP TABLE IF EXISTS `workexperience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workexperience` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `JOBSEEKER` int(11) NOT NULL,
  `WORKEDAT` varchar(255) NOT NULL DEFAULT '',
  `PERIOD` varchar(255) NOT NULL DEFAULT '',
  `POSITION` varchar(255) NOT NULL DEFAULT '',
  `MASK` int(11) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `CRTON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CRTBY` varchar(255) NOT NULL DEFAULT '',
  `MODON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODBY` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`CODE`),
  KEY `FK_WORKEXPERIENCE_JOBSEEKER_idx` (`JOBSEEKER`),
  CONSTRAINT `FK_WORKEXPERIENCE_JOBSEEKER` FOREIGN KEY (`JOBSEEKER`) REFERENCES `jobseeker` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workexperience`
--

LOCK TABLES `workexperience` WRITE;
/*!40000 ALTER TABLE `workexperience` DISABLE KEYS */;
/*!40000 ALTER TABLE `workexperience` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-16  1:49:19

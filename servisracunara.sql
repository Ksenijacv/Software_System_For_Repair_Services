/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.21-MariaDB : Database - servisracunara
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`servisracunara` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `servisracunara`;

/*Table structure for table `izvestajoservisu` */

DROP TABLE IF EXISTS `izvestajoservisu`;

CREATE TABLE `izvestajoservisu` (
  `IzvestajID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `UkupnaCena` int(100) unsigned NOT NULL,
  `DatumVremeIzvestaja` datetime NOT NULL,
  `Napomena` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Placanje` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PrijavaID` int(100) unsigned NOT NULL,
  PRIMARY KEY (`IzvestajID`,`PrijavaID`),
  KEY `FK_PRIJAVA_IZV` (`PrijavaID`),
  CONSTRAINT `FK_PRIJAVA_IZV` FOREIGN KEY (`PrijavaID`) REFERENCES `prijavakvara` (`PrijavaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `izvestajoservisu` */

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `ImePrezimeKlijenta` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Kontakt` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MestoID` int(100) unsigned NOT NULL,
  `ServiserID` int(100) unsigned NOT NULL,
  PRIMARY KEY (`KlijentID`),
  KEY `FK_MESTO` (`MestoID`),
  KEY `FK_SERVISER` (`ServiserID`),
  CONSTRAINT `FK_MESTO` FOREIGN KEY (`MestoID`) REFERENCES `mesto` (`MestoID`),
  CONSTRAINT `FK_SERVISER` FOREIGN KEY (`ServiserID`) REFERENCES `serviser` (`ServiserID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`ImePrezimeKlijenta`,`Kontakt`,`MestoID`,`ServiserID`) values 
(1,'Marko Markovic','06412',1,1),
(2,'Jovan Jovic','06123',2,1);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `MestoID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `NazivMesta` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PttBroj` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`MestoID`,`NazivMesta`,`PttBroj`) values 
(1,'Beograd','11000'),
(2,'Novi Sad ','12000'),
(3,'Kragujevac','13000');

/*Table structure for table `prijavakvara` */

DROP TABLE IF EXISTS `prijavakvara`;

CREATE TABLE `prijavakvara` (
  `PrijavaID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `OpisKvara` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `DatumVremePrijave` datetime NOT NULL,
  `Status` int(1) unsigned NOT NULL,
  `KlijentID` int(100) unsigned NOT NULL,
  `ServiserID` int(100) unsigned NOT NULL,
  PRIMARY KEY (`PrijavaID`),
  KEY `FK_PRIJAVA_KLIJ` (`KlijentID`),
  KEY `FK_PRIJAVA_SERV` (`ServiserID`),
  CONSTRAINT `FK_PRIJAVA_KLIJ` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`),
  CONSTRAINT `FK_PRIJAVA_SERV` FOREIGN KEY (`ServiserID`) REFERENCES `serviser` (`ServiserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `prijavakvara` */

/*Table structure for table `rezervnideo` */

DROP TABLE IF EXISTS `rezervnideo`;

CREATE TABLE `rezervnideo` (
  `RezervniDeoID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `NazivRezervnogDela` varchar(100) COLLATE utf8_unicode_520_nopad_ci NOT NULL,
  `CenaRezervnogDela` int(100) unsigned NOT NULL,
  `OpisRezervnogDela` varchar(500) COLLATE utf8_unicode_520_nopad_ci NOT NULL,
  `ServiserID` int(100) unsigned NOT NULL,
  PRIMARY KEY (`RezervniDeoID`),
  KEY `FK_DEO_SERV` (`ServiserID`),
  CONSTRAINT `FK_DEO_SERV` FOREIGN KEY (`ServiserID`) REFERENCES `serviser` (`ServiserID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_nopad_ci;

/*Data for the table `rezervnideo` */

insert  into `rezervnideo`(`RezervniDeoID`,`NazivRezervnogDela`,`CenaRezervnogDela`,`OpisRezervnogDela`,`ServiserID`) values 
(1,'RD 1',100,'Opis 1',1),
(3,'RD 2',200,'Opis 2',1),
(4,'RD 3',300,'Opis 3',1);

/*Table structure for table `serviser` */

DROP TABLE IF EXISTS `serviser`;

CREATE TABLE `serviser` (
  `ServiserID` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Lozinka` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ServiserID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `serviser` */

insert  into `serviser`(`ServiserID`,`KorisnickoIme`,`Lozinka`) values 
(1,'admin','admin');

/*Table structure for table `stavkaizvestaja` */

DROP TABLE IF EXISTS `stavkaizvestaja`;

CREATE TABLE `stavkaizvestaja` (
  `Rb` int(100) unsigned NOT NULL,
  `IzvestajID` int(100) unsigned NOT NULL,
  `PrijavaID` int(100) unsigned NOT NULL,
  `Kolicina` int(100) unsigned NOT NULL,
  `RezervniDeoID` int(100) unsigned NOT NULL,
  PRIMARY KEY (`Rb`,`IzvestajID`,`PrijavaID`),
  KEY `FK_DEO_STAVKA` (`RezervniDeoID`),
  KEY `FK_IZVES_STAVKA` (`IzvestajID`),
  KEY `FK_IZVEST_STAVKA_KVAR` (`PrijavaID`),
  CONSTRAINT `FK_DEO_STAVKA` FOREIGN KEY (`RezervniDeoID`) REFERENCES `rezervnideo` (`RezervniDeoID`),
  CONSTRAINT `FK_IZVEST_STAVKA_KVAR` FOREIGN KEY (`PrijavaID`) REFERENCES `izvestajoservisu` (`PrijavaID`),
  CONSTRAINT `FK_IZVES_STAVKA` FOREIGN KEY (`IzvestajID`) REFERENCES `izvestajoservisu` (`IzvestajID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkaizvestaja` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

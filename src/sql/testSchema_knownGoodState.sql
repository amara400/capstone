-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MCU_Dashboard_Test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MCU_Dashboard_Test
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `MCU_Dashboard_Test`;
CREATE DATABASE `MCU_Dashboard_Test`;
CREATE SCHEMA IF NOT EXISTS `MCU_Dashboard_Test` DEFAULT CHARACTER SET utf8 ;
USE `MCU_Dashboard_Test` ;

-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Movie` (
  `idMovie` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `UsReleaseDate` DATE NOT NULL,
  `runtime` INT NOT NULL,
  `ImdbRating` DECIMAL(2,1) NULL,
  `metascore` INT NULL,
  `budget` BIGINT(8) NULL,
  `domesticGross` BIGINT(8) NULL,
  `totalGross` BIGINT(16) NULL,
  `openingGross` BIGINT(8) NULL,
  `oscarNominations` INT NULL,
  `oscarsWon` INT NULL,
  `franchise` VARCHAR(45) NULL,
  PRIMARY KEY (`idMovie`),
  UNIQUE INDEX `idMovie_UNIQUE` (`idMovie` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Genre` (
  `idGenre` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Movie_Genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Movie_Genre` (
  `idMovie` INT NOT NULL,
  `idGenre` INT NOT NULL,
  INDEX `fk_Movie/Genre_Movie1_idx` (`idMovie` ASC) VISIBLE,
  PRIMARY KEY (`idMovie`,`idGenre`),
  CONSTRAINT `fk_Movie/Genre_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard_Test`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie/Genre_Genre1`
    FOREIGN KEY (`idGenre`)
    REFERENCES `MCU_Dashboard_Test`.`Genre` (`idGenre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Movie_Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Movie_Person` (
  `idMovie` INT NOT NULL,
  `idPerson` INT NOT NULL,
  INDEX `fk_Movie/Person_Movie1_idx` (`idMovie` ASC) VISIBLE,
  PRIMARY KEY (`idMovie`, `idPerson`),
  CONSTRAINT `fk_Movie/Person_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard_Test`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie/Person_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `MCU_Dashboard_Test`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`User` (
  `idUser` INT NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Favorites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Favorites` (
  `idUser` INT NOT NULL,
  `idMovie` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idMovie`),
  INDEX `fk_Favorites_Movie1_idx` (`idMovie` ASC) VISIBLE,
  CONSTRAINT `fk_Favorites_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `MCU_Dashboard_Test`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Favorites_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard_Test`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_Test`.`Person_Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MCU_Dashboard_Test`.`Person_Role` (
  `idPerson` INT NOT NULL,
  `idRole` INT NOT NULL,
  PRIMARY KEY (`idPerson`, `idRole`),
  INDEX `fk_Person_has_Role_Role1_idx` (`idRole` ASC) VISIBLE,
  INDEX `fk_Person_has_Role_Person1_idx` (`idPerson` ASC) VISIBLE,
  CONSTRAINT `fk_Person_has_Role_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `MCU_Dashboard_Test`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Role_Role1`
    FOREIGN KEY (`idRole`)
    REFERENCES `MCU_Dashboard_Test`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

delimiter //
create procedure set_known_good_state()
begin

set SQL_SAFE_UPDATES = 0;

delete from Movie;
alter table Movie auto_increment = 1;
delete from Genre;
alter table Genre auto_increment = 1;
delete from Person;
alter table Person auto_increment = 1;
delete from `Role`;
alter table `Role` auto_increment = 1;
delete from `User`;
alter table `User` auto_increment = 1;

delete from Movie_Genre;
delete from Movie_Person;
delete from Person_Role;
delete from Favorites;

insert into Movie(title, UsReleaseDate, runtime, ImdbRating, metascore, budget, domesticGross, totalGross, openingGross, oscarNominations, oscarsWon, franchise) values
	('Iron Man', '2008-05-02', 126, 7.9, 79, 140000000, 318604126, 585366247, 98618668, 2, 0, 'Iron Man'),
    ('The Incredible Hulk', '2008-06-13', 112, 6.6, 61, 150000000, 134806913, 264770996, 55414050, 0, 0, 'The Hulk'),
    ('Iron Man 2', '2010-05-07', 124, 7, 57, 200000000, 312433331, 623933331, 128122480, 1, 0, 'Iron Man'),
    ("Marvel's the Avengers", '2012-05-04', 143, 8, 69, 220000000, 623357910, 1518812988, 207438708, 1, 0, 'Avengers');
    
insert into Genre(`name`) values
	('Action'),
    ('Adventure'),
    ('Sci-fi');

insert into Movie_Genre(idMovie, idGenre) values
	(1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (3, 3),
    (4, 1),
    (4, 2),
    (4, 3);

insert into `Role` (`role`) values
	('Director'),
    ('Producer'),
    ('Cast');
    
insert into Person (`name`) values
	('Jon Favreau'),
    ('Avi Arad'),
    ('Kevin Feige'),
    ('Robert Downey Jr.'),
    ('Gweneth Paltrow'),
    ('Louis Leterrier'),
    ('Edward Norton'),
    ('Liv Tyler'),
    ('Joss Whedon'),
    ('Chris Evans');

insert into Person_Role (idPerson, idRole) values
	(1, 1),
    (2, 2),
    (3, 2),
    (4, 3),
    (5, 2),
    (6, 1),
    (7, 3),
    (8, 3),
    (9, 1),
    (10, 3);
    
insert into Movie_Person (idMovie, idPerson) values
	(1, 1),
    (1, 2),
    (1, 3),
    (1, 4), 
    (1, 5),
    (2, 6),
    (2, 2),
    (2, 3),
    (2, 7),
    (2, 8),
    (3, 1),
    (3, 3),
    (3, 4), 
    (3, 5),
    (4, 9),
    (4, 3),
    (4, 4), 
    (4, 10);






set SQL_SAFE_UPDATES = 1;
end//
delimiter ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MCU_Dashboard
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MCU_Dashboard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MCU_Dashboard` DEFAULT CHARACTER SET utf8 ;
USE `MCU_Dashboard` ;

-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Movie` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Movie` (
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
-- Table `MCU_Dashboard`.`Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Genre` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Genre` (
  `idGenre` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idGenre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Movie_Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Movie_Genre` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Movie_Genre` (
  `idMovie` INT NOT NULL,
  `idGenre` INT NOT NULL,
  INDEX `fk_Movie/Genre_Movie1_idx` (`idMovie` ASC) VISIBLE,
  PRIMARY KEY (`idMovie`,`idGenre`),
  CONSTRAINT `fk_Movie/Genre_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie/Genre_Genre1`
    FOREIGN KEY (`idGenre`)
    REFERENCES `MCU_Dashboard`.`Genre` (`idGenre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Person` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Movie_Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Movie_Person` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Movie_Person` (
  `idMovie` INT NOT NULL,
  `idPerson` INT NOT NULL,
  INDEX `fk_Movie/Person_Movie1_idx` (`idMovie` ASC) VISIBLE,
  PRIMARY KEY (`idMovie`, `idPerson`),
  CONSTRAINT `fk_Movie/Person_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie/Person_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `MCU_Dashboard`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Role` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`User` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`User` (
  `idUser` INT NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Favorites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Favorites` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Favorites` (
  `idUser` INT NOT NULL,
  `idMovie` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idMovie`),
  INDEX `fk_Favorites_Movie1_idx` (`idMovie` ASC) VISIBLE,
  CONSTRAINT `fk_Favorites_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `MCU_Dashboard`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Favorites_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`Person_Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`Person_Role` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`Person_Role` (
  `idPerson` INT NOT NULL,
  `idRole` INT NOT NULL,
  PRIMARY KEY (`idPerson`, `idRole`),
  INDEX `fk_Person_has_Role_Role1_idx` (`idRole` ASC) VISIBLE,
  INDEX `fk_Person_has_Role_Person1_idx` (`idPerson` ASC) VISIBLE,
  CONSTRAINT `fk_Person_has_Role_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `MCU_Dashboard`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Role_Role1`
    FOREIGN KEY (`idRole`)
    REFERENCES `MCU_Dashboard`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

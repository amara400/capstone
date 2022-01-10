
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
  `totalGross` BIGINT(8) NULL,
  `openingGross` BIGINT(8) NULL,
  `oscarNominations` INT NULL,
  `oscarsWon` INT NULL,
  `franchise` VARCHAR(45) NULL,
  PRIMARY KEY (`idMovie`),
  UNIQUE INDEX `idMovie_UNIQUE` (`idMovie` ASC) VISIBLE)
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
  `role` VARCHAR(45) NOT NULL,
  `idPerson` INT NOT NULL,
  INDEX `fk_Movie/Person_Movie1_idx` (`idMovie` ASC) VISIBLE,
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
-- Table `MCU_Dashboard`.`app_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`app_user` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`app_user` (
  `app_user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password_hash` VARCHAR(2048) NOT NULL,
  `disabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`app_user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`app_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`app_role` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`app_role` (
  `app_role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`app_role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard`.`app_user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard`.`app_user_role` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard`.`app_user_role` (
  `app_user_id` INT NOT NULL,
  `app_role_id` INT NOT NULL,
  PRIMARY KEY (`app_user_id`, `app_role_id`),
  INDEX `fk_app_user_has_app_role_app_role1_idx` (`app_role_id` ASC) VISIBLE,
  INDEX `fk_app_user_has_app_role_app_user1_idx` (`app_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_app_user_has_app_role_app_user1`
    FOREIGN KEY (`app_user_id`)
    REFERENCES `MCU_Dashboard`.`app_user` (`app_user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_app_user_has_app_role_app_role1`
    FOREIGN KEY (`app_role_id`)
    REFERENCES `MCU_Dashboard`.`app_role` (`app_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



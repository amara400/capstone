SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MCU_Dashboard_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MCU_Dashboard_test` DEFAULT CHARACTER SET utf8 ;
USE `MCU_Dashboard_test` ;

-- -----------------------------------------------------
-- Table `MCU_Dashboard_test`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard_test`.`Movie` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard_test`.`Movie` (
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
-- Table `MCU_Dashboard_test`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard_test`.`Person` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard_test`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MCU_Dashboard_test`.`Movie_Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MCU_Dashboard_test`.`Movie_Person` ;

CREATE TABLE IF NOT EXISTS `MCU_Dashboard_test`.`Movie_Person` (
  `idMovie` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `idPerson` INT NOT NULL,
  INDEX `fk_Movie/Person_Movie1_idx` (`idMovie` ASC) VISIBLE,
  CONSTRAINT `fk_Movie/Person_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `MCU_Dashboard_test`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie/Person_Person1`
    FOREIGN KEY (`idPerson`)
    REFERENCES `MCU_Dashboard_test`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

delimiter //
create procedure set_known_good_state()
begin

set SQL_SAFE_UPDATES = 0;

delete from Movie_Person;

delete from Person;
-- alter table Person auto_increment = 1;

delete from Movie;
alter table Movie auto_increment = 1;

insert into Movie(title, UsReleaseDate, runtime, ImdbRating, metascore, budget, domesticGross, totalGross, openingGross, oscarNominations, oscarsWon, franchise) values
	('Iron Man', '2008-05-02', 126, 7.9, 79, 140000000, 318604126, 585366247, 98618668, 2, 0, 'Iron Man'),
    ('The Incredible Hulk', '2008-06-13', 112, 6.6, 61, 150000000, 134806913, 264770996, 55414050, 0, 0, 'The Hulk'),
    ('Iron Man 2', '2010-05-07', 124, 7, 57, 200000000, 312433331, 623933331, 128122480, 1, 0, 'Iron Man'),
    ("Marvel's the Avengers", '2012-05-04', 143, 8, 69, 220000000, 623357910, 1518812988, 207438708, 1, 0, 'Avengers');
    
    
insert into Person (idPerson, `name`) values
(1,	'Jon Favreau'),
(2,	'Avi Arad'),
(3,	'Kevin Feige'),
(4,	'Robert Downey Jr.'), 
(5,	'Gweneth Paltrow'), 
(6,	'Terrence Howard'),
(7,	'Louis Leterrier'),
(8,	'Gale Ann Hurd'),
(9,	'Edward Norton'), 
(10, 'Liv Tyler'), 
(11, 'Tim Roth'),
(12, 'Mickey Rourke'),
(13, 'Kenneth Branagh'),
(14, 'Chris Hemsworth'), 
(15, 'Anthony Hopkins'), 
(16, 'Natalie Portman');

    
insert into Movie_Person (idMovie, `role` ,idPerson) values
(1,	'Director', 1),
(1,	'Producer', 2),
(1,	'Producer', 3),
(1,	'Cast', 4),
(1,	'Cast', 5),
(1,	'Cast', 6),
(2,	'Director', 7),
(2,	'Producer', 2),
(2,	'Producer', 8),
(2,	'Producer', 3),
(2,	'Cast', 9),
(2,	'Cast', 10),
(2,	'Cast', 11),
(3,	'Director', 1),
(3,	'Producer', 3),
(3,	'Cast', 4),
(3,	'Cast', 5),
(3,	'Cast', 12),
(4,	'Director', 13),
(4,	'Producer', 3),
(4,	'Cast', 14),
(4,	'Cast', 15),
(4,	'Cast', 16);

set SQL_SAFE_UPDATES = 1;
end//
delimiter ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

call set_known_good_state();

select * from Movie;
select * from Person;
select * from Movie_Person;

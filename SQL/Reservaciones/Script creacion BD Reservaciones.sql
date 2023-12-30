-- MySQL Script generated by MySQL Workbench
-- lun 18 nov 2022 17:08:27 CST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering 

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Reservaciones
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Reservaciones
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Reservaciones` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `Reservaciones` ;

-- -----------------------------------------------------
-- Table `Reservaciones`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Reservaciones`.`Usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Reservaciones`.`Reservaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Reservaciones`.`Reservaciones` (
  `idReservacion` INT(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idHotel` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  `Monto` DOUBLE UNSIGNED NOT NULL,
  `Estatus` VARCHAR(45) NOT NULL,
  `idHabitacion` INT NOT NULL,
  PRIMARY KEY (`idReservacion`),
  INDEX `fk_Reservaciones_Usuarios_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Reservaciones_Usuarios`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Reservaciones`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'usuario' IDENTIFIED BY 'oracle';

GRANT ALL ON `Reservaciones`.* TO 'usuario';
GRANT SELECT, INSERT, TRIGGER ON TABLE `Reservaciones`.* TO 'usuario';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `Reservaciones`.* TO 'usuario';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

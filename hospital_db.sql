-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hospital_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hospital_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital_db` DEFAULT CHARACTER SET utf8 ;
USE `hospital_db` ;

-- -----------------------------------------------------
-- Table `hospital_db`.`hospital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`hospital` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital_db`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`doctor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `especialidad` VARCHAR(50) NOT NULL,
  `hospital_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_doctor_hospital_idx` (`hospital_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_hospital`
    FOREIGN KEY (`hospital_id`)
    REFERENCES `hospital_db`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital_db`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital_db`.`paciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `diagnostico` VARCHAR(100) NOT NULL,
  `fecha_cita` DATE NOT NULL,
  `numero_habitacion` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `hospital_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_paciente_doctor1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_paciente_hospital1_idx` (`hospital_id` ASC) VISIBLE,
  CONSTRAINT `fk_paciente_doctor1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital_db`.`doctor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paciente_hospital1`
    FOREIGN KEY (`hospital_id`)
    REFERENCES `hospital_db`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO hospital (nombre, direccion, telefono) VALUES
('St. Mary Hospital', '123 Main Street', '555-1234'),
('City Hospital', '456 Maple Avenue', '555-5678');

INSERT INTO doctor (nombre, especialidad, hospital_id) VALUES
('Dr. Smith', 'Cardiology', 1),
('Dr. Johnson', 'Oncology', 1),
('Dr. Lee', 'Pediatrics', 2),
('Dr. Chen', 'Orthopedics', 2);

INSERT INTO paciente (nombre, edad, genero, diagnostico, fecha_cita, numero_habitacion, doctor_id, hospital_id) VALUES
('John Doe', 45, 'M', 'Pneumonia', '2023-04-15', 101, 1, 1),
('Jane Smith', 32, 'F', 'Appendicitis', '2023-04-02', 201, 2, 1),
('Bob Johnson', 68, 'M', 'Heart attack', '2023-04-03', 304, 1, 1),
('Alice Brown', 52, 'F', 'Cancer', '2023-05-01', 102, 2, 1),
('Mike Davis', 25, 'M', 'Broken leg', '2023-04-04', 203, 3, 2);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

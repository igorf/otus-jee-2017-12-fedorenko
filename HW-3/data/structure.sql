-- Valentina Studio --
-- MySQL dump --
-- ---------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- ---------------------------------------------------------


-- CREATE TABLE "Employee" ---------------------------------
-- CREATE TABLE "Employee" -------------------------------------
CREATE TABLE `Employee` ( 
	`id` BigInt( 20 ) AUTO_INCREMENT NOT NULL,
	`city` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`email` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`firstname` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`lastname` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`login` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`password` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`phone` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`position` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`salary` Int( 11 ) NULL,
	`unit` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`role_id` BigInt( 20 ) NULL,
	PRIMARY KEY ( `id` ),
	CONSTRAINT `UK_2y5g3ij0kgtvrlp3rtmjlabj4` UNIQUE( `email` ),
	CONSTRAINT `UK_331sk8mksaog7t7dryttfng3b` UNIQUE( `login` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 4;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE TABLE "Role" -------------------------------------
-- CREATE TABLE "Role" -----------------------------------------
CREATE TABLE `Role` ( 
	`id` BigInt( 20 ) AUTO_INCREMENT NOT NULL,
	`role` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `id` ),
	CONSTRAINT `UK_33x416oeil31hpge9a6qc8jau` UNIQUE( `role` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE INDEX "FK_sqjn246r85wisl3om900th5q1" -------------
-- CREATE INDEX "FK_sqjn246r85wisl3om900th5q1" -----------------
CREATE INDEX `FK_sqjn246r85wisl3om900th5q1` USING BTREE ON `Employee`( `role_id` );
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "FK_sqjn246r85wisl3om900th5q1" --------------
-- CREATE LINK "FK_sqjn246r85wisl3om900th5q1" ------------------
ALTER TABLE `Employee`
	ADD CONSTRAINT `FK_sqjn246r85wisl3om900th5q1` FOREIGN KEY ( `role_id` )
	REFERENCES `Role`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE FUNCTION "max_salary_employee" -------------------

delimiter $$$ 
-- CREATE FUNCTION "max_salary_employee" -----------------------
CREATE DEFINER=`root`@`localhost` PROCEDURE `max_salary_employee`()
BEGIN
select lastname from Employee order by salary DESC LIMIT 1;
END;
-- -------------------------------------------------------------

$$$ delimiter ;
-- ---------------------------------------------------------


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- ---------------------------------------------------------



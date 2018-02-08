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
	`salary` Int( 11 ) NULL,
	`unit` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`position_id` BigInt( 20 ) NULL,
	`role_id` BigInt( 20 ) NULL,
	PRIMARY KEY ( `id` ),
	CONSTRAINT `UK_2y5g3ij0kgtvrlp3rtmjlabj4` UNIQUE( `email` ),
	CONSTRAINT `UK_331sk8mksaog7t7dryttfng3b` UNIQUE( `login` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 3;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE TABLE "Position" ---------------------------------
-- CREATE TABLE "Position" -------------------------------------
CREATE TABLE `Position` ( 
	`id` BigInt( 20 ) AUTO_INCREMENT NOT NULL,
	`position` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `id` ),
	CONSTRAINT `UK_3tq75kxn14gki61rtq7odgfnq` UNIQUE( `position` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 3;
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
AUTO_INCREMENT = 5;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- Dump data of "Employee" ---------------------------------
INSERT INTO `Employee`(`id`,`city`,`email`,`firstname`,`lastname`,`login`,`password`,`phone`,`salary`,`unit`,`position_id`,`role_id`) VALUES ( '1', 'Berlin', 'xxx@example.com', 'Xxx', 'Yyy', 'xxx', 'qwerty123', '1234567890', '10000', 'Unit #1', '1', '1' );
INSERT INTO `Employee`(`id`,`city`,`email`,`firstname`,`lastname`,`login`,`password`,`phone`,`salary`,`unit`,`position_id`,`role_id`) VALUES ( '2', 'London', 'john@ex.com', 'John', 'Doe', 'johndoe', 'johndoe', '+4124453556', '12000', 'Unit #2', '2', '2' );
-- ---------------------------------------------------------


-- Dump data of "Position" ---------------------------------
INSERT INTO `Position`(`id`,`position`) VALUES ( '1', 'Employee' );
INSERT INTO `Position`(`id`,`position`) VALUES ( '2', 'Supervisor' );
-- ---------------------------------------------------------


-- Dump data of "Role" -------------------------------------
INSERT INTO `Role`(`id`,`role`) VALUES ( '4', 'admin' );
INSERT INTO `Role`(`id`,`role`) VALUES ( '1', 'employee' );
INSERT INTO `Role`(`id`,`role`) VALUES ( '2', 'finance' );
INSERT INTO `Role`(`id`,`role`) VALUES ( '3', 'hr' );
-- ---------------------------------------------------------


-- CREATE INDEX "FK6d2ec6vi3nvplfkiv3tmg870e" --------------
-- CREATE INDEX "FK6d2ec6vi3nvplfkiv3tmg870e" ------------------
CREATE INDEX `FK6d2ec6vi3nvplfkiv3tmg870e` USING BTREE ON `Employee`( `role_id` );
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE INDEX "FK7tknxvg2w7in1sm3bdhs39hre" --------------
-- CREATE INDEX "FK7tknxvg2w7in1sm3bdhs39hre" ------------------
CREATE INDEX `FK7tknxvg2w7in1sm3bdhs39hre` USING BTREE ON `Employee`( `position_id` );
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "FK6d2ec6vi3nvplfkiv3tmg870e" ---------------
-- CREATE LINK "FK6d2ec6vi3nvplfkiv3tmg870e" -------------------
ALTER TABLE `Employee`
	ADD CONSTRAINT `FK6d2ec6vi3nvplfkiv3tmg870e` FOREIGN KEY ( `role_id` )
	REFERENCES `Role`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "FK7tknxvg2w7in1sm3bdhs39hre" ---------------
-- CREATE LINK "FK7tknxvg2w7in1sm3bdhs39hre" -------------------
ALTER TABLE `Employee`
	ADD CONSTRAINT `FK7tknxvg2w7in1sm3bdhs39hre` FOREIGN KEY ( `position_id` )
	REFERENCES `Position`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------
-- ---------------------------------------------------------


-- CREATE LINK "FK_9c4q00vudumkn18cc56spvyvr" --------------
-- CREATE LINK "FK_9c4q00vudumkn18cc56spvyvr" ------------------
ALTER TABLE `Employee`
	ADD CONSTRAINT `FK_9c4q00vudumkn18cc56spvyvr` FOREIGN KEY ( `position_id` )
	REFERENCES `Position`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
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


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- ---------------------------------------------------------



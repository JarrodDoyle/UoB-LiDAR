DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(150) NOT NULL UNIQUE,
	`pw_hash` VARCHAR(88) NOT NULL,
	PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `previous_logons`;
CREATE TABLE `previous_logons` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`occured` DATETIME NOT NULL,
	`user_id` INT NOT NULL,
	`ip` VARCHAR(16) NOT NULL,
	`browser` VARCHAR(64) NOT NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE `tokens` (
	`token` VARCHAR(64) NOT NULL,
	`user_id` INT NOT NULL,
	`master` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`token`)
);

DROP TABLE IF EXISTS `token_perms`;
CREATE TABLE `token_perms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(64) NOT NULL,
	`site_id` VARCHAR(64) NOT NULL,
	`read` BOOLEAN NOT NULL DEFAULT false,
	`write` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `token_log`;
CREATE TABLE `token_log` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(128) NOT NULL,
	`occured` DATETIME NOT NULL,
	`action` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `previous_logons` ADD CONSTRAINT `previous_logons_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `tokens` ADD CONSTRAINT `tokens_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `token_perms` ADD CONSTRAINT `token_perms_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `token_log` ADD CONSTRAINT `token_log_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);

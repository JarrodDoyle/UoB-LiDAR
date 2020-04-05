CREATE TABLE `accounts` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(150) NOT NULL UNIQUE,
	`pw_hash` VARCHAR(100) NOT NULL,
	`salt` BINARY(64) NOT NULL,
	`organisation_id` INT,
	`organisation_perm` VARCHAR(255) NOT NULL DEFAULT 'none',
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `previous_logons` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`occurred` DATETIME NOT NULL,
	`ip` VARCHAR(16) NOT NULL,
	`platform` VARCHAR(20) NOT NULL,
	`browser_name` VARCHAR(20) NOT NULL,
	`browser_version` VARCHAR(20) NOT NULL,
	`language` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `tokens` (
	`token` VARCHAR(88) NOT NULL,
	`user_id` INT NOT NULL,
	`master` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`token`)
);

CREATE TABLE `token_perms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(64) NOT NULL,
	`site_id` INT NOT NULL,
	`read` BOOLEAN NOT NULL DEFAULT '0',
	`write` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`id`)
);

CREATE TABLE `token_log` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(128) NOT NULL,
	`occured` DATETIME NOT NULL,
	`action` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `organisation` (
	`organisation_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`organisation_id`)
);

CREATE TABLE `site` (
	`site_id` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`site_id`)
);

CREATE TABLE `account_site_perms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`site_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`read` BOOLEAN NOT NULL DEFAULT '0',
	`write` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `organisation_perm` (
	`id` VARCHAR(255) NOT NULL,
	`write_meta` BOOLEAN NOT NULL DEFAULT '0',
	`add_user` BOOLEAN NOT NULL DEFAULT '0',
	`add_site` BOOLEAN NOT NULL DEFAULT '0',
	`change_user_perms` BOOLEAN NOT NULL DEFAULT '0',
	`grant_site_access` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `site_access` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`site_id` INT NOT NULL,
	`organisation_id` INT NOT NULL,
	`write` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

ALTER TABLE `accounts` ADD CONSTRAINT `accounts_fk0` FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`);
ALTER TABLE `accounts` ADD CONSTRAINT `accounts_fk1` FOREIGN KEY (`organisation_perm`) REFERENCES `organisation_perm`(`id`);
ALTER TABLE `previous_logons` ADD CONSTRAINT `previous_logons_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `tokens` ADD CONSTRAINT `tokens_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `token_perms` ADD CONSTRAINT `token_perms_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `token_perms` ADD CONSTRAINT `token_perms_fk1` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);
ALTER TABLE `token_log` ADD CONSTRAINT `token_log_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `account_site_perms` ADD CONSTRAINT `account_site_perms_fk0` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);
ALTER TABLE `account_site_perms` ADD CONSTRAINT `account_site_perms_fk1` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `site_access` ADD CONSTRAINT `site_access_fk0` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);
ALTER TABLE `site_access` ADD CONSTRAINT `site_access_fk1` FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`);

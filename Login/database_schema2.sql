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

CREATE TABLE `token_log` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(88) NOT NULL,
	`occured` DATETIME NOT NULL,
	`action` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `token_perms_sites` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(88) NOT NULL,
	`site_id` INT NOT NULL,
	`read` BOOLEAN NOT NULL DEFAULT '0',
	`write` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`id`)
);

CREATE TABLE `organisation` (
	`organisation_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`organisation_id`)
);

CREATE TABLE `site` (
	`site_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`description` VARCHAR(256) NOT NULL,
        `lat` DECIMAL(9,6) NOT NULL,
        `lng` DECIMAL(9,6) NOT NULL,
	PRIMARY KEY (`site_id`)
);

CREATE TABLE `account_organisation_perm` (
	`id` VARCHAR(255) NOT NULL,
	`write_meta` BOOLEAN NOT NULL DEFAULT '0',
	`add_user` BOOLEAN NOT NULL DEFAULT '0',
	`add_site` BOOLEAN NOT NULL DEFAULT '0',
	`add_lidar` BOOLEAN NOT NULL DEFAULT '0',
	`change_user_perms` BOOLEAN NOT NULL DEFAULT '0',
	`grant_site_access` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `organisation_site_access` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`site_id` INT NOT NULL,
	`organisation_id` INT NOT NULL,
	`write` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `token_perms_lidars` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`token` VARCHAR(88) NOT NULL,
	`lidar_id` INT NOT NULL,
	`read` BOOLEAN NOT NULL DEFAULT '0',
	`write` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`id`)
);

CREATE TABLE `lidar` (
	`lidar_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`site_id` INT NOT NULL,
	PRIMARY KEY (`lidar_id`)
);

CREATE TABLE `organisation_lidar_access` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`lidar_id` INT NOT NULL,
	`organisation_id` INT NOT NULL,
	`write` BOOLEAN NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

ALTER TABLE `accounts` ADD CONSTRAINT `accounts_fk0` FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`);
ALTER TABLE `accounts` ADD CONSTRAINT `accounts_fk1` FOREIGN KEY (`organisation_perm`) REFERENCES `account_organisation_perm`(`id`);
ALTER TABLE `previous_logons` ADD CONSTRAINT `previous_logons_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);

ALTER TABLE `tokens` ADD CONSTRAINT `tokens_fk0` FOREIGN KEY (`user_id`) REFERENCES `accounts`(`user_id`);
ALTER TABLE `token_log` ADD CONSTRAINT `token_log_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `token_perms_sites` ADD CONSTRAINT `token_perms_sites_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `token_perms_sites` ADD CONSTRAINT `token_perms_sites_fk1` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);
ALTER TABLE `token_perms_lidars` ADD CONSTRAINT `token_perms_lidars_fk0` FOREIGN KEY (`token`) REFERENCES `tokens`(`token`);
ALTER TABLE `token_perms_lidars` ADD CONSTRAINT `token_perms_lidars_fk1` FOREIGN KEY (`lidar_id`) REFERENCES `lidar`(`lidar_id`);
ALTER TABLE `lidar` ADD CONSTRAINT `lidar_fk0` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);

ALTER TABLE `organisation_site_access` ADD CONSTRAINT `organisation_site_access_fk0` FOREIGN KEY (`site_id`) REFERENCES `site`(`site_id`);
ALTER TABLE `organisation_site_access` ADD CONSTRAINT `organisation_site_access_fk1` FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`);
ALTER TABLE `organisation_lidar_access` ADD CONSTRAINT `organisation_lidar_access_fk0` FOREIGN KEY (`lidar_id`) REFERENCES `lidar`(`lidar_id`);
ALTER TABLE `organisation_lidar_access` ADD CONSTRAINT `organisation_lidar_access_fk1` FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`);

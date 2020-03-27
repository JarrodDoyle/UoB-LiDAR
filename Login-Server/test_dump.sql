DROP TABLE IF EXISTS `logins`;
CREATE TABLE `logins` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(255) NOT NULL UNIQUE,
	`pw_hash` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `loaded_files`;
CREATE TABLE `time_stamps` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`time` TIME NOT NULL,
	`user_id` INT NOT NULL,
	`ip` VARCHAR(255) NOT NULL,
	`browser` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `time_stamps` ADD CONSTRAINT `time_stamps_fk0` FOREIGN KEY (`user_id`) REFERENCES `logins`(`user_id`);



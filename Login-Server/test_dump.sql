DROP TABLE IF EXISTS `logins`;
CREATE TABLE `logins` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(150) NOT NULL UNIQUE,
	`pw_hash` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `loaded_files`;
CREATE TABLE `time_stamps` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`time` TIME NOT NULL,
	`user_id` INT NOT NULL,
	`ip` VARCHAR(16) NOT NULL,
	`browser` VARCHAR(64) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `time_stamps` ADD CONSTRAINT `time_stamps_fk0` FOREIGN KEY (`user_id`) REFERENCES `logins`(`user_id`);



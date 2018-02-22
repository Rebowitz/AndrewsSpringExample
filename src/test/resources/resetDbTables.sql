SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP TABLE `roles_privileges`;
DROP TABLE `privileges`;
DROP TABLE `userPasswordResetToken`;
DROP TABLE `userVerificationToken`;
DROP TABLE `user_roles`;
DROP TABLE `roles`;

CREATE TABLE IF NOT EXISTS `privileges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FK5duhoc7rwt8h06avv41o41cfy` (`privilege_id`),
  KEY `FK629oqwrudgp5u7tewl07ayugj` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `userPasswordResetToken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `newEncryptPassword` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5je774r7gry8c2maaovfivjfe` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `userVerificationToken` (
  `id` bigint(20) NOT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `previousToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VERIFY_USER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_Id` bigint(20) NOT NULL,
  `role_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_Id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    
INSERT INTO privileges(`id`,`name`) VALUES(null,'READ_PRIVILEGE');
INSERT INTO privileges(`id`,`name`) VALUES(null,'WRITE_PRIVILEGE');		
INSERT INTO roles (`id`,`name`,`dateCreated`,`lastUpdated`,`version`) VALUES (NULL,'ROLE_ADMIN',DATE( NOW( )),DATE( NOW( )),'1');
INSERT INTO roles (`id`,`name`,`dateCreated`,`lastUpdated`,`version`) VALUES (NULL,'ROLE_USER',DATE( NOW( )),DATE( NOW( )),'1');
INSERT INTO roles_privileges (`role_id`, `privilege_id`) VALUES(1,1);
INSERT INTO roles_privileges (`role_id`, `privilege_id`) VALUES(1,2);
INSERT INTO roles_privileges (`role_id`, `privilege_id`) VALUES(2,1);
INSERT INTO roles_privileges (`role_id`, `privilege_id`) VALUES(2,2);    
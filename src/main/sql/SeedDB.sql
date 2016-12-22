
INSERT INTO `Accounts` (`id`, `AccountNonExpired`, `AccountNonLocked`, `CreateDate`, `CredentialsNonExpired`, `Email`, `Enabled`, `FirstName`, `LastName`, `LoginId`, `OrgName`, `Password`, `Plan`, `ProfileComplete`, `Role`) VALUES
(1, b'1', b'1', '2016-11-30 21:44:00', b'1', 'rbais@rbais.com', b'1', 'Robert', 'Andrews', 'aexample', 'RBA Internet Services, LLC', '$2a$04$8RDP.CPL4Unv5hrpj96gyu47RxApVQHKKMTC.SqhH6RIby3GWrRaC', NULL, b'0', 'ROLE_ADMIN'),
(2, b'1', b'1', '2016-12-05 00:00:00', b'1', 'rbais@rbais.com', b'1', 'ATest', 'User', 'atestuser', 'none', '$2a$04$xW/F.lnvdyQcLGnaVulVq.HzLE02OT6lPttEWiVLUCAaIsh6MkBM6', 'BASIC', b'0', 'ROLE_USER');

INSERT INTO `UserRole` (`id`, `LoginId`, `Role`) values
(1,'aexample','ROLE_ADMIN');
INSERT INTO `UserRole` (`id`, `LoginId`, `Role`) values
(2,'atestuser','ROLE_USER');
